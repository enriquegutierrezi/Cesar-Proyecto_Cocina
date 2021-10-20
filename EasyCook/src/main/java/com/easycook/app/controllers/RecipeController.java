package com.easycook.app.controllers;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Ingredient;
import com.easycook.app.entities.IngredientRecipe;
import com.easycook.app.entities.Recipe;
import com.easycook.app.exceptions.AlreadyExistException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecipeController {
    private final Gson gson;
    private final IngredientController ingredientController;

    public RecipeController() {
        this.gson = new GsonBuilder().create();
        this.ingredientController = new IngredientController();
    }

    public static ArrayList<Recipe> getAllRecipes() {
        Gson localGson = new GsonBuilder().create();
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(localGson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }
        return recipes;
    }

    public ArrayList<Recipe> findAllRecipes() {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(this.gson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }
        return recipes;
    }

    public Recipe findByName(String name) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByName(Recipe.COLLECTION_NAME, name);

        return this.gson.fromJson(document.toJson(), Recipe.class);
    }

    public Recipe findByLocalID(int id) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByLocalID(Recipe.COLLECTION_NAME, id);

        return this.gson.fromJson(document.toJson(), Recipe.class);
    }

    public List<Recipe> findByIngredient(String ingredient) {
        ArrayList<Recipe> recipes = RecipeController.getAllRecipes();
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .anyMatch(ing -> ing.getName().toLowerCase().contains(ingredient.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Recipe> findByAmount(int amount) {
        ArrayList<Recipe> recipes = RecipeController.getAllRecipes();
        return recipes.stream().filter(recipe -> recipe.getAmountPeople() >= amount).collect(Collectors.toList());
    }

    public List<Recipe> findByCookingTime(int time) {
        ArrayList<Recipe> recipes = RecipeController.getAllRecipes();
        return recipes.stream().filter(recipe -> recipe.getCookingTime() <= time).collect(Collectors.toList());
    }

    public List<Recipe> findByRecipeName(String name) {
        ArrayList<Recipe> recipes = RecipeController.getAllRecipes();
        return recipes.stream().filter(recipe -> recipe.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Recipe createRecipe(Recipe recipe) throws AlreadyExistException {
        ArrayList<Recipe> recipes = RecipeController.getAllRecipes();
        boolean existRecipe = recipes.stream().anyMatch(c -> c.getName().equals(recipe.getName()));

        if (existRecipe) {
            throw new AlreadyExistException(String.format("Recipe %s already created", recipe.getName()));
        }

        ArrayList<Ingredient> currentIngredients = this.ingredientController.findAllIngredients();
        ArrayList<IngredientRecipe> recipeIngredients = recipe.getIngredients();

        recipeIngredients.forEach(ingredient -> {
            Optional<Ingredient> existingIngredient = currentIngredients.stream()
                    .filter(ing -> ing.getName().equalsIgnoreCase(ingredient.getName())
                            && ing.getState() == ingredient.getState())
                    .findAny();
            if (existingIngredient.isPresent())
                ingredient.setId(existingIngredient.get().getId());
            else {
                Ingredient newIngredient = new Ingredient(ingredientController.getNextId(), ingredient.getName(), ingredient.getState());
                ingredient.setId(ingredientController.getNextId());
                try {
                    ingredientController.createIngredient(newIngredient);
                } catch (AlreadyExistException e) {
                    System.out.println("Ingredient already exist");
                }
            }
        });

        recipe.setIngredients(recipeIngredients);

        String temp = this.gson.toJson(recipe);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Recipe.COLLECTION_NAME, d);

        return recipe;
    }

    public Recipe updateRecipe(Recipe recipe) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoCollection<Document> collection = MongoConnection.findCollection(Recipe.COLLECTION_NAME);

        BasicDBObject doc = new BasicDBObject();
        doc.put("Recipe", recipe.getId());
        collection.deleteOne(doc);

        String temp = this.gson.toJson(recipe);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Recipe.COLLECTION_NAME, d);

        return recipe;
    }

    public Recipe deleteRecipe(Recipe recipe) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoConnection.deleteByLocalID(Recipe.COLLECTION_NAME, recipe.getId());
        return recipe;
    }

    public int getNextId() {
        ArrayList<Recipe> recipes = RecipeController.getAllRecipes();
        int lastId = recipes.size() > 0 ? recipes.stream().max(Comparator.comparing(Recipe::getId)).get().getId() : 0;
        return lastId + 1;
    }
}

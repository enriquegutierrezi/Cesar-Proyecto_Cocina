package com.easycook.app.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Ingredient;
import com.easycook.app.entities.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

public class RecipeController {
    private Gson gson;
    private IngredientController ingredientController;

    public RecipeController() {
        this.gson = new GsonBuilder().create();
        this.ingredientController = new IngredientController();
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
        Recipe recipe = this.gson.fromJson(document.toJson(), Recipe.class);

        return recipe;
    }

    public Recipe findByLocalID(int id) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByLocalID(Recipe.COLLECTION_NAME, id);
        Recipe recipe = this.gson.fromJson(document.toJson(), Recipe.class);

        return recipe;
    }

    public List<Recipe> findByIngredient(String ingredient) {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(this.gson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }

        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .filter(ing -> ing.getName().toLowerCase().contains(ingredient.toLowerCase())).findAny()
                        .isPresent())
                .collect(Collectors.toList());
    }

    public List<Recipe> findByAmount(int amount) {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(this.gson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }

        return recipes.stream().filter(recipe -> recipe.getAmountPeople() >= amount).collect(Collectors.toList());
    }

    public List<Recipe> findByCookingTime(int time) {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(this.gson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }

        return recipes.stream().filter(recipe -> recipe.getCookingTime() <= time).collect(Collectors.toList());
    }

    public List<Recipe> findByRecipeName(String name) {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(this.gson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }

        return recipes.stream().filter(recipe -> recipe.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Recipe createRecipe(Recipe recipe) {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(this.gson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }

        Boolean existRecipe = recipes.stream().filter(c -> c.getName().equals(recipe.getName())).findAny().isPresent();

        if (existRecipe) {
            System.out.println("Recipe ya existe");
            return null;
        }

        ArrayList<Ingredient> currentIngredients = this.ingredientController.findAllIngredients();
        ArrayList<Ingredient> recipeIngredients = recipe.getIngredients();

        recipeIngredients.forEach(ingredient -> {
            Optional<Ingredient> existingIngredient = currentIngredients.stream()
                    .filter(ing -> ing.getName().toLowerCase().equals(ingredient.getName().toLowerCase())
                            && Character.compare(ing.getState(), ingredient.getState()) == 0)
                    .findAny();
            if (existingIngredient.isPresent())
                ingredient.setId(existingIngredient.get().getId());
            else {
                ingredient.setId(ingredientController.getNextId());
                ingredientController.createIngredient(ingredient);
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
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Recipe> recipes = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Recipe.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                recipes.add(this.gson.fromJson(cursor.next().toJson(), Recipe.class));
            }
        }

        int lastId = recipes.size() > 0 ? recipes.stream().max(Comparator.comparing(Recipe::getId)).get().getId() : 0;
        return lastId + 1;
    }
}

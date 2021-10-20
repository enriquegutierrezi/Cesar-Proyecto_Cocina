package com.easycook.app.controllers;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Ingredient;
import com.easycook.app.exceptions.AlreadyExistException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;

public class IngredientController {
    private final Gson gson;

    public IngredientController() {
        this.gson = new GsonBuilder().create();
    }

    public static ArrayList<Ingredient> getAllIngredients() {
        Gson localGson = new GsonBuilder().create();
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Ingredient.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                ingredients.add(localGson.fromJson(cursor.next().toJson(), Ingredient.class));
            }
        }
        return ingredients;
    }

    public ArrayList<Ingredient> findAllIngredients() {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Ingredient.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                ingredients.add(this.gson.fromJson(cursor.next().toJson(), Ingredient.class));
            }
        }
        return ingredients;
    }

    public Ingredient findByName(String name) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByName(Ingredient.COLLECTION_NAME, name);

        return this.gson.fromJson(document.toJson(), Ingredient.class);
    }

    public Ingredient findByLocalID(int id) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByLocalID(Ingredient.COLLECTION_NAME, id);

        return this.gson.fromJson(document.toJson(), Ingredient.class);
    }

    public Ingredient createIngredient(Ingredient ingredient) throws AlreadyExistException {
        ArrayList<Ingredient> ingredients = IngredientController.getAllIngredients();

        boolean existIngredient = ingredients.stream().anyMatch(c -> c.getName().equals(ingredient.getName()) && c.getState() == ingredient.getState());

        if (existIngredient) {
            throw new AlreadyExistException(String.format("Recipe %s with status %s already exists", ingredient.getName(), ingredient.getState()));
        }

        String temp = this.gson.toJson(ingredient);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Ingredient.COLLECTION_NAME, d);

        return ingredient;
    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoCollection<Document> collection = MongoConnection.findCollection(Ingredient.COLLECTION_NAME);

        BasicDBObject doc = new BasicDBObject();
        doc.put("Ingredient", ingredient.getId());
        collection.deleteOne(doc);

        String temp = this.gson.toJson(ingredient);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Ingredient.COLLECTION_NAME, d);

        return ingredient;
    }

    public Ingredient deleteIngredient(Ingredient ingredient) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoConnection.deleteByLocalID(Ingredient.COLLECTION_NAME, ingredient.getId());
        return ingredient;
    }

    public int getNextId() {
        ArrayList<Ingredient> ingredients = IngredientController.getAllIngredients();
        int lastId = ingredients.size() > 0 ? ingredients.stream().max(Comparator.comparing(Ingredient::getId)).get().getId() : 0;
        return lastId + 1;
    }
}

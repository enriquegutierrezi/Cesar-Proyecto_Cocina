package com.easycook.app.controllers;

import java.util.ArrayList;
import java.util.Comparator;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Ingredient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

public class IngredientController {
    private Gson gson;

    public IngredientController() {
        this.gson = new GsonBuilder().create();
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
        Ingredient ingredient = this.gson.fromJson(document.toJson(), Ingredient.class);

        return ingredient;
    }

    public Ingredient findByLocalID(int id) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByLocalID(Ingredient.COLLECTION_NAME, id);
        Ingredient ingredient = this.gson.fromJson(document.toJson(), Ingredient.class);

        return ingredient;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
		MongoCollection<Document> documents = MongoConnection.findCollection(Ingredient.COLLECTION_NAME);
		try (MongoCursor<Document> cursor = documents.find().iterator()) {
			while (cursor.hasNext()) {
				ingredients.add(this.gson.fromJson(cursor.next().toJson(), Ingredient.class));
			}
		}

        Boolean existIngredient = ingredients.stream().filter(c -> c.getName().equals(ingredient.getName())).findAny().isPresent();

        if (existIngredient) {
            System.out.println("Ingrediente ya existe");
            return null;
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
        MongoConnection mongoConnection = new MongoConnection();
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		MongoCollection<Document> documents = MongoConnection.findCollection(Ingredient.COLLECTION_NAME);
		try (MongoCursor<Document> cursor = documents.find().iterator()) {
			while (cursor.hasNext()) {
				ingredients.add(this.gson.fromJson(cursor.next().toJson(), Ingredient.class));
			}
		}

        int lastId = ingredients.size() > 0 ? ingredients.stream().max(Comparator.comparing(Ingredient::getId)).get().getId() : 0;
		return lastId + 1;
    }
}

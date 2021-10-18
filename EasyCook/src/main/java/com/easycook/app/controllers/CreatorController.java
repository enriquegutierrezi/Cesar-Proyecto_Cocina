package com.easycook.app.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Creator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

public class CreatorController {
    private Gson gson;

    public CreatorController() {
        this.gson = new GsonBuilder().create();
    }

    public ArrayList<Creator> findAllCreators() {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Creator> creators = new ArrayList<Creator>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Creator.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                creators.add(this.gson.fromJson(cursor.next().toJson(), Creator.class));
            }
        }
        return creators;
    }

    public Creator findByName(String name) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByName(Creator.COLLECTION_NAME, name);
        Creator creator = this.gson.fromJson(document.toJson(), Creator.class);

        return creator;
    }

    public Creator createCreator(Creator creator) {
        MongoConnection mongoConnection = new MongoConnection();

        ArrayList<Creator> creators = new ArrayList<Creator>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Creator.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                creators.add(this.gson.fromJson(cursor.next().toJson(), Creator.class));
            }
        }

        Boolean existCreator = creators.stream().filter(c -> c.getName().equals(creator.getName())).findAny().isPresent();

        if (existCreator) {
            System.out.println("Creador ya existe");
            return null;
        }
        String temp = this.gson.toJson(creator);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Creator.COLLECTION_NAME, d);

        return creator;
    }

    public Creator updateCreator(Creator creator) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoCollection<Document> collection = MongoConnection.findCollection(Creator.COLLECTION_NAME);

        BasicDBObject doc = new BasicDBObject();
        doc.put("creator", creator.getId());
        collection.deleteOne(doc);

        String temp = this.gson.toJson(creator);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Creator.COLLECTION_NAME, d);

        return creator;
    }

    public Creator deleteCreator(Creator creator) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoConnection.deleteByID(Creator.COLLECTION_NAME, creator.get_id().toString());
        return creator;
    }

    public int getNextId() {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Creator> creators = new ArrayList<Creator>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Creator.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                creators.add(this.gson.fromJson(cursor.next().toJson(), Creator.class));
            }
        }

        int lastId = creators.size() > 0 ? creators.stream().max(Comparator.comparing(Creator::getId)).get().getId() : 0;
        return lastId + 1;
    }
}

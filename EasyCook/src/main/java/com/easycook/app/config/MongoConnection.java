package com.easycook.app.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static MongoClient mongoClient;
    private static final String DB = "EasyCook";

    public MongoConnection() {
        ConnectionString uri = new ConnectionString(
                String.format("mongodb+srv://EasyCook1:cesar2558@cluster0.xrctp.mongodb.net/%s?retryWrites=true&w=majority", DB));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(uri)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    public static MongoCollection<Document> findCollection(String collectionName) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        return mongoBD.getCollection(collectionName);
    }

    public static void insertObject(String collectionName, Document nDoc) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        MongoCollection<Document> colection = mongoBD.getCollection(collectionName);

        colection.insertOne(nDoc);
    }

    public static void updateObject(String nameCollection, String _id, Document nDoc) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        MongoCollection<Document> collection = mongoBD.getCollection(nameCollection);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(_id));

        collection.replaceOne(query, nDoc);
    }

    public static Document searchByID(String collectionName, String _id) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        MongoCollection<Document> collection = mongoBD.getCollection(collectionName);

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(_id));

        Document doc = collection.find(query).first();
        return doc;
    }

    public static Document searchByName(String collectionName, String name) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        MongoCollection<Document> collection = mongoBD.getCollection(collectionName);

        BasicDBObject query = new BasicDBObject();
        query.put("name", name);

        Document doc = collection.find(query).first();
        return doc;
    }

    public static Document searchByLocalID(String collectionName, int id) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        MongoCollection<Document> collection = mongoBD.getCollection(collectionName);

        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        Document doc = collection.find(query).first();
        return doc;
    }

    public static void deleteByID(String collectionName, String _id) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        MongoCollection<Document> collection = mongoBD.getCollection(collectionName);

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(_id));

        collection.deleteOne(query);
    }

    public static void deleteByLocalID(String collectionName, int id) {
        MongoDatabase mongoBD = mongoClient.getDatabase(DB);
        MongoCollection<Document> collection = mongoBD.getCollection(collectionName);

        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        collection.deleteOne(query);
    }

    public static void closeMongoDB() {
        mongoClient.close();
    }
}

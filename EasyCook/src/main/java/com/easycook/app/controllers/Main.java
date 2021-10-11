package com.easycook.app.controllers;

import com.easycook.app.config.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoCollection<Document> documents = MongoConnection.findCollection("DATES");
        MongoCursor<Document> cursor = documents.find().iterator();
        while (cursor.hasNext()) {
            System.out.println("collection is " +cursor.next() );
        }
    }
}

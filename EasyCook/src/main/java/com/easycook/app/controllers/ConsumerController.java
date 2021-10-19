package com.easycook.app.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Consumer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

public class ConsumerController {

    private Gson gson;

    public ConsumerController() {
        this.gson = new GsonBuilder().create();
    }

    public static ArrayList<Consumer> getAllConsumers() {
        Gson localGson = new GsonBuilder().create();
        MongoConnection mongoConnection = new MongoConnection();
		ArrayList<Consumer> consumers = new ArrayList<Consumer>();
		MongoCollection<Document> documents = MongoConnection.findCollection(Consumer.COLLECTION_NAME);
		try (MongoCursor<Document> cursor = documents.find().iterator()) {
			while (cursor.hasNext()) {
				consumers.add(localGson.fromJson(cursor.next().toJson(), Consumer.class));
			}
		}
		return consumers;
    }

    public ArrayList<Consumer> findAllConsumers() {
        MongoConnection mongoConnection = new MongoConnection();
		ArrayList<Consumer> consumers = new ArrayList<Consumer>();
		MongoCollection<Document> documents = MongoConnection.findCollection(Consumer.COLLECTION_NAME);
		try (MongoCursor<Document> cursor = documents.find().iterator()) {
			while (cursor.hasNext()) {
				consumers.add(this.gson.fromJson(cursor.next().toJson(), Consumer.class));
			}
		}
		return consumers;
    }

    public Consumer findByName(String name) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByName(Consumer.COLLECTION_NAME, name);
        Consumer consumer = this.gson.fromJson(document.toJson(), Consumer.class);

        return consumer;
    }

    public Consumer createConsumer(Consumer consumer) {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
		MongoCollection<Document> documents = MongoConnection.findCollection(Consumer.COLLECTION_NAME);
		try (MongoCursor<Document> cursor = documents.find().iterator()) {
			while (cursor.hasNext()) {
				consumers.add(this.gson.fromJson(cursor.next().toJson(), Consumer.class));
			}
		}

        Boolean existConsumer = consumers.stream().filter(c -> c.getName().equals(consumer.getName())).findAny().isPresent();

        if (existConsumer) {
            System.out.println("Consumidor ya existe");
            return null;
        }

		String temp = this.gson.toJson(consumer);
		Document d = Document.parse(temp);
		MongoConnection.insertObject(Consumer.COLLECTION_NAME, d);

		return consumer;
    }

    public Consumer updateConsumer(Consumer consumer) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoCollection<Document> collection = MongoConnection.findCollection(Consumer.COLLECTION_NAME);

		BasicDBObject doc = new BasicDBObject();
		doc.put("consumer", consumer.getId());
		collection.deleteOne(doc);

		String temp = this.gson.toJson(consumer);
		Document d = Document.parse(temp);
		MongoConnection.insertObject(Consumer.COLLECTION_NAME, d);

		return consumer;
    }

    public Consumer deleteConsumer(Consumer consumer) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoConnection.deleteByID(Consumer.COLLECTION_NAME, consumer.get_id().toString());
        return consumer;
    }

    public int getNextId() {
        MongoConnection mongoConnection = new MongoConnection();
		ArrayList<Consumer> consumers = new ArrayList<Consumer>();
		MongoCollection<Document> documents = MongoConnection.findCollection(Consumer.COLLECTION_NAME);
		try (MongoCursor<Document> cursor = documents.find().iterator()) {
			while (cursor.hasNext()) {
				consumers.add(this.gson.fromJson(cursor.next().toJson(), Consumer.class));
			}
		}

        int lastId = consumers.size() > 0 ? consumers.stream().max(Comparator.comparing(Consumer::getId)).get().getId() : 0;
		return lastId + 1;
    }

    public Optional<Consumer> getConsumerByPassword(String nickname, String password) {
        ArrayList<Consumer> consumers = ConsumerController.getAllConsumers();
        return consumers
            .stream()
            .filter(consumer -> (consumer.getName().equals(nickname) && consumer.getPassword().equals(password)))
            .findFirst();
    }
}

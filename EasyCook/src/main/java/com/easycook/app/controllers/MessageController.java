package com.easycook.app.controllers;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Consumer;
import com.easycook.app.entities.Creator;
import com.easycook.app.entities.Message;
import com.easycook.app.exceptions.NotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MessageController {
    private final Gson gson;

    public MessageController() {
        this.gson = new GsonBuilder().create();
    }

    public static ArrayList<Message> getAllMessages() {
        Gson localGson = new GsonBuilder().create();
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Message> messages = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Message.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                messages.add(localGson.fromJson(cursor.next().toJson(), Message.class));
            }
        }
        return messages;
    }

    public ArrayList<Message> findAllMessages() {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Message> messages = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Message.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                messages.add(this.gson.fromJson(cursor.next().toJson(), Message.class));
            }
        }
        return messages;
    }

    public Message findByName(String name) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByName(Message.COLLECTION_NAME, name);

        return this.gson.fromJson(document.toJson(), Message.class);
    }

    public Message findByLocalID(int id) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByLocalID(Message.COLLECTION_NAME, id);

        return this.gson.fromJson(document.toJson(), Message.class);
    }

    public List<Message> getReceivedMessagesByUsername(String username) {
        ArrayList<Message> messages = MessageController.getAllMessages();
        return messages.stream().filter(message -> message.getTo().equals(username)).collect(Collectors.toList());
    }

    public List<Message> getSentMessagesByUsername(String username) {
        ArrayList<Message> messages = MessageController.getAllMessages();
        return messages.stream().filter(message -> message.getFrom().equals(username)).collect(Collectors.toList());
    }

    public Message createMessage(Message message) throws NotFoundException {
        ConsumerController consumerController = new ConsumerController();
        CreatorController creatorController = new CreatorController();

        ArrayList<Consumer> consumers = consumerController.findAllConsumers();
        ArrayList<Creator> creators = creatorController.findAllCreators();

        boolean existUserTo =
                consumers.stream().anyMatch(consumer -> consumer.getName().equals(message.getTo())) ||
                        creators.stream().anyMatch(creator -> creator.getName().equals(message.getTo()));

        if (!existUserTo) {
            System.out.println("Destinatary user don't exist");
            throw new NotFoundException(String.format("Destinatary user (%s) don't exist", message.getTo()));
        }

        String temp = this.gson.toJson(message);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Message.COLLECTION_NAME, d);

        return message;
    }

    public Message updateMessage(Message message) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoCollection<Document> collection = MongoConnection.findCollection(Message.COLLECTION_NAME);

        BasicDBObject doc = new BasicDBObject();
        doc.put("Message", message.getId());
        collection.deleteOne(doc);

        String temp = this.gson.toJson(message);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Message.COLLECTION_NAME, d);

        return message;
    }

    public Message deleteMessage(Message message) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoConnection.deleteByLocalID(Message.COLLECTION_NAME, message.getId());
        return message;
    }

    public int getNextId() {
        ArrayList<Message> messages = MessageController.getAllMessages();
        int lastId = messages.size() > 0 ? messages.stream().max(Comparator.comparing(Message::getId)).get().getId() : 0;
        return lastId + 1;
    }
}

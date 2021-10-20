package com.easycook.app.controllers;

import com.easycook.app.config.MongoConnection;
import com.easycook.app.entities.Blog;
import com.easycook.app.exceptions.AlreadyExistException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;

public class BlogController {
    private final Gson gson;

    public BlogController() {
        this.gson = new GsonBuilder().create();
    }

    public static ArrayList<Blog> getAllBlogs() {
        Gson localGson = new GsonBuilder().create();
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Blog> blogs = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Blog.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                blogs.add(localGson.fromJson(cursor.next().toJson(), Blog.class));
            }
        }
        return blogs;
    }

    public ArrayList<Blog> findAllBlogs() {
        MongoConnection mongoConnection = new MongoConnection();
        ArrayList<Blog> blogs = new ArrayList<>();
        MongoCollection<Document> documents = MongoConnection.findCollection(Blog.COLLECTION_NAME);
        try (MongoCursor<Document> cursor = documents.find().iterator()) {
            while (cursor.hasNext()) {
                blogs.add(this.gson.fromJson(cursor.next().toJson(), Blog.class));
            }
        }
        return blogs;
    }

    public Blog findByName(String name) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByName(Blog.COLLECTION_NAME, name);

        return this.gson.fromJson(document.toJson(), Blog.class);
    }

    public Blog findByLocalID(int id) {
        MongoConnection mongoConnection = new MongoConnection();
        Document document = MongoConnection.searchByLocalID(Blog.COLLECTION_NAME, id);

        return this.gson.fromJson(document.toJson(), Blog.class);
    }

    public Blog createBlog(Blog blog) throws AlreadyExistException {
        ArrayList<Blog> blogs = BlogController.getAllBlogs();

        boolean existBlog = blogs.stream().anyMatch(c -> c.getTitle().toLowerCase().equals(blog.getTitle().toLowerCase()));

        if (existBlog) {
            throw new AlreadyExistException(String.format("Blog %s already exist", blog.getTitle()));
        }

        String temp = this.gson.toJson(blog);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Blog.COLLECTION_NAME, d);

        return blog;
    }

    public Blog updateBlog(Blog blog) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoCollection<Document> collection = MongoConnection.findCollection(Blog.COLLECTION_NAME);

        BasicDBObject doc = new BasicDBObject();
        doc.put("Blog", blog.getId());
        collection.deleteOne(doc);

        String temp = this.gson.toJson(blog);
        Document d = Document.parse(temp);
        MongoConnection.insertObject(Blog.COLLECTION_NAME, d);

        return blog;
    }

    public Blog deleteBlog(Blog blog) {
        MongoConnection mongoConnection = new MongoConnection();
        MongoConnection.deleteByLocalID(Blog.COLLECTION_NAME, blog.getId());
        return blog;
    }

    public int getNextId() {
        ArrayList<Blog> blogs = BlogController.getAllBlogs();
        int lastId = blogs.size() > 0 ? blogs.stream().max(Comparator.comparing(Blog::getId)).get().getId() : 0;
        return lastId + 1;
    }
}

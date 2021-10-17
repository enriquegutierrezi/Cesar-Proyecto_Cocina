package com.easycook.app.entities;

import java.util.ArrayList;

import org.bson.types.ObjectId;

public class Creator extends Guest {
    public static String COLLECTION_NAME = "creators";

    private ObjectId _id;
    private String email;
    private String password;
    private ArrayList<Blog> blogs;
    private ArrayList<Recipe> recipes;
    
    public Creator(int id, String name, ArrayList<Blog> blogs, ArrayList<Recipe> recipes) {
        super(id,name);
        this.blogs = blogs;
        this.recipes = recipes;
    }

    public Creator(int id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }

    public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }
    public void setBlogs(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

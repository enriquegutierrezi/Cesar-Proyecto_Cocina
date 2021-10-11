package com.easycook.app.entities;

import java.util.ArrayList;

public class Creator extends Guest {
    private ArrayList<Blog> blogs;
    private ArrayList<Recipe> recipes;

    
    public Creator(int id, String name, ArrayList<Blog> blogs, ArrayList<Recipe> recipes) {
        super(id,name);
        this.blogs = blogs;
        this.recipes = recipes;
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
    
}

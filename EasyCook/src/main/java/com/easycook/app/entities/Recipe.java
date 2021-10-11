package com.easycook.app.entities;

import java.util.ArrayList;

public class Recipe {
    private String id;
    private String name;
    private ArrayList<Ingredient> ingredients;
    private int minPeople;
    private int maxPeople;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public int getMinPeople() {
        return minPeople;
    }
    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }
    public int getMaxPeople() {
        return maxPeople;
    }
    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    
}

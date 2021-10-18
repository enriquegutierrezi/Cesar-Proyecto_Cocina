package com.easycook.app.entities;

import java.util.ArrayList;

public class Recipe {
    public static final String COLLECTION_NAME = "recipe";

    private int id;
    private String name;
    private int cookingTime;
    private ArrayList<Ingredient> ingredients;
    private int amountPeople;
    private String urlImage;

    public Recipe(int id, String name, int cookingTime, int amountPeople,ArrayList<Ingredient> ingredients,String url) {
        this.id = id;
        this.name = name;
        this.cookingTime = cookingTime;
        this.amountPeople = amountPeople;
        this.ingredients = ingredients;
        this.urlImage = url;
    }
    

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }


    public String getUrlImage() {
        return urlImage;
    }


    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public int getCookingTime() {
        return cookingTime;
    }
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
    public int getAmountPeople() {
        return amountPeople;
    }
    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
}

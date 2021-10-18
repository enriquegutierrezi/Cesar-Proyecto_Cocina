package com.easycook.app.entities;

public class Ingredient {
    public static final String COLLECTION_NAME = "ingredients";

    private int id;
    private String name;
    private char state;
    //private int amount;

    

    public Ingredient(int id, String name, char state/*int amount*/) {
        this.id = id;
        this.name = name;
        this.state = state;
      //  this.amount = amount;
    
    }
    
    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

/*    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    */

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
    
    
    
    
}



package com.easycook.app.controllers;

import java.util.ArrayList;

import com.easycook.app.entities.Ingredient;
import com.easycook.app.entities.Message;
import com.easycook.app.entities.Recipe;
import com.easycook.app.exceptions.NotFoundException;

public class Main {
    public static void main(String[] args) throws NotFoundException {

        /*IngredientController ingredientController = new IngredientController();
        RecipeController recipeController = new RecipeController();
        
        Ingredient ingredient1 = new Ingredient(ingredientController.getNextId(), "Leche",'L');
        ingredientController.createIngredient(ingredient1);
        Ingredient ingredient2 = new Ingredient(ingredientController.getNextId(), "Arroz",'S');
        ingredientController.createIngredient(ingredient2);
        Ingredient ingredient3 = new Ingredient(ingredientController.getNextId(), "Canela",'S');
        ingredientController.createIngredient(ingredient3);

        Ingredient ingredient4 = new Ingredient(ingredientController.getNextId(), "Chocolate",'S');
        ingredientController.createIngredient(ingredient4);

        Ingredient ingredient5 = new Ingredient(ingredientController.getNextId(), "Huevo",'S');
        ingredientController.createIngredient(ingredient5);
        Ingredient ingredient6 = new Ingredient(ingredientController.getNextId(), "Tomate",'S');
        ingredientController.createIngredient(ingredient6);
        Ingredient ingredient7 = new Ingredient(ingredientController.getNextId(), "Sal",'S');
        ingredientController.createIngredient(ingredient7);
        Ingredient ingredient8 = new Ingredient(ingredientController.getNextId(), "Cebolla",'S');
        ingredientController.createIngredient(ingredient8);

        ArrayList<Ingredient> ingredientsList1 = new ArrayList<>();
        ArrayList<Ingredient> ingredientsList2 = new ArrayList<>();
        ArrayList<Ingredient> ingredientsList3 = new ArrayList<>();

        ingredientsList1.add(ingredient1);
        ingredientsList1.add(ingredient2);
        ingredientsList1.add(ingredient3);

        ingredientsList2.add(ingredient2);
        ingredientsList2.add(ingredient4);

        ingredientsList3.add(ingredient5);
        ingredientsList3.add(ingredient6);
        ingredientsList3.add(ingredient7);
        ingredientsList3.add(ingredient8);


        Recipe recipe1 = new Recipe(recipeController.getNextId(), "Arroz con leche", 120, 7,ingredientsList1,"https://www.cocinavital.mx/wp-content/uploads/2019/04/como-hacer-arroz-con-leche.jpg");
        recipeController.createRecipe(recipe1);

        Recipe recipe2 = new Recipe(recipeController.getNextId(), "Chocolate en leche", 30, 7,ingredientsList2,"data:image/jpeg;base64,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");
        recipeController.createRecipe(recipe2);

        Recipe recipe3 = new Recipe(recipeController.getNextId(), "Huevos Pericos", 20, 3,ingredientsList3,"https://cdn.colombia.com/gastronomia/2017/07/10/huevos-pericos-colombianos-3033.jpg");
        recipeController.createRecipe(recipe3);
        */

        RecipeController recipeController = new RecipeController();
        recipeController.findByAmount(8).forEach(System.out::println);

        MessageController messageController = new MessageController();
        Message message = new Message(messageController.getNextId(), "From", "To", "Content", false);
        messageController.createMessage(message);
    }
}

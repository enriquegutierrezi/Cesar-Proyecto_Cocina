package com.easycook.app.presentation;


import java.io.IOException;
import java.util.ArrayList;

import com.easycook.app.controllers.IngredientController;
import com.easycook.app.controllers.RecipeController;
import com.easycook.app.entities.Ingredient;
import com.easycook.app.entities.Recipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateRecipeFormController {

    @FXML
    private Button CreateRecipeButton;

    @FXML
    private TextField txtAmount1;

    @FXML
    private TextField txtAmount2;

    @FXML
    private TextField txtAmount3;

    @FXML
    private TextField txtAmountPeople;

    @FXML
    private TextField txtCookingTime;

    @FXML
    private TextField txtIngredient1;

    @FXML
    private TextField txtIngredient2;

    @FXML
    private TextField txtIngredient3;

    @FXML
    private TextField txtNameRecipe;

    @FXML
    private TextField txtState1;

    @FXML
    private TextField txtState2;

    @FXML
    private TextField txtState3;

    @FXML
    private TextField txtUrl;
    @FXML
    private Button ReturnButton;

    @FXML
    void CreateRecipe(ActionEvent event) {
        IngredientController ingredientController = new IngredientController();
        RecipeController recipeController = new RecipeController();

        Ingredient ingredient1 = new Ingredient(txtIngredient1.getText(),txtState1.getText().charAt(0));
        ingredientController.createIngredient(ingredient1);
        Ingredient ingredient2 = new Ingredient(txtIngredient2.getText(),txtState2.getText().charAt(0));
        ingredientController.createIngredient(ingredient2);
        Ingredient ingredient3 = new Ingredient(txtIngredient3.getText(),txtState3.getText().charAt(0));
        ingredientController.createIngredient(ingredient3);

        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        

        ingredientsList.add(ingredient1);
        ingredientsList.add(ingredient2);
        ingredientsList.add(ingredient3);

        Recipe recipe = new Recipe(recipeController.getNextId(), txtNameRecipe.getText(), Integer.parseInt(txtCookingTime.getText()), Integer.parseInt(txtAmountPeople.getText()),ingredientsList,txtUrl.getText());
        recipeController.createRecipe(recipe);

        
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("CreationsMenu");
    }

}

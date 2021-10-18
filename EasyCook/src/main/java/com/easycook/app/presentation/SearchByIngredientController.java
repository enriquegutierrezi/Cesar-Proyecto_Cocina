package com.easycook.app.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.easycook.app.controllers.RecipeController;
import com.easycook.app.entities.Recipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SearchByIngredientController {

    @FXML
    private TextField txtIngredient;
    @FXML
    private ImageView ImageRecipe1;

    @FXML
    private ImageView ImageRecipe2;

    @FXML
    private ImageView ImageRecipe3;

    @FXML
    private Button nextButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button SearchButton;

    
    
    

    @FXML
    void SearchIngredient(ActionEvent event) throws IOException {
        RecipeController recipeController = new RecipeController();
        List<Recipe> recipes = recipeController.findByIngredient(txtIngredient.getText());
        

        Image recipe1 = new Image(recipes.get(0).getUrlImage());
    
        ImageRecipe1.setImage(recipe1);
        
        /*
        
        ArrayList<Recipe> searchResult = recipeController.findByIngredient(txtIngredient.getText());
        int amount = (int)Math.ceil(searchResult.size()/3);
        Image recipe1 = new Image(searchResult.get(0).getUrlImage());
        Image recipe2 = new Image(searchResult.get(1).getUrlImage());
        Image recipe3 = new Image(searchResult.get(2).getUrlImage());
        ImageRecipe1.setImage(recipe1);
        ImageRecipe2.setImage(recipe2);
        ImageRecipe3.setImage(recipe3);
        */
    }
    @FXML
    void NextPage(ActionEvent event) {

    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("searchMenu");
    }

}

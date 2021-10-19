package com.easycook.app.presentation;

 
    
 import java.io.IOException;
import java.util.List;

import com.easycook.app.controllers.RecipeController;
import com.easycook.app.entities.Recipe;

import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.scene.control.Button;
 import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 
 public class SearchByCookingTimeController  {
 
     @FXML
     private ImageView ImageRecipe1;
 
     @FXML
     private ImageView ImageRecipe2;
 
     @FXML
     private ImageView ImageRecipe3;
 
     @FXML
     private Button SearchButton;
 
     @FXML
     private Button nextButton;
 
     @FXML
     private Button returnButton;
 
     @FXML
     private TextField txtTime;
 
     @FXML
     void NextPage(ActionEvent event) {
 
     }
 
     @FXML
     void Return(ActionEvent event) throws IOException {
        App.setRoot("searchMenu");
     }
 
     @FXML
     void SearchRecipe(ActionEvent event) {
        RecipeController recipeController = new RecipeController();
        List<Recipe> recipes = recipeController.findByCookingTime(Integer.parseInt(txtTime.getText()));

        Image recipe1 = new Image(recipes.get(0).getUrlImage());
    
        ImageRecipe1.setImage(recipe1);
     }
 
 }
 


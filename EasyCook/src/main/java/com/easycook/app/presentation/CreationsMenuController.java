 package com.easycook.app.presentation;

 import java.io.IOException;

import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.scene.control.Button;
 
 public class  CreationsMenuController   {
 
     @FXML
     private Button CreateBlogButton;
 
     @FXML
     private Button CreateRecipeButton;
 
     @FXML
     private Button LogOutButton;
 
     @FXML
     void GoToCreateBlog(ActionEvent event) throws IOException {
        App.setRoot("CreateBlogForm");
     }
 
     @FXML
     void GoToCreateRecipe(ActionEvent event) throws IOException {
        App.setRoot("CreateRecipeForm");
     }
 
     @FXML
     void LogOut(ActionEvent event) {
 
     }
 
 }
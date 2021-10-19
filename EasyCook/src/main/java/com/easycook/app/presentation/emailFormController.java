package com.easycook.app.presentation;

  
    
 import java.io.IOException;

import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.scene.control.Button;
 import javafx.scene.control.TextArea;
 import javafx.scene.control.TextField;
 
 public class emailFormController {
 
     @FXML
     private Button SendButton;
 
     @FXML
     private Button returnButton;
 
     @FXML
     private TextArea txtMessage;
 
     @FXML
     private TextField txtTo;
 
     @FXML
     void Return(ActionEvent event) throws IOException {
        App.setRoot("InteractionCreatorMenu");
     }
 
     @FXML
     void SendMessage(ActionEvent event) {
        
     }
 
 }

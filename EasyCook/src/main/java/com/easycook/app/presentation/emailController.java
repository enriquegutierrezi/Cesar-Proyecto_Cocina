package com.easycook.app.presentation;

 

 import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class emailController  {

    @FXML
    private Button lookButton;

    @FXML
    private TextFlow messagge1;

    @FXML
    private TextFlow messagge2;

    @FXML
    private TextFlow messagge3;

    @FXML
    private TextFlow messagge4;

    @FXML
    private Button returnButton;

    @FXML
    void LookMessages(ActionEvent event) throws IOException {
    
        
        Text m1 = new Text ("Bienvenido al juego");
        messagge1.getChildren().addAll(m1);
        messagge1.setStyle("-fx-background-color:blue");
        
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("InteractionCreatorMenu");
    }

}
    


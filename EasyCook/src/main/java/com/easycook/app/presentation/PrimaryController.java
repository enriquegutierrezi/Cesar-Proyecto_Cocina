package com.easycook.app.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView; 
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class PrimaryController implements Initializable {


    @FXML
    private Button GuestModeButton;

    @FXML
    private ImageView fondo;

    @FXML
    private Button Login;

    @FXML
    private Button registrobutton;

    @FXML
    private Hyperlink PasswordRecoverButton;

    

    @FXML
    void GoToGuestMode(ActionEvent event) {

    }

    @FXML
    void GoToLoginForm(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    @FXML
    void GoToRegisterForm(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    void GoToRecoverPassword(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image myImage = new Image (getClass().getResourceAsStream("fondo.jpeg"));
        fondo.setImage(myImage);
        
    }

}
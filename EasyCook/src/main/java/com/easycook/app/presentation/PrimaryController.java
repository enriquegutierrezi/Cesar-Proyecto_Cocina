package com.easycook.app.presentation;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

// App.setRoot("secondary");

public class PrimaryController {


    @FXML
    private Button GuestModeButton;

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
        
    }

    @FXML
    void GoToRegisterForm(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    void GoToRecoverPassword(ActionEvent event) {

    }
}
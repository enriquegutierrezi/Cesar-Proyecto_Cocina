package com.easycook.app.presentation;



import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Button returnButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void goToApp(ActionEvent event) throws IOException {
        App.setRoot("searchMenu");
    }

    @FXML
    void returnScene(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
}


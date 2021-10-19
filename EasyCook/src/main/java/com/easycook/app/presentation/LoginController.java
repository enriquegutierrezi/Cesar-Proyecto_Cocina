package com.easycook.app.presentation;



import java.io.IOException;
import java.util.Optional;

import com.easycook.app.controllers.ConsumerController;
import com.easycook.app.controllers.CreatorController;
import com.easycook.app.entities.Consumer;
import com.easycook.app.entities.Creator;

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
        CreatorController creatorController = new CreatorController();
        ConsumerController consumerController = new ConsumerController();

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Optional<Creator> creatorOptional = creatorController.getCreatorByPassword(username, password);
        if (creatorOptional.isPresent())
            App.setRoot("Creationsmenu");

        Optional<Consumer> consumerOptional = consumerController.getConsumerByPassword(username, password);
        if (consumerOptional.isPresent())
            App.setRoot("searchMenu");
    }

    @FXML
    void returnScene(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
}


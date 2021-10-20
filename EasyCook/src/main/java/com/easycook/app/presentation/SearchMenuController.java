package com.easycook.app.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SearchMenuController {

    @FXML
    private Button IngredientSearchButton;

    @FXML
    private Button nimeButton;

    @FXML
    private Button peopleFilterButton;

    @FXML
    private Button timeButton;

    private String username;
    @FXML
    private Button interactionButton;

    @FXML
    private Button returnButton;

    @FXML
    void GoToInteraction(ActionEvent event) throws IOException {
        App.setRoot("InteractionCreatorMenu");
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    

    @FXML
    void SearchByCookingTime(ActionEvent event) throws IOException {
        App.setRoot("searchByCookingTime");
    }

    @FXML
    void searchByAmountPeople(ActionEvent event) throws IOException {
        App.setRoot("searchByAmountPeople");
    }

    @FXML
    void searchByIngredient(ActionEvent event) throws IOException {
        App.setRoot("searchByIngredient");
    }

    @FXML
    void searchByName(ActionEvent event) throws IOException {
        App.setRoot("searchByRecipeName");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


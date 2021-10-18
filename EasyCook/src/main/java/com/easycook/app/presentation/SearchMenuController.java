package com.easycook.app.presentation;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SearchMenuController {

    @FXML
    private Button IngredientSearchButton;

    @FXML
    private Button nimeButton;

    @FXML
    private Button peopleFilterButton;

    @FXML
    private Button timeButton;

    @FXML
    void SearchByCookingTime(ActionEvent event) {

    }

    @FXML
    void searchByAmountPeople(ActionEvent event) {

    }

    @FXML
    void searchByIngredient(ActionEvent event) throws IOException {
        App.setRoot("searchByIngredient");
    }

    @FXML
    void searchByName(ActionEvent event) {

    }

}


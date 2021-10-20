package com.easycook.app.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InteractionCreatorMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            interactionCreatorMenuController controller = loader.getController();
            controller.setUsername(username);
            controller.setIsConsumer(true);
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            LoginController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    

    @FXML
    void SearchByCookingTime(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchByCookingTime.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            SearchByCookingTimeController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    @FXML
    void searchByAmountPeople(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchByAmountPeople.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            SearchByAmountPeopleController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    @FXML
    void searchByIngredient(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchByIngredient.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            SearchByIngredientController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    @FXML
    void searchByName(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchByRecipeName.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            SearchByRecipeNameController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


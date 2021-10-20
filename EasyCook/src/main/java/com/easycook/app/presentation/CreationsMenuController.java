package com.easycook.app.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreationsMenuController {

    @FXML
    private Button CreateBlogButton;

    @FXML
    private Button CreateRecipeButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private Button interactionButton;

    private String username;

    @FXML
    void GoToCreateBlog(ActionEvent event) throws IOException {
        App.setRoot("CreateBlogForm");
    }

    @FXML
    void GoToCreateRecipe(ActionEvent event) throws IOException {
        App.setRoot("CreateRecipeForm");
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
      App.setRoot("Login");
    }

    @FXML
    void goToInteraction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InteractionCreatorMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            interactionCreatorMenuController controller = loader.getController();
            controller.setUsername(username);
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
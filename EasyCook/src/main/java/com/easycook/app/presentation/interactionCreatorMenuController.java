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

public class interactionCreatorMenuController {

    @FXML
    private Button ReturnButton;

    @FXML
    private Button emailButton;

    @FXML
    private Button sendMailButton;

    private String username;

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("CreationsMenu");
    }

    @FXML
    void goToEmail(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("email.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            emailController controller = loader.getController();
            controller.setUsername(username);
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    @FXML
    void goToSendMail(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("emailForm.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            emailFormController controller = loader.getController();
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
    


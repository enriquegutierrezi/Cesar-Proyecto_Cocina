package com.easycook.app.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
    void GoToRegisterForm(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            SecondaryController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
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
package com.easycook.app.presentation;

import com.easycook.app.controllers.ConsumerController;
import com.easycook.app.controllers.CreatorController;
import com.easycook.app.entities.Consumer;
import com.easycook.app.entities.Creator;
import com.easycook.app.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button returnButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private ImageView fondo;

    @FXML
    void goToApp(ActionEvent event) throws Exception {
        CreatorController creatorController = new CreatorController();
        ConsumerController consumerController = new ConsumerController();

        String username = txtUsername.getText();
        String password = Utils.encrypt(txtPassword.getText());

        Optional<Creator> creatorOptional = creatorController.getCreatorByPassword(username, password);
        if (creatorOptional.isPresent()) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreationsMenu.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                CreationsMenuController controller = loader.getController();
                controller.setUsername(creatorOptional.get().getName());
                loader.setController(controller);
                stage.show();
            } catch (IOException e) {
                System.err.printf("Error: %s%n", e.getMessage());
            }
        }

        Optional<Consumer> consumerOptional = consumerController.getConsumerByPassword(username, password);
        if (consumerOptional.isPresent()) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("searchMenu.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                SearchMenuController controller = loader.getController();
                controller.setUsername(consumerOptional.get().getName());
                loader.setController(controller);
                stage.show();
            } catch (IOException e) {
                System.err.printf("Error: %s%n", e.getMessage());
            }
        }

        if (consumerOptional.isEmpty() && creatorOptional.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Usuario o contraseña erroneos");
            alert.show();
        }
    }

    @FXML
    void returnScene(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            PrimaryController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image myImage = new Image(getClass().getResourceAsStream("fondo.jpeg"));
        fondo.setImage(myImage);

    }
}

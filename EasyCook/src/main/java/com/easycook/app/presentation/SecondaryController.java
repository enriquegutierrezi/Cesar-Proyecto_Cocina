package com.easycook.app.presentation;

import com.easycook.app.controllers.ConsumerController;
import com.easycook.app.controllers.CreatorController;
import com.easycook.app.entities.Consumer;
import com.easycook.app.entities.Creator;
import com.easycook.app.exceptions.AlreadyExistException;
import com.easycook.app.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable{

    @FXML
    private RadioButton ConsumerOption;

    @FXML
    private ImageView fondo;

    @FXML
    private Button CreateButton;

    @FXML
    private RadioButton CreatorOption;

    @FXML
    private Button ReturnButton;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNameUser;

    @FXML
    private TextField txtPassword;

    @FXML
    void CreateAccount(ActionEvent event) throws Exception {
        String username = txtNameUser.getText();
        String email = txtEmail.getText();
        String password = Utils.encrypt(txtPassword.getText());
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {
            if (this.ConsumerOption.isSelected() && (!this.CreatorOption.isSelected())) {
                ConsumerController consumerController = new ConsumerController();
                int nextId = consumerController.getNextId();
                Consumer consumer = new Consumer(nextId, username, email, password);
                consumerController.createConsumer(consumer);

            } else if ((this.CreatorOption.isSelected()) && (!this.ConsumerOption.isSelected())) {
                CreatorController creatorController = new CreatorController();
                int nextId = creatorController.getNextId();
                Creator creator = new Creator(nextId, username, email, password);
                creatorController.createCreator(creator);
            }
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(String.format("El usuario %s ha sido registrado exitosamente", username));
            alert.show();
        } catch (AlreadyExistException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(String.format("El usuario %s ya existe", username));
            alert.show();
        }
    }

    @FXML
    void ReturnToPrimary(ActionEvent event) throws IOException {
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

        // System.out.println(this.txtEmail.getText());

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image myImage = new Image (getClass().getResourceAsStream("fondo.jpeg"));
        fondo.setImage(myImage);
        
    }
}

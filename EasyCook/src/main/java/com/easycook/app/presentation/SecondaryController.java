package com.easycook.app.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.easycook.app.controllers.ConsumerController;
import com.easycook.app.controllers.CreatorController;
import com.easycook.app.entities.Consumer;
import com.easycook.app.entities.Creator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SecondaryController {

    @FXML
    private RadioButton ConsumerOption;

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
    void CreateAccount(ActionEvent event) {

        if (this.ConsumerOption.isSelected() && (!this.CreatorOption.isSelected())) {
            ConsumerController consumerController = new ConsumerController();
            int nextId = consumerController.getNextId();
            Consumer consumer = new Consumer(nextId, this.txtNameUser.getText(), this.txtEmail.getText(),
                    this.txtPassword.getText());

            consumerController.createConsumer(consumer);

        } else if ((this.CreatorOption.isSelected()) && (!this.ConsumerOption.isSelected())) {
            CreatorController creatorController = new CreatorController();
            int nextId = creatorController.getNextId();
            Creator creator = new Creator(nextId, this.txtNameUser.getText(), this.txtEmail.getText(),
                    this.txtPassword.getText());

            creatorController.createCreator(creator);
        }

    }

    @FXML
    void ReturnToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");

        // System.out.println(this.txtEmail.getText());

    }
}

package com.easycook.app.presentation;


import com.easycook.app.controllers.MessageController;
import com.easycook.app.entities.Message;
import com.easycook.app.exceptions.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class emailFormController {

    @FXML
    private Button SendButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextArea txtMessage;

    @FXML
    private TextField txtTo;

    private String username;

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("InteractionCreatorMenu");
    }

    @FXML
    void SendMessage(ActionEvent event) {
        MessageController messageController = new MessageController();
        Message message = new Message(messageController.getNextId(), username, txtTo.getText(), txtMessage.getText(), false);
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {
            messageController.createMessage(message);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(String.format("Mensaje a %s enviado con exito", txtTo.getText()));
            alert.show();
        } catch (NotFoundException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(String.format("El destinatario %s no existe", txtTo.getText()));
            alert.show();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

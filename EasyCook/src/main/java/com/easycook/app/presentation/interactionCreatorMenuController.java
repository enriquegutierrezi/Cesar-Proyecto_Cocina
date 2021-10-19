package com.easycook.app.presentation;

 

 import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class interactionCreatorMenuController  {

    @FXML
    private Button ReturnButton;

    @FXML
    private Button emailButton;

    @FXML
    private Button sendMailButton;

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("CreationsMenu");
    }

    @FXML
    void goToEmail(ActionEvent event) throws IOException {
        App.setRoot("email");
    }

    @FXML
    void goToSendMail(ActionEvent event) throws IOException {
        App.setRoot("emailForm");
    }

}
    


package com.easycook.app.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateBlogFormController {

    @FXML
    private Button PublishButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea txtBlogContent;

    @FXML
    private TextField txtBlogTitle;

    @FXML
    private TextField txtUrlImage;

    @FXML
    void Publish(ActionEvent event) {

    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        App.setRoot("CreationsMenu");
    }

}

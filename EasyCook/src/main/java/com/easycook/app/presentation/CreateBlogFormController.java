package com.easycook.app.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreationsMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            CreationsMenuController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

}

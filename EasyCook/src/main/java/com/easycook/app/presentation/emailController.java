package com.easycook.app.presentation;


import com.easycook.app.controllers.MessageController;
import com.easycook.app.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class emailController {

    @FXML
    private Button lookButton;

    @FXML
    private Label message1;

    @FXML
    private Label message2;

    @FXML
    private Label message3;

    @FXML
    private Button returnButton;

    private String username;

    private Boolean isConsumer;

    @FXML
    void LookMessages(ActionEvent event) throws IOException {
        Text m1 = new Text("Bienvenido al juego");

        MessageController messageController = new MessageController();
        List<Message> messages = messageController.getReceivedMessagesByUsername(username);

        if (messages.size() == 1) {
            StringBuilder message1String = new StringBuilder();
            Message message1Controller = messages.get(0);

            message1String.append(String.format("Remitente: %s\n", message1Controller.getFrom()));
            message1String.append(String.format("Contenido: %s\n", message1Controller.getContent()));

            message1.setText(String.valueOf(message1String));
        } else if (messages.size() == 2) {
            StringBuilder message1String = new StringBuilder();
            StringBuilder message2String = new StringBuilder();

            Message message1Controller = messages.get(0);
            Message message2Controller = messages.get(1);

            message1String.append(String.format("Remitente: %s\n", message1Controller.getFrom()));
            message1String.append(String.format("Contenido: %s\n", message1Controller.getContent()));

            message2String.append(String.format("Remitente: %s\n", message2Controller.getFrom()));
            message2String.append(String.format("Contenido: %s\n", message2Controller.getContent()));

            message1.setText(String.valueOf(message1String));
            message2.setText(String.valueOf(message2String));
        } else if (messages.size() >= 3) {
            StringBuilder message1String = new StringBuilder();
            StringBuilder message2String = new StringBuilder();
            StringBuilder message3String = new StringBuilder();

            Message message1Controller = messages.get(0);
            Message message2Controller = messages.get(1);
            Message message3Controller = messages.get(2);

            message1String.append(String.format("Remitente: %s\n", message1Controller.getFrom()));
            message1String.append(String.format("Contenido: %s\n", message1Controller.getContent()));

            message2String.append(String.format("Remitente: %s\n", message2Controller.getFrom()));
            message2String.append(String.format("Contenido: %s\n", message2Controller.getContent()));

            message3String.append(String.format("Remitente: %s\n", message3Controller.getFrom()));
            message3String.append(String.format("Contenido: %s\n", message3Controller.getContent()));

            message1.setText(String.valueOf(message1String));
            message2.setText(String.valueOf(message2String));
            message3.setText(String.valueOf(message3String));
        }
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InteractionCreatorMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            interactionCreatorMenuController controller = loader.getController();
            controller.setIsConsumer(isConsumer);
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

    public Boolean getIsConsumer() {
        return isConsumer;
    }

    public void setIsConsumer(Boolean isConsumer) {
        this.isConsumer = isConsumer;
    }
}
    


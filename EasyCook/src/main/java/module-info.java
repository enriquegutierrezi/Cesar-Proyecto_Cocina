module com.mongo.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive mongo.java.driver;
    requires transitive com.google.gson;
    requires org.apache.commons.codec;

    exports com.easycook.app.presentation;
    opens com.easycook.app.presentation to javafx.fxml;
    exports com.easycook.app.controllers;
    opens com.easycook.app.controllers to javafx.fxml;
    exports com.easycook.app.entities;
    opens com.easycook.app.entities to javafx.fxml;
    exports com.easycook.app.exceptions;
    opens com.easycook.app.exceptions to javafx.fxml;
}
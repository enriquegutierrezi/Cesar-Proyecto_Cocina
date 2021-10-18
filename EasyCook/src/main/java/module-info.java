module com.mongo.demo {
    requires javafx.controls;
    requires javafx.fxml;
	requires transitive mongo.java.driver;
    requires transitive com.google.gson;

    exports com.easycook.app.presentation;
    opens com.easycook.app.presentation to javafx.fxml;
    exports com.easycook.app.controllers;
    opens com.easycook.app.controllers to javafx.fxml;
    exports com.easycook.app.entities;
    opens com.easycook.app.entities to javafx.fxml;
}
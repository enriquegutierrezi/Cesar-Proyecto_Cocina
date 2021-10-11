module com.mongo.demo {
    requires javafx.controls;
    requires javafx.fxml;
	requires mongo.java.driver;

    exports com.easycook.app.presentation;
    opens com.easycook.app.presentation to javafx.fxml;
    exports com.easycook.app.controllers;
    opens com.easycook.app.controllers to javafx.fxml;
}
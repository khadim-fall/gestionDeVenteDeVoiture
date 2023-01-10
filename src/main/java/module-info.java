module com.example.khadimfall {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.khadimfall to javafx.fxml;
    exports com.example.khadimfall;
    exports com.example.khadimfall.controllers;
    opens com.example.khadimfall.controllers to javafx.fxml;
}
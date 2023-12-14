module application.foodolingo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;


    opens application.foodolingo1 to javafx.fxml;
    exports application.foodolingo1;
}
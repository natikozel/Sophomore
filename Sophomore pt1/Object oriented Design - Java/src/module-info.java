module MyProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    opens application to javafx.fxml;
    exports application;
    exports model;
    opens model to javafx.fxml;
}
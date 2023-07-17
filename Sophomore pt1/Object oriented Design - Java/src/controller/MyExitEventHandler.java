package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import javax.swing.*;

public class MyExitEventHandler implements EventHandler<ActionEvent> {

    private Node button = new Button();

    @Override
    public void handle(ActionEvent ae) {
        Platform.exit();
    }
}
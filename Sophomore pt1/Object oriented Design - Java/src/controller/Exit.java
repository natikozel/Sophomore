package controller;
import javafx.application.Platform;

public class Exit implements Command {

    @Override
    public void execute() {
        Platform.exit();
    }
}
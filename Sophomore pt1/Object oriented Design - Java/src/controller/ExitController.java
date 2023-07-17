package controller;

import javafx.scene.Node;

public class ExitController {

    private Command cmd;

    public void exitAndExecute() {
        cmd = new Exit();
        cmd.execute();
    }
}

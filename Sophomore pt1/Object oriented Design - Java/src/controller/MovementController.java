package controller;

import view.MainView;

public class MovementController {

    private QuestionController controller;
    private MainView newView;
    private Command cmd;

    public MovementController(MainView newView, QuestionController controller) {
        this.controller=controller;
        this.newView = newView;
    }

    public void MoveAndExecute() {
        cmd = new MoveStage(newView, controller);
        cmd.execute();
    }
}

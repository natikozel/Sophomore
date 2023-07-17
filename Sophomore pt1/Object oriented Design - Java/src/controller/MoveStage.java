package controller;
import view.*;

public class MoveStage implements Command {

    private QuestionController controller;
    private MainView newView;

    public MoveStage(MainView newView, QuestionController controller) {
        this.controller = controller;
        this.newView = newView;
    }

    @Override
    public void execute() {
        if (newView instanceof ScreenView that) {
            this.controller.setView(that);
        } else if (newView instanceof CreateExam that) {
            this.controller.setView(that);
        } else if (newView instanceof CreateQuestion that) {
            this.controller.setView(that);
        } else if (newView instanceof MyArrayListView that) {
            this.controller.setView(that);
        } else if (newView instanceof MyArrayListView2 that) {
            this.controller.setView(that);
        } else if (newView instanceof UpdateQuestion that) {
            this.controller.setView(that);
        }
        this.controller.getView().registerListener(controller);
    }

}

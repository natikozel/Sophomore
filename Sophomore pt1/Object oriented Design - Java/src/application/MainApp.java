package application;
import controller.QuestionController;
import javafx.stage.Stage;
import model.Exam;
import view.MainView;
import javafx.application.Application;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    	

    	Exam model = new Exam();
        MainView view = new MainView(primaryStage);
        QuestionController controller = new QuestionController(view, model);
    }       
    
    public static void main(String[] args) {
        launch();
    }
}
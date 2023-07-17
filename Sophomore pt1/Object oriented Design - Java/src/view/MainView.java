package view;

import controller.Exit;
import controller.ExitController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.ActionEventsListener;

import java.util.ArrayList;

public class MainView implements UIView {

    private VBox root;
    private AnchorPane ap;
    private Button startbtn;
    private MenuBar mb;
    private MenuItem quit;
    private Menu menu;
    private Text txt;
    private ExitController exit = new ExitController();
    private ArrayList< ActionEventsListener > allListeners = new ArrayList<>();



    public MainView(Stage stage) throws Exception {
        stage.setTitle("Welcome");
        initValues();
        initActions();

        stage.setScene(new Scene(root, 698, 445));
        stage.show();

    }

    public MainView() {
    }

    @Override
    public void registerListener(ActionEventsListener listener) {
        allListeners.add(listener);
    }

    @Override
    public void removeListener(ActionEventsListener listener) {
        allListeners.remove(listener);
    }

    public void shutStageDown() {
        Stage currentStage = (Stage) mb.getScene().getWindow();
        currentStage.close();
    }

    public void hideCurrentStage() {
        Stage currentStage = (Stage) mb.getScene().getWindow();
        currentStage.hide();
    }

    @Override
    public void initActions() throws Exception {
        quit.setOnAction(e-> {
          exit.exitAndExecute();
        });
        CreateQuestion.returnToMethodsScreen(startbtn, allListeners);
    }

    @Override
    public void initValues() {

        root = new VBox();
        InitiateVBox(root);

        ap = new AnchorPane();
        ap.setMinSize(698, 453);

        mb = new MenuBar();
        menu = new Menu("File");
        quit = new MenuItem("Quit");
        mb.setMinSize(700, 27);
        menu.getItems().add(quit);
        mb.getMenus().add(menu);


        txt = new Text("In this object-oriented program we will hereby create an exam either manually or automatically and according to your own desire!");
        txt.setFont(Font.font("Verdana", 20));
        txt.setLayoutY(59);
        txt.setLayoutX(64);
        txt.setWrappingWidth(583);

        startbtn = new Button("Start");
        startbtn.setLayoutY(151);
        startbtn.setLayoutX(234);
        startbtn.setPrefSize(230, 200);

        ap.getChildren().addAll(mb, txt, startbtn);
        root.getChildren().addAll(ap);
    }

    public void InitiateVBox(VBox vbox) {
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
    }


}

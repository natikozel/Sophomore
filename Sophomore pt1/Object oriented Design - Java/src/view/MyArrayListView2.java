package view;

import controller.ExitController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listeners.ActionEventsListener;

import javax.swing.*;
import java.util.ArrayList;

public class MyArrayListView2 extends MainView implements UIView {

    private VBox root;
    private AnchorPane ap;
    private MenuBar mb;
    private MenuItem quit;
    private Menu menu;
    private TextArea txt1, txt2;
    private Button returnBack, btn1, btn2;
    private ExitController exit = new ExitController();
    private ArrayList< ActionEventsListener > allListeners = new ArrayList<>();

    public MyArrayListView2(Stage stage) throws Exception {
        super();
        stage.setTitle("MyArrayList view!");
        initValues();
        initActions();

        stage.setScene(new Scene(root, 1000, 689));
        stage.show();
    }

    @Override
    public void removeListener(ActionEventsListener listener) {
        allListeners.remove(listener);
    }


    @Override
    public void registerListener(ActionEventsListener listener) {
        allListeners.add(listener);
    }

    @Override
    public void initValues() {
        root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);

        mb = new MenuBar();
        menu = new Menu("File");
        quit = new MenuItem("Quit");
        menu.getItems().add(quit);
        mb.getMenus().add(menu);
        mb.setMinSize(1000, 27);
        mb.setLayoutY(-20);
        mb.setLayoutX(-10);

        ap = new AnchorPane();
        ap.setMinSize(867, 646);

        txt1 = new TextArea();
        txt1.setLayoutX(1);
        txt1.setLayoutY(270);
        txt1.setMinSize(1, 350);

        txt2 = new TextArea();
        txt2.setLayoutX(500);
        txt2.setLayoutY(270);
        txt2.setMinSize(1, 350);

        returnBack = new Button("Return");
        returnBack.setLayoutX(440);
        returnBack.setLayoutY(150);
        returnBack.setMinSize(100, 50);

        btn2 = new Button("Show Java's ArrayList");
        btn2.setLayoutX(685);
        btn2.setLayoutY(150);
        btn2.setMinSize(50, 50);

        btn1 = new Button("Show MyArrayList");
        btn1.setLayoutX(185);
        btn1.setLayoutY(150);
        btn1.setMinSize(50, 50);




        ap.getChildren().addAll(btn1, btn2, returnBack, txt1, txt2, mb);
        root.getChildren().addAll(ap);
    }


    @Override
    public void initActions() {

        quit.setOnAction(e-> {
            exit.exitAndExecute();
        });

        btn1.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.showMyArrayList(txt1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH MYARRAYLIST VIEW");
                }
            }
        });

        btn2.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.quickAndNonDupesCollection(txt2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH MYARRAYLIST VIEW");
                }
            }
        });

        returnBack.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.hideStage();
                    l.moveToScreenViewStage();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH MYARRAYLIST VIEW");
                }
            }
        });
    }

    @Override
    public void shutStageDown() {
        Stage currentStage = (Stage) mb.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void hideCurrentStage() {
        Stage currentStage = (Stage) mb.getScene().getWindow();
        currentStage.hide();
    }


}

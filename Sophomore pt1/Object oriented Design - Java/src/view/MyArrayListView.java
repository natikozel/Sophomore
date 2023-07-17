package view;

import controller.Exit;
import controller.ExitController;
import javafx.scene.text.Text;
import listeners.Observer;
import model.MyButton;
import model.MyLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import listeners.ActionEventsListener;

import javax.swing.*;
import java.util.ArrayList;

public class MyArrayListView extends MainView implements UIView {

    private VBox root;
    private AnchorPane ap;
    private MenuBar mb;
    private MenuItem quit;
    private Menu menu;
    private TextArea txt1, txt2;
    private Text txt, txt3;
    private MyLabel mylbl;
    private MyButton mybtn;
    private Button update, update2, returnBack, btn1, btn2, undo;
    private ExitController exit = new ExitController();
    private ArrayList< ActionEventsListener > allListeners = new ArrayList<>();
    private ArrayList <Observer> allObservers = new ArrayList<>();

    public MyArrayListView(Stage stage) throws Exception {
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

        btn1 = new Button("Create My Iterator");
        btn1.setLayoutX(185);
        btn1.setLayoutY(150);
        btn1.setMinSize(50, 50);

        btn2 = new Button("Show Java's ArrayList");
        btn2.setLayoutX(685);
        btn2.setLayoutY(150);
        btn2.setMinSize(50, 50);

        update = new Button("Remove & Update\nUsing MyIterator");
        update.setLayoutX(220);
        update.setLayoutY(70);
        update.setMinSize(50, 50);

        txt = new Text("Activate MyIterator's remove");
        txt.setFont(Font.font("Arial Italic", 13));
        txt.setLayoutY(100);
        txt.setLayoutX(50);

        mybtn = new MyButton("Activate Iterator");
        mybtn.setLayoutX(185);
        mybtn.setLayoutY(150);
        allObservers.add(mybtn);

        undo = new Button("Undo");
        undo.setLayoutY(25);
        undo.setLayoutX(250);
        undo.setMinSize(30, 30);


        mylbl = new MyLabel("Iterator is ready to be used!");
        mylbl.setLayoutY(180);
        allObservers.add(mylbl);

        txt3 = new Text("Activate Java's Iterator's remove");
        txt3.setFont(Font.font("Arial Italic", 13));
        txt3.setLayoutY(100);
        txt3.setLayoutX(500);

        update2 = new Button("Remove & Update\nUsing Java's Iterator");
        update2.setLayoutX(700);
        update2.setLayoutY(70);
        update2.setMinSize(50, 50);



        ap.getChildren().addAll(undo, txt3, update2,mybtn, mylbl,txt, update, btn1, btn2, returnBack, txt1, txt2, mb);
        root.getChildren().addAll(ap);
    }


    @Override
    public void initActions() throws Exception {

        quit.setOnAction(e-> {
            exit.exitAndExecute();
        });

        mybtn.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.showMyArrayList(txt1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH MYARRAYLIST VIEW");
                }
            }
        });

        btn1.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    for (Observer o : allObservers) {
                        l.attachObserversToModel(o);
                    }
                    l.activateIterator();
                    this.btn1.setVisible(false);
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

        update.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.activateCommander(txt1, 1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Iterator must be activated beforehand.");
                }
            }
        });
        update2.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.activateCommander(txt2, 2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please show the Collection's Array-list beforehand.");
                }
            }
        });

        undo.setOnAction(e-> {
            for (ActionEventsListener l : allListeners)
                try {
                    l.undoRemove(txt1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Must activate the iterator and remove something beforehand");
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

    public TextArea getTxt1() {
        return txt1;
    }

    public TextArea getTxt2() {
        return txt2;
    }
}

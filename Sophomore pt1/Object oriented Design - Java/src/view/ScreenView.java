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
import javafx.stage.Stage;
import listeners.ActionEventsListener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ScreenView extends MainView implements UIView {

    private VBox root;
    private AnchorPane ap;
    private MenuBar mb;
    private MenuItem quit;
    private MenuItem[] methods;
    private MenuButton menubtn;
    private Menu menu;
    private TextArea txt;
    private ExitController exit = new ExitController();
    private ArrayList< ActionEventsListener > allListeners = new ArrayList<>();

    public ScreenView(Stage stage) throws Exception {
        super();
        stage.setTitle("Choose a Method!");
        initValues();
        initActions();

        stage.setScene(new Scene(root, 913, 689));
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
        mb.setMinSize(913, 27);
        mb.setLayoutY(-25);
        mb.setLayoutX(-10);

        ap = new AnchorPane();
        ap.setMinSize(867, 646);

        menubtn = new MenuButton("Please select an option");
        menubtn.setLayoutX(14);
        menubtn.setLayoutY(14);
        menubtn.setMinSize(879, 0);
        methods = new MenuItem[]{new MenuItem("Show all Questions available and their Answers."),
                new MenuItem("Create your own Question & Answer."),
                new MenuItem("Update / Delete."),
                new MenuItem("Create an Exam."),
                new MenuItem("Clone an existing exam."),
                new MenuItem("Turn questions into a sorted Collection."),
                new MenuItem("Turn the sorted Collection of questions into a quick and non-dupes Collection."),
                new MenuItem("MyArrayList VS Java's ArrayList"),
                new MenuItem("MyArrayList Iterator With Remove Method"),
                new MenuItem("Reset Questions"),
                new MenuItem("Exit.")};
        menubtn.setFont(Font.font("Arial Italic", 15));
        menubtn.getItems().addAll(methods);

        txt = new TextArea();
        txt.setLayoutX(19);
        txt.setLayoutY(266);
        txt.setMinSize(861, 331);

        ap.getChildren().addAll(txt, menubtn, mb);
        root.getChildren().addAll(ap);


    }


    @Override
    public void initActions() {

        quit.setOnAction(e-> {
            exit.exitAndExecute();
        });

        methods[ 0 ].setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.print(txt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR HAS OCCURRED WITH PRINT");
                }
            }
        });

        methods[ 1 ].setOnAction(e -> {
            try {
                for (ActionEventsListener l : allListeners) {
                    l.hideStage();
                    l.moveToQuestionCreationStage();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception has been caught!\n ERROR OCCURRED");
                for (ActionEventsListener l : allListeners)
                    l.shutDown();
            }
        });

        methods[ 2 ].setOnAction(e -> {
            try {
                for (ActionEventsListener l : allListeners) {
                    l.hideStage();
                    l.moveToUpdateQuestionStage();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception has been caught!\n ERROR OCCURRED");
                for (ActionEventsListener l : allListeners)
                    l.shutDown();
            }
        });

        methods[ 3 ].setOnAction(e -> {
            try {
                for (ActionEventsListener l : allListeners) {
                    l.hideStage();
                    l.resetNewTestArrayList();
                    l.moveToExamCreationStage();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception has been caught!\n ERROR OCCURRED");
                for (ActionEventsListener l : allListeners)
                    l.shutDown();
            }
        });

        methods[ 4 ].setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.cloneExam();
                    l.print(txt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH CLONE METHOD");
                }
            }
        });

        methods[ 5 ].setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.questionsToSortedCollection(txt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH COLLECTION MAKING");
                }
            }
        });
        methods[ 6 ].setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.quickAndNonDupesCollection(txt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH COLLECTION MAKING");
                }
            }
        });
        methods[7].setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.MakeMyArrayList();
                    l.hideStage();
                    l.moveToMyArrayListView2();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH MYARRAYLIST");
                    l.shutDown();
                }
            }
        });
        methods[8].setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.MakeMyArrayList();
                    l.hideStage();
                    l.moveToMyArrayListView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH MYARRAYLIST");
                    l.shutDown();
                }
            }
        });
        methods[ 9 ].setOnAction(e -> {
            for (ActionEventsListener l :allListeners) {
                try {
                    l.resetQuestions();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH RESETTING QUESTIONS");
                }
            }
        });

        methods[ 10 ].setOnAction(e-> {
                exit.exitAndExecute();
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

    public MenuBar getMb() {
        return mb;
    }

    public void setMb(MenuBar mb) {
        this.mb = mb;
    }

    public MenuItem getQuit() {
        return quit;
    }

    public void setQuit(MenuItem quit) {
        this.quit = quit;
    }

    public MenuItem[] getMethods() {
        return methods;
    }

    public void setMethods(MenuItem[] methods) {
        this.methods = methods;
    }

    public MenuButton getMenubtn() {
        return menubtn;
    }

    public void setMenubtn(MenuButton menubtn) {
        this.menubtn = menubtn;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public TextArea getTxt() {
        return txt;
    }

    public void setTxt(TextArea txt) {
        this.txt = txt;
    }
}

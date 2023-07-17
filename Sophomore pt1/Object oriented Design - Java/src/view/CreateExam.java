package view;

import controller.Exit;
import controller.ExitController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import listeners.ActionEventsListener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CreateExam extends MainView implements UIView {

    private VBox root;
    private MenuBar mb;
    private Menu menu;
    private MenuItem quit;
    private SplitPane sp;
    private AnchorPane apTab1, apTab2, questionsPane;
    private TabPane tp;
    private Tab tab1, tab2;
    private Label questionRepository, amountOfQuestions1, amountOfQuestions2, examName, lecturerName, institution, city;
    private TextField questionPicked1, questionPicked2, eName, lName, instiField, cityField;
    private Button addQuestion, createExam, preViewTest, createAutoExam, showQuestions, returnToPrevious;
    private TextArea resultArea1, resultArea2, resultArea3;
    private Pane pane;
    private final ExitController exit = new ExitController();
    private final ArrayList< ActionEventsListener > allListeners = new ArrayList<>();

    public CreateExam(Stage stage) throws Exception {
        super();
        stage.setTitle("Create a new exam!");
        initValues();
        initActions();

        stage.setScene(new Scene(root, 900, 600));
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
        super.InitiateVBox(root);
        mb = new MenuBar();
        menu = new Menu("File");
        quit = new MenuItem("Quit");
        menu.getItems().add(quit);
        mb.getMenus().add(menu);
        mb.setMinSize(900, 25);
        sp = new SplitPane();
        sp.setMinSize(100, 160);
        tp = new TabPane();
        tp.setMinSize(446, 440);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        apTab1 = new AnchorPane();
        apTab1.setMinSize(446, 526);
        apTab1.setLayoutY(6);
        apTab2 = new AnchorPane();
        apTab2.setMinSize(446, 526);
        apTab2.setLayoutY(6);
        questionsPane = new AnchorPane();
        questionsPane.setMinSize(430, 526);
        tab1 = new Tab("Manual Exam");
        tab2 = new Tab("Automatic Exam");
        amountOfQuestions1 = new Label("Pick questions from Repository by their position (1,2,3... etc");
        amountOfQuestions1.setMinSize(386, 18);
        amountOfQuestions1.setLayoutY(23);
        amountOfQuestions1.setLayoutX(20);
        amountOfQuestions1.setFont(Font.font("System Bold", 13));
        amountOfQuestions2 = new Label("How many questions?");
        amountOfQuestions2.setMinSize(150, 19);
        amountOfQuestions2.setLayoutY(22);
        amountOfQuestions2.setLayoutX(38);
        amountOfQuestions2.setFont(Font.font("System Bold", 13));
        questionRepository = new Label("Question Repository And Information");
        questionRepository.setMinSize(335, 18);
        questionRepository.setLayoutY(28);
        questionRepository.setLayoutX(48);
        questionRepository.setFont(Font.font("Arial Italic", 20));
        examName = new Label("Give your exam a name: ");
        examName.setMinSize(150, 18);
        examName.setLayoutY(88);
        examName.setLayoutX(28);
        questionPicked1 = new TextField();
        questionPicked1.setMinSize(280, 26);
        questionPicked1.setLayoutY(53);
        questionPicked1.setLayoutX(20);
        questionPicked2 = new TextField();
        questionPicked2.setMinSize(225, 26);
        questionPicked2.setLayoutY(19);
        questionPicked2.setLayoutX(197);

        eName = new TextField();
        eName.setMinSize(125, 26);
        eName.setLayoutY(80);
        eName.setLayoutX(208);
        lName = new TextField();
        lName.setMinSize(125, 26);
        lName.setLayoutY(120);
        lName.setLayoutX(208);
        cityField = new TextField();
        cityField.setMinSize(125, 26);
        cityField.setLayoutY(200);
        cityField.setLayoutX(208);
        instiField = new TextField();
        instiField.setMinSize(125, 26);
        instiField.setLayoutY(160);
        instiField.setLayoutX(208);
        city = new Label("City: ");
        city.setMinSize(150, 18);
        city.setLayoutY(208);
        city.setLayoutX(28);
        institution = new Label("Institution Name: ");
        institution.setMinSize(150, 18);
        institution.setLayoutY(168);
        institution.setLayoutX(28);

        addQuestion = new Button("Add question");
        addQuestion.setLayoutY(53);
        addQuestion.setLayoutX(316);
        createExam = new Button("Create Exam");
        createExam.setLayoutY(100);
        createExam.setLayoutX(87);
        createExam.setMinSize(272, 39);
        lecturerName = new Label("Enter your full name: ");
        lecturerName.setMinSize(150, 18);
        lecturerName.setLayoutY(128);
        lecturerName.setLayoutX(28);
        showQuestions = new Button("Show available Questions");
        showQuestions.setMinSize(100, 26);
        showQuestions.setLayoutY(230);
        showQuestions.setLayoutX(260);
        preViewTest = new Button("Preview Test");
        preViewTest.setLayoutY(167);
        preViewTest.setLayoutX(88);
        preViewTest.setMinSize(272, 26);
        returnToPrevious = new Button("Return");
        returnToPrevious.setLayoutY(12);
        returnToPrevious.setLayoutX(372);
        returnToPrevious.setMinSize(157, 25);
        createAutoExam = new Button("Create Exam");
        createAutoExam.setLayoutY(100);
        createAutoExam.setLayoutX(88);
        createAutoExam.setMinSize(276, 39);
        pane = new Pane();
        pane.setMinSize(900, 50);
        resultArea1 = new TextArea();
        resultArea1.setLayoutY(216);
        resultArea1.setMinSize(0, 240);
        resultArea2 = new TextArea();
        resultArea2.setLayoutY(216);
        resultArea2.setMinSize(0, 240);
        resultArea3 = new TextArea();
        resultArea3.setLayoutY(261);
        resultArea3.setMinSize(0, 240);

        apTab1.getChildren().addAll(amountOfQuestions1, questionPicked1, addQuestion, createExam, preViewTest, resultArea1);
        apTab2.getChildren().addAll(amountOfQuestions2, resultArea2, questionPicked2, createAutoExam);
        questionsPane.getChildren().addAll(cityField, city, instiField, institution, eName, lName, examName, lecturerName, showQuestions, questionRepository, resultArea3);
        tab1.setContent(apTab1);
        tab2.setContent(apTab2);
        tp.getTabs().addAll(tab1, tab2);
        sp.getItems().addAll(tp, questionsPane);
        pane.getChildren().add(returnToPrevious);
        root.getChildren().addAll(mb, sp, pane);
    }


    @Override
    public void initActions() throws Exception {

        quit.setOnAction(e-> {
            exit.exitAndExecute();
        });
        CreateQuestion.returnToMethodsScreen(returnToPrevious, this.allListeners);

        showQuestions.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.print(resultArea3);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR HAS OCCURRED WITH PRINT");
                }
            }
        });

        createAutoExam.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.createAutomaticExam(questionPicked2.getText(), lName.getText(), eName.getText(), cityField.getText(), instiField.getText());
                } catch (IOException | CloneNotSupportedException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH AUTO EXAM CREATION");
                }
            }
        });

        createExam.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                try {
                    l.createManualExam(lName.getText(), eName.getText(), cityField.getText(), instiField.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR OCCURRED WITH MANUAL EXAM CREATION");
                }
            }
        });

        preViewTest.setOnAction(e -> {
            for (ActionEventsListener l : allListeners)
                try {
                    l.printPreView(resultArea1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR HAS OCCURRED WITH PRINT");
                }
        });

        addQuestion.setOnAction(e -> {
            for (ActionEventsListener l : allListeners) {
                l.addQuestionToManualExamCollection(questionPicked1.getText());
                questionPicked1.clear();
            }
        });

    }

    public void shutStageDown() {
        Stage currentStage = (Stage) mb.getScene().getWindow();
        currentStage.close();
    }

    public void hideCurrentStage() {
        Stage currentStage = (Stage) mb.getScene().getWindow();
        currentStage.hide();
    }
}
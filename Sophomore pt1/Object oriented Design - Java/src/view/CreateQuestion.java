package view;

import controller.Exit;
import controller.ExitController;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.ActionEventsListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import javax.swing.*;
import java.util.ArrayList;

public class CreateQuestion extends MainView implements UIView {

    private BorderPane root;
    private TabPane tp;
    private Tab americanQuestionTab, openFieldTab;
    private AnchorPane apA, apO; // A for american and O for openfield
    private VBox vboxA, vboxO;
    private Label questionInfo1, questionInfo2;
    private Separator separator1, separator2;
    private GridPane gpA, gpO;
    private Text questionText1, correctAns1, questionText2, correctAns2;
    private Text[] wrongAns; // 4
    private TextField[] wrongAmericanAnswers; // 4
    private TextField americanAnswer, americanQuestion, openFieldAnswer, openFieldQuestion;
    private Button create1, create2, returnToPrevious1, returnToPrevious2, exit1, exit2;
    private ExitController exit = new ExitController();
    private ArrayList< ActionEventsListener > allListeners = new ArrayList<>();

    public CreateQuestion(Stage stage) throws Exception {
        super();
        stage.setTitle("Create a new Question!");
        initValues();
        initActions();

        stage.setScene(new Scene(root, 500, 598));
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
        root = new BorderPane();
        root.setPrefWidth(500);
        root.setPrefHeight(600);
        root.setPadding(new Insets(10));
        tp = new TabPane();
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        americanQuestionTab = new Tab("American Question");
        openFieldTab = new Tab("OpenField Question");
        apA = new AnchorPane();
        apO = new AnchorPane();
        vboxA = new VBox();
        vboxO = new VBox();
        vboxA.setPadding(new Insets(10));
        vboxA.setSpacing(5);
        vboxA.setMaxSize(429, 522);
        vboxO.setPadding(new Insets(10));
        vboxO.setSpacing(5);
        vboxO.setMaxSize(429, 522);

        questionInfo1 = new Label("Question Information");
        questionInfo1.setMinSize(250, 0);
        questionInfo1.setFont(Font.font("Arial Italic", 20));
        questionInfo2 = new Label("Question Information");
        questionInfo2.setMinSize(250, 0);
        questionInfo2.setFont(Font.font("Arial Italic", 20));

        separator1 = new Separator();
        separator1.setMinSize(387, 4);
        separator2 = new Separator();
        separator2.setMinSize(387, 4);

        gpA = new GridPane();
        gpO = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints(10, 102.4, 190);
        ColumnConstraints col2 = new ColumnConstraints(10, 285.6, 323.6);
        ColumnConstraints col3 = new ColumnConstraints(10, 102.4, 190);
        ColumnConstraints col4 = new ColumnConstraints(10, 285.6, 323.6);
        gpA.getColumnConstraints().addAll(col1, col2);
        gpO.getColumnConstraints().addAll(col3, col4);
        for (int i = 0; i < 6; i++) {
            RowConstraints c = new RowConstraints(10, 30, 50);
            RowConstraints d = new RowConstraints(10, 30, 50);
            gpA.getRowConstraints().addAll(c);
            gpO.getRowConstraints().addAll(d);
        }

        questionText1 = new Text("Question's text:");
        questionText1.setFont(Font.font("Arial Italic", 13));
        questionText2 = new Text("Question's text:");
        questionText2.setFont(Font.font("Arial Italic", 13));

        correctAns1 = new Text("Correct Answer");
        correctAns2 = new Text("Correct Answer");

        wrongAns = new Text[]{new Text("Wrong Answer #1"), new Text("Wrong Answer #2"), new Text("Wrong Answer #3"), new Text("Wrong Answer #4")};

        wrongAmericanAnswers = new TextField[]{new TextField(), new TextField(), new TextField(), new TextField()};
        openFieldQuestion = new TextField();
        openFieldAnswer = new TextField();
        americanQuestion = new TextField();
        americanAnswer = new TextField();

        for (int i = 0; i < wrongAmericanAnswers.length; i++)
            gpA.add(wrongAmericanAnswers[ i ], 1, i + 3);
        for (int i = 0; i < wrongAns.length; i++)
            gpA.add(wrongAns[ i ], 0, i + 3);
        gpA.add(americanQuestion, 1, 1);
        gpA.add(americanAnswer, 1, 2);
        gpA.add(questionText1, 0, 1);
        gpA.add(correctAns1, 0, 2);
        gpO.add(questionText2, 0, 1);
        gpO.add(correctAns2, 0, 2);
        gpO.add(openFieldQuestion, 1, 1);
        gpO.add(openFieldAnswer, 1, 2);

        create1 = new Button("Create Question");
        exit1 = new Button("Exit");
        returnToPrevious1 = new Button("Return");
        create2 = new Button("Create Question");
        exit2 = new Button("Exit");
        returnToPrevious2 = new Button("Return");
        createButtons(create1, exit1, returnToPrevious1);
        createButtons(create2, exit2, returnToPrevious2);

        tp.getTabs().addAll(americanQuestionTab, openFieldTab);
        americanQuestionTab.setContent(apA);
        openFieldTab.setContent(apO);
        vboxA.getChildren().addAll(questionInfo1, separator1, gpA);
        vboxO.getChildren().addAll(questionInfo2, separator2, gpO);
        apA.getChildren().addAll(vboxA, returnToPrevious1, exit1, create1);
        apO.getChildren().addAll(vboxO, returnToPrevious2, exit2, create2);
        root.setCenter(tp);

    }

    private void createButtons(Button create, Button exit, Button returnToPrevious) {
        UpdateQuestion.createButtonsForThisView(create, exit, returnToPrevious);
    }

    @Override
    public void initActions() throws Exception {
        returnToMethodsScreen(returnToPrevious1, this.allListeners);
        returnToMethodsScreen(returnToPrevious2, this.allListeners);
        exit1.setOnAction(e -> {
            exit.exitAndExecute();
        });
        exit2.setOnAction(e -> {
            exit.exitAndExecute();
        });
        create1.setOnAction(e -> {
            try {
                for (ActionEventsListener l : allListeners) {
                    String[] allAnswers = new String[ wrongAmericanAnswers.length ];
                    for (int i = 0; i < wrongAmericanAnswers.length; i++)
                        allAnswers[ i ] = wrongAmericanAnswers[ i ].getText();
                    l.createAmericanQuestion(americanQuestion.getText(), americanAnswer.getText(), allAnswers);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR CREATING AMERICAN QUESTION");
            }
        });
        create2.setOnAction(e -> {
            try {
                for (ActionEventsListener l : allListeners) {
                    l.createOpenFieldQuestion(openFieldQuestion.getText(), openFieldAnswer.getText());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR CREATING OPEN-FIELD QUESTION");
            }
        });
    }

    public static void returnToMethodsScreen(Button returnToPrevious, ArrayList< ActionEventsListener > allListeners) {
        returnToPrevious.setOnAction(e -> {
            try {
                for (ActionEventsListener l : allListeners) {
                    l.hideStage();
                    l.moveToScreenViewStage();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception has been caught!\n ERROR OCCURRED");
                for (ActionEventsListener l : allListeners)
                    l.shutDown();
            }
        });
    }

    @Override
    public void hideCurrentStage() {
        Stage currentStage = (Stage) exit1.getScene().getWindow();
        currentStage.hide();
    }

    @Override
    public void shutStageDown() {
        Stage currentStage = (Stage) exit1.getScene().getWindow();
        currentStage.close();
    }


}

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

public class UpdateQuestion extends MainView implements UIView {

    private BorderPane root;
    private TabPane tp;
    private Tab UpdateQuestion, UpdateAnswer;
    private AnchorPane updateQuestionAnchor, updateAnswerAnchor;
    private VBox vboxQuestion, vboxAnswer;
    private Label questionInfo1, questionInfo2;
    private Separator separator1, separator2;
    private GridPane gpQuestion, gpAnswer;
    private Text questionID1, newQuestion, questionID2, newAnswer;
    private TextField questionIDField1, questionIDField2, newQuestionField, newAnswerField;
    private Button update1, update2, remove, returnToPrevious1, returnToPrevious2, exit1, exit2;
    private ExitController exit = new ExitController();
    private ArrayList< ActionEventsListener > allListeners = new ArrayList<>();

    public UpdateQuestion(Stage stage) throws Exception {
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
        UpdateQuestion = new Tab("Update Question");
        UpdateAnswer = new Tab("Update/Delete Answer");
        updateQuestionAnchor = new AnchorPane();
        updateAnswerAnchor = new AnchorPane();
        vboxQuestion = new VBox();
        vboxAnswer = new VBox();
        vboxQuestion.setPadding(new Insets(10));
        vboxQuestion.setSpacing(5);
        vboxQuestion.setMaxSize(429, 522);
        vboxAnswer.setPadding(new Insets(10));
        vboxAnswer.setSpacing(5);
        vboxAnswer.setMaxSize(429, 522);

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

        gpQuestion = new GridPane();
        gpAnswer = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints(10, 102.4, 190);
        ColumnConstraints col2 = new ColumnConstraints(10, 285.6, 323.6);
        ColumnConstraints col3 = new ColumnConstraints(10, 102.4, 190);
        ColumnConstraints col4 = new ColumnConstraints(10, 285.6, 323.6);
        gpQuestion.getColumnConstraints().addAll(col1, col2);
        gpAnswer.getColumnConstraints().addAll(col3, col4);
        for (int i = 0; i < 2; i++) {
            RowConstraints c = new RowConstraints(10, 30, 50);
            RowConstraints d = new RowConstraints(10, 30, 50);
            gpQuestion.getRowConstraints().addAll(c);
            gpAnswer.getRowConstraints().addAll(d);
        }

        questionID1 = new Text("Question's ID:");
        questionID1.setFont(Font.font("Arial Italic", 13));
        questionID2 = new Text("Question's ID:");
        questionID2.setFont(Font.font("Arial Italic", 13));

        newQuestion = new Text("New question:");
        newAnswer = new Text("New right answer:");
        newAnswerField = new TextField();
        newQuestionField = new TextField();
        questionIDField2 = new TextField();
        questionIDField1 = new TextField();

        gpQuestion.add(questionIDField1, 1, 1);
        gpQuestion.add(questionID1, 0, 1);
        gpQuestion.add(newQuestionField, 1, 2);
        gpQuestion.add(newQuestion, 0, 2);
        gpAnswer.add(questionIDField2, 1, 1);
        gpAnswer.add(questionID2, 0, 1);
        gpAnswer.add(newAnswerField, 1, 2);
        gpAnswer.add(newAnswer, 0, 2);

        update1 = new Button("Update");
        exit1 = new Button("Exit");
        returnToPrevious1 = new Button("Return");
        update2 = new Button("Update");
        exit2 = new Button("Exit");
        returnToPrevious2 = new Button("Return");
        remove = new Button("Remove");
        createButtons(update1, exit1, returnToPrevious1);
        update2.setLayoutY(521);
        update2.setLayoutX(14);
        update2.setMinSize(100, 25);
        exit2.setLayoutY(521);
        exit2.setLayoutX(246);
        exit2.setMinSize(100, 25);
        returnToPrevious2.setLayoutY(521);
        returnToPrevious2.setLayoutX(362);
        returnToPrevious2.setMinSize(100, 25);
        remove.setLayoutY(521);
        remove.setLayoutX(130);
        remove.setMinSize(100, 25);


        tp.getTabs().addAll(UpdateQuestion, UpdateAnswer);
        UpdateQuestion.setContent(updateQuestionAnchor);
        UpdateAnswer.setContent(updateAnswerAnchor);
        vboxQuestion.getChildren().addAll(questionInfo1, separator1, gpQuestion);
        vboxAnswer.getChildren().addAll(questionInfo2, separator2, gpAnswer);
        updateQuestionAnchor.getChildren().addAll(vboxQuestion, returnToPrevious1, exit1, update1);
        updateAnswerAnchor.getChildren().addAll(vboxAnswer, returnToPrevious2, exit2, update2, remove);
        root.setCenter(tp);

    }

    private void createButtons(Button create, Button exit, Button returnToPrevious) {
        createButtonsForThisView(create, exit, returnToPrevious);
    }

    static void createButtonsForThisView(Button create, Button exit, Button returnToPrevious) {
        create.setLayoutY(522);
        create.setLayoutX(14);
        create.setMinSize(142, 26);
        exit.setLayoutY(522);
        exit.setLayoutX(322);
        exit.setMinSize(141, 26);
        returnToPrevious.setLayoutY(522);
        returnToPrevious.setLayoutX(164);
        returnToPrevious.setMinSize(149, 26);
    }

    @Override
    public void initActions() throws Exception {
        CreateQuestion.returnToMethodsScreen(returnToPrevious1, allListeners);
        CreateQuestion.returnToMethodsScreen(returnToPrevious2, allListeners);
        exit1.setOnAction(e-> {
            exit.exitAndExecute();
        });
        exit2.setOnAction(e-> {
            exit.exitAndExecute();
        });

        update1.setOnAction(e -> {
            try {
                for (ActionEventsListener l : allListeners)
                    l.updateQuestion(questionIDField1.getText(), newQuestionField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR UPDATING QUESTION");
            }
        });

        update2.setOnAction(e -> {
            try {
            for (ActionEventsListener l : allListeners)
                l.updateAnswer(questionIDField2.getText(), newAnswerField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR UPDATING QUESTION");
            }
        });

        remove.setOnAction(e -> {
            try {
            for (ActionEventsListener l : allListeners)
                l.removeAnswer(questionIDField2.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR UPDATING QUESTION");
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

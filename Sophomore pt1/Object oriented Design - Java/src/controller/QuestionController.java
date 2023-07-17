package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import listeners.Observer;
import model.*;
import listeners.ActionEventsListener;
import view.*;

import java.io.IOException;
import java.util.*;

public class QuestionController implements ActionEventsListener {

    private MainView view;
    private Exam model;
    private IteratorController ic;
    private MovementController mc;
    private Stack< MyArrayList< Question >.Memento > stack = new Stack<>();

    public QuestionController(MainView view, Exam model) throws Exception {
        this.view = view;
        this.model = model;

        model.registerListener(this);
        view.registerListener(this);

    }

    @Override
    public void shutDown() {
        this.view.shutStageDown();
    }

    @Override
    public void hideStage() {
        this.view.hideCurrentStage();
    }

    @Override
    public void moveToScreenViewStage() throws Exception {
        mc = new MovementController(new ScreenView(new Stage()), this);
        mc.MoveAndExecute();
    }

    @Override
    public void moveToQuestionCreationStage() throws Exception {
        mc = new MovementController(new CreateQuestion(new Stage()), this);
        mc.MoveAndExecute();
    }

    @Override
    public void createAmericanQuestion(String question, String answer, String[] wrongAnswers) throws Exception {
        model.createAmericanQuestion(question, answer, wrongAnswers);
    }

    @Override
    public void createOpenFieldQuestion(String question, String answer) throws Exception {
        model.createOpenFieldQuestion(question, answer);
    }

    @Override
    public void moveToUpdateQuestionStage() throws Exception {
        mc = new MovementController(new UpdateQuestion(new Stage()), this);
        mc.MoveAndExecute();
    }

    @Override
    public void removeAnswer(String questionIDField2) throws Exception {
        model.removeAnswer(questionIDField2);
    }

    @Override
    public void updateAnswer(String questionIDField2, String newAnswerField) throws Exception {
        model.updateAnswer(questionIDField2, newAnswerField);
    }

    @Override
    public void updateQuestion(String questionIDField1, String newQuestionField) throws Exception {
        model.updateQuestion(questionIDField1, newQuestionField);
    }

    @Override
    public void moveToExamCreationStage() throws Exception {
        mc = new MovementController(new CreateExam(new Stage()), this);
        mc.MoveAndExecute();
    }

    @Override
    public void createAutomaticExam(String amountOfQuestions, String lName, String eName, String city, String institution) throws IOException, CloneNotSupportedException, ClassNotFoundException {
        model.createAutomaticExam(amountOfQuestions, lName, eName, city, institution);
    }

    @Override
    public void resetNewTestArrayList() {
        model.resetNewTestArrayList();
    }

    @Override
    public void activateCommander(TextArea txt, int indicator) throws Exception {
        if (this.view instanceof MyArrayListView that) {
            if (Objects.equals(txt.getText(), ""))
                throw new IllegalArgumentException();
        }

        Iterator< Question > itr = indicator == 1 ? model.getMyArray().iterator() : model.getMyQuickCollection().iterator();
        ic = new IteratorController(itr);
        MyArrayList<Question>.Memento q = model.getMyArray().createMemento();
        stack.push(q);
        ic.removeAndExecute();
        printCollection(txt, itr);
    }

    @Override
    public void undoRemove(TextArea txt) throws Exception {
        MyArrayList<Question>.Memento q;
        if (stack.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            q = stack.pop();
            model.getMyArray().setMemento(q);
            printCollection(txt, model.getMyArray().iterator());
        }
    }

    @Override
    public void addQuestionToManualExamCollection(String questionNum) {
        model.addQuestion(questionNum);
    }

    @Override
    public void createManualExam(String lName, String eName, String city, String institution) throws Exception {
        model.createManualExam(lName, eName, city, institution);
    }

    @Override
    public void printPreView(TextArea resultArea) throws Exception {
        resultArea.setWrapText(true);
        int k = 1;
        setNewTextArea(resultArea, model.getNewTest().iterator());
    }

    @Override
    public void questionsToSortedCollection(TextArea txt) throws Exception {
        model.makeSortedCollection();
        printCollection(txt, model.getMyCollection().iterator());
    }

    @Override
    public void quickAndNonDupesCollection(TextArea txt) throws Exception {
        model.makeQuickAndNonDupesCollection();
        printCollection(txt, model.getMyQuickCollection().iterator());
    }

    @Override
    public void MakeMyArrayList() throws Exception {
        model.makeQuickAndNonDupesCollection();
        model.MakeMyArrayList();
    }

    @Override
    public void moveToMyArrayListView() throws Exception {
        mc = new MovementController(new MyArrayListView(new Stage()), this);
        mc.MoveAndExecute();
    }

    @Override
    public void showMyArrayList(TextArea txt) throws Exception {
        stack = new Stack<>();
        model.MakeMyArrayList();
        model.showMyArrayList(txt);
    }

    @Override
    public void attachObserversToModel(Observer o) {
        model.getMyArray().attach(o);
    }

    @Override
    public void activateIterator() {
        model.createIterator();
    }

    @Override
    public void moveToMyArrayListView2() throws Exception {
        mc = new MovementController(new MyArrayListView2(new Stage()), this);
        mc.MoveAndExecute();
    }

    public void printCollection(TextArea txt, Iterator< Question > collection) throws CloneNotSupportedException {
        txt.setWrapText(true);
        setNewTextArea(txt, collection);
    }

    public void print(TextArea resultArea) throws Exception {

        resultArea.setWrapText(true);
        setNewTextArea(resultArea, model.getAllQuestions().iterator());

    }

    @Override
    public void cloneExam() throws IOException, CloneNotSupportedException {
        model.cloneExam();
    }

    public void resetQuestions() throws IOException {
        model.resetQuestions();
    }

    public void setNewTextArea(TextArea resultArea, Iterator< Question > itr) throws CloneNotSupportedException {
        StringBuilder sb = new StringBuilder("");
        int k = 1;

        if (!itr.hasNext()) resultArea.setText("");
        while (itr.hasNext()) {
            Question question = itr.next();
            if (question != null)
                sb.append(k++).append(") ").append(question.showAllQuestionAndAnswer());
            resultArea.setText(String.valueOf(sb));
        }
    }

    public MainView getView() {
        return view;
    }

    public void setView(MainView newView) {
        this.view = newView;
    }
}

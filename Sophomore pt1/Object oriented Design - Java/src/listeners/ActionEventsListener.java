package listeners;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.MyButton;
import model.MyLabel;
import model.Question;

import java.io.IOException;
import java.util.ArrayList;

public interface ActionEventsListener {

    void cloneExam() throws IOException, CloneNotSupportedException;
    void shutDown();
    void resetQuestions() throws IOException;
    void hideStage();
    void moveToScreenViewStage() throws Exception;
    void print(TextArea resultArea2) throws Exception;
    void moveToQuestionCreationStage() throws Exception;
    void createAmericanQuestion(String question, String answer, String[] wrongAnswers) throws Exception;
    void createOpenFieldQuestion(String question, String answer) throws Exception;
    void moveToUpdateQuestionStage() throws Exception;
    void removeAnswer(String questionIDField2) throws Exception;
    void updateAnswer(String questionIDField2, String newAnswerField) throws Exception;
    void updateQuestion(String questionIDField1, String newQuestionField) throws Exception;
    void moveToExamCreationStage() throws Exception;
    void addQuestionToManualExamCollection(String questionNum);
    void createManualExam(String text1, String text2, String text3, String text4) throws Exception;
    void printPreView(TextArea resultArea1) throws Exception;
    void questionsToSortedCollection(TextArea txt) throws Exception;
    void quickAndNonDupesCollection(TextArea txt) throws Exception;
    void MakeMyArrayList() throws Exception;
    void moveToMyArrayListView() throws Exception;
    void showMyArrayList(TextArea txt) throws Exception;
    void attachObserversToModel(Observer o);
    void activateIterator() throws Exception;
    void moveToMyArrayListView2() throws Exception;
    void createAutomaticExam(String text, String text1, String text2, String text3, String text4) throws IOException, CloneNotSupportedException, ClassNotFoundException;
    void resetNewTestArrayList();
    void activateCommander(TextArea txt, int indicator) throws Exception;
    void undoRemove(TextArea txt) throws Exception;
}

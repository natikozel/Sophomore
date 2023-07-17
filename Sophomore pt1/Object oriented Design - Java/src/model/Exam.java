package model;

import javafx.scene.control.TextArea;
import listeners.ActionEventsListener;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Exam {

    private ArrayList< Question > allQuestions;
    private ArrayList< Question > newTest;
    private ArrayList< ActionEventsListener > allListeners;
    private Set< Question > mySortedCollection;
    private Set< Question > myQuickCollection;
    private MyArrayList< Question > MyArray;
    private Iterator <Question> itr;

    public Exam() {
        try {
            allQuestions = MainMethods.readEverythingFromBinaryFile();
            newTest = new ArrayList<>();
            mySortedCollection = new TreeSet<>(Comparator.reverseOrder());
            myQuickCollection = new LinkedHashSet<>();
            allListeners = new ArrayList<>();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR HAS OCCURRED IN EXAM CLASS");
        }
    }


    public void resetQuestions() throws IOException {
        allQuestions = new ArrayList<>();
        Question q1 = new AmericanQuestion("What is karen's last name?", new Answer("Kalif", true));
        Question q2 = new OpenFieldQuestion("How many days in a year?", new Answer("365", true));
        Question q3 = new OpenFieldQuestion("How many months in a year?", new Answer("12", true));
        Question q4 = new AmericanQuestion("How many months in a year?", new Answer("12", true));
        Question q5 = new AmericanQuestion("In which year was Facebook first launched?", new Answer("2004", true));
        Question q6 = new OpenFieldQuestion("How many days in a year?", new Answer("365", true));
        Question q7 = new OpenFieldQuestion("How many calories is there in water?", new Answer("0", true));
        Question q8 = new AmericanQuestion("Which country gifted the State Of Liberty to the US?", new Answer("France", true));
        allQuestions.add(q1);
        allQuestions.add(q2);
        allQuestions.add(q3);
        allQuestions.add(q4);
        allQuestions.add(q5);
        allQuestions.add(q6);
        allQuestions.add(q7);
        allQuestions.add(q8);
        q1.addAnswer(new Answer("Cohen", false));
        q1.addAnswer(new Answer("Levi", false));
        q1.addAnswer(new Answer("Biton", false));
        q1.addAnswer(new Answer("Bosani", false));
        q1.addAnswer(new Answer("Aviav", false));
        q1.addAnswer(new Answer("Barkat", false));
        q8.addAnswer(new Answer("England", false));
        q8.addAnswer(new Answer("Austria", false));
        q8.addAnswer(new Answer("Germany", false));
        q8.addAnswer(new Answer("The Netherlands", false));
        q4.addAnswer(new Answer("3", false));
        q4.addAnswer(new Answer("4", false));
        q4.addAnswer(new Answer("6", false));
        q4.addAnswer(new Answer("7", false));
        q4.addAnswer(new Answer("8", false));
        q4.addAnswer(new Answer("9", false));
        q5.addAnswer(new Answer("2005", false));
        q5.addAnswer(new Answer("2006", false));
        q5.addAnswer(new Answer("2007", false));
        q5.addAnswer(new Answer("2008", false));
        q5.addAnswer(new Answer("2009", false));
        q5.addAnswer(new Answer("2010", false));
        MainMethods.saveEverythingIntoBinaryFile(allQuestions);
    }


    public void registerListener(ActionEventsListener listener) {
        allListeners.add(listener);
    }

    public void removeListener(ActionEventsListener listener) {
        allListeners.remove(listener);
    }

    public ArrayList< Question > getAllQuestions() {
        newTest = new ArrayList<>();
        return allQuestions;
    }

    public void setAllQuestions(ArrayList< Question > allQuestions) {
        this.allQuestions = allQuestions;
    }

    public ArrayList< Question > getNewTest() {
        return newTest;
    }

    public void setNewTest(ArrayList< Question > newTest) {
        this.newTest = newTest;
    }

    public void cloneExam() throws CloneNotSupportedException, IOException {
        cloneExam(allQuestions);
    }

    public void createOpenFieldQuestion(String question, String answer) throws IndexOutOfBoundsException {
        if (!Objects.equals(question, "") && question != null && answer != null && !Objects.equals(answer, "")) {
            try {
                Question newQuestion = new OpenFieldQuestion(question, new Answer(answer, true));
                MainMethods.addQuestion(this.allQuestions, newQuestion);
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Question added and saved.");
                for (ActionEventsListener l : allListeners) {
                    l.hideStage();
                    l.moveToScreenViewStage();
                }
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR MOVING TO SCREEN VIEW PAGE");
            }
        } else {
            JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
        }
    }

    public void createAmericanQuestion(String question, String answer, String[] wrongAnswers) throws IOException, IndexOutOfBoundsException {
        ArrayList< Answer > newList = new ArrayList<>();
        if (!Objects.equals(question, "") && question != null && answer != null && !Objects.equals(answer, "")) {
            try {
                newList.add(new Answer(answer, true));
                for (int i = 0; i < wrongAnswers.length; i++) {
                    if (wrongAnswers[ i ] != null && !Objects.equals(wrongAnswers[ i ], ""))
                        newList.add(new Answer(wrongAnswers[ i ], false));
                }
                if (newList.get(0) != null) {
                    Question newQuestion = new AmericanQuestion(question, newList);
                    MainMethods.addQuestion(this.allQuestions, newQuestion);
                    MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                    JOptionPane.showMessageDialog(null, "Question added and saved.");
                    for (ActionEventsListener l : allListeners) {
                        l.hideStage();
                        l.moveToScreenViewStage();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
                }
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR CREATING AMERICAN QUESTION");
            }
        } else {
            JOptionPane.showMessageDialog(null, "An empty question is illegal. Try again.");
        }
    }

    public void createIterator() {
        itr = MyArray.iterator();
    }

    public void updateQuestion(String questionID, String newQuestion) {
        try {
            if (MainMethods.IDExist(questionID, allQuestions) != -1) {
                allQuestions.get(MainMethods.IDExist(questionID, allQuestions)).setQuestionText(newQuestion);
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Question updated successfully!");
            } else
                JOptionPane.showMessageDialog(null, "ID doesn't exist! Try again!");
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }
    }

    public void updateAnswer(String questionID, String newAnswer) {
        try {
            Answer answer = new Answer(newAnswer, true);
            if (MainMethods.IDExist(questionID, allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID, allQuestions)) instanceof OpenFieldQuestion that) {
                answer.setID(that.getAnswer().getID());
                that.setAnswer(answer);
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Answer updated successfully!");
            } else if (MainMethods.IDExist(questionID, allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID, allQuestions)) instanceof AmericanQuestion that) {
                answer.setID(that.getSpecificAnswer(0).getID());
                that.setRightAnswer(answer);
                that.setSpecificAnswer(answer, 0);
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                JOptionPane.showMessageDialog(null, "Answer updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "ID doesn't exist! Try again!");
            }
        } catch (NumberFormatException | IOException | CloneNotSupportedException e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }
    }

    public void removeAnswer(String questionID) {
        try {
            if (MainMethods.IDExist(questionID, allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID, allQuestions)) instanceof OpenFieldQuestion that) {
                ((OpenFieldQuestion) allQuestions.get(MainMethods.IDExist(questionID, allQuestions))).setAnswer(null);
                JOptionPane.showMessageDialog(null, "Answer removed successfully!");
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);

            } else if (MainMethods.IDExist(questionID, allQuestions) != -1 && allQuestions.get(MainMethods.IDExist(questionID, allQuestions)) instanceof AmericanQuestion that) {
                ((AmericanQuestion) allQuestions.get(MainMethods.IDExist(questionID, allQuestions))).removeSpecificAnswer(0);
                JOptionPane.showMessageDialog(null, "Answer removed successfully!");
                MainMethods.saveEverythingIntoBinaryFile(allQuestions);

            } else
                JOptionPane.showMessageDialog(null, "ID doesn't exist! Try again!");
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!");
        }
    }

    public void addQuestion(String question) {
        if (question != null && !Objects.equals(question, "")) {
            int questionNum = Integer.parseInt(question) - 1;
            if (questionNum < 0 || questionNum > allQuestions.size() - 1) {
                JOptionPane.showMessageDialog(null, "Wrong input! Try again");
            } else {
                try {
                    for (int i = 0; i < newTest.size(); i++) {
                        if (newTest.get(i).equals(allQuestions.get(questionNum)))
                            throw new IOException();
                    }
                    newTest.add(allQuestions.get(questionNum));
                    JOptionPane.showMessageDialog(null, "Question added successfully, continue.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "You already chose this question! try a different one.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a question's position");
        }
    }

    public void createAutomaticExam(String amountOfQuestions, String lName, String eName, String city, String institution) throws IOException, CloneNotSupportedException, ClassNotFoundException {
        newTest = new ArrayList<>();
        makeQuickAndNonDupesCollection();
        allQuestions = new ArrayList<>();
        allQuestions.addAll(myQuickCollection);
        if (amountOfQuestions == null || Objects.equals(amountOfQuestions, "") || Integer.parseInt(amountOfQuestions) < 1 || Integer.parseInt(amountOfQuestions) > allQuestions.size()) {
            JOptionPane.showMessageDialog(null, "Please enter a number in range of available questions in the Repository.\nDupes don't count.");
        } else {
            for (int i = 0; i < Integer.parseInt(amountOfQuestions); i++) {
                automaticExam(i);
            }
            try {
                MainMethods.selectionSort(newTest);
                executeAndSave(lName, eName, city, institution);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Your full name has to consist a first name and a last name,\nseparated by a spacebar!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Creation failed because no questions were selected!");
            }
            allQuestions = MainMethods.readEverythingFromBinaryFile();
        }
    }

    private void executeAndSave(String lName, String eName, String city, String institution) throws Exception {
        MainMethods.sortByLetters(newTest);
        MainMethods.createTxtFiles(newTest);
        JOptionPane.showMessageDialog(null, "Exam created successfully. Files saved!");

        for (ActionEventsListener l : allListeners) {
            l.hideStage();
            l.moveToScreenViewStage();
        }
    }

    public static void automaticExam(int i, ArrayList< Question > allQuestions, ArrayList< Question > newTest) throws CloneNotSupportedException {
        do {
            int randomQuestion;
            boolean res = true;
            do {
                randomQuestion = (int) (Math.random() * (allQuestions.size()));
                if (newTest.size() == 0)
                    res = false;
                else
                    for (int j = 0; j < newTest.size(); j++) {
                        if (newTest.get(j).equals(allQuestions.get(randomQuestion))) {
                            break;
                        } else if (j == newTest.size() - 1 && !(newTest.get(j).equals(allQuestions.get(randomQuestion)))) {
                            res = false;
                        }
                    }
            } while (res);

            if (allQuestions.get(randomQuestion) instanceof AmericanQuestion that) {
                AmericanQuestion newQuestion = new AmericanQuestion(that.getQuestionText());
                newQuestion.setRightAnswer(that.getRightAnswer());
                newQuestion.setId(that.getId());
                if (that.getMultipleAnswers().size() > 0)
                    for (int j = 0; j <= 3; j++) {
                        boolean bolli = true;
                        do {
                            int rand = (int) (Math.random() * that.getMultipleAnswers().size());
                            String randomString = that.getSpecificAnswer(rand).getText();
                            Answer answer = new Answer(randomString, false);
                            answer.setID(that.getSpecificAnswer(rand).getID());
                            if (newQuestion.getMultipleAnswers().size() == 0) {
                                bolli = false;
                                newQuestion.setSpecificAnswer(answer);
                            } else
                                for (int k = 0; k < newQuestion.getMultipleAnswers().size(); k++) {
                                    if (newQuestion.getMultipleAnswers().get(k).equals(answer)) {
                                        break;
                                    } else if (k == newQuestion.getMultipleAnswers().size() - 1 && !(newQuestion.getMultipleAnswers().get(k).equals(answer))) {
                                        newQuestion.setSpecificAnswer(answer);
                                        if (newQuestion.getSpecificAnswer(j) != null)
                                            if (newQuestion.getSpecificAnswer(j).equals((that.getRightAnswer()))) {
                                                newQuestion.setRightAnswer(newQuestion.getSpecificAnswer((j)));
                                            }
                                        bolli = false;
                                    }
                                }
                        } while (bolli);
                    }
                 //   MainMethods.addAmericanQuestionLastAnswers(allQuestions, newQuestion);
                newTest.add(i, newQuestion);
            } else if (allQuestions.get(randomQuestion) instanceof OpenFieldQuestion) {
                newTest.add(i, allQuestions.get(randomQuestion));
            }
        } while (newTest.get(i) == null);
    }

    public void automaticExam(int i) throws CloneNotSupportedException {
        do {
            int randomQuestion;
            boolean res = true;
            do {
                randomQuestion = (int) (Math.random() * (allQuestions.size()));
                if (newTest.size() == 0)
                    res = false;
                else
                    for (int j = 0; j < newTest.size(); j++) {
                        if (newTest.get(j).equals(allQuestions.get(randomQuestion))) {
                            break;
                        } else if (j == newTest.size() - 1 && !(newTest.get(j).equals(allQuestions.get(randomQuestion)))) {
                            res = false;
                        }
                    }
            } while (res);

            if (allQuestions.get(randomQuestion) instanceof AmericanQuestion that) {
                AmericanQuestion newQuestion = new AmericanQuestion(that.getQuestionText());
                newQuestion.setRightAnswer(that.getRightAnswer());
                newQuestion.setId(that.getId());
                if (that.getMultipleAnswers().size() > 0)
                    for (int j = 0; j <= 3; j++) {
                        boolean bolli = true;
                        do {
                            int rand = (int) (Math.random() * that.getMultipleAnswers().size());
                            String randomString = that.getSpecificAnswer(rand).getText();
                            Answer answer = new Answer(randomString, false);
                            answer.setID(that.getSpecificAnswer(rand).getID());
                            if (newQuestion.getMultipleAnswers().size() == 0) {
                                bolli = false;
                                newQuestion.setSpecificAnswer(answer);
                            } else
                                for (int k = 0; k < newQuestion.getMultipleAnswers().size(); k++) {
                                    if (newQuestion.getMultipleAnswers().get(k).equals(answer)) {
                                        break;
                                    } else if (k == newQuestion.getMultipleAnswers().size() - 1 && !(newQuestion.getMultipleAnswers().get(k).equals(answer))) {
                                        newQuestion.setSpecificAnswer(answer);
                                        if (newQuestion.getSpecificAnswer(j) != null)
                                            if (newQuestion.getSpecificAnswer(j).equals((that.getRightAnswer()))) {
                                                newQuestion.setRightAnswer(newQuestion.getSpecificAnswer((j)));
                                            }
                                        bolli = false;
                                    }
                                }
                        } while (bolli);
                    }
                  //  MainMethods.addAmericanQuestionLastAnswers(allQuestions, newQuestion);
                newTest.add(i, newQuestion);
            } else if (allQuestions.get(randomQuestion) instanceof OpenFieldQuestion) {
                newTest.add(i, allQuestions.get(randomQuestion));
            }
        } while (newTest.get(i) == null);
    }

    public void createManualExam(String lName, String eName, String city, String institution) throws Exception {
        try {
            if (newTest.get(0) == null)
                throw new IndexOutOfBoundsException();
            executeAndSave(lName, eName, city, institution);
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Creation failed because no questions were selected!");
        }
    }

    public void makeSortedCollection() throws CloneNotSupportedException {
        mySortedCollection = new TreeSet<>(Comparator.reverseOrder());
        mySortedCollection.addAll(allQuestions);
        sortByLettersLength(mySortedCollection);
    }

    public void sortByLettersLength(Set< Question > mySet) {
        ArrayList< Question > myList = new ArrayList< Question >(mySet);
        myList.sort(new MyComparator());
        mySortedCollection = new TreeSet<>(Comparator.reverseOrder());
        mySortedCollection.addAll(myList);
    }

    public void makeQuickAndNonDupesCollection() throws CloneNotSupportedException {
        myQuickCollection = new LinkedHashSet<>();
        makeSortedCollection();
        Iterator< Question > itr = mySortedCollection.iterator();
        while (itr.hasNext())
            linkedHashSetComparator(myQuickCollection, itr.next());
    }

    public void linkedHashSetComparator(Set< Question > set, Question q) {

        ArrayList< Question > allQuestions = new ArrayList<>(set);
        for (Question question : allQuestions) {
            if (question.getQuestionText().equalsIgnoreCase(q.getQuestionText()) &&
                    question.getClass().getSimpleName().equalsIgnoreCase(q.getClass().getSimpleName()))
                return;
        }
        set.add(q);
    }

    public Set< Question > getMyCollection() {
        return this.mySortedCollection;
    }

    public Set< Question > getMyQuickCollection() {
        return myQuickCollection;
    }

    public static void clone(ArrayList< Question > allQuestions) throws CloneNotSupportedException, IOException {
        cloneExam(allQuestions);
    }

    private static void cloneExam(ArrayList< Question > allQuestions) throws CloneNotSupportedException, IOException {
        ArrayList< Question > newQuestions = new ArrayList<>();
        for (int i = 0; i < allQuestions.size(); i++) {
            newQuestions.add(i, allQuestions.get(i).clone());
        }
        MainMethods.createTxtFiles(newQuestions);
        JOptionPane.showMessageDialog(null, "Exam was cloned and saved into Files Repository!");

    }

    public void MakeMyArrayList() throws Exception {
        MyArray = new MyArrayList<>();
        Set< Question > temp = myQuickCollection;
        makeQuickAndNonDupesCollection();
        Iterator< Question > itr = myQuickCollection.iterator();
        while (itr.hasNext()) {
            MyArray.add(itr.next());
        }
        myQuickCollection = temp;
    }

    public void showMyArrayList(TextArea txt) throws CloneNotSupportedException {
        printIntoTextArea(txt);
    }

    private void printIntoTextArea(TextArea txt) throws CloneNotSupportedException {
        txt.setWrapText(true);
        StringBuilder sb = new StringBuilder("");
        int k = 1;

        itr = MyArray.iterator();
        if (!itr.hasNext()) txt.setText("");
        while (itr.hasNext()) {
            Question question = itr.next();
            if (question != null)
                sb.append(k++).append(") ").append(question.showAllQuestionAndAnswer());
            txt.setText(String.valueOf(sb));
        }
    }

    public ArrayList< ActionEventsListener > getAllListeners() {
        return allListeners;
    }

    public void setAllListeners(ArrayList< ActionEventsListener > allListeners) {
        this.allListeners = allListeners;
    }

    public Set< Question > getMySortedCollection() {
        return mySortedCollection;
    }

    public void setMySortedCollection(Set< Question > mySortedCollection) {
        this.mySortedCollection = mySortedCollection;
    }

    public void setMyQuickCollection(Set< Question > myQuickCollection) {
        this.myQuickCollection = myQuickCollection;
    }

    public void setMyArray(MyArrayList< Question > myArray) {
        MyArray = myArray;
    }

    public MyArrayList< Question > getMyArray() {
        return MyArray;
    }

    public void resetNewTestArrayList() {
        newTest = new ArrayList<>();
    }
}
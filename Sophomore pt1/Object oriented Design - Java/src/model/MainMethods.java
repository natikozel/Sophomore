package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface MainMethods {

    String EXAM = "exam_" + LocalDate.now() + ".txt";
    String SOLUTION = "solution_" + LocalDate.now() + ".txt";


    static char chooseAmericanAnswer(Scanner s, AmericanQuestion that) throws InputMismatchException {
        char ch = 'a';
        try {
            System.out.println("Choose which one?");
            for (int j = 0; j < that.getMultipleAnswers().size(); j++) {
                if (that.getMultipleAnswers().get(j) != null) {
                    System.out.print("     " + ch++ + ") " + that.getMultipleAnswers().get(j) + "  (" + that.checkAnswer((String) that.getMultipleAnswers().get(j).getText()) + ")\n");
                }
            }
            return s.next().charAt(0);
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException | CloneNotSupportedException e) {
            System.out.println("Wrong Input! You must enter a char!");
            s.next();
            return chooseAmericanAnswer(s, that);
        }
    }

    static void createTxtFiles(ArrayList< Question > allQuestions) throws IOException, CloneNotSupportedException {
        PrintWriter pw = new PrintWriter(EXAM);
        PrintWriter pw2 = new PrintWriter(SOLUTION);
        for (Question allQuestion : allQuestions) {
            pw.println(allQuestion.getQuestionText());
        }
        pw.close();
        for (Question allQuestion : allQuestions) {
            if (allQuestion instanceof AmericanQuestion that)
                pw2.println(that.getMultipleAnswers());
            else if (allQuestion instanceof OpenFieldQuestion that)
                pw2.println(that.getAnswer());
        }
        pw2.close();
        System.out.println("Files saved!");
    }

    static void sortByLetters(ArrayList< Question > allQuestions) throws FileNotFoundException, CloneNotSupportedException {
        for (int i = 0; i < allQuestions.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < allQuestions.size(); j++) {
                if ((allQuestions.get(j).compareLettersLength(allQuestions.get(minIndex)))) {
                    minIndex = j;
                    swap(allQuestions, i, minIndex);
                }
            }
        }
    }

    static void swap(ArrayList< Question > a, int i, int j) {
        Question tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }

    static void selectionSort(ArrayList< Question > allQuestions) {
        int n = allQuestions.size();
        int i, IndMax;
        for (; n > 1; n--) {
            for (IndMax = 0, i = 1; i < n; i++)
                if (allQuestions.get(IndMax).compareTo(allQuestions.get(i)) < 0)
                    IndMax = i;
            swap(allQuestions, n - 1, IndMax);
        }
    }

    static int calculateAmountOfAnswers(ArrayList< Question > allQuestions) throws CloneNotSupportedException {
        int counter = 0;
        for (Question q : allQuestions) {
            if (q instanceof OpenFieldQuestion)
                counter++;
            else if (q instanceof AmericanQuestion that)
                counter += that.getMultipleAnswers().size();
        }
        return counter;
    }

    static void addQuestion(ArrayList< Question > allQuestions, Question newQuestion) throws CloneNotSupportedException {//Method 2
        newQuestion.setId(allQuestions.get(allQuestions.size() - 1).getId() + 1);

        if (newQuestion instanceof OpenFieldQuestion that)
            that.getAnswer().setID(calculateAmountOfAnswers(allQuestions)+10001);
        else if (newQuestion instanceof AmericanQuestion that) {
            for (int i=0; i<that.getMultipleAnswers().size(); i++) {
                that.getMultipleAnswers().get(i).setID(calculateAmountOfAnswers(allQuestions)+(i+10001));
            }
        }

        System.out.println("Question added successfully");
        allQuestions.add(newQuestion);
    }

    static void addAmericanQuestionLastAnswers(ArrayList <Question> allQuestions, AmericanQuestion that) throws CloneNotSupportedException {
        Answer a = new Answer ("All Answers are wrong", false);
        a.setID(calculateAmountOfAnswers(allQuestions)+10001);
        Answer b = new Answer ("More than 1 answer is correct", false);
        b.setID(calculateAmountOfAnswers(allQuestions)+10002);

        that.setSpecificAnswer(a);
        that.setSpecificAnswer(b);
    }

    static int lookForID(Scanner s, ArrayList< Question > allQuestions) throws InputMismatchException {
        System.out.println("Question\"s ID?");
        try {
            int scan = s.nextInt();
            for (int i = 0; i < allQuestions.size(); i++) {
                if (allQuestions.get(i).getId() == scan)
                    return i;
            }
            s.nextLine();
            throw new MyException();
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input! You must enter an actual and existing ID!!!");
            s.next();
            return lookForID(s, allQuestions);
        } catch (MyException e) {
            System.out.println("ID not found, try again!");
            return lookForID(s, allQuestions);
        }
    }

    static int IDExist(String ID, ArrayList< Question > allQuestions) throws InputMismatchException {
        int newID = Integer.parseInt(ID);
        try {
            for (int i = 0; i < allQuestions.size(); i++) {
                if (allQuestions.get(i).getId() == newID)
                    return i;
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
        return -1;
    }

    static void saveEverythingIntoBinaryFile(ArrayList< Question > allQuestions) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("allQuestions.dat"));
        oos.writeObject(allQuestions);
        oos.close();
    }

    static ArrayList< Question > readEverythingFromBinaryFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("allQuestions.dat"));
        ArrayList< Question > allQuestions = (ArrayList< Question >) ois.readObject();
        ois.close();
        return allQuestions;
    }

}
package model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main implements MainMethods {


    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException, InputMismatchException {

        Scanner s = new Scanner(System.in);
        System.out.println("In this object-oriented program we will hereby create an exam either manually or automatically and according to your own desire! \nLet's get started!");
        boolean bol = true;
        ArrayList< Question > allQuestions;
        allQuestions = MainMethods.readEverythingFromBinaryFile();

        do {
            try {
                System.out.println("""
                        1) Show all questions available and their answers.
                        2) Create your own question & answer.
                        3) Update an existing question's text.
                        4) Update an existing question's answer.
                        5) Delete an existing question's answer.
                        6) Create an exam manually.
                        7) Create an exam automatically.
                        8) Clone an existing exam.
                        9) Exit.
                        """);
                int choice = s.nextInt();
                switch (choice) {
                    case 1 -> {
                        int k = 1;
                        for (Question question : allQuestions) {
                            if (question != null)
                                System.out.println((k++ + ") " + question.showAllQuestionAndAnswer()));
                        }
                    }
                    case 2 -> {
                        s.nextLine();
                        Answer answer;
                        String type = null, text = null;
                        boolean boll = true;
                        do {
                            try {
                                System.out.println("American or OpenField?");
                                type = s.nextLine();
                                if (!(type.equalsIgnoreCase("american")) && !(type.equalsIgnoreCase("openfield")))
                                    throw new MyException();
                                System.out.println("What is the question?");
                                text = s.nextLine();
                                for (Question allQuestion : allQuestions) {
                                    if (allQuestion.getQuestionText().equalsIgnoreCase(text) && (allQuestion instanceof AmericanQuestion && type.equalsIgnoreCase("american")))
                                        throw new IOException();
                                    else if (allQuestion.getQuestionText().equalsIgnoreCase(text) && (allQuestion instanceof OpenFieldQuestion && type.equalsIgnoreCase("openfield"))) {
                                        throw new IOException();
                                    }
                                }
                                boll = false;
                            } catch (IOException e) {
                                System.out.println("Question already exist! Try a different text!");
                            } catch (MyException e) {
                                System.out.println("Pick between American or Openfield!");
                            }
                        } while (boll);
                        if (type.equalsIgnoreCase("american")) {
                            System.out.println("What is the correct answer?");
                            String correctAnswer = s.nextLine();
                            boolean indicator = true;
                            int numOfWrongAnswers = 0;
                            do {
                                try {
                                    System.out.println("How many additional wrong answers?");
                                    numOfWrongAnswers = s.nextInt();
                                    if (numOfWrongAnswers > 9 || numOfWrongAnswers < 3)
                                        throw new IOException();
                                    else
                                        indicator = false;
                                } catch (IOException e) {
                                    System.out.println("There can only be 10 answers in total and minimum of 4! Try again");
                                } catch (InputMismatchException e) {
                                    s.next();
                                    System.out.println("Wrong Input! You must enter a positive number, not a string!");
                                }
                            } while (indicator);
                            System.out.println("What are the wrong answers?");
                            s.nextLine();
                            ArrayList< Answer > newList = new ArrayList<>(numOfWrongAnswers + 1);
                            newList.add(0, new Answer(correctAnswer, true));
                            for (int i = 1; i < numOfWrongAnswers + 1; i++) {
                                newList.add(i, new Answer(s.nextLine(), false));
                            }
                            Question newQuestion = new AmericanQuestion(text, newList);
                            MainMethods.addQuestion(allQuestions, newQuestion);
                        } else if (type.equalsIgnoreCase("openfield")) {
                            System.out.println("What is the answer?");
                            answer = new Answer(s.nextLine(), true);
                            Question newQuestion = new OpenFieldQuestion(text, answer);
                            MainMethods.addQuestion(allQuestions, newQuestion);
                        }
                    }
                    case 3 -> {
                        int id = MainMethods.lookForID(s, allQuestions);
                        s.nextLine();
                        System.out.println("Type the new text for this question:");
                        allQuestions.get(id).setQuestionText(s.nextLine());
                        System.out.println("Question updated successfully.");
                    }
                    case 4 -> {
                        int id = MainMethods.lookForID(s, allQuestions);
                        s.nextLine();
                        System.out.println("Type the new answer for this question.");
                        Answer answer = new Answer(s.nextLine(), true);
                        if (allQuestions.get(id) instanceof OpenFieldQuestion) {
                            ((OpenFieldQuestion) allQuestions.get(id)).setAnswer(answer);
                            System.out.println("Answer updated successfully.");
                        } else if (allQuestions.get(id) instanceof AmericanQuestion that) {
                            char specificAnswer = MainMethods.chooseAmericanAnswer(s, that);
                            if (specificAnswer == 'a') {
                                that.setRightAnswer(answer);
                                ((AmericanQuestion) allQuestions.get(id)).setSpecificAnswer(answer);
                            } else {
                                ((AmericanQuestion) allQuestions.get(id)).setSpecificAnswer(answer, (int) specificAnswer - 97);
                            }
                            System.out.println("Answer updated successfully");
                        }
                    }
                    case 5 -> {
                        int id = MainMethods.lookForID(s, allQuestions);
                        if (allQuestions.get(id) instanceof OpenFieldQuestion) {
                            ((OpenFieldQuestion) allQuestions.get(id)).setAnswer(null);
                        } else if (allQuestions.get(id) instanceof AmericanQuestion that) {
                            char ch = MainMethods.chooseAmericanAnswer(s, that);
                            ((AmericanQuestion) allQuestions.get(id)).removeSpecificAnswer((int) ch - 97);
                            System.out.println("Answer removed successfully");
                        }
                    }
                    case 6 -> {
                        boolean indicator = true;
                        ArrayList< Question > newTest = new ArrayList<>();
                        do {
                            try {
                                System.out.println("How many questions would you like the test to have?");
                                int amountOfQuestions = s.nextInt();
                                if (amountOfQuestions > allQuestions.size())
                                    throw new IOException();
                                for (int i = 0; i < amountOfQuestions; i++) {
                                    int k = 1;
                                    for (int j = 0; j < allQuestions.size(); j++) {
                                        Question allQuestion = allQuestions.get(j);
                                        System.out.println((k++ + ") " + allQuestion.showAllQuestionAndAnswer()));
                                    }
                                    System.out.println("Choose a question");
                                    int question = 0;
                                    boolean bolli = true;
                                    do {
                                        try {
                                            if (newTest.size() == 0)
                                                bolli = false;
                                            else
                                                question = s.nextInt() - 1;
                                            for (int p = 0; p < newTest.size(); p++)
                                                if (newTest.get(p).equals(allQuestions.get(question))) {
                                                    throw new IOException();
                                                } else if (p == newTest.size() - 1) {
                                                    bolli = false;
                                                }
                                        } catch (IOException e) {
                                            System.out.println("You already chose this question! try something else.");

                                        }
                                    } while (bolli);
                                    if (newTest.size() == 0)
                                        question = s.nextInt() - 1;
                                    if (allQuestions.get(question) instanceof OpenFieldQuestion) {
                                        newTest.add(i, allQuestions.get(question));
                                    } else if (allQuestions.get(question) instanceof AmericanQuestion) {
                                        newTest.add(i, new AmericanQuestion(allQuestions.get(question).getQuestionText()));
                                        System.out.println("Which answers do you want to take?");
                                        boolean boll = true;
                                        do {
                                            int index = MainMethods.chooseAmericanAnswer(s, (AmericanQuestion) allQuestions.get(question)) - 97;
                                            AmericanQuestion that = (AmericanQuestion) newTest.get(i);
                                            that.addAnswer(((AmericanQuestion) allQuestions.get(question)).getSpecificAnswer(index));
                                            System.out.println("Do you need more answers? (Y/N)");
                                            String ch = s.next();
                                            if (ch.equalsIgnoreCase("N")) {
                                                MainMethods.addAmericanQuestionLastAnswers(allQuestions, that);
                                                that.setRightAnswer(((AmericanQuestion) allQuestions.get(question)).getRightAnswer());
                                                newTest.set(i, that);
                                                boll = false;
                                            }
                                        } while (boll);
                                    }
                                }
                                int k = 1;
                                for (Question question : newTest) {
                                    System.out.println((k++ + ") " + question.showAllQuestionAndAnswer()));
                                }
                                System.out.println("\nThis is your new test.\n");
                                indicator = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Wrong Input! try entering an integer!");
                            } catch (IOException e) {
                                System.out.println("Lower your amount, the number entered is bigger than the entire Question's list length.");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Wrong Input! try entering a char!");
                            }
                        } while (indicator);
                        try {
                            MainMethods.sortByLetters(newTest);
                            MainMethods.createTxtFiles(newTest);
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found");
                        }
                    }
                    case 7 -> {
                        int amountOfQuestions = 0;
                        boolean indicator = true;
                        do {
                            try {
                                System.out.println("How many questions would you like the test to have?");
                                amountOfQuestions = s.nextInt();
                                if (amountOfQuestions > allQuestions.size()) {
                                    throw new IOException();
                                } else {
                                    indicator = false;
                                }
                            } catch (IOException e) {
                                System.out.println("Not enough questions to choose from! pick a lower number please");
                            }
                        } while (indicator);
                        ArrayList< Question > newTest = new ArrayList<>(amountOfQuestions);
                        for (int i = 0; i < amountOfQuestions; i++) {
                            Exam.automaticExam(i, allQuestions, newTest);
                        }
                        System.out.println("This is your new test!\n");
                        int k = 1;
                        MainMethods.selectionSort(newTest);
                        for (Question question : newTest) {
                            if (question != null)
                                System.out.println((k++ + ") " + question.showAllQuestionAndAnswer()));
                        }
                        try {
                            MainMethods.sortByLetters(newTest);
                            MainMethods.createTxtFiles(newTest);
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found");
                        }
                    }
                    case 8 -> {
                        Exam.clone(allQuestions);
                    }
                    case 9 -> {
                        MainMethods.saveEverythingIntoBinaryFile(allQuestions);
                        bol = false;
                    }
                    default -> System.out.println("Option invalid!\nPlease choose again, carefully.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Integer required. Try again\n");
                s.next();
            }
        } while (bol);
    }
}
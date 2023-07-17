package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExamManager implements Serializable  {

    private static int INSTANCE = 10;
    private int ExamID;
    private Lecturer Lecturer;
    private String ExamName;
    private LocalDate date;
    private int NumOfQuestions;
    private ArrayList<Question> allQuestions;

    public ExamManager(Lecturer lecturer, String testName, int numOfQuestions) {
        this.ExamID = ++INSTANCE;
        this.Lecturer = lecturer;
        this.ExamName = testName;
        this.date = LocalDate.now();
        this.NumOfQuestions = numOfQuestions;
        this.allQuestions = new ArrayList<>();
    }

    public ExamManager(Lecturer lecturer, String testName, int numOfQuestions, ArrayList<Question> allQuestions) {
        this.ExamID = ++INSTANCE;
        this.Lecturer = lecturer;
        this.ExamName = testName;
        this.date = LocalDate.now();
        this.NumOfQuestions = numOfQuestions;
        this.allQuestions = allQuestions;
    }

    public ArrayList< Question > getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(ArrayList< Question > allQuestions) {
        this.allQuestions = allQuestions;
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int examID) {
        ExamID = examID;
    }

    public Lecturer getLecturer() {
        return Lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        Lecturer = lecturer;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumOfQuestions() {
        return NumOfQuestions;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        NumOfQuestions = numOfQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamManager)) return false;
        ExamManager that = (ExamManager) o;
        return getExamID() == that.getExamID() && getNumOfQuestions() == that.getNumOfQuestions() && Objects.equals(getLecturer(), that.getLecturer()) && Objects.equals(getExamName(), that.getExamName()) && Objects.equals(getDate(), that.getDate());
    }


    @Override
    public String toString() {
        return "Exam's name: " + ExamName +
                "\nID: " + ExamID +
                "\nMade by: " + Lecturer.getFName() + " " + Lecturer.getLName() +
                "\nWith: " + getNumOfQuestions() +
                "\nTested in: " + getDate();
    }
}

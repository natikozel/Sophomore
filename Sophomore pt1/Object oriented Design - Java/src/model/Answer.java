package model;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable {

    private static int INSTANCE = 10000;
    private int ID;
    private Question question;
    private String text;
    private boolean correct;

    public Answer(String text, boolean correct) {
        this.ID = ++INSTANCE;
        this.text = text;
        this.correct = correct;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getID() == answer.getID() && isCorrect() == answer.isCorrect() && Objects.equals(getText(), answer.getText());
    }

    @Override
    public String toString() {
        return text;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}

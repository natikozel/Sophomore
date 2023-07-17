package model;

import java.io.FileNotFoundException;
import java.io.Serializable;


public class OpenFieldQuestion extends Question implements Serializable {
    private Answer answer;

    public OpenFieldQuestion(String questionText, Answer answer) {
        super(questionText);
        this.answer = answer;
        answer.setQuestion(this);
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public void addAnswer(Answer answer) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpenFieldQuestion that)) return false;
        if (!super.equals(o)) return false;
        return getQuestionText().equals(that.getQuestionText());
    }

    @Override
    public String toString() {
        return super.toString() +
                "Answer =" + this.answer;
    }

    @Override
    public StringBuilder showAllQuestionAndAnswer() {// Method 1
        StringBuilder sb = new StringBuilder();
        sb.append("(ID = ").append(super.getId()).append(") ").append(this.getQuestionText()).append("  (").append(this.getAnswer()).append(")\n");

        return sb;
    }

    @Override
    public boolean compareLettersLength(Question q) throws FileNotFoundException, CloneNotSupportedException {
        int counter=0;

        if (q instanceof OpenFieldQuestion that)
            if (this.getAnswer() == null)
                return 0 < that.getAnswer().getText().chars().count();
            else if (that.getAnswer() == null)
                return (int) this.getAnswer().getText().chars().count() < 0;
                else
            return (int) this.getAnswer().getText().chars().count() < (int) that.getAnswer().getText().chars().count();
        else if (q instanceof AmericanQuestion that)
            for (int i=0; i<that.getMultipleAnswers().size(); i++)
                counter +=(int) ((String)that.getMultipleAnswers().get(i).getText()).chars().count();
        if (this.getAnswer() ==null)
            return 0 < counter;
            return (int) this.getAnswer().getText().chars().count() < counter;
    }
}



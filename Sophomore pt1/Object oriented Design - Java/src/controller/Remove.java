package controller;

import model.Question;

import java.util.Iterator;

public class Remove implements Command {

    private Iterator <Question>  itr;

    public Remove(Iterator<Question> itr) {
        this.itr=itr;
    }

    @Override
    public void execute() {
        this.itr.next();
        this.itr.remove();
    }
}

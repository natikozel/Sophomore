package controller;

import model.Question;
import java.util.Iterator;

public class IteratorController {

    private Iterator< Question > itr;
    private Command cmd;

    public IteratorController (Iterator<Question> itr) {
        this.itr = itr;
    }

    public void removeAndExecute() {
        this.cmd = new Remove(this.itr);
        cmd.execute();
    }
}

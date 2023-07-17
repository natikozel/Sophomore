package model;

import java.util.Comparator;
import java.util.Map;

public class MyComparator implements Comparator< Question > {

    @Override
    public int compare(Question o1, Question o2) { // ALLOW DUPES
        if (o1.getQuestionText().length() < o2.getQuestionText().length()) return 1;
        else if (o1.getQuestionText().length() > o2.getQuestionText().length()) return -1;
        else return 0;
    }
}

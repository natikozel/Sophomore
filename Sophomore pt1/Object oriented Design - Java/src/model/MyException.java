package model;

import java.time.LocalTime;

public class MyException extends Exception {
    private final LocalTime created;

    public MyException(String msg) {
        super(msg);
        this.created = LocalTime.now();
    }

    public MyException() {
        super("My Exception");
        this.created = LocalTime.now();
    }

    public String getMessage() {
        return created + " " + super.getMessage();
    }

}

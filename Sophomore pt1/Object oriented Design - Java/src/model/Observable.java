package model;

import listeners.Observer;

public interface Observable {

    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

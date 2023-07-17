package model;

import listeners.Observer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MyArrayList< T > implements Observable, Iterable< T > {

    public static int MAX = 2;
    private T[] MyArray;
    private int count;
    private Set< Observer > observers = new LinkedHashSet<>();

    public MyArrayList() {
        MyArray = (T[]) new Object[ MAX ];
        this.count = 0;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.update();
    }

    public void add(T value) {
        if (count == MAX) {
            MAX *= 2;
            MyArray = Arrays.copyOf(MyArray, MAX);
        }
        MyArray[ count++ ] = value;
    }

    public Iterator< T > iterator() {
        notifyObservers();
        return new myIterator();
    }


    private class myIterator implements Iterator< T > {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new IllegalArgumentException("ARRAY IS FULL");
            return MyArray[ current++ ];
        }

        @Override
        public void remove() {
            MyArray[ current ] = null;
            for (int i = current; i < count; i++) {
                MyArray[ i ] = MyArray[ i + 1 ];
            }
            count--;
            current--;
        }
    }

    public int getCount() {
        return count;
    }


  /*  private class myIterator implements Iterator<T> {

        private int current = -1;

        @Override
        public boolean hasNext() {
            return current < count -1;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new IllegalArgumentException("ARRAY IS FULL");
            return MyArray[ ++current ];
        }

        @Override
        public void remove() {
            MyArray[current] = null;
            for (int i=current; i<count; i++) {
                MyArray[i] = MyArray[i+1];
            }
            count--;
        }
    }*/

    public class Memento {
        private T[] arr;

        private Memento(T[] arr) {
            this.arr = arr;
        }

        private T[] getMemento() {
            return this.arr;
        }
    }

    public Memento createMemento() {
        return new Memento(Arrays.copyOf(MyArray, MyArray.length));
    }


    public void setMemento(Memento m) {
        this.MyArray = m.getMemento();
        count = MyArray.length;
    }
}

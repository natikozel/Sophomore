package model;

import java.io.Serializable;
import java.util.*;

public class MySet< E > implements Set< E >, Serializable {
    private final ArrayList< E > theSet;

    public MySet() {
        this.theSet = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.theSet.size();
    }

    @Override
    public boolean isEmpty() {
        return this.theSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.theSet.contains(o);
    }

    @Override
    public Iterator< E > iterator() {
        return this.theSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.theSet.toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public < T > T[] toArray(T[] a) {
        return (T[]) this.theSet.toArray((Object[]) a);
    }

    @Override
    public boolean add(E e) {
        if (!this.contains(e)) {
            this.theSet.add(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        this.theSet.remove(o);
        return true;
    }
    public E remove(int o) {
        return this.theSet.remove(o);

    }

    @Override
    public boolean containsAll(Collection< ? > c) {
        return this.theSet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection< ? extends E > c) {
        boolean changed = false;
        for (E e : c) {
            if (this.add(e) && !changed) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection< ? > c) {
        return this.theSet.removeIf(e -> !c.contains(e));
    }

    @Override
    public boolean removeAll(Collection< ? > c) {
        return this.theSet.removeIf(c::contains);
    }

    @Override
    public void clear() {
        this.theSet.clear();

    }
    public void add(int index, E element) {
        this.theSet.add(index, element);

    }
    public E get(int index) {
        return this.theSet.get(index);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.theSet.clone();
    }

    @Override
    public String toString() {
        return this.theSet.toString();
    }

    public E set(E answer, int i) {
        return this.theSet.set(i, answer);
    }
}
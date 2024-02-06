package co.edu.uptc.persistence;

import java.util.ArrayList;

public class InMemoryPersistence<T> {

    public ArrayList<T> data = new ArrayList<T>();

    public InMemoryPersistence() {
    }

    public InMemoryPersistence(ArrayList<T> data) {
        this.data = data;
    }

}

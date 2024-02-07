package co.edu.uptc.persistence;

import java.util.ArrayList;

public class InMemoryPersistence<T> implements Persistence<T>{

    public ArrayList<T> data = new ArrayList<T>();

    public InMemoryPersistence() {
    }

    public InMemoryPersistence(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public boolean persist(T value) {
        return false;
    }

    @Override
    public boolean erase(int id) {
        return false;
    }

    @Override
    public T obtainById(int id) {
        return null;
    }

    @Override
    public ArrayList<T> obtainAll() {
        return new ArrayList<>();
    }
}

package co.edu.uptc.persistence;

import java.util.ArrayList;

import co.edu.uptc.model.Entity;

public class InMemoryPersistence<T extends Entity> implements Persistence<T> {

    public ArrayList<T> data = new ArrayList<T>();

    public InMemoryPersistence() {
    }

    public InMemoryPersistence(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public boolean persist(T value) {
        return data.add(value);

    }

    @Override
    public boolean erase(int id) {
        T t = obtainById(id);
        if (t != null) {
            data.remove(t);
            return true;
        }
        return false;
    }

    @Override
    public T obtainById(int id) {

        for (T obj : data) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public ArrayList<T> obtainAll() {
        return new ArrayList<>(data);
    }

    @Override
    public boolean persist(int index, T saveData) {
        T t = data.set(index, saveData);
        if (t != null) {
            return true;
        } else {
            return false;
        }
    }

}

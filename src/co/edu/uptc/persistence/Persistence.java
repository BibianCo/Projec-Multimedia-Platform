package co.edu.uptc.persistence;

import java.util.ArrayList;

public interface Persistence<T> {

    public boolean persist(T value);

    public boolean persist(int index, T saveData);

    public boolean erase(int id);

    public T obtainById(int id);

    public ArrayList<T> obtainAll();

}

package co.edu.uptc.persistence;

import java.util.ArrayList;

public interface Persistence<T> {

    public boolean persist(T value);

    public boolean erase(int id);

    public T obtaninById(int id);

    public ArrayList<T> obtainByld();

}

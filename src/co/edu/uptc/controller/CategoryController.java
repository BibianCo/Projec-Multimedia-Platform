package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Category;
import co.edu.uptc.persistence.Persistence;

public class CategoryController {
    private Persistence<Category> persistence;

    public CategoryController() {
    }

    public CategoryController(Persistence<Category> persistence) {
        this.persistence = persistence;
    }

    public boolean add(Category category) {
        return persistence.persist(category);
    }

    public boolean delete(int id) {
        return persistence.erase(id);
    }

    public Category get(int id) {
        return this.persistence.obtainById(id);
    }

    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();
        return categories;
    }

    public Persistence getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence persistence) {
        this.persistence = persistence;
    }

}

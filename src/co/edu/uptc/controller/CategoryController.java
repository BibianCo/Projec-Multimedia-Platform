package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Category;
import co.edu.uptc.persistence.Persistence;

public class CategoryController {
    private Persistence persistence;

    public CategoryController() {
    }

    public CategoryController(Persistence persistence) {
        this.persistence = persistence;
    }

    public boolean add(Category category) {
        return persistence.persist(category);
    }

    public boolean delete(int id) {
        return true;
    }

    public Category get(int id) {
        return new Category(id, null);
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

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
        if (get(category.getId()) == null) {
            return persistence.persist(category);
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return this.persistence.erase(id);
    }

    public Category get(int id) {
        return this.persistence.obtainById(id);
    }

    public boolean update(int id, Category newCategory) {
        Category currentCategory = get(id);

        if (currentCategory != null) {
            int index = getAll().indexOf(currentCategory);
            return this.persistence.persist(index, newCategory);
        } else {
            return false;
        }

    }

    public ArrayList<Category> getAll() {
        return persistence.obtainAll();
    }

    public Persistence<Category> getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence<Category> persistence) {
        this.persistence = persistence;
    }

}

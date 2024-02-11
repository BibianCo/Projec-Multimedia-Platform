package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.Persistence;

public class SerieController {

    private Persistence<Serie> persistence;
    private CategoryController categoryController;

    public SerieController() {
    }

    public SerieController(Persistence<Serie> persistence, CategoryController categoryController) {
        this.persistence = persistence;
        this.categoryController = categoryController;
    }

    public boolean categoriesExists(ArrayList<Category> categories) {

        for (Category category : categories) {
            if (categoryController.get(category.getId()) == null) {
                return false;
            }
        }
        return true;
    }

    public boolean add(Serie serie) {
        if (categoriesExists(serie.getCategories())) {
            return this.persistence.persist(serie);
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return this.persistence.erase(id);
    }

    public Serie get(int id) {
        return this.persistence.obtainById(id);
    }

    public boolean update(int id, Serie newSerie) {
        Serie currentSerie = get(id);
        if (currentSerie != null && categoriesExists(newSerie.getCategories())) {
            int index = getAll().indexOf(currentSerie);
            return this.persistence.persist(index, newSerie);

        } else {
            return false;
        }
    }

    public ArrayList<Serie> getAll() {
        ArrayList<Serie> series = new ArrayList<>();
        series = this.persistence.obtainAll();
        return series;
    }

    public Persistence<Serie> getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence<Serie> persistence) {
        this.persistence = persistence;
    }
}

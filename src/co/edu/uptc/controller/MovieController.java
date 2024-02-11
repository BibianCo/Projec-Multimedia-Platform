package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.persistence.Persistence;

public class MovieController {

    private Persistence<Movie> persistence;
    private CategoryController categoryController;

    public MovieController(CategoryController categoryController) {
    }

    public MovieController(Persistence<Movie> persistence) {
        this.persistence = persistence;
    }

    public boolean categoriesExists(ArrayList<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == this.categoryController.getAll().get(i).getId()) {
                return true;
            }
        }
        return false;

    }

    public boolean add(Movie movie) {
        if (categoriesExists(movie.getCategories())) {
            return this.persistence.persist(movie);
        } else {
            return false;
        }

    }

}

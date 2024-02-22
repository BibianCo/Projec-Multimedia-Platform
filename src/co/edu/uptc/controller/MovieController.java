package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.persistence.Persistence;

public class MovieController {

    private Persistence<Movie> persistence;
    private CategoryController categoryController;

    public MovieController() {
    }

    public MovieController(Persistence<Movie> persistence, CategoryController categoryController) {
        this.persistence = persistence;
        this.categoryController = categoryController;
    }

    public boolean categoriesExists(ArrayList<Category> categories) {

        if (categories.isEmpty()) {
            return false;
        } else {
            for (Category category : categories) {
                if (categoryController.get(category.getId()) == null) {
                    return false;
                }
            }
            return true;
        }

    }

    public boolean add(Movie movie) {
        if (categoriesExists(movie.getCategories())) {
            return this.persistence.persist(movie);
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return this.persistence.erase(id);
    }

    public Movie get(int id) {
        return this.persistence.obtainById(id);
    }

    public ArrayList<Movie> getAll() {
        return this.persistence.obtainAll();
    }

    public boolean update(int id, Movie newMovie) {
        Movie currentMovie = get(id);
        if (currentMovie != null && categoriesExists(newMovie.getCategories())) {
            int index = getAll().indexOf(currentMovie);
            return this.persistence.persist(index, newMovie);

        } else {
            return false;
        }
    }

    public ArrayList<Movie> groupByCategory(int idCategory) {
        ArrayList<Movie> allMovie = getAll();
        Category category = categoryController.get(idCategory);
        ArrayList<Movie> gbcm = new ArrayList<>();

        if (!allMovie.isEmpty() && category != null) {
            for (Movie movie : allMovie) {
                ArrayList<Category> cat = movie.getCategories();
                for (Category categoryFind : cat) {
                    if (category.getId() == categoryFind.getId()) {
                        gbcm.add(movie);
                        break;
                    } else {
                        return null;
                    }
                }

            }
            return gbcm;
        } else {
            return null;
        }

    }

}

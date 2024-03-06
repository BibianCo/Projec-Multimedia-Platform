package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Season;
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

    public boolean serieExists(Serie serie) {
        if (serie == null) {
            return false;
        } else if (get(serie.getId()) == null) {
            return false;
        }

        return true;
    }

    public boolean add(Serie serie) {
        if (categoriesExists(serie.getCategories()) && serieExists(serie) == false) {
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
        if (currentSerie != null) {
            int index = 0;
            for (Serie serie : getAll()) {
                if (serie.getId() == id) {
                    return this.persistence.persist(index, newSerie);
                }
                index++;
            }

        } else {
            return false;
        }
        return false;
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

    public ArrayList<Serie> groupByCategory(int idCategory) {
        ArrayList<Serie> allSeries = getAll();
        Category category = categoryController.get(idCategory);
        ArrayList<Serie> gbcs = new ArrayList<>();

        if (!allSeries.isEmpty() && category != null) {
            for (Serie serie : allSeries) {
                ArrayList<Category> cat = serie.getCategories();
                for (Category categoryFind : cat) {
                    if (category.getId() == categoryFind.getId()) {
                        gbcs.add(serie);
                        break;
                    } else {
                        return null;
                    }
                }
            }
            return gbcs;
        } else {
            return null;
        }

    }

    public boolean setSeason(Serie serie, Season season) {
        if (serie != null && season != null) {
            // serie.setSeasons(season);
            return true;
        }
        return false;

    }
}

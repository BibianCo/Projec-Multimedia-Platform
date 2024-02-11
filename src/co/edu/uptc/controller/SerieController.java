package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.Persistence;

public class SerieController {

    private Persistence<Serie> persistence;

    public SerieController() {
    }

    public SerieController(Persistence<Serie> persistence) {
        this.persistence = persistence;
    }

    public boolean add(Serie serie) {
        return this.persistence.persist(serie);
    }

    public boolean delete(int id) {
        return this.persistence.erase(id);
    }

    public Serie get(int id) {
        return this.persistence.obtainById(id);
    }

    public Serie update(int option, int serieId, String title, String synopsis, ArrayList<Category> categories,
            ArrayList<Season> seasons) {

        Serie serie = get(serieId);
        switch (option) {
            case 1:
                serie.setTitle(title);
                break;
            case 2:
                serie.setSynopsis(synopsis);
                break;
            case 3:
                serie.setCategories(categories);
                break;
            case 4:
                serie.setSeasons(seasons);
                break;
            default:
                break;
        }
        return serie;
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


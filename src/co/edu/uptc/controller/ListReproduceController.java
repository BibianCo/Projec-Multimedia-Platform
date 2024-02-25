package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.Persistence;

public class ListReproduceController {
    private Persistence<Multimedia> persistence;
    private MovieController movieController;
    private SerieController serieController;

    public ListReproduceController() {
    }

    public ListReproduceController(Persistence<Multimedia> persistence, MovieController movieController,
            SerieController serieController) {
        this.persistence = persistence;
        this.movieController = movieController;
        this.serieController = serieController;
    }

    public boolean add(Multimedia multimedia) {
        if (multimedia != null && get(multimedia.getId()) == null) {
            if (multimedia instanceof Movie && movieController.movieExists((Movie) multimedia)) {
                return persistence.persist(multimedia);
            } else if (multimedia instanceof Serie && serieController.serieExists((Serie) multimedia)) {
                return persistence.persist(multimedia);
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return persistence.erase(id);
    }

    public Multimedia get(int id) {
        return persistence.obtainById(id);
    }

    public ArrayList<Multimedia> getAll() {
        return persistence.obtainAll();
    }

    public boolean update(int id, Multimedia newsubscription) {
        Multimedia currentMultimedia = get(id);
        if (currentMultimedia != null && newsubscription != null) {
            int index = getAll().indexOf(currentMultimedia);
            return this.persistence.persist(index, newsubscription);
        } else {
            return false;
        }
    }

}

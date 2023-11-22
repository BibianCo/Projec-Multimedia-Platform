package co.edu.uptc.controller;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class AdministratorController {
    MultimediaGallery multimedia = new MultimediaGallery();

    public boolean addSerie(String title, String description, String category, LocalDate publication) {
        if (!title.isEmpty() && !description.isEmpty() && !category.isEmpty()) {
            multimedia.setSeries(new Serie(title, description, category, publication, false));
            return true;
        }
        return false;
    }

    public boolean addMovie(String title, String description, String category, LocalDate publication, int duration) {

        multimedia.setMovies(new Movie(title, description, category, publication, false));

        if (multimedia.getMovies() != null) {
            return true;
        } else {
            return false;
        }

    }

    public Movie findMovie(String title) {
        ArrayList<Movie> movies = multimedia.getMovies();

        for (int i = 0; i < movies.size(); i++)

            if (title.equals(movies.get(i).getTitle())) {

                return movies.get(i);
            }
        return null;
    }

    public Movie upDate(String title, String description, String category, LocalDate publication, int diration) {
        Movie findMovie = findMovie(title);

        if (findMovie != null) {
            findMovie.setTitle(title);
            findMovie.setDescription(description);
            findMovie.setCategory(category);
            findMovie.setPublication(publication);
            findMovie.setDuration(diration);

            return findMovie;
        } else {
            return null;
        }

    }

    public boolean deleteMovie(String title) {
        Movie m1 = findMovie(title);

        if (m1 != null) {
            multimedia.getMovies().remove(m1);
            return true;
        }
        return false;
    }

    public Serie UpdateSerie(String titleSerie, String description, String category,
            LocalDate publication) {
        Serie serie = findSerie(titleSerie);
        if (serie != null) {
            if (serie.getTitle().equals(titleSerie)) {

                serie.setCategory(category);
                serie.setDescription(description);
                serie.setPublication(publication);

                return serie;
            }
        }

        return null;
    }

    public Serie findSerie(String title) {
        if (!title.isEmpty()) {// title.equals("");
            for (Serie serie : multimedia.getSeries()) {
                if (serie.getTitle().equals(title)) {
                    return serie;
                }
            }
        }
        return null;
    }

    public Serie deleteSerie(String title) {

        if (title != null) {
            Serie serie = findSerie(title);
            multimedia.getSeries().remove(findSerie(title));
            return serie;
        }
        return null;
    }

    public ArrayList<Movie> showMovies() {
        return multimedia.getMovies();
    }

}

package co.edu.uptc.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;

public class AdministratorController {

    MultimediaGallery multimediaGallery = new MultimediaGallery();

    public boolean addMovie(String title, String description, String category, LocalDate publication, int duration) {

        multimediaGallery.setMovies(new Movie(title, description, category, publication, false, duration));

        if (multimediaGallery.getMovies() != null) {
            return true;
        } else {
            return false;
        }

    }

    public Movie findMovie(String title) {
        ArrayList<Movie> movies = multimediaGallery.getMovies();

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

}
package co.edu.uptc.controller;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class UserController {

    MultimediaGallery multimediaGallery;
    User user;
    Serie serie;

    public boolean addWishList(String title) {
        for (Movie movie : multimediaGallery.getMovies()) {
            if (movie.getTitle().equals(title)) {
                user.addWishList(movie);
                return true;
            }
        }

        for (Serie serie : multimediaGallery.getSeries()) {
            if (serie.getTitle().equals(title)) {
                user.addWishList(serie);
            }
        }
        return false;
    }
}

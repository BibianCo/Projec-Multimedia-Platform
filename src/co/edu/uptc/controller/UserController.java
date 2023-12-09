package co.edu.uptc.controller;

import java.time.LocalDate;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class UserController {

    private User user;
    private AdministratorController administratorController;

    public UserController(AdministratorController administratorController) {
        this.administratorController = administratorController;
        user = new User(null, null, null, null, null);
    }

    public boolean addWishList(String title) {

        Movie movie = administratorController.findMovie(title);
        if (administratorController.findMovie(title) != null) {
            user.addWishList(movie);
            return true;
        }

        Serie serie = administratorController.findSerie(title);
        if (administratorController.findSerie(title) != null) {
            user.addWishList(serie);
            return true;
        }
        return false;
    }

    public String showWishList() {
        return user.getWishList().toString();
    }
}

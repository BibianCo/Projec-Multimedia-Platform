package co.edu.uptc.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class AdministratorController {
    //MultimediaGallery multimediaGallery = new MultimediaGallery();
    private ArrayList<User> userList;
    private Administrator administrator;
    private MultimediaGalleryController mgc = new MultimediaGalleryController();

    public AdministratorController() {
        userList = new ArrayList<User>();
        administrator = new Administrator("admin1", "admin1@uptc.edu.co", "2244");

    }

    public ArrayList<User> showUserList() {
        return userList;
    }

    public boolean updateAdministratorDetails(int option, String newDetails) {
        switch (option) {
            case 1:
                administrator.setFirstName(newDetails);
                return true;
            case 2:
                // emailValidation();
                administrator.setEmail(newDetails);
                return true;
            case 3:
                // passwordValidation
                administrator.setPassword(newDetails);
                return true;
            default:
                return false;

        }
    }

    public boolean addSerie(String title, String description, String category, LocalDate publication) {
        if (!title.isEmpty() && !description.isEmpty() && !category.isEmpty()) {
            mgc.multimedia.setSeries(mgc.GenerateKey(true), new Serie(title, description, category, publication, false));
            return true;
        }
        return false;
    }

    public boolean addMovie(String title, String description, String category, LocalDate publication, int duration) {
        Movie m1 = new Movie(title, description, category, publication, false);
        if (m1 != null) {
            multimediaGallery.setMovies(m1);
            return true;
        }
        return false;
    }

    public Movie findMovie(String title) {
        ArrayList<Movie> movies = multimediaGallery.getMovies();

        for (int i = 0; i < movies.size(); i++)

            if (title.equals(movies.get(i).getTitle())) {

                return movies.get(i);
            }
        return null;
    }

    public Movie updateMovie(String title, String description, String category, LocalDate publication, int diration) {
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
            multimediaGallery.getMovies().remove(m1);
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
            for (Serie serie : multimediaGallery.getSeries()) {
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
            multimediaGallery.getSeries().remove(findSerie(title));
            return serie;
        }
        return null;
    }

    public ArrayList<Movie> showMovies() {
        return multimediaGallery.getMovies();
    }

}

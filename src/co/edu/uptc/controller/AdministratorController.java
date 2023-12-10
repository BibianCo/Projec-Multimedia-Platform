package co.edu.uptc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class AdministratorController {
    // MultimediaGallery multimediaGallery = new MultimediaGallery();
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

    public boolean validateAdminCredentials(String adminName, String adminEmail, String adminPassword) {
        return administrator.getFirstName().equals(adminName) &&
                administrator.getEmail().equals(adminEmail) &&
                administrator.getPassword().equals(adminPassword);
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
            mgc.multimedia.setSeries(mgc.GenerateKey(true),
                    new Serie(title, description, category, publication, false));
            return true;
        }
        return false;
    }

    public boolean addMovie(String title, String description, String category, LocalDate publication, int duration) {
        Movie m1 = new Movie(title, description, category, publication, false);
        if (m1 != null) {
            mgc.multimedia.setMovies(mgc.GenerateKey(false), m1);
            return true;
        }
        return false;
    }

    public Movie findMovie(String title) {
        HashMap<Integer, Movie> movies = mgc.multimedia.getMovies();

        for (Integer movieId : movies.keySet()) {
            Movie movie = movies.get(movieId);

            if (title.equals(movie.getTitle())) {
                // se llama el metodo de showMonie para imprimir la pelicula encontrada
                return movie;
            }
        }
        return null;
    }

    public Movie updateMovie(int options, String title, String dataUpdate, LocalDate publication) {
        Movie movie = findMovie(title);
        if (movie != null) {
            switch (options) {
                case 1:
                    movie.setTitle(title);
                    break;
                case 2:
                    movie.setDescription(dataUpdate);
                    break;
                case 3:
                    movie.setCategory(dataUpdate);
                    break;
                case 4:
                    movie.setPublication(publication);
                    break;

                default:
                    break;
            }
            return movie;
        }
        return null;

    }

    public boolean deleteMovie(String title) {
        Movie mov = findMovie(title);

        if (mov != null) {
            mgc.multimedia.getMovies().remove(mov);
            return true;
        }
        return false;
    }

    public Serie updateSerie(int option, String titleSerie, String newD, LocalDate newPublication, int newSeason) {
        Serie serie = findSerie(titleSerie);

        if (!titleSerie.isEmpty()) {
            switch (option) {
                case 1:
                    serie.setDescription(newD);
                    return serie;
                case 2:
                    serie.setCategory(newD);
                    return serie;
                case 3:
                    serie.setPublication(newPublication);
                    return serie;
                case 4:
                    serie.setNumberSeasons(newSeason);

                    return serie;
                default:
                    return null;
            }
        }
        return null;
    }

    public Serie findSerie(String title) {
        if (!title.isEmpty()) {// title.equals("");
            for (Serie serie : mgc.multimedia.getSeries().values()) {
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
            mgc.multimedia.getSeries().remove(findSerie(title));
            return serie;
        }
        return null;
    }

    public HashMap<Integer, Movie> showMovie() {
        return mgc.multimedia.getMovies();
    }

    public HashMap<Integer, Serie> showSeries() {
        return mgc.multimedia.getSeries();
    }

}

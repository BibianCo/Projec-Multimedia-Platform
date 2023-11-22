package co.edu.uptc.controller;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;

import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class AdministratorController {
    MultimediaGallery multimedia = new MultimediaGallery();
    private ArrayList<User> userList;
    private Administrator administrator;

    public AdministratorController() {
        userList = new ArrayList<User>();
        administrator = new Administrator("admin1", "admin1@uptc.edu.co", "2244");
    }

    public ArrayList <User> showUserList() {
        return userList;
    }

    public boolean updateAdministratorDetails(int option, String newDetails) {
        switch (option) {
            case 1:
                administrator.setFirstName(newDetails);
                return true;
            case 2:
                //emailValidation();
                administrator.setEmail(newDetails);
                return true;
            case 3:
                //passwordValidation
                administrator.setPassword(newDetails);
                return true;
            default:
                return false;

        }
    }


    public boolean emailValidation(String email) {

        ArrayList<String> listDominio = new ArrayList<>();
        listDominio.add("@gmail.com");
        listDominio.add("@uptc.edu.co");
        listDominio.add("@outlook.es");
        listDominio.add("@yahoo.com");
        
        for (String s : listDominio) {
            if (email.contains(s)) {
                int position = email.length() - s.length();
                String aux = email.substring(0, position);

                if (aux.contains("@") || aux.length() < 5) {
                    return false;
                } else {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean passwordValidation(String password) {

        if (password.length() > 3 && password.length() < 20) { // >3 <20
            if (!password.equals(password.toLowerCase())) { // min. una mayuscula
                if (!password.equals(password.toUpperCase())) { // min. una miniscula
                    if (password.matches(".*\\d.*\\d.*")) { // min. 2 numeros
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
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

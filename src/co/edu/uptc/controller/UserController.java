package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.User;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class UserController {
    private User user;
    private AdministratorController administratorController;
    private ArrayList<Multimedia> playMultimedias = new ArrayList<>();

    public boolean addListHistory(Multimedia multimedia) {
        if (multimedia.isReproduce()) {
            playMultimedias.add(multimedia);
            user.setPlaylist(playMultimedias);
            return true;
        }
        return false;
    }

    public ArrayList<Multimedia> showListHistory() {
        return user.getPlaylist();
    }

    ArrayList<User> users = new ArrayList<>();
    // comentareado momentaneamente

    /*
     * public boolean addUser(String name, String email, String password, String
     * userName, Plan plan) {
     * User user = new User(name, email, password, userName, plan);
     * if (user.getFirstName().isEmpty() && user.getEmail().isEmpty() &&
     * user.getPassword().isEmpty()
     * && user.getPlan() != null) {
     * users.add(user);
     * administrator.setUsers(users);
     * return true;
     * }
     * return false;
     * }
     */

    public boolean authentication(String email, String password) {

        User user = findUser(email);
        if (user != null) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

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

package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.User;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class UserController {
    private User user = new User();
    private AdministratorController administratorController;
    private ArrayList<Multimedia> playMultimedias = new ArrayList<>();
    private Administrator administrator = new Administrator();

    public UserController() {
    }

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

    public boolean addUser(String name, String email, String password, String userName, Plan plan) {
        User user = new User(name, email, password, userName, plan);
        if (!user.getFirstName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()
                && user.getPlan() != null) {
            users.add(user);
            administrator.setUsers(users);
            return true;
        }
        return false;
    }

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

    public boolean updateUser(String email, String newName, String newEmail, String newPassword) {
        User userToUpdate = findUser(email);
        if (userToUpdate != null) {
            userToUpdate.setFirstName(newName);
            userToUpdate.setEmail(newEmail);
            userToUpdate.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String email) {
        User userToDelete = findUser(email);
        if (userToDelete != null) {

            administrator.getUsers().remove(userToDelete);
            users.remove(userToDelete);
            return true;
        }
        return false;
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

    public boolean deleteWish(String title) {
        for (int i = 0; i < user.getWishList().size(); i++) {
            if (user.getWishList().get(i).getTitle().equals(title)) {
                user.getWishList().remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAll() {
        user.getWishList().clear();
        if (user.getWishList().isEmpty()) {
            return true;
        }
        return false;
    }

    public String showWishList() {
        return user.getWishList().toString();
    }
}

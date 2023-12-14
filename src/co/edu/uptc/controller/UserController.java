package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.User;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class UserController {
    private static User user = User.getInstance();
    private AdministratorController administratorController;
    // private ArrayList<Multimedia> playMultimedias = new ArrayList<>();
    private static Administrator administrator = Administrator.getInstance2();
    // private ArrayList<User> users = new ArrayList<>();

    public UserController() {
        administratorController = new AdministratorController();
    }

    // public boolean addListHistory(Multimedia multimedia, User user) {

    // if (multimedia.isReproduce()) {
    // user.setPlaylist(multimedia);
    // return true;
    // }
    // return false;
    // }

    public ArrayList<Multimedia> showListHistory() {
        return user.getPlaylist();
    }
    // comentareado momentaneamente

    public boolean addUser(String name, String email, String password, String userName, Plan plan) {
        User user = new User(name, email, password, userName, plan);
        if (!user.getFirstName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()
                && user.getPlan() != null) {
            // users.add(user);
            administrator.setUsers(user);
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
        for (User user : administrator.getUsers()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean updateUser(String email, String nameUpdate, Plan plan, int opcion) {
        User userToUpdate = findUser(email);
        if (userToUpdate != null) {
            switch (opcion) {
                case 1:
                    if (nameUpdate != null) {
                        userToUpdate.setFirstName(nameUpdate);
                        return true;
                    }
                    break;
                case 2:
                    if (nameUpdate != null) {
                        userToUpdate.setUserName(nameUpdate);
                        return true;
                    }
                    break;
                case 3:
                    if (nameUpdate != null) {
                        userToUpdate.setEmail(nameUpdate);
                        return true;
                    }
                    break;
                case 4:
                    if (nameUpdate != null) {
                        userToUpdate.setPassword(nameUpdate);
                        return true;
                    }
                    break;
                case 5:
                    userToUpdate.setPlan(plan);
                    return true;
                default:
                    System.out.println("Invalid option");
                    return false;
            }
        }
        // Usuario no encontrado
        return false;
    }

    public boolean deleteUser(String email) {
        User userToDelete = findUser(email);
        if (userToDelete != null) {

            administrator.getUsers().remove(userToDelete);
            administrator.getUsers().remove(userToDelete);
            return true;
        }
        return false;
    }

    public UserController(AdministratorController administratorController) {
        this.administratorController = administratorController;
        user = new User(null, null, null, null, null);
    }

    public boolean addWishList(String title, int option) { // 1. Movie, 2. Serie
        switch (option) {
            case 1:
                Movie movie = administratorController.findMovie(title);
                if (administratorController.findMovie(title) != null) {
                    user.addWishList(movie);
                    return true;
                }
                break;
            case 2:
                Serie serie = administratorController.findSerie(title);
                if (administratorController.findSerie(title) != null) {
                    user.addWishList(serie);
                    return true;
                }
                break;

            default:
                break;
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
        String wishList = "";
        for (int i = 0; i < user.getWishList().size(); i++) {
            wishList = wishList + "\n" + user.getWishList().get(i).getTitle();
        }
        return wishList;
    }
}

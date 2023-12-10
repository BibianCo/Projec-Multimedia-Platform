package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.User;

public class UserController {
    private User user;

    private ArrayList<Multimedia> playMultimedias = new ArrayList<>();
    private Administrator administrator = new Administrator();

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
            multimediaGallery.getUsers().remove(userToDelete);
            return true;
        }
        return false;
    }
}

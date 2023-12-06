package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.User;

public class UserController {
    private User user;
    private Administrator administrator;
    private ArrayList<Multimedia> playMultimedias = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

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

    public boolean addUser(String name, String email, String password, String userName, Plan plan) {
        user = new User(name, email, password, userName, plan);
        if (user != null) {
            users.add(user);
            administrator.setUsers(users);
            return true;
        }
        return false;
    }

    public User findUser(String userName) {
        if (!userName.isEmpty()) {
            for (User user : administrator.getUsers()) {
                if (user.getUserName().equals(userName)) {
                    return user;
                }

            }
        }
        return null;
    }

}

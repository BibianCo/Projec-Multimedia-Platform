package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.User;
import co.edu.uptc.persistence.Persistence;

public class UserController {

    private Persistence<User> persistence;

    public UserController() {
    }

    public UserController(Persistence<User> persistence) {
        this.persistence = persistence;
    }

    public boolean add(User user) {
        return this.persistence.persist(user);
    }

    public boolean delete(int id) {
        return this.persistence.erase(id);
    }

    public User get(int id) {
        return this.persistence.obtainById(id);

    }

    public boolean updateUserById(int userId, User newUser) {

        User currentUser = get(userId);

        if (currentUser != null) {
            int index = getAll().indexOf(currentUser);
            return this.persistence.persist(index, newUser);
        } else {
            return false;
        }

    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        users = this.persistence.obtainAll();
        return users;
    }

    public Persistence getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence persistence) {
        this.persistence = persistence;
    }
}

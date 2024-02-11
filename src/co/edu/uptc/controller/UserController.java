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

    public User updateUserById(int userId, User newUser) {

        User currentUser = get(userId);

        if (currentUser != null) {
            if (currentUser.getId() != newUser.getId()) {
                currentUser.setId(newUser.getId());
            }
            if (!currentUser.getFirstName().equals(newUser.getFirstName())) {
                currentUser.setFirstName(newUser.getFirstName());
            }
            if (!currentUser.getLastName().equals(newUser.getLastName())) {
                currentUser.setLastName(newUser.getLastName());
            }
            if (!currentUser.getEmail().equals(newUser.getEmail())) {
                currentUser.setEmail(newUser.getEmail());
            }
            if (!currentUser.getPassword().equals(newUser.getPassword())) {
                currentUser.setPassword(newUser.getPassword());
            }
            if (currentUser.getRole() != newUser.getRole()) {
                currentUser.setRole(newUser.getRole());
            }

            return currentUser;
        }
        return null;

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

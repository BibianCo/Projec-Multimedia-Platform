package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.Persistence;

public class UserController {

    private Persistence<User> persistence;
    private SubscriptionController subscriptionController;

    public UserController() {
    }

    public UserController(Persistence<User> persistence, SubscriptionController subscriptionController) {
        this.persistence = persistence;
        this.subscriptionController = subscriptionController;
    }

    public boolean add(User user) {
        if (user.getRole().getName().equals("user") && user.getSubscription() != null) {
            return this.persistence.persist(user);
        }
        if (user.getRole().getName().equals("admin")) {
            return this.persistence.persist(user);
        }
        return false;
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
            int index = 0;
            for (User user : getAll()) {
                if (user.getId() == userId) {
                    return this.persistence.persist(index, newUser);
                }
                index++;
            }

        } else {
            return false;
        }
        return false;

    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        users = this.persistence.obtainAll();
        return users;
    }

    public Persistence<User> getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence<User> persistence) {
        this.persistence = persistence;
    }

    public boolean logIn(String email, String password) {
        ArrayList<User> users = getAll();
        for (User user : users) {
            if (user.getSubscription() != null && !subscriptionController.expireSubscription(user.getSubscription())) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean renewSuscription(Subscription newSuscription, int id) {

        User user = get(id);
        if (user != null && user.getSubscription() != null) {
            if (subscriptionController.expireSubscription(user.getSubscription())) {

                subscriptionController.update(user.getSubscription().getId(), newSuscription);
                user.setSubscription(subscriptionController.get(user.getSubscription().getId()));
                return true;
            }

        }
        return false;
    }

}

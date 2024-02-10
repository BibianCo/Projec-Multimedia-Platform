package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Role;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.Persistence;

public class UserController {

    private Persistence<User> persistence;
    private User user;

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

    public User update(int option, int userId, int newId, String newDescirption, Role newRole) {

        User user = get(userId);
        switch (option) {
            case 1:
                user.setId(newId);
                break;
            case 2:
                user.setFirstName(newDescirption);
                break;
            case 3:
                user.setLastName(newDescirption);
                break;
            case 4:
                user.setEmail(newDescirption);
                break;
            case 5:
                user.setPassword(newDescirption);
                break;
            case 6:
                user.setRole(newRole);
                break;
            default:
                break;
        }
        return user;
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

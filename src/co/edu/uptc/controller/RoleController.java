package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Role;
import co.edu.uptc.persistence.Persistence;

public class RoleController {

    private Persistence<Role> persistence;

    public RoleController() {
    }

    public RoleController(Persistence<Role> persistence) {
        this.persistence = persistence;
    }

    public boolean add(Role role) {
        return this.persistence.persist(role);
    }

    public boolean delete(int id) {
        return this.persistence.erase(id);
    }

    public Role get(int id) {
        return this.persistence.obtainById(id);
    }

    public Role update(int option, int roleId, int newId, String name) {

        Role role = get(roleId);
        switch (option) {
            case 1:
                role.setId(newId);
                break;
            case 2:
                role.setName(name);
            default:
                break;
        }
        return role;
    }

    public ArrayList<Role> getAll() {
        ArrayList<Role> roles = new ArrayList<>();
        roles = this.persistence.obtainAll();
        return roles;
    }

    public Persistence<Role> getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence<Role> persistence) {
        this.persistence = persistence;
    }

}

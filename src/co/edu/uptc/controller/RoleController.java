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

    public Role updateRoleById(int roleId, Role newRole) {

        Role currentRole = get(roleId);

        if (currentRole != null && currentRole.getId() == roleId) {
            if (currentRole.getId() != newRole.getId()) {
                currentRole.setId(newRole.getId());
            }
            if (currentRole.getName() != newRole.getName()) {
                currentRole.setName(newRole.getName());
                ;
            }

            return currentRole;
        }

        return null;

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

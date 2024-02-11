package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Role;
import co.edu.uptc.persistence.InMemoryPersistence;

public class RoleControllerTest {

    public InMemoryPersistence<Role> inMemoryPersistence;
    public RoleController roleController;
    private Role admin, user;

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Role>();
        roleController = new RoleController(inMemoryPersistence);

    }

    public void setUp2() {
        admin = new Role(01, "admin");
        user = new Role(02, "user");

        roleController.add(admin);
        roleController.add(user);

    }

    @Test
    public void testAddRole() {
        Role admin3 = new Role(45, "visit");
        assertEquals(true, roleController.add(admin3));
    }

    @Test
    public void testDeleteRole() {
        setUp2();

        assertTrue(roleController.delete(01));
        assertFalse(roleController.delete(01));
        assertEquals(false, roleController.delete(0));
    }

    @Test
    public void testGet() {
        assertEquals(admin, roleController.get(01));
        assertNull(roleController.get(0));
    }

    @Test
    public void testUpdate() {
        setUp2();

        Role newRole = new Role(45, "visit");

        Role role = roleController.updateRoleById(01, newRole);

        assertEquals("visit", role.getName());
        assertEquals(45, role.getId());
        assertEquals(45, admin.getId());
        assertEquals("visit", admin.getName());

    }

    @Test
    public void testGetAll() {
        setUp2();
        assertEquals(admin, roleController.getAll().get(0));
        assertEquals(user, roleController.getAll().get(1));
    }

    @Test
    public void testGetPersistence() {
        assertEquals(inMemoryPersistence, roleController.getPersistence());
    }
}

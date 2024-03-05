package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Role;
import co.edu.uptc.persistence.FilePersistence;

public class RoleControllerTest {

    public FilePersistence<Role> inFilePersistence;
    public RoleController roleController;
    private Role admin, user;

    @Before
    public void setUp() {
        Type type = new TypeToken<ArrayList<Role>>() {
        }.getType();

        inFilePersistence = new FilePersistence<>(type, "role");
        roleController = new RoleController(inFilePersistence);
        // create FIles
        inFilePersistence.createFile();

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

        Role newRole = new Role(0, "visit");

        assertTrue(roleController.updateRoleById(01, newRole));
        assertEquals(false, roleController.updateRoleById(78, newRole));
        assertEquals("visit", roleController.get(0).getName());
        assertNull(roleController.get(01));
    }

    @Test
    public void testGetAll() {
        setUp2();
        assertEquals(admin.getName(), roleController.getAll().get(0).getName());
        assertEquals(user.getId(), roleController.getAll().get(1).getId());
    }

    @Test
    public void testGetPersistence() {
        assertEquals(inFilePersistence, roleController.getPersistence());
    }
}

package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Role;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.InMemoryPersistence;

public class UserControllerTest {

    public UserController userController;
    public InMemoryPersistence<User> inMemoryPersistence;
    private User user1, user2;

    @Before
    public void setUp() {
        this.inMemoryPersistence = new InMemoryPersistence<User>();
        this.userController = new UserController(inMemoryPersistence);

    }

    public void setUp2() {
        user1 = new User(123, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(1, "admin"));

        user2 = new User(1234, "laura", "garcia", "garcia2003@gmail.com", "78956", new Role(2, "admin"));

        userController.add(user1);
        userController.add(user2);
    }

    @Test
    public void testAddUser() {
        User user = new User(123, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(1, "admin"));
        assertTrue(userController.add(user));
    }

    @Test
    public void testDeleteUser() {
        setUp2();
        assertEquals(true, userController.delete(123));
        assertFalse(userController.delete(123));
        assertFalse(userController.delete(5));
    }

    @Test
    public void testGet() {
        setUp2();
        assertEquals(user1, userController.get(123));
        assertNull(userController.get(0));
    }

    @Test
    public void testUpdate() {
        setUp2();
        // 1-change id
        // 2-change first name
        // 3-change last name
        // 4-change email
        // 5-change password
        // 6-change Role

        userController.update(1, 123, 456, null, null);
        assertEquals(user1.getId(), 456);
        assertEquals(user1.getFirstName(), "juan");

        userController.update(2, 456, 0, "david", null);
        assertEquals(user1.getFirstName(), "david");
        assertEquals(user1.getId(), 456);

        userController.update(5, 1234, 0, "123", null);
        assertEquals(user2.getPassword(), "123");
        assertEquals(user2.getId(), 1234);

    }

    @Test
    public void testGetAll() {
        setUp2();
        assertEquals(user1, userController.getAll().get(0));
        assertEquals(user2, userController.getAll().get(1));
    }

    @Test
    public void testGetPersistence() {
        assertEquals(inMemoryPersistence, userController.getPersistence());
    }
}

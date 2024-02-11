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

        Role newRole = new Role(0, "visit");
        User User = new User(45, "liliana", "fernandez", "juferi200", "123", newRole);

        userController.updateUserById(123, User);
        assertEquals("liliana", user1.getFirstName());
        assertEquals(45, user1.getId());
        assertEquals(newRole, user1.getRole());

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

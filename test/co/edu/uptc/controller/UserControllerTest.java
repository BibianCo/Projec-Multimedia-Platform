package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.InMemoryPersistence;

public class UserControllerTest {

    public UserController userController;
    public PlanController planController;
    public Role role;
    public SubscriptionController subscriptionController;
    public InMemoryPersistence<Subscription> ims;
    public InMemoryPersistence<User> inMemoryPersistence;
    public InMemoryPersistence<Plan> imp;

    private User user1, user2, user3, user4;
    private Subscription subs1, subs2;
    private Plan pl1;

    @Before
    public void setUp() {
        this.inMemoryPersistence = new InMemoryPersistence<User>();
        this.ims = new InMemoryPersistence<Subscription>();
        this.imp = new InMemoryPersistence<Plan>();
        this.planController = new PlanController(imp);
        subscriptionController = new SubscriptionController(ims, planController);
        this.userController = new UserController(inMemoryPersistence, subscriptionController);

    }

    public void setUp2() {

        pl1 = new Plan(12354, "gold", "juansd as", 45, 50);
        planController.add(pl1);

        user1 = new User(123, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(1, "admin"));
        user2 = new User(1234, "laura", "garcia", "garcia2003@gmail.com", "7895", new Role(2, "admin"));
        user3 = new User(23, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(8, "user"));
        user4 = new User(10542820, "carlos", "alberto", "carlos@gmail", "asdas53", new Role(5, "user"));

        subs1 = new Subscription(4, new Plan(0, "prem", "30 dias adicionales", 7000, 30), user3);
        subs2 = new Subscription(1, pl1, user3);

        subscriptionController.add(subs1);
        subscriptionController.add(subs2);

        user4.setSubscription(subs1);
        user3.setSubscription(subs2);

        subs2.setDateStart(LocalDate.now());
        subscriptionController.setEndDate(subs2);

        subs1.setDateStart(LocalDate.of(2021, 02, 02));
        subscriptionController.setEndDate(subs1);

        userController.add(user1);
        userController.add(user2);
        userController.add(user3);
        userController.add(user4);

    }

    @Test
    public void testAddUser() {
        User user = new User(26, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(1, "user"));

        Subscription sb = new Subscription(1, new Plan(12, "prem", "30 dias adicionales", 7000, 30), user);
        user.setSubscription(sb);

        User user1 = new User(123, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(1, "admin"));
        User user2 = new User(123, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(1, "admi"));

        assertFalse(userController.add(user2));
        assertEquals(true, userController.add(user));
        assertTrue(userController.add(user1));
        assertEquals(user.getFirstName(), userController.get(26).getFirstName());
    }

    @Test
    public void testDeleteUser() {
        setUp2();
        assertEquals(true, userController.delete(123));
        assertFalse(userController.delete(12346));
        assertFalse(userController.delete(7));
    }

    @Test
    public void testGet() {
        setUp2();
        assertEquals(user1.getFirstName(), userController.get(123).getFirstName());
        assertEquals(user3.getFirstName(), userController.get(23).getFirstName());
        assertNull(userController.get(0));
    }

    @Test
    public void testUpdate() {
        setUp2();

        Role newRole = new Role(0, "visit");
        User User = new User(45, "liliana", "fernandez", "juferi200", "123", newRole);

        assertTrue(userController.updateUserById(123, User));
        assertEquals(false, userController.updateUserById(78, User));
        assertEquals("liliana", userController.get(45).getFirstName());
        assertNull(userController.get(12345));

    }

    @Test
    public void testGetAll() {
        setUp2();
        assertEquals(user1.getPassword(), userController.getAll().get(0).getPassword());
        assertEquals(user2.getEmail(), userController.getAll().get(1).getEmail());
    }

    @Test
    public void testGetPersistence() {
        assertEquals(inMemoryPersistence, userController.getPersistence());
    }

    @Test
    public void testLogin() {
        setUp2();
        assertEquals(false, userController.logIn("juferi2003@gmail.com", "asda"));
        assertEquals(true, userController.logIn("juferi2003@gmail.com", "78956"));
        assertEquals(true, userController.logIn("garcia2003@gmail.com", "7895"));
    }

    @Test
    public void testRenewSuscription() {
        setUp2();

        Subscription newSubscription = new Subscription(12, new Plan(1, "plas", "asd", 12, 30), user3);
        Subscription newSubscription2 = new Subscription(12, new Plan(1, "plas", "asd", 12, 0), user4);

        assertEquals(false, userController.renewSuscription(newSubscription, 23));
        assertEquals(false, userController.renewSuscription(newSubscription2, 10542820));
    }

    @Test
    public void testLoginSub () {
        setUp2();
        Plan plan = new Plan(12354, "gold", "juansd as", 45, 50);
        PlanController planController = new PlanController(new InMemoryPersistence<Plan>());
        planController.add(plan);

        User user1 = new User(123, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(1, "admin"));
        User user2 = new User(1234, "laura", "garcia", "garcia2003@gmail.com", "7895", new Role(2, "admin"));
        User user3 = new User(23, "juan", "fernandez", "juferi2003@gmail.com", "78956", new Role(8, "user"));
        User user4 = new User(10542820, "carlos", "alberto", "carlos@gmail", "asdas53", new Role(5, "user"));

        Subscription subs1 = new Subscription(4, new Plan(0, "prem", "30 dias adicionales", 7000, 30), user3);
        Subscription subs2 = new Subscription(1, plan, user3);

        subscriptionController.add(subs1);
        subscriptionController.add(subs2);

        user4.setSubscription(subs1);
        user3.setSubscription(subs2);

        subs2.setDateStart(LocalDate.now());
        subscriptionController.setEndDate(subs2);

        subs1.setDateStart(LocalDate.of(2021, 02, 02));
        subscriptionController.setEndDate(subs1);

        userController.add(user1);
        userController.add(user2);
        userController.add(user3);
        userController.add(user4);
    }
}


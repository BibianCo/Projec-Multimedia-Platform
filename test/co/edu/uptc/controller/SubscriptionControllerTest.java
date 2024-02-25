package co.edu.uptc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.InMemoryPersistence;

public class SubscriptionControllerTest {

    public static SubscriptionController subscriptionController;
    public static InMemoryPersistence<Subscription> inMemoryPersistence;
    public static Subscription sc1;
    public static Subscription sc2;
    public static Subscription sc3;
    public static Plan plan1;
    public static User user1;

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Subscription>();
        subscriptionController = new SubscriptionController(inMemoryPersistence);

        plan1 = new Plan(1, "Basico", "Disfruta tu plataforma de Multimedia en tu smartphone", 39000, 30);
        user1 = new User(1234, "bibian", "corredor", "bibian@gmail.com", "bibian1234", new Role(1235, "user"));

        sc1 = new Subscription(111, plan1, user1);
        sc2 = new Subscription(222, null, null);
        sc3 = new Subscription(333, plan1, user1);

    }

    @Test
    public void testMethods() {
        addSubscriptionTest();
        deleteSubscriptionTest();
        getSubscriptionTest();
        getAllSubscriptionTest();
        updateSubscriptionTest();
        expireSubscriptionTest();
    }

    public void addSubscriptionTest() {
        assertEquals(false, subscriptionController.add(sc2));
        assertEquals(false, subscriptionController.add(sc2));
        assertEquals(true, subscriptionController.add(sc1));
        assertEquals(LocalDate.now(), subscriptionController.setStartDate(sc1));
        // assertEquals(true, subscriptionController.setEndDate(sc1));
        assertEquals(true, subscriptionController.add(sc3));
    }

    public void deleteSubscriptionTest() {
        assertEquals(false, subscriptionController.delete(123));
        assertEquals(true, subscriptionController.delete(333));
    }

    public void getSubscriptionTest() {
        assertEquals(sc1, subscriptionController.get(111));
        assertEquals(null, subscriptionController.get(333));
        assertEquals(null, subscriptionController.get(123));
    }

    public void getAllSubscriptionTest() {
        ArrayList<Subscription> prueba = new ArrayList<>();
        prueba.add(sc1);
        assertEquals(sc1, subscriptionController.getAll().get(0));
        assertEquals(prueba, subscriptionController.getAll());
        assertEquals(1, subscriptionController.getAll().size());
    }

    public void updateSubscriptionTest() {
        Subscription prueba = new Subscription(253, plan1, user1);
        assertEquals(false, subscriptionController.update(1234, prueba));
        assertEquals(true, subscriptionController.update(111, prueba));
        assertEquals(253, subscriptionController.get(253).getId());
    }

    public void expireSubscriptionTest() {
        assertEquals(false, subscriptionController.expireSubscription(subscriptionController.get(253)));

    }
}

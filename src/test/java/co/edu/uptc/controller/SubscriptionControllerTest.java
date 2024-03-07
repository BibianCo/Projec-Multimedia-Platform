package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.FilePersistence;

public class SubscriptionControllerTest {

    public static SubscriptionController subscriptionController;
    public static FilePersistence<Subscription> inFilePersistence;
    public static PlanController planController;
    public static FilePersistence<Plan> ifpp;
    public static Subscription sc1;
    public static Subscription sc2;
    public static Subscription sc3;
    public static Plan plan1;
    public static User user1;

    @Before
    public void setUp() {

        Type type2 = new TypeToken<ArrayList<Subscription>>() {
        }.getType();
        Type type3 = new TypeToken<ArrayList<Plan>>() {
        }.getType();
        inFilePersistence = new FilePersistence<>(type2, "suscription");
        ifpp = new FilePersistence<>(type3, "plan");
        planController = new PlanController(ifpp);
        subscriptionController = new SubscriptionController(inFilePersistence, planController);

        ifpp.createFile();
        inFilePersistence.createFile();
        plan1 = new Plan(1, "Basico", "Disfruta tu plataforma de Multimedia en tu smartphone", 39000, 30);
        user1 = new User(1234, "bibian", "corredor", "bibian@gmail.com", "bibian1234", new Role(1235, "user"));

        planController.add(plan1);

        sc1 = new Subscription(111, plan1);
        sc2 = new Subscription(222, null);
        sc3 = new Subscription(333, plan1);

    }

    @Test
    public void testMethods() {
        addSubscriptionTest();
        deleteSubscriptionTest();
        getSubscriptionTest();
        getAllSubscriptionTest();
        // updateSubscriptionTest();
        expireSubscriptionTest();
    }

    public void addSubscriptionTest() {
        assertEquals(false, subscriptionController.add(sc2));
        assertEquals(false, subscriptionController.add(sc2));
        assertEquals(true, subscriptionController.add(sc1));
        assertEquals(LocalDate.now(), subscriptionController.setStartDate(sc1));
        assertEquals(true, subscriptionController.add(sc3));
    }

    public void deleteSubscriptionTest() {
        assertEquals(false, subscriptionController.delete(123));
        assertEquals(true, subscriptionController.delete(333));
    }

    public void getSubscriptionTest() {
        assertEquals(sc1.getId(), subscriptionController.get(111).getId());
        assertEquals(null, subscriptionController.get(333));
        assertEquals(null, subscriptionController.get(123));
    }

    public void getAllSubscriptionTest() {
        ArrayList<Subscription> prueba = new ArrayList<>();
        prueba.add(sc1);
        assertEquals(sc1.getDateStart(), subscriptionController.getAll().get(0).getDateStart());
        assertEquals(prueba.get(0).getId(), subscriptionController.getAll().get(0).getId());
        assertEquals(1, subscriptionController.getAll().size());
    }

    public void updateSubscriptionTest() {
        Subscription prueba = new Subscription(253, plan1);
        assertEquals(false, subscriptionController.update(1234, prueba));
        assertEquals(true, subscriptionController.update(111, prueba));
        assertEquals(253, subscriptionController.get(253).getId());
    }

    public void expireSubscriptionTest() {
        assertEquals(false, subscriptionController.expireSubscription(subscriptionController.get(111)));

    }

    @Test
    public void testSimulatePayment() {
        setUp();
        subscriptionController.add(sc1);
        assertEquals(-1, subscriptionController.simulatePayment("4554545454", 45, sc1));
        assertEquals(6000, subscriptionController.simulatePayment("4554545454", 45000, sc1));
        assertEquals(6000, subscriptionController.simulatePayment("4554545454", 45000, sc1));
        assertEquals(-1, subscriptionController.simulatePayment("32", 45000, sc1));
    }
}

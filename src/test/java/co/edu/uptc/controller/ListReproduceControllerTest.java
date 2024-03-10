package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.FilePersistence;

public class ListReproduceControllerTest {
    public static ListReproduceController listReproduceController;
    public static SerieController serieController;
    public static MovieController movieController;
    public static CategoryController categoryController;
    public static UserController userController;
    public static FilePersistence<Movie> impm;
    public static FilePersistence<Serie> imps;
    public static FilePersistence<Category> impc;
    public static FilePersistence<User> impu;
    public static SubscriptionController subscriptionController;
    public static FilePersistence<Subscription> impsb;
    public static PlanController planController;
    public static FilePersistence<Plan> impp;
    public static ArrayList<Category> cat1 = new ArrayList<>();
    public static Movie m1;
    public static Movie m2;
    public static Category c1;
    public static Category c2;
    public static Serie s1;
    public static Serie s2;
    public static User u1;
    public static User u2;
    public static Plan plan;
    public static Subscription sbn;
    public static Type movieType;
    public static Type categoryType;
    public static Type serieType;
    public static Type userType;
    public static Type subscriptionType;
    public static Type planType;

    @Before
    public void setUp() {
        movieType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        categoryType = new TypeToken<ArrayList<Category>>() {
        }.getType();
        serieType = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        userType = new TypeToken<ArrayList<User>>() {
        }.getType();
        subscriptionType = new TypeToken<ArrayList<Subscription>>() {
        }.getType();
        planType = new TypeToken<ArrayList<Plan>>() {
        }.getType();

        impm = new FilePersistence<>(movieType, "movies");
        impc = new FilePersistence<>(categoryType, "categories");
        imps = new FilePersistence<>(serieType, "series");
        impu = new FilePersistence<>(userType, "users");
        impsb = new FilePersistence<>(subscriptionType, "subscription");
        impp = new FilePersistence<>(planType, "plans");
        planController = new PlanController(impp);
        subscriptionController = new SubscriptionController(impsb, planController);
        categoryController = new CategoryController(impc);
        serieController = new SerieController(imps, categoryController);
        movieController = new MovieController(impm, categoryController);
        userController = new UserController(impu, subscriptionController);
        listReproduceController = new ListReproduceController(movieController, serieController,
                userController);

        // crea archivos
        impm.createFile();
        impc.createFile();
        imps.createFile();
        impu.createFile();
        impsb.createFile();
        impp.createFile();

        c1 = new Category(1, "romance");
        c2 = new Category(2, "drama");
        cat1.add(c2);
        cat1.add(c1);

        m1 = new Movie(111, "blanca nieves", "una pelicula animada de Disney", LocalDate.of(2012, 12, 25),
                60, cat1);
        m2 = new Movie(222, "Cenicienta", "una pelicula animada de Disney", LocalDate.of(2010, 05, 07),
                120, cat1);

        s1 = new Serie(1, "Merlina", "serie de netflix", LocalDate.of(2010, 05, 07), cat1, new ArrayList<>());
        s2 = new Serie(2, "Etile", "serie de netflix", LocalDate.of(2019, 04, 05), cat1, new ArrayList<>());

        plan = new Plan(1, "Basico", "Disfruta tu plataforma de Multimedia en tu smartphone", 39000, 30);
        sbn = new Subscription(112, plan);
        u1 = new User(1, "bibian", "Corredor", "bibian@gmail.com", "bsnxsa455", new Role(1, "user"), sbn,
                null);
        sbn = new Subscription(112, plan);

        u2 = new User(2, "Alejandra", "Deaquiz", "Ale@gmail.com", "bsnxsa454", new Role(2, "user"), sbn,
                null);

    }

    @Test
    public void testMethods() {
        assertEquals(true, categoryController.add(c1));
        // assertEquals(true, categoryController.add(c2));
        assertEquals(true, movieController.add(m1));
        assertEquals(true, movieController.add(m2));
        assertEquals(true, serieController.add(s1));
        assertEquals(true, serieController.add(s2));
        assertEquals(true, planController.add(plan));
        assertEquals(true, userController.add(u1));
        assertEquals(true, subscriptionController.add(sbn));
        assertEquals(true, userController.add(u2));
        addListTest();
        deleteListTest();
        getListTest();
        getAllListTest();
    }

    private void addListTest() {
        assertEquals(true, listReproduceController.add(111, 1));
        assertEquals(true, listReproduceController.add(222, 1));
        assertEquals(true, listReproduceController.add(1, 2));

        assertEquals(false, listReproduceController.add(111, 123));

        assertEquals(s1.getId(), userController.get(2).getListPersonalized().get(0).getId());
        assertEquals(true, listReproduceController.add(2, 1));
        assertEquals(false, listReproduceController.add(2, 513));

        assertEquals(3, userController.get(1).getListPersonalized().size());
        assertEquals(1, userController.get(2).getListPersonalized().size());
        assertEquals(true, listReproduceController.add(2, 2));
        assertEquals(2, userController.get(2).getListPersonalized().size());
        assertEquals(s2.getId(), userController.get(2).getListPersonalized().get(1).getId());
        assertEquals(false, listReproduceController.add(123, 2));
        assertEquals(false, listReproduceController.add(1256, 1));
    }

    private void deleteListTest() {
        assertEquals(false, listReproduceController.delete(1265, 212));
        assertEquals(false, listReproduceController.delete(222, 2));
        assertEquals(true, listReproduceController.delete(111, 1));
        assertEquals(false, listReproduceController.delete(111, 1));

    }

    private void getListTest() {
        assertEquals(null, listReproduceController.get(111, 1));
        assertEquals(null, listReproduceController.get(156, 152));
        assertEquals(null, listReproduceController.get(222, 2));
        assertEquals(s1.getId(), listReproduceController.get(1, 2).getId());
        assertEquals(s2.getId(), listReproduceController.get(2, 1).getId());

    }

    private void getAllListTest() {
        assertEquals(2, listReproduceController.getAll(2).size());
        assertEquals(2, listReproduceController.getAll(1).size());

    }

    @After
    public void tearDown() {
        imps.deleteFile();
    }

}

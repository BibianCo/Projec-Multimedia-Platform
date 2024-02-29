package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.InMemoryPersistence;

public class ListReproduceTest {
    public static ListReproduceController listReproduceController;
    public static SerieController serieController;
    public static MovieController movieController;
    public static CategoryController categoryController;
    public static UserController userController;
    public static InMemoryPersistence<Movie> impm;
    public static InMemoryPersistence<Serie> imps;
    public static InMemoryPersistence<Category> impc;
    public static InMemoryPersistence<User> impu;
    public static SubscriptionController subscriptionController;
    public static InMemoryPersistence<Subscription> impsb;
    public static PlanController planController;
    public static InMemoryPersistence<Plan> impp;
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

    @Before
    public void setUp() {
        impm = new InMemoryPersistence<Movie>();
        impc = new InMemoryPersistence<Category>();
        imps = new InMemoryPersistence<Serie>();
        impu = new InMemoryPersistence<User>();
        impsb = new InMemoryPersistence<Subscription>();
        impp = new InMemoryPersistence<Plan>();
        planController = new PlanController(impp);
        subscriptionController = new SubscriptionController(impsb, planController);
        categoryController = new CategoryController(impc);
        serieController = new SerieController(imps, categoryController);
        movieController = new MovieController(impm, categoryController);
        userController = new UserController(impu);
        listReproduceController = new ListReproduceController(movieController, serieController,
                userController);

        c1 = new Category(1, "romance");
        c2 = new Category(2, "drama");
        cat1.add(c2);
        cat1.add(c1);

        m1 = new Movie(111, "blanca nieves", "una pelicula animada de Disney", Date.valueOf(LocalDate.of(2012, 12, 25)),
                cat1);
        m2 = new Movie(222, "Cenicienta", "una pelicula animada de Disney", Date.valueOf(LocalDate.of(2010, 05, 07)),
                cat1);

        s1 = new Serie(1, "Merlina", "serie de netflix", Date.valueOf(LocalDate.of(2010, 05, 07)), cat1,
                new ArrayList<Season>());
        s2 = new Serie(2, "Etile", "serie de netflix", Date.valueOf(LocalDate.of(2019, 04, 05)), cat1,
                new ArrayList<Season>());

        plan = new Plan(1, "Basico", "Disfruta tu plataforma de Multimedia en tu smartphone", 39000, 30);
        sbn = new Subscription(112, plan, u1);
        u1 = new User(1, "bibian", "Corredor", "bibian@gmail.com", "bsnxsa455", new Role(1, "user"), sbn);
        sbn = new Subscription(112, plan, u1);

        u2 = new User(2, "Alejandra", "Deaquiz", "Ale@gmail.com", "bsnxsa454", new Role(2, "user"), sbn);

    }

    @Test
    public void testMethods() {
        assertEquals(true, categoryController.add(c1));
        assertEquals(true, categoryController.add(c2));
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
        updateListTest();
    }

    private void addListTest() {
        assertEquals(true, listReproduceController.add(m1, 1));
        assertEquals(false, listReproduceController.add(m1, 123));
        assertEquals(true, listReproduceController.add(s1, 2));
        assertEquals(s1, userController.get(2).getListPersonalized().get(0));
        assertEquals(true, listReproduceController.add(s2, 1));
        assertEquals(false, listReproduceController.add(s2, 513));

        assertEquals(2, userController.get(1).getListPersonalized().size());
        assertEquals(1, userController.get(2).getListPersonalized().size());
        assertEquals(true, listReproduceController.add(s2, 2));
        assertEquals(2, userController.get(2).getListPersonalized().size());
        assertEquals(s2, userController.get(2).getListPersonalized().get(1));
        assertEquals(false, listReproduceController.add(new Movie(), 2));
        assertEquals(false, listReproduceController.add(new Serie(), 1));
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
        assertEquals(s1, listReproduceController.get(1, 2));
        assertEquals(s2, listReproduceController.get(2, 1));

    }

    private void getAllListTest() {
        ArrayList<Multimedia> prueba = new ArrayList<>();
        prueba.add(s1);
        prueba.add(s2);
        assertEquals(2, listReproduceController.getAll(2).size());
        listReproduceController.add(m2, 2);
        prueba.add(m2);
        assertEquals(prueba, listReproduceController.getAll(2));
        assertEquals(3, listReproduceController.getAll(2).size());

    }

    private void updateListTest() {
        assertEquals(true, listReproduceController.update(222, m1, 2));
        assertEquals(null, listReproduceController.get(222, 1));
        assertEquals(m1, listReproduceController.get(111, 2));
        assertEquals(false, listReproduceController.update(222, m1, 1));

    }

}

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
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class ListReproduceTest {
    public static ListReproduceController listReproduceController;
    public static SerieController serieController;
    public static MovieController movieController;
    public static CategoryController categoryController;
    public static InMemoryPersistence<Multimedia> inMemoryPersistence;
    public static InMemoryPersistence<Movie> impm;
    public static InMemoryPersistence<Serie> imps;
    public static InMemoryPersistence<Category> impc;
    public static ArrayList<Category> cat1 = new ArrayList<>();
    public static Movie m1;
    public static Movie m2;
    public static Serie s1;
    public static Serie s2;

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Multimedia>();
        impm = new InMemoryPersistence<Movie>();
        impc = new InMemoryPersistence<Category>();
        imps = new InMemoryPersistence<Serie>();
        categoryController = new CategoryController(impc);
        serieController = new SerieController(imps, categoryController);
        movieController = new MovieController(impm, categoryController);
        listReproduceController = new ListReproduceController(inMemoryPersistence, movieController, serieController);

        Category c1 = new Category(1, "romance");
        Category c2 = new Category(2, "drama");

        categoryController.add(c1);
        categoryController.add(c2);

        cat1.add(c2);
        cat1.add(c1);
        m1 = new Movie(111, "blanca nieves", "una pelicula animada de Disney", Date.valueOf(LocalDate.of(2012, 12, 25)),
                cat1);
        m2 = new Movie(222, "Cenicienta", "una pelicula animada de Disney", Date.valueOf(LocalDate.of(2010, 05, 07)),
                cat1);

        movieController.add(m1);
        movieController.add(m2);

        s1 = new Serie(1, "Merlina", "serie de netflix", Date.valueOf(LocalDate.of(2010, 05, 07)), cat1,
                new ArrayList<Season>());
        s2 = new Serie(2, "Etile", "serie de netflix", Date.valueOf(LocalDate.of(2019, 04, 05)), cat1,
                new ArrayList<Season>());
        serieController.add(s1);
        serieController.add(s2);

    }

    @Test
    public void testMethods() {
        addListTest();
        deleteListTest();
        getListTest();
        getAllListTest();
        updateListTest();
    }

    private void addListTest() {
        assertEquals(true, listReproduceController.add(m1));
        assertEquals(false, listReproduceController.add(m1));
        assertEquals(true, listReproduceController.add(s1));
        assertEquals(true, listReproduceController.add(s2));
        assertEquals(false, listReproduceController.add(s2));
        assertEquals(false, listReproduceController.add(new Movie()));
        assertEquals(false, listReproduceController.add(new Serie()));
    }

    private void deleteListTest() {
        assertEquals(false, listReproduceController.delete(1265));
        assertEquals(false, listReproduceController.delete(222));
        assertEquals(true, listReproduceController.delete(111));
        assertEquals(false, listReproduceController.delete(111));

    }

    private void getListTest() {
        assertEquals(null, listReproduceController.get(111));
        assertEquals(null, listReproduceController.get(156));
        assertEquals(null, listReproduceController.get(222));
        assertEquals(s1, listReproduceController.get(1));
        assertEquals(s2, listReproduceController.get(2));

    }

    private void getAllListTest() {
        ArrayList<Multimedia> prueba = new ArrayList<>();
        prueba.add(s1);
        prueba.add(s2);
        assertEquals(2, listReproduceController.getAll().size());
        listReproduceController.add(m2);
        prueba.add(m2);
        assertEquals(prueba, listReproduceController.getAll());
        assertEquals(3, listReproduceController.getAll().size());

    }

    private void updateListTest() {
        assertEquals(true, listReproduceController.update(222, m1));
        assertEquals(null, listReproduceController.get(222));
        assertEquals(m1, listReproduceController.get(111));
        assertEquals(false, listReproduceController.update(222, m1));

    }

}

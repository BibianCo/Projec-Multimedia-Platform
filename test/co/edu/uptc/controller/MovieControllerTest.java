package co.edu.uptc.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class MovieControllerTest {
    public static MovieController movieController;
    public static InMemoryPersistence<Movie> inMemoryPersistence;
    public static InMemoryPersistence<Category> impc;
    public static CategoryController categoryController;

    public static ArrayList<Category> cat1 = new ArrayList<>();
    public static ArrayList<Category> cat2 = new ArrayList<>();
    public static ArrayList<Category> cat3 = new ArrayList<>();
    public static ArrayList<Category> cat4 = new ArrayList<>();
    public static Movie m1 = new Movie();
    public static Movie m2 = new Movie();
    public static Movie m3 = new Movie();
    public static Movie m4 = new Movie();

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Movie>();
        impc = new InMemoryPersistence<Category>();
        categoryController = new CategoryController(impc);
        movieController = new MovieController(inMemoryPersistence, categoryController);

        Category c1 = new Category(122, "Romance");
        Category c2 = new Category(123, "Drama");
        Category c3 = new Category(124, "Terror");
        categoryController.add(c1);
        categoryController.add(c2);
        categoryController.add(c3);

        Category cm1 = new Category(0, null);
        Category cm2 = new Category(122, "Romance");
        Category cm3 = new Category(128, "Animada");
        Category cm4 = new Category(124, "Terror");

        cat1.add(cm1);
        cat1.add(cm2);

        cat2.add(cm3);
        cat2.add(cm1);

        cat3.add(cm4);
        cat3.add(cm2);

        cat4.add(cm2);
        cat4.add(cm3);

        m1 = new Movie(111, "Titanic", "Jack y Ross un amor imposible", Date.valueOf("2004-05-04"), cat1);
        m2 = new Movie(222, "Pinocho", "padre e hijo", Date.valueOf("2019-04-05"), cat2);
        m3 = new Movie(333, "It", "payaso asusta ninios", Date.valueOf("2019-04-05"), cat3);
        m4 = new Movie(444, "La monja", "Iglesia", Date.valueOf("2019-05-05"), cat3);
    }

    @Test
    public void testCategoryExists() {

        assertEquals(false, movieController.categoriesExists(cat1));
        assertEquals(true, movieController.categoriesExists(cat3));
        assertEquals(false, movieController.categoriesExists(cat2));
        assertEquals(false, movieController.categoriesExists(cat4));
        assertEquals(false, movieController.categoriesExists(new ArrayList<>()));

    }

    @Test
    public void testAddMovie() {
        Movie m5 = new Movie(555, "El conjuro", "no se de que trata", Date.valueOf("2014-05-01"), new ArrayList<>());
        assertEquals(false, movieController.add(m1));
        assertEquals(false, movieController.add(m2));
        assertEquals(true, movieController.add(m3));
        assertEquals(false, movieController.add(m5));

    }

    @Test
    public void testDeleteMovie() {

        movieController.add(m3);

        assertEquals(false, movieController.delete(0));
        assertEquals(true, movieController.delete(333));
        assertEquals(false, movieController.delete(333));

    }

    @Test
    public void testGetMovie() {
        movieController.add(m3);
        assertEquals(m3, movieController.get(333));
        assertEquals(null, movieController.get(111));
        assertNull(movieController.get(222));

    }

    @Test
    public void testGetAllMovie() {
        movieController.add(m3);
        assertEquals(m3, movieController.getAll().get(0));
        assertEquals(1, movieController.getAll().size());

    }

    @Test
    public void testUpdateMovie() {
        movieController.add(m3);
        assertEquals(false, movieController.update(333, m1));
        assertEquals(false, movieController.update(154, m1));
        assertEquals(true, movieController.update(333,
                new Movie(123, "Harry Potter", "trio de amigos", Date.valueOf("2006-06-12"), cat3)));

        assertEquals(false, movieController.update(333,
                new Movie(123, "Harry Potter", "trio de amigos", Date.valueOf("2006-06-12"), cat3)));
        assertEquals(null, movieController.get(333));
    }

    @Test
    public void testGroupByCategory() {
        movieController.add(m3);
        movieController.add(m4);
        ArrayList<Movie> movies = movieController.groupByCategory(124);

        assertEquals(movies, movieController.groupByCategory(124));
        assertEquals(m3, movieController.groupByCategory(124).get(0));
        assertNull(movieController.groupByCategory(111));
        assertNull(movieController.groupByCategory(122));

    }

}
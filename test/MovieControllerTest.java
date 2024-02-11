
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.MovieController;
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

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Movie>();
        impc = new InMemoryPersistence<Category>();
        categoryController = new CategoryController(impc);
        movieController = new MovieController(inMemoryPersistence, categoryController);
    }

    public void setUp2() {
        Category c1 = new Category(122, "Romance");
        Category c2 = new Category(123, "Drama");
        Category c3 = new Category(124, "Terror");
        categoryController.add(c1);
        categoryController.add(c2);
        categoryController.add(c3);

    }

    public void setUp3() {

        Category c1 = new Category(0, null);
        Category c2 = new Category(122, "Romance");
        Category c3 = new Category(128, "Animada");
        Category c4 = new Category(124, "Terror");

        cat1.add(c1);
        cat1.add(c2);

        cat2.add(c3);
        cat2.add(c1);

        cat3.add(c4);
        cat3.add(c2);

        cat4.add(c2);
        cat4.add(c3);

        m1 = new Movie(111, "Titanic", "Jack y Ross un amor imposible", Date.valueOf("2004-05-04"), cat1);
        m2 = new Movie(222, "Pinocho", "padre e hijo", Date.valueOf("2019-04-05"), cat2);
        m3 = new Movie(333, "It", "payaso asusta ninios", Date.valueOf("2019-04-05"), cat3);

    }

    @Test
    public void testCategoryExists() {
        setUp2();
        setUp3();

        assertEquals(false, movieController.categoriesExists(cat1));
        assertEquals(true, movieController.categoriesExists(cat3));
        assertEquals(false, movieController.categoriesExists(cat2));
        assertEquals(false, movieController.categoriesExists(cat4));

    }

    @Test
    public void testAddMovie() {
        setUp2();
        setUp3();

        assertEquals(false, movieController.add(m1));
        assertEquals(false, movieController.add(m2));
        assertEquals(true, movieController.add(m3));

    }

    @Test
    public void testDeleteMovie() {
        setUp2();
        setUp3();

        movieController.add(m3);

        assertEquals(false, movieController.delete(0));
        assertEquals(true, movieController.delete(333));
        assertEquals(false, movieController.delete(333));

    }

    @Test
    public void testGetMovie() {
        setUp2();
        setUp3();
        movieController.add(m3);
        assertEquals(m3, movieController.get(333));
        assertEquals(null, movieController.get(111));
        assertNull(movieController.get(222));

    }

    @Test
    public void testGetAllMovie() {
        setUp2();
        setUp3();
        movieController.add(m3);
        assertEquals(m3, movieController.getAll().get(0));

    }

}

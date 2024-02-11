import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import javax.xml.catalog.Catalog;

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
    public static CategoryController categoryController;

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Movie>();
        movieController = new MovieController(inMemoryPersistence);
        categoryController = new CategoryController();
        categoryController.add(new Category(122, "Romance"));
        categoryController.add(new Category(123, "Drama"));
        categoryController.add(new Category(124, "Terror"));
    }

    @Test
    public void testAddMovie() {

        ArrayList<Category> categories = new ArrayList<>();
        Category c1 = categoryController.getAll().get(0);
        Category c2 = categoryController.getAll().get(1);
        categories.add(c1);
        categories.add(c2);

        Movie m1 = new Movie(1, "Titanic", "Jack y Ross un amor imposible", Date.valueOf("2004-05-04"), categories);

        assertEquals(false, movieController.add(m1));

    }

}

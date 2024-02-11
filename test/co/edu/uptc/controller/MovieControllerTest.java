package co.edu.uptc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Movie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class MovieControllerTest {
    public static MovieController movieController;
    public static InMemoryPersistence<Movie> inMemoryPersistence;

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Movie>();
        movieController = new MovieController(inMemoryPersistence);
    }

    @Test
    public void testAddMovie() {
        Movie m1 = new Movie(123, "Sweet girl", "Defensor de su familia", Date.valueOf("2005-12-14"), null);

        assertEquals(true, movieController.add(m1));
    }

}

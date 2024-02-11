package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class SerieControllerTest {

    public SerieController serieController;
    public InMemoryPersistence<Serie> inMemoryPersistence;
    public static InMemoryPersistence<Category> impc;
    public static CategoryController categoryController;
    private Serie serie1, serie2;

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Serie>();
        impc = new InMemoryPersistence<Category>();
        categoryController = new CategoryController(impc);
        serieController = new SerieController(inMemoryPersistence, categoryController);
    }

    public void setUp2() {
        serie1 = new Serie(1, "Serie 1", "Synopsis 1", null, new ArrayList<Category>(), new ArrayList<Season>());
        serie2 = new Serie(2, "Serie 2", "Synopsis 2", null, new ArrayList<Category>(), new ArrayList<Season>());

        serieController.add(serie1);
        serieController.add(serie2);
    }

    @Test
    public void testAddSerie() {
        Serie serie = new Serie(3, "New Serie", "New Synopsis", null, new ArrayList<Category>(),
                new ArrayList<Season>());
        assertTrue(serieController.add(serie));
    }

    @Test
    public void testDeleteSerie() {
        setUp2();
        assertEquals(true, serieController.delete(1));
        assertFalse(serieController.delete(1));
        assertFalse(serieController.delete(5));
    }

    @Test
    public void testGet() {
        setUp2();
        assertEquals(serie1, serieController.get(1));
        assertNull(serieController.get(0));
    }

    @Test
    public void testUpdateMovie() {
        setUp2();
        ArrayList<Episode> episodes = new ArrayList<>();

        Episode ep = new Episode(1, 23, 45);
        episodes.add(ep);
        Category c1 = new Category(122, "Romance");
        Category c2 = new Category(123, "Drama");
        Category c3 = new Category(124, "Terror");
        categoryController.add(c1);
        categoryController.add(c2);
        categoryController.add(c3);

        Serie newSerie = new Serie(45, "merlina", "chica mala", null, new ArrayList<Category>(),
                new ArrayList<Season>());

        assertTrue(serieController.update(1, newSerie));
        assertEquals(false, serieController.update(333, newSerie));
        assertEquals(false, serieController.update(154, newSerie));
        assertNull(serieController.get(1));
    }

    @Test
    public void testGetAll() {
        setUp2();
        assertEquals(serie1, serieController.getAll().get(0));
        assertEquals(serie2, serieController.getAll().get(1));
    }

    @Test
    public void testGetPersistence() {
        assertEquals(inMemoryPersistence, serieController.getPersistence());
    }
}

package co.edu.uptc.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class SerieControllerTest {

    public SerieController serieController;
    public InMemoryPersistence<Serie> inMemoryPersistence;
    private Serie serie1, serie2;

    @Before
    public void setUp() {
        this.inMemoryPersistence = new InMemoryPersistence<Serie>();
        this.serieController = new SerieController(inMemoryPersistence);
    }

    public void setUp2() {
        serie1 = new Serie(1, "Serie 1", "Synopsis 1", null, new ArrayList<Category>(), new ArrayList<Season>());
        serie2 = new Serie(2, "Serie 2", "Synopsis 2", null, new ArrayList<Category>(), new ArrayList<Season>());

        serieController.add(serie1);
        serieController.add(serie2);
    }

    @Test
    public void testAddSerie() {
        Serie serie = new Serie(3, "New Serie", "New Synopsis", null, new ArrayList<Category>(), new ArrayList<Season>());
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
    public void testUpdate() {
        setUp2();
        // 1-change title
        // 2-change synopsis
        // 3-change categories
        // 4-change seasons

        serieController.update(1, 1, "New Title", null, null, null);
        assertEquals(serie1.getTitle(), "New Title");
        assertEquals(serie1.getSynopsis(), "Synopsis 1");

        serieController.update(2, 1, null, "New Synopsis", null, null);
        assertEquals(serie1.getSynopsis(), "New Synopsis");
        assertEquals(serie1.getTitle(), "New Title");

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


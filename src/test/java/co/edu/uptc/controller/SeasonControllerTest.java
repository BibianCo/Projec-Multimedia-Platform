package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class SeasonControllerTest {

    private InMemoryPersistence<Season> inMemoryPersistence;
    private InMemoryPersistence<Serie> imps;
    private InMemoryPersistence<Category> impc;
    private CategoryController categoryController;
    private SerieController serieController;
    private SeasonController seasonController;

    @Before
    public void setUp() {
        this.inMemoryPersistence = new InMemoryPersistence<>();
        this.imps = new InMemoryPersistence<>();
        this.impc = new InMemoryPersistence<>();
        this.categoryController = new CategoryController(impc);
        this.serieController = new SerieController(imps, categoryController);
        this.seasonController = new SeasonController(inMemoryPersistence, serieController);
        Category category = new Category(1, "terror");
        categoryController.add(category);

        Serie serie = new Serie(23, "as", "ad", null, categoryController.getAll());
        serieController.add(serie);

        Season season = new Season(1123, 1, 23);
        Season season1 = new Season(2323, 2, 83);
        Season season2 = new Season(2321, 3, 23);

        seasonController.add(season);
        seasonController.add(season1);
        seasonController.add(season2);
    }

    @Test
    public void addTest() {

        Category category = new Category(1, "terror");
        categoryController.add(category);

        Serie serie = new Serie(23, "as", "ad", null, categoryController.getAll());

        serieController.add(serie);
        Season season = new Season(11123, 1, 23);
        Season season1 = new Season(21323, 2, 83);
        Season season2 = new Season(23121, 3, 23);

        assertEquals(true, seasonController.add(season));
        assertFalse(seasonController.add(season1));
        assertEquals(false, seasonController.add(null));
        assertEquals(false, seasonController.add(season1));
        assertEquals(true, seasonController.add(season2));
    }

    @Test
    public void deleteTest() {
        setUp();
        assertEquals(true, seasonController.delete(1123));
        assertEquals(false, seasonController.delete(2323));
        assertFalse(seasonController.delete(-5));
    }

    @Test
    public void getTest() {
        setUp();
        assertEquals(3, seasonController.get(2321).getNumber());
        assertNull(seasonController.get(1323));
        assertEquals(1, seasonController.get(1123).getNumber());
    }

    @Test
    public void getAllTest() {
        setUp();

        assertEquals(1123, seasonController.getAll().get(0).getId());
        assertEquals(3, seasonController.getAll().get(1).getNumber());
    }

    @Test
    public void update() {
        Season season = new Season(45, 78, 23);
        assertEquals(true, seasonController.update(1123, season));
        assertEquals(45, seasonController.get(45).getId());
    }

}

package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;

public class SeasonControllerTest {

    private FilePersistence<Season> inFilePersistence;
    private FilePersistence<Serie> ifps;
    private FilePersistence<Category> ifpc;
    private CategoryController categoryController;
    private SerieController serieController;
    private SeasonController seasonController;
    private Season season, season2;

    @Before
    public void setUp() {

        Type type = new TypeToken<ArrayList<Season>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        Type type3 = new TypeToken<ArrayList<Category>>() {
        }.getType();
        this.inFilePersistence = new FilePersistence<>(type, "season");
        this.ifps = new FilePersistence<>(type2, "serie");
        this.ifpc = new FilePersistence<>(type3, "category");
        this.categoryController = new CategoryController(ifpc);
        this.serieController = new SerieController(ifps, categoryController);
        this.seasonController = new SeasonController(inFilePersistence, serieController);

        // create Files

        inFilePersistence.createFile();
        ifpc.createFile();
        ifps.createFile();

        Category category = new Category(12, "comedia");
        categoryController.add(category);

        Serie serie = new Serie(23, "as", "ad", LocalDate.parse("2003-02-03"));
        serie.setCategories(categoryController.getAll());
        serieController.add(serie);

        season = new Season(788, 4, 23);
        season2 = new Season(245, 4, 23);
        seasonController.add(season);
        seasonController.add(season2);

    }

    @Test
    public void addTest() {

        Category category = new Category(1, "terror");
        assertTrue(categoryController.add(category));

        Serie serie = new Serie(234, "as", "ad", LocalDate.parse("2003-02-03"));
        serie.setCategories(categoryController.getAll());

        assertTrue(serieController.add(serie));
        Season season = new Season(11123, 1, 234);
        Season season1 = new Season(21323, 2, 83);
        Season season2 = new Season(23121, 3, 234);

        assertEquals(true, seasonController.add(season));
        assertFalse(seasonController.add(season1));
        assertEquals(false, seasonController.add(null));
        assertEquals(false, seasonController.add(season1));
        assertEquals(true, seasonController.add(season2));
    }

    @Test
    public void deleteTest() {
        setUp();
        assertTrue(seasonController.delete(788));
        assertFalse(seasonController.delete(5454));
        assertFalse(seasonController.delete(788));

    }

    @Test
    public void getTest() {
        setUp();
        assertEquals(4, seasonController.get(788).getNumber());
        assertNull(seasonController.get(1323));
        assertEquals(season2.getId(), seasonController.get(245).getId());
    }

    @Test
    public void getAllTest() {
        setUp();

        assertEquals(788, seasonController.getAll().get(0).getId());
        assertEquals(4, seasonController.getAll().get(1).getNumber());
    }

    @Test
    public void update() {
        Season season = new Season(45, 78, 23);
        assertTrue(seasonController.update(245, season));
        assertEquals(78, seasonController.get(45).getNumber());
    }

}

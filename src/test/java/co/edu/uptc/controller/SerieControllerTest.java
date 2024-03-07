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
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;

public class SerieControllerTest {

    private FilePersistence<Serie> infilePersistenceSerie;
    private FilePersistence<Season> inFilePersistenceSeason;
    private FilePersistence<Category> inFilePeristenceCategory;
    private FilePersistence<Episode> inFilePeristenceEpisode;

    private CategoryController categoryController;
    private SerieController serieController;
    private SeasonController seasonControllers;
    private EpisodeController episodeController;

    private Serie serie, serie2;

    @Before
    public void setup() {
        Type type = new TypeToken<ArrayList<Season>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        Type type3 = new TypeToken<ArrayList<Category>>() {
        }.getType();
        Type type4 = new TypeToken<ArrayList<Episode>>() {
        }.getType();

        this.inFilePeristenceCategory = new FilePersistence<>(type3, "category");
        this.infilePersistenceSerie = new FilePersistence<>(type2, "serie");
        this.inFilePersistenceSeason = new FilePersistence<>(type, "season");
        this.inFilePeristenceEpisode = new FilePersistence<>(type4, "episode");

        this.categoryController = new CategoryController(inFilePeristenceCategory);
        this.serieController = new SerieController(infilePersistenceSerie, categoryController);
        this.seasonControllers = new SeasonController(inFilePersistenceSeason, serieController);
        this.episodeController = new EpisodeController(inFilePeristenceEpisode, seasonControllers);
        infilePersistenceSerie.createFile();
        inFilePeristenceCategory.createFile();
        inFilePersistenceSeason.createFile();
        inFilePeristenceEpisode.createFile();
        // adicionar categorias
        Category category = new Category(5, "gold");
        categoryController.add(category);

        // series
        serie = new Serie(445, "Titanic", "asdasd", LocalDate.parse("2003-02-03"));
        serie.setCategories(categoryController.getAll());

        serie2 = new Serie(4445, "Amarte", "asdasd", LocalDate.parse("2003-02-03"));
        serie2.setCategories(categoryController.getAll());

        serieController.add(serie);
        serieController.add(serie2);

    }

    @Test
    public void testCategoryExists() {
        assertTrue(serieController.categoriesExists(categoryController.getAll()));
    }

    @Test
    public void addSerie() {

        Serie serie = new Serie(45, "Hola", "asdasd", LocalDate.parse("2003-02-03"));
        Season season = new Season(5, 5, 45);
        Episode episode = new Episode(4223, 5, 45, 5);
        serie.setCategories(categoryController.getAll());
        assertTrue(serieController.add(serie));
        assertTrue(seasonControllers.add(season));
        assertTrue(episodeController.add(episode));

    }

    @Test
    public void testDelete() {
        setup();
        assertTrue(serieController.delete(445));
        assertFalse(serieController.delete(789));
        assertFalse(serieController.delete(445));
    }

    @Test
    public void testGet() {
        setup();
        assertEquals(serie2.getId(), serieController.get(4445).getId());
        assertNull(serieController.get(0));
        assertEquals(serie.getReleaseDate(), serieController.get(445).getReleaseDate());

    }

    @Test
    public void testUpdate() {
        setup();

        Serie serie = new Serie(78, "it", "hola como estas?", LocalDate.parse("2003-02-03"));
        serie.setCategories(categoryController.getAll());
        assertTrue(serieController.update(445, serie));
        assertFalse(serieController.update(545452, serie));
    }

    @Test
    public void testGetAll() {
        assertEquals(serie.getId(), serieController.getAll().get(0).getId());
        assertEquals(serie2.getTitle(), serieController.getAll().get(1).getTitle());
    }

    @Test
    public void testGroupByCategory() {
        setup();

        assertEquals(445, serieController.groupByCategory(5).get(0).getId());
        assertNull(serieController.groupByCategory(0));

    }

}
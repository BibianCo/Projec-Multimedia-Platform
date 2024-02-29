package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.controller.EpisodeController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class EpisodeControllerTest {

    private InMemoryPersistence<Episode> inMemoryPersistence;
    private EpisodeController episodeController;
    private SeasonController seasonController;
    private SerieController serieController;
    private CategoryController categoryController;
    private InMemoryPersistence<Episode> impe;
    private InMemoryPersistence<Season> imps;
    private InMemoryPersistence<Serie> impserie;
    private InMemoryPersistence<Category> impc;
    private Episode ep1, ep2;
    private Serie serie1, serie2;

    @Before
    public void setUp() {
        this.inMemoryPersistence = new InMemoryPersistence<>();
        this.imps = new InMemoryPersistence<>();
        this.impserie = new InMemoryPersistence<>();
        this.impc = new InMemoryPersistence<>();
        this.categoryController = new CategoryController(impc);
        this.serieController = new SerieController(impserie, categoryController);
        this.seasonController = new SeasonController(imps, serieController);
        this.episodeController = new EpisodeController(impe, seasonController);

        // adicionar categorias
        ArrayList<Category> categories = new ArrayList<>();
        Category category = new Category(1, "Romantica");
        Category category2 = new Category(2, "Comedia");
        categoryController.add(category);
        categoryController.add(category2);
        categories.add(category2);
        categories.add(category2);

        //
        // adicionar serie

        serie1 = new Serie(23, "antes de ti", "adsdasd", Date.valueOf(LocalDate.of(2023, 12, 45)), categories, null);

        this.ep1 = new Episode(1, 12, 45);
        this.ep2 = new Episode(2, 2, 45);

        episodeController.add(ep1);
        episodeController.add(ep2);
    }

    @Test
    public void addTest() {
        Episode episode = new Episode(45, 7, 45);
        Episode episode2 = new Episode(0, 7, 45);

        assertEquals(true, episodeController.add(episode));
        assertEquals(false, episodeController.add(episode2));
    }

    @Test
    public void deleteTest() {
        setUp();
        assertEquals(true, episodeController.delete(1));
        assertEquals(false, episodeController.delete(12321));
    }

    @Test
    public void getTest() {
        setUp();
        assertEquals(ep1.getDuration(), episodeController.get(1).getDuration());
        assertEquals(null, episodeController.get(23323));
    }

    @Test
    public void getAllTest() {
        setUp();
        assertEquals(1, episodeController.getAll().get(0).getId());
        assertEquals(2, episodeController.getAll().get(1).getId());
    }

    @Test
    public void update() {
        Episode episode = new Episode(23, 12, 78);

        assertEquals(true, episodeController.update(1, episode));
    }

}

package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class EpisodeControllerTest {
    private EpisodeController episodeController;
    private SeasonController seasonController;
    private SerieController serieController;
    private CategoryController categoryController;
    private InMemoryPersistence<Episode> impe;
    private InMemoryPersistence<Season> imps;
    private InMemoryPersistence<Serie> impserie;
    private InMemoryPersistence<Category> impc;
    private Episode ep1, ep2;
    private Serie serie, serie2;
    private Season season;

    @Before
    public void setUp() {
        this.impe = new InMemoryPersistence<>();
        this.imps = new InMemoryPersistence<>();
        this.impserie = new InMemoryPersistence<>();
        this.impc = new InMemoryPersistence<>();

        this.categoryController = new CategoryController(impc);
        this.serieController = new SerieController(impserie, categoryController);
        this.seasonController = new SeasonController(imps, serieController);
        this.episodeController = new EpisodeController(impe, seasonController);

        // adicionar categorias
        Category category = new Category(1, "Romantica");
        Category category2 = new Category(2, "Comedia");
        categoryController.add(category);
        categoryController.add(category2);

        // adicionar serie
        serie = new Serie(23, "antes de ti", "adsdasd", LocalDate.parse("2003-02-03"),
                categoryController.getAll());
        serieController.add(serie);
        serie2 = new Serie(234, "antes de ti", "adsdasd", LocalDate.of(2023, 12, 05),
                categoryController.getAll());
        serieController.add(serie2);

        ;
        // adicionar Season
        season = new Season(1, 1, 23);
        seasonController.add(season);

        // adicionar episode
        ep1 = new Episode(321, 1, 45, 1);
        ep2 = new Episode(232, 2, 78, 1);
        episodeController.add(ep1);
        episodeController.add(ep2);

    }

    @Test
    public void addTest() {
        serie = new Serie(223, "antes de ti", "adsdasd", LocalDate.of(2023, 12, 05),
                categoryController.getAll());
        serieController.add(serie);
        Episode episode = new Episode(12, 1, 45, 1);
        Episode episode2 = new Episode(23, 1, 45, 1);

        assertEquals(true, episodeController.add(episode));
        assertEquals(true, episodeController.add(episode2));
        assertEquals(false, episodeController.add(episode2));
    }

    @Test
    public void deleteTest() {
        setUp();
        assertEquals(true, episodeController.delete(321));
        assertEquals(false, episodeController.delete(321));
    }

    @Test
    public void getTest() {
        setUp();
        assertEquals(ep1.getDuration(), episodeController.get(321).getDuration());
        assertEquals(null, episodeController.get(23323));
    }

    @Test
    public void getAllTest() {
        setUp();
        assertEquals(321, episodeController.getAll().get(0).getId());
        assertEquals(232, episodeController.getAll().get(1).getId());
    }

    @Test
    public void update() {

        Episode episode = new Episode(321, 1, 45, 1);
        assertTrue(episodeController.update(321, episode));
    }

}

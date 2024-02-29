package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class SerieControllerTest {

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
    private Season season, season2;

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
        serie = new Serie(23, "antes de ti", "adsdasd", Date.valueOf(LocalDate.of(2023, 12, 45)),
                categoryController.getAll());

        // adicionar Season
        season = new Season(1, 1, null, 0);

        // adicionar episode
        ep1 = new Episode(321, 1, 45, 1);
        ep2 = new Episode(232, 2, 78, 1);

    }

}

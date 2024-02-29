package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.controller.EpisodeController;
import co.edu.uptc.controller.SeasonController;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.persistence.InMemoryPersistence;

public class SeasonControllerTest {

    private InMemoryPersistence<Season> inMemoryPersistence;
    private InMemoryPersistence<Episode> impe;
    private SeasonController seasonController;
    private EpisodeController episodeController;
    private Season season1, season2, season3;
    private Episode episode1, episode2;
    private ArrayList<Episode> episodes;

    @Before
    public void setUp() {
        this.inMemoryPersistence = new InMemoryPersistence<Season>();
        this.impe = new InMemoryPersistence<Episode>();
        this.episodeController = new EpisodeController(impe);
        this.seasonController = new SeasonController(inMemoryPersistence, episodeController);
        this.episodes = new ArrayList<>();
        episode1 = new Episode(1, 1, 30);
        episode2 = new Episode(2, 2, 45);
        episodes.add(episode1);
        episodes.add(episode2);

        season1 = new Season(1, 1, episodes);
        season2 = new Season(2, 2, episodes);
        season3 = new Season(3, 3, new ArrayList<>());
        seasonController.add(season1);
        seasonController.add(season2);
        seasonController.add(season3);

    }

    @Test
    public void addTest() {
        ArrayList<Episode> episodes = new ArrayList<>();
        Episode episode = new Episode(12, 1, 56);
        episodeController.add(episode);
        episodes.add(episode);
        Season season = new Season(1123, 1, episodes);
        Season season1 = new Season(1123, 1, episodes);

        System.out.println(season.toString());

        assertEquals(true, seasonController.add(season));
        assertFalse(seasonController.add(season1));
        assertEquals(false, seasonController.add(null));

    }

    @Test
    public void deleteTest() {
        setUp();
        assertEquals(true, seasonController.delete(1));
        assertEquals(false, seasonController.delete(234234));
        assertFalse(seasonController.delete(-5));
    }

    @Test
    public void getTest() {
        setUp();
        assertEquals(1, seasonController.get(1).getNumber());
        assertNull(seasonController.get(1323));
        assertEquals(30, seasonController.get(2).getEpisodes().get(0).getDuration());
    }

    @Test
    public void getAllTest() {
        setUp();

        assertEquals(1, seasonController.getAll().get(0).getId());
        assertEquals(2, seasonController.getAll().get(1).getId());
    }

    @Test
    public void update() {
        Season season = new Season(45, 78, episodes);
        assertEquals(true, seasonController.update(1, season));
        assertEquals(45, seasonController.get(45).getId());
    }

}

package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.controller.EpisodeController;
import co.edu.uptc.model.Episode;
import co.edu.uptc.persistence.InMemoryPersistence;

public class EpisodeControllerTest {

    private InMemoryPersistence<Episode> inMemoryPersistence;
    private EpisodeController episodeController;
    private Episode ep1, ep2;

    @Before
    public void setUp() {
        this.inMemoryPersistence = new InMemoryPersistence<>();
        this.episodeController = new EpisodeController(inMemoryPersistence);

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

package co.edu.uptc.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import co.edu.uptc.model.Movie;
import co.edu.uptc.view.MediaPlayerApp;

public class MediaPlayerTest {

    @Test
    public void reproduce() {
        MediaPlayerApp mp = new MediaPlayerApp();
        Movie harry = new Movie("harry", "en la aldea", "suspenso", LocalDate.of(2023, 2, 12), false);
        mp.reproduce(harry);
        assertEquals(true, harry.isReproduce());
    }

}

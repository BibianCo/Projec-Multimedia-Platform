package co.edu.uptc.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import co.edu.uptc.controller.MediaPlayerController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Serie;
import co.edu.uptc.view.MediaPlayerApp;

public class MediaPlayerTest {

    @Test
    public void reproduce() {
        MediaPlayerApp mp = new MediaPlayerApp();
        Movie harry = new Movie("harry", "en la aldea", "suspenso", LocalDate.of(2023, 2, 12), false);
        mp.reproduce(harry);
        assertEquals(true, harry.isReproduce());
    }

     @Test
    public void searchName() { 
        MediaPlayerController mpc = new MediaPlayerController();
        String input = "Harry";
        ArrayList <Multimedia> multi = mpc.searchName(input);
        System.out.println(input);
        
        for(Multimedia m : multi){
            System.out.println(m);
        }

    }
}

package co.edu.uptc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javax.print.attribute.standard.Media;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;

public class MediaPlayerApp {
    private static boolean paused = false;

    public static void main(String[] args) {

        MediaPlayerApp mApp = new MediaPlayerApp();
        Movie harry = new Movie("harry", "en la aldea", "suspenso", LocalDate.of(2023, 2, 12), false);
        mApp.reproduce(harry);

    }

    public void printReproductor() {
        int numberOfSpaces = 50;
        String input;
        Scanner sc = new Scanner(System.in);
        for (int currentPitch = 0; currentPitch <= numberOfSpaces; currentPitch++) {
            int porcentaje = (currentPitch * 100) / numberOfSpaces;

            System.out.print("\r[");
            for (int i = 0; i < currentPitch; i++) {
                System.out.print("=");
            }

            for (int i = currentPitch; i < numberOfSpaces; i++) {
                System.out.print(" ");
            }

            System.out.print("]" + porcentaje + " %          " + "P Paused " + "C Continue");

            try {
                while (paused == true) {
                    continue_();
                }

                Thread.sleep(100);

                if (System.in.available() > 0) {
                    input = sc.nextLine().toLowerCase();
                    if (input.equals("p")) {
                        stop();
                    }
                }

            } catch (InterruptedException e) {
                System.out.println("Error en la espera: " + e.getMessage());
            } catch (Exception e) {
            }

        }

        System.out.println("");
        System.out.println("finished reproduction");

        sc.close();

    }

    public void reproduce(Multimedia multimedia) {
        if (multimedia.getTitle() != null && multimedia.getTitle() != "") {
            multimedia.setReproduce(true);
            System.out.println("Playing  " + multimedia.getTitle());
            printReproductor();
        }
    }

    public void stop() {
        paused = true;
        System.out.println("Paused");
    }

    public void continue_() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("c")) {
            paused = false;
        }
        System.out.println("Continue");
    }

}
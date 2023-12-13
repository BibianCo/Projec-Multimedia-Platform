package co.edu.uptc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import javax.print.attribute.standard.Media;

import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.controller.MultimediaGalleryController;
import co.edu.uptc.controller.PlanController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Serie;

public class MediaPlayerApp {

    private static AdministratorController administratorController = new AdministratorController();
    private static MultimediaGalleryController mgc = MultimediaGalleryController.getInstance();
    private static PlanController planController = new PlanController();

    private static boolean paused = false;

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

    public void showTableSerie(HashMap<Integer, Serie> Series) {
        System.out.println("Series:");
        System.out.println("+------------+----------------------+-------------------------+-----------------+");
        System.out.println("|    ID      |         TITLE        |       DESCRIPTION       |  NUMBER SEASON  |");
        System.out.println("+------------+----------------------+-------------------------+-----------------+");

        for (Serie serie : administratorController.showSeries().values()) {

            if (serie.getDescription().length() > 22) {

            }
            System.out.printf("|%-12d| %-21s| %-24s| %-11d |\n", serie.getCode(),
                    serie.getTitle(), serie.getDescription(), serie.getNumberSeasons());
            System.out
                    .println("+------------+----------------------+-----------------+-----------------+");

        }

    }

    public void showTableMovie(HashMap<Integer, Movie> movies) {
        System.out.println("Movies:");
        System.out.println("+------------+----------------------+-------------------------+-----------------+");
        System.out.println("|    ID      |         TITLE        |       DESCRIPTION       |     DURATION    |");
        System.out.println("+------------+----------------------+-------------------------+-----------------+");

        for (Movie movie : administratorController.showMovie().values()) {

            if (movie.getDescription().length() > 22) {
            }
            System.out.printf("|%-12d| %-21s| %-24s| %-16d|\n", movie.getCode(),
                    movie.getTitle(), movie.getDescription(), movie.getDuration());
            System.out.println("+------------+----------------------+-------------------------+-----------------+");

        }

    }

    public void showMultimedia(String name) {
        System.out.println("Movies and Serie");
        System.out.println("+------------+----------------------+-------------------------+-----------------+");
        System.out.println("|    ID      |         TITLE        |       DESCRIPTION       |     CATEGORY    |");
        System.out.println("+------------+----------------------+-------------------------+-----------------+");

        for (Multimedia multimedia2 : mgc.searchName(name)) {

            System.out.printf("|%-12d| %-21s| %-24s| %-16s|\n", multimedia2.getCode(),
                    multimedia2.getTitle(), multimedia2.getDescription(), multimedia2.getCategory().toString());
            System.out.println("+------------+----------------------+-------------------------+-----------------+");

        }

    }

    public void showPlans() {
        System.out.println("Plans");
        System.out.println("+------------+-------------------------+-------------------------+");
        System.out.println("|    CODE    |         VALUE           |       DESCRIPTION       |");
        System.out.println("+------------+-------------------------+-------------------------+");

        int iterador = 0;
        for (Plan plan : planController.getListPlans()) {
            iterador++;
            System.out.printf("|%-12d| %-24s| %-24s|\n", iterador, plan.getValue(), plan.getDescription());
            System.out.println("+------------+-------------------------+-------------------------+");

        }

    }

}
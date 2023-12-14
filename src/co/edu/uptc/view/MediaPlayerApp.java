package co.edu.uptc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                System.out.println("Error en la espera: ");
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

    public void showTableSerie(HashMap<Integer, Serie> series) {
        System.out.println("Series:");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------------+---------------------+------------------+");
        System.out.println(
                "|    ID      |         TITLE        |       DESCRIPTION         |     NUMBER SEASONS    |      PUBLICATION    |     CATEGORY     |");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------------+---------------------+------------------+");

        for (Serie serie : administratorController.showSeries().values()) {
            String truncatedDescription = serie.getDescription();

            if (truncatedDescription.length() > 22) {
                truncatedDescription = truncatedDescription.substring(0, 19) + "...";
            }

            System.out.printf("|%-12d| %-21s| %-26s| %-22d| %-20s| %-17s|\n", serie.getCode(),
                    serie.getTitle(), truncatedDescription, serie.getNumberSeasons(), serie.getPublication(),
                    serie.getCategory().toString());
            System.out.println(
                    "+------------+----------------------+---------------------------+-----------------------+---------------------+------------------+");

        }
    }

    public void showTableMovie(HashMap<Integer, Movie> movies) {
        System.out.println("Movies:");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------+---------------------+------------------+");
        System.out.println(
                "|    ID      |         TITLE        |       DESCRIPTION         |     DURATION    |      PUBLICATION    |     CATEGORY     |");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------+---------------------+------------------+");

        for (Movie movie : administratorController.showMovie().values()) {
            String truncatedDescription = movie.getDescription();

            if (truncatedDescription.length() > 22) {
                truncatedDescription = truncatedDescription.substring(0, 19) + "...";
            }

            System.out.printf("|%-12d| %-21s| %-26s| %-16d| %-20s| %-17s|\n", movie.getCode(),
                    movie.getTitle(), truncatedDescription, movie.getDuration(), movie.getPublication(),
                    movie.getCategory().toString());
            System.out.println(
                    "+------------+----------------------+---------------------------+-----------------+---------------------+------------------+");

        }
    }

    public void showMultimedia(String name) {
        System.out.println("Movies and Serie");
        System.out.println(
                "+------------+----------------------+--------------------------+-----------------+---------------------+");
        System.out.println(
                "|    ID      |         TITLE        |        DESCRIPTION       |     CATEGORY    |    PUBLICATION     |");
        System.out.println(
                "+------------+----------------------+--------------------------+-----------------+---------------------+");

        for (Multimedia multimedia2 : mgc.searchName(name)) {
            System.out.printf("|%-12d| %-21s| %-25s| %-16s| %-20s|\n", multimedia2.getCode(),
                    multimedia2.getTitle(), multimedia2.getDescription(), multimedia2.getCategory().toString(),
                    multimedia2.getPublication());
            System.out.println(
                    "+------------+----------------------+-------------------------+-----------------+---------------------+");

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

    public void showFavorite(String name) {
        System.out.println("Movies and Serie");
        System.out.println(
                "+------------+----------------------+--------------------------+-----------------+---------------------+");
        System.out.println(
                "|    ID      |         TITLE        |        DESCRIPTION       |     CATEGORY    |    PUBLICATION     |");
        System.out.println(
                "+------------+----------------------+--------------------------+-----------------+---------------------+");

        for (Multimedia multimedia2 : mgc.searchName(name)) {
            System.out.printf("|%-12d| %-21s| %-25s| %-16s| %-20s|\n", multimedia2.getCode(),
                    multimedia2.getTitle(), multimedia2.getDescription(), multimedia2.getCategory().toString(),
                    multimedia2.getPublication());
            System.out.println(
                    "+------------+----------------------+-------------------------+-----------------+---------------------+");

        }
    }

    public void showTableSeriesByCategory(int categoryNum) {
        List<Serie> series = administratorController.getSeriesByCategory(categoryNum);

        System.out.println("Series:");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------------+---------------------+");
        System.out.println(
                "|    ID      |         TITLE        |       DESCRIPTION         |     NUMBER SEASONS    |     PUBLICATION    |");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------------+---------------------+");

        for (Serie serie : series) {
            String truncatedDescription = serie.getDescription();

            if (truncatedDescription.length() > 22) {
                truncatedDescription = truncatedDescription.substring(0, 19) + "...";
            }

            System.out.printf("|%-12d| %-21s| %-26s| %-22d| %-19s|\n", serie.getCode(),
                    serie.getTitle(), truncatedDescription, serie.getNumberSeasons(), serie.getPublication());
            System.out.println(
                    "+------------+----------------------+---------------------------+-----------------------+---------------------+");
        }
    }

    public void showTableMoviesByCategory(int categoryNum) {
        List<Movie> movies = administratorController.getMoviesByCategory(categoryNum);

        System.out.println("MOVIES:");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------------+---------------------+");
        System.out.println(
                "|    ID      |         TITLE        |       DESCRIPTION         |       DURATION        |     PUBLICATION    |");
        System.out.println(
                "+------------+----------------------+---------------------------+-----------------------+---------------------+");

        for (Movie movie : movies) {
            String truncatedDescription = movie.getDescription();

            if (truncatedDescription.length() > 22) {
                truncatedDescription = truncatedDescription.substring(0, 19) + "...";
            }

            System.out.printf("|%-12d| %-21s| %-26s| %-22d| %-19s|\n", movie.getCode(),
                    movie.getTitle(), truncatedDescription, movie.getDuration(), movie.getPublication());
            System.out.println(
                    "+------------+----------------------+---------------------------+-----------------------+---------------------+");
        }
    }
}
package co.edu.uptc.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.controller.MediaPlayerController;
import co.edu.uptc.controller.MultimediaGalleryController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class InterfazUser {
    private static UserController userController = new UserController();
    private static AdministratorController administratorController = new AdministratorController();
    private static MediaPlayerApp mediaPlayerApp = new MediaPlayerApp();
    private static MediaPlayerController mpc = new MediaPlayerController();
    private static MultimediaGalleryController mgc = MultimediaGalleryController.getInstance();
    private static Scanner sc = new Scanner(System.in);
    private static int option = 0;
    private static boolean flag = false;
    private static String[] messErrorInt = new String[3];
    private static String name = "";

    public static void interfaz(User user) {
        messErrorInt[0] = "............ Error, no characters accepted, please enter a correct answer ............\n";
        messErrorInt[1] = " ............ Invalid option ............\n";
        messErrorInt[2] = "Sorry " + user.getUserName() + " ,but what you are looking for does not exist\n";

        System.out.printf("\n------------------ Hello %s ", user.getUserName() + " ------------------\n" + "  Look \n");

        userController.showListHistory().forEach(System.out::println);
        do {
            try {
                System.out.println("\n[ You can choose the functionality you want  ]\n"
                        + "1. Watch available movies and series\n"
                        + "2. Search\n"
                        + "3. Search by category\n"
                        + "4. Favorites\n"
                        + "5. Your prefile\n"
                        + "6. Leave");
                option = sc.nextInt();
                if (option > 0 && option <= 6) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                    sc.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println(messErrorInt[0]);
                flag = false;
                sc.nextLine();
            }
        } while (!flag);
        switch (option) {
            case 1:
                lookMultimedia(user);
                break;
            case 2:
                System.out.println("What do you want to look for?\n");
                searchName(user);

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:

                break;
            case 6:
                Runner.main(new String[] {});
                break;
            default:
                break;
        }
    }

    public static void validationUser() {
        InterfazVisitor.singIn();

    }

    public static void lookMultimedia(User user) {
        System.out.println("Movies:");

        for (Movie movie : administratorController.showMovie().values()) {
            System.out.println("Name: " + movie.getTitle());
        }
        System.out.println("Series:");
        for (Serie serie : administratorController.showSeries().values()) {
            System.out.println("Name: " + serie.getTitle());
        }
        do {
            try {
                System.out.println("Do you want to look?\n" + "1. Yes\n" + "2. No\n");
                option = sc.nextInt();
                sc.nextLine();
                if (option > 0 && option <= 2) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println(messErrorInt[0]);
                flag = false;
                sc.nextLine();
            }
        } while (!flag);
        switch (option) {
            case 1:
                System.out.println("What do you want to look at");
                searchName(user);
                break;
            case 2:
                interfaz(user);
                break;
        }

    }

    public static void searchName(User user) {
        name = sc.next();
        sc.nextLine();

        if (mgc.searchName(name) != null) {
            mgc.searchName(name).forEach(System.out::println);
            System.out.println("To confirm your search, enter the full name: ");
            name = sc.next();

            Movie movie = administratorController.findMovie(name);
            Serie serie = administratorController.findSerie(name);

            if (movie != null) {
                System.out.println("The movie: " + movie);
                play(movie, user);
            } else if (serie != null) {
                System.out.println("The serie: " + serie);
                play(serie, user);
            } else {
                System.out.println(messErrorInt[2]);
                interfaz(user);
            }

        } else {
            System.out.println(messErrorInt[2]);
            interfaz(user);
        }

    }

    public static void play(Multimedia multimedia, User user) {
        do {
            try {
                System.out.println("Do you want to reproduce?\n" + "1.Yes\n" + "2.No");
                option = sc.nextInt();
                sc.nextLine();
                if (option > 0 && option <= 2) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println(messErrorInt[0]);
                flag = false;
                sc.nextLine();
            }
        } while (!flag);
        switch (option) {
            case 1:
                mediaPlayerApp.reproduce(multimedia);
                if (multimedia instanceof Serie) {
                    userController.addListHistory(multimedia, user);
                    mpc.currentChapter(multimedia instanceof Serie);
                    System.out.println("");
                }
                if (multimedia instanceof Movie)
                    userController.addListHistory(multimedia, user);

                break;

            case 2:
                break;
        }

    }
}

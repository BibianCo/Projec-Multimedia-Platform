package co.edu.uptc.view;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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

        System.out.printf("\n------------------ Hello %s ", user.getUserName() + " ------------------\n");

        do {
            try {
                System.out.println("\n[ You can choose the functionality you want  ]\n"
                        + "1. Watch available movies and series\n"
                        + "2. Search\n"
                        + "3. Search by categories\n"
                        + "4. Favorites\n"
                        + "5. Your porfile");
                option = sc.nextInt();
                if (option > 0 && option <= 6) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                    sc.nextLine();
                }

            } catch (Exception e) {
                if (e instanceof InputMismatchException) {
                    System.out.println(messErrorInt[0]);
                } else if (e instanceof NoSuchElementException) {
                    if (sc.hasNextLine()) {
                        sc.nextLine();
                    }
                }
                flag = false;
            }
        } while (!flag);
        switch (option) {
            case 1:
                lookMultimedia(user);
                break;
            case 2:
                System.out.println("Write the name of the multimedia you want to search\n");
                searchName(user);

                break;
            case 3:
                searchCategory(user);
                break;
            case 4:
                favorites(user);
                break;
            case 5:
                porfile(user);
                break;
            default:
                break;
        }
    }

    private static void porfile(User user) {
        System.out.println("...........................................................");
        System.out.println(user.getFirstName() + "\n1.update data");
    }

    private static void favorites(User user) {
    }

    private static void searchCategory(User user) {
        System.out.println("...........................................................");

    }

    public static void validationUser() {
        InterfazVisitor.singIn();

    }

    public static void lookMultimedia(User user) {
        System.out.println("...........................................................");
        System.out.println("                     Movies");

        for (Movie movie : administratorController.showMovie().values()) {
            System.out.println(" Name: " + movie.getTitle());
        }
        System.out.println("\n                    Series");
        for (Serie serie : administratorController.showSeries().values()) {
            System.out.println(" Name: " + serie.getTitle()
                    + "\n Description: " + serie.getDescription()
                    + "\n Category: " + serie.getCategory()
                    + "\n Seasons: " + serie.getNumberSeasons()
                    + "\n Publication date: " + serie.getPublication());
        }
        do {
            try {
                System.out.println("\nDo you want to look?\n" + "1. Yes\n" + "2. No");
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
                System.out.println("..................................................."
                        + "\n Type the name of the multimedia you want to watch");
                searchName(user);
                break;
            case 2:
                interfaz(user);
                break;
        }

    }

    public static void searchName(User user) {
        name = sc.nextLine();
        if (mgc.searchName(name) != null) {
            for (Multimedia multimedia : mgc.searchName(name)) {
                System.out.println(" Name: " + multimedia.getTitle());
            }
            System.out.println("To confirm your search, enter the full name: ");
            name = sc.nextLine();

            Movie movie = administratorController.findMovie(name);
            Serie serie = administratorController.findSerie(name);

            if (movie != null) {
                System.out.println("The movie: " + movie.getTitle());
                System.out.println(" Name: " + movie.getTitle()
                        + "\n Description: " + movie.getDescription()
                        + "\n Category: " + movie.getCategory()
                        + "\n Publication date: " + movie.getPublication());
                play(movie, user);
            } else if (serie != null) {
                System.out.println("The serie: " + serie);
                System.out.println(" Name: " + serie.getTitle()
                        + "\n Description: " + serie.getDescription()
                        + "\n Category: " + serie.getCategory()
                        + "\n Seasons: " + serie.getNumberSeasons()
                        + "\n Publication date: " + serie.getPublication());
                play(serie, user);
            } else {
                System.out.println(messErrorInt[2]);
                interfaz(user);
            }

        } else

        {
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
                if (multimedia instanceof Serie) {

                    Serie serie = (Serie) multimedia;
                    mediaPlayerApp.reproduce(multimedia);
                    System.out.println("1 to see next chapter or 2. to see previous chapter");
                    option = sc.nextInt();
                    if (option == 1) {

                        boolean nextChapter = mpc.nextChapter(serie);
                        if (!nextChapter) {
                            System.out.println("there is no next chapter");
                        }
                    } else {
                        boolean previousChapter = mpc.previousChapter(serie);
                        if (!previousChapter) {
                            System.out.println("there is no previous chapter");

                        }
                    }
                } else if (multimedia instanceof Movie) {
                    mediaPlayerApp.reproduce(multimedia);
                    interfaz(user);
                    break;
                }
                break;

            case 2:
                interfaz(user);
                break;
        }

    }
}

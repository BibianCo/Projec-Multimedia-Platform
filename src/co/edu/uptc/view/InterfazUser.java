package co.edu.uptc.view;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.controller.MediaPlayerController;
import co.edu.uptc.controller.MultimediaGalleryController;
import co.edu.uptc.controller.PlanController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class InterfazUser {
    private static UserController userController = new UserController();
    private static AdministratorController administratorController = new AdministratorController();
    private static MediaPlayerApp mediaPlayerApp = new MediaPlayerApp();
    private static MediaPlayerController mpc = new MediaPlayerController();
    private static PlanController planController = new PlanController();
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
                System.out.println("\n[  You can choose the functionality you want  ]\n"
                        + "1. Watch available movies and series\n"
                        + "2. Search\n"
                        + "3. Search by categories\n"
                        + "4. Favorites\n"
                        + "5. Your porfile");
                option = sc.nextInt();
                if (option > 0 && option <= 5) {
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
        do {
            try {
                System.out.println(user.getFirstName() + "\n1.My data" + "\n2.Sing of" + "\n3.Leave");
                option = sc.nextInt();
                if (option > 0 && option <= 3) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                    sc.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println(messErrorInt[0]);
                sc.nextLine();
                flag = false;
            }
        } while (!flag);
        switch (option) {
            case 1:
                System.out.println("---------- My personal information ----------\n");
                userData(user);
                break;
            case 2:
                Runner.main(new String[] {});
                break;
            case 3:
                interfaz(user);
                break;
            default:
                break;
        }
    }

    private static void userData(User user) {
        String op = "";
        System.out.println(" Name:     " + user.getFirstName()
                + "\n User:     " + user.getUserName()
                + "\n Email:    " + user.getEmail()
                + "\n Password: " + user.getPassword()
                + "\n Plan: " + user.getPlan());
        do {
            System.out.println("Do you want to update data? \nyes\nno");
            op = sc.next();

            if (op.equals("yes")) {
                do {
                    try {
                        System.out.println("1. Name\n"
                                + "2. User\n"
                                + "3. Email\n"
                                + "4. Passmord\n"
                                + "5. Plan");
                        option = sc.nextInt();
                        if (option > 0 && option <= 5) {
                            flag = true;
                            sc.nextLine();
                        } else {
                            System.out.println(messErrorInt[1]);
                            flag = false;
                            sc.nextLine();
                        }

                    } catch (InputMismatchException e) {
                        System.out.println(messErrorInt[0]);
                        sc.nextLine();
                        flag = false;
                    }
                } while (!flag);
                switch (option) {
                    case 1:
                        do {
                            System.out.println("\nEnter your name to update");
                            name = sc.nextLine();
                            if (name.matches("[a-zA-Z\\s]+")) {
                                flag = true;
                            } else {
                                System.out
                                        .println(
                                                "............ It is possible that you entered numbers and characters, digital valid answer ............\n");
                                flag = false;
                            }
                        } while (!flag);
                        if (userController.updateUser(user.getEmail(), name, user.getPlan(), option)) {
                            System.out.println("uptada");
                        }
                        break;
                    case 2:
                        System.out.println("\nEnter your username to update");
                        name = sc.next();
                        userController.updateUser(user.getEmail(), name, user.getPlan(), option);
                        break;
                    case 3:
                        do {
                            System.out.println("\nEnter your email to update");
                            name = sc.next();
                            sc.nextLine();
                            if (!InterfazVisitor.emailValidation(name)) {
                                System.out.println("............ Invalid email ............\n");
                                flag = false;
                            } else {
                                flag = true;

                            }
                        } while (!flag);
                        userController.updateUser(user.getEmail(), name, user.getPlan(), option);
                        break;
                    case 4:
                        do {
                            System.out.println("\nEnter your password\n"
                                    + "[Minimum three characters, maximum twenty, minimum one uppercase letter, one lowercase letter and two numbers]");
                            name = sc.nextLine();
                            if (InterfazVisitor.passwordValidation(name)) {
                                flag = true;
                            } else {
                                System.out.println("............ Invalid password ............");
                                flag = false;
                            }
                        } while (!flag);
                        userController.updateUser(user.getEmail(), name, user.getPlan(), option);
                        break;
                    case 5:
                        do {
                            try {
                                System.out.println("\nChoose the plan you want to use");
                                // planController.getListPlans().forEach(System.out::println);
                                mediaPlayerApp.showPlans();
                                option = sc.nextInt();
                                sc.nextLine();
                                if (option > 0 && option < 100) {
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

                        Plan p1 = planController.assignTypePlan(option);
                        planController.assignUser(user.getUserName());
                        userController.updateUser(user.getEmail(), null, p1, option);
                        break;
                    default:
                        break;
                }
                System.out.println("---------- Your updated data ----------\n");
                userData(user);
                flag = true;

            } else if (op.equals("no")) {
                porfile(user);
                flag = true;

            } else {
                System.out.println(messErrorInt[1]);
                userData(user);
            }
        } while (!flag);

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
        // System.out.println("Movies:");

        mediaPlayerApp.showTableMovie(administratorController.showMovie());
        // for (Movie movie : administratorController.showMovie().values()) {
        // System.out.println("Name: " + movie.getTitle());
        // }
        System.out.println("");

        mediaPlayerApp.showTableSerie(administratorController.showSeries());

        // for (Serie serie : administratorController.showSeries().values()) {
        // System.out.println("Name: " + serie.getTitle());
        // }
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
            mediaPlayerApp.showMultimedia(name);

            System.out.println("To confirm your search, enter the full name: ");
            name = sc.nextLine();

            Movie movie = administratorController.findMovie(name);
            Serie serie = administratorController.findSerie(name);

            if (movie != null) {
                // System.out.println("The movie: " + movie.getTitle());
                mediaPlayerApp.showMultimedia(name);
                play(movie, user);

            } else if (serie != null) {
                mediaPlayerApp.showMultimedia(name);
                // System.out.println("The serie: " + serie);
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
                    playSeries(user, serie);

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

    public static void playSeries(User user, Serie serie) {
        int i = 0;
        String opt = "";
        int optionSeason = 0;
        for (Season season : serie.getSeasons()) {
            System.out.printf("Seasons %d: %s%n ", (i + 1), season.getDescription());
            i++;
        }

        System.out.println("You want to see the content of the season, write a for yes or b for no");
        do {
            opt = sc.next();
            if (opt.equals("a")) {

                try {
                    System.out.println("Enter the season number you want to see");
                    optionSeason = sc.nextInt();
                    sc.nextLine();
                    if (optionSeason < 0 || optionSeason > serie.getSeasons().size()) {
                        System.out.println("Invalid season number. Please enter a valid season number or 0 to exit:");

                    } else if (optionSeason > 0 || optionSeason <= serie.getSeasons().size()) {
                        playEpisodes(user, serie.getSeasons().get(optionSeason - 1), serie);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(messErrorInt[1]);
                }

            } else if (opt.equals("b")) {
                interfaz(user);

            } else {
                System.out.println(messErrorInt[1]);
                playSeries(user, serie);
            }
        } while (optionSeason != 0);
    }

    private static void playEpisodes(User user, Season season, Serie serie) {
        int i = 0;
        System.out.printf("Episodes in %s:%n", season.getDescription());
        for (Chapter chapter : season.getNumberOfChapters()) {
            System.out.printf("%d. %s%n", (i + 1), chapter.getTitle());
            i++;
        }
        System.out.println("Enter the number of the chapter you want to watch (or enter 0 to return to seasons):");
        int selectedEpisode;
        do {
            selectedEpisode = sc.nextInt();
            sc.nextLine(); // Consume el salto de línea

            if (selectedEpisode < 0 || selectedEpisode > season.getNumberOfChapters().size()) {
                System.out.println(
                        "Invalid episode number. Please enter a valid episode number or 0 to go back to seasons:");

            } else if (selectedEpisode > 0) {
                System.out.printf("Playing %s.%n",
                        season.getNumberOfChapters().get(selectedEpisode - 1).getTitle());
                mediaPlayerApp.reproduce(serie);
                System.out.println("1 to see next chapter or 2. to see previous chapter");
                option = sc.nextInt();
                if (option == 1) {

                    boolean nextChapter = mpc.nextChapter(serie);
                    if (!nextChapter) {
                        System.out.println("there is no next chapter");
                        playEpisodes(user, season, serie);
                    }
                } else {
                    boolean previousChapter = mpc.previousChapter(serie);
                    if (!previousChapter) {
                        System.out.println("there is no previous chapter");
                        playEpisodes(user, season, serie);

                    }
                }
            }
        } while (selectedEpisode != 0);
        playSeries(user, serie);
    }
}

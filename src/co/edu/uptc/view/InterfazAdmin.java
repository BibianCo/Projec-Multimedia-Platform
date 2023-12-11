package co.edu.uptc.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;

public class InterfazAdmin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdministratorController administratorController = new AdministratorController();

        int maxTries = 3;
        int triesPass = 0;
        int opc2, opc3, opc4, opc5;

        while (triesPass < maxTries) {
            System.out.println("Enter the name of the administrator:");
            String adminNameInput = sc.next();

            System.out.println("Enter the administrator's email:");
            String adminEmailInput = sc.next();

            System.out.println("Enter the administrator password");
            String adminPasswordInput = sc.next();

            if (administratorController.validateAdminCredentials(adminNameInput, adminEmailInput, adminPasswordInput)) {
                System.out.println("---------------------------------------------");
                System.out.println("Correct credentials.");
                System.out.println("---------------------------------------------");
                triesPass = 0;
                break;
            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Incorrect credentials.");
                System.out.println("---------------------------------------------");
                triesPass++;
            }
        }

        if (triesPass == maxTries) {
            System.out.println("The number of attempts has been exceeded");
            System.out.println("---------------------------------------------");
        }

        do {
            System.out.println("Choose the action you want to perform as Administrator");
            System.out.println("1.View Media Listing");
            System.out.println("2.Add Media");
            System.out.println("3.Delete Media");
            System.out.println("4.Update Media");
            System.out.println("5.Return to previous menu\n" +
                    "6. main");
            opc2 = sc.nextInt();

            switch (opc2) {
                case 1:
                    HashMap<Integer, Movie> movies = administratorController.showMovie();
                    // ArrayList<Movie> movies = administratorController.showMovies();
                    if (movies.isEmpty()) {
                        System.out.println("There are not movies to show.");
                    } else {
                        System.out.println("List of Films:");
                        for (Movie movie : administratorController.showMovie().values()) {
                            System.out.println("Títle: " + movie.getTitle());
                            System.out.println("Description: " + movie.getDescription());
                            System.out.println("Category: " + movie.getCategory());
                            System.out.println("Duration: " + movie.getDuration());
                            System.out.println("Publication Date:" + movie.getPublication());
                            System.out.println("---------------------------------------");
                        }
                    }

                    HashMap<Integer, Serie> series = administratorController.showSeries();
                    // ArrayList<Serie> series = administratorController.showSeries();
                    if (series.isEmpty()) {
                        System.out.println("There are not series to show");
                    } else {
                        System.out.println("List of Series:");
                        for (Serie serie : administratorController.showSeries().values()) {
                            System.out.println("Títle: " + serie.getTitle());
                            System.out.println("Description: " + serie.getDescription());
                            System.out.println("Category: " + serie.getCategory());
                            System.out.println("Publication Date:" + serie.getPublication());
                            System.out.println("---------------------------------------");
                        }
                    }
                    break;
                case 2:
                    do {
                        System.out.println("Choose the content you want to add");
                        System.out.println("1.Movies");
                        System.out.println("2.Series");
                        System.out.println("3.Exit");
                        opc3 = sc.nextInt();
                        switch (opc3) {
                            case 1:
                                System.out.println("Enter Movie details:");
                                sc.nextLine();
                                System.out.println("Title: ");
                                String title = sc.nextLine();
                                System.out.println("Description: ");
                                String description = sc.nextLine();
                                System.out.println("Category: ");
                                String category = sc.nextLine();
                                System.out.println("Publication Date (YYYY-MM-DD): ");
                                String publicationDateStr = sc.nextLine();

                                System.out.println("Duration: ");
                                int duration = sc.nextInt();

                                if (administratorController.addMovie(title, description, category,
                                        LocalDate.parse(publicationDateStr), duration)) {
                                    System.out.println("Movie added successfully!");
                                } else {
                                    System.out.println("Failed to add movie. Please check your input.");
                                }
                                break;

                            case 2:
                                sc.nextLine();
                                System.out.println("Enter Series details:");
                                System.out.print("Title: ");
                                String serieTitle = sc.nextLine();
                                System.out.print("Description: ");
                                String serieDescription = sc.nextLine();
                                System.out.print("Category: ");
                                String serieCategory = sc.nextLine();
                                System.out.print("Publication Date (YYYY-MM-DD): ");
                                String seriePublicationDateStr = sc.nextLine();

                                if (administratorController.addSerie(serieTitle, serieDescription, serieCategory,
                                        LocalDate.parse(seriePublicationDateStr))) {
                                    System.out.println("Serie added successfully!");
                                } else {
                                    System.out.println("Failed to add serie. Please check your input.");
                                }
                                break;
                            case 3:
                                System.out.println("Regresando al menu anterior");
                                System.out.println("---------------------------------------");
                                break;
                            default:
                                System.out.println("Elija una opcion valida");
                                break;
                        }
                    } while (opc3 != 3);
                    break;

                case 3:
                    do {
                        System.out.println("Choose the content you want to Delete");
                        System.out.println("1.Movies");
                        System.out.println("2.Series");
                        System.out.println("3.Exit");
                        opc4 = sc.nextInt();
                        switch (opc4) {
                            case 1:
                                HashMap<Integer, Movie> moviesToDelete = administratorController.showMovie();
                                // ArrayList<Movie> moviesToDelete = administratorController.showMovies();
                                if (moviesToDelete.isEmpty()) {
                                    System.out.println("No hay películas para mostrar.");
                                } else {
                                    System.out.println("Listado de Películas:");
                                    for (int i = 0; i < moviesToDelete.size(); i++) {
                                        System.out.println((i + 1) + ". Título: " + moviesToDelete.get(i).getTitle());
                                    }

                                    System.out.println("Seleccione el número de la película que desea eliminar:");
                                    int selectedMovieIndex = sc.nextInt();
                                    if (selectedMovieIndex > 0 && selectedMovieIndex <= moviesToDelete.size()) {
                                        Movie selectedMovie = moviesToDelete.get(selectedMovieIndex - 1);
                                        if (administratorController.deleteMovie(selectedMovie.getTitle())) {
                                            System.out.println(
                                                    "Película eliminada exitosamente: " + selectedMovie.getTitle());
                                        } else {
                                            System.out
                                                    .println("Error al eliminar la película. Película no encontrada.");
                                        }
                                    } else {
                                        System.out.println("Número de película seleccionado no válido.");
                                    }
                                }
                                break;
                            case 2:
                                HashMap<Integer, Serie> seriesToDelete = administratorController.showSeries();
                                // ArrayList<Serie> seriesToDelete = administratorController.showSeries();
                                if (seriesToDelete.isEmpty()) {
                                    System.out.println("No hay series para mostrar.");
                                } else {
                                    System.out.println("Listado de Series:");
                                    for (int i = 0; i < seriesToDelete.size(); i++) {
                                        System.out.println((i + 1) + ". Título: " + seriesToDelete.get(i).getTitle());
                                    }
                                    System.out.println("Seleccione el número de la serie que desea eliminar:");
                                    int serieChoice = sc.nextInt();
                                    if (serieChoice >= 1 && serieChoice <= seriesToDelete.size()) {
                                        String serieTitleToDelete = seriesToDelete.get(serieChoice - 1).getTitle();
                                        if (administratorController.deleteSerie(serieTitleToDelete) != null) {
                                            System.out.println("Serie eliminada exitosamente.");
                                        } else {
                                            System.out.println("Error al eliminar la serie.");
                                        }
                                    } else {
                                        System.out.println("Selección no válida.");
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Regresando al menu anterior");
                                System.out.println("---------------------------------------");
                                break;
                            default:
                                System.out.println("Elija una opcion valida");
                                break;
                        }
                    } while (opc4 != 3);
                    break;

                case 4:
                    do {
                        System.out.println("Choose the Content You Want to Update");
                        System.out.println("1.Movies");
                        System.out.println("2.Series");
                        System.out.println("3.Return to previous menu");
                        opc5 = sc.nextInt();
                        switch (opc5) {
                            case 1:
                                System.out.println("Enter Movie title to update: ");
                                sc.nextLine();
                                String movieTitleToUpdate = sc.nextLine();
                                Movie movieToUpdate = administratorController.findMovie(movieTitleToUpdate);

                                if (movieToUpdate != null) {
                                    System.out.println("Enter updated details:");
                                    System.out.print("Title: ");
                                    movieToUpdate.setTitle(sc.nextLine());
                                    System.out.print("Description: ");
                                    movieToUpdate.setDescription(sc.nextLine());
                                    System.out.print("Category: ");
                                    movieToUpdate.setCategory(sc.nextLine());
                                    System.out.print("Publication Date (YYYY-MM-DD): ");
                                    String updatedPublicationDateStr = sc.nextLine();
                                    movieToUpdate.setPublication(LocalDate.parse(updatedPublicationDateStr));
                                    System.out.print("Duration: ");
                                    movieToUpdate.setDuration(sc.nextInt());

                                    System.out.println("Movie updated successfully!");
                                } else {
                                    System.out.println("Movie not found. Please check the title.");
                                }
                                break;
                            case 2:
                                System.out.println("Enter Serie title to update: ");
                                sc.nextLine();
                                String serieTitleToUpdate = sc.nextLine();
                                Serie serieToUpdate = administratorController.findSerie(serieTitleToUpdate);

                                if (serieToUpdate != null) {
                                    System.out.println("Enter updated details:");
                                    System.out.print("Title: ");
                                    serieToUpdate.setTitle(sc.nextLine());
                                    System.out.print("Description: ");
                                    serieToUpdate.setDescription(sc.nextLine());
                                    System.out.print("Category: ");
                                    serieToUpdate.setCategory(sc.nextLine());
                                    System.out.print("Publication Date (YYYY-MM-DD): ");
                                    String updatedSeriePublicationDateStr = sc.nextLine();
                                    serieToUpdate.setPublication(LocalDate.parse(updatedSeriePublicationDateStr));

                                    System.out.println("Serie updated successfully!");
                                } else {
                                    System.out.println("Serie not found. Please check the title.");
                                }
                                break;
                            case 3:
                                System.out.println("Regresando al menu anterior");
                                break;

                            default:
                                System.out.println("Elija una opcion valida");
                                break;
                        }
                    } while (opc5 != 3);
                    break;
                case 5:
                    System.out.println("Regresando al menu anterior");
                    break;
                case 6:
                    Runner.main(new String[] {});
                    break;
                default:
                    System.out.println("Elija una opcion valida");
                    break;

            }
        } while (opc2 != 5);

    }

}

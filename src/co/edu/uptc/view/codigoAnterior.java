package co.edu.uptc.view;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class codigoAnterior {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AdministratorController administratorController = new AdministratorController();
        HashMap<Integer, Movie> movies = administratorController.showMovie();

        int maxTries = 3;
        int triesPass = 0;
        int opc3=0;
        int opc2=0;
        int opc4=0;
        int opc5=0;


        
        
        do { 
            try{
                System.out.println("Choose the action you want to perform as Administrator");
                System.out.println("-----------------------------------------------------------");
                System.out.println("1.View Media Listing");
                System.out.println("2.Add Media");
                System.out.println("3.Delete Media");
                System.out.println("4.Update Media");
                System.out.println("5.view User");
                System.out.println("6.Return to previous menu");
                opc2 = sc.nextInt();

                switch (opc2) {
                    case 1:
                        do {
                            try {
                                System.out.println("Choose the content view");
                                System.out.println("1. Movies");
                                System.out.println("2. Series");
                                System.out.println("3. Exit");
                                opc3 = sc.nextInt();
                    
                                switch (opc3) {
                                    case 1:
                                        if (movies.isEmpty()) {
                                            System.out.println("---------------------------------------\nThere are not movies to show:\n-------------------------------");
                                        } else {
                                            System.out.println("---------------------------------------\n List of Films:\n---------------------------------------");
                                            for (Movie movie : administratorController.showMovie().values()) {
                                                System.out.println("Title: " + movie.getTitle());
                                                System.out.println("Description: " + movie.getDescription());
                                                System.out.println("Category: " + movie.getCategory().getCategory());
                                                System.out.println("Duration: " + movie.getDuration());
                                                System.out.println("Publication Date:" + movie.getPublication());
                                                System.out.println("---------------------------------------");
                                            }
                                        }
                                        break;
                    
                                    case 2:
                                        HashMap<Integer, Serie> series = administratorController.showSeries();
                    
                                        if (series.isEmpty()) {
                                            System.out.println("---------------------------------------");
                                            System.out.println("There are no series to show");
                                            System.out.println("---------------------------------------");
                                        } else {
                                            System.out.println("---------------------------------------\n List of Series:\n---------------------------------------");
                    
                                            for (Serie serie : series.values()) {
                                                int index=1;
                                                 System.out.println("---------------------------------------\nSerie:"+(index++)+"\n---------------------------------------");
                                                System.out.println("Title: " + serie.getTitle());
                                                System.out.println("Description: " + serie.getDescription());
                                                System.out.println("Category: " + serie.getCategory());
                                                System.out.println("Publication Date: " + serie.getPublication());
                    
                                                // Display Seasons
                                                System.out.println("----------------------------------\nSeasons:\n----------------------------");
                                                int index2=1;
                                                for (Season season : serie.getSeasons()) {
                                                    System.out.println("----------------------------------\nSeason:"+(index2++)+"\n----------------------------");
                                                    System.out.println("  Season Number: " + season.getNumberSeason());
                                                    System.out.println("  Season Description: " + season.getDescription());
                                                    System.out.println("  Season Publication Date: " + season.getPublicationSeason());
                    
                                                    // Display Chapters
                                                    System.out.println("--------------------------\nChapters:\n----------------------------");
                                                    for (Chapter chapter : season.getNumberOfChapters()) {
                                                        System.out.println("    Chapter Title: " + chapter.getTitle());
                                                        System.out.println("    Chapter Duration: " + chapter.getDuration());
                                                        System.out.println("    Chapter Description: " + chapter.getDescription());
                                                        System.out.println("---------------------------------------");

                                                    }
                                                }
                    
                                                System.out.println("---------------------------------------");
                                            }
                                        }
                                        break;
                    
                                    case 3:
                                        System.out.println("Returning to the previous menu");
                                        System.out.println("---------------------------------------");
                                        break;
                    
                                    default:
                                        System.out.println("Choose a valid option");
                                        break;
                                }
                            
                            } catch (InputMismatchException e) {
                                System.out.println("---------------------------------------");
                                System.out.println("Please enter a valid number.");
                                System.out.println("---------------------------------------");
                                sc.nextLine();  
                            } catch (Exception e) {
                                System.out.println("---------------------------------------");
                                System.out.println("An error occurred: " + e.getMessage());
                                System.out.println("---------------------------------------");
                            }
                        } while (opc3 != 3);
                        break;
                    case 2:
                        do {
                            try {
                            System.out.println("---------------------------------------");
                            System.out.println("Choose the content you want to add");
                            System.out.println("1.Movies");
                            System.out.println("2.Series");
                            System.out.println("3.Exit");
                            opc3 = sc.nextInt();
                            System.out.println("---------------------------------------");
                            switch (opc3) {
                                case 1:
                                    System.out.println("Enter Movie details:");
                                    System.out.println("---------------------------------------");
                                    sc.nextLine();

                                    System.out.println("Title: ");
                                    String movieTitle = sc.nextLine();
                                    System.out.println("---------------------------------------------");

                                    boolean loop = true;

                                    do {
                                        try {
                                            System.out.println("Publication Date (YYYY-MM-DD): ");
                                            String moviePublicationDateStr = sc.nextLine();

                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                            LocalDate moviePublicationDate = LocalDate.parse(moviePublicationDateStr, formatter);

                                            Movie duplicateMovie = administratorController.findMovie(movieTitle);

                                            if (duplicateMovie != null) {
                                                System.out.println("---------------------------------------------------------------------------------------------------");
                                                System.out.println("A movie with the same title and publication date already exists. Cannot add duplicate movies.");
                                                System.out.println("---------------------------------------------------------------------------------------------------");
                                                loop = false; // Set loop flag to false to exit the loop
                                            } else {
                                                System.out.println("Description: ");
                                                String description = sc.nextLine();
                                                System.out.println("---------------------------------------------");

                                                // Display available categories
                                                System.out.println("Available Categories:");
                                                System.out.println(administratorController.showCategories());
                                                System.out.println("---------------------------------------------");

                                                int numCategory;
                                                do {
                                                    System.out.println("Choose a Category (Enter the category number): ");
                                                    numCategory = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.println("---------------------------------------------");

                                                    if (numCategory < 1 || numCategory > 5) {
                                                        System.out.println("Invalid category number. Please choose a valid category.");
                                                        System.out.println("---------------------------------------------");
                                                    }
                                                } while (numCategory < 1 || numCategory > 5);

                                                System.out.println("Duration: ");
                                                int duration = sc.nextInt();
                                                sc.nextLine();
                                                System.out.println("---------------------------------------------");

                                                if (administratorController.addMovie(movieTitle, description, numCategory, moviePublicationDate, duration)) {
                                                    System.out.println("Movie added successfully!");
                                                    System.out.println("---------------------------------------");
                                                } else {
                                                    System.out.println("Failed to add movie. Please check your input.");
                                                }

                                                loop = false; 
                                            }
                                        } catch (DateTimeParseException e) {
                                            System.out.println("---------------------------------------------");
                                            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                                            System.out.println("---------------------------------------------");
                                        } catch (Exception e) {
                                            System.out.println("An error occurred: " + e.getMessage());
                                            loop = false;
                                        }
                                    } while (loop);
                                    break;
                                case 2:
                                    sc.nextLine(); 
                                    System.out.println("Enter Series details:");
                                    System.out.print("Title: ");
                                    String serieTitle = sc.nextLine();
                                    System.out.println("---------------------------------------");
                                    System.out.print("Description: ");
                                    String serieDescription = sc.nextLine();
                                    System.out.println("---------------------------------------");
                                
                                
                                    System.out.println("Available Categories:");
                                    System.out.println(administratorController.showCategories());
                                
                                    int numCategory;
                                            do {
                                                System.out.println("Choose a Category (Enter the category number): ");
                                                numCategory = sc.nextInt();
                                                System.out.println("---------------------------------------------");

                                                if (numCategory < 1 || numCategory > 5) {
                                                    System.out.println("Invalid category number. Please choose a valid category.");
                                                    System.out.println("---------------------------------------------");
                                                }
                                            } while (numCategory < 1 || numCategory > 5);
                                
                                    try {
                                        System.out.print("Publication Date (YYYY-MM-DD): ");
                                        String seriePublicationDateStr = sc.next();
                                        System.out.println("---------------------------------------");
                                
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                        LocalDate seriePublicationDate = LocalDate.parse(seriePublicationDateStr, formatter);
                                
                                        if (administratorController.addSerie(serieTitle, serieDescription, numCategory, seriePublicationDate)) {
                                            System.out.println("Serie added successfully!");
                                            System.out.println("---------------------------------------");
                                
                                            // Add Seasons and Chapters
                                            while (true) {
                                                System.out.print("Season Number: ");
                                                int numberSeason = sc.nextInt();
                                                System.out.println("---------------------------------------");
                                                System.out.print("Season Description: ");
                                                sc.nextLine(); 
                                                String seasonDescription = sc.nextLine();
                                                System.out.println("---------------------------------------");
                                                System.out.print("Season Publication Date (YYYY-MM-DD): ");
                                                String seasonPublicationDateStr = sc.nextLine();
                                                System.out.println("---------------------------------------");
                                
                                                
                                                    LocalDate seasonPublicationDate = LocalDate.parse(seasonPublicationDateStr, formatter);
                                
                                                    if (administratorController.addSeason(serieTitle, seasonDescription, seasonPublicationDate,numberSeason)) {
                                                        System.out.println("Season added successfully!");
                                                        System.out.println("---------------------------------------");
                                                        
                                                    } else {
                                                        System.out.println("Failed to add season. Please check your input.");
                                                        break;
                                                    }
                                
                                                    // Add Chapters for the current Season
                                                    while (true) {
                                                        System.out.print("Chapter Title: ");
                                                        String chapterTitle = sc.nextLine();
                                                        System.out.println("---------------------------------------");
                                                        System.out.print("Chapter Duration: ");
                                                        int chapterDuration = sc.nextInt();
                                                        System.out.println("---------------------------------------");
                                                        System.out.print("Chapter Description: ");
                                                        String chapterDescription = sc.next();
                                                        System.out.println("---------------------------------------");
                                
                                                        if (administratorController.addChapter(serieTitle, numberSeason, chapterDuration, chapterDescription, chapterTitle)) {
                                                            System.out.println("Chapter added successfully!");
                                                            System.out.println("---------------------------------------");
                                                        } else {
                                                            System.out.println("Failed to add chapter. Please check your input.");
                                                            break;
                                                        }
                                                        sc.nextLine();
                                                        System.out.println("Do you want to add another Chapter to this Season? (yes/no): ");
                                                        String addAnotherChapterOption = sc.next().toLowerCase();
                                                        sc.nextLine();
                                                        System.out.println("---------------------------------------");
                                                        if (addAnotherChapterOption.equals("no")) {
                                                            break;
                                                        }
                                                    }
                                                    System.out.println("---------------------------------------");
                                                    System.out.println("Do you want to add another Season? (yes/no): ");
                                                    String addAnotherSeasonOption = sc.next().toLowerCase();
                                                    System.out.println("---------------------------------------");
                                                    if (addAnotherSeasonOption.equals("no")) {
                                                        break;
                                                    }
                                            }
                                        } else {
                                            System.out.println("Failed to add serie. Please check your input.");
                                        }
                                    } catch (DateTimeParseException e) {
                                        System.out.println("------------------------------------------------------------------------");
                                        System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                                        System.out.println("-------------------------------------------------------------------------");
                                    } catch (Exception e) {
                                        // Handle other exceptions if necessary
                                        System.out.println("An error occurred: " + e.getMessage());
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

                        } catch (InputMismatchException e) {
                                System.out.println("---------------------------------------");
                                System.out.println("Please enter a valid number.");
                                System.out.println("---------------------------------------");
                                sc.nextLine();  
                            } catch (Exception e) {
                                System.out.println("---------------------------------------");
                                System.out.println("An error occurred: " + e.getMessage());
                                System.out.println("---------------------------------------");
                            }
                        } while (opc3 != 3);
                        break;

                    case 3:
                        do {
                            try {
                                System.out.println("Choose the content you want to Delete");
                                System.out.println("1.Movies");
                                System.out.println("2.Series");
                                System.out.println("3.Exit");
                                System.out.println("---------------------------------------");
                                opc4 = sc.nextInt();
                                switch (opc4) {
                                    case 1:
                                    // Mostrar lista de películas
                                    HashMap<Integer, Movie> moviesToDelete = administratorController.showMovie();
                                
                                    if (moviesToDelete.isEmpty()) {
                                        System.out.println("---------------------------------------\nThere are no movies to show:\n-------------------------------");
                                    } else {
                                        System.out.println("---------------------------------------\nList of Movies:\n---------------------------------------");
                                
                                        // Mostrar la lista de películas
                                        int index = 1;
                                        for (Movie movie : moviesToDelete.values()) {
                                            System.out.println(index + ". Título: " + movie.getTitle());
                                            System.out.println("   Descripción: " + movie.getDescription());
                                            System.out.println("   Categoría: " + movie.getCategory());
                                            System.out.println("   Duración: " + movie.getDuration());
                                            System.out.println("   Fecha de Publicación: " + movie.getPublication());
                                            System.out.println("---------------------------------------");
                                            index++;
                                        }
                                
                                        System.out.println("Select the number of the movie you want to delete:");
                                
                                        if (sc.hasNextInt()) {
                                            int selectedMovieIndex = sc.nextInt();
                                
                                            if (selectedMovieIndex > 0 && selectedMovieIndex <= moviesToDelete.size()) {
                                                // Obtener la película seleccionada
                                                Movie selectedMovie = null;
                                
                                                int currentIndex = 1;
                                                for (Movie movie : moviesToDelete.values()) {
                                                    if (currentIndex == selectedMovieIndex) {
                                                        selectedMovie = movie;
                                                        break;
                                                    }
                                                    currentIndex++;
                                                }
                                
                                                // Verificar si la película seleccionada no es nula antes de realizar más operaciones
                                                if (selectedMovie != null) {
                                                    String selectedMovieTitle = selectedMovie.getTitle();
                                
                                                    // Llama al método para eliminar la película
                                                    if (administratorController.deleteMovie(selectedMovieTitle)) {
                                                        System.out.println("---------------------------------------");
                                                        System.out.println("Movie Successfully Deleted:" + selectedMovieTitle);
                                                        System.out.println("---------------------------------------");
                                                        
                                                        
                                                    } else {
                                                        System.out.println("---------------------------------------");
                                                        System.out.println("Error deleting the movie. Film Not Found.");
                                                    }
                                                } else {
                                                    System.out.println("---------------------------------------");
                                                    System.out.println("Error: Selected movie is null and void.");
                                                }
                                            } else {
                                                System.out.println("---------------------------------------");
                                                System.out.println("Invalid selected movie number.");
                                            }
                                        } else {
                                        
                                            System.out.println("Please enter a valid number.");
                                            sc.next();
                                        }
                                    }
                                    break;
                                

                                    case 2:
                                    HashMap<Integer, Serie> seriesToDelete = administratorController.showSeries();
                                    if (seriesToDelete.isEmpty()) {
                                        System.out.println("---------------------------------------");
                                        System.out.println("No hay series para mostrar.");
                                        System.out.println("---------------------------------------");
                                    } else {
                                        System.out.println("Listado de Series:");
                                        System.out.println("---------------------------------------");
                                        int index=1;
                                        for (HashMap.Entry<Integer, Serie> entry : seriesToDelete.entrySet()) {
                                            Serie serie = entry.getValue();
                                            System.out.println((entry.getKey() + 1) +"#"+(index++)+". Título: " + serie.getTitle());
                                        }
                                        System.out.println("Seleccione el número de la serie que desea gestionar:");
                                        int serieChoice = sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("---------------------------------------");
                                    
                                        if (serieChoice >= 1 && serieChoice <= seriesToDelete.size()) {
                                            Serie selectedSerie = new ArrayList<>(seriesToDelete.values()).get(serieChoice - 1);
                                            String serieTitleToDelete = selectedSerie.getTitle();
                                    
                                            // Mostrar opciones
                                            System.out.println("1. Eliminar toda la serie");
                                            System.out.println("2. Eliminar una temporada");
                                            System.out.println("3. Eliminar un capítulo");
                                            System.out.println("Seleccione la opción deseada:");
                                    
                                            int deleteOption = sc.nextInt();
                                            sc.nextLine(); 
                                            System.out.println("---------------------------------------");
                                    
                                            switch (deleteOption) {
                                                case 1:
                                                    // Eliminar toda la serie
                                                    Serie deletedSerie = administratorController.deleteSerie(serieTitleToDelete);
                                                    if (deletedSerie != null) {
                                                        System.out.println("Serie eliminada exitosamente.");
                                                        System.out.println("---------------------------------------");
                                                    } else {
                                                        System.out.println("Error al eliminar la serie.");
                                                        System.out.println("---------------------------------------");
                                                    }
                                                break;
                                            
                                                case 2:
                                                    // Mostrar temporadas disponibles
                                                    System.out.println("Temporadas disponibles:");
                                                    System.out.println("---------------------------------------");
                                                        int seasonIndex = 1;
                                                        for (Season season : selectedSerie.getSeasons()) {
                                                            System.out.println(seasonIndex + ". Número de Temporada: " + season.getNumberSeason());
                                                            seasonIndex++;
                                                        }
                                    
                                                    System.out.println("Seleccione el número de la temporada que desea eliminar:");
                                                    int seasonNumberToDelete = sc.nextInt();
                                                    sc.nextLine(); // Consumir el carácter de nueva línea después de leer el entero
                                                    System.out.println("---------------------------------------");
                                    
                                                    Season deletedSeason = administratorController.deleteSeason(serieTitleToDelete, seasonNumberToDelete);
                                                    if (deletedSeason != null) {
                                                        System.out.println("Temporada eliminada exitosamente.");
                                                        System.out.println("---------------------------------------");
                                                    } else {
                                                        System.out.println("Error al eliminar la temporada.");
                                                        System.out.println("---------------------------------------");
                                                    }
                                                    break;
                                                case 3:

                                                    // Display available seasons
                                                    System.out.println("Available Seasons:");
                                                    System.out.println("---------------------------------------");
                                                    int seasonIndex2 = 1;
                                                    for (Season season : selectedSerie.getSeasons()) {
                                                        System.out.println(seasonIndex2 + ". Season Number: " + season.getNumberSeason());
                                                        seasonIndex2++;
                                                    }
                                                
                                                    System.out.println("Select the number of the season in which you want to delete a chapter:");
                                                    int seasonNumberToDelete2 = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.println("---------------------------------------");
                                                
                                                    // Display chapters available in the selected season
                                                    Season selectedSeasonToDelete = selectedSerie.getSeasonByNumber(seasonNumberToDelete2);
                                                
                                                    if (selectedSeasonToDelete != null) {
                                                        System.out.println("Chapters available in Season " + seasonNumberToDelete2 + ":");
                                                        System.out.println("---------------------------------------");
                                                        int chapterIndex = 1;
                                                        for (Chapter chapter : selectedSeasonToDelete.getNumberOfChapters()) {
                                                            System.out.println(chapterIndex + ". Chapter Title: " + chapter.getTitle());
                                                            chapterIndex++;
                                                        }
                                                
                                                        System.out.println("Select the number of the chapter you want to delete:");
                                                        int chapterNumberToDelete = sc.nextInt();
                                                        sc.nextLine(); 
                                                        System.out.println("---------------------------------------");
                                                
                                                        // Attempt to delete the chapter
                                                        Chapter deletedChapter = administratorController.deleteChapter(serieTitleToDelete, seasonNumberToDelete2, chapterNumberToDelete);

        
                                                        if (deletedChapter != null) {
                                                        System.out.println("Capítulo eliminado exitosamente.");
                                                        System.out.println("---------------------------------------");
                                                        } else {
                                                        System.out.println("Error al eliminar el capítulo.");
                                                        System.out.println("---------------------------------------");
                                                        }
                                                    }
                                                    
                                                    break;
                                                default:
                                                    System.out.println("Opción no válida.");     
                                                    System.out.println("---------------------------------------");
                                            }
                                        }
                                    }
                                }
                            } catch (InputMismatchException e) {
                                    System.out.println("---------------------------------------");
                                    System.out.println("Please enter a valid number.");
                                    System.out.println("---------------------------------------");
                                    sc.nextLine();  
                                } catch (Exception e) {
                                    System.out.println("---------------------------------------");
                                    System.out.println("An error occurred: " + e.getMessage());
                                    System.out.println("---------------------------------------");
                                }
                        } while (opc4 != 3);
                        break;                         
                    case 4:
                        boolean error;
                        do {
                        error = false;
                        try{
                            System.out.println("Choose the Content You Want to Update");
                            System.out.println("1.Movies");
                            System.out.println("2.Series");
                            System.out.println("3.Return to previous menu");
                            opc5 = sc.nextInt();
                            System.out.println("---------------------------------------");
                            switch (opc5) {
                                case 1:
                                    if (movies.isEmpty()) {
                                        System.out.println("There are not movies to show.");
                                        System.out.println("---------------------------------------");
                                    } else {
                                    System.out.println("List of Films:");
                                    System.out.println("---------------------------------------");
                                    for (Integer index : movies.keySet()) {
                                        Movie movie = movies.get(index);
                                        System.out.println(index + ". Title: " + movie.getTitle());
                                    }
                        
                                    System.out.print("Enter the code of the movie to update: ");
                                    int selectedMovieIndex = sc.nextInt();
                                    System.out.println("---------------------------------------");
                                    
                                    if (movies.containsKey(selectedMovieIndex)) {
                                        Movie movieToUpdate = movies.get(selectedMovieIndex);
                        
                                        System.out.println("Enter updated details:");
                                        System.out.println("1. Update Title");
                                        System.out.println("2. Update Description");
                                        System.out.println("3. Update Category");
                                        System.out.println("4. Update Publication Date");
                                        System.out.print("Choose an option: ");
                                        int updateOption = sc.nextInt();
                                        System.out.println("---------------------------------------");
                        
                                        String updatedValue = "";
                                        LocalDate updatedPublicationDate = null;
                        
                                        switch (updateOption) {
                                            case 1:
                                                System.out.print("Enter updated title: ");
                                                updatedValue = sc.next();
                                                movieToUpdate.setTitle(updatedValue);
                                                System.out.println("---------------------------------------");
                                                break;
                                            case 2:
                                                System.out.print("Enter updated description: ");
                                                updatedValue = sc.next();
                                                movieToUpdate.setDescription(updatedValue);
                                                System.out.println("---------------------------------------");
                                                break;
                                            case 3:

                                                System.out.println("Available Categories:");
                                                System.out.println(administratorController.showCategories());

                                                System.out.print("Enter the index of the category to update: ");
                                                int selectedCategoryIndex = sc.nextInt();
                                                sc.nextLine();  
                                                System.out.println("---------------------------------------");

                                                // Validate the selected category index
                                                if (administratorController.validationCategory(selectedCategoryIndex)) {

                                                    Category selectedCategory = administratorController.findCategory(selectedCategoryIndex);
                                                    movieToUpdate.setCategory(selectedCategory);

                                                    System.out.println("Movie updated successfully:");
                                                    System.out.println(movieToUpdate);

                                                    System.out.println("Category updated successfully for the selected series.");
                                                } else {
                                                    System.out.println("Invalid category index. Please select a valid category.");
                                                }

                                                break;
                                            case 4:
                                                try {
                                                    System.out.print("Enter updated publication date (YYYY-MM-DD): ");
                                                    String updatedPublicationDateStr = sc.next();
                                                    updatedPublicationDate = LocalDate.parse(updatedPublicationDateStr);
                                                    movieToUpdate.setPublication(updatedPublicationDate);
                                                    System.out.println("---------------------------------------");
                                                } catch (DateTimeParseException e) {
                                                    System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                                                    System.out.println("---------------------------------------");
                                                    return;
                                                } catch (Exception e) {
                                                    // Handle other exceptions if necessary
                                                    System.out.println("An error occurred: " + e.getMessage());
                                                    System.out.println("---------------------------------------");
                                                    return;
                                                }
                                                break;
                                                 default:
                                                System.out.println("Invalid option");
                                                System.out.println("---------------------------------------");
                                                break;
                                        }
                        
                                        Movie updatedMovie = administratorController.updateMovie(updateOption, movieToUpdate.getTitle(), updatedValue, updatedPublicationDate);

                        
                                        if (updatedMovie != null) {
                                            System.out.println("---------------------------------------");
                                            System.out.println("Movie updated successfully!");
                                            System.out.println("---------------------------------------");
                                        } else {
                                            System.out.println("---------------------------------------");
                                            System.out.println("Failed to update movie. Please check your input.");
                                            System.out.println("---------------------------------------");
                                        }
                                    } else {
                                        System.out.println("---------------------------------------");
                                        System.out.println("Invalid movie index. Please enter a valid index.");
                                        System.out.println("---------------------------------------");
                                    }
                                }
                                 break;
                                case 2:
                                HashMap<Integer, Serie> seriesToUpdate = administratorController.showSeries();
                                if (seriesToUpdate.isEmpty()) {
                                    System.out.println("No hay series para mostrar.");
                                    System.out.println("---------------------------------------");
                                } else {
                                    System.out.println("Listado de Series:");
                                    System.out.println("---------------------------------------");
                                    int index = 1;
                                    for (Serie serie : seriesToUpdate.values()) {
                                        System.out.println(index + ". Título: " + serie.getTitle());
                                        System.out.println("   Descripción: " + serie.getDescription());
                                        System.out.println("   Categoría: " + serie.getCategory());
                                        System.out.println("   Fecha de Publicación: " + serie.getPublication());
                                        System.out.println("---------------------------------------");
                                        }

                                    System.out.println("Seleccione el número de la serie que desea actualizar:");
                                    int serieChoiceToUpdate = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("---------------------------------------");
                                
                                    if (serieChoiceToUpdate >= 1 && serieChoiceToUpdate <= seriesToUpdate.size()) {
                                        Serie serieToUpdate = new ArrayList<>(seriesToUpdate.values()).get(serieChoiceToUpdate - 1);
                                
                                        System.out.println("Enter updated details:");
                                        System.out.println("1. Update Serie Details");
                                        System.out.println("2. Update Season Details");
                                        System.out.println("3. Update Chapter Details");
                                        System.out.print("Enter your choice: ");
                                        int updateOption = sc.nextInt();
                                        System.out.println("---------------------------------------");
                                        sc.nextLine();
                                        switch (updateOption) {
                                            case 1:
                                                System.out.println("Select the information you want to update:");
                                                System.out.println("1. Title");
                                                System.out.println("2. Description");
                                                System.out.println("3. Category");
                                                System.out.println("4. Publication Date");
                                                System.out.println("5. Exit ");
                                                
                                                System.out.print("Enter your choice : ");
                                                int userChoice = Integer.parseInt(sc.nextLine());
                                                System.out.println("---------------------------------------");
                                                
                                                switch (userChoice) {
                                                    case 1:
                                                        // Update Title
                                                        System.out.print("New Title: ");
                                                        serieToUpdate.setTitle(sc.nextLine());
                                                        System.out.println("---------------------------------------");
                                                        System.out.println("Serie title updated successfully!");
                                                        System.out.println("---------------------------------------");
                                                        break;
                                                
                                                    case 2:
                                                        // Update Description
                                                        System.out.print("New Description: ");
                                                        serieToUpdate.setDescription(sc.nextLine());
                                                        System.out.println("---------------------------------------");
                                                        System.out.println("Serie description updated successfully!");
                                                        System.out.println("---------------------------------------");
                                                        break;
                                                
                                                    case 3:
                                                        // Update Category
                                                        System.out.println("Available Categories:");
                                                        System.out.println(administratorController.showCategories());

                                                        System.out.print("Enter the index of the category to update: ");
                                                        int selectedCategoryIndex = sc.nextInt();
                                                        sc.nextLine();  
                                                        System.out.println("---------------------------------------");

                                                break;
                                                
                                                    case 4:
                                                        // Update Publication Date
                                                        System.out.print("New Publication Date (YYYY-MM-DD): ");
                                                        String updatedSeriePublicationDateStr = sc.nextLine();
                                                        System.out.println("---------------------------------------");
                                                        serieToUpdate.setPublication(LocalDate.parse(updatedSeriePublicationDateStr));
                                                        System.out.println("Serie publication date updated successfully!");
                                                        System.out.println("---------------------------------------");
                                                        break;
                                                
                                                    case 5:
                                                        // Exit without updates
                                                        System.out.println("No updates were made.");
                                                        System.out.println("---------------------------------------");
                                                        break;
                                                
                                                    default:
                                                        System.out.println("Invalid choice. No updates were made.");
                                                        System.out.println("---------------------------------------");
                                                        break;
                                                }
                                            break;
                                            case 2:
                                                System.out.println("Available Seasons:");
                                                System.out.println("---------------------------------------");
                                                int seasonIndex = 1;
                                                for (Season season : serieToUpdate.getSeasons()) {
                                                    System.out.println(seasonIndex + ". Season " + season.getNumberSeason());
                                                    System.out.println("   Description: " + season.getDescription());
                                                    System.out.println("   Publication Date: " + season.getPublicationSeason());
                                                    // Mostrar más detalles según sea necesario
                                                    System.out.println("---------------------------------------");
                                                    seasonIndex++;
                                                }
                    
                                                System.out.print("Enter Season Number to update: ");
                                                int seasonNumberToUpdate = sc.nextInt();
                                                sc.nextLine(); 
                                                
                                                Season seasonToUpdate = serieToUpdate.getSeasonByNumber(seasonNumberToUpdate);
                                                
                                                if (seasonToUpdate != null) {
                                                    // Prompt for update options
                                                    System.out.println("Select the information you want to update for Season " + seasonToUpdate.getNumberSeason() + ":");
                                                    System.out.println("1. Description");
                                                    System.out.println("2. Publication Date");
                                                    System.out.println("3. Exit ");
                                                
                                                    System.out.print("Enter your choice (1-3): ");
                                                    int userChoice2= Integer.parseInt(sc.nextLine());
                                                    System.out.println("---------------------------------------");
                                                
                                                    switch (userChoice2) {
                                                        case 1:
                                                            // Update Description
                                                            System.out.print("New Description for Season: ");
                                                            seasonToUpdate.setDescription(sc.nextLine());
                                                            System.out.println("Season description updated successfully!");
                                                            System.out.println("---------------------------------------");
                                                            break;
                                                
                                                        case 2:
                                                            // Update Publication Date
                                                            System.out.print("New Publication Date for Season (YYYY-MM-DD): ");
                                                            String updatedSeasonPublicationDateStr = sc.nextLine();
                                                            seasonToUpdate.setPublicationSeason(LocalDate.parse(updatedSeasonPublicationDateStr));
                                                            System.out.println("Season publication date updated successfully!");
                                                            System.out.println("---------------------------------------");
                                                            break;
                                                
                                                        case 3:
                                                            System.out.println("No updates were made for Season " + seasonToUpdate.getNumberSeason() + ".");
                                                            break;
                                                
                                                        default:
                                                            System.out.println("Invalid choice. No updates were made for Season " + seasonToUpdate.getNumberSeason() + ".");
                                                    }
                                                    } else {
                                                    System.out.println("Season not found. Please check the number.");
                                                    }
                                                break;
                                            case 3:
                                                // Mostrar temporadas disponibles
                                                    System.out.println("Available Seasons:");
                                                    System.out.println("---------------------------------------");

                                                    for (Season season : serieToUpdate.getSeasons()) {
                                                        System.out.println("Season " + season.getNumberSeason() + ": " + season.getDescription());
                                                    }

                                                    // Obtener la entrada del usuario para la actualización de la temporada
                                                    System.out.print("Enter Season Number to update: ");
                                                    int seasonNumberToUpdate1 = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.println("---------------------------------------");

                                                    Season seasonToUpdate1 = serieToUpdate.getSeasonByNumber(seasonNumberToUpdate1);

                                                    if (seasonToUpdate1 != null) {
                                                        // Mostrar capítulos de la temporada seleccionada
                                                        System.out.println("Available Chapters for Season " + seasonToUpdate1.getNumberSeason() + ":");
                                                        System.out.println("---------------------------------------");

                                                        int chapterIndex = 1;
                                                        for (Chapter chapter : seasonToUpdate1.getNumberOfChapters()) {
                                                            System.out.println(chapterIndex + ". Title: " + chapter.getTitle());
                                                            System.out.println("   Duration: " + chapter.getDuration());
                                                            System.out.println("   Description: " + chapter.getDescription());
                                                            System.out.println("---------------------------------------");
                                                            chapterIndex++;
                                                        }

                                                        System.out.print("Select the number of the chapter you want to update: ");
                                                        int selectedChapterIndex = sc.nextInt();
                                                        sc.nextLine(); 
                                                        System.out.println("---------------------------------------");

                                                        if (selectedChapterIndex > 0 && selectedChapterIndex <= seasonToUpdate1.getNumberOfChapters().size()) {
                                                            Chapter chapterToUpdate = seasonToUpdate1.getNumberOfChapters().get(selectedChapterIndex - 1);

                                                            
                                                            System.out.println("Chapter selected for update: " + chapterToUpdate.getTitle());
                                                            System.out.println("1. Update Duration");
                                                            System.out.println("2. Update Description");
                                                            System.out.print("Enter your choice (1-2): ");
                                                            int updateChoice = sc.nextInt();
                                                            sc.nextLine(); 
                                                            System.out.println("---------------------------------------");

                                                            switch (updateChoice) {
                                                                case 1:
                                                                    // Actualizar duración del capítulo
                                                                    System.out.print("Enter new Duration for Chapter: ");
                                                                    int newDuration = sc.nextInt();
                                                                    sc.nextLine(); 
                                                                    chapterToUpdate.setDuration(newDuration);
                                                                    System.out.println("Chapter duration updated successfully!");
                                                                    System.out.println("---------------------------------------");
                                                                    break;

                                                                case 2:
                                                                    // Actualizar descripción del capítulo
                                                                    System.out.print("Enter new Description for Chapter: ");
                                                                    String newDescription = sc.nextLine();
                                                                    chapterToUpdate.setDescription(newDescription);
                                                                    System.out.println("Chapter description updated successfully!");
                                                                    System.out.println("---------------------------------------");
                                                                    break;

                                                                default:
                                                                    System.out.println("Invalid choice. No updates were made for Chapter.");
                                                                    System.out.println("---------------------------------------");
                                                                    break;
                                                            }
                                                        } else {
                                                            System.out.println("Invalid chapter number selected.");
                                                            System.out.println("---------------------------------------");
                                                        }
                                                    } else {
                                                        System.out.println("Season not found. Please check the season number.");
                                                        System.out.println("---------------------------------------");
                                                    }
                                                    break;
                                            default:
                                                System.out.println("Invalid option.");
                                                break;
                                        }
                                    } else {
                                        System.out.println("---------------------------------------");
                                        System.out.println("Serie not found. Please check the title.");
                                        System.out.println("---------------------------------------");
                                    }
                                }
                                case 3:
                                    System.out.println("Regresando al menu anterior");
                                    System.out.println("---------------------------------------");
                                    break;

                                default:
                                    System.out.println("Elija una opcion valida");
                                    System.out.println("---------------------------------------");
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            error = true;
                            System.out.println("Please enter a valid number.");
                            System.out.println("---------------------------------------");
                            sc.next();  
                        } catch (Exception e) {
                            error = true;
                            System.out.println("An error occurred: " + e.getMessage());
                            System.out.println("---------------------------------------");
                        }
                        if (error) {
                            System.out.println("---------------------------------------");
                            System.out.println("Error Please check your entry.");
                            System.out.println("---------------------------------------");
                        }
                        } while (opc5 != 3);
                        break;
                    case 5:
                            System.out.println("List of Users:");
                            ArrayList<User> users = administratorController.showUserList();
                            
                            if (users.isEmpty()) {
                                System.out.println("No users found.");
                            } else {
                                for (User user : users) {
                                    System.out.println(user.toString()); 
                                }
                            }
                            System.out.println("---------------------------------------");
                        break;
                    case 6:
                        System.out.println("Regresando al menu anterior");
                        System.out.println("---------------------------------------");
                        break;

                    default:
                        System.out.println("Elija una opcion valida");
                        System.out.println("---------------------------------------");
                        break;
                        
                }
        } catch (InputMismatchException e) {
            System.out.println("---------------------------------------");
            System.out.println("Please enter a valid number.");
            System.out.println("---------------------------------------");
            sc.nextLine();  
            } catch (Exception e) {
            System.out.println("---------------------------------------");
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("---------------------------------------");
            }

        } while (opc2 != 5);
        
    }
}




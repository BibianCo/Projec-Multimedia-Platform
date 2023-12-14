package co.edu.uptc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.uptc.model.Administrator;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class AdministratorController {
    // MultimediaGallery multimediaGallery = new MultimediaGallery();
    private Administrator administrator;
    private MultimediaGalleryController mgc = MultimediaGalleryController.getInstance();
    // private MultimediaGallery multimedia;

    public AdministratorController() {
        administrator = new Administrator("admin1", "admin1@uptc.edu.co", "2244");
        categories.add(new Category("Action"));
        categories.add(new Category("Animated"));
        categories.add(new Category("Comedy"));
        categories.add(new Category("Romance"));
        categories.add(new Category("Terror"));
        addMovie("son como niños", "aventura con adam sambler", 1, LocalDate.of(2002, 10, 10), 60);
        addMovie("los juegos del hambre", "aventura de distopia", 2, LocalDate.of(2002, 10, 10), 120);
        addMovie("rapidos y furiosos 6", "actores como la roca", 3, LocalDate.of(2002, 10, 10), 60);
        addSerie("merlina", "una niña rara", 1, LocalDate.of(2001, 10, 10));
        addSerie("elite", "serie de niños ricos", 4, LocalDate.of(2001, 10, 10));
        addSerie("la casa de papel", "robo y asaltos", 2, LocalDate.of(2001, 10, 10));
        addSeason("merlina", "temporada 1", LocalDate.of(2010, 10, 31), 8);
        addChapter("merlina", 1, 60, "hjd sjhdks", "la casa de los muertos");
        addChapter("merlina", 1, 45, "ijdsdnlqs", "una vezperdidos");
        addChapter("merlina", 1, 30, "dhauksds", "locos de miedo");
        // addSeason("merlina", "temporada 2", LocalDate.of(2001, 01, 10), 4);
        // addChapter("merlina", 2, 60, "hjd sjhdks", "la casa de los muertos");
        // addChapter("merlina", 2, 45, "ijdsdnlqs", "una vezperdidos");
        // addChapter("merlina", 2, 30, "dhauksds", "locos de miedo");

    }

    public boolean validateAdminCredentials(String adminName, String adminEmail, String adminPassword) {
        return administrator.getFirstName().equals(adminName) &&
                administrator.getEmail().equals(adminEmail) &&
                administrator.getPassword().equals(adminPassword);
    }

    public boolean updateAdministratorDetails(int option, String newDetails) {
        switch (option) {
            case 1:
                administrator.setFirstName(newDetails);
                return true;
            case 2:
                // emailValidation();
                administrator.setEmail(newDetails);
                return true;
            case 3:
                // passwordValidation
                administrator.setPassword(newDetails);
                return true;
            default:
                return false;

        }
    }

    public boolean addSerie(String title, String description, int numCategory, LocalDate publication) {
        int code = mgc.GenerateKey(true);

        Serie serie = new Serie(title, description, findCategory(numCategory), publication, false, code);
        if (!title.isEmpty() && !description.isEmpty() && numCategory > 0) {
            this.mgc.getInstance().multimediaGallery.setSeries(mgc.GenerateKey(true),
                    serie);
            this.mgc.getInstance().multimediaGallery.getCategories().get(numCategory - 1).setSeries(serie);
            serie.setCode(code);
            return true;
        }
        return false;
    }

   

      public Serie findSerie(String title) {
        if (!title.isEmpty()) {// title.equals("");
            for (Serie serie : this.mgc.getInstance().multimediaGallery.getSeries().values()) {
                if (serie.getTitle().equals(title)) {
                    return serie;
                }
            }
        }
        return null;
    }

   public Serie deleteSerie(String title) {
    HashMap<Integer, Serie> series = this.mgc.getInstance().multimediaGallery.getSeries();

    // Buscar la serie por título
    for (Map.Entry<Integer, Serie> entry : series.entrySet()) {
        Serie serie = entry.getValue();
        if (serie.getTitle().equalsIgnoreCase(title)) {
            // Eliminar la serie y devolverla
            series.remove(entry.getKey());
            return serie;
        }
    }

    // Si no se encuentra la serie
    return null;
}
    
    
    
    

    public boolean addMovie(String title, String description, int numCategory, LocalDate publication, int duration) {
        if (validationCategory(numCategory)) {
            int code = mgc.GenerateKey(false);
            Movie m1 = new Movie(title, description, findCategory(numCategory), publication, false, code, duration);
            if (m1 != null) {
                this.mgc.getInstance().multimediaGallery.setMovies(code, m1);
                this.mgc.getInstance().multimediaGallery.getCategories().get(numCategory - 1).setMovies(m1);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMovie(String title) {
        Movie mov = findMovie(title);
    
        if (mov != null) {
            MultimediaGalleryController mgcInstance = this.mgc.getInstance();
            HashMap<Integer, Movie> movies = mgcInstance.multimediaGallery.getMovies();
            movies.remove(mov.getCode());
            
            return true;
        }
        return false;
    }   

    public void updateMoviesList(HashMap<Integer, Movie> movies) {

        HashMap<Integer, Movie> updatedMovies = new HashMap<>();
        int index = 1;
        for (Movie movie : movies.values()) {
            updatedMovies.put(index, movie);
            index++;
        }
        movies = updatedMovies;
    }


    public Movie findMovie(String title) {
        HashMap<Integer, Movie> movies = mgc.getInstance().multimediaGallery.getMovies();

        for (Integer movieId : movies.keySet()) {
            Movie movie = movies.get(movieId);

            // Utilizar trim() para eliminar espacios al principio y al final del título
            if (movie.getTitle().trim().equalsIgnoreCase(title.trim())) {
                // Se llama el método de showMovie para imprimir la película encontrada
                return movie;
            }
        }
        return null;
    }

    public Movie updateMovie(int options, String title, String dataUpdate, LocalDate publication) {
        Movie movie = findMovie(title);
    
        if (movie == null) {
            System.out.println("Error: Movie not found.");
            return null;
        }
    
        switch (options) {
            case 1:
                if (title != null && !title.equals(movie.getTitle())) {
                    movie.setTitle(title);
                }
                break;
            case 2:
                if (dataUpdate != null && !dataUpdate.equals(movie.getDescription())) {
                    movie.setDescription(dataUpdate);
                }
                break;
            case 3:
                if (dataUpdate != null && !dataUpdate.equals(movie.getCategory().getName())) {
                    movie.setCategory(new Category(dataUpdate));
                }
                break;
            case 4:
                if (publication != null && !publication.equals(movie.getPublication())) {
                    movie.setPublication(publication);
                }
                break;
            default:
                System.out.println("Error: Invalid option.");
                return null;
        }
    
        return movie;
    }

    

    public Serie updateSerie(int option, String titleSerie, String newD, LocalDate newPublication, int newSeason) {
        Serie serie = findSerie(titleSerie);

        if (!titleSerie.isEmpty()) {
            switch (option) {
                case 1:
                    serie.setDescription(newD);
                    return serie;
                case 2:
                    serie.setCategory(new Category(newD));
                    return serie;
                case 3:
                    serie.setPublication(newPublication);
                    return serie;
                case 4:
                    serie.setNumberSeasons(newSeason);

                    return serie;
                default:
                    return null;
            }
        }
        return null;
    }


    public Season deleteSeason(String title, int numberSeason) {
        Serie serie = findSerie(title);
    
        if (serie != null) {
            int adjustedIndex = numberSeason - 1;
    
            if (adjustedIndex >= 0 && adjustedIndex < serie.getSeasons().size()) {
                return serie.getSeasons().remove(adjustedIndex);
            }
        }
    
        return null;
    }
    
    

    public HashMap<Integer, Movie> showMovie() {
        return this.mgc.getInstance().multimediaGallery.getMovies();
    }

    public HashMap<Integer, Serie> showSeries() {
        return this.mgc.getInstance().multimediaGallery.getSeries();
    }

    public boolean addCategory(String newCategory) {
        for (Category category : mgc.multimediaGallery.getCategories()) {
            if (category.getCategory().equals(newCategory)) {
                return false;
            }
        }
        mgc.multimediaGallery.getCategories().add(new Category(newCategory));
        return true;
    }

    public Category findCategory(int numCategory) { // 1. Action, 2. Animated, 3. Comedy, 4. Romance, 5. Terror...
        return mgc.multimediaGallery.getCategories().get(numCategory - 1);
    }

    public boolean validationCategory(int numCategory) {
        try {
            mgc.multimediaGallery.getCategories().get(numCategory - 1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String showCategories() {
        String categoryStr = "";
        for (int i = 0; i < mgc.multimediaGallery.getCategories().size(); i++) {
            categoryStr = categoryStr + "\n" + (i + 1) + ". " + mgc.multimediaGallery.getCategories().get(i).toString();
        }
        return categoryStr;
    }

    public String showMoviesCategory(int numCategory) {
        return mgc.multimediaGallery.getCategories().get(numCategory - 1).getMovies().toString();
    }

    public String showSeriesCategory(int numCategory) {
        return mgc.multimediaGallery.getCategories().get(numCategory - 1).getSeries().toString();
    }

    public boolean addSeason(String serieTitle, String description, LocalDate publicationSeason, int numberSeason) {

        for (HashMap.Entry<Integer, Serie> serie : mgc.getInstance().multimediaGallery.getSeries().entrySet()) {
            if (serie.getValue().getTitle().equals(serieTitle)) {
                for (int index = 0; index < serie.getValue().getSeasons().size(); index++) {
                    if (serie.getValue().getSeasons().get(index).getDescription().equals(description)) {
                        return false;
                    }
                }
                serie.getValue().addSeason(new Season(description, publicationSeason, numberSeason));
                return true;
            }
        }
        return false;
    }

    public Chapter deleteChapter(String serieTitle, int numberSeason, int chapterNumber) {
        Serie serie = findSerie(serieTitle);

        if (serie != null) {
            for (int i = 0; i < serie.getSeasons().size(); i++) {
                if (serie.getSeasons().get(i).getNumberSeason() == numberSeason) {
                    List<Chapter> chapters = serie.getSeasons().get(i).getNumberOfChapters();
                    if (chapterNumber >= 1 && chapterNumber <= chapters.size()) {
                        return chapters.remove(chapterNumber - 1);
                    }
                }
            }
        }
        return null;
    }
 

    public boolean addChapter(String serieTitle, int numberSeason, int duration, String description, String title) {
        for (HashMap.Entry<Integer, Serie> serie : mgc.getInstance().multimediaGallery.getSeries().entrySet()) {
            if (serie.getValue().getTitle().equals(serieTitle)) {
                for (int i = 0; i < serie.getValue().getSeasons().size(); i++) {
                    if (serie.getValue().getSeasons().get(i).getNumberSeason() == numberSeason) {
                        for (int j = 0; j < serie.getValue().getSeasons().get(i).getNumberOfChapters().size(); j++) {
                            if (serie.getValue().getSeasons().get(i).getNumberOfChapters().get(i).getTitle()
                                    .equals(title)) {
                                return false;
                            }
                        }
                        serie.getValue().getSeasons().get(i).addChapter(new Chapter(duration, description, title));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    

    // newValue puede ser para la nueva Description o para el nuevo Title.
    // ChapterTitle es para verificar la existencia del capítulo
    // Option 1, actualiza Description; 2, actualiza Duration; 3, actualiza Title

    public boolean updateChapter(String seriesTitle, int numberSeason, String chapterTitle, int option, String newValue,
            int duration) {
        for (HashMap.Entry<Integer, Serie> serie : mgc.getInstance().multimediaGallery.getSeries().entrySet()) {
            if (serie.getValue().getTitle().equals(seriesTitle)) {
                for (int i = 0; i < serie.getValue().getSeasons().size(); i++) {
                    if (serie.getValue().getSeasons().get(i).getNumberSeason() == numberSeason) {
                        for (int j = 0; j < serie.getValue().getSeasons().get(i).getNumberOfChapters().size(); j++) {
                            if (serie.getValue().getSeasons().get(i).getNumberOfChapters().get(j)
                                    .getTitle() == chapterTitle) {
                                switch (option) {
                                    case 1:
                                        serie.getValue().getSeasons().get(i).getNumberOfChapters().get(j)
                                                .setDescription(newValue);
                                        return true;
                                    case 2:
                                        serie.getValue().getSeasons().get(i).getNumberOfChapters().get(j)
                                                .setDuration(duration);
                                        return true;
                                    case 3:
                                        serie.getValue().getSeasons().get(i).getNumberOfChapters().get(j)
                                                .setTitle(newValue);
                                        return true;
                                    default:
                                        return false;
                                }
                            }
                        }
                    }
                }

            }
        }
        return false;
    }

    public ArrayList<User> showUserList() {

        return administrator.getUsers();
    }
<<<<<<< HEAD
=======

    public String showMovieTitle() {
        String moviList = "";
        for (int i = 0; i < mgc.getInstance().multimediaGallery.getMovies().size(); i++) {
            moviList = moviList + "\n" + mgc.getInstance().multimediaGallery.getMovies().get(i).getTitle();
        }
        return moviList;
    }

    public String showSerieTitle() {
        String moviList = "";
        for (int i = 0; i < mgc.getInstance().multimediaGallery.getSeries().size(); i++) {
            moviList = moviList + "\n" + mgc.getInstance().multimediaGallery.getSeries().get(i).getTitle();
        }
        return moviList;
    }
}
>>>>>>> d8f9a6543b5e0455ec529b27e31456125367e08a

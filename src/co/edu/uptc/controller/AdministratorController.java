package co.edu.uptc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void cargaMultimedia() {
        addMovie("son como niños", "aventura con adam sambler", 1, LocalDate.of(2002,
                10, 10), 60);
        addMovie("los juegos del hambre", "aventura de distopia", 2,
                LocalDate.of(2002, 10, 10), 120);
        addMovie("rapidos y furiosos 6", "actores como la roca", 3,
                LocalDate.of(2002, 10, 10), 60);
        addSerie("merlina", "una niña rara", 1, LocalDate.of(2001, 10, 10));
        addSerie("elite", "serie de niños ricos", 4, LocalDate.of(2001, 10, 10));
        addSerie("la casa de papel", "robo y asaltos", 2, LocalDate.of(2001, 10,
                10));

        addSeason("merlina", "temporada 1", LocalDate.of(2010, 10, 31), 1);
        addChapter("merlina", 1, 60, "hjd sjhdks", "la casa de los muertos");
        addChapter("merlina", 1, 45, "ijdsdnlqs", "una vezperdidos");
        addChapter("merlina", 1, 30, "dhauksds", "locos de miedo");
        addSeason("merlina", "temporada 2", LocalDate.of(2001, 01, 10), 2);
        addChapter("merlina", 2, 60, "hjd sjhdks", "la casa de los muertos");
        addChapter("merlina", 2, 45, "ijdsdnlqs", "una vezperdidos");
        addChapter("merlina", 2, 30, "dhauksds", "locos de miedo");

    }

    public AdministratorController() {
        administrator = new Administrator("admin1", "admin1@uptc.edu.co", "2244");

        // addMovie("son como niños", "aventura con adam sambler", 1, LocalDate.of(2002,
        // 10, 10), 60);
        // addMovie("los juegos del hambre", "aventura de distopia", 2,
        // LocalDate.of(2002, 10, 10), 120);
        // addMovie("rapidos y furiosos 6", "actores como la roca", 3,
        // LocalDate.of(2002, 10, 10), 60);
        // addSerie("merlina", "una niña rara", 1, LocalDate.of(2001, 10, 10));
        // addSerie("elite", "serie de niños ricos", 4, LocalDate.of(2001, 10, 10));
        // addSerie("la casa de papel", "robo y asaltos", 2, LocalDate.of(2001, 10,
        // 10));
        // addSeason("merlina", "temporada 1", LocalDate.of(2010, 10, 31), 8);
        // addChapter("merlina", 1, 60, "hjd sjhdks", "la casa de los muertos");
        // addChapter("merlina", 1, 45, "ijdsdnlqs", "una vezperdidos");
        // addChapter("merlina", 1, 30, "dhauksds", "locos de miedo");
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
        if (movie != null) {
            switch (options) {
                case 1:
                    movie.setTitle(title);
                    break;
                case 2:
                    movie.setDescription(dataUpdate);
                    break;
                case 3:
                    movie.setCategory(new Category(dataUpdate));
                    break;
                case 4:
                    movie.setPublication(publication);
                    break;

                default:
                    break;
            }
            return movie;
        }
        return null;

    }

    public boolean deleteMovie(String title) {
        Movie mov = findMovie(title);

        if (mov != null) {
            this.mgc.multimediaGallery.getMovies().remove(mov);
            return true;
        }
        return false;
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

        if (title != null) {
            Serie serie = findSerie(title);
            this.mgc.getInstance().multimediaGallery.getSeries().remove(findSerie(title));
            return serie;
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

    public List<Movie> getMoviesByCategory(int numCategory) {
        List<Movie> seriesList = new ArrayList<>();

        // Verificar si el índice de la categoría es válido
        if (numCategory > 0 && numCategory <= mgc.multimediaGallery.getCategories().size()) {
            Category category = mgc.multimediaGallery.getCategories().get(numCategory - 1);
            seriesList.addAll(category.getMovies());
        }

        return seriesList;
    }

    public List<Serie> getSeriesByCategory(int numCategory) {
        List<Serie> seriesList = new ArrayList<>();
        if (numCategory > 0 && numCategory <= mgc.multimediaGallery.getCategories().size()) {
            Category category = mgc.multimediaGallery.getCategories().get(numCategory - 1);
            seriesList.addAll(category.getSeries());
        }

        return seriesList;
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
        for (HashMap.Entry<Integer, Serie> entry : mgc.getInstance().multimediaGallery.getSeries().entrySet()) {
            Serie serie = entry.getValue();
            if (serie.getTitle().equals(serieTitle)) {
                for (Season season : serie.getSeasons()) {
                    if (season.getNumberSeason() == numberSeason) {
                        for (Chapter chapter : season.getNumberOfChapters()) {
                            if (chapter.getTitle().equals(title)) {
                                return false;
                            }
                        }
                        season.addChapter(new Chapter(duration, description, title));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Season deleteSeason(String title, int numberSeason) {

        Serie serie = findSerie(title);

        if (serie != null) {
            serie.getSeasons().get(numberSeason);

        }

        return null;
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

    public String showMovieTitle() {
        StringBuilder moviList = new StringBuilder();
        for (Movie movie : mgc.getInstance().multimediaGallery.getMovies().values()) {
            moviList.append("\n").append(movie.getTitle());
        }
        return moviList.toString();
    }

    public String showSerieTitle() {
        StringBuilder serieList = new StringBuilder();
        for (Serie serie : mgc.getInstance().multimediaGallery.getSeries().values()) {
            serieList.append("\n").append(serie.getTitle());
        }
        return serieList.toString();
    }
}
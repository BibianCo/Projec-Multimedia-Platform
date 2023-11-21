package co.edu.uptc.model;

import java.io.Serial;
import java.util.ArrayList;

public class MultimediaGallery {
    private ArrayList<Serie> series = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Movie movie) {
        this.movies.add(movie);
    }

}

package co.edu.uptc.model;

import java.util.HashMap;

public class MultimediaGallery {

    private HashMap<Integer, Serie> series;
    private HashMap<Integer, Movie> movies;

    public MultimediaGallery() {
        series = new HashMap<>();
        movies = new HashMap<>();
    }

    public HashMap<Integer, Serie> getSeries() {
        return series;
    }

    public void setSeries(Integer key, Serie value) {
        series.put(key, value);
    }

    public HashMap<Integer, Movie> getMovies() {
        return movies;
    }

    public void setMovies(Integer key, Movie value) {
        movies.put(key, value);
    }



}

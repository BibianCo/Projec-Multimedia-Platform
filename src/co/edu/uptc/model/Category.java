package co.edu.uptc.model;

import java.util.ArrayList;

public class Category {

    private String category;
    private ArrayList<Movie> movies;
    private ArrayList<Serie> series;

    public Category(String category) {
        this.category = category;
        movies = new ArrayList<>();
        series = new ArrayList<>();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Movie movie) {
        this.movies.add(movie);
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(Serie serie) {
        this.series.add(serie);
    }

    @Override
    public String toString() {
        return category;
    }
}

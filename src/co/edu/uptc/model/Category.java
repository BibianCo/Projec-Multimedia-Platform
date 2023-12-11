package co.edu.uptc.model;

import java.util.ArrayList;

public class Category {

    private String category;
    private ArrayList<Movie> movies;
    private ArrayList<Serie> series;

    public Category(String category) {
        this.category = category;
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

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return category;
    }
}

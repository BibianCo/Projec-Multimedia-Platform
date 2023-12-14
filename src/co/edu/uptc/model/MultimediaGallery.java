package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.HashMap;

public class MultimediaGallery {

    private HashMap<Integer, Serie> series;
    private HashMap<Integer, Movie> movies;
    private ArrayList<Category> categories;

    public MultimediaGallery() {
        series = new HashMap<>();
        movies = new HashMap<>();
        categories = new ArrayList<>();
        categories.add(new Category("Action"));
        categories.add(new Category("Animated"));
        categories.add(new Category("Comedy"));
        categories.add(new Category("Romance"));
        categories.add(new Category("Terror"));
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



    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}

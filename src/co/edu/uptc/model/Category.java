package co.edu.uptc.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private Movie movie;

    public Category(int id, String name, Movie movie) {
        this.id = id;
        this.name = name;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + " id=" + id + ", Name='" + name + '}';
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}

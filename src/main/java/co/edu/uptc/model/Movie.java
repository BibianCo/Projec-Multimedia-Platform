package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie extends Multimedia {
    private int duration;
    private ArrayList<Category> categories;
    private String fileMovie;

    public Movie() {

    }

    public Movie(int id, String title, String synopsis, LocalDate releaseDate) {
        super(id, title, synopsis, releaseDate);
    }

    public Movie(int id, String title, String synopsis, LocalDate releaseDate, int duration,
            ArrayList<Category> categories) {
        super(id, title, synopsis, releaseDate);
        this.duration = duration;
        this.categories = categories;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return super.getTitle();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getFileMovie() {
        return fileMovie;
    }

    public void setFileMovie(String fileMovie) {
        this.fileMovie = fileMovie;
    }

}
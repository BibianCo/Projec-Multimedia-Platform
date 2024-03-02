package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie extends Multimedia {
    private int duration;
    private ArrayList<Category> categories;

    public Movie() {

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
        return "\nId: " + super.getId() + "\nTitle: " + super.getTitle() + "\nSynopsis: "
                + super.getSynopsis() + "\nDate: " + super.getReleaseDate() + "\nDuration: " + duration;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}
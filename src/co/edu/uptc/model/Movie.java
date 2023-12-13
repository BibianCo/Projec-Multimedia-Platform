package co.edu.uptc.model;

import java.time.LocalDate;

public class Movie extends Multimedia {
    private int duration;

    public Movie(String title, String description, Category category, LocalDate publication, boolean reproduce,
            int code, int duration) {
        super(title, description, category, publication, reproduce, code);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "\nTitle: " + super.getTitle() + "\nDescription: " + super.getDescription() + "\nLaunching: "
                + super.getPublication() + "\nDuration: " + duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
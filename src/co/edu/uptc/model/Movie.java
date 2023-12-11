package co.edu.uptc.model;

import java.time.LocalDate;

public class Movie extends Multimedia {
    private int duration;

    public Movie(String title, String description, Category category, LocalDate publication, boolean reproduce) {
        super(title, description, category, publication, reproduce);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie [duration=" + duration + "]" + super.toString();
    }

}
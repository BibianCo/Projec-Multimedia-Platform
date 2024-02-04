package co.edu.uptc.model;

import java.util.Date;

public class Movie extends Multimedia {
    private int duration;

    public Movie(int id, String title, String synopsis, Date releaseDate) {
        super(id, title, synopsis, releaseDate);
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
        return "\nId: " + super.getId()+"\nTitle: " + super.getTitle() + "\nSynopsis: "
                + super.getSynopsis() + "\nDate: " + super.getReleaseDate() +"\nDuration: " + duration;
    }

}
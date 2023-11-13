package co.edu.uptc.model;

public class Movie {
    private int duration;
    private String director;
    private Multimedia multimedia;

    public Movie(int duration, String director, Multimedia multimedia, String title, String description,
            String category) {
        this.duration = duration;
        this.director = director;
        this.multimedia = new Multimedia(title, description, category);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

}

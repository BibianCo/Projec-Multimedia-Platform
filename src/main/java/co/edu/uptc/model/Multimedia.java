package co.edu.uptc.model;

import java.time.LocalDate;

public abstract class Multimedia extends Entity {

    protected String title, synopsis;
    protected LocalDate releaseDate;

    public Multimedia() {
    }

    public Multimedia(int id, String title, String synopsis, LocalDate releaseDate) {
        super(id);
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

}

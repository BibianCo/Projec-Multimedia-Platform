package co.edu.uptc.model;

import java.util.Date;

public abstract class Multimedia extends Entity {

    protected String title, synopsis;
    protected Date releaseDate;

    public Multimedia() {
    }

    public Multimedia(int id, String title, String synopsis, Date releaseDate) {
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}

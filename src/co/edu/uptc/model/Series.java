package co.edu.uptc.model;

public class Series {
    private int numberSeasons;
    private String director;
    private Multimedia multimedia;

    public Series(int numberSeasons, String director, Multimedia multimedia, String title, String description,
            String category) {
        this.numberSeasons = numberSeasons;
        this.director = director;
        this.multimedia = new Multimedia(title, description, category);
    }

    public int getNumberSeasons() {
        return numberSeasons;
    }

    public void setNumberSeasons(int numberSeasons) {
        this.numberSeasons = numberSeasons;
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

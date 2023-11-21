package co.edu.uptc.model;

import java.util.ArrayList;

public class Season {
    private ArrayList<Chapter> numberOfChapters;
    private String description;
    private String publicationSeason;
    private String numberSeason;

    public Season(String description, String publicationSeason, String numberSeason) {
        this.description = description;
        this.publicationSeason = publicationSeason;
        this.numberSeason = numberSeason;
        this.numberOfChapters = new ArrayList<>();
    }

    public void addChapter(Chapter chapter) {
        numberOfChapters.add(chapter);
    }

    public ArrayList<Chapter> getNumberOfChapters() {
        return numberOfChapters;
    }

    public void setNumberOfChapters(ArrayList<Chapter> numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationSeason() {
        return publicationSeason;
    }

    public void setPublicationSeason(String publicationSeason) {
        this.publicationSeason = publicationSeason;
    }

    @Override
    public String toString() {
        return "Season [numberOfChapters=" + numberOfChapters + ", description=" + description + ", publicationSeason="
                + publicationSeason + "]";
    }

    public String getNumberSeason() {
        return numberSeason;
    }

    public void setNumberSeason(String numberSeason) {
        this.numberSeason = numberSeason;
    }

}

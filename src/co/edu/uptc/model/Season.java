package co.edu.uptc.model;

public class Season {
    private int numberOfChapters;
    private String description;
    private String publicationSeason;
    private Chapter chapter;

    public Season(int numberOfChapters, String description, String publicationSeason) {
        this.numberOfChapters = numberOfChapters;
        this.description = description;
        this.publicationSeason = publicationSeason;
        chapter = new Chapter(numberOfChapters, description, publicationSeason);
    }

    public int getNumberOfChapters() {
        return numberOfChapters;
    }

    public void setNumberOfChapters(int numberOfChapters) {
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

}

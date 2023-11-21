package co.edu.uptc.model;

public class Multimedia {
    private String title;
    private String description;
    private String category;
    private int publication;
    private boolean reproduce;

    public Multimedia(String title, String description, String category, int publication) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.publication = publication;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public boolean isReproduce() {
        return reproduce;
    }

    public void setReproduce(boolean reproduce) {
        this.reproduce = reproduce;
    }

    @Override
    public String toString() {
        return "Multimedia [title=" + title + ", description=" + description + ", category=" + category
                + ", publication=" + publication + ", reproduce=" + reproduce + "]";
    }

}

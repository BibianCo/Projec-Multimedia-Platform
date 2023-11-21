package co.edu.uptc.model;

import java.time.LocalDate;

public class Multimedia {
    private String title;
    private String description;
    private String category;
    private LocalDate publication;
    private boolean reproduce;

    public Multimedia(String title, String description, String category, LocalDate publication, boolean reproduce) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.publication = publication;
        this.reproduce = reproduce;
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

    public boolean isReproduce() {
        return reproduce;
    }

    public void setReproduce(boolean reproduce) {
        this.reproduce = reproduce;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

}

package co.edu.uptc.model;

import java.time.LocalDate;

public class Multimedia {
    private String title;
    private String description;
    private Category category;
    private LocalDate publication;
    private boolean reproduce;
    private int code;

    public Multimedia(String title, String description, Category category, LocalDate publication, boolean reproduce,
            int code) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.publication = publication;
        this.code = code;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return title;
    }
}

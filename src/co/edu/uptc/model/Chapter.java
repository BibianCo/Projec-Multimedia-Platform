package co.edu.uptc.model;

public class Chapter {
    private int duration;
    private String description;
    private String title;

    public Chapter(int duration, String description, String title) {
        this.duration = duration;
        this.description = description;
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    @Override
    public String toString() {
        return "Chapter [duration=" + duration + ", description=" + description + ", title=" + title + "]";
    }

}

package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Serie extends Multimedia {

    private ArrayList<Season> seasons;
    private ArrayList<Category> categories;

    public Serie() {
        super();
    }

    public Serie(int id, String title, String synopsis, LocalDate releaseDate) {
        super(id, title, synopsis, releaseDate);
    }

    public Serie(int id, String title, String synopsis, LocalDate releaseDate, ArrayList<Category> categories,
            ArrayList<Season> seasons) {
        super(id, title, synopsis, releaseDate);
        this.categories = categories;
        this.seasons = seasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return super.getTitle();
    }

}

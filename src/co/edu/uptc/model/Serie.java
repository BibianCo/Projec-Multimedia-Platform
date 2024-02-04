package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;

public class Serie extends Multimedia {

    private ArrayList<Season> seasons;
    private ArrayList<Category> categories;

    public Serie() {
        super();
    }

    public Serie(int id, String title, String synopsis, Date releaseDate, ArrayList<Category> categories, ArrayList<Season> seasons) {
        super(id, title, synopsis, releaseDate);
        this.seasons = seasons;
        this.categories = categories;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories
    }

}

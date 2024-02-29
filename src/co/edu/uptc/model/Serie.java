package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;

public class Serie extends Multimedia {

    private ArrayList<Season> seasons;
    private ArrayList<Category> categories;

    public Serie() {
        super();
    }

    public Serie(int id, String title, String synopsis, Date releaseDate, ArrayList<Category> categories) {
        super(id, title, synopsis, releaseDate);
        this.categories = categories;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Season season) {
        seasons.add(season);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {

    }

}

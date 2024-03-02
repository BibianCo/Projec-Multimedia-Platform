package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Serie extends Multimedia {

    private ArrayList<Season> seasons = new ArrayList<>();
    private ArrayList<Category> categories;

    public Serie() {
        super();
    }

    public Serie(int id, String title, String synopsis, LocalDate releaseDate, ArrayList<Category> categories) {
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

package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;

public class Serie extends Multimedia {

    private ArrayList<Category> categories;

    public Serie(int id, String title, String synopsis, Date releaseDate) {
        super(id, title, synopsis, releaseDate);
        this.categories = new ArrayList<>();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}

package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Date;

public class Serie extends Multimedia {

    private ArrayList<Season> seasons;

    public Serie(int id, String title, String synopsis, Date releaseDate) {
        super(id, title, synopsis, releaseDate);
        this.seasons = new ArrayList<>();

    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

}

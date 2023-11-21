package co.edu.uptc.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Serie extends Multimedia {
    private int numberSeasons;
    private ArrayList<Season> seasons;

    public Serie(String title, String description, String category, int publication) {
        super(title, description, category, publication);
        this.numberSeasons = numberSeasons;
        seasons = new ArrayList<>();

    }

    public void addSeason(Season season) {
        seasons.add(season);
    }

    public int getNumberSeasons() {
        return numberSeasons;
    }

    public void setNumberSeasons(int numberSeasons) {
        this.numberSeasons = numberSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "Serie [numberSeasons=" + numberSeasons + ", seasons=" + seasons + "]" + super.toString();
    }

}
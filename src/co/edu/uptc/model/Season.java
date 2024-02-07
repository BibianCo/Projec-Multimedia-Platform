package co.edu.uptc.model;

import java.util.ArrayList;

public class Season extends Entity {
    private int number;
    private ArrayList<Episode> episodes;

    public Season() {

    }

    public Season(int id, int number, ArrayList<Episode> episodes) {
        super(id);
        this.number = number;
        this.episodes = episodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Season{" + "Id= " + id + ", Number= " + number + "}";
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

}
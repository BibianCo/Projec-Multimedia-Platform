package co.edu.uptc.model;

import java.util.ArrayList;

public class Season extends Entity {
    private int number;
    private int idSerie;
    private ArrayList<Episode> episodes;

    public Season() {

    }

    public Season(int id, int number, int idSerie, ArrayList<Episode> episodes) {
        super(id);
        this.number = number;
        this.idSerie = idSerie;
        this.episodes = episodes;
    }

    public Season(int id, int number, int idSerie) {
        super(id);
        this.number = number;
        this.idSerie = idSerie;
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

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    @Override
    public String toString() {
        return "Season [number=" + number + ", idSerie=" + idSerie + ", episodes=" + episodes + "]";
    }

}
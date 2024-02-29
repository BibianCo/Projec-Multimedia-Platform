package co.edu.uptc.model;

public class Episode extends Entity {

    private int number;
    private int duration;
    private int idSeason;

    public Episode() {
    }

    public Episode(int id, int number, int duration, int idSeason) {
        super(id);
        this.number = number;
        this.duration = duration;
        this.idSeason = idSeason;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(int idSeason) {
        this.idSeason = idSeason;
    }

    @Override
    public String toString() {
        return "Episode [number=" + number + ", duration=" + duration + ", idSeason=" + idSeason + "]";
    }

}

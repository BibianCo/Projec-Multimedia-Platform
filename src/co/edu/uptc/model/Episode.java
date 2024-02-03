package co.edu.uptc.model;

public class Episode {

    private int id;
    private int number;
    private int duration;

    public Episode() {
    }

    public Episode(int id, int number, int duration) {
        this.id = id;
        this.number = number;
        this.duration = duration;
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

    @Override
    public String toString() {
        return "Episode [id=" + id + ", number=" + number + ", duration=" + duration + "]";
    }

}

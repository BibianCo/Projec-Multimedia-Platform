package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Plan extends Entity {

    private String namePlan;
    private String description;
    private int price;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int duration;
    private ArrayList<User> users;

    public Plan(int id, String namePlan, String description, int price, LocalDate dateStart, LocalDate dateEnd,
            int duration, ArrayList<User> users) {
        super(id);
        this.namePlan = namePlan;
        this.description = description;
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.duration = duration;
        this.users = users;
    }

    public String getNamePlan() {
        return namePlan;
    }

    public void setNamePlan(String namePlan) {
        this.namePlan = namePlan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Plan [namePlan=" + namePlan + ", description=" + description + ", price=" + price + ", dateStart="
                + dateStart + ", dateEnd=" + dateEnd + ", duration=" + duration + ", users=" + users + "]";
    }

}

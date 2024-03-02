package co.edu.uptc.model;

import java.time.LocalDate;

public class Subscription extends Entity {

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Plan plan;
    private User user;

    public Subscription(int id, Plan plan, User user) {
        super(id);
        this.plan = plan;
        this.user = user;
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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subscription [dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", plan=" + plan + ", user=" + user
                + "]";
    }

}

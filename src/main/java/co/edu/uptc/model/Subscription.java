package co.edu.uptc.model;

import java.time.LocalDate;

public class Subscription extends Entity {

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Plan plan;

    public Subscription(int id, Plan plan) {
        super(id);
        this.plan = plan;
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

    @Override
    public String toString() {
        return "Subscription [dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", plan=" + plan + ", user=" + "]";
    }

}

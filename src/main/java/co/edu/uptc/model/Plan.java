package co.edu.uptc.model;

public class Plan extends Entity {

    private String namePlan;
    private String description;
    private int price;
    private int duration;

    public Plan(int id, String namePlan, String description, int price, int duration) {
        super(id);
        this.namePlan = namePlan;
        this.description = description;
        this.price = price;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Plan [namePlan=" + namePlan + ", description=" + description + ", price=" + price + ", duration="
                + duration + "]";
    }

}

package co.edu.uptc.model;

public class Role extends Entity {

    private String name;

    public Role() {
    }

    public Role(int id, String name) {
        super(id);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

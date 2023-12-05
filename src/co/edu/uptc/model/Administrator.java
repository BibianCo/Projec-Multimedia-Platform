package co.edu.uptc.model;

import java.util.ArrayList;

public class Administrator extends Person {
    private int code;
    private ArrayList<User> users = new ArrayList<>();

    public Administrator(String name, String email, String password) {
        super(name, email, password);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}

package co.edu.uptc.model;

import java.util.ArrayList;

public class Administrator extends Person {
    private int code;

    private static Administrator administrator;
    private ArrayList<User> users = new ArrayList<>();

    public static Administrator getInstance2() {
        if (administrator == null) {
            administrator = new Administrator();
        }
        return administrator;
    }

    public Administrator(String name, String email, String password) {
        super(name, email, password);
        this.code = code;
    }

    public Administrator() {

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

    public void setUsers(User users) {
        this.users.add(users);
    }

}

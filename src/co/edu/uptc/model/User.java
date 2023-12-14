package co.edu.uptc.model;

import java.util.ArrayList;

public class User extends Person {

    private String userName;
    private ArrayList<Multimedia> wishList = new ArrayList<>();
    private ArrayList<Multimedia> playlist = new ArrayList<>();
    private Plan plan;
    private static User user;

    public User(String name, String email, String password, String userName, Plan plan) {
        super(name, email, password);
        this.userName = userName;
        this.plan = plan;
    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Multimedia> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Multimedia> wishList) {
        this.wishList = wishList;
    }

    public ArrayList<Multimedia> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Multimedia> playlist) {
        this.playlist = playlist;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void addWishList(Multimedia multimedia) {
        wishList.add(multimedia);
    }
}

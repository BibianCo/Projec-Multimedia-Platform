package co.edu.uptc.model;

import java.util.ArrayList;

public class User extends Person {

    private String userName;
    private ArrayList<Multimedia> wishList = new ArrayList<>();
    private ArrayList<Multimedia> playlist = new ArrayList<>();

    public User(String name, String email, String password, String userName) {
        super(name, email, password);
        this.userName = userName;

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

}

package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.User;

public class UserController {
    private User user;

    private ArrayList<Multimedia> playMultimedias = new ArrayList<>();

    public boolean addListHistory(Multimedia multimedia) {
        if (multimedia.isReproduce()) {
            playMultimedias.add(multimedia);
            user.setPlaylist(playMultimedias);
            return true;
        }
        return false;
    }

    public ArrayList<Multimedia> showListHistory() {
        return user.getPlaylist();
    }

}

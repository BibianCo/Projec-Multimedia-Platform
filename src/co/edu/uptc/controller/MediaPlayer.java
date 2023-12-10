package co.edu.uptc.controller;

import co.edu.uptc.model.Multimedia;

public class MediaPlayer {

    public boolean reproduce(Multimedia multimedia) {
        if (!multimedia.isReproduce()) {
            multimedia.setReproduce(true);
            return true;
        }
        return false;
    }

    public boolean stop(Multimedia multimedia) {
        if (multimedia.isReproduce()) {
            multimedia.setReproduce(false);
            return true;
        }
        return false;

    }

    public void advance(int newPosition) {

    }
}

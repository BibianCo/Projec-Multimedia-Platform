package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.view.MediaPlayerApp;

public class MediaPlayerController {
    MultimediaGallery multimedia = new MultimediaGallery();
    MediaPlayerApp mApp = new MediaPlayerApp();

    public boolean nextChapter(Serie serie) {

        if (serie.getCurretChapter() < findChapters(serie).size() - 1) {
            serie.setCurretChapter((int) (serie.getCurretChapter() + 1l));
            currentChapter(serie);
            mApp.reproduce(serie);
            return true;

        }

        return false;
    }

    public boolean previousChapter(Serie serie) {

        if (serie.getCurretChapter() > 0) {
            serie.setCurretChapter(serie.getCurretChapter() - 1);
            currentChapter(serie);
            mApp.reproduce(serie);
            return true;

        }

        return false;
    }

    public Chapter currentChapter(Serie serie) {

        if (serie.getCurretChapter() < findChapters(serie).size()) {
            Chapter currentChapter = findChapters(serie).get(serie.getCurretChapter());

            return currentChapter;
        }
        return null;
    }

    public ArrayList<Chapter> findChapters(Serie serie) {

        for (Season season : serie.getSeasons()) {
            ArrayList<Chapter> chapters = season.getNumberOfChapters();
            return chapters;
        }
        return null;
    }
}

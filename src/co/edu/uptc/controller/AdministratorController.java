package co.edu.uptc.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class AdministratorController {
    MultimediaGallery multimedia = new MultimediaGallery();

    public boolean addSerie(String title, String description, String category, int publication) {
        if (!title.isEmpty() && !description.isEmpty() && !category.isEmpty() && publication > 0) {
            multimedia.setSeries(new Serie(title, description, category, publication));
            return true;
        }
        return false;
    }

    public Serie UpdateSerie(String titleSerie, String description, String category,
            int publication) {
        Serie serie = findSerie(titleSerie);
        if (serie != null) {
            if (serie.getTitle().equals(titleSerie)) {

                serie.setCategory(category);
                serie.setDescription(description);
                serie.setPublication(publication);

                return serie;
            }
        }

        return null;
    }

    public Serie findSerie(String title) {
        if (!title.isEmpty()) {// title.equals("");
            for (Serie serie : multimedia.getSeries()) {
                if (serie.getTitle().equals(title)) {
                    return serie;
                }
            }
        }
        return null;
    }

    public Serie deleteSerie(String title) {

        if (title != null) {
            Serie serie = findSerie(title);
            multimedia.getSeries().remove(findSerie(title));
            return serie;
        }
        return null;
    }

}

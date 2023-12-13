package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.MultimediaGallery;
import co.edu.uptc.model.Serie;

public class MultimediaGalleryController {

    public MultimediaGallery multimediaGallery;
    private static MultimediaGalleryController instance;

    private MultimediaGalleryController() {
        this.multimediaGallery = new MultimediaGallery();
    }

    public static MultimediaGalleryController getInstance() {
        if (instance == null) {
            instance = new MultimediaGalleryController();
        }
        return instance;
    }

    // metodo para buscar el nombre de una serie y/o pelicula a partir del ingreso
    // de parcial de su nombre
    public ArrayList<Multimedia> searchName(String input) {
        String aux = "";
        ArrayList<Multimedia> listOptions = new ArrayList<>();

        // recorrido para listMovies
        for (HashMap.Entry<Integer, Movie> movies : multimediaGallery.getMovies().entrySet()) {
            aux = movies.getValue().getTitle().substring(0,
                    Math.min(input.length(), movies.getValue().getTitle().length()));
            // aux = movies.getValue().getTitle().substring(0, input.length());
            if (movies.getValue().getTitle().contains(aux) && aux.equals(input)) {
                listOptions.add(movies.getValue());
            }
        }
        // recorrido para listSeries
        for (HashMap.Entry<Integer, Serie> series : multimediaGallery.getSeries().entrySet()) {
            aux = series.getValue().getTitle().substring(0,
                    Math.min(input.length(), series.getValue().getTitle().length()));
            // aux = series.getValue().getTitle().substring(0, input.length());
            if (series.getValue().getTitle().contains(aux) && aux.equals(input)) {
                listOptions.add(series.getValue());
            }
        }
        return listOptions;
    }

    public ArrayList<Multimedia> searchCategory(int index) {
        int newIndex = index - 1;
        ArrayList<Multimedia> listOptions = new ArrayList<>();

        return null;
    }

    // type = true = serie type = false = pelicula
    public Integer GenerateKey(Boolean type) {
        Random rd = new Random();
        String aux;
        int num = rd.nextInt(90000) + 10000;

        if (type) {
            aux = "8" + Integer.toString(num);
        } else {
            aux = "5" + Integer.toString(num);
        }
        return Integer.parseInt(aux);
    }

}

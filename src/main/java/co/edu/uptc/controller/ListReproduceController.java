package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.User;

public class ListReproduceController {
    private MovieController movieController;
    private SerieController serieController;
    private UserController userController;

    public ListReproduceController() {
    }

    public ListReproduceController(MovieController movieController,
            SerieController serieController, UserController userController) {
        this.movieController = movieController;
        this.serieController = serieController;
        this.userController = userController;
    }

    public boolean add(int idMultimedia, int idUser) {

        User user = userController.get(idUser);
        if (user == null) {
            return false;
        }

        if (user.getListPersonalized() == null) {
            user.setListPersonalized(new ArrayList<Multimedia>());
        }

        if (movieController.get(idMultimedia) != null) {
            Movie movie = movieController.get(idMultimedia);
            user.getListPersonalized().add(movie);
            userController.updateUserById(idUser, user);
            return true;

        } else if (serieController.get(idMultimedia) != null) {
            Serie serie = serieController.get(idMultimedia);
            user.getListPersonalized().add(serie);
            userController.updateUserById(idUser, user);
            return true;

        }

        return false;

    }

    public boolean delete(int id, int idUser) {
        User user = userController.get(idUser);
        if (user != null) {
            for (int i = 0; i < getAll(idUser).size(); i++) {
                if (getAll(idUser).get(i).getId() == id) {
                    user.getListPersonalized().remove(i);
                    userController.updateUserById(idUser, user);
                    return true;

                }
            }
        }
        return false;

    }

    public Multimedia get(int id, int idUser) {
        User user = userController.get(idUser);
        if (user != null) {
            for (Multimedia findMultimedia : user.getListPersonalized()) {
                if (findMultimedia.getId() == id) {
                    return findMultimedia;
                }

            }
            return null;
        } else {
            return null;
        }
    }

    public ArrayList<Multimedia> getAll(int idUser) {
        return userController.get(idUser).getListPersonalized();
    }

}

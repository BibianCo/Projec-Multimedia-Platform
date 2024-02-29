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

    public boolean add(Multimedia multimedia, int idUser) {

        User user = userController.get(idUser);

        if (multimedia == null || user == null) {
            return false;
        } else if (multimedia instanceof Movie && movieController.movieExists((Movie) multimedia)) {
            user.getListPersonalized().add(multimedia);

            return true;
        } else if (multimedia instanceof Serie && serieController.serieExists((Serie) multimedia)) {
            user.getListPersonalized().add(multimedia);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id, int idUser) {
        User user = userController.get(idUser);
        if (user != null) {
            Multimedia deleteMultimedia = get(id, idUser);
            if (deleteMultimedia != null) {
                user.getListPersonalized().remove(deleteMultimedia);
                return true;
            } else {
                return false;
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

    public boolean update(int id, Multimedia newSubscription, int idUser) {
        User user = userController.get(idUser);
        if (user != null && newSubscription != null) {
            ArrayList<Multimedia> personalizedList = user.getListPersonalized();
            for (int i = 0; i < personalizedList.size(); i++) {
                if (personalizedList.get(i).getId() == id) {
                    personalizedList.set(i, newSubscription);
                    return true;
                }
            }
        }
        return false;
    }

}

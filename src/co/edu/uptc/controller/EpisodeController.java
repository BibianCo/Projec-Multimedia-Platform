package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Episode;
import co.edu.uptc.persistence.Persistence;

public class EpisodeController {

    private Persistence<Episode> persistence;

    public EpisodeController(Persistence<Episode> persistence) {
        this.persistence = persistence;
    }

    public boolean add(Episode episode) {
        if (episode.getId() > 0 && episode.getDuration() > 0 && episode.getNumber() > 0
                && get(episode.getId()) == null) {
            return persistence.persist(episode);
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return persistence.erase(id);
    }

    public Episode get(int id) {
        return persistence.obtainById(id);
    }

    public ArrayList<Episode> getAll() {
        return persistence.obtainAll();
    }

    public boolean update(int id, Episode newEpisode) {
        Episode currentEpisode = get(id);
        if (currentEpisode != null) {
            int index = getAll().indexOf(currentEpisode);
            return this.persistence.persist(index, newEpisode);
        } else {
            return false;
        }
    }

}

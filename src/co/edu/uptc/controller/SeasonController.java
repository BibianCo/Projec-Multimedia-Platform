package co.edu.uptc.controller;

import java.util.ArrayList;

import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.persistence.Persistence;

public class SeasonController {

    private Persistence<Season> persistence;
    private EpisodeController episodeController;

    public SeasonController(Persistence<Season> persistence, EpisodeController episodeController) {
        this.persistence = persistence;
        this.episodeController = episodeController;
    }

    public boolean add(Season season) {
        if (season != null && !season.getEpisodes().isEmpty() && get(season.getId()) == null) {
            return persistence.persist(season);
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return persistence.erase(id);
    }

    public Season get(int id) {
        return persistence.obtainById(id);
    }

    public ArrayList<Season> getAll() {
        return persistence.obtainAll();
    }

    public boolean update(int id, Season newSeason) {
        Season currentSeason = get(id);
        if (currentSeason != null) {
            int index = getAll().indexOf(currentSeason);
            return this.persistence.persist(index, newSeason);
        } else {
            return false;
        }
    }

}

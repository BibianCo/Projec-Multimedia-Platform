package co.edu.uptc.controller;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;
import co.edu.uptc.persistence.Persistence;

public class EpisodeController {

    private Persistence<Episode> persistence;
    private SeasonController seasonController;
    private SerieController serieController;

    public EpisodeController(Persistence<Episode> persistence, SeasonController seasonController) {
        this.persistence = persistence;
        this.seasonController = seasonController;
        this.serieController = new SerieController();

    }

    public boolean add(Episode episode) {
        if (episode.getId() > 0 && get(episode.getId()) == null && episode.getDuration() > 0 && episode.getNumber() > 0
                && setEpisodeToSeason(episode.getIdSeason(), episode)) {
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
            int index = 0;
            for (Episode episode : getAll()) {
                if (episode.getId() == id) {


                    if (!this.persistence.persist(index, newEpisode)) {
                        return false;
                    }
                    Season season = seasonController.get(episode.getIdSeason());
                    ArrayList<Episode> episodes = season.getEpisodes();
                    int index2 = 0;
                    for (Episode season2 : episodes) {
                        if (season2.getId() == id) {
                            episodes.set(index2, newEpisode);
                            break;
                        }
                        index2++;
                    }
                    season.setEpisodes(episodes);
                    seasonController.update(episode.getIdSeason(), season);
                    return true;

                }
                index++;
            }

        } else {
            return false;
        }
        return false;
    }

    public boolean setEpisodeToSeason(int idSeason, Episode episode) {
        Season season = seasonController.get(idSeason);

        if (season != null && episode != null) {
            ArrayList<Episode> episodes = season.getEpisodes();
            if (episodes == null) {
                episodes = new ArrayList<>();
            }
            episodes.add(episode);
            season.setEpisodes(episodes);
            seasonController.update(idSeason, season);


            return true;
        } else {
            return false;
        }
    }

}

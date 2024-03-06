package co.edu.uptc.controller;

import java.util.ArrayList;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.Persistence;

public class SeasonController {

    private Persistence<Season> persistence;
    private SerieController serieController;

    public SeasonController(Persistence<Season> persistence, SerieController serieController) {
        this.persistence = persistence;
        this.serieController = serieController;
    }

    public boolean add(Season season) {
        if (season != null && get(season.getId()) == null
                && serieController.get(season.getIdSerie()) != null && setSeasonToSerie(season.getIdSerie(), season)) {
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

    public boolean setSeasonToSerie(int idSerie, Season season) {
        Serie serie = serieController.get(idSerie);
        if (serie != null && season != null) {
            ArrayList<Season> seasons = serie.getSeasons();
            if (seasons == null) {
                seasons = new ArrayList<>();
            }
            seasons.add(season);
            serie.setSeasons(seasons);

            return true;
        } else {
            return false;
        }
    }
}

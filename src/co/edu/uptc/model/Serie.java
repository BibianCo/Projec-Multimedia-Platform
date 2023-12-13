package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Serie extends Multimedia {
    private int numberSeasons;
    private ArrayList<Season> seasons;
    private int curretChapter;
    private int code;

    public Serie(String title, String description, Category category, LocalDate publication, boolean reproduce,
            int code) {
        super(title, description, category, publication, reproduce, code);
        seasons = new ArrayList<>();
        curretChapter = 0;
    }

    public void addSeason(Season season) {
        seasons.add(season);
        numberSeasons = seasons.size();
        season.setNumberSeason(numberSeasons);
    }

    public int getNumberSeasons() {
        return numberSeasons;
    }

    public void setNumberSeasons(int numberSeasons) {
        this.numberSeasons = numberSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "Serie [numberSeasons=" + numberSeasons + ", seasons=" + seasons + "]" + super.toString();
    }

    public int getCurretChapter() {
        return curretChapter;
    }

    public void setCurretChapter(int curretChapter) {
        this.curretChapter = curretChapter;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Season getSeasonByNumber(int seasonNumber) {
        for (Season season : seasons) {
            if (season.getNumberSeason() == seasonNumber) {
                return season;
            }
        }
        return null; // Season not found
    }

    public Chapter getChapterByTitle(int seasonNumber, String chapterTitle) {
        Season season = getSeasonByNumber(seasonNumber);
        if (season != null) {
            for (Chapter chapter : season.getNumberOfChapters()) {
                if (chapter.getTitle().equals(chapterTitle)) {
                    return chapter;
                }
            }
        }
        return null; // Chapter not found
    }
}
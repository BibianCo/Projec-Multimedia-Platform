package co.edu.uptc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class AdministratorControllerTest {

    AdministratorController administrator;

    @Before
    public void setOne() {
        administrator = new AdministratorController();
    }

    @Before
    public void setTwo() {
        setOne();
        administrator.addSerie("merlina", "s sdsdsd", 1, LocalDate.of(2004, 6, 14));

    }

    @Test
    public void addSerie() {
        assertTrue(administrator.addSerie("merlina", "s sdsdsd", 1, LocalDate.of(2004, 6, 14)));
    }

    @Test
    public void findSerie() {
        setTwo();

        Serie serie = administrator.findSerie("merlina");
        assertEquals("merlina", serie.getTitle());
    }

    @Test
    public void UpdateSerie() {
        addSerie();
        // Serie serie = administrator.UpdateSerie("merlina", "asdasd", "romantica",
        // LocalDate.of(2004, 6, 14));
        // assertEquals(2000, serie.getPublication());
    }

    @Test
    public void deleteSerie() {
        setTwo();
        Serie serie = administrator.findSerie("merlina");
        Serie serie2 = administrator.deleteSerie("merlina");
        assertEquals(serie, serie2);
    }

    @Test
    public void addMovie() {
        assertTrue(administrator.addMovie("up", "animada", 2, LocalDate.of(2004, 6, 14), 12));
        assertTrue(administrator.addMovie(null, null, 1, null, 0));
    }

    @Test
    public void addCategory() {
        assertEquals("\n1. Action\n"
                + "2. Animated\n"
                + "3. Comedy\n"
                + "4. Romance\n"
                + "5. Terror", administrator.showCategories());
        administrator.addCategory("Suspense");
        assertEquals("\n1. Action\n"
                + "2. Animated\n"
                + "3. Comedy\n"
                + "4. Romance\n"
                + "5. Terror\n"
                + "6. Suspense", administrator.showCategories());
        administrator.addCategory("Terror");
        assertEquals("\n1. Action\n"
                + "2. Animated\n"
                + "3. Comedy\n"
                + "4. Romance\n"
                + "5. Terror\n"
                + "6. Suspense", administrator.showCategories());
    }

    @Test
    public void findCategory() {
        assertEquals("Action", administrator.findCategory(1).toString());
        assertEquals("Animated", administrator.findCategory(2).toString());
        assertEquals("Terror", administrator.findCategory(5).toString());
        administrator.addCategory("Suspense");
        assertEquals("Suspense", administrator.findCategory(6).toString());
    }

    @Test
    public void moviesCategory() {
        administrator.addMovie("Movie1", "Description1", 1, LocalDate.of(2020, 02, 02), 0);
        administrator.addMovie("Movie3", "Description3", 3, LocalDate.of(2020, 02, 02), 0);
        administrator.addMovie("Movie2", "Description2", 2, LocalDate.of(2020, 02, 02), 0);

        assertEquals(
                "[Movie [duration=0]Multimedia [title=Movie1, description=Description1, category=Action, publication=2020-02-02, reproduce=false]]",
                administrator.showMoviesCategory(1));
        assertEquals(
                "[Movie [duration=0]Multimedia [title=Movie2, description=Description2, category=Animated, publication=2020-02-02, reproduce=false]]",
                administrator.showMoviesCategory(2));
        assertEquals(
                "[Movie [duration=0]Multimedia [title=Movie3, description=Description3, category=Comedy, publication=2020-02-02, reproduce=false]]",
                administrator.showMoviesCategory(3));

        administrator.addMovie("Movie2.1", "Description2.1", 2, LocalDate.of(2020, 02, 02), 0);
        assertEquals(
                "[Movie [duration=0]Multimedia [title=Movie2, description=Description2, category=Animated, publication=2020-02-02, reproduce=false], Movie [duration=0]Multimedia [title=Movie2.1, description=Description2.1, category=Animated, publication=2020-02-02, reproduce=false]]",
                administrator.showMoviesCategory(2));
    }

    @Test
    public void seriesCategory() {
        administrator.addSerie("Serie1", "Description1", 4, LocalDate.of(2004, 6, 14));
        administrator.addSerie("Serie2", "Description2", 5, LocalDate.of(2004, 6, 14));

        assertEquals(
                "[Serie [numberSeasons=0, seasons=[]]Multimedia [title=Serie1, description=Description1, category=Romance, publication=2004-06-14, reproduce=false]]",
                administrator.showSeriesCategory(4));
        assertEquals(
                "[Serie [numberSeasons=0, seasons=[]]Multimedia [title=Serie2, description=Description2, category=Terror, publication=2004-06-14, reproduce=false]]",
                administrator.showSeriesCategory(5));

        administrator.addSerie("Serie2.1", "Description2.1", 5, LocalDate.of(2004, 6, 14));

        assertEquals(
                "[Serie [numberSeasons=0, seasons=[]]Multimedia [title=Serie2, description=Description2, category=Terror, publication=2004-06-14, reproduce=false], Serie [numberSeasons=0, seasons=[]]Multimedia [title=Serie2.1, description=Description2.1, category=Terror, publication=2004-06-14, reproduce=false]]",
                administrator.showSeriesCategory(5));
    }

    public void setSeries() {
        setOne();
        administrator.addSerie("Prueba1", "Description1", 1, LocalDate.of(2020, 12, 12));
        administrator.addSeason("Prueba1", "Description1", LocalDate.of(1010, 10, 10));
    }

    @Test
    public void addSeason() {
        setSeries();
        assertEquals(true, administrator.addSeason("Prueba1", "SeasonDescription2", LocalDate.of(2020, 02, 02)));
        // Same description
        assertEquals(false, administrator.addSeason("Prueba1", "SeasonDescription2", LocalDate.of(2020, 02, 02)));
    }

    @Test
    public void addChapter() {
        setSeries();
        assertTrue(administrator.addChapter("Prueba1", 1, 120, "DescriptionChapter1", "Chapter1"));
        assertTrue(administrator.addChapter("Prueba1", 1, 120, "DescriptionChapter", "Chapter2"));
        // Same title
        assertFalse(administrator.addChapter("Prueba1", 1, 120, "DescriptionChapter1", "Chapter1"));
        // Nonexistent Season
        assertFalse(administrator.addChapter("Prueba1", 4, 120, "DescriptionChapter1", "Chapter1"));
    }

    @Test
    public void updateChapter() {
        setSeries();
        administrator.addChapter("Prueba1", 1, 20, "DescriptionChapter1", "Chapter1");

        assertTrue(administrator.updateChapter("Prueba1", 1, "Chapter1", 1, "New Description 1", 0));
        assertTrue(administrator.updateChapter("Prueba1", 1, "Chapter1", 2, "New Description 1", 333));
        assertTrue(administrator.updateChapter("Prueba1", 1, "Chapter1", 3, "New Title 1", 0));

        assertFalse(administrator.updateChapter("Prueba22", 1, "Chapter1", 3, "New Title 1", 0));
        assertFalse(administrator.updateChapter("Prueba1", 4, "Chapter1", 3, "New Title 1", 0));
        assertFalse(administrator.updateChapter("Prueba1", 1, "Chapter100", 3, "New Title 1", 0));
        assertFalse(administrator.updateChapter("Prueba1", 1, "Chapter1", 4, "New Title 1", 0));
    }
}

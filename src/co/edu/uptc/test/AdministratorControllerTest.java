package co.edu.uptc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;

public class AdministratorControllerTest {

    AdministratorController administrator;

    @Before
    public void setOne() {
        administrator = new AdministratorController();
    }
/* 
    @Before
    public void setTwo() {
        setOne();
        administrator.addSerie("merlina", "s sdsdsd", "asdads", 2019);

    }

    @Test
    public void addSerie() {
        assertTrue(administrator.addSerie("merlina", "s sdsdsd", "asdads", 2019));
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
        Serie serie = administrator.UpdateSerie("merlina", "asdasd", "romantica", 2000);
        assertEquals(2000, serie.getPublication());
    }

    @Test
    public void deleteSerie() {
        setTwo();
        Serie serie = administrator.findSerie("merlina");
        Serie serie2 = administrator.deleteSerie("merlina");
        assertEquals(serie, serie2);
    }
*/
}

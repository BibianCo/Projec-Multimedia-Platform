package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.InMemoryPersistence;

public class SerieControllerTest {

    private SerieController serieController;
    private CategoryController categoryController;
    private InMemoryPersistence<Serie> impserie;
    private InMemoryPersistence<Category> impc;
    private Serie serie, serie2;

    @Before
    public void setUp() {
        this.impserie = new InMemoryPersistence<>();
        this.impc = new InMemoryPersistence<>();

        this.categoryController = new CategoryController(impc);
        this.serieController = new SerieController(impserie, categoryController);
        // adicionar categorias
        Category category = new Category(1, "Romantica");
        Category category2 = new Category(2, "Comedia");
        categoryController.add(category);
        categoryController.add(category2);

        // adicionar serie
        serie = new Serie(23, "antes de ti", "adsdasd", LocalDate.of(2023, 12, 05),
                categoryController.getAll());
        serie2 = new Serie(232, "juego de tronos", "adsdasd", LocalDate.of(2023, 12, 05),
                categoryController.getAll());
        serieController.add(serie);
        serieController.add(serie2);

    }

    @Test
    public void testSerieExist() {

        Serie serie = new Serie(78, "hola", "asda", null, categoryController.getAll());
        assertEquals(false, serieController.serieExists(serie));
    }

    @Test
    public void testAdd() {
        Category category = new Category(1, "Romantica");
        Category category2 = new Category(2, "Comedia");
        categoryController.add(category);
        categoryController.add(category2);

        Serie serie = new Serie(1, "juego", "amantes", LocalDate.parse("2003-02-03"), categoryController.getAll());

        assertEquals(true, serieController.add(serie));

        assertEquals(false, serieController.add(serie));

    }

    @Test
    public void testDelete() {
        setUp();

        assertEquals(true, serieController.delete(232));
        assertEquals(false, serieController.delete(232));
    }

}

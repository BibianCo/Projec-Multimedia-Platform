package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import co.edu.uptc.model.Category;
import co.edu.uptc.persistence.InMemoryPersistence;

public class CategoryControllerTest {

    public static CategoryController controller;
    public static InMemoryPersistence<Category> inMemoryPersistence;

    @Before
    public void setUp() {
        inMemoryPersistence = new InMemoryPersistence<Category>();
        controller = new CategoryController(inMemoryPersistence);

    }

    @Test
    public void testAddCategory() {
        Category category = new Category(1, "Categoria de prueba");
        boolean result = controller.add(category);
        assertTrue(result);
    }

    @Test
    public void testDeleteCategory() {
        controller.add(new Category(123, "romantica"));
        assertEquals(true, controller.delete(123));
        assertEquals(false, controller.delete(0));
        assertEquals(false, controller.delete(123));

    }

    @Test
    public void testGet() {
        Category c1 = new Category(1, "Terror");
        Category c2 = new Category(2, "Comedia");
        Category c3 = new Category(3, "Drama");
        inMemoryPersistence.data.add(c1);
        inMemoryPersistence.data.add(c2);
        inMemoryPersistence.data.add(c3);

        assertEquals(c1, controller.get(1));
        assertNull(controller.get(4));
    }

}

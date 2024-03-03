package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Category;
import co.edu.uptc.persistence.FilePersistence;

public class CategoryControllerTest {

    public static CategoryController controller;
    public static FilePersistence<Category> filePersistence;
    private Type type;

    @Before
    public void setUp() {
        type = new TypeToken<ArrayList<Category>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "caregories");
        controller = new CategoryController(filePersistence);
    }

    @Test
    public void testAddCategory() {
        assertTrue(filePersistence.createFile());
        Category c1 = new Category(1, "Categoria1");
        boolean result = controller.add(c1);
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
        controller.add(c1);
        controller.add(c2);
        controller.add(c3);
        // inMemoryPersistence.data.add(c1);
        // inMemoryPersistence.data.add(c2);
        // inMemoryPersistence.data.add(c3);

        assertEquals(2, controller.get(2).getId());
        assertNull(controller.get(4));
    }

    @Test
    public void testUpdate() {

        Category c1 = new Category(123, "terror");
        Category c2 = new Category(124, "animada");
        controller.add(c1);
        controller.add(c2);

        assertEquals(true, controller.update(123, new Category(123, "romance")));

        assertEquals(false, controller.update(126, new Category(124, "romance")));

        assertEquals(true, controller.update(124, new Category(125, "terror")));

        assertEquals(false, controller.update(124, new Category(125, "terror")));

        assertEquals(true, controller.update(123, new Category(126, "romance")));
    }

    @Test
    public void testGetAllCategories() {
        Category c1 = new Category(1, "Terror");
        Category c2 = new Category(2, "Comedia");
        Category c3 = new Category(3, "Drama");
        // inMemoryPersistence.data.add(c1);
        // inMemoryPersistence.data.add(c2);
        // inMemoryPersistence.data.add(c3);
        controller.add(c1);
        controller.add(c2);
        controller.add(c3);

        ArrayList<Category> allCategories = controller.getAll();
        assertEquals(3, allCategories.size());
    }
}

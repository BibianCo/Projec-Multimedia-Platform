package co.edu.uptc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Category;
import co.edu.uptc.persistence.FilePersistence;

public class CategoryControllerTest {

    public static CategoryController controller;
    public static FilePersistence<Category> filePersistence;
    private Type type;
    private Category c1;
    private Category c2;
    private Category c3;
    private Category c4;

    @Before
    public void setUp() {
        type = new TypeToken<ArrayList<Category>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "categories");
        controller = new CategoryController(filePersistence);
        c1 = new Category(1, "Categoria1");
        c2 = new Category(123, "romantica");
        c3 = new Category(2, "Comedia");
        c4 = new Category(3, "Drama");
    }

    @Test
    public void testMethods() {
        testAddCategory();
        testDeleteCategory();
        testGet();
        testUpdate();
        testGetAllCategories();
    }

    public void testAddCategory() {
        assertTrue(filePersistence.createFile());
        boolean result = controller.add(c1);
        assertTrue(result);
        assertTrue(controller.add(c2));
        assertTrue(controller.add(c3));
        assertTrue(controller.add(c4));
    }

    public void testDeleteCategory() {
        assertEquals(true, controller.delete(123));
        assertEquals(false, controller.delete(0));
        assertEquals(false, controller.delete(123));

    }

    public void testGet() {
        assertEquals(2, controller.get(2).getId());
        assertNull(controller.get(4));
    }

    public void testUpdate() {

        assertEquals(true, controller.update(1, new Category(123, "romance")));

        assertEquals(false, controller.update(126, new Category(124, "romance")));

        assertEquals(true, controller.update(2, new Category(125, "terror")));

        assertEquals(false, controller.update(124, new Category(125, "terror")));

        assertEquals(true, controller.update(3, new Category(126, "romance")));
    }

    public void testGetAllCategories() {

        ArrayList<Category> allCategories = controller.getAll();
        assertEquals(3, allCategories.size());
    }

    @After
    public void tearDown() {
        filePersistence.deleteFile();

    }
}

package co.edu.uptc.test;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.model.Category;
import co.edu.uptc.persistence.InMemoryPersistence;
import java.util.ArrayList;

public class CategoryControllerTest {
    private CategoryController categoryController;

    @Before
    public void setUp() {
        ArrayList<Category> data = new ArrayList<>();
        InMemoryPersistence<Category> persistence = new InMemoryPersistence<>(data);
        categoryController = new CategoryController(persistence);
    }

    @Test
    public void testAddCategory() {

        Category category = new Category(1, "Categoria de prueba");
        boolean result = categoryController.add(category);
        assertTrue(result);

    }

    @Test
    public void testGetAllCategories() {
        categoryController.add(new Category(1, "Categoria 1"));
        categoryController.add(new Category(2, "Categoria 2"));
        categoryController.add(new Category(3, "Categoria 3"));

        ArrayList<Category> allCategories = categoryController.getAll();
        assertNotNull(allCategories);

        System.out.println("Lista de todas las categorías:");
        for (Category c : allCategories) {
            System.out.println(c);
        }

        for (Category c : allCategories) {
            if (c.getId() == 1) {
                assertEquals("Categoria 1", c.getName());
            } else if (c.getId() == 2) {
                assertEquals("Categoria 2", c.getName());
            } else if (c.getId() == 3) {
                assertEquals("Categoria 3", c.getName());
            } else {
                fail("Categoría no esperada: " + c);
            }
        }
    }
}

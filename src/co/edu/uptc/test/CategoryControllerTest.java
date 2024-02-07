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

}

package co.edu.uptc.test;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Category;

public class UserControllerTest {

    AdministratorController administratorController = new AdministratorController();
    UserController userController = new UserController(administratorController);

    public void addMultimedia() {
        administratorController.addMovie("Movie1", "Description1", 2, LocalDate.of(2020, 2, 2), 0);

        administratorController.addMovie("Movie2", "Description2", 2, LocalDate.of(2222, 12, 22),
                0);

        administratorController.addSerie("Serie1", "Description1", 2, LocalDate.of(2020, 2, 2));
    }

    @Test
    void addWishList() {
        assertEquals(false, userController.addWishList("PruebaNull"));
        addMultimedia();
        assertEquals(true, userController.addWishList("Movie1"));
        assertEquals(true, userController.addWishList("Serie1"));
        assertEquals(false, userController.addWishList("NoExist"));
        assertEquals(true, userController.addWishList("Movie2"));
    }
}

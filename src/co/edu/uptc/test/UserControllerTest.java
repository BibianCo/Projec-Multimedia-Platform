package co.edu.uptc.test;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.User;

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

    @Test
    void updateUser() {
        userController.addUser("juan", "juans@gmail.com", "JUASsdWE23", "JUAN    ", null);

        userController.updateUser("juans@gmail.com", null, null, 0)

    }

    @Test
    void findUser() {

        addUser();
        User user = userController.findUser("laura@gmail.com");

        assertEquals(user.getPlan(), user.getFirstName());
    }

    @Test
    void addUser() {

        Plan plan1 = new Plan("sds", 2, 454);
        Boolean user = userController.addUser("juan", "juferi@gmail.com", "sdsdSDSD232", "juaas", plan1);
        userController.addUser("laura", "laura@gmail.com", "JUASsdWE23", "laura    ", plan1);

        assertEquals(user, true);
    }
}

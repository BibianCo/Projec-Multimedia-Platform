package co.edu.uptc.view;

import java.util.Scanner;

import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.User;

public class InterfazUser {
    private static InterfazVisitor interfazVisitor;
    private static UserController userController = new UserController();
    private static Scanner sc = new Scanner(System.in);
    private static int option = 0;

    public static void interfaz(User user) {
        System.out.printf("\n------ Hello %s ------\n", user.getUserName() + "Look\n");

        userController.showListHistory().forEach(System.out::println);

        System.out.println("[ You can choose the functionality you want  ]\n" + "1. Watch available movies and series\n"
                + "2. Search\n"
                + "3. Search by category\n"
                + "4. Favorites\n"
                + "5. Leave");
        option = sc.nextInt();
        switch (option) {
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;

            default:
                break;
        }
    }

    private static void valUser() {
        interfazVisitor.singIn();
    }

}

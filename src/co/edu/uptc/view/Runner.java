package co.edu.uptc.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    private static InterfazUser interfazUser = new InterfazUser();
    private static InterfazVisitor interfazVisitor = new InterfazVisitor();
    private static InterfazAdmin interfazAdmin = new InterfazAdmin();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        int option = 0;
        do {
            try {
                System.out.println("\n hello person " + "\n Choose how you identify\n" +
                        "1. Administrator\n" +
                        "2. User\n" +
                        "3. Visitor\n" +
                        "4. Leave");
                option = sc.nextInt();
                if (option > 0 && option < 5) {
                    flag = true;
                } else {
                    System.out.println("............ Invalid option ............");
                    flag = false;
                }

            } catch (InputMismatchException e) {
                System.out.println(
                        "............ Error, no characters accepted, please enter a correct answer ............\n");
                flag = false;
                sc.nextLine();
            }
        } while (!flag);
        switch (option) {
            case 1:
                interfazAdmin.main(new String[] {});
                break;
            case 2:
                interfazUser.validationUser();
                break;
            case 3:
                interfazVisitor.interfaz();
                break;
            case 4:
                break;

        }

    }

}

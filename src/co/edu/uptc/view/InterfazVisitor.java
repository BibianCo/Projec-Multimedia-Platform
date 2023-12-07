package co.edu.uptc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.uptc.model.Plan;

public class InterfazVisitor {
    public static void interfaz() {
        String name = "";
        String email = "";
        String password = "";
        String userName = "";
        Plan plan = null;
        boolean flag = false;
        int option = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("--------- WELCOME ---------\n");
        do {
            try {
                System.out.println(" Enter\n" + "1. Sign in\n" + "2. Sign up");
                option = sc.nextInt();
                sc.nextLine();
                if (option > 0 && option < 3) {
                    flag = true;
                } else {
                    System.out.println(" ............ Invalid option ............");
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

                break;
            case 2:
                do {
                    System.out.println("\nEnter your name");
                    name = sc.nextLine();
                    if (name.matches("[a-zA-z]+")) {
                        flag = true;
                    } else {
                        System.out
                                .println(
                                        "............ It is possible that you entered numbers and characters, digital valid answer ............\n");
                        flag = false;
                    }
                } while (!flag);
                do {
                    System.out.println("\nEnter your email");
                    email = sc.nextLine();
                    if (!emailValidation(email)) {
                        System.out.println("............ Invalid email ............\n");
                        flag = false;
                    } else {
                        flag = true;
                    }
                } while (!flag);
                do {
                    System.out.println("\nEnter your password\n"
                            + "[Minimum three characters, maximum twenty, minimum one uppercase letter, one lowercase letter and two numbers]");
                    password = sc.nextLine();
                    if (passwordValidation(password)) {
                        flag = true;
                    } else {
                        System.out.println("............ Invalid password ............");
                        flag = false;
                    }
                } while (!flag);
                break;
        }

    }

    public static boolean emailValidation(String email) {

        ArrayList<String> listDominio = new ArrayList<>();
        listDominio.add("@gmail.com");
        listDominio.add("@uptc.edu.co");
        listDominio.add("@outlook.es");
        listDominio.add("@yahoo.com");

        for (String s : listDominio) {
            if (email.contains(s)) {
                int position = email.length() - s.length();
                String aux = email.substring(0, position);

                if (aux.contains("@") || aux.length() < 5) {
                    return false;
                } else {
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean passwordValidation(String password) {

        if (password.length() > 3 && password.length() < 20) { // >3 <20
            if (!password.equals(password.toLowerCase())) { // min. una mayuscula
                if (!password.equals(password.toUpperCase())) { // min. una miniscula
                    if (password.matches(".*\\d.*\\d.*")) { // min. 2 numeros
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

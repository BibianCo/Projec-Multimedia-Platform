package co.edu.uptc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.uptc.controller.PlanController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Plan;

public class InterfazVisitor {

    private static UserController userController = new UserController();
    private static InterfazUser interfazUser = new InterfazUser();
    private static Runner runner = new Runner();
    private static String name = "";
    private static String email = "";
    private static String password = "";
    private static String userName = "";
    private static PlanController planController = new PlanController();
    private static Plan plan = new Plan();
    private static boolean flag = false;
    private static int option = -1;
    private static Scanner sc = new Scanner(System.in);
    private static String[] messErrorInt = new String[2];

    public static void interfaz() {
        messErrorInt[0] = "............ Error, no characters accepted, please enter a correct answer ............\\n";
        messErrorInt[1] = " ............ Invalid option ............";
        // inicia interaccion con el visitante
        System.out.println(
                "\n---------------------------  WELCOME YOUR TRUSTED MILTIMEDIA  ---------------------------\n\n"
                        +
                        "You will find unlimited movies and series at a price that benefits you.\n" +
                        "\nReasons to join: \n - You will find stories tailored to your tastes. \n - You will find plans with an affordable cost.\n");

        boolean exit = false;

        while (!exit) {
            option = askQuestion(messErrorInt);
            switch (option) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    System.out.println(
                            "\nIt is a multimedia platform where you can find and watch the movies and series you want");
                    break;
                case 2:
                    // muestra una lista de planes que ofrese la multimedia
                    planController.getListPlans().forEach(System.out::println);
                    break;
            }
        }
        // ingresa para iniciar sesion o inscribirse
        do {
            try {
                System.out.println("Enter\n" + "1. Sign in\n" + "2. Sign up\n" + "3. Back");
                option = sc.nextInt();
                sc.nextLine();
                if (option > 0 && option < 4) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                }

            } catch (InputMismatchException e) {
                System.out.println(messErrorInt[0]);
                flag = false;
                sc.nextLine();
            }
        } while (!flag);

        switch (option) {
            case 1:
                singIn();
                break;
            case 2:
                singUp();
                break;
            case 3:
                // llamar a la clase del runner o menu principal
                // runner.main(messErrorInt);
                break;
        }

    }

    public static int askQuestion(String[] messErrorInt) {
        do {
            try {
                System.out.println(
                        "\nFrequent questions \n 1. What is the multimedia platform? \n 2. What are the plans offered by the platform?\n"
                                +
                                "Enter 1 or 2 if you want to know an answer, otherwise enter 0 ");
                option = sc.nextInt();
                if (option > -1 && option < 3) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println(messErrorInt[0]);
                flag = false;
                sc.nextLine();
            }
        } while (!flag);
        return option;
    }

    // medoto de iniciar sesion
    public static void singIn() {
        do {
            validationEmeilandPass();
            boolean valAuthen = userController.authentication(email, password);
            if (valAuthen) {
                interfazUser.interfaz();

            } else {
                System.out.println(" ..... Your email or password are not correct .....\n");
                do {
                    try {
                        System.out.println("Enter 1 to try again or 2 to go back.");
                        option = sc.nextInt();
                        sc.nextLine();
                        if (option > 0 && option < 3) {
                            flag = true;
                        } else {
                            System.out.println(messErrorInt[1]);
                            flag = false;
                        }

                    } catch (InputMismatchException e) {
                        System.out.println(messErrorInt[0]);
                        flag = false;
                        sc.nextLine();
                    }
                } while (!flag);
                if (option == 1) {
                    flag = false;
                } else if (option == 2) {
                    interfaz();
                }
            }
        } while (!flag);
    }

    // medoto de registrarse
    public static void singUp() {

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

        // metodo donde se encuentar para ingresar email y contraseña
        validationEmeilandPass();

        do {
            System.out.println("\nEnter your username");
            userName = sc.nextLine();
            if (userName.matches("[a-zA-z]+")) {
                flag = true;
            } else {
                System.out
                        .println(
                                "............ It is possible that you entered numbers and characters, digital valid answer ............\n");
                flag = false;
            }
        } while (!flag);

        // se muestra el plan y el cliente lo escoge

        do {
            try {
                System.out.println("\nChoose the plan you want to use");
                planController.getListPlans().forEach(System.out::println);
                option = sc.nextInt();
                sc.nextLine();
                if (option > 0 && option < 4) {
                    flag = true;
                } else {
                    System.out.println(messErrorInt[1]);
                    flag = false;
                }

            } catch (InputMismatchException e) {
                System.out.println(messErrorInt[0]);
                flag = false;
                sc.nextLine();
            }
        } while (!flag);

        Plan p1 = planController.assignTypePlan(option);
        planController.assignUser(userName);
        // se crea el usuarios y se valida si ya existe
        boolean valAddUser = userController.addUser(name, email, password, userName, p1);
        if (valAddUser == true) {
            System.out.println(" \n---- Hey, you have been successfully registered ----");
            interfazUser.interfaz();
        } else {
            System.out.println("\n..... There is already a registered person with the same data entered .....");
            interfaz();
        }
    }

    public static void validationEmeilandPass() {
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

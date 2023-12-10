package co.edu.uptc.view;

import java.util.Scanner;

import co.edu.uptc.controller.AdministratorController;
import co.edu.uptc.model.Administrator;

public class Runner {
    public static void main(String[] args) {

        InterfazVisitor interfazVisitor = new InterfazVisitor();
        interfazVisitor.interfaz();
        System.out.println("hola ");

    }

}

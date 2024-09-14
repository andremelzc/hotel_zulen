package main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author PC
 */
import java.io.IOException;
import java.util.Scanner;
import pruebitas_andre.*;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PersonalCrud personal = new PersonalCrud();
        main obj = new main();

        boolean flag = true;

        do {
            System.out.println("Menu de opciones");
            System.out.println("1. CRUD personal");
            System.out.println("2. CRUD huesped");
            System.out.println("3. CRUD habitacion");
            System.out.println("4. Salir");
            System.out.println("Que desea hacer?");
            int op = sc.nextInt();
            sc.nextLine(); //Limpiar el buffer

            switch (op) {
                case 1:
                    obj.menuPersonal();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    flag = false;
                    System.out.println("Saliendo...");
                    break;
            }
        } while (flag);
    }

    public void menuPersonal() {
        Scanner sc = new Scanner(System.in);
        boolean flagPersonal = true;
        do {
            System.out.println("1. Agregar personal");
            System.out.println("2. Leer todo personal");
            System.out.println("3. Actualizar personal");
            System.out.println("4. Eliminar personal");
            System.out.println("5. Retroceder");
            System.out.println("Que desea hacer?");
            int opPersonal = sc.nextInt();
            sc.nextLine();

            switch (opPersonal) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    flagPersonal = false;
                    break;
            }
        } while (flagPersonal);
    }

}

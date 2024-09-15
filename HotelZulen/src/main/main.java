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
            System.out.println("------------------");
            System.out.println("Menu de opciones");
            System.out.println("------------------");
            System.out.println("1. CRUD personal");
            System.out.println("2. CRUD huesped");
            System.out.println("3. CRUD habitacion");
            System.out.println("4. Salir");
            System.out.println("------------------");
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
        PersonalCrud personalCrud = new PersonalCrud();
        boolean flagPersonal = true;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD PERSONAL");
            System.out.println("------------------");
            System.out.println("1. Agregar personal");
            System.out.println("2. Leer todo personal");
            System.out.println("3. Buscar personal");
            System.out.println("4. Actualizar personal");
            System.out.println("5. Eliminar personal");
            System.out.println("6. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opPersonal = sc.nextInt();
            sc.nextLine();

            switch (opPersonal) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando personal");
                    System.out.println("-----------------------------");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    String apellido = sc.nextLine();
                    Personal nuevoPersonal = new Personal(nombre, apellido);
                    personalCrud.agregarPersonal(nuevoPersonal);
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todo el personal");
                    System.out.println("-----------------------------\n");
                    personalCrud.leerTodoPersonal();
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    personalCrud.buscarPersonal(id_buscar);
                    System.out.println("-----------------------------\n");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    personalCrud.actualizarPersonal(id_actualizar);
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    personalCrud.eliminarPersonal(id_eliminar);
                    System.out.println("-----------------------------\n");
                case 6:
                    flagPersonal = false;
                    break;
            }
        } while (flagPersonal);
    }

}

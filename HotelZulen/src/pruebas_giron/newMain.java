package pruebas_giron;

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
//import pruebas_giron.*; //En caso se use fuera de este package

public class newMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PersonalCrud personal = new PersonalCrud();
        //Reservación reservacion = new Reservación(); //Esto es de Melendez
        InicioSesion isesion = new InicioSesion();
        newMain obj = new newMain();
        boolean sesion = true;
        boolean flag = true;//Melendez lógica
        do{ 
            boolean ingresa = false;
            
            System.out.println("------------------");
            System.out.println(" INICIO DE SESION ");
            System.out.println("------------------");
            System.out.println("1. Ingrese su Usuario");
            String nombre = sc.nextLine();
            System.out.println("2. Ingrese su Contraseña");
            String contra = sc.nextLine();
            
            //Verificar condicion entonces puede acceder al menu de Opciones
            ingresa = isesion.verificarValidezPersonal(nombre, contra);
            
            if(ingresa){
                do {
                    System.out.println("------------------");
                    System.out.println("Menu de opciones");
                    System.out.println("------------------");
                    System.out.println("1. CRUD personal");
                    System.out.println("2. CRUD huesped");
                    System.out.println("3. CRUD habitacion");
                    System.out.println("4. Asignar habitacion");
                    System.out.println("5. Salir");
                    System.out.println("------------------");
                    System.out.println("Que desea hacer?");
                    int op = sc.nextInt();
                    sc.nextLine(); //Limpiar el buffer

                    switch (op) {
                        case 1:
                            obj.menuPersonal(sc);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            //reservacion.asignarReservacion(sc); //Esto es de Melendez
                            System.out.println("\n4");
                            break;
                        case 5:
                            flag = false;
                            System.out.println("Saliendo...");
                            break;
                        }
                    } while (flag);
            }
            
            System.out.println("Si desea cerrar la sesion, ingrese 'salir' para terminar la Ejecucion");
            String desicion = sc.nextLine();
            if(desicion.equalsIgnoreCase("salir")){
                sesion = false;
            }
            
        }while(sesion);
        
        
    }//Fin del main
    
    public void menuPersonal(Scanner sc) {
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
                    //Agregado de mi parte para verificar el inicio
                    System.out.println("Usuario: ");
                    String usuario = sc.nextLine();
                    System.out.println("Contrasena: ");
                    String contrasena = sc.nextLine();
                    
                    Personal nuevoPersonal = new Personal(nombre, apellido,usuario,contrasena);
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

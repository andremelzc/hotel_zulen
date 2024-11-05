/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;


import Persistencia.HuespedRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Huesped;
import modelo.Recepcionista;

/**
 *
 * @author Suyco
 */
public class VistaRecepcionista {
    private Recepcionista recepcionistaActual; // Objeto del recepcionista que ha iniciado sesión
    
    public VistaRecepcionista(Recepcionista recepcionista) {
        this.recepcionistaActual = recepcionista;
    }
    
    public void mostrar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        do {
            System.out.println("------------------");
            System.out.println("Vista Recepcionista");
            System.out.println("------------------");
            System.out.println("1. Gestionar huespedes");
            System.out.println("2. Consultar informacion");
            System.out.println("3. Reservar habitacion");
            System.out.println("4. Ampliar reserva");
            System.out.println("5. Registrar ingreso");
            System.out.println("6. Registrar salida");
            System.out.println("7. Salir");

            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    menuHuesped(scanner);
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    //realizarCheckOut();
                    break;
                case 5:
                    ///realizarCheckOut();
                    break;
                case 6:
                    //realizarCheckOut();
                    break;
                case 7:
                    System.out.println("Saliendo de la vista de recepcionista.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 7);
        
        scanner.close();
    }
    
    private void menuHuesped(Scanner sc) throws IOException {
        
        boolean flagHuesped = true;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD HUESPED");
            System.out.println("------------------");
            System.out.println("1. Agregar huesped");
            System.out.println("2. Mostrar huespedes");
            System.out.println("3. Buscar huesped");
            System.out.println("4. Actualizar huesped");
            System.out.println("5. Eliminar huesped");
            System.out.println("6. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opHuesped = sc.nextInt();
            sc.nextLine();

            switch (opHuesped) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando huesped");
                    System.out.println("-----------------------------");
                    /*System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.println("DNI: ");
                    int dni = sc.nextInt();
                    System.out.println("Telefono: ");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Direccion: ");
                    String direccion = sc.nextLine();
                    System.out.println("Usuario: ");
                    String usuario = sc.nextLine();
                    System.out.println("Contraseña: ");
                    String contrasena = sc.nextLine();
                    String estado = "Activo";

                    Huesped nuevoHuesped = new Huesped(dni, nombre, apellido, telefono, direccion, usuario, contrasena, estado);
                    nuevoHuesped.agregar(HuespedesCargados, nuevoHuesped);
                    huespedRepo.cargarRegistroToCSV(nuevoHuesped, "huespedes.csv");
                    */System.out.println("-----------------------------\n");
                    break;
                case 2:

                    int respuesta;
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo huespedes");
                    System.out.println("-----------------------------\n");
                    System.out.println("Seleccione opcion: ");
                    System.out.println("1. Huespedes activos ");
                    System.out.println("2. Huespedes desactivos ");
                    System.out.println("3. Toda la data (Inactivos y Activos) ");
                    /*respuesta = sc.nextInt();
                    sc.nextLine();
                    switch (respuesta) {
                        case 1:
                            huesped.leerHuespedActivo(HuespedesCargados);
                            break;
                        case 2:
                            huesped.leerHuespedInactivo(HuespedesCargados);
                            break;
                        case 3:
                            huesped.mostrarLista(HuespedesCargados);
                            break;
                        default:
                            System.out.println("Ingrese una opción válida.");
                    }
*/
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("DNI del huesped: ");
                    int dni_buscar = sc.nextInt();
                    sc.nextLine();
                    /*huesped.buscarHuespedXDNI(HuespedesCargados, dni_buscar);
                    */System.out.println("-----------------------------\n");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    /*for (Huesped huespedes : HuespedesCargados) {
                        if (huespedes.getDNI() == id_actualizar) {
                            huesped = huespedes;
                        }
                    }
                    huesped.actualizar(HuespedesCargados, huesped);
                    funcionalidadesRepository.vaciarCSV("huespedes.csv");
                    huespedRepo.cargarListaToCSV(HuespedesCargados, "huespedes.csv");
                    */System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    /*for (Huesped huespedes : HuespedesCargados) {
                        if (huespedes.getDNI() == id_eliminar) {
                            huesped = huespedes;
                        }
                    }
                    huesped.eliminar(HuespedesCargados, huesped);
                    funcionalidadesRepository.vaciarCSV("huespedes.csv");
                    huespedRepo.cargarListaToCSV(HuespedesCargados, "huespedes.csv");
                    */System.out.println("-----------------------------\n");
                case 6:
                    flagHuesped = false;
                    break;
            }
        } while (flagHuesped);
    }
}


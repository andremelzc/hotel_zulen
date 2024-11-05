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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Administrador;
import modelo.Huesped;
import modelo.Personal;

/**
 *
 * @author Suyco
 */
public class VistaAdministrador {
    private Administrador administradorActual;

    public VistaAdministrador(Administrador administradorActual) {
        this.administradorActual = administradorActual;
    }
    
    public void mostrar(){
        Scanner sc = new Scanner (System.in);
        int opcion =0;
        do {
            System.out.println("------------------");
            System.out.println("Vista Administrador");
            System.out.println("------------------");
            System.out.println("1. CRUD personal");
            System.out.println("2. CRUD huesped");
            System.out.println("3. CRUD habitacion");
            System.out.println("4. CRUD servicios");
            System.out.println("5. Salir");

            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int op = sc.nextInt();
            sc.nextLine(); //Limpiar el buffer

            switch (opcion) {
                case 1:
                    menuPersonal(sc);
                    break;
                case 2:
                    //menuHuesped(sc);
                    break;
                case 3:
                    //menuHabitacion(sc);
                    break;
                case 4:
                    //menuServicios(sc);
                    break;
                case 5:
                    
                    System.out.println("Saliendo...");
                    break;

            }
        } while (opcion !=5);
    }
   private void menuPersonal(Scanner sc) {
        int opPersonal;
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
            System.out.println("7. Leer todo Ama de Llaves");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            opPersonal = sc.nextInt();
            sc.nextLine();

            switch (opPersonal) {
                case 1:
                    

                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando personal");
                    System.out.println("-----------------------------");
                    
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando personal");
                    System.out.println("-----------------------------");
                    System.out.println("DNI del personal: ");
                    int id_buscar = sc.nextInt();
                    sc.nextLine();
                   /* personal.buscarPersonalXDNI(PersonalCargado, id_buscar);
                    */break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    /*for(Personal personal1 : PersonalCargado){
                        if(personal1.getDNI()==id_actualizar){
                            personal = personal1;
                        }
                    }
                    personal.actualizar(PersonalCargado, personal);
                    funcionalidadesRepository.vaciarCSV("personal.csv");
                    personalRepository.cargarListaToCSV(PersonalCargado, "personal.csv");
                    */System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    /*for(Personal personal1 : PersonalCargado){
                        if(personal1.getDNI()==id_eliminar){
                            personal = personal1;
                        }
                    }
                    personal.eliminar(PersonalCargado, personal);
                    funcionalidadesRepository.vaciarCSV("personal.csv");
                    personalRepository.cargarListaToCSV(PersonalCargado, "personal.csv");
                    */System.out.println("-----------------------------\n");
                case 6:
                    
                    break;
                case 7:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo Ama de Llaves");
                    System.out.println("-----------------------------\n");
                    //personalCrud.leerTodoAmaLlaves();
                    break;
            }
        } while (opPersonal != 6);
    } 

   private void menuHuesped(Scanner sc) throws IOException {
        /*HuespedCrud huespedCrud = new HuespedCrud();
        Huesped huesped = new Huesped();
        List<Huesped> HuespedesCargados = new ArrayList<>();
        HuespedRepository huespedRepo = new HuespedRepository();
        HuespedesCargados = huespedRepo.cargarCSVtoLista("huespedes.csv");
        */
        int opHuesped;
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
            opHuesped = sc.nextInt();
            sc.nextLine();

            switch (opHuesped) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("Nombre: ");
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

                    /*Huesped nuevoHuesped = new Huesped(dni, nombre, apellido, telefono, direccion, usuario, contrasena, estado);
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
                    respuesta = sc.nextInt();
                    sc.nextLine();
                    switch (respuesta) {
                        case 1:
                            //huesped.leerHuespedActivo(HuespedesCargados);
                            break;
                        case 2:
                            //huesped.leerHuespedInactivo(HuespedesCargados);
                            break;
                        case 3:
                            //huesped.mostrarLista(HuespedesCargados);
                            break;
                        default:
                            System.out.println("Ingrese una opción válida.");
                    }

                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("DNI del huesped: ");
                    int dni_buscar = sc.nextInt();
                    sc.nextLine();
                    //huesped.buscarHuespedXDNI(HuespedesCargados, dni_buscar);
                    System.out.println("-----------------------------\n");
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
                    
                    break;
            }
        } while (opHuesped!=6);
    }

   public void menuHabitacion(Scanner sc) throws IOException {
       
       int opHabitacion; 
       do {
            System.out.println("\n------------------");
            System.out.println("CRUD HABITACION");
            System.out.println("------------------");
            System.out.println("1. Agregar Habitacion");
            System.out.println("2. Leer todo Habitacion");
            System.out.println("3. Buscar Habitacion");
            System.out.println("4. Actualizar Habitacion");
            System.out.println("5. Eliminar Habitacion");
            System.out.println("6. Leer Tipos de Habitacion");
            System.out.println("7. Cambiar precio a tipo de Habitacion");
            System.out.println("8. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            opHabitacion = sc.nextInt();
            sc.nextLine();

            switch (opHabitacion) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando Habitacion");
                    System.out.println("-----------------------------");

                    System.out.println("Tipo: ");
                    String tipo = sc.nextLine();
                    /*for (TipoDeHabitacion tipoDeHabitacion : tiposCargados) {
                        if (tipo.equals(tipoDeHabitacion.getConcepto())) {
                            nuevoTipo = tipoDeHabitacion;
                        }
                    }
                    System.out.println("Piso: ");
                    int piso = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Estado: ");
                    String estado = sc.nextLine();
                    int id = funcionalidadesRepository.numeroLineas("habitaciones.csv") + 1;
                    Habitacion nuevoHabitacion = new Habitacion(id, nuevoTipo, String.valueOf(piso), estado);
                    habitacion.agregar(habitacionCargada, nuevoHabitacion);
                    habitacionRepo.cargarRegistroToCSV(nuevoHabitacion, "habitaciones.csv");
                    */System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todas las Habitaciones");
                    System.out.println("-----------------------------\n");
                    System.out.println("------------------------------------------");
                    System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
                    //habitacion.mostrarLista(habitacionCargada);
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    /*Habitacion habitacion_escogida = new Habitacion();
                    habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_buscar);
                    System.out.println("--------------------------------------------------------------------------------------");
                    System.out.printf("%-4s %-19s %-5s %-12s%n", "ID", "Tipo de habitacion", "Piso", "Estado");
                    System.out.println("--------------------------------------------------------------------------------------");
                    // Imprimimos toda la lista
                    System.out.printf("%-4s %-19s %-5s %-12s%n",
                            habitacion_escogida.getId(),
                            habitacion_escogida.getTipoHabitacion().getConcepto(),
                            habitacion_escogida.getPiso(),
                            habitacion_escogida.getEstado());

                    */System.out.println("--------------------------------------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    System.out.println("------------------------------------------");
                    /*System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
                    habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_actualizar);
                    habitacion.actualizar(habitacionCargada, habitacion_escogida);
                    funcionalidadesRepository.vaciarCSV("habitaciones.csv");
                    habitacionRepo.cargarListaToCSV(habitacionCargada, "habitaciones.csv");
                    */System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    /*habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_eliminar);
                    habitacion.eliminar(habitacionCargada, habitacion_escogida);
                    funcionalidadesRepository.vaciarCSV("habitaciones.csv");
                    habitacionRepo.cargarListaToCSV(habitacionCargada, "habitaciones.csv");
                    System.out.println("-----------------------------\n");
                    */break;
                case 6:
                    //nuevoTipo.mostrarLista(tiposCargados);
                    break;
                case 7:
                    System.out.println("\n-----------------------------");
                    //nuevoTipo.mostrarLista(tiposCargados);
                    System.out.println("\n-----------------------------");
                    System.out.println("Tipo de habitación a cambiar el precio: ");
                    int id_tipo = sc.nextInt();
                    sc.nextLine();
                    /*for (TipoDeHabitacion tipoDeHabitacion : tiposCargados) {
                        if (id_tipo == tipoDeHabitacion.getId()) {
                            System.out.println("Nuevo precio: ");
                            float nuevo_precio = sc.nextInt();
                            sc.nextLine();
                            tipoDeHabitacion.setPrecio(nuevo_precio);
                        }
                    }
                    funcionalidadesRepository.vaciarCSV("tipoHabitacion.csv");
                    tipoRepo.cargarListaToCSV(tiposCargados, "tipoHabitacion.csv");
                    */break;
                case 8:
                    
                    break;
            }
        } while (opHabitacion != 8);
    }

    public void menuServicios(Scanner sc) throws IOException {

        int opServicio;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD SERVICIOS");
            System.out.println("------------------");
            System.out.println("1. Agregar servicio");
            System.out.println("2. Mostrar servicios");
            System.out.println("3. Actualizar servicio");
            System.out.println("4. Eliminar servicio");
            System.out.println("5. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            opServicio = sc.nextInt();
            sc.nextLine();

            switch (opServicio) {
                case 1:
                    boolean flag_salir = false;

                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando servicio");
                    System.out.println("-----------------------------");
                    /*do {
                        //Nombre
                        System.out.println("Concepto: ");
                        String concepto = sc.nextLine();
                        //Costo
                        int costo;
                        boolean flag_costo = false;
                        do {
                            System.out.println("Costo: ");
                            costo = sc.nextInt();
                            if (costo > 0) {
                                flag_costo = true;
                            }
                            if (!flag_costo) {
                                System.out.println("-----------------------------");
                                System.out.println("Ingrese un precio correcto");
                                System.out.println("-----------------------------");
                            }
                        } while (!flag_costo);
                        int id = funcionalidadesRepository.numeroLineas("serviciosAdicionales.csv") + 1; //Agregando a la lista de servicios
                        ServiciosAdicionales nuevoServicio = new ServiciosAdicionales(id, concepto, costo, "Disponible");
                        servicios.agregar(serviciosCargados, servicios);
                        serviciosRepo.cargarRegistroToCSV(nuevoServicio, "serviciosAdicionales.csv");

                        System.out.println("-----------------------------");
                        System.out.println("Quieres agregar otro?");
                        sc.nextLine();// para consumir una linea >/
                        String op_seguir = sc.nextLine();

                        //Si la respuesta es no, se sale del do-while
                        if ("no".equals(op_seguir) || "No".equals(op_seguir) || "NO".equals(op_seguir)) {
                            flag_salir = true;
                        }
                    } while (!flag_salir);
                    */break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo los servicios");
                    System.out.println("-----------------------------\n");
                    //servicios.mostrarLista(serviciosCargados);
                    break;
                case 3: //ACTUALIZAR SERVICIO X ID
                    System.out.println("\n-----------------------------");

                    System.out.println("ID a actualizar: ");
                    int id_buscar = sc.nextInt();
                    sc.nextLine(); // consumir una linea en blanco

                    /*System.out.println("-----------------------------");
                    System.out.println("El ID ingresado no se encuentra");
                    System.out.println("-----------------------------");*/
                    /*for (ServiciosAdicionales serviciosAdicionales : serviciosCargados) {
                        if (serviciosAdicionales.getId() == id_buscar) {
                            //Nombre
                            System.out.println("Concepto: ");
                            String concepto = sc.nextLine();
                            //Costo
                            int costo;
                            boolean flag_costo = false;
                            do {
                                System.out.println("Costo: ");
                                costo = sc.nextInt();
                                if (costo > 0) {
                                    flag_costo = true;
                                }
                                if (!flag_costo) {
                                    System.out.println("-----------------------------");
                                    System.out.println("Ingrese un precio correcto");
                                    System.out.println("-----------------------------");
                                }
                            } while (!flag_costo);
                            serviciosAdicionales.setCosto(costo);
                            serviciosAdicionales.setConcepto(concepto);
                        }
                    }
                    funcionalidadesRepository.vaciarCSV("serviciosAdicionales.csv");
                    serviciosRepo.cargarListaToCSV(serviciosCargados, "serviciosAdicionales.csv");
                    */
                    System.out.println("-----------------------------");
                    System.out.println("Registro modificado");
                    System.out.println("-----------------------------");

                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando servicio");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    sc.nextLine();
                    /*for (ServiciosAdicionales serviciosAdicionales : serviciosCargados) {
                        if (serviciosAdicionales.getId() == id_eliminar) {
                            serviciosAdicionales.setEstado("No Disponible");
                        }
                    }
*/
                    /*System.out.println("-----------------------------");
                    System.out.println("El ID ingresado no se encuentra");
                    System.out.println("-----------------------------");*/
                    /*funcionalidadesRepository.vaciarCSV("serviciosAdicionales.csv");
                    serviciosRepo.cargarListaToCSV(serviciosCargados, "serviciosAdicionales.csv");
                    */
                    System.out.println("-----------------------------");
                    System.out.println("Registro eliminado");
                    System.out.println("-----------------------------\n");

                    break;

                case 5:
                    
                    break;

            }
        } while (opServicio != 5);
    }
}

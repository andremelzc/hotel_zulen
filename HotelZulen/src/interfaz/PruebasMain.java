/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;
import Controlador.*;
import modelo.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.ServiciosAdicionales;
import modelo.TipoDeHabitacion;
import Persistencia.HabitacionRepository;
import Persistencia.HotelRepository;
import Persistencia.HuespedRepository;
import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionRepository;
import Persistencia.ReservacionServicioRepository;
import Persistencia.ServiciosAdicionalesRepository;
import Persistencia.TipoHabitacionRepository;
import Persistencia.FuncionalidadesRepository;
import Persistencia.PersonalRepository;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebasMain {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PruebasMain pruebas = new PruebasMain();
        pruebas.menuHabitacion(sc);
    
    }
    public void menuHabitacion(Scanner sc) throws IOException{
       // Cargamos los tipos de habitaaciones
        List<TipoDeHabitacion> tiposCargados = new ArrayList<>();
        TipoHabitacionRepository tipoRepo = new TipoHabitacionRepository();
        TipoDeHabitacion nuevoTipo = new TipoDeHabitacion();
        tiposCargados = tipoRepo.cargarCSVtoLista("tipoHabitacion.csv");

        // Cargamos las habitaciones
        List<Habitacion> habitacionCargada = new ArrayList<>();
        HabitacionRepository habitacionRepo = new HabitacionRepository();
        Habitacion habitacion = new Habitacion();
        habitacionCargada = habitacionRepo.cargarCSVtoLista("habitaciones.csv");
        
        HotelRepository funcionalidadesRepository = new HotelRepository();
        boolean flagHabitacion = true;
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
            int opHabitacion = sc.nextInt();
            sc.nextLine();

            switch (opHabitacion) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("FALTA AGREGAR TONOTOS CON LA NUEVA LOGICA\n");
                    System.out.println("Agregando Habitacion");
                    System.out.println("-----------------------------");

                    System.out.println("Tipo: ");
                    String tipo = sc.nextLine();
                    for (TipoDeHabitacion tipoDeHabitacion : tiposCargados) {
                        if (tipo.equals(tipoDeHabitacion.getConcepto())) {
                            nuevoTipo = tipoDeHabitacion;
                        }
                    }
                    System.out.println("Piso: ");
                    int piso = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Estado: ");
                    String estado = sc.nextLine();
                    int id = funcionalidadesRepository.contarLineasCSV("habitaciones.csv")+1;
                    Habitacion nuevoHabitacion = new Habitacion(id, nuevoTipo, String.valueOf(piso), estado);
                    habitacion.agregar(habitacionCargada, nuevoHabitacion);
                    habitacionRepo.cargarRegistroToCSV(nuevoHabitacion, "habitaciones.csv");
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todas las Habitaciones");
                    System.out.println("-----------------------------\n");
                    System.out.println("------------------------------------------");
                    System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
                    habitacion.mostrarLista(habitacionCargada);
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    Habitacion habitacion_escogida = new Habitacion();
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

                    System.out.println("--------------------------------------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    System.out.println("------------------------------------------");
                    System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
                    habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_actualizar);
                    habitacion.actualizar(habitacionCargada, habitacion_escogida);
                    
                    habitacionRepo.cargarListaToCSV(habitacionCargada, "habitaciones.csv");
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_eliminar);
                    habitacion.eliminar(habitacionCargada, habitacion_escogida);
                    
                    habitacionRepo.cargarListaToCSV(habitacionCargada, "habitaciones.csv");
                    System.out.println("-----------------------------\n");
                case 6:
                    nuevoTipo.mostrarLista(tiposCargados);
                    break;
                case 7:
                    System.out.println("\n-----------------------------");
                    nuevoTipo.mostrarLista(tiposCargados);
                    System.out.println("\n-----------------------------");
                    System.out.println("Tipo de habitación a cambiar el precio: ");
                    int id_tipo = sc.nextInt();
                    sc.nextLine();
                    for (TipoDeHabitacion tipoDeHabitacion : tiposCargados) {
                        if (id_tipo == tipoDeHabitacion.getId()) {
                            System.out.println("Nuevo precio: ");
                            float nuevo_precio = sc.nextInt();
                            sc.nextLine();
                            tipoDeHabitacion.setPrecio(nuevo_precio);
                        }
                    }
                   
                    tipoRepo.cargarListaToCSV(tiposCargados, "tipoHabitacion.csv");
                    break;
                case 8:
                    flagHabitacion = false;
                    break;
            }
        } while (flagHabitacion);
    }
}

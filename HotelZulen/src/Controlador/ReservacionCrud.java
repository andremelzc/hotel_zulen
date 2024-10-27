/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.*;

import java.time.Period;                      // Cálculo de diferencia entre fechas
import java.time.format.DateTimeFormatter;    // Formato de fechas


public class ReservacionCrud {

    List<Reservacion> listaReservaciones = new ArrayList<>();

    HuespedCrud huespedCrud = new HuespedCrud();
    HabitacionCrud habitacionCrud = new HabitacionCrud();
    
    public List<Reservacion> cargarCSVlista() {
        

        // Creamos ArrayList para reservas
        List<Reservacion> listaReservaciones = new ArrayList<>();

        return listaReservaciones;
    }

    public void actualizarReservacion() {
        listaReservaciones.clear();
        List<Reservacion> listaReservaciones1 = new ArrayList<>();
        listaReservaciones1 = cargarCSVlista();
        LocalDate fechaHoy = LocalDate.now();

        for (Reservacion reserva : listaReservaciones1) {
            if (reserva.getFinHuesped().isBefore(fechaHoy) || (reserva.getFinHuesped().isEqual(fechaHoy) && LocalTime.now().isAfter(LocalTime.NOON))) { //verifica si ya acabo el tiempo de reserva
                reserva.setEstado("Finalizada");
                //System.out.println("Paso if 1");
            } else if (reserva.getEstado().equalsIgnoreCase("En espera") && (reserva.getIncioHuesped().isBefore(fechaHoy) || reserva.getIncioHuesped().isEqual(fechaHoy))) { //verifica si ya empezo una reservacion que hiciste para despues
                reserva.setEstado("Vigente");
                //System.out.println("Paso if 2");
            } else if (reserva.getEstado().equals("Finalizada") && reserva.getFinHuesped().isAfter(fechaHoy)) { //verifica si ya habia terminado tu reservacion pero ampliaste esta misma
                reserva.setEstado("Vigente");
                //System.out.println("Paso if 3");
            }
            listaReservaciones.add(reserva);
        }
        sobreescribirCSV();
    }

    public void agregarReservacion(Reservacion reservacion, List<Habitacion> listaHabitacion, List<Huesped> listaHuesped) {
        
    }

    public void sobreescribirCSV() {
        
    }
    public void sobreescribirCSVxLista(List<Reservacion> listaReservaciones) {
        
    }
    public void escribirCSV() {
        
    }

    public int numeroLineas() {
        int numero = 0;
        try (CSVReader lector = new CSVReader(new FileReader("reservaciones.csv"))) {
            try {
                while ((lector.readNext()) != null) {
                    numero++;
                }
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, "Error al leer el archivo CSV", ex);
        }

        return numero;
    }

    public boolean existeUsuarioReservacion(int id_usuario) {
        boolean existe = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("reservaciones.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    // Verificamos si existe una reservación para dicho huesped
                    if (id_usuario == Integer.parseInt(nextLine[2])) {
                        existe = true;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ReservacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(ReservacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReservacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] nextLine;
        return existe;
    }

    public boolean verificacionDisponibilidadHabitacion(List<Habitacion> listaHabitaciones, int id_habitacion) {
    for (Habitacion habitacion : listaHabitaciones) {
        if (habitacion.getId() == id_habitacion && habitacion.getEstado().equals("Disponible")) {
            return false;
        }
    }
    return true;
}

    public void leerTodoReservacion() {
        actualizarReservacion();
        try {
            CSVReader reader = new CSVReader(new FileReader("reservaciones.csv"));
            String[] nextLine;
            System.out.println("-----------------------------");
            System.out.println("ID   id_Habitacion  id_Huesped     Servicios        Estado        F. Ingreso     F.Salida   ");
            System.out.println("-----------------------------");
            try {
                while ((nextLine = reader.readNext()) != null) {

                    System.out.println(nextLine[0] + "       " + nextLine[1] + "        " + nextLine[2] + "    " + nextLine[3] + "        " + nextLine[4] + "    " + nextLine[5] + "    " + nextLine[6]);
                    System.out.println("-----------------------------");

                }
            } catch (IOException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerTiempoReservacionTiempo(List<Reservacion> listaReservaciones) {
    
    }
    public void leerTiempoReservacionEspera(List<Reservacion> listaReservaciones) {
    
    }
    public void leerTiempoReservacionFinalizada(List<Reservacion> listaReservaciones) {
    
    }
    public void ampliarReservacion() {
        
    }
    public void registrarSalida(List<Reservacion> listaReservaciones, List<Habitacion> listaHabitaciones, List<Huesped> listaHuespedes) {
    Scanner sc = new Scanner(System.in);

    // Solicitar DNI del huésped
    System.out.print("Ingrese DNI del huésped: ");
    int dni = sc.nextInt();
    
    // Buscar el huésped por DNI
    Huesped huesped = null;
    for (Huesped h : listaHuespedes) {
        if (h.getDNI() == dni) {
            huesped = h;
            break;
        }
    }

    // Si no se encuentra el huésped
    if (huesped == null) {
        System.out.println("No se encontró un huésped con ese DNI.");
        return;
    }

    // Buscar la reservación activa del huésped (estado "Vigente")
    /*Reservacion reservacionActiva = null;
    for (Reservacion reservacion : listaReservaciones) {
        if (reservacion.getHuespedd().getDNI() == dni && reservacion.getEstado().equalsIgnoreCase("Vigente")) {
            reservacionActiva = reservacion;
            break;
        }
    }*/
/*
    // Si no se encuentra una reservación activa
    if (reservacionActiva == null) {
        System.out.println("No se encontró una reservación activa para este huésped.");
        return;
    }

    // Mostrar los detalles de la reservación
    System.out.println("Detalles de la reservación actual:");
    System.out.println("ID de Reservación: " + reservacionActiva.getIdReserva());
    System.out.println("Habitación: " + reservacionActiva.getHabitacionn().getId());
    System.out.println("Fecha de ingreso: " + reservacionActiva.getIncioHuesped());
    System.out.println("Fecha de salida programada: " + reservacionActiva.getFinHuesped());
    
    // Preguntar si desea registrar la salida
    System.out.print("¿Desea registrar la salida del huésped? (s/n): ");
    char confirmacion = sc.next().charAt(0);

    if (confirmacion == 's' || confirmacion == 'S') {
        // Cambiar el estado del huésped a 0 (inactivo)
        huesped.setEstado(0);

        // Cambiar el estado de la habitación a "Disponible"
        Habitacion habitacion = reservacionActiva.getHabitacionn();
        habitacion.setEstado("Disponible");
        
        for (Habitacion h : listaHabitaciones) {
        if (h.getId() == habitacion.getId()) {
            h.setEstado("Disponible");  // Actualiza el estado en la lista principal
            break;
        }
        }
        // Cambiar el estado de la reservación a "Finalizada"
        reservacionActiva.setEstado("Finalizada");
        for (Reservacion r : listaReservaciones) {
        if (r.getIdReserva()== reservacionActiva.getIdReserva()) {
            r.setEstado("Finalizada"); // Actualiza el estado en la lista principal
            break;
        }
        }
        sobreescribirCSVxLista(listaReservaciones);
        System.out.println("La salida del huésped ha sido registrada exitosamente.");
        System.out.println("La habitación ahora está disponible.");
        
        //Agarra las listas y sobreEscribe en el CSV
        habitacionCrud.sobreescribirCSV(listaHabitaciones);
        huespedCrud.sobreescribirCSV(listaHuespedes);
    } else {
        System.out.println("Operación cancelada.");
    }*/
}
    public void registrarEntrada(List<Reservacion> listaReservaciones, List<Habitacion> listaHabitaciones, List<Huesped> listaHuespedes) {
  /*  Scanner sc = new Scanner(System.in);
    
    // Solicitar el DNI del huésped
    System.out.print("Ingrese DNI del huésped: ");
    int dniHuesped = sc.nextInt();
    sc.nextLine();
    
    // Buscar el huésped en la lista de huéspedes
    Huesped huespedEncontrado = null;
    for (Huesped huesped : listaHuespedes) {
        if (huesped.getDNI() == dniHuesped) {
            huespedEncontrado = huesped;
            break;
        }
    }

    // Si el huésped no se encuentra, notificar al usuario
    if (huespedEncontrado == null) {
        System.out.println("Huésped no encontrado.");
        return;
    }

    // Buscar la reserva del huésped
    Reservacion reservacionEncontrada = null;
    for (Reservacion reservacion : listaReservaciones) {
        if (reservacion.getHuespedd().getDNI() == dniHuesped && reservacion.getEstado().equals("En espera")) {
            reservacionEncontrada = reservacion;
            break;
        }
    }

    // Si no se encuentra la reserva vigente, notificar al usuario
    if (reservacionEncontrada == null) {
        
        System.out.println("No se encontro una reserva en espera para el huesped con DNI: " + dniHuesped);
        return;
    }

    // Mostrar detalles de la reserva
    System.out.println("Reserva encontrada:");
    System.out.printf("ID Reserva: %d\n", reservacionEncontrada.getIdReserva());
    System.out.printf("ID Habitación: %d\n", reservacionEncontrada.getHabitacionn().getId());
    System.out.printf("Fecha de Ingreso: %s\n", reservacionEncontrada.getIncioHuesped());
    System.out.printf("Fecha de Salida: %s\n", reservacionEncontrada.getFinHuesped());
    
    // Confirmar el check-in
    System.out.print("¿Desea registrar el check-in? (s/n): ");
    char confirmacion = sc.next().charAt(0);

    if (confirmacion == 's' || confirmacion == 'S') {
        // Cambiar el estado de la habitación a "Ocupada"
        Habitacion habitacion = reservacionEncontrada.getHabitacionn();
        habitacion.setEstado("Ocupada");
        // Buscar la habitación en la lista principal de habitaciones y actualizar su estado
    for (Habitacion h : listaHabitaciones) {
        if (h.getId() == habitacion.getId()) {
            h.setEstado("Ocupada");  // Actualiza el estado en la lista principal
            break;
        }
    }
        reservacionEncontrada.setEstado("Vigente");
        
        sobreescribirCSVxLista(listaReservaciones);
        habitacionCrud.sobreescribirCSV(listaHabitaciones);

        System.out.println("Check-in registrado con exito para el huesped: " + huespedEncontrado.getNombre() + " " + huespedEncontrado.getApellido());
    } else {
        System.out.println("Check-in cancelado.");
    }*/
}        
}

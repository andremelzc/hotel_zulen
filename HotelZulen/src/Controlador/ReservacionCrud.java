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
        // Cargamos el csv de huespedes en un hashmap
        HashMap<Integer, Huesped> mapaHuesped = huespedCrud.cargarCSVHash();

        // Cargamos el csv de habitaciones en un hashmap
        HashMap<Integer, Habitacion> mapaHabitacion = habitacionCrud.cargarCSVHash();

        // Creamos ArrayList para reservas
        List<Reservacion> listaReservaciones = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader("reservaciones.csv"));
            String[] nextLine;

            try {

                while ((nextLine = reader.readNext()) != null) {
                    int idReserva = Integer.parseInt(nextLine[0]);
                    int idHabitacion = Integer.parseInt(nextLine[1]);
                    int idHuesped = Integer.parseInt(nextLine[2]);
                    String servicios = nextLine[3];
                    String estado = nextLine[4];
                    LocalDate inicioHuesped = LocalDate.parse(nextLine[5]);
                    LocalDate finHuesped = LocalDate.parse(nextLine[6]);

                    String[] serviciosArray = servicios.split(",\\s*");
                    List<Servicios> listaServicios = new ArrayList<>();

                    if (serviciosArray.length == 2) {
                        listaServicios.add(new Servicios(1, "HouseKeeping", 70));
                        listaServicios.add(new Servicios(2, "FitnessCenter", 30));
                    } else if (serviciosArray[0].equals("HouseKeeping")) {
                        listaServicios.add(new Servicios(1, "HouseKeeping", 70));
                    } else if (serviciosArray[0].equals("FitnessCenter")) {
                        listaServicios.add(new Servicios(2, "FitnessCenter", 30));
                    }

                    Habitacion habitacion = mapaHabitacion.get(idHabitacion);
                    Huesped huesped = mapaHuesped.get(idHuesped);

                    if (huesped != null && habitacion != null) {

                        Reservacion reservacion = new Reservacion(idReserva, habitacion, huesped, listaServicios, estado, inicioHuesped, finHuesped);
                        listaReservaciones.add(reservacion);

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
        reservacion.setIdReserva(numeroLineas() + 1);

        reservacion.getHuespedd().setEstado(1);
        reservacion.getHabitacionn().setEstado("Reservado");
        habitacionCrud.sobreescribirCSV(listaHabitacion);
        huespedCrud.sobreescribirCSV(listaHuesped);
        //Agregamos a la listaa
        listaReservaciones.add(reservacion);
    }

    public void sobreescribirCSV() {
        StringBuilder serviciosString = new StringBuilder();
        //Lista para enviar al CSV
        try (CSVWriter escritor = new CSVWriter(new FileWriter("reservaciones.csv", false))) {
            int i = 0;
            for (Reservacion reservacion : listaReservaciones) {
                i++;
                reservacion.setIdReserva(i);
                serviciosString.setLength(0);
                for (Servicios servicio : reservacion.getServicios()) {
                    serviciosString.append(servicio.getConcepto()).append(", ");
                }
                if (serviciosString.length() > 0) {
                    serviciosString.setLength(serviciosString.length() - 2); // Elimina la última coma y espacio
                }
                String servicios = serviciosString.toString();

                String[] datos = {String.valueOf(reservacion.getIdReserva()), String.valueOf(reservacion.getHabitacionn().getId()),
                        String.valueOf(reservacion.getHuespedd().getID()), servicios, String.valueOf(reservacion.getEstado()), String.valueOf(reservacion.getIncioHuesped()),
                        String.valueOf(reservacion.getFinHuesped())};
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sobreescribirCSVxLista(List<Reservacion> listaReservaciones) {
        StringBuilder serviciosString = new StringBuilder();
        //Lista para enviar al CSV
        try (CSVWriter escritor = new CSVWriter(new FileWriter("reservaciones.csv", false))) {
            int i = 0;
            for (Reservacion reservacion : listaReservaciones) {
                i++;
                reservacion.setIdReserva(i);
                serviciosString.setLength(0);
                for (Servicios servicio : reservacion.getServicios()) {
                    serviciosString.append(servicio.getConcepto()).append(", ");
                }
                if (serviciosString.length() > 0) {
                    serviciosString.setLength(serviciosString.length() - 2); // Elimina la última coma y espacio
                }
                String servicios = serviciosString.toString();

                String[] datos = {String.valueOf(reservacion.getIdReserva()), String.valueOf(reservacion.getHabitacionn().getId()),
                        String.valueOf(reservacion.getHuespedd().getID()), servicios, String.valueOf(reservacion.getEstado()), String.valueOf(reservacion.getIncioHuesped()),
                        String.valueOf(reservacion.getFinHuesped())};
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void escribirCSV() {
        StringBuilder serviciosString = new StringBuilder();
        //Lista para enviar al CSV
        try (CSVWriter escritor = new CSVWriter(new FileWriter("reservaciones.csv", true))) {
            for (Reservacion reservacion : listaReservaciones) {
                for (Servicios servicio : reservacion.getServicios()) {
                    serviciosString.append(servicio.getConcepto()).append(", ");
                }
                if (serviciosString.length() > 0) {
                    serviciosString.setLength(serviciosString.length() - 2); // Elimina la última coma y espacio
                }
                String servicios = serviciosString.toString();

                String[] datos = {
                        String.valueOf(reservacion.getIdReserva()),
                        String.valueOf(reservacion.getHabitacionn().getId()),
                        String.valueOf(reservacion.getHuespedd().getID()), servicios,
                        String.valueOf(reservacion.getEstado()),
                        String.valueOf(reservacion.getIncioHuesped()),
                        String.valueOf(reservacion.getFinHuesped())};
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            return true;
        }
    }
    return false;
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
    // Cabecera de la tabla con formato
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-8s %-15s %-15s %-27s %-10s %-15s %-15s %-20s\n", 
            "ID", "id_Habitacion", "DNI_Huesped", "Servicios", "Estado", "F. Ingreso", "F. Salida", "Tiempo Restante");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

    // Obtener la fecha actual
    LocalDate fechaActual = LocalDate.now();
    
    // Iterar sobre la lista de reservaciones
    for (Reservacion reservacion : listaReservaciones) {
        // Filtrar solo las reservaciones con estado "Vigente"
        if (reservacion.getEstado().equalsIgnoreCase("Vigente")) {
            // Obtener la fecha de salida
            LocalDate fechaSalida = reservacion.getFinHuesped();

            // Calcular la diferencia entre la fecha actual y la fecha de salida
            Period diferencia = Period.between(fechaActual, fechaSalida);

            // Mostrar los días, meses y años restantes
            String tiempoRestante = diferencia.getYears() + " años, " +
                                    diferencia.getMonths() + " meses, " +
                                    diferencia.getDays() + " días";

            // Obtener los servicios de la reservación como una cadena de texto
            String servicios = reservacion.getServicios().stream()
                                  .map(Servicios::getConcepto)  // Usamos el getter para obtener el concepto
                                  .reduce((s1, s2) -> s1 + ", " + s2)
                                  .orElse("N/A");

            // Imprimir la información formateada
            System.out.printf("%-8s %-15s %-15s %-27s %-10s %-15s %-15s %-20s\n", 
                    reservacion.getIdReserva(), 
                    reservacion.getHabitacionn().getId(), 
                    reservacion.getHuespedd().getDNI(), 
                    servicios, 
                    reservacion.getEstado(), 
                    reservacion.getIncioHuesped(), 
                    reservacion.getFinHuesped(), 
                    tiempoRestante);
            
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
}
    public void leerTiempoReservacionEspera(List<Reservacion> listaReservaciones) {
    
    // Cabecera de la tabla con formato
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-8s %-15s %-15s %-27s %-10s %-15s %-15s %-20s\n", 
            "ID", "id_Habitacion", "DNI_Huesped", "Servicios", "Estado", "F. Ingreso", "F. Salida", "Tiempo Restante");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

    // Obtener la fecha actual
    LocalDate fechaActual = LocalDate.now();
    
    // Iterar sobre la lista de reservaciones
    for (Reservacion reservacion : listaReservaciones) {
        // Filtrar solo las reservaciones con estado "En espera"
        if (reservacion.getEstado().equalsIgnoreCase("En espera")) {
            // Obtener la fecha de salida
            LocalDate fechaSalida = reservacion.getFinHuesped();

            // Calcular la diferencia entre la fecha actual y la fecha de salida
            Period diferencia = Period.between(fechaActual, fechaSalida);

            // Mostrar los días, meses y años restantes
            String tiempoRestante = diferencia.getYears() + " años, " +
                                    diferencia.getMonths() + " meses, " +
                                    diferencia.getDays() + " días";

            // Obtener los servicios de la reservación como una cadena de texto
            String servicios = reservacion.getServicios().stream()
                                  .map(Servicios::getConcepto)  // Usamos el getter para obtener el concepto
                                  .reduce((s1, s2) -> s1 + ", " + s2)
                                  .orElse("N/A");

            // Imprimir la información formateada
            System.out.printf("%-8s %-15s %-15s %-27s %-10s %-15s %-15s %-20s\n", 
                    reservacion.getIdReserva(), 
                    reservacion.getHabitacionn().getId(), 
                    reservacion.getHuespedd().getDNI(), 
                    servicios, 
                    reservacion.getEstado(), 
                    reservacion.getIncioHuesped(), 
                    reservacion.getFinHuesped(), 
                    tiempoRestante);
            
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
}
    public void leerTiempoReservacionFinalizada(List<Reservacion> listaReservaciones) {
    
    // Cabecera de la tabla con formato
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-8s %-15s %-15s %-27s %-10s %-15s %-15s %-20s\n", 
            "ID", "id_Habitacion", "DNI_Huesped", "Servicios", "Estado", "F. Ingreso", "F. Salida", "Tiempo Restante");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

    // Obtener la fecha actual
    LocalDate fechaActual = LocalDate.now();
    
    // Iterar sobre la lista de reservaciones
    for (Reservacion reservacion : listaReservaciones) {
        // Filtrar solo las reservaciones con estado "En espera"
        if (reservacion.getEstado().equalsIgnoreCase("Finalizada")) {
            // Obtener la fecha de salida
            LocalDate fechaSalida = reservacion.getFinHuesped();

            // Calcular la diferencia entre la fecha actual y la fecha de salida
            Period diferencia = Period.between(fechaActual, fechaSalida);

            // Mostrar los días, meses y años restantes
            String tiempoRestante = diferencia.getYears() + " años, " +
                                    diferencia.getMonths() + " meses, " +
                                    diferencia.getDays() + " días";

            // Obtener los servicios de la reservación como una cadena de texto
            String servicios = reservacion.getServicios().stream()
                                  .map(Servicios::getConcepto)  // Usamos el getter para obtener el concepto
                                  .reduce((s1, s2) -> s1 + ", " + s2)
                                  .orElse("N/A");

            // Imprimir la información formateada
            System.out.printf("%-8s %-15s %-15s %-27s %-10s %-15s %-15s %-20s\n", 
                    reservacion.getIdReserva(), 
                    reservacion.getHabitacionn().getId(), 
                    reservacion.getHuespedd().getDNI(), 
                    servicios, 
                    reservacion.getEstado(), 
                    reservacion.getIncioHuesped(), 
                    reservacion.getFinHuesped(), 
                    tiempoRestante);
            
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
}
    public void ampliarReservacion() {
        listaReservaciones.clear();
        List<Reservacion> listaReservaciones1 = new ArrayList<>();
        listaReservaciones1 = cargarCSVlista();
        LocalDate fechaHoy = LocalDate.now();
        int idReservacion;
        boolean flag = false;

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID de la reservacion que desea ampliar: ");
        idReservacion = sc.nextInt();

        for (Reservacion reserva : listaReservaciones1) {
            if (reserva.getIdReserva() == idReservacion) {
                flag = true;
                if (reserva.getFinHuesped().isBefore(fechaHoy) || (reserva.getFinHuesped().isEqual(fechaHoy) && LocalTime.now().isAfter(LocalTime.NOON))) { //verifica si ya acabo el tiempo de reserva
                    System.out.println("La reservacion ya ha finalizado, no se puede ampliar");
                } else {
                    System.out.println("La reservacion seleccionada es la siguiente: ");
                    System.out.println("ID: " + reserva.getIdReserva());
                    System.out.println("Habitacion: " + reserva.getHabitacionn().getId());
                    System.out.println("DNI del Huesped: " + reserva.getHuespedd().getDNI());
                    System.out.println("Servicios: ");
                    for (Servicios servicio : reserva.getServicios()) {
                        System.out.println("    " + servicio.getConcepto());
                    }
                    System.out.println("Estado: " + reserva.getEstado());
                    System.out.println("Fecha de ingreso: " + reserva.getIncioHuesped());
                    System.out.println("Fecha de salida: " + reserva.getFinHuesped());

                    System.out.println("Ingrese la nueva fecha de salida: ");
                    LocalDate nuevaFechaSalida = LocalDate.parse(sc.next());
                    reserva.setFinHuesped(nuevaFechaSalida);
                    reserva.setEstado("Vigente");
                }

            } else {
                listaReservaciones.add(reserva);
                continue;
            }
            listaReservaciones.add(reserva);
        }

        if (!flag) {
            System.out.println("No se encontro la reservacion con el ID ingresado");
        }
        sobreescribirCSV();
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
    Reservacion reservacionActiva = null;
    for (Reservacion reservacion : listaReservaciones) {
        if (reservacion.getHuespedd().getDNI() == dni && reservacion.getEstado().equalsIgnoreCase("Vigente")) {
            reservacionActiva = reservacion;
            break;
        }
    }

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
    }
}
    public void registrarEntrada(List<Reservacion> listaReservaciones, List<Habitacion> listaHabitaciones, List<Huesped> listaHuespedes) {
    Scanner sc = new Scanner(System.in);
    
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
    }
}        
}

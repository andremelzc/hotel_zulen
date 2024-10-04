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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;
import java.time.Period;                      // Cálculo de diferencia entre fechas
import java.time.format.DateTimeFormatter;    // Formato de fechas


/**
 *
 * @author PC
 */
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
                    LocalDate inicioHuesped = LocalDate.parse(nextLine[4]);
                    LocalDate finHuesped = LocalDate.parse(nextLine[5]);

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

                        Reservacion reservacion = new Reservacion(idReserva, habitacion, huesped, listaServicios, inicioHuesped, finHuesped);
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

    public void agregarReservacion(Reservacion reservacion) {
        listaReservaciones.clear();
        reservacion.setIdReserva(numeroLineas() + 1);

        //Agregamos a la listaa
        listaReservaciones.add(reservacion);
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
                
                String[] datos = {String.valueOf(reservacion.getIdReserva()), String.valueOf(reservacion.getHabitacionn().getId()),
                    String.valueOf(reservacion.getHuespedd().getID()), servicios, String.valueOf(reservacion.getIncioHuesped()),
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

    public boolean existeHabitacionReservacion(int id_habitacion) {
        boolean existe = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("reservaciones.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    // Verificamos si existe una reservación para dicho huesped
                    if (id_habitacion == Integer.parseInt(nextLine[1])) {
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
    
    public void leerTodoReservacion() {
        try {
            CSVReader reader = new CSVReader(new FileReader("reservaciones.csv"));
            String[] nextLine;
            System.out.println("-----------------------------");
            System.out.println("ID      id_Habitacion  id_Huesped      Servicios     F. Ingreso     F.Salida   ");
            System.out.println("-----------------------------");
            try {
                while ((nextLine = reader.readNext()) != null) {
                    
                        System.out.println(nextLine[0] + "       " + nextLine[1] + "      " + nextLine[2] + "    " + nextLine[3] + "    " + nextLine[4]+ "    " + nextLine[5]);
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
    
    public void leerTiempoReservacionTiempo() {
        try {
            CSVReader reader = new CSVReader(new FileReader("reservaciones.csv"));
            String[] nextLine;
            System.out.println("-----------------------------");
            System.out.println("ID      id_Habitacion  id_Huesped      Servicios     F. Ingreso     F. Salida    Tiempo Restante");
            System.out.println("-----------------------------");
            try {
                // Definir el formato de fecha para analizar las fechas en el CSV
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
                // Obtener la fecha actual
                LocalDate fechaActual = LocalDate.now();
                
                while ((nextLine = reader.readNext()) != null) {
                    // Parsear la fecha almacenada en nextLine[5] a LocalDate
                    LocalDate fechaSalida = LocalDate.parse(nextLine[5], formatter);
                    
                    // Calcular el tiempo entre la fecha actual y la fecha de salida
                    Period diferencia = Period.between(fechaActual, fechaSalida);
                    
                    // Mostrar los días, meses y años restantes
                    String tiempoRestante = diferencia.getYears() + " anos, " +
                                            diferencia.getMonths() + " meses, " +
                                            diferencia.getDays() + " días";

                    // Imprimir la información
                    System.out.println(nextLine[0] + "       " + nextLine[1] + "      " + nextLine[2] + "    " + nextLine[3] + "    " + nextLine[4] + "    " + nextLine[5] + "    " + tiempoRestante);
                    System.out.println("-----------------------------");
                }
            } catch (IOException | CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

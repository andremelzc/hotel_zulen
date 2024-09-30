/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;

/**
 *
 * @author PC
 */
public class ReservacionCrud {

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

                    Habitacion habitacion = mapaHabitacion.get(idHabitacion);
                    Huesped huesped = mapaHuesped.get(idHuesped);

                    if (huesped != null && habitacion != null) {

                        Reservacion reservacion = new Reservacion(idReserva, habitacion, huesped, servicios, inicioHuesped, finHuesped);
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

    public void agregarReservacion() {

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
}

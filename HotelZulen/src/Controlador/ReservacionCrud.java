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

    List<Reservacion> listaReservacion = new ArrayList<>();
    
    public List<Personal> cargarCSVlista() {
        // Cargamos el csv en un hashmap
        
        List<Personal> listaPersonales = new ArrayList<>();
        try {
            // Leemos el csv
            CSVReader reader = new CSVReader(new FileReader("huespedes.csv"));
            String[] nextLine;

            try {

                while ((nextLine = reader.readNext()) != null) {
               

                }

            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersonales;
    }
    

    public void agregarReservacion() {

    }

    public boolean existeUsuarioReservacion(int id_usuario) {
        boolean existe = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("reservaciones.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null){
                    // Verificamos si existe una reservación para dicho huesped
                    if(id_usuario==Integer.parseInt(nextLine[2])){
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
                while ((nextLine = reader.readNext()) != null){
                    // Verificamos si existe una reservación para dicho huesped
                    if(id_habitacion==Integer.parseInt(nextLine[1])){
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

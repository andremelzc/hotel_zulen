/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import Controlador.HabitacionCrud;
import Controlador.HuespedCrud;
import Controlador.PersonalCrud;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.TipoDeHabitacion;

/**
 *
 * @author Suyco
 */
public class MainPrueba {


   public static void main(String[] args) {

        HabitacionCrud HabitacionCrud = new HabitacionCrud();
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        try {
            // Leemos el csv
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;
           
            try{
                
                while ((nextLine = reader.readNext()) != null) {
                    System.out.println(nextLine[0]);        
                }
            }catch (IOException  ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);     
            }catch (CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
    }catch (FileNotFoundException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

}
}
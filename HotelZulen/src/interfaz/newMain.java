/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import Controlador.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import modelo.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Personal;

/**
 *
 * @author PC
 */
public class newMain {

    public static void main(String[] args) {

        HuespedCrud huespedcrud= new HuespedCrud();
        List<Huesped> listaHuespedes = new ArrayList<>();
        try {
            // Leemos el csv
            CSVReader reader = new CSVReader(new FileReader("huespedes.csv"));
            String[] nextLine;

            try {

                while ((nextLine = reader.readNext()) != null) {
                    System.out.println(nextLine[0]);
                    Huesped huesped = new Huesped(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2], Integer.parseInt(nextLine[3]),
                            Integer.parseInt(nextLine[4]), nextLine[5], nextLine[6], nextLine[7], Integer.parseInt(nextLine[8]));
                    listaHuespedes.add(huesped);

                }

            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
}

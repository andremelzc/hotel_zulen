/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas_giron;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Miguel Giron
 */
public class InicioSesion {
    
    public boolean verificarValidezPersonal(String usu, String contra, String fun) {
        boolean validez = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("personales.csv"));
            String[] nextLine;

            try {
                while ((nextLine = reader.readNext()) != null) {
                    if ("1".equals(nextLine[3]) && usu.equals(nextLine[4]) && contra.equals(nextLine[5]) && fun.equals(nextLine[6]) ) {
                        System.out.println("Bienvenido:");
                        System.out.println(nextLine[0] + "  " + nextLine[1] + "    " + nextLine[2]+ "    " + nextLine[4]+ "    " + nextLine[5]+ "    " + nextLine[6]);
                        System.out.println("-----------------------------");
                        validez = true;
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    return validez;
    }
}

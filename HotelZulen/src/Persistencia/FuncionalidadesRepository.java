/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Controlador.HabitacionCrud;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class FuncionalidadesRepository {

    public FuncionalidadesRepository() {
    }
    
    public void vaciarCSV(String nombreArchivo) {
        try {
            // Abre el archivo en modo escritura y escribe una cadena vacía
            FileWriter writer = new FileWriter(nombreArchivo);
            writer.write(""); // Vacía el contenido del archivo
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int numeroLineas(String nombreArchivo) {
        int numero = 0;
        try (CSVReader lector = new CSVReader(new FileReader(nombreArchivo))) {
            try {
                while ((lector.readNext()) != null) {
                    numero++;
                }
            } catch (CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, "Error al leer el archivo CSV", ex);
        }

        return numero;
    }
}

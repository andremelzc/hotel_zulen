/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Habitacion;
import modelo.TipoDeHabitacion;

/**
 *
 * @author Suyco
 */
public class TipoHabitacionRepository implements IRepository<TipoDeHabitacion> {
    
    @Override
    public List<TipoDeHabitacion> cargarCSVtoLista(String archivo) throws IOException {
        
        List<TipoDeHabitacion> tiposCargados = new ArrayList<>();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                TipoDeHabitacion tipoHabitacion = new TipoDeHabitacion(
                        Integer.parseInt(nextLine[0]),
                        nextLine[1],
                        Double.parseDouble(nextLine[2])
                );
                tiposCargados.add(tipoHabitacion);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }
        
        return tiposCargados;
    }
    
    @Override
    public void cargarListaToCSV(List<TipoDeHabitacion> lista, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
            
            for (TipoDeHabitacion tipoDeHabitacion : lista) {
                
                String[] data = {
                    String.valueOf(tipoDeHabitacion.getId()),
                    tipoDeHabitacion.getConcepto(), String.valueOf(tipoDeHabitacion.getPrecio())};
                writer.writeNext(data); // Escribe la nueva línea en el archivo
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
    
    @Override
    public void cargarRegistroToCSV(TipoDeHabitacion elemento, String archivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

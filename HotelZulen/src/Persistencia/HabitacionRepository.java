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

public class HabitacionRepository implements IRepository<Habitacion> {
    
    
    @Override
    public List<Habitacion> cargarCSVtoLista(String archivo) throws IOException {
        List<Habitacion> habitacionCargada = new ArrayList<>();
        
        TipoHabitacionRepository tipoHabitacionRepo = new TipoHabitacionRepository();
        List<TipoDeHabitacion> tiposCargados = tipoHabitacionRepo.cargarCSVtoLista("tipoHabitacion.csv");
        
        TipoDeHabitacion tipoHabitacion = new TipoDeHabitacion();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] nextLine;
           
            while ((nextLine = csvReader.readNext()) != null) {

                int idHabitacion = Integer.parseInt(nextLine[0]); //ID Habitacion
                int idTipoHabitacion = Integer.parseInt(nextLine[1]); //ID tipoHabitacion
                String idPiso = nextLine[2]; //Piso
                String estado = nextLine[3]; // estado

                Habitacion habitacion = new Habitacion(idHabitacion,
                                                       tipoHabitacion.obtenerPorId(tiposCargados, idTipoHabitacion),
                                                       idPiso,
                                                       estado);
                habitacionCargada.add(habitacion);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }
 
        return habitacionCargada;
        
        
    }

    @Override
    public void cargarListaToCSV(List<Habitacion> lista, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
        
            for (Habitacion elemento : lista) {
            
                String[] data = {
                    String.valueOf(elemento.getId()),
                    String.valueOf(elemento.getTipoHabitacion().getId()),
                    String.valueOf(elemento.getPiso()),
                    String.valueOf(elemento.getEstado()),
                };
            writer.writeNext(data); // Escribe la nueva línea en el archivo
        }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    @Override
    public void cargarRegistroToCSV(Habitacion elemento, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {

                String[] data = {
                    String.valueOf(elemento.getId()),
                    String.valueOf(elemento.getTipoHabitacion().getId()),
                    String.valueOf(elemento.getPiso()),
                    String.valueOf(elemento.getEstado()),
                };
            writer.writeNext(data); // Escribe la nueva línea en el archivo
        
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
    
}

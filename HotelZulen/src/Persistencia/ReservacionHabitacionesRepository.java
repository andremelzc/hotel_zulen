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
import modelo.Reservacion;
import modelo.ResevacionHabitacion;

public class ReservacionHabitacionesRepository implements IRepository<ResevacionHabitacion> {
    
    
    @Override
    public List<ResevacionHabitacion> cargarCSVtoLista(String archivo) throws IOException {
        
        List<ResevacionHabitacion> reservaHabitacionCargados = new ArrayList<>();
        List<Reservacion> reservacionesCargadas = new ArrayList<>();
        List<Habitacion> habitacionesCargadas = new ArrayList<>();
        
        ReservacionRepository reservaRepo = new ReservacionRepository();
        HabitacionRepository habitacionRepo = new HabitacionRepository();
        
        reservacionesCargadas = reservaRepo.cargarCSVtoLista("reservaciones.csv");
        habitacionesCargadas = habitacionRepo.cargarCSVtoLista("habitaciones.csv");
        
        Habitacion nuevaHabitacion = new Habitacion();
        Reservacion nuevaReservacion = new Reservacion();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] nextLine;
            
            while ((nextLine = csvReader.readNext()) != null) {
                int idReserva = Integer.parseInt(nextLine[0]);
                int idHabitacion = Integer.parseInt(nextLine[1]);

                
                ResevacionHabitacion reservaServicio = new ResevacionHabitacion(
                                                      nuevaReservacion.obtenerPorId(reservacionesCargadas, idReserva),
                                                      nuevaHabitacion.obtenerPorId(habitacionesCargadas, idHabitacion)
                                                      );
                reservaHabitacionCargados.add(reservaServicio);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }        
        return reservaHabitacionCargados;
    }


    @Override
    public void cargarListaToCSV(List<ResevacionHabitacion> lista, String archivo) {
     
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
            for(ResevacionHabitacion elemento : lista){
                String[] data = {
                    String.valueOf(elemento.getReserva().getIdReserva()), 
                    String.valueOf(elemento.getHabitacion().getId()),
                };
                writer.writeNext(data); 
            }
    }  catch (IOException e) {
        e.printStackTrace(); 
    }
    } 

    @Override
    public void cargarRegistroToCSV(ResevacionHabitacion elemento, String archivo) {
       // Guardar en el archivo CSV
    try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {
        String[] data = {
            String.valueOf(elemento.getReserva().getIdReserva()), 
            String.valueOf(elemento.getHabitacion().getId()),
        };
        writer.writeNext(data); 
    } catch (IOException e) {
        e.printStackTrace(); 
    }
    }
}
    


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
import modelo.Reservacion;
import modelo.ReservacionServicio;
import modelo.ServiciosAdicionales;

/**
 *
 * @author Suyco
 */
public class ReservacionServicioRepository implements IRepository<ReservacionServicio> {

  
    
    @Override
    public List<ReservacionServicio> cargarCSVtoLista(String archivo) throws IOException {
        
        List<ReservacionServicio> reservacionServicioCargada = new ArrayList<>();
        List<Reservacion> reservacionesCargadas = new ArrayList<>();
        List<ServiciosAdicionales> serviciosCargados = new ArrayList<>();
        
        ReservacionRepository reservaRepo = new ReservacionRepository();      
        ServiciosAdicionalesRepository serviciosRepo = new ServiciosAdicionalesRepository();        
        
        reservacionesCargadas = reservaRepo.cargarCSVtoLista("reservaciones.csv");
        serviciosCargados = serviciosRepo.cargarCSVtoLista("serviciosAdicionales.csv");
        
        Reservacion nuevaReserva =new Reservacion();
        ServiciosAdicionales nuevoServicio =new ServiciosAdicionales();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] nextLine;
            

            while ((nextLine = csvReader.readNext()) != null) {
                int idReserva = Integer.parseInt(nextLine[0]);
                int idServicio = Integer.parseInt(nextLine[1]);

                
                ReservacionServicio reservaServicio = new ReservacionServicio(
                                                      nuevaReserva.obtenerPorId(reservacionesCargadas, idReserva),
                                                      nuevoServicio.obtenerPorId(serviciosCargados, idServicio)
                                                      );
                reservacionServicioCargada.add(reservaServicio);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }
        
        return reservacionServicioCargada;
    }



    @Override
    public void cargarListaToCSV(List<ReservacionServicio> lista, String archivo) {
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
            for(ReservacionServicio elemento : lista){
                String[] data = {
                    String.valueOf(elemento.getReserva().getIdReserva()), // Ajusta según los campos que tenga ReservacionHuesped
                    String.valueOf(elemento.getServicio().getId()),
                };
                writer.writeNext(data); // Escribe la nueva línea en el archivo
            }
        } catch (IOException e) {
        e.printStackTrace(); // Manejo de excepciones
        } 
    }

    @Override
    public void cargarRegistroToCSV(ReservacionServicio elemento, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {
                String[] data = {
                    String.valueOf(elemento.getReserva().getIdReserva()), // Ajusta según los campos que tenga ReservacionHuesped
                    String.valueOf(elemento.getServicio().getId()),
                };
                writer.writeNext(data); // Escribe la nueva línea en el archivo
            
        } catch (IOException e) {
        e.printStackTrace(); // Manejo de excepciones
        } 
    }
}

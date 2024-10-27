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
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ReservacionHuesped;

/**
 *
 * @author Suyco
 */
public class ReservacionHuespedRepository implements IRepository<ReservacionHuesped> {
    
    
    
    @Override
    public List<ReservacionHuesped> cargarCSVtoLista(String archivo) throws IOException {
            
        List<ReservacionHuesped> reservaHuespedCargados = new ArrayList<>();    
        List<Reservacion> reservacionesCargadas = new ArrayList<>();
        List<Huesped> huespedesCargados = new ArrayList<>();
        
        ReservacionRepository reservaRepo = new ReservacionRepository();
        HuespedRepository huespedRepo = new HuespedRepository ();
        
        reservacionesCargadas = reservaRepo.cargarCSVtoLista("reservaciones.csv");
        huespedesCargados = huespedRepo.cargarCSVtoLista("huespedes.csv");
        
        Reservacion nuevaReserva = new Reservacion();
        Huesped nuevoHuesped = new Huesped();
        
        try (CSVReader csvReader = new CSVReader(new FileReader("reservaHuesped.csv"))) {
            String[] nextLine;
            

            while ((nextLine = csvReader.readNext()) != null) {
                int idReserva = Integer.parseInt(nextLine[0]);
                int idHuesped = Integer.parseInt(nextLine[1]);

                
                ReservacionHuesped reservaHuesped = new ReservacionHuesped(
                                                      nuevaReserva.obtenerPorId(reservacionesCargadas, idReserva),
                                                      nuevoHuesped.obtenerPorId(huespedesCargados, idHuesped)
                                                      );
                reservaHuespedCargados.add(reservaHuesped);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }
        
        
        return reservaHuespedCargados;
    }



    @Override
    public void cargarListaToCSV(List<ReservacionHuesped> lista, String archivo) {
        // Guardar en el archivo CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
            for(ReservacionHuesped elemento: lista){
                String[] data = {
                    String.valueOf(elemento.getReserva().getIdReserva()), // Ajusta según los campos que tenga ReservacionHuesped
                    String.valueOf(elemento.getHuesped().getDNI()),
                };
                writer.writeNext(data); // Escribe la nueva línea en el archivo
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    @Override
    public void cargarRegistroToCSV(ReservacionHuesped elemento, String archivo) {
        // Guardar en el archivo CSV
    try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {
        String[] data = {
            String.valueOf(elemento.getReserva().getIdReserva()), // Ajusta según los campos que tenga ReservacionHuesped
            String.valueOf(elemento.getHuesped().getDNI()),
        };
        writer.writeNext(data); // Escribe la nueva línea en el archivo
    } catch (IOException e) {
        e.printStackTrace(); // Manejo de excepciones
    }
    }
}

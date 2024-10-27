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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Reservacion;

public class ReservacionRepository implements IRepository <Reservacion>{
    
    
    @Override
    public List<Reservacion> cargarCSVtoLista(String archivo) throws IOException {
        List<Reservacion> reservasCargadas = new ArrayList<>();
        
        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] nextLine;
            

            while ((nextLine = csvReader.readNext()) != null) {
                int idReserva = Integer.parseInt(nextLine[0]);
                int numHabitaciones = Integer.parseInt(nextLine[1]);
                String estado = nextLine[2];
                LocalDate inicioHuesped = LocalDate.parse(nextLine[3]);
                LocalDate finHuesped = LocalDate.parse(nextLine[4]);
                
                Reservacion reserva = new Reservacion(idReserva,
                                                      numHabitaciones,
                                                      estado,
                                                      inicioHuesped,
                                                      finHuesped);
                reservasCargadas.add(reserva);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }
 
        return reservasCargadas;
    }


    @Override
    public void cargarListaToCSV(List<Reservacion> lista, String archivo) {
       
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
        
            for (Reservacion elemento : lista) {
            
                String[] data = {
                    String.valueOf(elemento.getIdReserva()),
                    String.valueOf(elemento.getNumHabitaciones()),
                    String.valueOf(elemento.getEstado()),
                    elemento.getIncioHuesped().toString(), // Asegúrate de que este método devuelve una cadena legible
                    elemento.getFinHuesped().toString() // Igual que arriba
                };
            writer.writeNext(data); // Escribe la nueva línea en el archivo
        }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    
       
}

    @Override
    public void cargarRegistroToCSV(Reservacion elemento, String archivo) {
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {
            
                String[] data = {
                    String.valueOf(elemento.getIdReserva()),
                    String.valueOf(elemento.getNumHabitaciones()),
                    String.valueOf(elemento.getEstado()),
                    elemento.getIncioHuesped().toString(), 
                    elemento.getFinHuesped().toString() 
                };
            writer.writeNext(data); 
        
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
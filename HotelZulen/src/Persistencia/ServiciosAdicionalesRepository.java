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
import modelo.ServiciosAdicionales;

/**
 *
 * @author Suyco
 */
public class ServiciosAdicionalesRepository implements IRepository<ServiciosAdicionales> {

    @Override
    public List<ServiciosAdicionales> cargarCSVtoLista(String archivo) throws IOException {

        List<ServiciosAdicionales> serviciosCargados = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                ServiciosAdicionales servicios = new ServiciosAdicionales(
                        Integer.parseInt(nextLine[0]),
                        nextLine[1],
                        Double.parseDouble(nextLine[2]),
                        nextLine[3]
                );
                serviciosCargados.add(servicios);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return serviciosCargados;
    }

    @Override
    public void cargarListaToCSV(List<ServiciosAdicionales> lista, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {

            for (ServiciosAdicionales serviciosAdicionales : lista) {

                String[] data = {
                    String.valueOf(serviciosAdicionales.getId()),
                    serviciosAdicionales.getConcepto(),
                    String.valueOf(serviciosAdicionales.getCosto()),
                    serviciosAdicionales.getEstado()
                };
                writer.writeNext(data); // Escribe la nueva línea en el archivo
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    @Override
    public void cargarRegistroToCSV(ServiciosAdicionales elemento, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {

            String[] data = {
                String.valueOf(elemento.getId()),
                elemento.getConcepto(),
                String.valueOf(elemento.getCosto())
            };
            writer.writeNext(data); // Escribe la nueva línea en el archivo

        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}

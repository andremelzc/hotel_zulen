/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Suyco
 */
public class HotelRepository {
    public int contarLineasCSV(String archivo) {
    int contadorLineas = 0;

    try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
        List<String[]> todasLasLineas = csvReader.readAll();
        contadorLineas = todasLasLineas.size(); 
    } catch (IOException | CsvException e) {
        e.printStackTrace();
    }

    return contadorLineas;
}
}

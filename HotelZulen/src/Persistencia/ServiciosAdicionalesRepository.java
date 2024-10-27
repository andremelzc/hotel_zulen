/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.ServiciosAdicionales;
/**
 *
 * @author Suyco
 */
public class ServiciosAdicionalesRepository implements IRepository<ServiciosAdicionales> {

    
    @Override
    public List<ServiciosAdicionales> cargarCSVtoLista(String archivo) throws IOException {
        
        List<ServiciosAdicionales> serviciosCargados = new ArrayList<>();
        
        try(CSVReader csvReader = new CSVReader (new FileReader(archivo))){
            String[] nextLine;
            while((nextLine = csvReader.readNext())!=null){
                ServiciosAdicionales servicios = new ServiciosAdicionales(
                Integer.parseInt(nextLine[0]),
                nextLine[1],
                Double.parseDouble(nextLine[2])
                );
                serviciosCargados.add(servicios);
            }               
        } catch(CsvException e){
            e.printStackTrace();
        }
        return serviciosCargados;
    }

    @Override
    public void cargarListaToCSV(List<ServiciosAdicionales> lista, String archivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cargarRegistroToCSV(ServiciosAdicionales elemento, String archivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    


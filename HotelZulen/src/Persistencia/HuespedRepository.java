package Persistencia;

import Persistencia.IRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modelo.Habitacion;
import modelo.Huesped;
/**
 *
 * @author Suyco
 */
public class HuespedRepository implements IRepository<Huesped>{
    
    
    @Override
    public List<Huesped> cargarCSVtoLista(String archivo) throws IOException {
        List<Huesped> HuespedesCargados = new ArrayList<>();
        
        try(CSVReader csvReader = new CSVReader (new FileReader(archivo))){
            String[] nextLine;
            while((nextLine = csvReader.readNext())!=null){
                Huesped huesped = new Huesped(
                Integer.parseInt(nextLine[0]), //idHuesped
                nextLine[1], //nombre
                nextLine[2], //apellido
                Integer.parseInt(nextLine[3]), //DNI
                Integer.parseInt(nextLine[4]), //telefono
                nextLine[5], //direccion
                nextLine[6], //usuario
                nextLine[7], //contrasena
                Integer.parseInt(nextLine[8]) //estado
                );
                HuespedesCargados.add(huesped);
            }               
        } catch(CsvException e){
            e.printStackTrace();
        }
       
        return HuespedesCargados;
            
    }

        @Override
        public void cargarListaToCSV(List<Huesped> lista, String archivo) {
            // Agregar el nuevo huésped al archivo CSV
            try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
            
            for(Huesped elemento : lista){        
                String[] datosHuesped = {
                String.valueOf(elemento.getID()),
                elemento.getNombre(),
                elemento.getApellido(),
                String.valueOf(elemento.getDNI()),
                String.valueOf(elemento.getTelefono()),
                elemento.getDireccion(),
                elemento.getUsuario(),
                elemento.getContrasena(),
                String.valueOf(elemento.getEstado())
                };
                writer.writeNext(datosHuesped); // Escribe la nueva línea en el CSV
            }
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de excepciones
            }  
        }

        @Override
        public void cargarRegistroToCSV(Huesped elemento, String archivo) {

            // Agregar el nuevo huésped al archivo CSV
            try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {
                String[] datosHuesped = {
                    String.valueOf(elemento.getID()),
                    elemento.getNombre(),
                    elemento.getApellido(),
                    String.valueOf(elemento.getDNI()),
                    String.valueOf(elemento.getTelefono()),
                    elemento.getDireccion(),
                    elemento.getUsuario(),
                    elemento.getContrasena(),
                    String.valueOf(elemento.getEstado())
                };
                writer.writeNext(datosHuesped); // Escribe la nueva línea en el CSV
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de excepciones
            } 
        }
}




   
    

    


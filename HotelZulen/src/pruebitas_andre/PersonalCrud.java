/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebitas_andre;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class PersonalCrud {

    List<Personal> listaPersonal = new ArrayList<>();

    public void agregarPersonal(Personal personal) {
        listaPersonal.add(personal);
        System.out.println("Personal agregado: " + personal.getNombre() + " " + personal.getApellido());
        escribirCSV();
    }

    public void escribirCSV() {
        try (CSVWriter escritor = new CSVWriter(new FileWriter("jefe.csv",true))){
            System.out.println("holaaaa");
            for (Personal personal : listaPersonal) {
                String[] datos = {personal.getNombre(), personal.getApellido()};
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

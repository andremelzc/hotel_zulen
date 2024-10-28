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
import modelo.Personal;
import modelo.ResevacionHabitacion;

/**
 *
 * @author PC
 */
public class PersonalRepository implements IRepository<Personal> {

    @Override
    public List<Personal> cargarCSVtoLista(String archivo) throws IOException {
        List<Personal> PersonalCargado = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(archivo))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                Personal personal = new Personal(
                        Integer.parseInt(nextLine[0]), //dniPersonal
                        nextLine[1], //nombre
                        nextLine[2], //apellido
                        Integer.parseInt(nextLine[3]), //telefono
                        nextLine[4], //direccion
                        nextLine[5], //usuario
                        nextLine[6], //contrasena
                        nextLine[7], //funcion
                        nextLine[8] //estado
                );
                PersonalCargado.add(personal);
            }
        } catch (CsvException e) {
            e.printStackTrace();
        }

        return PersonalCargado;
    }

    @Override
    public void cargarListaToCSV(List<Personal> lista, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, false))) {
            for (Personal personal : lista) {
                String[] data = {
                    String.valueOf(personal.getDNI()), personal.getNombre(),
                    personal.getApellido(), String.valueOf(personal.getTelefono()),
                    personal.getDireccion(), personal.getUsuario(),
                    personal.getContrasena(), personal.getFuncion(), personal.getEstado()
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarRegistroToCSV(Personal personal, String archivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, true))) {

            String[] data = {
                String.valueOf(personal.getDNI()), personal.getNombre(),
                personal.getApellido(), String.valueOf(personal.getTelefono()),
                personal.getDireccion(), personal.getUsuario(),
                personal.getContrasena(), personal.getFuncion(), personal.getEstado()
            };
            writer.writeNext(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class Funcionalidades {

    public Funcionalidades() {
    }

    public void vaciarCSV(String nombreArchivo) {
        try {
            // Abre el archivo en modo escritura y escribe una cadena vacía
            FileWriter writer = new FileWriter(nombreArchivo);
            writer.write(""); // Vacía el contenido del archivo
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

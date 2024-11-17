/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Persistencia;

import Persistencia.LimpiezaRepository;
import java.time.LocalDate;
import modelo.Limpieza;

/**
 *
 * @author Fabrizio Mantari
 */
public class Pruebitas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LimpiezaRepository pruebita = new LimpiezaRepository();

        pruebita.asignarLimpiezas();
        /*Limpieza objeto = new Limpieza(
                101, // idHabitacion
                12345678, // personalDNI
                4, // categoriaHab
                "Profunda", // tipoLimpieza
                "Asignada", // estadoLimpieza
                LocalDate.now() // fechaLimpieza
        );

        pruebita.crear(objeto);*/
        
    }

}

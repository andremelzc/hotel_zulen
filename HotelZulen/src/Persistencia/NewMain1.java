/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Habitacion;

/**
 *
 * @author Suyco
 */
public class NewMain1 {

    static void main(String[] args) {
        HabitacionRepository hab = new HabitacionRepository();
        Habitacion habitacion = new Habitacion();
        List<Habitacion> listaHab = new ArrayList<>();
        listaHab = hab.filtrarHabitaciones("3", "Doble");
        for(Habitacion hab1 : listaHab){
            System.out.println(hab1.getId());
            System.out.println(hab1.getPiso());
            System.out.println(hab1.getTipoHabitacion().getConcepto());
            System.out.println("-------------------------------");
        }
        System.out.println("gaaa");
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import Controlador.*;
import modelo.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.Personal;

/**
 *
 * @author PC
 */
public class newMain {

    public static void main(String[] args) {
        List<Personal> listaPersonales = new ArrayList<>();
        List<Reservacion> listaReservaciones = new ArrayList<>();
        List<Habitacion> listaHabitaciones = new ArrayList<>();

        PersonalCrud personalCrud = new PersonalCrud();
        ReservacionCrud reservacionCrud = new ReservacionCrud();
        HabitacionCrud habitacionCrud = new HabitacionCrud();
        listaPersonales = personalCrud.cargarCSVlista();
        
        
        listaReservaciones = reservacionCrud.cargarCSVlista();
        
        HashMap<Integer, Habitacion> mapaHabitacion = habitacionCrud.cargarCSVHash();

        for(Reservacion reservacion : listaReservaciones){
            System.out.println(reservacion.getIdReserva());
            for(Servicios servicios : reservacion.getServicios()){
                System.out.println(servicios.getConcepto());
            }
        }
        
      

    }
}

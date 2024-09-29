/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.util.List;

public class Reservacion {

    private int idReserva;
    private int habitacion;
    private int huesped;
    // Lo cambie a string pq salía error y no sabía modificarlo
    private String servicios;
    //List<PedidosComida> pedidosComida;
    private LocalDate incioHuesped;
    private LocalDate finHuesped;
    
    public Reservacion(){
        
    }
    //Cambié los tipos de variable de Habitación y huesped
    public Reservacion(int idReserva, int habitacion, int huesped, String servicios, LocalDate incioHuesped, LocalDate finHuesped) {
        this.idReserva = idReserva;
        this.habitacion = habitacion;
        this.huesped = huesped;
        this.servicios = servicios;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
    }
    
    
}

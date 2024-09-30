/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.util.List;

public class Reservacion {

    private int idReserva;
    private Habitacion habitacionn;
    private Huesped huespedd;
    private String servicios;
    //List<PedidosComida> pedidosComida;
    private LocalDate incioHuesped;
    private LocalDate finHuesped;
    
    //Atributos solo para guardar los datos
    private int idHabitacion;;
    private int idHuesped;
    
    public Reservacion(){
        
    }
    
    //Sirve para guardar los datos
    public Reservacion(int idReserva, int habitacion, int huesped, String servicios, LocalDate incioHuesped, LocalDate finHuesped) {
        this.idReserva = idReserva;
        this.idHabitacion = habitacion;
        this.idHuesped = huesped;
        this.servicios = servicios;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
    }
    
    //Constructor "oficial"?
    public Reservacion(int idReserva, Habitacion habitacion, Huesped huespedd, String servicios, LocalDate incioHuesped, LocalDate finHuesped) {
        this.idReserva = idReserva;
        this.habitacionn = habitacion;
        this.huespedd = huespedd;
        this.servicios = servicios;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public Habitacion getHabitacionn() {
        return habitacionn;
    }

    public Huesped getHuespedd() {
        return huespedd;
    }

    public String getServicios() {
        return servicios;
    }

    public LocalDate getIncioHuesped() {
        return incioHuesped;
    }

    public LocalDate getFinHuesped() {
        return finHuesped;
    }
    
    
    
    
    
}

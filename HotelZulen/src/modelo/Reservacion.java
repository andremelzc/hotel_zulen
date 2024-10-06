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
    private List<Servicios> servicios;
    //List<PedidosComida> pedidosComida;
    private String estado;
    private LocalDate incioHuesped;
    private LocalDate finHuesped;

    public Reservacion() {

    }

    public Reservacion(int idReserva, Habitacion habitacion, Huesped huespedd, List<Servicios> servicios, String estado, LocalDate incioHuesped, LocalDate finHuesped) {
        this.idReserva = idReserva;
        this.habitacionn = habitacion;
        this.huespedd = huespedd;
        this.servicios = servicios;
        this.estado = estado;
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

    public List<Servicios> getServicios() {
        return servicios;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getIncioHuesped() {
        return incioHuesped;
    }

    public LocalDate getFinHuesped() {
        return finHuesped;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setHabitacionn(Habitacion habitacionn) {
        this.habitacionn = habitacionn;
    }

    public void setHuespedd(Huesped huespedd) {
        this.huespedd = huespedd;
    }

    public void setServicios(List<Servicios> servicios) {
        this.servicios = servicios;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIncioHuesped(LocalDate incioHuesped) {
        this.incioHuesped = incioHuesped;
    }

    public void setFinHuesped(LocalDate finHuesped) {
        this.finHuesped = finHuesped;
    }

    
}

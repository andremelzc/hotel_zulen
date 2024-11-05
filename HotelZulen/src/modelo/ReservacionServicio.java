/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.ReservacionServicioRepository;
import Persistencia.ServiciosAdicionalesRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservacionServicio  {
    private Reservacion reserva;
    private ServiciosAdicionales servicio;

    public ReservacionServicio() {
    }

    public ReservacionServicio(Reservacion reserva, ServiciosAdicionales servicio) {
        this.reserva = reserva;
        this.servicio = servicio;
    }

    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public ServiciosAdicionales getServicio() {
        return servicio;
    }

    public void setServicio(ServiciosAdicionales servicio) {
        this.servicio = servicio;
    }
    
    
    
}

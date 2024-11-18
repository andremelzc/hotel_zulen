/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suyco
 */
public class ReservacionHuesped  {
    Reservacion reserva;
    Huesped huesped;

    public ReservacionHuesped() {
    }

    public ReservacionHuesped(Reservacion reserva, Huesped huesped) {
        this.reserva = reserva;
        this.huesped = huesped;
    }
    
 
    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
    
}

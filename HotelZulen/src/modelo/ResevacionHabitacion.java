/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

/**
 *
 * @author Suyco
 */
public class ResevacionHabitacion  {
    private Reservacion reserva;
    private Habitacion habitacion;

    public ResevacionHabitacion() {
    }

    public ResevacionHabitacion(Reservacion reserva, Habitacion habitacion) {
        this.reserva = reserva;
        this.habitacion = habitacion;
    }

    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

}

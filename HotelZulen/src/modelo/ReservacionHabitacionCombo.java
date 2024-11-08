/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PC
 */
public class ReservacionHabitacionCombo {
    private Reservacion reservacion;
    private Habitacion habitacion;
    private TipoDeHabitacion tipoDeHabitacion;
    private Combo combo;

    public ReservacionHabitacionCombo(Reservacion reservacion, Habitacion habitacion, TipoDeHabitacion tipoDeHabitacion, Combo combo) {
        this.reservacion = reservacion;
        this.habitacion = habitacion;
        this.tipoDeHabitacion = tipoDeHabitacion;
        this.combo = combo;
    }

    public ReservacionHabitacionCombo() {
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public TipoDeHabitacion getTipoDeHabitacion() {
        return tipoDeHabitacion;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setTipoDeHabitacion(TipoDeHabitacion tipoDeHabitacion) {
        this.tipoDeHabitacion = tipoDeHabitacion;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }
    
    
}

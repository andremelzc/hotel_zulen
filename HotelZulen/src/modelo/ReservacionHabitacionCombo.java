/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class ReservacionHabitacionCombo {
    private int id_Reserva;
    private Reservacion reservacion;
    private Habitacion habitacion;
    private TipoDeHabitacion tipoDeHabitacion;
    private Combo combo;
    private int cantPedido;
    private String estado;
    private LocalDateTime fechaPedido;
    private LocalDateTime fechaEnvio;

    public ReservacionHabitacionCombo(int id_Reserva, Reservacion reservacion, Habitacion habitacion, TipoDeHabitacion tipoDeHabitacion, Combo combo, int cantPedido, String estado, LocalDateTime fechaPedido, LocalDateTime fechaEnvio) {
        this.id_Reserva = id_Reserva;
        this.reservacion = reservacion;
        this.habitacion = habitacion;
        this.tipoDeHabitacion = tipoDeHabitacion;
        this.combo = combo;
        this.cantPedido = cantPedido;
        this.estado = estado;
        this.fechaPedido = fechaPedido;
        this.fechaEnvio = fechaEnvio;
    }
    
    

    public ReservacionHabitacionCombo() {
    }

    public int getId_Reserva() {
        return id_Reserva;
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

    public int getCantPedido() {
        return cantPedido;
    }

    public void setId_Reserva(int id_Reserva) {
        this.id_Reserva = id_Reserva;
    }

    public void setCantPedido(int cantPedido) {
        this.cantPedido = cantPedido;
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

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    
    
}

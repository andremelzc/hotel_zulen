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
    
    private int idPedido;
    private int idHabitacion;
    private int piso;
    private int categoria;
    private int idCombo;
    private String nombreCombo;
    private String descripcionCombo;
    private String fPedido;
    private String fEnvio;
    
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

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(int idCombo) {
        this.idCombo = idCombo;
    }

    public String getNombreCombo() {
        return nombreCombo;
    }

    public void setNombreCombo(String nombreCombo) {
        this.nombreCombo = nombreCombo;
    }

    public String getDescripcionCombo() {
        return descripcionCombo;
    }

    public void setDescripcionCombo(String descripcionCombo) {
        this.descripcionCombo = descripcionCombo;
    }

    public String getfPedido() {
        return fPedido;
    }

    public void setfPedido(String fPedido) {
        this.fPedido = fPedido;
    }

    public String getfEnvio() {
        return fEnvio;
    }

    public void setfEnvio(String fEnvio) {
        this.fEnvio = fEnvio;
    }
    
    public void mostrarInfos(){
        
        System.out.println("----------------------------------------");
        System.out.println("Pedido ID: " + getIdPedido());
        System.out.println("ID Habitacion: "+ getIdHabitacion());
        System.out.println("Piso: "+getPiso());
        System.out.println("Categoria: "+ getCategoria());
        System.out.println("ID Combo: "+ getIdCombo());
        System.out.println("Nombre Combo: "+getNombreCombo());
        System.out.println("Descripcion Combo: "+ getDescripcionCombo());
        System.out.println("Estado: "+getEstado());
        System.out.println("FPedido: "+ getfPedido());
        System.out.println("FEnvio: "+ getfEnvio());
    }
}

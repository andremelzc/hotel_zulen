/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Fabrizio Mantari
 */
public class Boleta {
    private Reservacion reserva;
    
    private String MetodoPagoInicial;
    private Double PagoInicial;
    private String EstadoInicial;
    private LocalDateTime FechaPagoInicial;
    
    private String MetodoPagoCheckOut;
    private Double PagoCheckOut;
    private String EstadoPagoCheckOut;
    private LocalDateTime FechaPagoCheckOut;

    public Boleta() {
    }

    public Boleta(Reservacion reserva, String MetodoPagoInicial, Double PagoInicial, String EstadoInicial, LocalDateTime FechaPagoInicial, String MetodoPagoCheckOut, Double PagoCheckOut, String EstadoPagoCheckOut, LocalDateTime FechaPagoCheckOut) {
        this.reserva = reserva;
        this.MetodoPagoInicial = MetodoPagoInicial;
        this.PagoInicial = PagoInicial;
        this.EstadoInicial = EstadoInicial;
        this.FechaPagoInicial = FechaPagoInicial;
        this.MetodoPagoCheckOut = MetodoPagoCheckOut;
        this.PagoCheckOut = PagoCheckOut;
        this.EstadoPagoCheckOut = EstadoPagoCheckOut;
        this.FechaPagoCheckOut = FechaPagoCheckOut;
    }

    
    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public String getMetodoPagoInicial() {
        return MetodoPagoInicial;
    }

    public void setMetodoPagoInicial(String MetodoPagoInicial) {
        this.MetodoPagoInicial = MetodoPagoInicial;
    }

    public Double getPagoInicial() {
        return PagoInicial;
    }

    public void setPagoInicial(Double PagoInicial) {
        this.PagoInicial = PagoInicial;
    }

    public String getEstadoInicial() {
        return EstadoInicial;
    }

    public void setEstadoInicial(String EstadoInicial) {
        this.EstadoInicial = EstadoInicial;
    }

    public LocalDateTime getFechaPagoInicial() {
        return FechaPagoInicial;
    }

    public void setFechaPagoInicial(LocalDateTime FechaPagoInicial) {
        this.FechaPagoInicial = FechaPagoInicial;
    }

    public String getMetodoPagoCheckOut() {
        return MetodoPagoCheckOut;
    }

    public void setMetodoPagoCheckOut(String MetodoPagoCheckOut) {
        this.MetodoPagoCheckOut = MetodoPagoCheckOut;
    }

    public Double getPagoCheckOut() {
        return PagoCheckOut;
    }

    public void setPagoCheckOut(Double PagoCheckOut) {
        this.PagoCheckOut = PagoCheckOut;
    }

    public String getEstadoPagoCheckOut() {
        return EstadoPagoCheckOut;
    }

    public void setEstadoPagoCheckOut(String EstadoPagoCheckOut) {
        this.EstadoPagoCheckOut = EstadoPagoCheckOut;
    }

    public LocalDateTime getFechaPagoCheckOut() {
        return FechaPagoCheckOut;
    }

    public void setFechaPagoCheckOut(LocalDateTime FechaPagoCheckOut) {
        this.FechaPagoCheckOut = FechaPagoCheckOut;
    }

    
    
    
}

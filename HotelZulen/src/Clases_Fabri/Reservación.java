/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Fabri;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 *
 * @author Fabrizio Mantari
 */
public class Reservación {
    private Huesped cliente;
    private List<Servicio> servicios;
    private LocalDateTime Inicio;
    private LocalDateTime Egreso;

    public Reservación(Huesped cliente, LocalDateTime Inicio, LocalDateTime Egreso) {
        this.cliente = cliente;
        this.servicios = new ArrayList<>();
        this.Inicio = Inicio;
        this.Egreso = Egreso;
    }
    
    public void agregarServicios(Servicio servicio){
        servicios.add(servicio);
    }

    public List<Servicio> getServicios() {
        return servicios;
    }
    
}

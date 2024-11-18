/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Fabrizio Mantari
 */
public class Limpieza {
    private int idHabitacion;
    private int personalDNI;
    private int categoriaHab;
    private String tipoLimpieza;
    private String estadoLimpieza;
    private LocalDate fechaLimpieza;

    public Limpieza() {
    }
    public Limpieza(int idHabitacion, int personalDNI, int categoriaHab, String tipoLimpieza, String estadoLimpieza, LocalDate fechaLimpieza) {
        this.idHabitacion = idHabitacion;
        this.personalDNI = personalDNI;
        this.categoriaHab = categoriaHab;
        this.tipoLimpieza = tipoLimpieza;
        this.estadoLimpieza = estadoLimpieza;
        this.fechaLimpieza = fechaLimpieza;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public int getPersonalDNI() {
        return personalDNI;
    }

    public int getCategoriaHab() {
        return categoriaHab;
    }

    public String getTipoLimpieza() {
        return tipoLimpieza;
    }

    public String getEstadoLimpieza() {
        return estadoLimpieza;
    }

    public LocalDate getFechaLimpieza() {
        return fechaLimpieza;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public void setPersonalDNI(int personalDNI) {
        this.personalDNI = personalDNI;
    }

    public void setCategoriaHab(int categoriaHab) {
        this.categoriaHab = categoriaHab;
    }

    public void setTipoLimpieza(String tipoLimpieza) {
        this.tipoLimpieza = tipoLimpieza;
    }

    public void setEstadoLimpieza(String estadoLimpieza) {
        this.estadoLimpieza = estadoLimpieza;
    }

    public void setFechaLimpieza(LocalDate fechaLimpieza) {
        this.fechaLimpieza = fechaLimpieza;
    }
    
    
    
}

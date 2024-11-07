package modelo;

import Persistencia.TipoHabitacionRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Habitacion implements IActualizar<Habitacion> {

    private int id;
    private TipoDeHabitacion tipoHabitacion;
    private String piso;
    private String estado;

    public Habitacion(int id, TipoDeHabitacion tipoHabitacion, String piso, String estado) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.piso = piso;
        this.estado = estado;
    }

    public Habitacion() {
    }

    public int getId() {
        return id;
    }

    public TipoDeHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public String getPiso() {
        return piso;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoHabitacion(TipoDeHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}

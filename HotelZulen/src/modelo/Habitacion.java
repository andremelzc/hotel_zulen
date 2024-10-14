package modelo;

public class Habitacion {

    private int id;
    private TipoDeHabitacion tipoHabitacion;
    private int piso;
    private String estado;
    private String servicio;
    /**
    public Habitacion(int piso, TipoDeHabitacion tipoHabitacion, String estado, String personalAsignado, String servicio, int IDHuesped) {

        this.piso = piso;
        this.tipoHabitacion = tipoHabitacion;
        this.estado = estado;
        this.personalAsignado = personalAsignado;
        this.servicio = servicio;
        this.IDHuesped = IDHuesped;
    }
    
  
    public Habitacion(int piso, TipoDeHabitacion tipoHabitacion, String servicio, int IDHuesped) {
        this.piso = piso;
        this.tipoHabitacion = tipoHabitacion;
        this.servicio = servicio;
        this.IDHuesped = IDHuesped;
    }*/

    public Habitacion(int id, TipoDeHabitacion tipoHabitacion, int piso, String estado) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.piso = piso;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public TipoDeHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public int getPiso() {
        return piso;
    }

    public String getEstado() {
        return estado;
    }

    public String getServicio() {
        return servicio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoHabitacion(TipoDeHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    
    
}

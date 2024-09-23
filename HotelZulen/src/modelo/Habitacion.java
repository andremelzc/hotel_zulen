package modelo;

public class Habitacion {

    private int ID;
    private int piso;
    private TipoDeHabitacion tipoDeHabitacion;
    private String estado;
    // private Housekeeper housekeeper;
    private String personalAsignado;
    // private Servicio servicio;
    private String servicio;
    private int IDHuesped;

    public Habitacion(int piso, TipoDeHabitacion tipoHabitacion, String estado, String personalAsignado, String servicio, int IDHuesped) {

        this.piso = piso;
        this.tipoDeHabitacion = tipoHabitacion;
        this.estado = estado;
        this.personalAsignado = personalAsignado;
        this.servicio = servicio;
        this.IDHuesped = IDHuesped;
    }

    public Habitacion(int piso, TipoDeHabitacion tipoHabitacion, String servicio, int IDHuesped) {
        this.piso = piso;
        this.tipoDeHabitacion = tipoHabitacion;
        this.servicio = servicio;
        this.IDHuesped = IDHuesped;
    }

    public int getPiso() {
        return piso;
    }

    public TipoDeHabitacion getTipoHabitacion() {
        return tipoDeHabitacion;
    }

    public String getEstado() {
        return estado;
    }

    public String getServicio() {
        return servicio;
    }

    public int getIDHuesped() {
        return IDHuesped;
    }
}

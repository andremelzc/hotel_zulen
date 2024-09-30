package modelo;

public class Habitacion {

    private int id;
    private TipoDeHabitacion tipoHabitacion;
    private int piso;
    private String estado;
    private int idHouseKeeper;
    // private Servicio servicio;
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

    public Habitacion(int id, TipoDeHabitacion tipoHabitacion, int piso, String estado, int idHousekeeper) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.piso = piso;
        this.estado = estado;
        this.idHouseKeeper = idHousekeeper;
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

    public int getIdHouseKeeper() {
        return idHouseKeeper;
    }

    public String getServicio() {
        return servicio;
    }
    
    
    
    
}

package modelo;

public class Habitacion {

    private int ID;
    private int piso;
    private String tipo;
    private String estado;
    // private Housekeeper housekeeper;
    private String personalAsignado;
    // private Servicio servicio;
    private String servicio;
    private int IDHuesped;

    public Habitacion(int piso, String tipo, String estado, String personalAsignado, String servicio, int IDHuesped) {

        this.piso = piso;
        this.tipo = tipo;
        this.estado = estado;
        this.personalAsignado = personalAsignado;
        this.servicio = servicio;
        this.IDHuesped = IDHuesped;
    }

    public Habitacion(int piso, String tipo, String servicio, int IDHuesped) {
        this.piso = piso;
        this.tipo = tipo;
        this.servicio = servicio;
        this.IDHuesped = IDHuesped;
    }

    public int getPiso() {
        return piso;
    }

    public String getTipo() {
        return tipo;
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

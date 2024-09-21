package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Huesped {

    private String nombre;
    private String apellido;
    private int DNI;
    private int telefono;
    private String direccion;
    private int ID;
    private String usuario;
    private String contrasena;
    private int IDHabitacion;
    private int IDFactura;
    private LocalDate incioHuesped;
    private LocalDate finHuesped;

    public Huesped(String nombre, String apellido, int DNI, int telefono, String direccion, int ID, String usuario, String contrasena, int IDHabitacion, int IDFactura, LocalDate incioHuesped, LocalDate finHuesped) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ID = ID;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.IDHabitacion = IDHabitacion;
        this.IDFactura = IDFactura;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
    }

    public Huesped(String nombre, String apellido, String usuario, String contrasena, int IDHabitacion, LocalDate incioHuesped, LocalDate finHuesped) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.IDHabitacion = IDHabitacion;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getID() {
        return ID;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIDHabitacion() {
        return IDHabitacion;
    }

    public int getIDFactura() {
        return IDFactura;
    }

    public LocalDate getIncioHuesped() {
        return incioHuesped;
    }

    public LocalDate getFinHuesped() {
        return finHuesped;
    }
}

package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Huesped {
    private int ID;
    private String nombre;
    private String apellido;
    private int DNI;
    private int telefono;
    private String direccion;
    private String usuario;
    private String contrasena;
    private int estado;

    public Huesped(int ID, String nombre, String apellido, int DNI, int telefono, String direccion, String usuario, String contrasena, int estado) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
    }
    
   
    public Huesped(int ID, String nombre, String apellido, int DNI, int telefono, String direccion, String usuario, String contrasena) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Huesped(String nombre, String apellido, String usuario, String contrasena, int IDHabitacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;

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
}

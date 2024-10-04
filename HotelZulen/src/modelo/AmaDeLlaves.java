/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Suyco
 */
public class AmaDeLlaves extends Personal{
    
    int ID;
    String nombre;
    String apellido;
    int DNI;
    int telefono;
    String direccion;
    String usuario;
    String contrasena;
    int estado;

    public AmaDeLlaves(int ID, String nombre, String apellido, int DNI, int telefono, String direccion, String usuario, String contrasena, int estado) {
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

    public AmaDeLlaves() {
    }
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas_giron;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Personal {

    String nombre;
    String apellido;
    int DNI;
    int telefono;
    String direccion;
    int ID;
    String usuario;
    String contrasena;
    String funcion;

    public Personal() {

    }

    public Personal(String nombre, String apellido, int DNI, int telefono, String direccion, int ID, String usuario, String contrasena, String funcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ID = ID;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.funcion = funcion;
    }

    public Personal(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    //Constructor Agregado por Miguel para el Inicio de Usuario
    public Personal(String nombre, String apellido, String usuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public void setFuncion(String funcion) {
        this.funcion = funcion;
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

    public String getFuncion() {
        return funcion;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public  class Personal {
    int ID;
    String nombre;
    String apellido;
    int DNI;
    int telefono;
    String direccion;
    String usuario;
    String contrasena;
    String funcion;
    int estado;

    public Personal() {

    }

    public Personal(int ID, String nombre, String apellido, int DNI, int telefono, String direccion, String usuario, String contrasena, String funcion, int estado) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.funcion = funcion;
        this.estado = estado;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.LimpiezaRepository;
import Persistencia.PersonalRepository;
import java.util.List;

/**
 *
 * @author Suyco
 */
public class AmaDeLlaves extends Personal {
    
    private PersonalRepository repoPersonal;
    
    public AmaDeLlaves(int DNI, String nombre, String apellido, int telefono, String direccion, String usuario, String contrasena, String estado) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
        this.repoPersonal = new PersonalRepository();
    }

    public AmaDeLlaves() {
        this.repoPersonal = new PersonalRepository();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Housekeeper obtenerHousekeeper(int dni){
        return repoPersonal.obtenerHouskeeper(dni);
    }
    public List<Housekeeper> obtenerListaHouseActivos(){
        return repoPersonal.obtenerHousekeeperActivos();
    }
    
    public List<Housekeeper> obtenerListaHouseInactivos(){
        return repoPersonal.obtenerHousekeeperInactivos();
    }
    public void asignarLimpiezas(){
        LimpiezaRepository repoLimpieza = new LimpiezaRepository();
        repoLimpieza.asignarLimpiezas();
    }
}

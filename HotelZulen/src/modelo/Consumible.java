/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.ConsumibleRepository;
import java.io.File;

public class Consumible {
    private int id;
    private String nombre;
    private float precio;

    public Consumible(int id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    public Consumible(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Consumible() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public void crear(Consumible consumible){
        ConsumibleRepository repoC= new ConsumibleRepository();
        repoC.crear(consumible);
    }
    public void actualizar (Consumible objeto){
        ConsumibleRepository repoC= new ConsumibleRepository();
        repoC.actualizar(objeto);
    }
    
}

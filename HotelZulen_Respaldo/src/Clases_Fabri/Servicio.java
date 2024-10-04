/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Fabri;

/**
 *
 * @author Fabrizio Mantari
 */
public class Servicio {
    private String nombre;
    private float Precio;
    private Estado estado;
    
    public enum Estado{
        AGOTADO, NO_DISPONIBLE, DISPONIBLE;
     } 

    public Servicio(String nombre, float Precio, Estado estado) {
        this.nombre = nombre;
        this.Precio = Precio;
        this.estado = estado;
    }
    
    
}

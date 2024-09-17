/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Fabri;

import java.io.File;
/**
 *
 * @author Fabrizio Mantari
 */
public class Consumible {
    protected int precio;
    protected String nombre;
    protected String descripcion;
    protected File foto;
    protected Subtipo subtipo;
    
    protected enum Subtipo{
        cafe("bebida"),jugo("bebida"),LomoSaltado("Almuerzo"); /* Los tipos seríam “Comidas” Desayuno, Almuerzo y Cena.
“Bebidas” Infusiones,  Bebidas Alcohólicas y Bebidas Sin Alcohol .*/
        
        private String tipo; //atributo que detecta la configuracion de la constante elegida
        
        private Subtipo(String tipo){
            this.tipo = tipo;
        }

        public String getTipo() { //Getter del tipo al que pertenece el consumible
            return tipo;
        }
        
    }

    public Consumible(int precio, String nombre, String descripcion, File foto, Subtipo subtipo) {
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.subtipo = subtipo;
        
    }
    
    
}

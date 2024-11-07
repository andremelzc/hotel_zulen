/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PC
 */
public class Combo {
    private int id;
    private String tipoComida;
    private String descripcion;

    public Combo(int id, String tipoComida, String descripcion) {
        this.id = id;
        this.tipoComida = tipoComida;
        this.descripcion = descripcion;
    }

    public Combo() {
    }

    public int getId() {
        return id;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}

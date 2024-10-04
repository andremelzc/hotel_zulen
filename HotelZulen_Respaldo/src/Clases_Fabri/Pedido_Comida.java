/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Fabri;

/**
 *
 * @author Fabrizio Mantari
 */
public class Pedido_Comida {
    private Huesped cliente;
    private Consumible comida;
    private Consumible bebida;
    private float costo;

    public Pedido_Comida(Huesped cliente, Consumible comida, Consumible bebida, float costo) {
        this.cliente = cliente;
        this.comida = comida;
        this.bebida = bebida;
        this.costo = costo;
    }
    
}

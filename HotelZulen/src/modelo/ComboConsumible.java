/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PC
 */
public class ComboConsumible {
    private Combo combo;
    private Consumible consumible;

    public ComboConsumible(Combo combo, Consumible consumible) {
        this.combo = combo;
        this.consumible = consumible;
    }

    public ComboConsumible() {
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public void setConsumible(Consumible consumible) {
        this.consumible = consumible;
    }

    public Combo getCombo() {
        return combo;
    }

    public Consumible getConsumible() {
        return consumible;
    }

    

    
    
    
    
}

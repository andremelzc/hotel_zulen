/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import Persistencia.ComboConsumibleRepository;
import Persistencia.ConsumibleRepository;
import modelo.Combo;
import modelo.Consumible;
import Persistencia.ComboRepository;
import java.util.ArrayList;
import java.util.List;
import modelo.ComboConsumible;
/**
 *
 * @author PC
 */
public class hola {
    public static void main(String args[]){
        ComboConsumibleRepository combor = new ComboConsumibleRepository();
        
        List<Consumible> consumibles = new ArrayList<>();
        ComboConsumibleRepository ccr = new ComboConsumibleRepository();
        consumibles = ccr.obtenerConsumiblesPorCombo(1);
        
        for(Consumible consumible : consumibles){
            System.out.println(consumible.getPrecio());
        }
        
        
       
    }
}

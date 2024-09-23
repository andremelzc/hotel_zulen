/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Suyco
 */
public class TipoDeHabitacion {
    String concepto;
    private Map<String, Integer> preciosPorTipo;
  
    public TipoDeHabitacion() {

    preciosPorTipo = new HashMap<>();
    
    preciosPorTipo.put("standard", 100);  // Precio inicial en dólares o la moneda que elijas
    preciosPorTipo.put("doble", 150);
    preciosPorTipo.put("suite", 250);
    preciosPorTipo.put("business", 200);
    
    }

    public TipoDeHabitacion(String concepto) {
        this.concepto = concepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
    public int obtenerPrecio(String tipo) {
        return preciosPorTipo.getOrDefault(tipo.toLowerCase(), 0);
    }

    // Método para cambiar el precio de un tipo de habitación
    public void cambiarPrecio(String tipo, Integer nuevoPrecio) {
        if (preciosPorTipo.containsKey(tipo.toLowerCase())) {
            preciosPorTipo.put(tipo.toLowerCase(), nuevoPrecio);
            System.out.println("Precio actualizado para " + tipo + ": " + nuevoPrecio);
        } else {
            System.out.println("Tipo de habitación no reconocido.");
        }
    }
    public void mostrarTiposDeHabitaciones(){
        for (Map.Entry<String, Integer> entry : preciosPorTipo.entrySet()) {
            String tipo = entry.getKey();
            Integer precio = entry.getValue();
            System.out.println("Tipo: " + tipo + " - Precio: $" + precio);
    }
    }
}

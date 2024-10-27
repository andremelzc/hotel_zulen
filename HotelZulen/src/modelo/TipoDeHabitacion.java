/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

/**
 *
 * @author Suyco
 */
public class TipoDeHabitacion implements IActualizar <TipoDeHabitacion>{
    int id;
    String concepto;
    double precio;

    public TipoDeHabitacion(int id, String concepto, double precio) {
        this.id = id;
        this.concepto = concepto;
        this.precio = precio;
    }

    public TipoDeHabitacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public void agregar(List<TipoDeHabitacion> lista, TipoDeHabitacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(List<TipoDeHabitacion> lista, TipoDeHabitacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(List<TipoDeHabitacion> lista, TipoDeHabitacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<TipoDeHabitacion> lista) {
     
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-19s %-5s%n", "ID", "Tipo de Habitacion", "Precio");
        System.out.println("--------------------------------------------------------------------------------------");
    
        // Imprimimos toda la lista
        for (TipoDeHabitacion tipos : lista) {
            System.out.printf("%-4d %-19s %-5.2f%n", // Aquí usamos %d para entero y %.2f para double
                tipos.getId(),
                tipos.getConcepto(),
                tipos.getPrecio()
            );
        }
        System.out.println("--------------------------------------------------------------------------------------");
}
    

    @Override
    public TipoDeHabitacion obtenerPorId(List<TipoDeHabitacion> lista, int id) {

        for (TipoDeHabitacion tipo : lista) {
            if (tipo.getId()== id) {
                return tipo; 
            }
        }
        return null; 
    
    }
  
    
}
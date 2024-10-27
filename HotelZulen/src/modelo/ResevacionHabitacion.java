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
public class ResevacionHabitacion implements IActualizar<ResevacionHabitacion> {
    private Reservacion reserva;
    private Habitacion habitacion;

    public ResevacionHabitacion() {
    }

    public ResevacionHabitacion(Reservacion reserva, Habitacion habitacion) {
        this.reserva = reserva;
        this.habitacion = habitacion;
    }

    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public void agregar(List<ResevacionHabitacion> lista, ResevacionHabitacion elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<ResevacionHabitacion> lista, ResevacionHabitacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(List<ResevacionHabitacion> lista, ResevacionHabitacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<ResevacionHabitacion> lista) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-20s %-15s%n", 
            "ID Reserva", "Habitacion", "Piso", "Tipo Habitacion", "Estado Habitacion");
         System.out.println("--------------------------------------------------------------------------------------");

    // Recorre y muestra la lista de ResevacionHabitacion
    for (ResevacionHabitacion reservaHabitacion : lista) {
        Reservacion reservacion = reservaHabitacion.getReserva();
        Habitacion habitacionM = reservaHabitacion.getHabitacion();

        System.out.printf("%-10d %-20d %-10s %-20s %-15s%n",
                reservacion.getIdReserva(),                    // entero
                habitacionM.getId(),                            // entero
                habitacionM.getPiso(),                          // entero
                habitacionM.getTipoHabitacion().getConcepto(),   // cadena
                habitacionM.getEstado());                       // cadena
    }
    System.out.println("--------------------------------------------------------------------------------------");
    
    }

    @Override
    public ResevacionHabitacion obtenerPorId(List<ResevacionHabitacion> lista, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}

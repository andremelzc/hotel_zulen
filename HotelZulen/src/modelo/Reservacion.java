/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.util.List;


public class Reservacion implements IActualizar<Reservacion>{

    private int idReserva;
    private int numHabitaciones;
    private String estado;
    private LocalDate incioHuesped;
    private LocalDate finHuesped;

    public Reservacion() {

    }

    public Reservacion(int idReserva, int numHabitaciones, String estado, LocalDate incioHuesped, LocalDate finHuesped) {
        this.idReserva = idReserva;
        this.numHabitaciones = numHabitaciones;
        this.estado = estado;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getIncioHuesped() {
        return incioHuesped;
    }

    public void setIncioHuesped(LocalDate incioHuesped) {
        this.incioHuesped = incioHuesped;
    }

    public LocalDate getFinHuesped() {
        return finHuesped;
    }

    public void setFinHuesped(LocalDate finHuesped) {
        this.finHuesped = finHuesped;
    }

    @Override
    public void agregar(List<Reservacion> lista, Reservacion elemento) {
        lista.add(elemento);
    }
    
    
    @Override
    public void actualizar(List<Reservacion> lista, Reservacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(List<Reservacion> lista, Reservacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<Reservacion> lista) {
        
    System.out.println("--------------------------------------------------------------------------------------");
    System.out.printf("%-10s %-15s %-10s %-15s %-15s%n", "ID Reserva", "Num Habitaciones", "Estado", "Inicio Huesped", "Fin Huesped");
    System.out.println("--------------------------------------------------------------------------------------");
    
    // Imprimimos toda la lista
    for (Reservacion reservacion : lista) {
        System.out.printf("%-10d %-15d %-10s %-15s %-15s%n",
                reservacion.getIdReserva(),
                reservacion.getNumHabitaciones(),
                reservacion.getEstado(),
                reservacion.getIncioHuesped(),  
                reservacion.getFinHuesped());    
    }
    System.out.println("--------------------------------------------------------------------------------------");

    }

    @Override
    public Reservacion obtenerPorId(List<Reservacion> lista, int id) {
        for (Reservacion reserva : lista){
            if(reserva.getIdReserva()==id){
                return reserva;
            }
        }
        return null;
    
    }

    
    
}
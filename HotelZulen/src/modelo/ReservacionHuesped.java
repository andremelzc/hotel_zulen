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
public class ReservacionHuesped implements IActualizar<ReservacionHuesped> {
    Reservacion reserva;
    Huesped huesped;

    public ReservacionHuesped() {
    }

    public ReservacionHuesped(Reservacion reserva, Huesped huesped) {
        this.reserva = reserva;
        this.huesped = huesped;
    }

    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    @Override
    public void agregar(List<ReservacionHuesped> lista, ReservacionHuesped elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<ReservacionHuesped> lista, ReservacionHuesped elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(List<ReservacionHuesped> lista, ReservacionHuesped elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<ReservacionHuesped> lista) {
        System.out.println("------------------------------------------------------------------------------------------------------");
    System.out.printf("%-12s %-20s %-20s %-15s %-15s %-15s %-15s%n", 
            "ID Reserva", "Nombre Huesped", "Apellido Huesped", "Inicio Reserva", "Fin Reserva", "Estado Reserva", "Estado Huesped");
    System.out.println("------------------------------------------------------------------------------------------------------");

    // Recorre y muestra la lista de ReservacionHuesped
    for (ReservacionHuesped reservacionHuesped : lista) {
        Reservacion reservacion = reservacionHuesped.getReserva();
        Huesped huesped = reservacionHuesped.getHuesped();


        System.out.printf("%-12d %-20s %-20s %-15s %-15s %-15s %-15s%n",
                reservacion.getIdReserva(),
                huesped.getNombre(),
                huesped.getApellido(),
                reservacion.getIncioHuesped(),
                reservacion.getFinHuesped(),
                reservacion.getEstado(),
                huesped.getEstado());
    }
    System.out.println("------------------------------------------------------------------------------------------------------");
    }

    @Override
    public ReservacionHuesped obtenerPorId(List<ReservacionHuesped> lista, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

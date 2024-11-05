/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.HuespedRepository;
import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionRepository;
import Persistencia.ReservacionServicioRepository;
import java.time.LocalDate;
import java.util.List;


public class Reservacion {

    private int idReserva;
    private int numHabitaciones;
    private String estado;
    private LocalDate incioHuesped;
    private LocalDate finHuesped;

    public Reservacion() {

    }
    
//Para crear reserva , ya q id es autoincremental en la BD
    public Reservacion(int numHabitaciones, String estado, LocalDate incioHuesped, LocalDate finHuesped) {
        this.numHabitaciones = numHabitaciones;
        this.estado = estado;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
    }
    //cuando recupere una reserva
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

   public void crearReservacion(Reservacion reservacion, List<Huesped> listaHuespedes,List<Habitacion> listaHabitaciones,List<ServiciosAdicionales> listaServicios) {
        // Crear la reserva y obtener el ID
        ReservacionRepository reservacionRepository = new ReservacionRepository();
        ReservacionHuespedRepository reservacionHuespedRepository = new ReservacionHuespedRepository();
        ReservacionHabitacionesRepository reservacionHabitacionRepository = new ReservacionHabitacionesRepository();
        ReservacionServicioRepository reservacionServicioRepository = new ReservacionServicioRepository();
        HuespedRepository huespedRepo = new HuespedRepository ();
        
        huespedRepo.crearHuespedes(listaHuespedes);
        int idReservacion = reservacionRepository.crearReserva(reservacion);
        if (idReservacion > 0) {
            // Asociar los huéspedes a la nueva reserva
            reservacionHuespedRepository.asociarReservaHuespedes(idReservacion, listaHuespedes);
            reservacionHabitacionRepository.asociarReservaHabitacion(idReservacion, listaHabitaciones);
            reservacionServicioRepository.asociarReservaHabitacion(idReservacion, listaServicios);
            
            System.out.println("Reserva generada satisfactoriamente!");
            
        }
    }
    
    
}
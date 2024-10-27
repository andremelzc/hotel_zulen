/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;

import modelo.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import modelo.ServiciosAdicionales;
import modelo.TipoDeHabitacion;
import Persistencia.HabitacionRepository;
import Persistencia.HuespedRepository;
import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionRepository;
import Persistencia.ReservacionServicioRepository;
import Persistencia.ServiciosAdicionalesRepository;
import Persistencia.TipoHabitacionRepository;

/**
 *
 * @author Suyco
 */
public class SuycoNewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
       List<Habitacion> habitacionCargada = new ArrayList<>();
       HabitacionRepository habitacionRepo = new HabitacionRepository();
       Habitacion nuevahabitacion = new Habitacion();
       habitacionCargada = habitacionRepo.cargarCSVtoLista("habitaciones.csv");
       
       
       List<Reservacion> reservacionesCargadas = new ArrayList<>();
       ReservacionRepository reservaRepo = new ReservacionRepository();
       Reservacion nuevaReserva = new Reservacion();
       reservacionesCargadas = reservaRepo.cargarCSVtoLista("reservaciones.csv");
       
       List<ServiciosAdicionales> serviciosCargados = new ArrayList<>();
       ServiciosAdicionalesRepository serviciosRepo = new ServiciosAdicionalesRepository();
       ServiciosAdicionales nuevoServicio = new ServiciosAdicionales();
       serviciosCargados = serviciosRepo.cargarCSVtoLista("serviciosAdicionales.csv");
       
       
       List<Huesped> HuespedesCargados = new ArrayList<>();
       HuespedRepository huespedRepo = new HuespedRepository ();
       Huesped nuevoHuesped = new Huesped();
       HuespedesCargados = huespedRepo.cargarCSVtoLista("huespedes.csv");
       
       
       List<TipoDeHabitacion> tiposCargados = new ArrayList<>();
       TipoHabitacionRepository tipoRepo = new TipoHabitacionRepository();
       TipoDeHabitacion nuevoTipo = new TipoDeHabitacion();
       tiposCargados = tipoRepo.cargarCSVtoLista("tipoHabitacion.csv");
       
       
       
       List<ResevacionHabitacion> reservaHabitacionCargados = new ArrayList<>();
       ReservacionHabitacionesRepository reservaHabitacionesRepo = new ReservacionHabitacionesRepository();
       ResevacionHabitacion reservaHabitacion = new ResevacionHabitacion();
       reservaHabitacionCargados = reservaHabitacionesRepo.cargarCSVtoLista("reservaHabitacion.csv");
       
       
       List<ReservacionHuesped> reservaHuespedCargados = new ArrayList<>();
       ReservacionHuespedRepository reservaHuespedRepo = new ReservacionHuespedRepository();
       ReservacionHuesped reservaHuesped = new ReservacionHuesped();
       reservaHuespedCargados = reservaHuespedRepo.cargarCSVtoLista("reservaHuesped");
       
       List<ReservacionServicio> reservacionServicioCargada = new ArrayList<>();
       ReservacionServicioRepository reservaServicioRepo = new ReservacionServicioRepository();
       ReservacionServicio reservaServicio = new ReservacionServicio();
       reservacionServicioCargada = reservaServicioRepo.cargarCSVtoLista("reservaServicios.csv");
        
        nuevahabitacion.mostrarLista(habitacionCargada);

        nuevaReserva.mostrarLista(reservacionesCargadas);

        nuevoServicio.mostrarLista(serviciosCargados);

        nuevoHuesped.mostrarLista(HuespedesCargados);
        
        nuevahabitacion.leerTodoHabitacionDisponible(habitacionCargada);

        nuevoTipo.mostrarLista(tiposCargados);
                 
}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.ReservacionServicioRepository;
import Persistencia.ServiciosAdicionalesRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservacionServicio implements IActualizar<ReservacionServicio> {
    private Reservacion reserva;
    private ServiciosAdicionales servicio;

    public ReservacionServicio() {
    }

    public ReservacionServicio(Reservacion reserva, ServiciosAdicionales servicio) {
        this.reserva = reserva;
        this.servicio = servicio;
    }

    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public ServiciosAdicionales getServicio() {
        return servicio;
    }

    public void setServicio(ServiciosAdicionales servicio) {
        this.servicio = servicio;
    }

    @Override
    public void agregar(List<ReservacionServicio> lista, ReservacionServicio elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<ReservacionServicio> lista, ReservacionServicio elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(List<ReservacionServicio> lista, ReservacionServicio elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<ReservacionServicio> lista) {

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-30s %-10s%n", "ID Reserva", "Concepto Servicio", "Costo Servicio", "Estado");
        System.out.println("--------------------------------------------------------------------------------------");

        // Imprimimos toda la lista de ReservacionServicio
        for (ReservacionServicio reservacionServicio : lista) {
            Reservacion reservacion = reservacionServicio.getReserva();
            ServiciosAdicionales servicioM = reservacionServicio.getServicio();
            System.out.printf("%-10d %-20s %-30.2f %-10s%n",
                    reservacion.getIdReserva(),  // Asegúrate de que este método exista
                    servicioM.getConcepto(),
                    servicioM.getCosto(),
                    reservacion.getEstado());  // Asegúrate de que este método exista
        }
        System.out.println("--------------------------------------------------------------------------------------");
    
    }

    @Override
    public ReservacionServicio obtenerPorId(List<ReservacionServicio> lista, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // Función para separar el string en base a " - " y agregar los elementos a una lista
    public  List<String> separarServicios(String servicios) {
        List<String> listaServicios = new ArrayList<>();
        String[] serviciosArray = servicios.split(" - ");
        for (String servicioM : serviciosArray) {
            listaServicios.add(servicioM.trim());
        }
        return listaServicios;
    }
    // Función para agregar ReservaServicios a partir de la lista de servicios
    public  void crearReservaServicios(List<String> listaSolicitud, List<ServiciosAdicionales> serviciosDisponibles, Reservacion nuevareserva, List<ReservacionServicio> reservasServicio) {
         
      
        for (String servicioNombre : listaSolicitud) {
            // Buscar el servicio correspondiente en la lista de servicios disponibles
            for (ServiciosAdicionales servicioM : serviciosDisponibles) {
                if (servicioM.getConcepto().equalsIgnoreCase(servicioNombre)) {
                    // Si coincide, crea un nuevo objeto ReservaServicios con idReserva y el id del servicio
                    ReservacionServicio nuevaReservaServicio = new ReservacionServicio(
                                                                                    nuevareserva, 
                                                                                    servicioM.obtenerPorId(serviciosDisponibles, servicioM.getId()));
                    agregar(reservasServicio, nuevaReservaServicio);
                    
    
                }
            }
        }
    }


    
    
    
    
    
    
    
}

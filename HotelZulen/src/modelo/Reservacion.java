    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.HabitacionRepository;
import Persistencia.HuespedRepository;
import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionRepository;
import Persistencia.ReservacionServicioRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class Reservacion   {

    private int idReserva;
    private int numHabitaciones;
    private String estado;
    private LocalDateTime incioHuesped;
    private LocalDateTime finHuesped;
    private LocalDateTime fechaCrea;
    private LocalDateTime fechaMod;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private HuespedRepository repoHuesped;
    private HabitacionRepository repoHab;
    private ReservacionRepository repoReserva;
    private ReservacionHuespedRepository repoReservaHuesped;
    private ReservacionHabitacionesRepository repoReservaHab;
    private ReservacionServicioRepository repoReservaServ;

    public Reservacion() {
        this.repoHuesped = new HuespedRepository();
        this.repoHab = new HabitacionRepository();
        this.repoReserva = new ReservacionRepository();
        this.repoReservaHab = new ReservacionHabitacionesRepository();
        this.repoReservaHuesped = new ReservacionHuespedRepository();
        this.repoReservaServ = new ReservacionServicioRepository();

    }

    // Para crear una reserva para despues 
    public Reservacion(int numHabitaciones, String estado, LocalDateTime incioHuesped, LocalDateTime finHuesped) {
        this.numHabitaciones = numHabitaciones;
        this.estado = estado;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
        this.fechaCrea = LocalDateTime.now();
        this.fechaMod = null;
        this.repoHuesped = new HuespedRepository();
        this.repoHab = new HabitacionRepository();
        this.repoReserva = new ReservacionRepository();
        this.repoReservaHab = new ReservacionHabitacionesRepository();
        this.repoReservaHuesped = new ReservacionHuespedRepository();
        this.repoReservaServ = new ReservacionServicioRepository();
    }

    // Para crear una "reserva" que es en el momento. (Se considera checkin)
    public Reservacion(int numHabitaciones, String estado, LocalDateTime incioHuesped, LocalDateTime finHuesped, LocalDateTime checkIn) {
        this.numHabitaciones = numHabitaciones;
        this.estado = estado;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
        this.fechaCrea = LocalDateTime.now();
        this.fechaMod = null;
        this.checkIn = checkIn;
        this.repoHuesped = new HuespedRepository();
        this.repoHab = new HabitacionRepository();
        this.repoReserva = new ReservacionRepository();
        this.repoReservaHab = new ReservacionHabitacionesRepository();
        this.repoReservaHuesped = new ReservacionHuespedRepository();
        this.repoReservaServ = new ReservacionServicioRepository();
    }

    //Al momento de recuperar una reserva y no tiene todos los campos llenos - Sin check-in
    public Reservacion(int idReserva, int numHabitaciones, String estado, LocalDateTime incioHuesped, LocalDateTime finHuesped, LocalDateTime fechaCrea) {
        this.idReserva = idReserva;
        this.numHabitaciones = numHabitaciones;
        this.estado = estado;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
        this.fechaCrea = fechaCrea;
        this.repoHuesped = new HuespedRepository();
        this.repoHab = new HabitacionRepository();
        this.repoReserva = new ReservacionRepository();
        this.repoReservaHab = new ReservacionHabitacionesRepository();
        this.repoReservaHuesped = new ReservacionHuespedRepository();
        this.repoReservaServ = new ReservacionServicioRepository();
    }

    //Al momento de recuperar los datos de una reserva y no tiene todos los campos llenos
    public Reservacion(int idReserva, int numHabitaciones, String estado, LocalDateTime incioHuesped, LocalDateTime finHuesped, LocalDateTime fechaCrea, LocalDateTime checkIn) {
        this.idReserva = idReserva;
        this.numHabitaciones = numHabitaciones;
        this.estado = estado;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
        this.fechaCrea = fechaCrea;
        this.checkIn = checkIn;
        this.repoHuesped = new HuespedRepository();
        this.repoHab = new HabitacionRepository();
        this.repoReserva = new ReservacionRepository();
        this.repoReservaHab = new ReservacionHabitacionesRepository();
        this.repoReservaHuesped = new ReservacionHuespedRepository();
        this.repoReservaServ = new ReservacionServicioRepository();
    }

    //Al momento de recuperar una reserva y tiene todos los campos llenos
    public Reservacion(int idReserva, int numHabitaciones, String estado, LocalDateTime incioHuesped, LocalDateTime finHuesped, LocalDateTime fechaCrea,LocalDateTime fechaMod, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.idReserva = idReserva;
        this.numHabitaciones = numHabitaciones;
        this.estado = estado;
        this.incioHuesped = incioHuesped;
        this.finHuesped = finHuesped;
        this.fechaCrea = fechaCrea;
        this.fechaMod = fechaMod;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.repoHuesped = new HuespedRepository();
        this.repoHab = new HabitacionRepository();
        this.repoReserva = new ReservacionRepository();
        this.repoReservaHab = new ReservacionHabitacionesRepository();
        this.repoReservaHuesped = new ReservacionHuespedRepository();
        this.repoReservaServ = new ReservacionServicioRepository();
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

    public LocalDateTime getIncioHuesped() {
        return incioHuesped;
    }

    public void setIncioHuesped(LocalDateTime incioHuesped) {
        this.incioHuesped = incioHuesped;
    }

    public LocalDateTime getFinHuesped() {
        return finHuesped;
    }

    public void setFinHuesped(LocalDateTime finHuesped) {
        this.finHuesped = finHuesped;
    }

    public LocalDateTime getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(LocalDateTime fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public LocalDateTime getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(LocalDateTime fechaMod) {
        this.fechaMod = fechaMod;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }
    //Para crear
    public void setCheckIn() {
        this.checkIn = LocalDateTime.now();
    }
    //Para recibir
    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public ReservacionRepository getRepoReserva() {
        return repoReserva;
    }

    public void setRepoReserva(ReservacionRepository repoReserva) {
        this.repoReserva = repoReserva;
    }

    public void crearReservacion(Reservacion reservacion, List<Huesped> listaHuespedes, List<Habitacion> listaHabitaciones, List<ServiciosAdicionales> listaServicios) {

        repoHuesped.crearHuespedes(listaHuespedes);
        int idReservacion = repoReserva.crearReserva(reservacion);
        if (idReservacion > 0) {
            // Asociar los huéspedes a la nueva reserva
            repoReservaHuesped.asociarReservaHuespedes(idReservacion, listaHuespedes);
            repoReservaHab.asociarReservaHabitacion(idReservacion, listaHabitaciones);
            repoReservaServ.asociarReservaServi(idReservacion, listaServicios);
            repoHab.setOcupados(listaHabitaciones);

            System.out.println("Reserva generada satisfactoriamente!");

        }
    }

    public void reservarAhora(Reservacion reservacion, List<Huesped> listaHuespedes, List<Habitacion> listaHabitaciones, List<ServiciosAdicionales> listaServicios) {

        repoHuesped.crearHuespedes(listaHuespedes);
        int idReservacion = repoReserva.reservarAhora(reservacion);
        if (idReservacion > 0) {
            // Asociar los huéspedes a la nueva reserva
            repoReservaHuesped.asociarReservaHuespedes(idReservacion, listaHuespedes);
            repoReservaHab.asociarReservaHabitacion(idReservacion, listaHabitaciones);
            repoReservaServ.asociarReservaServi(idReservacion, listaServicios);
            repoHab.setOcupados(listaHabitaciones);

            System.out.println("Reserva generada satisfactoriamente!");

        }
    }
   
   public List<Reservacion> obtenerXDniYEstado(int dni,String stado){
       return repoReserva.obtenerReservasPorHuespedYEstado(dni, stado);    
   }
   
   public int seleccionarReserva(JTable Tabla) {
        try {
            int fila = Tabla.getSelectedRow();

            if (fila >= 0) {
                int id = Integer.parseInt(Tabla.getValueAt(fila, 0).toString());
                return id;
            } else {
                System.out.println("No se ha seleccionado ninguna fila.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar la fila: " + e.getMessage());
            e.printStackTrace(); // Para depuración
            return -1;
        }
        
    }
   public List<Habitacion> obtenerHabitacionesPorReservacion(int id) {
       List<Habitacion> listaHabitaciones = repoReservaHab.obtenerHabitacionesPorReservacion(id);
       return listaHabitaciones;
   }
   /*public List<ServiciosAdicionales> obtenerServiciosPorReservacion(int id){
       List<ServiciosAdicionales> listaServicios = repoReservaServ.obtener(id);
       return listaServicios
   }*/
    public List<Huesped> obtenerHuespedesXReserva(int id){
       List<Huesped> listaHuespedes = repoReservaHuesped.obtenerHuespedesPorReserva(idReserva);
       return listaHuespedes;
   }
   public Reservacion obtenerReserva (int id){
       return repoReserva.obtener(id);
   }
   public void actualizarCheckIn (Reservacion obj){
       repoReserva.actualizarCheckIn(obj);
   }
   public int obteneridReservaXidHabitacion(int idHabitacion){
       return repoReservaHab.obtenerIdReservaXHabitacion(idHabitacion);
   }
}


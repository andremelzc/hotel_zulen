/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import modelo.InicioSession;
import Persistencia.DatabaseConnection;
import Persistencia.HabitacionRepository;
import Persistencia.HuespedRepository;

import Persistencia.ReservacionRepository;
import Persistencia.TipoHabitacionRepository;
import Persistencia.InicioSesionRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ServiciosAdicionales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import modelo.TipoDeHabitacion;

/**
 *
 * @author Suyco
 */
public class BD_Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DatabaseConnection.testConnection();
        // Crear una instancia de InicioSesion para probar el método iniciarSesion
        InicioSession inicioSesion = new InicioSession();

        // Llamar a iniciarSesion y verificar si se devuelve un objeto Personal no nulo
        //inicioSesion.iniciarSesion("miguelgiron", "miguel00");
        //inicioSesion.cerrarSesion();
        //HuespedRepository huespedRepo = new HuespedRepository();
        //Huesped huesped = new Huesped(12345678, "Juan", "Pérez", 987654321,
          //  "Dirección", "usuario", "contraseña", "Activo", true);
        //String gaa=huesped.getContrasena();
        //System.out.println(gaa);
        //huespedRepo.crear(huesped);
        //huespedRepo.eliminar(12345678);
        /*
        ReservacionController reservaController = new ReservacionController();
        ReservacionRepository reservaRepo = new ReservacionRepository();
        LocalDate inicioHuesped = LocalDate.of(2024, 11, 4); // Fecha de inicio específica
        LocalDate finHuesped = LocalDate.of(2024, 11, 10);   // Fecha de fin específica
        
        Reservacion reservacion = new Reservacion(1, "Reservado", inicioHuesped, finHuesped);
        Huesped huesped1= new Huesped(123, "Andrey", "Zafra Venegas", 9999, "Mz G ", "pepe8", "andrey00", "Activo", Boolean.TRUE);
        Huesped huesped2= new Huesped(465, "BIzarro", "Zafra Venegas", 9999, "Mz G ", "pepe8", "bizarro00", "Activo", Boolean.FALSE);
        ServiciosAdicionales servicio1 = new ServiciosAdicionales(1, "masaje", 122, "Activo");
        ServiciosAdicionales servicio2 = new ServiciosAdicionales(2,"piscina", 333, "Activo");
        //TipoDeHabitacion tipoHab = new TipoDeHabitacion(1,"Bussiness", 123);
        //Habitacion habitacion1 = new Habitacion();
        HabitacionRepository habRepo = new HabitacionRepository();
        Habitacion habitacion1 = new Habitacion();
        habitacion1= habRepo.obtener(89);
        List<Huesped> listaHuespedes = new ArrayList<>();
        List<ServiciosAdicionales> listaServicios = new ArrayList<>();
        List<Habitacion> listaHabitacion = new ArrayList<>();
        
        listaHuespedes.add(huesped1);
        listaHuespedes.add(huesped2);
        listaServicios.add(servicio1);
        listaServicios.add(servicio2);
        listaHabitacion.add(habitacion1);
        int numerito = reservaRepo.crearReserva(reservacion);
        System.out.println("numerito: "+numerito);
        
        reservaController.crearReservacion(reservacion, listaHuespedes, listaHabitacion, listaServicios);
        */
    /* try (Connection connection = DatabaseConnection.getConnection()) {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, "hotel_zulen", "reservaciones", "NumeroHabitaciones");
        
        if (columns.next()) {
            System.out.println("Columna NumeroHabitaciones encontrada exitosamente.");
        } else {
            System.out.println("Columna NumeroHabitaciones no encontrada en la tabla reservaciones.");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }*/
    /*TipoDeHabitacion tipoHab = new TipoDeHabitacion(4,"Business", 300);
    TipoHabitacionRepository TipoRepo = new TipoHabitacionRepository();
    HabitacionRepository habRepo=new HabitacionRepository();
    //TipoRepo.crear(tipoHab);
    Habitacion habi= new Habitacion(1,tipoHab,"2","Disponible");
    habRepo.crear(habi);
    */
    }
}

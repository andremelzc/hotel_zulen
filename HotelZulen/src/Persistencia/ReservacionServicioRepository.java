/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.util.List;
import modelo.Reservacion;
import modelo.ReservacionServicio;
import modelo.ServiciosAdicionales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Statement; // <-- Agrega esta línea
import java.util.ArrayList;
/**
 *
 * @author Suyco
 */
public class ReservacionServicioRepository implements IRepository<ReservacionServicio> {

  
    public void asociarReservaServi(int idReservacion, List<ServiciosAdicionales> listaServicios) {
        String sql = "INSERT INTO reservaciones_has_servicios_adicionales (RESERVACIONES_idReservaciones, SERVICIOS_ADICIONALES_idSERVICIOS_UNICO) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);  // Iniciar la transacción
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                
                for (ServiciosAdicionales servicio : listaServicios) {
                    stmt.setInt(1, idReservacion);
                    stmt.setInt(2, servicio.getId());                    

                    stmt.addBatch();
                }
                stmt.executeBatch();
                connection.commit();  // Confirmar la transacción
                System.out.println("Reserva asociada al servicio(s) exitosamente!");
                
            } catch (SQLException e) {
                connection.rollback();  // Hacer rollback en caso de error
                System.out.println("Error al asociar la reserva: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
        }
    }
    @Override
    public void crear(ReservacionServicio objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReservacionServicio obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(ReservacionServicio objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<ServiciosAdicionales> obtenerServiciosXIdReserva(int idReserva) {
        List<ServiciosAdicionales> servicios = new ArrayList<>();
        String sql = "SELECT sa.idSERVICIOS_UNICO, sa.NombreServicio, sa.Costo, sa.Estado " +
                     "FROM reservaciones_has_servicios_adicionales rsa " +
                     "INNER JOIN servicios_adicionales sa ON rsa.SERVICIOS_ADICIONALES_idSERVICIOS_UNICO = sa.idSERVICIOS_UNICO " +
                     "WHERE rsa.RESERVACIONES_idReservaciones = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Establecer el idReserva en el PreparedStatement
            stmt.setInt(1, idReserva);

            ResultSet rs = stmt.executeQuery();

            // Mapear los resultados a objetos ServiciosAdicionales
            while (rs.next()) {
                ServiciosAdicionales servicio = new ServiciosAdicionales();
                servicio.setId(rs.getInt("idSERVICIOS_UNICO"));
                servicio.setConcepto(rs.getString("NombreServicio"));
                servicio.setCosto(rs.getDouble("Costo"));
                servicio.setEstado(rs.getString("Estado"));
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return servicios;
    }

}

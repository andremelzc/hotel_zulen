/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ResevacionHabitacion;
import modelo.TipoDeHabitacion;

public class ReservacionHabitacionesRepository implements IRepository<ResevacionHabitacion> {

    public void asociarReservaHabitacion(int idReservacion, List<Habitacion> habitaciones) {
        String sql = "INSERT INTO reservaciones_has_habitaciones (HABITACIONES_idHabitaciones, HABITACIONES_TIPO_HAB_idCategoria, RESERVACIONES_idReservaciones) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);  // Iniciar la transacción
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {

                for (Habitacion habitacion : habitaciones) {
                    stmt.setInt(1, habitacion.getId());
                    stmt.setInt(2, habitacion.getTipoHabitacion().getId());
                    stmt.setInt(3, idReservacion);
                    stmt.addBatch();
                }
                stmt.executeBatch();
                connection.commit();  // Confirmar la transacción
                System.out.println("Reserva asociada a la(s) habitacion(es) exitosamente!");

            } catch (SQLException e) {
                connection.rollback();  // Hacer rollback en caso de error
                System.out.println("Error al asociar la reserva: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
        }
    }

    public List<Habitacion> obtenerHabitacionesPorReservacion(int id) {
        List<Habitacion> habitaciones = new ArrayList<>();
        HabitacionRepository habitacionRepository = new HabitacionRepository();
        
        String sql = "SELECT * FROM reservaciones_has_habitaciones WHERE RESERVACIONES_idReservaciones = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = habitacionRepository.obtener(rs.getInt("HABITACIONES_idHabitaciones"));
                habitaciones.add(habitacion);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }
    
    public boolean relacionHabitacionReserva(int idHabitacion, int idReserva) {
        boolean relacion = false;
        String sql = "SELECT * FROM reservaciones_has_habitaciones WHERE RESERVACIONES_idReservaciones = ? AND HABITACIONES_idHabitaciones = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReserva);
            stmt.setInt(2, idHabitacion);
            ResultSet rs = stmt.executeQuery();

            // If there's a result, then the room is related to the reservation
            if (rs.next()) {
                relacion = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    return relacion;
    }

    @Override
    public void crear(ResevacionHabitacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResevacionHabitacion obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(ResevacionHabitacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Obtener el id de la reserva x el id de habitaicon, obviamente no debe estar disponible
    public Reservacion obtenerReservaXHabitacion(int idHabitacion) {
        // Consulta SQL para obtener una reserva asociada a la habitación con un estado distinto de 'Disponible'
        String sql = "SELECT r.idReservaciones, r.NumeroHabitaciones, r.FechaInicio, r.FechaFinal, r.Estado, "
                   + "r.FechaCreacion, r.FechaMod, r.CheckIn, r.CheckOut "
                   + "FROM reservaciones r "
                   + "JOIN reservaciones_has_habitaciones rh ON r.idReservaciones = rh.RESERVACIONES_idReservaciones "
                   + "WHERE rh.HABITACIONES_idHabitaciones = ? AND r.Estado != 'Disponible'";  // Filtrar solo reservas no disponibles

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Asignar el id de la habitación a la consulta
            stmt.setInt(1, idHabitacion);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Si encontramos una reserva que cumple con las condiciones
            if (rs.next()) {
                // Obtener y convertir las fechas
                LocalDateTime fechaInicio = rs.getTimestamp("FechaInicio").toLocalDateTime();
                LocalDateTime fechaFinal = rs.getTimestamp("FechaFinal").toLocalDateTime();
                LocalDateTime fechaCreacion = rs.getTimestamp("FechaCreacion").toLocalDateTime();
                LocalDateTime fechaMod = rs.getTimestamp("FechaMod") != null ? rs.getTimestamp("FechaMod").toLocalDateTime() : null;
                LocalDateTime checkIn = rs.getTimestamp("CheckIn") != null ? rs.getTimestamp("CheckIn").toLocalDateTime() : null;
                LocalDateTime checkOut = rs.getTimestamp("CheckOut") != null ? rs.getTimestamp("CheckOut").toLocalDateTime() : null;

                // Crear y devolver el objeto Reservacion
                return new Reservacion(
                    rs.getInt("idReservaciones"),
                    rs.getInt("NumeroHabitaciones"),
                    rs.getString("Estado"),
                    fechaInicio,
                    fechaFinal,
                    fechaCreacion,
                    fechaMod,
                    checkIn,
                    checkOut
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si no se encontró una reserva, retornamos null
        return null;
    }
    public int obtenerIdReservaXHabitacion(int idHabitacion) {
    // Consulta SQL para obtener el id de la reserva asociada a la habitación, si la habitación no está en estado 'Disponible'
    String sql = "SELECT r.idReservaciones "
               + "FROM reservaciones r "
               + "JOIN reservaciones_has_habitaciones rh ON r.idReservaciones = rh.RESERVACIONES_idReservaciones "
               + "JOIN habitaciones h ON h.idHabitaciones = rh.HABITACIONES_idHabitaciones "
               + "WHERE rh.HABITACIONES_idHabitaciones = ? AND h.Estado != 'Disponible'"; // Filtrar habitaciones no disponibles

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Asignar el id de la habitación a la consulta
        stmt.setInt(1, idHabitacion);

        // Ejecutar la consulta
        ResultSet rs = stmt.executeQuery();

        // Si encontramos una reserva asociada a la habitación
        if (rs.next()) {
            // Retornar el id de la reserva
            return rs.getInt("idReservaciones");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Si no se encuentra una reserva válida, retornamos null
    return 0;
}

}

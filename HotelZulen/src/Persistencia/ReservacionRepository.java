/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import modelo.Reservacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Statement; // <-- Agrega esta línea
import java.sql.Timestamp;
import java.time.LocalDateTime;
import modelo.Combo;

public class ReservacionRepository implements IRepository <Reservacion>{ 
    
    
    @Override
    public Reservacion obtener(int id) {
        String sql = "SELECT * FROM reservaciones WHERE idReservaciones = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                
                Timestamp fechaComienzoSQL = rs.getTimestamp("FechaInicio");
                LocalDateTime fechaComienzo = fechaComienzoSQL.toLocalDateTime();
                
                Timestamp fechaFinSQL = rs.getTimestamp("FechaFinal");
                LocalDateTime fechaFin = fechaFinSQL.toLocalDateTime();
                
                Timestamp fechaCreacionSQL = rs.getTimestamp("FechaCreacion");
                LocalDateTime fechaCreacion = fechaCreacionSQL.toLocalDateTime();
                
                return new Reservacion(
                        rs.getInt("idReservaciones"), 
                        rs.getInt("NumeroHabitaciones"), 
                        rs.getString("Estado"), 
                        fechaComienzo, 
                        fechaFin, 
                        fechaCreacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra, retorna null
    }

    @Override
    public void actualizar(Reservacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void crear(Reservacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int crearReserva(Reservacion reservacion) {
        // Consulta SQL para insertar una nueva reserva
        String sql = "INSERT INTO reservaciones (NumeroHabitaciones, FechaInicio, FechaFinal, Estado,FechaCreacion) VALUES (?, ?, ?, ?,?)";

        // Establecer la conexión y preparar la declaración
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la reserva
            stmt.setInt(1, reservacion.getNumHabitaciones()); // Número de habitaciones
            stmt.setTimestamp(2, Timestamp.valueOf(reservacion.getIncioHuesped())); // Fecha de inicio
            stmt.setTimestamp(3, Timestamp.valueOf(reservacion.getFinHuesped())); // Fecha final
            stmt.setString(4, reservacion.getEstado()); // Estado de la reserva
            stmt.setTimestamp(5, Timestamp.valueOf(reservacion.getFechaCrea())); // Fecha final
            

            // Ejecutar la inserción de la reserva
            int affectedRows = stmt.executeUpdate();

            // Verificar si se insertó alguna fila
            if (affectedRows > 0) {
                // Obtener el ID generado
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Retorna el ID de la reserva creada
                    } else {
                        System.out.println("No se pudo obtener el ID de la reserva.");
                        return -1; // Retorna -1 si no se pudo obtener el ID
                    }
                }
            } else {
                System.out.println("No se pudo crear la reserva.");
                return -1; // Retorna -1 si no se creó la reserva
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para depuración
            return -1; // Retorna -1 en caso de error
        }
    }
    public int reservarAhora(Reservacion reservacion) {
        // Consulta SQL para insertar una nueva reserva con checkIn y datetime
        String sql = "INSERT INTO reservaciones (NumeroHabitaciones, FechaInicio, FechaFinal, Estado, FechaCreacion, CheckIn) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la reserva
            stmt.setInt(1, reservacion.getNumHabitaciones()); // Número de habitaciones
            stmt.setTimestamp(2, Timestamp.valueOf(reservacion.getIncioHuesped())); // Fecha de inicio
            stmt.setTimestamp(3, Timestamp.valueOf(reservacion.getFinHuesped())); // Fecha final
            stmt.setString(4, reservacion.getEstado()); // Estado de la reserva
            stmt.setTimestamp(5, Timestamp.valueOf(reservacion.getFechaCrea())); // Fecha de creación
            stmt.setTimestamp(6, Timestamp.valueOf(reservacion.getIncioHuesped())); // CheckIn, igual a inicioHuesped

            // Ejecutar la inserción de la reserva
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Retorna el ID de la reserva creada
                    } else {
                        System.out.println("No se pudo obtener el ID de la reserva.");
                        return -1;
                    }
                }
            } else {
                System.out.println("No se pudo crear la reserva.");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }


}


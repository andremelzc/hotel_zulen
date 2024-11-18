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
import java.sql.Statement; // <-- Agrega esta línea
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class ReservacionRepository implements IRepository <Reservacion>{ 
    
    
    @Override
    public Reservacion obtener(int id) {
        String sql = "SELECT * FROM reservaciones WHERE idReservaciones = ?";
        try (Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                
                Timestamp fechaComienzoSQL = rs.getTimestamp("FechaInicio");
                LocalDateTime fechaComienzo = fechaComienzoSQL.toLocalDateTime();
                
                Timestamp fechaFinSQL = rs.getTimestamp("FechaFinal");
                LocalDateTime fechaFin = fechaFinSQL.toLocalDateTime();
                
                Timestamp fechaCreacionSQL = rs.getTimestamp("FechaCreacion");
                LocalDateTime fechaCreacion = fechaCreacionSQL.toLocalDateTime();
                // Verificar si CheckIn y CheckOut son null
                Timestamp checkInTimestamp = rs.getTimestamp("CheckIn");
                Timestamp checkOutTimestamp = rs.getTimestamp("CheckOut");
                LocalDateTime FechaMod = rs.getTimestamp("FechaMod") != null ? rs.getTimestamp("FechaMod").toLocalDateTime() : null;
                
                
                if(checkInTimestamp != null && checkOutTimestamp != null){
                    LocalDateTime checkIn = checkInTimestamp.toLocalDateTime();
                    LocalDateTime checkOut = checkOutTimestamp.toLocalDateTime();
                    return new Reservacion(
                        rs.getInt("idReservaciones"), 
                        rs.getInt("NumeroHabitaciones"), 
                        rs.getString("Estado"), 
                        fechaComienzo, 
                        fechaFin, 
                        fechaCreacion,
                        FechaMod,
                        checkIn,
                        checkOut
                    );
                }else if (checkInTimestamp != null && checkOutTimestamp ==null ){
                    LocalDateTime checkIn = checkInTimestamp.toLocalDateTime();
                    return new Reservacion(
                        rs.getInt("idReservaciones"),
                        rs.getInt("NumeroHabitaciones"),
                        rs.getString("Estado"),
                        fechaComienzo,
                        fechaFin,
                        fechaCreacion,
                        FechaMod,
                        checkIn, 
                        null
                    );
                }else {
                    return new Reservacion(
                    rs.getInt("idReservaciones"),
                    rs.getInt("NumeroHabitaciones"),
                    rs.getString("Estado"),
                    fechaComienzo,
                    fechaFin,
                    fechaCreacion,
                    FechaMod,
                    null,
                    null
                    );
                }
                
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

    public void actualizarCheckIn(Reservacion reserva) {
        String sql = "UPDATE reservaciones SET CheckIn = ? WHERE idReservaciones = ?";

        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            if (reserva.getCheckIn() != null) {
                stmt.setTimestamp(1, Timestamp.valueOf(reserva.getCheckIn()));
            } else {
                stmt.setNull(1, java.sql.Types.TIMESTAMP); // Nunca pasará, pero esta bueno
            }
            
            stmt.setInt(2, reserva.getIdReserva());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        String sql = "INSERT INTO reservaciones (NumeroHabitaciones, FechaInicio, FechaFinal, Estado,FechaCreacion,FechaMod, CheckIn, CheckOut) VALUES (?, ?, ?, ?, ?,?,?,?)";

        // Establecer la conexión y preparar la declaración
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la reserva
            stmt.setInt(1, reservacion.getNumHabitaciones()); // Número de habitaciones
            stmt.setTimestamp(2, Timestamp.valueOf(reservacion.getIncioHuesped())); // Fecha de inicio
            stmt.setTimestamp(3, Timestamp.valueOf(reservacion.getFinHuesped())); // Fecha final
            stmt.setString(4, reservacion.getEstado()); // Estado de la reserva
            stmt.setTimestamp(5, Timestamp.valueOf(reservacion.getFechaCrea())); //
            stmt.setTimestamp(6, Timestamp.valueOf(reservacion.getFechaCrea())); //siempre fechaCrea
            stmt.setNull(7, java.sql.Types.NULL); // CheckIn a NULL
            stmt.setNull(8, java.sql.Types.NULL);

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
        String sql = "INSERT INTO reservaciones (NumeroHabitaciones, FechaInicio, FechaFinal, Estado, FechaCreacion,FechaMod, CheckIn) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Configurar los parámetros de la reserva
            stmt.setInt(1, reservacion.getNumHabitaciones()); // Número de habitaciones
            stmt.setTimestamp(2, Timestamp.valueOf(reservacion.getIncioHuesped())); // Fecha de inicio
            stmt.setTimestamp(3, Timestamp.valueOf(reservacion.getFinHuesped())); // Fecha final
            stmt.setString(4, reservacion.getEstado()); // Estado de la reserva
            stmt.setTimestamp(5, Timestamp.valueOf(reservacion.getFechaCrea())); // Fecha de creación
            stmt.setTimestamp(6, Timestamp.valueOf(reservacion.getFechaCrea())); // Fecha de creación
            stmt.setTimestamp(7, Timestamp.valueOf(reservacion.getIncioHuesped())); // CheckIn, igual a inicioHuesped

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
    
    public List<Reservacion> obtenerReservasPorHuespedYEstado(int dni, String estado) {
        List<Reservacion> reservas = new ArrayList<>();
        
        String sql = "SELECT r.* FROM reservaciones r " +
                     "JOIN reservaciones_has_huespedes rhh ON r.idReservaciones = rhh.RESERVACIONES_idReservaciones " +
                     "WHERE rhh.HUESPEDES_DNI = ? " +
                     (estado.equalsIgnoreCase("Todos") ? "" : "AND r.Estado = ?");
        

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, dni);
            if (!estado.equalsIgnoreCase("Todos")) {
                stmt.setString(2, estado);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDateTime fechaInicio = rs.getTimestamp("FechaInicio").toLocalDateTime();
                LocalDateTime fechaFinal = rs.getTimestamp("FechaFinal").toLocalDateTime();
                LocalDateTime fechaCreacion = rs.getTimestamp("FechaCreacion").toLocalDateTime();
                
                // Verificar si CheckIn y CheckOut son null
                Timestamp checkInTimestamp = rs.getTimestamp("CheckIn");
                Timestamp checkOutTimestamp = rs.getTimestamp("CheckOut");
                LocalDateTime FechaMod = rs.getTimestamp("FechaMod").toLocalDateTime();

                // Construir el objeto Reservacion según los valores de CheckIn y CheckOut
                if (checkInTimestamp != null && checkOutTimestamp != null) {
                    LocalDateTime checkIn = checkInTimestamp.toLocalDateTime();
                    LocalDateTime checkOut = checkOutTimestamp.toLocalDateTime();
                    Reservacion reservacion = new Reservacion(
                        rs.getInt("idReservaciones"),
                        rs.getInt("NumeroHabitaciones"),
                        rs.getString("Estado"),
                        fechaInicio,
                        fechaFinal,
                        fechaCreacion,
                        FechaMod,
                        checkIn,
                        checkOut
                    );
                    reservas.add(reservacion);
                } else if (checkInTimestamp != null && checkOutTimestamp == null) {
                    LocalDateTime checkIn = checkInTimestamp.toLocalDateTime();
                    Reservacion reservacion = new Reservacion(
                        rs.getInt("idReservaciones"),
                        rs.getInt("NumeroHabitaciones"),
                        rs.getString("Estado"),
                        fechaInicio,
                        fechaFinal,
                        fechaCreacion,
                        FechaMod,
                        checkIn, 
                        null
                    );
                    reservas.add(reservacion);
                } else {
                    Reservacion reservacion = new Reservacion(
                        rs.getInt("idReservaciones"),
                        rs.getInt("NumeroHabitaciones"),
                        rs.getString("Estado"),
                        fechaInicio,
                        fechaFinal,
                        fechaCreacion,
                        FechaMod,
                        null,
                        null
                    );
                    reservas.add(reservacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return reservas;
    }
    


}


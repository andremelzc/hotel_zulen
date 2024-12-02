/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Timestamp;
import modelo.Recepcionista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Boleta;
import modelo.Reservacion;

/**
 *
 * @author Suyco
 */
public class RecepcionistaRepository implements IRepository<Recepcionista> {

    @Override
    public void crear(Recepcionista recepcionista) {
        String sql = "INSERT INTO personal (DNI, TipoPersonal, Nombre, Apellidos, Telefono, Direccion, Usuario, Contrasena, Estado) VALUES (?, 'Recepcionista', ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, recepcionista.getDNI());
            stmt.setString(2, recepcionista.getNombre());
            stmt.setString(3, recepcionista.getApellido());
            stmt.setString(4, String.valueOf(recepcionista.getTelefono()));
            stmt.setString(5, recepcionista.getDireccion());
            stmt.setString(6, recepcionista.getUsuario());
            stmt.setString(7, recepcionista.getContrasena());
            stmt.setString(8, recepcionista.getEstado());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Recepcionista creado exitosamente!");
            }

        } catch (SQLException e) {
            System.out.println("Error al crear el recepcionista: " + e.getMessage());
        }
    }

    @Override
    public Recepcionista obtener(int dni) {
        String sql = "SELECT * FROM personal WHERE DNI = ? AND TipoPersonal = 'Recepcionista'";
        Recepcionista recepcionista = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                recepcionista = new Recepcionista(
                        rs.getInt("DNI"),
                        rs.getString("TipoPersonal"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getInt("Telefono"),
                        rs.getString("Direccion"),
                        rs.getString("Usuario"),
                        rs.getString("Contrasena"),
                        rs.getString("Estado")
                );
            } else {
                System.out.println("No se encontró un recepcionista con ese DNI.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el recepcionista: " + e.getMessage());
        }
        
        return recepcionista;
    }

    @Override
    public void actualizar(Recepcionista recepcionista) {
        String sql = "UPDATE personal SET Nombre = ?, Apellidos = ?, Telefono = ?, Direccion = ?, Usuario = ?, Contrasena = ?, Estado = ? WHERE DNI = ? AND TipoPersonal = 'Recepcionista'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, recepcionista.getNombre());
            stmt.setString(2, recepcionista.getApellido());
            stmt.setString(3, String.valueOf(recepcionista.getTelefono()));
            stmt.setString(4, recepcionista.getDireccion());
            stmt.setString(5, recepcionista.getUsuario());
            stmt.setString(6, recepcionista.getContrasena());
            stmt.setString(7, recepcionista.getEstado());
            stmt.setInt(8, recepcionista.getDNI());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Recepcionista actualizado exitosamente!");
            } else {
                System.out.println("No se encontró un recepcionista con ese DNI.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el recepcionista: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int dni) {
        String sql = "DELETE FROM personal WHERE DNI = ? AND TipoPersonal = 'Recepcionista'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, dni);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Recepcionista eliminado exitosamente!");
            } else {
                System.out.println("No se encontró un recepcionista con ese DNI.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el recepcionista: " + e.getMessage());
        }
    }
    
    public void crearBoleta(Boleta boleta) {
       
        String sql = "INSERT INTO pago (RESERVA_id, MetodoPagoInicial, PagoInicial, EstadoInicial, FechaPagoInicial) "
                   + "VALUES (?, ?, ?, ?, ?)";

        
        try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Configurar los valores en la consulta
            stmt.setInt(1, boleta.getReserva().getIdReserva());
            stmt.setString(2, boleta.getMetodoPagoInicial());
            stmt.setDouble(3, boleta.getPagoInicial());
            stmt.setString(4, boleta.getEstadoInicial());
            stmt.setTimestamp(5, Timestamp.valueOf(boleta.getFechaPagoInicial()));

            // Ejecutar la consulta
            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("La boleta se registró correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar la boleta: " + e.getMessage());
        }
    }
    public Boleta obtenerBoleta(int idReserva) {
    String sql = """
        SELECT
            MetodoPagoInicial,
            PagoInicial,
            EstadoInicial,
            FechaPagoInicial
        FROM pago
        WHERE RESERVA_id = ?;
    """;
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setInt(1, idReserva);  // Establecer el parámetro de idReserva
        ResultSet rs = stmt.executeQuery();

        // Verificar si la consulta devuelve un resultado
        if (rs.next()) {
            Boleta boleta = new Boleta();
            Reservacion reservacion = new Reservacion(); 
            reservacion.setIdReserva(idReserva); 
            boleta.setReserva(reservacion); 
            boleta.setMetodoPagoInicial(rs.getString("MetodoPagoInicial"));
            boleta.setPagoInicial(rs.getDouble("PagoInicial"));
            boleta.setEstadoInicial(rs.getString("EstadoInicial"));
            boleta.setFechaPagoInicial(rs.getTimestamp("FechaPagoInicial").toLocalDateTime());
            boleta.setMetodoPagoCheckOut(null);
            boleta.setFechaPagoCheckOut(null);
            boleta.setPagoCheckOut(null);
            boleta.setEstadoPagoCheckOut(null);
            return boleta;
        } else {
            System.out.println("No se encontró la boleta para la reserva con ID: " + idReserva);
            return null;
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener la boleta: " + e.getMessage());
        return null;
    }
}
    public void actualizarBoleta(Boleta boleta) {
    String sql = """
        UPDATE pago
        SET
            MetodoPagoInicial = ?,
            PagoInicial = ?,
            EstadoInicial = ?,
            FechaPagoInicial = ?,
            MetodoPagoCheckOut = ?,
            PagoCheckOut = ?,
            EstadoPagoCheckOut = ?,
            FechaPagoCheckOut = ?
        WHERE RESERVA_id = ?;
    """;

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Establecer los valores de los parámetros del PreparedStatement
        stmt.setString(1, boleta.getMetodoPagoInicial()); // MetodoPagoInicial
        stmt.setDouble(2, boleta.getPagoInicial()); // PagoInicial
        stmt.setString(3, boleta.getEstadoInicial()); // EstadoInicial
        stmt.setTimestamp(4, Timestamp.valueOf(boleta.getFechaPagoInicial())); // FechaPagoInicial
        stmt.setString(5, boleta.getMetodoPagoCheckOut()); // MetodoPagoCheckOut
        stmt.setDouble(6, boleta.getPagoCheckOut()); // PagoCheckOut
        stmt.setString(7, boleta.getEstadoPagoCheckOut()); // EstadoPagoCheckOut
        stmt.setTimestamp(8, Timestamp.valueOf(boleta.getFechaPagoCheckOut())); // FechaPagoCheckOut
        stmt.setInt(9, boleta.getReserva().getIdReserva()); // RESERVA_id

        // Ejecutar la actualización
        int filasActualizadas = stmt.executeUpdate();

        if (filasActualizadas > 0) {
            System.out.println("La boleta se actualizó correctamente.");
        } else {
            System.out.println("No se encontró la reserva para actualizar.");
        }

    } catch (SQLException e) {
        System.out.println("Error al actualizar la boleta: " + e.getMessage());
    }
}
    
    
}

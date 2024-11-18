/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import modelo.Habitacion;
import modelo.TipoDeHabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Combo;

public class HabitacionRepository implements IRepository<Habitacion> {

    @Override
    public void crear(Habitacion objeto) {
        String sql = "INSERT INTO habitaciones (idHabitaciones, Piso, Estado, TIPO_HAB_idCategoria) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, objeto.getId());
            stmt.setInt(2, Integer.parseInt(objeto.getPiso()));
            stmt.setString(3, objeto.getEstado());
            stmt.setInt(4, objeto.getTipoHabitacion().getId());
            stmt.executeUpdate();
            System.out.println("Habitación creada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Habitacion obtener(int id) {
        String sql = "SELECT * FROM habitaciones WHERE idHabitaciones = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TipoDeHabitacion tipo = new TipoHabitacionRepository().obtener(rs.getInt("TIPO_HAB_idCategoria"));
                return new Habitacion(
                        rs.getInt("idHabitaciones"),
                        tipo,
                        rs.getString("Piso"),
                        rs.getString("Estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra, retorna null
    }

    public Habitacion obtenerxTipo(int tipoHabitacion) {
        String sql = "SELECT * FROM habitaciones \n"
                + "WHERE TIPO_HAB_idCategoria = ? \n"
                + "AND Estado = 'Disponible' \n"
                + "LIMIT 1;";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, tipoHabitacion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TipoDeHabitacion tipo = new TipoHabitacionRepository().obtener(rs.getInt("TIPO_HAB_idCategoria"));
                return new Habitacion(
                        rs.getInt("idHabitaciones"),
                        tipo,
                        rs.getString("Piso"),
                        rs.getString("Estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra, retorna null
    }

    @Override
    public void actualizar(Habitacion objeto) {
        String sql = "UPDATE habitaciones SET Piso = ?, Estado = ?, TIPO_HAB_idCategoria = ? WHERE idHabitaciones = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, objeto.getPiso());
            stmt.setString(2, objeto.getEstado());
            stmt.setInt(3, objeto.getTipoHabitacion().getId());
            stmt.setInt(4, objeto.getId());
            stmt.executeUpdate();
            System.out.println("Habitación actualizada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM habitaciones WHERE idHabitaciones = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Habitación eliminada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setReservado(List<Habitacion> listaHabitaciones) {
        String sql = "UPDATE habitaciones SET Estado = ? WHERE idHabitaciones = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (Habitacion habitacion : listaHabitaciones) {
                stmt.setString(1, "Reservado"); // Configura el estado a "ocupado"
                stmt.setInt(2, habitacion.getId());
                stmt.executeUpdate();
            }

            System.out.println("Habitaciones actualizadas a 'ocupado' exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado de las habitaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setOcupados(List<Habitacion> listaHabitaciones) {
        String sql = "UPDATE habitaciones SET Estado = ? WHERE idHabitaciones = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (Habitacion habitacion : listaHabitaciones) {
                stmt.setString(1, "Ocupado"); // Configura el estado a "ocupado"
                stmt.setInt(2, habitacion.getId());
                stmt.executeUpdate();
            }

            System.out.println("Habitaciones actualizadas a 'ocupado' exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado de las habitaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Habitacion> obtenerTodos() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoDeHabitacion tipo = new TipoHabitacionRepository().obtener(rs.getInt("TIPO_HAB_idCategoria"));
                Habitacion habitacion = new Habitacion(
                        rs.getInt("idHabitaciones"),
                        tipo,
                        rs.getString("Piso"),
                        rs.getString("Estado")
                );
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }

    public List<Habitacion> filtrarHabitaciones(String piso, String tipoHabitacion) {
        List<Habitacion> habitaciones = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM habitaciones WHERE 1=1");

        // Construcción dinámica de la consulta
        if (!"Ninguno".equalsIgnoreCase(piso)) {
            sql.append(" AND Piso = ?");
        }
        if (!"Ninguno".equalsIgnoreCase(tipoHabitacion)) {
            sql.append(" AND TIPO_HAB_idCategoria = (SELECT idCategoria FROM tipo_hab WHERE Concepto = ?)");
        }

        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            // Seteo de parámetros en PreparedStatement
            if (!"ninguno".equalsIgnoreCase(piso)) {
                stmt.setInt(paramIndex++, Integer.parseInt(piso));
            }
            if (!"ninguno".equalsIgnoreCase(tipoHabitacion)) {
                stmt.setString(paramIndex++, tipoHabitacion);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TipoDeHabitacion tipo = new TipoHabitacionRepository().obtener(rs.getInt("TIPO_HAB_idCategoria"));
                Habitacion habitacion = new Habitacion(
                        rs.getInt("idHabitaciones"),
                        tipo,
                        rs.getString("Piso"),
                        rs.getString("Estado")
                );
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }

}

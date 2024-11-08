/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import modelo.Recepcionista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 
}

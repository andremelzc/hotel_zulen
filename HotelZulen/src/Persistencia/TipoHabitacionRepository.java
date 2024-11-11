/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import modelo.TipoDeHabitacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Suyco
 */
public class TipoHabitacionRepository implements IRepository<TipoDeHabitacion> {

    public TipoHabitacionRepository() {
    }

    
    
    @Override
    public void crear(TipoDeHabitacion objeto) {
         String sql = "INSERT INTO tipo_hab (idCategoria, Concepto,Precio, Estado) VALUES (?,?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getConcepto());
            stmt.setInt(3, (int)objeto.getPrecio());
            stmt.setString(4, objeto.getEstado());
            
            
            stmt.executeUpdate();
            System.out.println("Tipo de habitación creado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TipoDeHabitacion obtener(int id) {
        String sql = "SELECT * FROM tipo_hab WHERE idCategoria = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TipoDeHabitacion(
                    rs.getInt("idCategoria"),
                    rs.getString("Concepto"),
                    rs.getDouble("Precio"),
                    rs.getString("Estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra, retorna null
    }
    
    @Override
    public void actualizar(TipoDeHabitacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tipo_de_habitacion WHERE idCategoria = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Tipo de habitacion eliminado con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public TipoDeHabitacion obtenerPorConcepto(String concepto) {
    String sql = "SELECT * FROM tipo_hab WHERE Concepto = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, concepto); // Use concepto here
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new TipoDeHabitacion(
                rs.getInt("idCategoria"),
                rs.getString("Concepto"),
                rs.getDouble("Precio"),
                rs.getString("Estado")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Si no se encuentra, retorna null
}

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Consumible;

/**
 *
 * @author PC
 */
public class ConsumibleRepository implements IRepository<Consumible>{

    @Override
    public void crear(Consumible objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Consumible obtener(int id) {
        String sql = "SELECT * FROM consumible WHERE idCONSUMIBLE = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               return new Consumible(
                       rs.getInt("idCONSUMIBLE"),
                       rs.getString("NombreConsumible"),
                       rs.getFloat("Precio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra, retorna null
    }

    @Override
    public void actualizar(Consumible objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

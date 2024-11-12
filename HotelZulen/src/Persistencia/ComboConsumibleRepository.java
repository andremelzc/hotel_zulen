/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Combo;
import modelo.Consumible;
import modelo.ComboConsumible;
import modelo.Habitacion;
import modelo.TipoDeHabitacion;

/**
 *
 * @author PC
 */
public class ComboConsumibleRepository implements IRepository<ComboConsumible> {

    @Override
    public void crear(ComboConsumible objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(ComboConsumible objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ComboConsumible obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Consumible> obtenerConsumiblesPorCombo(int comboId) {
        List<Consumible> consumibles = new ArrayList<>();
        String sql = "SELECT c.idCONSUMIBLE, c.NombreConsumible, c.Precio " +
                 "FROM combo_has_consumible chc " +
                 "JOIN consumible c ON chc.CONSUMIBLE_idCONSUMIBLE = c.idCONSUMIBLE " +
                 "WHERE chc.COMBO_idCOMBO = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, comboId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Consumible consumible = new Consumible();
                consumible.setId(rs.getInt("idCONSUMIBLE"));
                consumible.setNombre(rs.getString("NombreConsumible"));
                consumible.setPrecio((float)rs.getDouble("Precio"));
                consumibles.add(consumible);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consumibles;
    }

}

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
    public void asociarComboConsumible(int idCombo, List<Consumible> listaConsumibles) {
        String sql = "INSERT INTO combo_has_consumible (COMBO_idCOMBO, CONSUMIBLE_idCONSUMIBLE) VALUES (?, ?)";

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            for (Consumible consumible : listaConsumibles) {
                stmt.setInt(1, idCombo); // Asocia el ID del combo
                stmt.setInt(2, consumible.getId()); // Obtiene el ID del consumible
                stmt.addBatch(); // Añade el batch para ejecución múltiple
            }

            // Ejecuta todas las sentencias preparadas en un solo paso
            stmt.executeBatch();

            System.out.println("Consumibles asociados correctamente al combo con ID: " + idCombo);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al asociar consumibles al combo.");
        }
    }
    
    public void modificarComboConsumibles(int idCombo, List<Consumible> listaConsumibles) {
        String consultaActualSQL = "SELECT CONSUMIBLE_idCONSUMIBLE FROM combo_has_consumible WHERE COMBO_idCOMBO = ?";
        String eliminarSQL = "DELETE FROM combo_has_consumible WHERE COMBO_idCOMBO = ? AND CONSUMIBLE_idCONSUMIBLE = ?";
        String insertarSQL = "INSERT INTO combo_has_consumible (COMBO_idCOMBO, CONSUMIBLE_idCONSUMIBLE) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Paso 1: Atrapar las id´s actuales de la BD
            List<Integer> idsActuales = new ArrayList<>();
            try (PreparedStatement consultaStmt = connection.prepareStatement(consultaActualSQL)) {
                consultaStmt.setInt(1, idCombo);
                try (ResultSet rs = consultaStmt.executeQuery()) {
                    while (rs.next()) {
                        idsActuales.add(rs.getInt(1));
                    }
                }
            }

            // Paso 2: Extraer los IDs de la nueva lista
            List<Integer> idsNuevos = listaConsumibles.stream()
                    .map(Consumible::getId)
                    .toList();

            // Paso 3: Comparar listas y crear dos listas más
            List<Integer> idsAEliminar = new ArrayList<>(idsActuales);
            idsAEliminar.removeAll(idsNuevos); // IDs que están en la base pero no en la nueva lista

            List<Integer> idsAInsertar = new ArrayList<>(idsNuevos);
            idsAInsertar.removeAll(idsActuales); // IDs que están en la nueva lista pero no en la base

            // Si no hay cambios, salir
            if (idsAEliminar.isEmpty() && idsAInsertar.isEmpty()) {
                System.out.println("No hay cambios necesarios.");
                return;
            }

            connection.setAutoCommit(false); // Iniciar transacción

            // Paso 4: Eliminar relaciones obsoletas
            try (PreparedStatement eliminarStmt = connection.prepareStatement(eliminarSQL)) {
                for (Integer idConsumible : idsAEliminar) {
                    eliminarStmt.setInt(1, idCombo);
                    eliminarStmt.setInt(2, idConsumible);
                    eliminarStmt.executeUpdate();
                    System.out.println("Relación eliminada: Combo " + idCombo + " con Consumible " + idConsumible);
                }
            }

            // Paso 5: Insertar nuevas relaciones
            try (PreparedStatement insertarStmt = connection.prepareStatement(insertarSQL)) {
                for (Integer idConsumible : idsAInsertar) {
                    insertarStmt.setInt(1, idCombo);
                    insertarStmt.setInt(2, idConsumible);
                    insertarStmt.executeUpdate();
                    System.out.println("Relación añadida: Combo " + idCombo + " con Consumible " + idConsumible);
                }
            }

            connection.commit(); 
        } catch (SQLException e) {
            System.err.println("Error al modificar las relaciones: " + e.getMessage());
        }
    }


}

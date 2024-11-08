/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import modelo.Huesped;
import modelo.ReservacionHabitacionCombo;

/**
 *
 * @author PC
 */
public class ReservacionHabitacionComboRepository implements IRepository<ReservacionHabitacionCombo> {

    @Override
    public void crear(ReservacionHabitacionCombo objeto) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReservacionHabitacionCombo obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(ReservacionHabitacionCombo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void asociarReservacionCombo(int idReservacion, int idHabitacion, Huesped huesped, int idCategoria, int idCombo, String estado, LocalDateTime fechaPedido) {
        String sql = "INSERT INTO reservaciones_has_habitaciones_has_combo (RESERVA_has_HAB_RESERVA_idReserva, RESERVA_has_HAB_HAB_idHabitaciones, RESERVA_has_HAB_HAB_TIPO_HAB_idCategoria, COMBO_idCOMBO, Estado, FechaPedido, FechaEnvio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Convertir LocalDateTime a Timestamp
            Timestamp fechaPedidoSQL = Timestamp.valueOf(fechaPedido);

            // Establecer los parámetros de la consulta SQL
            stmt.setInt(1, idReservacion);
            stmt.setInt(2, idHabitacion);
            stmt.setInt(3, idCategoria);
            stmt.setInt(4, idCombo);
            stmt.setString(5, estado);
            stmt.setTimestamp(6, fechaPedidoSQL); // Cambiado a setTimestamp para Timestamp
            stmt.setNull(7, java.sql.Types.TIMESTAMP); // Establecer FechaEnvio como NULL

            // Añadir la instrucción al lote
            stmt.addBatch();

            // Ejecutar todas las inserciones del lote
            stmt.executeBatch();

            System.out.println("Combo asociado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al asociar el combo: " + e.getMessage());
        }
    }

}

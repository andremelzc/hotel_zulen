/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.util.List;
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ReservacionHuesped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Suyco
 */
public class ReservacionHuespedRepository  {
    
    
    
    public void asociarReservaHuespedes(int idReservacion, List<Huesped> huespedes) {
        String sql = "INSERT INTO reservaciones_has_huespedes (HUESPEDES_DNI, RESERVACIONES_idReservaciones) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
             
            // Asociar cada huésped con la reserva
            for (Huesped huesped : huespedes) {
                stmt.setInt(1, huesped.getDNI()); // DNI del huésped
                stmt.setInt(2, idReservacion); // ID de la reserva
                stmt.addBatch(); // Agregar al lote
            }
            stmt.executeBatch(); // Ejecutar todas las inserciones
            System.out.println("Huéspedes asociados a la reserva exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al asociar los huéspedes: " + e.getMessage());
        }
    }


    
    public ReservacionHuesped obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void actualizar(ReservacionHuesped objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

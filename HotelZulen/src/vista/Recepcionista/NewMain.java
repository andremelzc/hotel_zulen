/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vista.Recepcionista;

import Persistencia.DatabaseConnection;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
/**
 *
 * @author Suyco
 */
public class NewMain {

    public static boolean verificarSolapamientosReservas(int idReservaciones, LocalDateTime nuevaFechaInicio, LocalDateTime nuevaFechaFin, List<Integer> listaHabitaciones) {
        
        String query = "SELECT h.idHabitaciones, r.idReservaciones, r.FechaInicio, r.FechaFinal " +
                       "FROM habitaciones h " +
                       "LEFT JOIN reservaciones_has_habitaciones rhh ON h.idHabitaciones = rhh.HABITACIONES_idHabitaciones " +
                       "LEFT JOIN reservaciones r ON rhh.RESERVACIONES_idReservaciones = r.idReservaciones " +
                       "WHERE h.idHabitaciones IN (" + String.join(",", Collections.nCopies(listaHabitaciones.size(), "?")) + ") " +
                       "AND r.idReservaciones != ? " +  // Excluir la propia reserva
                       "AND (r.FechaInicio <= ? AND r.FechaFinal >= ? " +
                       "OR r.FechaInicio BETWEEN ? AND ? " +
                       "OR r.FechaFinal BETWEEN ? AND ?);";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder mensajeSolapamientos = new StringBuilder();  // Para acumular los solapamientos
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            // Establecer parámetros para las habitaciones (69 y 88)
            int paramIndex = 1;
            for (int habitacionId : listaHabitaciones) {
                stmt.setInt(paramIndex++, habitacionId);
            }
            
            // Establecer el ID de la reserva que estamos modificando
            stmt.setInt(paramIndex++, idReservaciones);
            
            // Establecer las fechas de la nueva reserva
            stmt.setObject(paramIndex++, nuevaFechaInicio);
            stmt.setObject(paramIndex++, nuevaFechaFin);
            stmt.setObject(paramIndex++, nuevaFechaInicio);
            stmt.setObject(paramIndex++, nuevaFechaFin);
            stmt.setObject(paramIndex++, nuevaFechaInicio);
            stmt.setObject(paramIndex++, nuevaFechaFin);
            
            ResultSet rs = stmt.executeQuery();
            
            // Verificar los resultados
            boolean haySolapamientos = false;
            while (rs.next()) {
                int habitacionId = rs.getInt("idHabitaciones");
                int reservaId = rs.getInt("idReservaciones");
                LocalDateTime fechaInicioReserva = rs.getObject("FechaInicio", LocalDateTime.class);
                LocalDateTime fechaFinReserva = rs.getObject("FechaFinal", LocalDateTime.class);
                
                // Acumular la información del solapamiento
                haySolapamientos = true;
                mensajeSolapamientos.append("La habitación ").append(habitacionId)
                                     .append(" está ocupada por la reserva ").append(reservaId)
                                     .append(" desde ").append(fechaInicioReserva.format(formatter))
                                     .append(" hasta ").append(fechaFinReserva.format(formatter)).append("\n");
            }
            
            if (haySolapamientos) {
                
                JOptionPane.showMessageDialog(null, mensajeSolapamientos.toString(), "Solapamientos Encontrados", JOptionPane.WARNING_MESSAGE);
                return true;
            } else {
                
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static void main(String[] args) {
        


        // Establecer la fecha y hora de inicio y fin de la nueva reserva
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Crear fechas de ejemplo para la prueba
        String fechaInicioStr = "2024-11-20 14:00:00";  // Fecha de inicio
        String fechaFinStr = "2024-12-23 12:00:00";    // Fecha de fin

        LocalDateTime nuevaFechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime nuevaFechaFinal = LocalDateTime.parse(fechaFinStr, formatter);

        // Imprimir las fechas de inicio y fin
        System.out.println("Fecha de inicio de la nueva reserva: " + nuevaFechaInicio);
        System.out.println("Fecha de fin de la nueva reserva: " + nuevaFechaFinal);

        // Ahora puedes llamar a tu función verificarDisponibilidadReservas con estos valores
        // Suponiendo que tienes una lista de habitaciones, por ejemplo, habitaciones 101, 102 y 103:
        List<Integer> listaHabitaciones = List.of(69,88);
        int idReservaciones = 3;
        // Suponiendo que la función que verifica la disponibilidad de las habitaciones ya esté implementada
        boolean solapa = verificarSolapamientosReservas(idReservaciones,nuevaFechaInicio, nuevaFechaFinal, listaHabitaciones);
        if(!solapa){
            JOptionPane.showMessageDialog(null, "No hay solapamientos. Puedes modificar la reserva.", "Sin Solapamientos", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        
        
    }
    
}

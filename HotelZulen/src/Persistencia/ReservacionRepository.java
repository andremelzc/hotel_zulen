/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Reservacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Statement; // <-- Agrega esta línea

public class ReservacionRepository implements IRepository <Reservacion>{ 
    
    
    @Override
    public Reservacion obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Reservacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void crear(Reservacion objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int crearReserva(Reservacion reservacion) {
    // Consulta SQL para insertar una nueva reserva
    String sql = "INSERT INTO reservaciones (NumeroHabitaciones, FechaInicio, FechaFinal, Estado) VALUES (?, ?, ?, ?)";
    
    // Establecer la conexión y preparar la declaración
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
         
        // Configurar los parámetros de la reserva
        stmt.setInt(1, reservacion.getNumHabitaciones()); // Número de habitaciones
        stmt.setDate(2, java.sql.Date.valueOf(reservacion.getIncioHuesped())); // Fecha de inicio
        stmt.setDate(3, java.sql.Date.valueOf(reservacion.getFinHuesped())); // Fecha final
        stmt.setString(4, reservacion.getEstado()); // Estado de la reserva

        // Ejecutar la inserción de la reserva
        int affectedRows = stmt.executeUpdate();
        
        // Verificar si se insertó alguna fila
        if (affectedRows > 0) {
            // Obtener el ID generado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna el ID de la reserva creada
                } else {
                    System.out.println("No se pudo obtener el ID de la reserva.");
                    return -1; // Retorna -1 si no se pudo obtener el ID
                }
            }
        } else {
            System.out.println("No se pudo crear la reserva.");
            return -1; // Retorna -1 si no se creó la reserva
        }
    } catch (SQLException e) {
        System.out.println("Error al crear la reserva: " + e.getMessage());
        e.printStackTrace(); // Imprime el stack trace para depuración
        return -1; // Retorna -1 en caso de error
    }
}
}


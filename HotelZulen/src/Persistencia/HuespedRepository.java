package Persistencia;

import Persistencia.IRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modelo.Habitacion;
import modelo.Huesped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Suyco
 */
public class HuespedRepository implements IRepository<Huesped>{
    
    @Override
    public void crear(Huesped huesped) {
        String sql = "INSERT INTO huespedes (DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, EsTitular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, huesped.getDNI());
            stmt.setString(2, huesped.getNombre());
            stmt.setString(3, huesped.getApellido());
            stmt.setString(4, String.valueOf(huesped.getTelefono())); // Convertir telefono a String si es int en el objeto
            stmt.setString(5, huesped.getDireccion());
            stmt.setString(6, huesped.getUsuario());
            stmt.setString(7, huesped.getContrasena());
            stmt.setString(8, huesped.getEstado());
            stmt.setBoolean(9, huesped.getEsTitular());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Huesped "+huesped.getNombre()+" creado exitosamente!");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al crear el huesped: " + e.getMessage());
        }
    }


    @Override
    public Huesped obtener(int dni) {
        String sql = "SELECT DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contrasena, Estado, EsTitular FROM huespedes WHERE DNI = ?";
        Huesped huesped = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
        
            stmt.setInt(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                huesped = new Huesped();
                huesped.setDNI(rs.getInt("DNI")) ;
                huesped.setNombre(rs.getString("Nombre"));
                huesped.setApellido(rs.getString("Apellidos")) ;
                huesped.setTelefono( Integer.parseInt(rs.getString("Telefono")));
                huesped.setDireccion(rs.getString("Direccion"));
                huesped.setUsuario(rs.getString("Usuario"));
                huesped.setContrasena(rs.getString("Contrasena"));
                huesped.setEstado(rs.getString("Estado"));
                huesped.setEsTitular(rs.getBoolean("EsTitular"));
        
                return huesped;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el huesped: " + e.getMessage());
        }
        return null;
    }

        @Override
public void actualizar(Huesped huesped) {
    String sql = "UPDATE huespedes SET Nombre = ?, Apellidos = ?, Telefono = ?, Direccion = ?, Usuario = ?, Contrasena = ?, Estado = ?, EsTitular = ? WHERE DNI = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        
        stmt.setString(1, huesped.getNombre());
        stmt.setString(2, huesped.getApellido());
        stmt.setString(3, String.valueOf(huesped.getTelefono()));
        stmt.setString(4, huesped.getDireccion());
        stmt.setString(5, huesped. getUsuario());
        stmt.setString(6, huesped.getContrasena());
        stmt.setString(7, huesped. getEstado());
        stmt.setBoolean(8, huesped.getEsTitular());
        stmt.setInt(9, huesped.getDNI());

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Huesped"+huesped.getNombre()+" actualizado exitosamente!");
        } else {
            System.out.println("No se encontró un huesped con ese DNI.");
        }

    } catch (SQLException e) {
        System.out.println("Error al actualizar el huesped: " + e.getMessage());
    }
}


    @Override
    public void eliminar(int dni) {
        String sql = "DELETE FROM huespedes WHERE DNI = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
        
            stmt.setInt(1, dni);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Huesped eliminado exitosamente!");
            } else {
                System.out.println("No se encontró un huesped con ese DNI.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el huesped: " + e.getMessage());
        }
    }
    
public void crearHuespedes(List<Huesped> huespedes) {
    String sql = "INSERT INTO huespedes (DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, EsTitular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        for (Huesped huesped : huespedes) {
            stmt.setInt(1, huesped.getDNI());
            stmt.setString(2, huesped.getNombre());
            stmt.setString(3, huesped.getApellido());
            stmt.setString(4, String.valueOf(huesped.getTelefono())); // Convertir telefono a String si es int en el objeto
            stmt.setString(5, huesped.getDireccion());
            stmt.setString(6, huesped.getUsuario());
            stmt.setString(7, huesped.getContrasena());
            stmt.setString(8, huesped.getEstado());
            stmt.setBoolean(9, huesped.getEsTitular());

            stmt.addBatch(); // Agregar a la tanda de inserciones
        }

        int[] rowsInserted = stmt.executeBatch(); // Ejecutar la tanda de inserciones
        System.out.println(rowsInserted.length + " huespedes creados exitosamente!");

    } catch (SQLException e) {
        System.out.println("Error al crear los huespedes: " + e.getMessage());
    }
}

    
}




   
    

    


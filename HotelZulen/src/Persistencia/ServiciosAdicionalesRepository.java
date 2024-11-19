/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import modelo.ServiciosAdicionales;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suyco
 */
public class ServiciosAdicionalesRepository implements IRepository<ServiciosAdicionales> {

    @Override
    public void crear(ServiciosAdicionales objeto) {
        String sql = "INSERT INTO servicios_adicionales (NombreServicio, Costo,Estado) VALUES (?, ?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, objeto.getConcepto()); // Suponiendo que 'concepto' se usa como 'NombreServicio'
            stmt.setDouble(2, objeto.getCosto());
            stmt.setString(3, objeto.getEstado());
            stmt.executeUpdate();
            System.out.println("Servicio adicional creado con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServiciosAdicionales obtener(int id) {
        String sql = "SELECT * FROM servicios_adicionales WHERE idSERVICIOS_UNICO = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ServiciosAdicionales(
                    rs.getInt("idSERVICIOS_UNICO"),
                    rs.getString("NombreServicio"),
                    rs.getDouble("Costo"),
                    rs.getString("Estado") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public void actualizar(ServiciosAdicionales objeto) {
        String sql = "UPDATE servicios_adicionales SET NombreServicio = ?, Costo = ?,Estado=? WHERE idSERVICIOS_UNICO = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, objeto.getConcepto());
            stmt.setDouble(2, objeto.getCosto());
            stmt.setString(3, objeto.getEstado());
            stmt.setInt(4, objeto.getId());
            stmt.executeUpdate();
            System.out.println("Servicio adicional actualizado con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM servicios_adicionales WHERE idSERVICIOS_UNICO = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Servicio adicional eliminado con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ServiciosAdicionales obtenerxString(String servicio) {
        String sql = "SELECT idSERVICIOS_UNICO, NombreServicio, Costo, Estado FROM servicios_adicionales WHERE NombreServicio = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, servicio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ServiciosAdicionales(
                    rs.getInt("idSERVICIOS_UNICO"),
                    rs.getString("NombreServicio"),
                    rs.getDouble("Costo"), 
                    rs.getString("Estado") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; 
    }
    
    public List<ServiciosAdicionales> obtenerServicios (){
        List<ServiciosAdicionales> listaServicios = new ArrayList<>();
        String sql = "SELECT * FROM servicios_adicionales";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareCall(sql);
             ResultSet resultSet = stmt.executeQuery()){
            
            while(resultSet.next()){
                ServiciosAdicionales servicio = new ServiciosAdicionales(resultSet.getInt("idSERVICIOS_UNICO"),
                                                                        resultSet.getString("NombreServicio"), 
                                                                        resultSet.getInt("Costo"), 
                                                                        resultSet.getString("Estado"));
                listaServicios.add(servicio);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener los servicios adicionales: " + e.getMessage());
        }
        return listaServicios;
    }
}

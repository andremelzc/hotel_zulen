/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import modelo.Consumible;

/**
 *
 * @author PC
 */
public class ConsumibleRepository implements IRepository<Consumible>{

    @Override
    public void crear(Consumible objeto) {
        String sql = "INSERT INTO consumible (NombreConsumible, Precio) VALUES (?, ?)";

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            // Configura los parámetros de la consulta
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setDouble(2, objeto.getPrecio());

            // Ejecuta la consulta
            int filasInsertadas = preparedStatement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Consumible creado exitosamente.");
            } else {
                System.err.println("No se pudo crear el consumible.");
            }

        } catch (Exception e) {
            System.err.println("Error al crear el consumible: " + e.getMessage());
            e.printStackTrace();
        }
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
        String sql = "UPDATE consumible SET NombreConsumible = ?, Precio = ? WHERE idCONSUMIBLE = ?";

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            // Configura los parámetros de la consulta
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setDouble(2, objeto.getPrecio());
            preparedStatement.setInt(3, objeto.getId());

            // Ejecuta la consulta
            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Consumible actualizado exitosamente.");
            } else {
                System.err.println("No se encontró el consumible para actualizar.");
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar el consumible: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void MostrarConsumiblesEnComboBox(JComboBox<String> jcombo) {
        String sql = "SELECT NombreConsumible FROM consumible";

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {


            // Itera sobre los resultados y añade cada elemento al JComboBox
            while (resultSet.next()) {
                String nombreConsumible = resultSet.getString("NombreConsumible");
                jcombo.addItem(nombreConsumible); // Añade el nombre al JComboBox
            }

        } catch (Exception e) {
            System.err.println("Error al cargar los consumibles: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Consumible obtenerXNombre(String nombre) {
        // Consulta SQL para buscar el consumible por su nombre
        String sql = "SELECT * FROM consumible WHERE NombreConsumible = ?";

        
        Consumible consumible = null;

        try (Connection conexion = DatabaseConnection.getConnection(); // Obtener conexión
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            // Establecer el parámetro en la consulta SQL
            stmt.setString(1, nombre);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar si hay resultados
            if (rs.next()) {
                // Crear un objeto Consumible con los datos obtenidos
                int id = rs.getInt("idCONSUMIBLE");
                String nombreConsumible = rs.getString("NombreConsumible");
                double precio = rs.getDouble("Precio");
                
                consumible = new Consumible(id, nombreConsumible, (float)precio);
            }

        } catch (SQLException e) {
            // Manejo de excepciones
            System.err.println("Error al buscar el consumible por nombre: " + e.getMessage());
        }

        // Devolver el objeto Consumible encontrado (o null si no se encontró)
        return consumible;
        }
    }

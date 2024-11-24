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

/**
 *
 * @author PC
 */
public class ComboRepository implements IRepository<Combo> {

    @Override
    public void crear(Combo objeto){
        
    }
    public int crearCombo(String tipoComida, String descripcion) {
        String sql = "INSERT INTO combo (TipoComida, Descripcion) VALUES (?, ?)";
        int generatedId = -1; // Valor predeterminado si falla

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, tipoComida);
            preparedStatement.setString(2, descripcion);

            // Ejecutar la consulta
            preparedStatement.executeUpdate();

            // Obtener el ID generado automáticamente
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Recupera el ID de la primera columna generada
                    System.out.println("Registro insertado correctamente con ID: " + generatedId);
                }
            }

        } catch (SQLException e) {
            // Manejo de errores de base de datos
            System.err.println("Error al insertar el registro: " + e.getMessage());
        }

        return generatedId; // Devuelve el ID generado
    }


    @Override
    public Combo obtener(int id) {
        String sql = "SELECT * FROM combo WHERE idCOMBO = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Combo(
                        rs.getInt("idCOMBO"),
                        rs.getString("TipoComida"),
                        rs.getString("Descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra, retorna null
    }

    public List<Combo> obtenerTodosCombos() {
        List<Combo> combos = new ArrayList<>();
        String sql = "SELECT * FROM combo";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Combo combo = new Combo(
                        rs.getInt("idCOMBO"),
                        rs.getString("TipoComida"),
                        rs.getString("Descripcion"));
                combos.add(combo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return combos;
    }

    @Override
    public void actualizar(Combo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public  List<Combo> obtenerListaXTipo(String tipoComida){
        List<Combo> listaCombos = new ArrayList<>();
        
        String sql = "SELECT * FROM combo WHERE TipoComida = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoComida);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Combo combo = new Combo(rs.getInt("idCOMBO"),rs.getString("TipoComida"),rs.getString("Descripcion"));
                listaCombos.add(combo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        return listaCombos;
    }
    public void modificarCombo(Combo combo) {
        String sql = "UPDATE combo " +
                     "SET TipoComida = ?, Descripcion = ? " +
                     "WHERE idCOMBO = ? " +
                     "AND (TipoComida != ? OR Descripcion != ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Establecer los parámetros de la consulta
            preparedStatement.setString(1, combo.getTipoComida());
            preparedStatement.setString(2, combo.getDescripcion());
            preparedStatement.setInt(3, combo.getId());
            preparedStatement.setString(4, combo.getTipoComida());
            preparedStatement.setString(5, combo.getDescripcion());

            // Ejecutar la consulta
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("El registro se ha actualizado correctamente.");
            } else {
                System.out.println("No se realizaron cambios, los datos eran iguales.");
            }

        } catch (SQLException e) {
            System.err.println("Error al modificar el combo: " + e.getMessage());
        }
    }

}
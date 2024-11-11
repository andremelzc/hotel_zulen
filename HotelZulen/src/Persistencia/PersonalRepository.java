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
import modelo.Personal;

/**
 *
 * @author PC
 */
public class PersonalRepository implements IRepository<Personal> {

    @Override
    public void crear(Personal personal) {
        String sql = "INSERT INTO personal (DNI, TipoPersonal, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, FechaCrea, FechaMod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            
        } catch (SQLException e) {
            System.out.println("Error al crear el personal: " + e.getMessage());
        }
    }

    @Override
    public Personal obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Personal objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Personal> obtenerTodos() {
        List<Personal> personalList = new ArrayList<>();
        String sql = "SELECT DNI, TipoPersonal, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, FechaCrea, FechaMod FROM personal";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Personal personal = new Personal(
                        rs.getInt("DNI"),
                        rs.getString("TipoPersonal"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getInt("Telefono"),
                        rs.getString("Direccion"),
                        rs.getString("Usuario"),
                        rs.getString("Contraseña"),
                        rs.getString("Estado")) {
                };

                personalList.add(personal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personalList;
    }
}

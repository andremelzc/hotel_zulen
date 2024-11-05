/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Administrador;
import modelo.Recepcionista;
import modelo.Huesped;
import modelo.Personal;
import modelo.KitchenManager;
import modelo.AmaDeLlaves;
import modelo.Personal;
import modelo.PersonalFactory;
/**
 *
 * @author Miguel Giron
 */
public class InicioSesionRepository {
   
    public Personal iniciarSesion(String usuario, String contraseña) {
        String sql = "SELECT * FROM personal WHERE usuario = ? AND contraseña = ?  ";
        
        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellidos");
                int telefono = rs.getInt("Telefono");
                String direccion = rs.getString("Direccion");
                String tipoPersonal = rs.getString("TipoPersonal");
                String estado = rs.getString("Estado"); // Asumiendo que es un string
         
                
                System.out.println("Inicio de sesion exitoso");
                // Usamos la fábrica para obtener una instancia de Personal según la función
                return PersonalFactory.crearPersona(dni, tipoPersonal, nombre, apellido, telefono, direccion, usuario, contraseña, estado);
                
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Housekeeper;
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
            // Convertir LocalDateTime a Timestamp
            Timestamp fechaPedidoSQL = Timestamp.valueOf(personal.getFechaCrea());

            // Establecemos
            stmt.setInt(1, personal.getDNI());
            stmt.setString(2, personal.getFuncion());
            stmt.setString(3, personal.getNombre());
            stmt.setString(4, personal.getApellido());
            stmt.setInt(5, personal.getTelefono());
            stmt.setString(6, personal.getDireccion());
            stmt.setString(7, personal.getUsuario());
            stmt.setString(8, personal.getContrasena());
            stmt.setString(9, personal.getEstado());
            stmt.setTimestamp(10, fechaPedidoSQL); // Cambiado a setTimestamp para Timestamp
            stmt.setTimestamp(11, fechaPedidoSQL); // Establecer FechaEnvio como NULL

            // Ejecutamos la inserción
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear el personal: " + e.getMessage());
        }
    }

    @Override
    public Personal obtener(int id) {
    String sql = "SELECT DNI, TipoPersonal, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, FechaCrea, FechaMod FROM personal WHERE DNI = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        
        // Establecemos el DNI en el PreparedStatement
        stmt.setInt(1, id);

        // Ejecutamos la consulta
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Obtenemos los datos del ResultSet y los asignamos a un objeto Personal
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
            return personal;
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el personal: " + e.getMessage());
    }
    return null;
}


    @Override
    public void actualizar(Personal personal) {
        String sql = "UPDATE personal SET TipoPersonal = ?, Nombre = ?, Apellidos = ?, Telefono = ?, Direccion = ?, Usuario = ?, Contraseña = ?, Estado = ?, FechaMod = ? WHERE DNI = ?";

        try (Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            
            Timestamp fechaModSQL = Timestamp.valueOf(LocalDateTime.now());

            
            stmt.setString(1, personal.getFuncion());
            stmt.setString(2, personal.getNombre());
            stmt.setString(3, personal.getApellido());
            stmt.setInt(4, personal.getTelefono());
            stmt.setString(5, personal.getDireccion());
            stmt.setString(6, personal.getUsuario());
            stmt.setString(7, personal.getContrasena());
            stmt.setString(8, personal.getEstado());
            stmt.setTimestamp(9, fechaModSQL); 
            stmt.setInt(10, personal.getDNI());

           
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el registro con el DNI especificado.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el personal: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE personal SET Estado = ? WHERE DNI = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Establecemos el valor del Estado y el DNI
            stmt.setString(1, "Inactivo");
            stmt.setInt(2, id);

            // Ejecutamos la actualización
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Estado cambiado a 'Inactivo' exitosamente.");
            } else {
                System.out.println("No se encontró el registro con el DNI especificado.");
            }

        } catch (SQLException e) {
            System.out.println("Error al cambiar el estado a 'Inactivo': " + e.getMessage());
        }
    }

    public List<Personal> obtenerTodos() {
        List<Personal> personalList = new ArrayList<>();
        String sql = "SELECT DNI, TipoPersonal, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, FechaCrea, FechaMod FROM personal";

        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
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
    
    public Housekeeper obtenerHouskeeper(int dni){
        String sql = "SELECT DNI, TipoPersonal, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, FechaCrea, FechaMod FROM personal WHERE DNI = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Establecemos el DNI en el PreparedStatement
            stmt.setInt(1, dni);

            // Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp FechaCreaTimestamps = rs.getTimestamp("FechaCrea");
                Timestamp FechaModTimestamps = rs.getTimestamp("FechaMod");
                LocalDateTime FechaCrea = FechaCreaTimestamps.toLocalDateTime();
                LocalDateTime FechaMod = FechaModTimestamps.toLocalDateTime();
                
                Housekeeper housekeeper = new Housekeeper(
                            rs.getInt("DNI"),
                            rs.getString("TipoPersonal"),
                            rs.getString("Nombre"),
                            rs.getString("Apellidos"),
                            rs.getInt("Telefono"),
                            rs.getString("Direccion"),
                            rs.getString("Usuario"),
                            rs.getString("Contraseña"),
                            rs.getString("Estado"),
                            FechaCrea,
                            FechaMod) {
                    };
                return housekeeper;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el housekeeper: " + e.getMessage());
        }
        return null;
    }
    public List<Housekeeper> obtenerHousekeeperActivos() {
        List<Housekeeper> lista = new ArrayList<>();
        String sql = "SELECT DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, FechaCrea, FechaMod "
                   + "FROM personal WHERE TipoPersonal = 'Housekeeper' AND Estado = 'Activo'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("Nombre");
                String apellidos = rs.getString("Apellidos");
                int telefono = rs.getInt("Telefono");
                String direccion = rs.getString("Direccion");
                String usuario = rs.getString("Usuario");
                String contrasena = rs.getString("Contraseña");
                String estado = rs.getString("Estado");
                LocalDateTime fechaCrea = rs.getTimestamp("FechaCrea").toLocalDateTime();
                LocalDateTime fechaMod = rs.getTimestamp("FechaMod").toLocalDateTime();

                Housekeeper housekeeper = new Housekeeper(dni, "Housekeeper", nombre, apellidos, telefono, direccion, usuario, contrasena, estado, fechaCrea, fechaMod);
                lista.add(housekeeper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return lista;
    }
    public List<Housekeeper> obtenerHousekeeperInactivos() {
        List<Housekeeper> lista = new ArrayList<>();
        String sql = "SELECT DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, FechaCrea, FechaMod "
                   + "FROM personal WHERE TipoPersonal = 'Housekeeper' AND Estado = 'Inactivo'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int dni = rs.getInt("DNI");
                String nombre = rs.getString("Nombre");
                String apellidos = rs.getString("Apellidos");
                int telefono = rs.getInt("Telefono");
                String direccion = rs.getString("Direccion");
                String usuario = rs.getString("Usuario");
                String contrasena = rs.getString("Contraseña");
                String estado = rs.getString("Estado");
                LocalDateTime fechaCrea = rs.getTimestamp("FechaCrea").toLocalDateTime();
                LocalDateTime fechaMod = rs.getTimestamp("FechaMod").toLocalDateTime();

                Housekeeper housekeeper = new Housekeeper(dni, "Housekeeper", nombre, apellidos, telefono, direccion, usuario, contrasena, estado, fechaCrea, fechaMod);
                lista.add(housekeeper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return lista;
    }
}

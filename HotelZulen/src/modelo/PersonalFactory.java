/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.DatabaseConnection;
import Persistencia.TipoHabitacionRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suyco
 */
public class PersonalFactory {

    public static Personal crearPersona(int dni, String funcion, String nombre, String apellido, int telefono, String direccion, String usuario, String contrasena, String estado) {
        switch (funcion) {
            case "Administrador":
                return new Administrador(dni, funcion, nombre, apellido, telefono, direccion, usuario, contrasena, estado);
            case "Recepcionista":
                return new Recepcionista(dni, funcion, nombre, apellido, telefono, direccion, usuario, contrasena, estado);
            case "Jefe de cocina":
                return new KitchenManager(dni, funcion, nombre, apellido, telefono, direccion, usuario, contrasena, estado);
            case "Ama de llaves":
                return new AmaDeLlaves(dni, nombre, apellido, telefono, direccion, usuario, contrasena, estado);
            default:
                throw new IllegalArgumentException("Tipo de persona no válido");
        }
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import modelo.ReservacionHabitacionCombo;

/**
 *
 * @author PC
 */
public class ReservacionHabitacionComboRepository implements IRepository<ReservacionHabitacionCombo>{

    @Override
    public void crear(ReservacionHabitacionCombo objeto) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReservacionHabitacionCombo obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(ReservacionHabitacionCombo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Map<Integer, Integer> obtenerCantPedidos(){
        // Crear un mapa para almacenar el idCombo y su totalCantidad de pedidos
        Map<Integer, Integer> estadisticasMap = new HashMap<>();
        
        // Consulta SQL para sumar la cantidad de pedidos (cantPedido) agrupados por id del combo (COMBO_idCOMBO)
        String query = "SELECT COMBO_idCOMBO AS idCombo, SUM(cantPedido) AS totalCantidad " +
                       "FROM reservaciones_has_habitaciones_has_combo " +
                       "GROUP BY COMBO_idCOMBO";
        
        // try-with-resources para manejar automáticamente el cierre de la conexión y recursos
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()){
            
            while(resultSet.next()){ //inicialmente resultSet no apunta a ninguna fila en particular
                int idCombo = resultSet.getInt("idCombo"); //devolvera cero si es NULL en la db 
                
                int totalCantidad = resultSet.getInt("totalCantidad");
                
                //agregar al mapa
                estadisticasMap.put(idCombo, totalCantidad);
                
            }
        }catch(SQLException e){
             e.printStackTrace();
        }
        
        return estadisticasMap;
    }
    
}

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
import java.util.PriorityQueue;
import modelo.Combo;
import modelo.Limpieza;

/**
 *
 * @author Fabrizio Mantari
 */
public class LimpiezaRepository implements IRepository<Limpieza>{

    @Override
    public void crear(Limpieza objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Limpieza obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Limpieza objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void asignarLimpiezas(){
        String query1 = "SELECT " +
               "idHabitaciones, " +
               "Estado, " +
               "CASE " +
               "    WHEN Estado = 'CheckOut' THEN 4 " +
               "    WHEN Estado = 'Ocupado' THEN 3 " +
               "    WHEN Estado IN ('Disponible', 'Reservado') THEN 2 " +
               "    ELSE 1 " +
               "END AS peso " +
               "FROM habitaciones " +
               "ORDER BY peso, Estado, idHabitaciones;";
        
        List<int[]> habitacionesConPesos = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query1)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int fila [] = new int[2];
                fila[0] = resultSet.getInt("idHabitaciones");
                fila[1] = resultSet.getInt("peso");
                habitacionesConPesos.add(fila);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        String query2 = "SELECT DNI FROM personal WHERE TipoPersonal = 'Housekeeper' AND Estado = 'Activo';";
        try(Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query2)){
            ResultSet resultSet = stmt.executeQuery();
            PriorityQueue<int[]> queue = new PriorityQueue<>();
        }catch(SQLException e){
            e.printStackTrace();
        }
            
        }
    }
}

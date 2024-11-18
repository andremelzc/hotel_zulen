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
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import modelo.Huesped;
import modelo.ReservacionHabitacionCombo;
import java.sql.CallableStatement;

import javax.swing.JTable;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



/**
 *
 * @author PC
 */
public class ReservacionHabitacionComboRepository implements IRepository<ReservacionHabitacionCombo> {

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

    public void asociarReservacionCombo(int idReservacion, int idHabitacion, Huesped huesped, int idCategoria, int idCombo, int cantidad, String estado, LocalDateTime fechaPedido) {
        String sql = "INSERT INTO reservaciones_has_habitaciones_has_combo (RESERVA_has_HAB_RESERVA_idReserva, RESERVA_has_HAB_HAB_idHabitaciones, RESERVA_has_HAB_HAB_TIPO_HAB_idCategoria, COMBO_idCOMBO,cantPedido, Estado, FechaPedido, FechaEnvio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Convertir LocalDateTime a Timestamp
            Timestamp fechaPedidoSQL = Timestamp.valueOf(fechaPedido);

            // Establecer los parámetros de la consulta SQL
            stmt.setInt(1, idReservacion);
            stmt.setInt(2, idHabitacion);
            stmt.setInt(3, idCategoria);
            stmt.setInt(4, idCombo);
            stmt.setInt(5, cantidad);
            stmt.setString(6, estado);
            stmt.setTimestamp(7, fechaPedidoSQL); // Cambiado a setTimestamp para Timestamp
            stmt.setNull(8, java.sql.Types.TIMESTAMP); // Establecer FechaEnvio como NULL

            // Añadir la instrucción al lote
            stmt.addBatch();

            // Ejecutar todas las inserciones del lote
            stmt.executeBatch();

            System.out.println("Combo asociado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al asociar el combo: " + e.getMessage());
        }
    }

    public void mostrarHabitacionComboSNoEnviados(JTable Tabla) {
        DatabaseConnection obj = new DatabaseConnection();

        // Configuramos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        Tabla.setRowSorter(ordenarTabla);

        // Agrego los títulos de las columnas
        modelo.addColumn("PedidoID");
        modelo.addColumn("HabID");
        modelo.addColumn("HabPiso");
        modelo.addColumn("HabCategoria");
        modelo.addColumn("CombID");
        modelo.addColumn("CombTipo");
        modelo.addColumn("CombDescripcion");
        modelo.addColumn("Estado");
        modelo.addColumn("FPedido");
        modelo.addColumn("FEnvio");

        Tabla.setModel(modelo);

        // Consulta SQL con espacios añadidos para evitar errores de sintaxis
        String consulta = "SELECT " +
        "    rhhc.id_Pedido AS PedidoID, " +
        "    h.idhabitaciones AS HabitacionID, " +
        "    h.Piso AS HabitacionPiso, " +
        "    h.TIPO_HAB_idCategoria AS HabitacionCategoria, " +
        "    c.idCOMBO AS ComboID, " +
        "    c.TipoComida AS ComboTipoComida, " +
        "    c.Descripcion AS ComboDescripcion, " +
        "    rhhc.Estado AS EstadoPedido, " +
        "    rhhc.FechaPedido AS FPedido, " +
        "    rhhc.FechaEnvio AS FEnvio " +
        "FROM " +
        "    reservaciones_has_habitaciones_has_combo rhhc " +
        "JOIN " +
        "    habitaciones h ON rhhc.RESERVA_has_HAB_HAB_idhabitaciones = h.idhabitaciones " +
        "JOIN " +
        "    combo c ON rhhc.COMBO_idCOMBO = c.idCOMBO " +
        "WHERE " +
        "    rhhc.Estado <> 'Enviado';";


        // Ajustamos el tamaño del arreglo 'datos' a 6 columnas
        String[] datos = new String[10];
        Statement st;

        try {
            // Obtenemos la conexión y ejecutamos la consulta
            st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);

            // Procesamos los resultados
            while (rs.next()) {
                datos[0] = rs.getString("PedidoID");
                datos[1] = rs.getString("HabitacionID");
                datos[2] = rs.getString("HabitacionPiso");
                datos[3] = rs.getString("HabitacionCategoria");
                datos[4] = rs.getString("ComboID");
                datos[5] = rs.getString("ComboTipoComida");
                datos[6] = rs.getString("ComboDescripcion");
                datos[7] = rs.getString("EstadoPedido");
                datos[8] = rs.getString("FPedido");
                datos[9] = rs.getString("FEnvio");
                
                modelo.addRow(datos);
            }

            // Actualizamos el modelo de la tabla
            Tabla.setModel(modelo);

        } catch (SQLException e) {
            System.out.println("No se pudo Mostrar la Tabla Habitacion-Combo");
            e.printStackTrace(); // Imprimir el stack trace para más detalles del error
        }
    }
    
    public void mostrarHabitacionComboSEnviados(JTable Tabla) {
        DatabaseConnection obj = new DatabaseConnection();

        // Configuramos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        Tabla.setRowSorter(ordenarTabla);

        // Agrego los títulos de las columnas
        modelo.addColumn("PedidoID");
        modelo.addColumn("HabID");
        modelo.addColumn("HabPiso");
        modelo.addColumn("HabCategoria");
        modelo.addColumn("CombID");
        modelo.addColumn("CombTipo");
        modelo.addColumn("CombDescripcion");
        modelo.addColumn("Estado");
        modelo.addColumn("FPedido");
        modelo.addColumn("FEnvio");

        Tabla.setModel(modelo);

        // Consulta SQL con espacios añadidos para evitar errores de sintaxis
        String consulta = "SELECT " +
    "    rhhc.id_Pedido AS PedidoID, " +
    "    h.idhabitaciones AS HabitacionID, " +
    "    h.Piso AS HabitacionPiso, " +
    "    h.TIPO_HAB_idCategoria AS HabitacionCategoria, " +
    "    c.idCOMBO AS ComboID, " +
    "    c.TipoComida AS ComboTipoComida, " +
    "    c.Descripcion AS ComboDescripcion, " +
    "    rhhc.Estado AS EstadoPedido, " +
    "    rhhc.FechaPedido AS FPedido, " +
    "    rhhc.FechaEnvio AS FEnvio " +
    "FROM " +
    "    reservaciones_has_habitaciones_has_combo rhhc " +
    "JOIN " +
    "    habitaciones h ON rhhc.RESERVA_has_HAB_HAB_idhabitaciones = h.idhabitaciones " +
    "JOIN " +
    "    combo c ON rhhc.COMBO_idCOMBO = c.idCOMBO " +
    "WHERE " +
    "    rhhc.Estado = 'Enviado';";

        // Ajustamos el tamaño del arreglo 'datos' a 6 columnas
        String[] datos = new String[10];
        Statement st;

        try {
            // Obtenemos la conexión y ejecutamos la consulta
            st = DatabaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);

            // Procesamos los resultados
            while (rs.next()) {
                datos[0] = rs.getString("PedidoID");
                datos[1] = rs.getString("HabitacionID");
                datos[2] = rs.getString("HabitacionPiso");
                datos[3] = rs.getString("HabitacionCategoria");
                datos[4] = rs.getString("ComboID");
                datos[5] = rs.getString("ComboTipoComida");
                datos[6] = rs.getString("ComboDescripcion");
                datos[7] = rs.getString("EstadoPedido");
                datos[8] = rs.getString("FPedido");
                datos[9] = rs.getString("FEnvio");
                
                modelo.addRow(datos);
            }

            // Actualizamos el modelo de la tabla
            Tabla.setModel(modelo);

        } catch (SQLException e) {
            System.out.println("No se pudo Mostrar la Tabla Habitacion-Combo");
            e.printStackTrace(); // Imprimir el stack trace para más detalles del error
        }
    }

    
    public void seleccionarPedido(JTable Tabla, ReservacionHabitacionCombo obj) {
        try {
            int fila = Tabla.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila >= 0) {
                // Asignar valores con validaciones para evitar errores de formato
                obj.setIdPedido(Integer.parseInt(Tabla.getValueAt(fila, 0).toString()));
                obj.setIdHabitacion(Integer.parseInt(Tabla.getValueAt(fila, 1).toString()));
                obj.setPiso(Integer.parseInt(Tabla.getValueAt(fila, 2).toString()));
                obj.setCategoria(Integer.parseInt(Tabla.getValueAt(fila, 3).toString()));
                obj.setIdCombo(Integer.parseInt(Tabla.getValueAt(fila, 4).toString()));
                obj.setNombreCombo(Tabla.getValueAt(fila, 5).toString());
                obj.setDescripcionCombo(Tabla.getValueAt(fila, 6).toString());
                obj.setEstado(Tabla.getValueAt(fila, 7).toString());
                // Utilizar el método parseDateTime con un formato personalizado para evitar errores de formato
                Object fecha = Tabla.getValueAt(fila, 8);
                if(fecha != null){
                    obj.setfPedido(Tabla.getValueAt(fila, 8).toString());
                }else{
                    obj.setfPedido("NULL");
                }
                fecha = Tabla.getValueAt(fila, 9);
                if(fecha!= null){
                    obj.setfEnvio(Tabla.getValueAt(fila, 9).toString());
                }else{
                    obj.setfEnvio("NULL");
                }
                
                
                
            } else {
                System.out.println("No se ha seleccionado ninguna fila.");
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar la fila: " + e.getMessage());
            e.printStackTrace(); // Para depuración
        }
    }

    public void modificarEstadoListoS(ReservacionHabitacionCombo obje){
        
        DatabaseConnection obj = new DatabaseConnection();
        
        String consulta = "UPDATE hotel_zulen.reservaciones_has_habitaciones_has_combo hhcc SET  hhcc.Estado = ?, hhcc.FechaEnvio = ? WHERE  hhcc.id_Pedido = ?;";
        
        try {
            CallableStatement cs = DatabaseConnection.getConnection().prepareCall(consulta);
            cs.setString(1, "Enviado");
            
            
            
            
            Timestamp fechaActual = Timestamp.valueOf(LocalDateTime.now());
            cs.setTimestamp(2, fechaActual);
        
            cs.setInt(3, obje.getIdPedido());
            
            
            cs.execute();
            System.out.println("Datos del Alumno Modificado Exitosamente");
            
        } catch (Exception e) {
            System.out.println("Datos del Alumno No se pudieron modificar, error:");
            System.out.println("Error al seleccionar la fila: " + e.getMessage());
        }
        
        
        
    }
    
}
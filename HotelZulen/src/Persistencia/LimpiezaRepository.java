/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import modelo.Combo;
import modelo.Limpieza;

/**
 *
 * @author Fabrizio Mantari
 */
public class LimpiezaRepository implements IRepository<Limpieza> {

    @Override
    public void crear(Limpieza objeto) {

        String query = "INSERT INTO hotel_zulen.Limpiezas (HABITACIONES_idHabitaciones, HABITACIONES_TIPO_HAB_idCategoria, PERSONAL_DNI, FechaLimpieza, TipoLimpieza, estadoLimpieza) VALUES (?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, objeto.getIdHabitacion());      // idHabitacion
            stmt.setInt(2, objeto.getCategoriaHab());     // categoria
            stmt.setInt(3, objeto.getPersonalDNI());      // personalDNI
            stmt.setDate(4, java.sql.Date.valueOf(objeto.getFechaLimpieza())); // fechaLimpieza
            stmt.setString(5, objeto.getTipoLimpieza());  // tipoLimpieza
            stmt.setString(6, objeto.getEstadoLimpieza());// estadoLimpieza
            stmt.executeUpdate();
            System.out.println("Limpieza asignada con exito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void asignarLimpiezas() {
        String query1 = "SELECT "
                + "idHabitaciones, "
                + "TIPO_HAB_idCategoria, "
                + "Estado, "
                + "CASE "
                + "    WHEN Estado = 'CheckOut' THEN 4 "
                + "    WHEN Estado = 'Ocupado' THEN 3 "
                + "    WHEN Estado IN ('Disponible', 'Reservado') THEN 2 "
                + "    ELSE 1 "
                + "END AS peso "
                + "FROM habitaciones "
                + "ORDER BY peso, Estado, idHabitaciones;";

        List<int[]> habitacionesConPesos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query1)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int fila[] = new int[3];
                fila[0] = resultSet.getInt("idHabitaciones");
                fila[1] = resultSet.getInt("peso");
                fila[2] = resultSet.getInt("TIPO_HAB_idCategoria");  // Añadir la categoría
                habitacionesConPesos.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query2 = "SELECT DNI FROM personal WHERE TipoPersonal = 'Housekeeper' AND Estado = 'Activo';";
        PriorityQueue<int[]> housekeepers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // Priority queue de los housekeeper (PESO,DNI)

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query2)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int DNI = resultSet.getInt("DNI");
                housekeepers.add(new int[]{0, DNI});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Limpieza> nuevosRegistros = new ArrayList<>();

        for (int[] fila : habitacionesConPesos) {

            LocalDate fechaHoy = LocalDate.now();

            int[] aux = housekeepers.poll();

            int idHabitacion = fila[0];
            int peso = fila[1];
            int idCategoria = fila[2];  // Recuperamos la categoría

            nuevosRegistros.add(new Limpieza(idHabitacion, aux[1], idCategoria, "Profunda", "asignada", fechaHoy));

            aux[0] += peso; //sumando el peso de la habitacion al peso acumulado del housekeeper                     

            housekeepers.add(aux);
        }
        
        for(Limpieza registro : nuevosRegistros){
            crear(registro);
        }
    }
}

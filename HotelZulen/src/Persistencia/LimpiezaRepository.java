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
import modelo.Housekeeper;
import modelo.Limpieza;

/**
 *
 * @author Fabrizio Mantari
 */
public class LimpiezaRepository implements IRepository<Limpieza> {

    @Override
    public void crear(Limpieza objeto) {
        int indice = 1;
        String query = "INSERT INTO hotel_zulen.Limpiezas (HABITACIONES_idHabitaciones, HABITACIONES_TIPO_HAB_idCategoria, PERSONAL_DNI, FechaLimpieza, TipoLimpieza, estadoLimpieza) VALUES (?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, objeto.getIdHabitacion());      // idHabitacion
            stmt.setInt(2, objeto.getCategoriaHab());     // categoria
            stmt.setInt(3, objeto.getPersonalDNI());      // personalDNI
            stmt.setDate(4, java.sql.Date.valueOf(objeto.getFechaLimpieza())); // fechaLimpieza
            stmt.setString(5, objeto.getTipoLimpieza());  // tipoLimpieza
            stmt.setString(6, objeto.getEstadoLimpieza());// estadoLimpieza
            stmt.executeUpdate();
            System.out.println("Limpieza asignada con exito" + indice++);
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

            String tipoLimpieza;
            switch (peso) {
                case 4:
                    tipoLimpieza = "Profunda";
                    break;
                case 3:
                    tipoLimpieza = "Intermedia";
                    break;
                case 2:
                    tipoLimpieza = "Ligera";
                    break;
                default:
                    tipoLimpieza = "Chequeo simple";
                    break;
            }
            
            nuevosRegistros.add(new Limpieza(idHabitacion, aux[1], idCategoria, tipoLimpieza, "asignada", fechaHoy));

            aux[0] += peso; //sumando el peso de la habitacion al peso acumulado del housekeeper                     

            housekeepers.add(aux);
        }

        for (Limpieza registro : nuevosRegistros) {
            crear(registro);
        }
    }
    
    public List<Limpieza> obtenerListaLimpiezasxDNI(int dni){
        List<Limpieza> limpiezas = new ArrayList<>();
        String sql = """
            SELECT HABITACIONES_idHabitaciones, HABITACIONES_TIPO_HAB_idCategoria, FechaLimpieza, TipoLimpieza, estadoLimpieza
            FROM Limpiezas
            WHERE PERSONAL_DNI = ? AND FechaLimpieza = CURDATE();
        """;

        try (Connection connection = DatabaseConnection.getConnection(); 
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Establecer el parámetro del DNI
            stmt.setInt(1, dni);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Limpieza limpieza = new Limpieza();
                    limpieza.setIdHabitacion(rs.getInt("HABITACIONES_idHabitaciones"));
                    limpieza.setCategoriaHab(rs.getInt("HABITACIONES_TIPO_HAB_idCategoria"));
                    limpieza.setFechaLimpieza(rs.getDate("FechaLimpieza").toLocalDate());
                    limpieza.setTipoLimpieza(rs.getString("TipoLimpieza"));
                    limpieza.setEstadoLimpieza(rs.getString("estadoLimpieza"));
                    limpiezas.add(limpieza);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return limpiezas;
    }
    public Limpieza obtenerLimpiezaXidHabitacion(int idHabitacion) {
    String sql = "SELECT * FROM Limpiezas WHERE HABITACIONES_idHabitaciones = ? AND FechaLimpieza = CURDATE()";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idHabitacion);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Extraer datos del ResultSet
                    int idCategoria = resultSet.getInt("HABITACIONES_TIPO_HAB_idCategoria");
                    int personalDni = resultSet.getInt("PERSONAL_DNI");
                    LocalDate fechaLimpieza = resultSet.getDate("FechaLimpieza").toLocalDate();
                    String tipoLimpieza = resultSet.getString("TipoLimpieza");
                    String estadoLimpieza = resultSet.getString("estadoLimpieza");

                    // Crear un objeto Limpieza
                    return new Limpieza(idHabitacion, personalDni, idCategoria, tipoLimpieza, estadoLimpieza, fechaLimpieza);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; 
    }
    public boolean registroHechoHoyParaHabitacion1() {
    String sql = "SELECT COUNT(*) AS cuenta FROM Limpiezas WHERE FechaLimpieza = CURDATE() AND HABITACIONES_idHabitaciones = 1";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        try (ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                int cuenta = resultSet.getInt("cuenta");
                return cuenta > 0; 
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; 
}
    public Housekeeper obtenerHousekeeperxidHabitacion(int idHabitacion) {
        String sql = "SELECT PERSONAL_DNI FROM Limpiezas " +
                     "WHERE HABITACIONES_idHabitaciones = ? AND FechaLimpieza = CURDATE()";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Establecer el parámetro para el id de la habitación
            stmt.setInt(1, idHabitacion);

            // Ejecutar la consulta
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Obtener el DNI del Housekeeper
                    int personalDni = resultSet.getInt("PERSONAL_DNI");

                    Housekeeper house = new Housekeeper();
                    return house.obtener(personalDni);
                } else {
                    // Si no hay resultados, retornar null
                    System.out.println("No se encontró un registro de limpieza para hoy.");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}


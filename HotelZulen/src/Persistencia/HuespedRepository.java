package Persistencia;


import java.util.List;
import modelo.Habitacion;
import modelo.Huesped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Suyco
 */
public class HuespedRepository implements IRepository<Huesped>{
    
    @Override
    public void crear(Huesped huesped) {
        String sql = "INSERT INTO huespedes (DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, EsTitular,FechaCrea,FechaMod) VALUES (?, ?, ?, ?, ?, ?,?,?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, huesped.getDNI());
            stmt.setString(2, huesped.getNombre());
            stmt.setString(3, huesped.getApellido());
            stmt.setString(4, String.valueOf(huesped.getTelefono())); // Convertir telefono a String si es int en el objeto
            stmt.setString(5, huesped.getDireccion());
            stmt.setString(6, huesped.getUsuario());
            stmt.setString(7, huesped.getContrasena());
            stmt.setString(8, huesped.getEstado());
            stmt.setBoolean(9, huesped.getEsTitular());
            stmt.setTimestamp(10, Timestamp.valueOf(huesped.getFechaCrea())); 
            stmt.setTimestamp(11, Timestamp.valueOf(huesped.getFechaMod() ));
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Huesped "+huesped.getNombre()+" creado exitosamente!");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al crear el huesped: " + e.getMessage());
        }
    }


    @Override
    public Huesped obtener(int dni) {
        String sql = "SELECT DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, EsTitular,FechaCrea,FechaMod FROM huespedes WHERE DNI = ?";
        Huesped huesped = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
        
            stmt.setInt(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                huesped = new Huesped();
                huesped.setDNI(rs.getInt("DNI")) ;
                huesped.setNombre(rs.getString("Nombre"));
                huesped.setApellido(rs.getString("Apellidos")) ;
                huesped.setTelefono( Integer.parseInt(rs.getString("Telefono")));
                huesped.setDireccion(rs.getString("Direccion"));
                huesped.setUsuario(rs.getString("Usuario"));
                huesped.setContrasena(rs.getString("Contraseña"));
                huesped.setEstado(rs.getString("Estado"));
                huesped.setEsTitular(rs.getBoolean("EsTitular"));
                LocalDateTime fechaCrea = rs.getObject("FechaCrea", LocalDateTime.class);
                huesped.setFechaCrea(fechaCrea);
                LocalDateTime fechaMod = rs.getObject("FechaMod", LocalDateTime.class);
                huesped.setFechaCrea(fechaMod);

                return huesped;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el huesped: " + e.getMessage());
        }
        return null;
    }

        @Override
public void actualizar(Huesped huesped) {
    String sql = "UPDATE huespedes SET Nombre = ?, Apellidos = ?, Telefono = ?, Direccion = ?, Usuario = ?, Contrasena = ?, Estado = ?, EsTitular = ? WHERE DNI = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        
        stmt.setString(1, huesped.getNombre());
        stmt.setString(2, huesped.getApellido());
        stmt.setString(3, String.valueOf(huesped.getTelefono()));
        stmt.setString(4, huesped.getDireccion());
        stmt.setString(5, huesped. getUsuario());
        stmt.setString(6, huesped.getContrasena());
        stmt.setString(7, huesped. getEstado());
        stmt.setBoolean(8, huesped.getEsTitular());
        stmt.setInt(9, huesped.getDNI());

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Huesped"+huesped.getNombre()+" actualizado exitosamente!");
        } else {
            System.out.println("No se encontró un huesped con ese DNI.");
        }

    } catch (SQLException e) {
        System.out.println("Error al actualizar el huesped: " + e.getMessage());
    }
}


    @Override
    public void eliminar(int dni) {
        String sql = "DELETE FROM huespedes WHERE DNI = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
        
            stmt.setInt(1, dni);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Huesped eliminado exitosamente!");
            } else {
                System.out.println("No se encontró un huesped con ese DNI.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el huesped: " + e.getMessage());
        }
    }
    
    public void crearHuespedes(List<Huesped> huespedes) {
        String sql = """
            INSERT INTO huespedes (DNI, Nombre, Apellidos, Telefono, Direccion, Usuario, Contraseña, Estado, EsTitular, FechaCrea, FechaMod)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                Nombre = VALUES(Nombre),
                Apellidos = VALUES(Apellidos),
                Telefono = VALUES(Telefono),
                Direccion = VALUES(Direccion),
                Usuario = VALUES(Usuario),
                Contraseña = VALUES(Contraseña),
                Estado = VALUES(Estado),
                EsTitular = VALUES(EsTitular),
                FechaMod = VALUES(FechaMod);
        """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (Huesped huesped : huespedes) {
                stmt.setInt(1, huesped.getDNI());
                stmt.setString(2, huesped.getNombre());
                stmt.setString(3, huesped.getApellido());
                stmt.setString(4, String.valueOf(huesped.getTelefono())); // Convertir telefono a String si es int en el objeto
                stmt.setString(5, huesped.getDireccion());
                stmt.setString(6, huesped.getUsuario());
                stmt.setString(7, huesped.getContrasena());
                stmt.setString(8, huesped.getEstado());
                stmt.setBoolean(9, huesped.getEsTitular());
                stmt.setTimestamp(10, Timestamp.valueOf(huesped.getFechaCrea())); 
                stmt.setTimestamp(11, Timestamp.valueOf(huesped.getFechaMod() ));


                stmt.addBatch(); // Agregar a la tanda de inserciones
            }

            int[] rowsInserted = stmt.executeBatch(); // Ejecutar la tanda de inserciones
            System.out.println(rowsInserted.length + " huespedes creados exitosamente!");

        } catch (SQLException e) {
            System.out.println("Error al crear los huespedes: " + e.getMessage());
        }
    }
    
    private int obtenerDniHuespedTitularXidReserva(int id) {
        String sql = "SELECT h.DNI " +
                     "FROM huespedes h " +
                     "JOIN reservaciones_has_huespedes rh ON rh.HUESPEDES_DNI = h.DNI " +
                     "JOIN reservaciones r ON rh.RESERVACIONES_idReservaciones = r.idReservaciones " +
                     "WHERE r.idReservaciones = ? AND h.EsTitular = 1";

        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("DNI");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return -1;  // Si no se encuentra, devolvemos -1 como valor indicativo
    }
    public Huesped obtenerHuespedTitutlarxIdReserva(int id){
        int dni= obtenerDniHuespedTitularXidReserva(id);
        return obtener(dni);
    }
}




   
    

    


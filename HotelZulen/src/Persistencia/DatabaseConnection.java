package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Suyco
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_zulen";
    private static final String USER = "root";
    private static final String PASSWORD = "Pablopalero1?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Método para probar la conexión
    public static void testConnection() {
        try (Connection connection = getConnection()) {
            System.out.println("Conexion exitosa a la base de datos!"+connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

    }
}

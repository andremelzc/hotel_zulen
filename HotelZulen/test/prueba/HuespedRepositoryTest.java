/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package prueba;

import Persistencia.DatabaseConnection;
import Persistencia.HuespedRepository;
import modelo.Huesped;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Suyco
 */
public class HuespedRepositoryTest {
    
    private HuespedRepository huespedRepository;
    private Huesped testHuesped;
    
    public HuespedRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // Crear instancia de HuespedRepository
        huespedRepository = new HuespedRepository();

        // Crear un huésped de prueba
        testHuesped = new Huesped(
            12345678, "Juan", "Pérez", 987654321,
            "Dirección", "usuario", "contraseña", "Activo", true
        );

        // Limpiar registros previos si existen
        eliminarHuesped(testHuesped.getDNI());
    }
    
    @AfterEach
    public void tearDown() {
        eliminarHuesped(testHuesped.getDNI());
    }
    
    private void eliminarHuesped(int dni) {
        String sqlPersona = "DELETE FROM persona WHERE DNI = ?";
        String sqlHuesped = "DELETE FROM huespedes WHERE Persona_DNI = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmtHuesped = connection.prepareStatement(sqlHuesped);
             PreparedStatement stmtPersona = connection.prepareStatement(sqlPersona)) {

            // Eliminar de la tabla huespedes
            stmtHuesped.setInt(1, dni);
            stmtHuesped.executeUpdate();

            // Eliminar de la tabla persona
            stmtPersona.setInt(1, dni);
            stmtPersona.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testCrearHuesped() {
     // Llamar al método que queremos probar
        huespedRepository.crear(testHuesped);

        // Verificar si el huésped fue insertado correctamente
        Huesped creado = huespedRepository.obtener(testHuesped.getDNI());
        assertNotNull(creado, "El huésped no fue encontrado en la base de datos");
        assertEquals(testHuesped.getNombre(), creado.getNombre());
        assertEquals(testHuesped.getApellido(), creado.getApellido());
        assertEquals(testHuesped.getDNI(), creado.getDNI());
        assertEquals(testHuesped.getEsTitular(), creado.getEsTitular());
     }
}

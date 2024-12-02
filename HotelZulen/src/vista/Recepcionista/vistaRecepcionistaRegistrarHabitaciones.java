/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Recepcionista;

import Persistencia.DatabaseConnection;
import Persistencia.HabitacionRepository;
import Persistencia.TipoHabitacionRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Habitacion;
import modelo.Reservacion;


/**
 *
 * @author PC
 */
public class vistaRecepcionistaRegistrarHabitaciones extends javax.swing.JPanel {

    DefaultTableModel mt = new DefaultTableModel();
    List<Habitacion> listaHabitaciones = new ArrayList<>();
    Reservacion reservacion = new Reservacion();
    int contador = 0;

    /**
     * Creates new form vistaRecepcionistaRegistrarHuespedes
     */
    public vistaRecepcionistaRegistrarHabitaciones() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        String ids[] = {"Nº Habitación", "Piso", "Tipo de Habitación"};
        mt.setColumnIdentifiers(ids);
        jTableHabitacion.setModel(mt);
        jButtonComprobarHabitacion.setEnabled(false);
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    
    
    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    private Habitacion mostrarTabla(String JFieldNumero, int tipoHabitacion, String fechaInicio, String fechaFinal) {
        StringBuilder sql = new StringBuilder("SELECT h.* FROM habitaciones h ");

        // Añadimos los JOIN de manera condicional si son necesarios
        if (tipoHabitacion != -1 || !"".equals(JFieldNumero)) {
            sql.append("""
                LEFT JOIN reservaciones_has_habitaciones rhh 
                ON h.idHabitaciones = rhh.HABITACIONES_idHabitaciones
                LEFT JOIN reservaciones r 
                ON rhh.RESERVACIONES_idReservaciones = r.idReservaciones
            """);
        }

        // Comenzamos con las condiciones de la consulta
        sql.append("WHERE 1=1 ");

        // Condición para tipoHabitacion
        if (tipoHabitacion != -1) {
            sql.append("AND h.TIPO_HAB_idCategoria = ? ");
        }
        // Condición para número de habitación ingresado en el JTextField
        if (!"".equals(JFieldNumero)) {
            sql.append("AND h.idHabitaciones = ? ");
        }

        // Condición para evitar conflictos de reservas
        if (tipoHabitacion != -1 || !"".equals(JFieldNumero)) { // Aplica si se ha seleccionado un tipo de habitación o se ha ingresado un número de habitación
            sql.append("AND (r.idReservaciones IS NULL OR (r.FechaFinal <= ? OR r.FechaInicio >= ?)) ");
        }
        
        sql.append("""
            AND NOT EXISTS (
                SELECT 1
                FROM reservaciones_has_habitaciones rhh2
                JOIN reservaciones r2
                    ON rhh2.RESERVACIONES_idReservaciones = r2.idReservaciones
                WHERE rhh2.HABITACIONES_idHabitaciones = h.idHabitaciones
                  AND r2.idReservaciones != r.idReservaciones 
                  AND (
                      (r2.FechaInicio <= ? AND r2.FechaFinal >= ?)
                  )
            )
        """);
        
        sql.append("LIMIT 1;");

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;

                // Seteo de parámetros en PreparedStatement
            if (tipoHabitacion != -1) {
                stmt.setInt(paramIndex++, tipoHabitacion);
            }

            // Si se ingresó un número de habitación en JTextField, lo añadimos
            if (!"".equals(JFieldNumero)) {
                stmt.setInt(paramIndex++, Integer.parseInt(JFieldNumero));
            }

            // Seteo de las fechas para evitar conflictos de reservas
            if (tipoHabitacion != -1 || !"".equals(JFieldNumero)) {
                stmt.setString(paramIndex++, fechaInicio);
                stmt.setString(paramIndex++, fechaFinal);
                // Parámetros de las fechas para la subconsulta de solapamientos
                stmt.setString(paramIndex++, fechaFinal);
                stmt.setString(paramIndex++, fechaInicio);
            }
            
            

            ResultSet rs = stmt.executeQuery();
            // Comprobamos si el ResultSet tiene algún registro
            if (!rs.next()) {
                // Si no hay registros, mostramos un mensaje
                JOptionPane.showMessageDialog(null, "No se encontraron habitaciones disponibles.");
                return null; 
            }

                TipoHabitacionRepository repoTipo = new TipoHabitacionRepository();
                Object filas[] = {
                    rs.getInt("idHabitaciones"),
                    rs.getString("Piso"),
                    rs.getInt("TIPO_HAB_idCategoria")
                    
                    
                };
                Habitacion habitacion = new Habitacion(rs.getInt("idHabitaciones"),
                                        repoTipo.obtener(rs.getInt("TIPO_HAB_idCategoria")),
                                        rs.getString("Piso"),
                                         rs.getString("Estado"));
                mt.addRow(filas);

            // Establecemos el modelo actualizado en la JTable
            //jTableHabitacion.setModel(mt);
            
            return habitacion;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta. Intente nuevamente.");
            return null;
        }
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelHabitacion1 = new javax.swing.JLabel();
        jLabelTipoHabitacion = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelNumHabitacion = new javax.swing.JLabel();
        jTextFieldNumHabitacion = new javax.swing.JTextField();
        jButtonComprobarHabitacion = new javax.swing.JButton();
        jButtonRegistrarHabitación = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHabitacion = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTipoHabitacion = new javax.swing.JComboBox<>();
        jLabelNombre = new javax.swing.JLabel();
        jLabelNombre2 = new javax.swing.JLabel();
        fechaHastaField = new javax.swing.JTextField();
        fechaDesdeField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHabitacion1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelHabitacion1.setText("Datos Habitacion");
        add(jLabelHabitacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 210, 30));

        jLabelTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelTipoHabitacion.setText("Tipo Habitación:");
        add(jLabelTipoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(75, 76, 73));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1200, 10));

        jLabelNumHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelNumHabitacion.setText("Nº Habitación:");
        add(jLabelNumHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, 20));

        jTextFieldNumHabitacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNumHabitacionFocusGained(evt);
            }
        });
        add(jTextFieldNumHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 240, 30));

        jButtonComprobarHabitacion.setBackground(new java.awt.Color(255, 127, 17));
        jButtonComprobarHabitacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonComprobarHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        jButtonComprobarHabitacion.setText("Asignar Habitación");
        jButtonComprobarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComprobarHabitacionActionPerformed(evt);
            }
        });
        add(jButtonComprobarHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 390, 30));

        jButtonRegistrarHabitación.setBackground(new java.awt.Color(255, 127, 17));
        jButtonRegistrarHabitación.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonRegistrarHabitación.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegistrarHabitación.setText("Guardar fecha de reserva");
        jButtonRegistrarHabitación.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarHabitaciónActionPerformed(evt);
            }
        });
        add(jButtonRegistrarHabitación, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 390, 30));

        jTableHabitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableHabitacion);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 680, 290));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        jComboBoxTipoHabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Estandar", "Doble", "Suite", "Business" }));
        add(jComboBoxTipoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 240, 30));

        jLabelNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelNombre.setText("Hasta:");
        add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabelNombre2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelNombre2.setText("Desde:");
        add(jLabelNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        fechaHastaField.setText("2024-12-20 12:00:00");
        add(fechaHastaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 320, 30));

        fechaDesdeField.setText("2024-12-02 09:30:00");
        add(fechaDesdeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 320, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonComprobarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComprobarHabitacionActionPerformed
        
        // TODO add your handling code here:
        Habitacion habitacion = new Habitacion();
        String habitacionSeleccionada = jComboBoxTipoHabitacion.getSelectedItem().toString();
        int intHabitacionSeleccionada = 0;
        switch (habitacionSeleccionada) {
            case "Estandar" ->
                intHabitacionSeleccionada = 1;
            case "Doble" ->
                intHabitacionSeleccionada = 2;
            case "Suite" ->
                intHabitacionSeleccionada = 3;
            case "Business" ->
                intHabitacionSeleccionada = 4;
            case "Ninguno" -> {
                intHabitacionSeleccionada = -1;
            }

        }

        habitacion = mostrarTabla(jTextFieldNumHabitacion.getText(), intHabitacionSeleccionada, fechaDesdeField.getText(), fechaHastaField.getText());
        if(habitacion != null){
          int idHabitacion = habitacion.getId();
            habitacion.setId(habitacion.getId() + contador);

            listaHabitaciones.add(habitacion);

            //jTextFieldNumHabitacion.setText(String.valueOf(habitacion.getId()));

            //mt.addRow(new Object[]{habitacion.getId(), habitacion.getPiso(), habitacion.getTipoHabitacion().getConcepto()});
            contador++;
            reservacion.setNumHabitaciones(contador);  
        }
        else {
            JOptionPane.showMessageDialog(null, "No se encontro habitacion.");
        }
        
    }//GEN-LAST:event_jButtonComprobarHabitacionActionPerformed

    private void jButtonRegistrarHabitaciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarHabitaciónActionPerformed
        // TODO add your handling code here:
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaHoraDesde = LocalDateTime.parse(fechaDesdeField.getText(), formatter);
        LocalDateTime fechaHoraHasta = LocalDateTime.parse(fechaHastaField.getText(), formatter);
        reservacion.setIncioHuesped(fechaHoraDesde);
        reservacion.setFinHuesped(fechaHoraHasta);
        
        reservacion.setEstado("confirmada");
        reservacion.setFechaCrea(LocalDateTime.now());
        reservacion.setCheckIn(LocalDateTime.now());
        reservacion.setCheckOut(LocalDateTime.now());
        jButtonComprobarHabitacion.setEnabled(true);

    }//GEN-LAST:event_jButtonRegistrarHabitaciónActionPerformed

    private void jTextFieldNumHabitacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNumHabitacionFocusGained
        jComboBoxTipoHabitacion.setSelectedItem("Ninguno"); 
    }//GEN-LAST:event_jTextFieldNumHabitacionFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fechaDesdeField;
    private javax.swing.JTextField fechaHastaField;
    private javax.swing.JButton jButtonComprobarHabitacion;
    private javax.swing.JButton jButtonRegistrarHabitación;
    private javax.swing.JComboBox<String> jComboBoxTipoHabitacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelHabitacion1;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombre2;
    private javax.swing.JLabel jLabelNumHabitacion;
    private javax.swing.JLabel jLabelTipoHabitacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableHabitacion;
    private javax.swing.JTextField jTextFieldNumHabitacion;
    // End of variables declaration//GEN-END:variables
}

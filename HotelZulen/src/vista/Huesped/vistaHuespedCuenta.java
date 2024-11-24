/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Huesped;

import Persistencia.DatabaseConnection;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import modelo.Huesped;
import vista.VistaDatosReserva;

/**
 *
 * @author PC
 */
public class vistaHuespedCuenta extends javax.swing.JPanel {

    private Huesped huespedActual;
    private int idReservaElegida;
    
    public vistaHuespedCuenta(Huesped huesped) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.huespedActual = huesped;
        cargarJComboBoxDeReservas();
        jTextArea1.setText("");
        jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
    }
    
    private void cargarJComboBoxDeReservas(){
        String sql = "SELECT * FROM reservaciones_has_huespedes WHERE HUESPEDES_DNI = ?";
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, huespedActual.getDNI());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                desplegableReservas.addItem(String.valueOf(rs.getInt("RESERVACIONES_idReservaciones")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarCuentaEnJText(){
        String encabezados = String.format(
            "%-15s %-10s %-10s %-15s %-20s\n",
            "Habitación", "Combo ID", "Cantidad", "Estado", "Fecha de Envío"
        );
        jTextArea1.append(encabezados);
        jTextArea1.append("------------------------------------------------------------\n");
        String sql = "SELECT  RESERVA_has_HAB_HAB_idHabitaciones, " +
                     "COMBO_idCOMBO, cantPedido, " +
                     "Estado, FechaEnvio " +
                     "FROM reservaciones_has_habitaciones_has_combo " +
                     "WHERE RESERVA_has_HAB_RESERVA_idReserva = ? " +
                     "AND Estado = 'Enviado' AND FechaEnvio IS NOT NULL";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idReservaElegida); // Establecer el parámetro de idReserva
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                int idHabitacion = rs.getInt("RESERVA_has_HAB_HAB_idHabitaciones");
                int idCombo = rs.getInt("COMBO_idCOMBO");
                int cantidad = rs.getInt("cantPedido");
                String estado = rs.getString("Estado");
                LocalDateTime fechaEnvio = rs.getTimestamp("FechaEnvio").toLocalDateTime();
                 String fila = String.format(
                    "%-15d %-10d %-10d %-15s %-20s\n",
                    idHabitacion, idCombo, cantidad, estado, fechaEnvio.toString()
                );
                jTextArea1.append(fila);
                
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        desplegableReservas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(221, 221, 221));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Reservacion");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Mostrar Datos de la reserva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(86, 86, 86)
                            .addComponent(desplegableReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(263, 263, 263)
                            .addComponent(jButton1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(desplegableReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(20, 20, 20)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextArea1.setText("");
        String reservaSeleccionada = (String) desplegableReservas.getSelectedItem();
         idReservaElegida = Integer.parseInt(reservaSeleccionada);
         cargarCuentaEnJText();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       VistaDatosReserva vistaDatos = new VistaDatosReserva(idReservaElegida);
       vistaDatos.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> desplegableReservas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Recepcionista;

import Persistencia.DatabaseConnection;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class vistaRecepcionistaFacturar extends javax.swing.JPanel {

    private int huespedDNI;
    public vistaRecepcionistaFacturar() {
        FlatNightOwlIJTheme.setup();
        initComponents();
        jTextArea1.setText("");
        jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void cargarJComboBoxDeReservas(){
        String sql = "SELECT * FROM reservaciones_has_huespedes WHERE HUESPEDES_DNI = ?";
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, huespedDNI);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                desplegableReservas.addItem(String.valueOf(rs.getInt("RESERVACIONES_idReservaciones")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void cargarCuentaEnJText(int idReservaElegida){
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
        DNIhuesped = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        desplegableReservas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 520));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("DNI del huesped");

        DNIhuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DNIhuespedActionPerformed(evt);
            }
        });

        jLabel2.setText("Reserva");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("FACTURAR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(desplegableReservas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(DNIhuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(158, 158, 158)
                            .addComponent(jButton1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(DNIhuesped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(desplegableReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void DNIhuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DNIhuespedActionPerformed
        huespedDNI = Integer.parseInt(DNIhuesped.getText());
        cargarJComboBoxDeReservas();
    }//GEN-LAST:event_DNIhuespedActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jTextArea1.setText("");
       String reservaSeleccionada = (String) desplegableReservas.getSelectedItem();
       int idReservaElegida = Integer.parseInt(reservaSeleccionada);
       cargarCuentaEnJText(idReservaElegida);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DNIhuesped;
    private javax.swing.JComboBox<String> desplegableReservas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

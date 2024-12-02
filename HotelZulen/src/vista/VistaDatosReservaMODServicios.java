/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import Persistencia.DatabaseConnection;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ServiciosAdicionales;

/**
 *
 * @author Suyco
 */
public class VistaDatosReservaMODServicios extends javax.swing.JFrame {

    DefaultTableModel modeloServicio;
    
    public VistaDatosReservaMODServicios(List<ServiciosAdicionales> listaServicios) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modeloServicio = (DefaultTableModel) TablaServicios.getModel();
        mostrarServiciosEnComboBox();
        mostrarDatos(listaServicios);
        
    }
    @Override
    public void dispose() {
        super.dispose();
        System.out.println("VistaDatosReservaMODServicios cerrado.");
    }
    private void mostrarDatos(List<ServiciosAdicionales> listaServicios){
        for(ServiciosAdicionales serv : listaServicios){
            Object[] fila = { 
                serv.getId(),
                serv.getConcepto(),
                serv.getCosto()
            };
            modeloServicio.addRow(fila);
        }
        TablaServicios.setModel(modeloServicio);
    }
    private void mostrarServiciosEnComboBox (){
        String sql = "SELECT NombreServicio FROM servicios_adicionales";
        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()){
            
            while(resultSet.next()){
                jComboBox1.addItem(resultSet.getString("NombreServicio"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener los servicios adicionales: " + e.getMessage());
        }
    }

     private void RegistrarServicioEnTabla(String servicio){
        String sql = "SELECT idSERVICIOS_UNICO, NombreServicio, Costo FROM servicios_adicionales WHERE NombreServicio = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servicio);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Object fila[] = {
                    rs.getInt("idSERVICIOS_UNICO"),
                    rs.getString("NombreServicio"),
                    rs.getDouble("Costo")
                    };
                modeloServicio.addRow(fila);
            }
            TablaServicios.setModel(modeloServicio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaServicios = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Servicio");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 33, -1, -1));

        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 149, 30));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 28, -1, 30));

        jButton2.setBackground(new java.awt.Color(255, 127, 17));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 28, -1, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        TablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idServicio", "Nombre del servicio", "Precio"
            }
        ));
        jScrollPane1.setViewportView(TablaServicios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 416, 174));

        jButton3.setBackground(new java.awt.Color(255, 127, 17));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Ejecutar cambios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = TablaServicios.getSelectedRow();
        modeloServicio.removeRow(fila);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RegistrarServicioEnTabla((String)jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null,
                            "Los servicios han sido registradas correctamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton3ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaServicios;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

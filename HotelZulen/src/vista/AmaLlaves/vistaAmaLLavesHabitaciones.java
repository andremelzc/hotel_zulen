/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.AmaLlaves;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.AmaDeLlaves;
import modelo.Habitacion;
import vista.Admin.vistaDatosLimpieza_Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Persistencia.DatabaseConnection;

public class vistaAmaLLavesHabitaciones extends javax.swing.JPanel {

    DefaultTableModel modelo;
    private AmaDeLlaves amaLlaves;
    
    public vistaAmaLLavesHabitaciones(AmaDeLlaves amaLlaves) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.amaLlaves = amaLlaves;
        modelo = (DefaultTableModel) Tabla.getModel();
        Object[] Habitaciones = new Object[3];
    }
    public int seleccionarPedido(JTable Tabla) {
        try {
            int fila = Tabla.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila >= 0) {
                int id = Integer.parseInt(Tabla.getValueAt(fila, 0).toString());
                System.out.println("id seleccionada: "+id);
                NumHab.setText(Tabla.getValueAt(fila, 0).toString());
                pisoField.setText(Tabla.getValueAt(fila, 2).toString());
                tipoField.setText(Tabla.getValueAt(fila, 1).toString());
                estadoField.setText(Tabla.getValueAt(fila, 3).toString());
                
               return id;
                
            } else {
                System.out.println("No se ha seleccionado ninguna fila.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar la fila: " + e.getMessage());
            e.printStackTrace(); 
            return -1;
        }
    }
    public void mostrarTabla (String Piso,String Tipo){
        modelo.setRowCount(0);

       
        StringBuilder sql = new StringBuilder("SELECT * FROM habitaciones WHERE 1=1");

        // Construcción dinámica de la consulta
        if (!"Ninguno".equalsIgnoreCase(Piso)) {
            sql.append(" AND Piso = ?");
        }
        if (!"Ninguno".equalsIgnoreCase(Tipo)) {
            sql.append(" AND TIPO_HAB_idCategoria = (SELECT idCategoria FROM tipo_hab WHERE Concepto = ?)");
        }

      
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            // Seteo de parámetros en PreparedStatement
            if (!"ninguno".equalsIgnoreCase(Piso)) {
                stmt.setInt(paramIndex++, Integer.parseInt(Piso));
            }
            if (!"ninguno".equalsIgnoreCase(Tipo)) {
                stmt.setString(paramIndex++, Tipo);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Object filas [] = {
                    rs.getInt("idHabitaciones"),
                        rs.getInt("TIPO_HAB_idCategoria"),
                        rs.getString("Piso"),
                        rs.getString("Estado")
                };
                modelo.addRow(filas);
            }
            Tabla.setModel(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void MostrarTabla(Habitacion habitacion){
        modelo.setRowCount(0);
        Object[] fila = {
                habitacion.getId(),
                habitacion.getTipoHabitacion().getConcepto(),
                habitacion.getPiso(),
                habitacion.getEstado()
            };
        modelo.addRow(fila);
        Tabla.setModel(modelo);
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tipoHab = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        verDatos = new javax.swing.JButton();
        filtro = new javax.swing.JButton();
        JPiso = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pisoField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tipoField = new javax.swing.JTextField();
        Reservación = new javax.swing.JLabel();
        estadoField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        NumHab = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 520));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Piso: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tipo: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        tipoHab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Estandard", "Doble", "Suite", "Business" }));
        jPanel1.add(tipoHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 200, 30));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# de habitacion", "Tipo de habitacion", "Piso", "Estado de la habitacion"
            }
        ));
        Tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClick(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 570, 270));

        verDatos.setBackground(new java.awt.Color(255, 127, 17));
        verDatos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        verDatos.setForeground(new java.awt.Color(255, 255, 255));
        verDatos.setText("Ver Housekeeper asignado");
        verDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verDatosActionPerformed(evt);
            }
        });
        jPanel1.add(verDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 260, 30));

        filtro.setBackground(new java.awt.Color(255, 127, 17));
        filtro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filtro.setForeground(new java.awt.Color(255, 255, 255));
        filtro.setText("Filtrar");
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });
        jPanel1.add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 260, 30));

        JPiso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "2", "3", "4", "5", "6", "7", " " }));
        jPanel1.add(JPiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 210, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Datos de habitación");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tipo:");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));
        jPanel2.add(pisoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 150, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Piso:");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));
        jPanel2.add(tipoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 130, 30));

        Reservación.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reservación.setForeground(new java.awt.Color(0, 0, 0));
        Reservación.setText("Estado:");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));
        jPanel2.add(estadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 360, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Número de habitacion: ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));
        jPanel2.add(NumHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 212, 30));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 210, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 430, 10));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 430, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 510, 240));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 560, 10));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 560, 10));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1280, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed

        mostrarTabla((String)JPiso.getSelectedItem(), (String) tipoHab.getSelectedItem());
        
    }//GEN-LAST:event_filtroActionPerformed

    private void verDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verDatosActionPerformed
        int idHabitacione = seleccionarPedido(Tabla);
        vistaDatosLimpieza_Habitacion vistaHabLimpie = new vistaDatosLimpieza_Habitacion(idHabitacione);
        vistaHabLimpie.setVisible(true);
    }//GEN-LAST:event_verDatosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Habitacion hab = new Habitacion();
        hab = hab.obtenerHabxId(Integer.parseInt(NumHab.getText()));
        MostrarTabla(hab);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void MouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClick
        int id = seleccionarPedido(Tabla);
        
    }//GEN-LAST:event_MouseClick


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JPiso;
    private javax.swing.JTextField NumHab;
    private javax.swing.JLabel Reservación;
    private javax.swing.JTable Tabla;
    private javax.swing.JTextField estadoField;
    private javax.swing.JButton filtro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField pisoField;
    private javax.swing.JTextField tipoField;
    private javax.swing.JComboBox<String> tipoHab;
    private javax.swing.JButton verDatos;
    // End of variables declaration//GEN-END:variables
}

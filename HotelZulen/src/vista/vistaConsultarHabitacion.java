/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import Persistencia.DatabaseConnection;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Habitacion;
import modelo.Reservacion;

import vista.Admin.vistaDatosLimpieza_Habitacion;

/**
 *
 * @author PC
 */
public class vistaConsultarHabitacion extends javax.swing.JPanel {

    DefaultTableModel modelo; 
    int idHabitacione;
    String estado;
    
    public vistaConsultarHabitacion() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        modelo = (DefaultTableModel) habitacionesTable.getModel();
        Object[] Habitaciones = new Object[3];
        btnVerReserva.setEnabled(false);
        configurar(habitacionesTable);
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
            habitacionesTable.setModel(modelo);
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
        habitacionesTable.setModel(modelo);
    }
    
    private void configurar(JTable tabla) {

        tabla.getSelectionModel().addListSelectionListener(event -> {

            if (!event.getValueIsAdjusting()) {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    idHabitacione = Integer.parseInt(habitacionesTable.getValueAt(filaSeleccionada, 0).toString());
                    estado =  habitacionesTable.getValueAt(filaSeleccionada, 3).toString();
                    if("Disponible".equalsIgnoreCase(estado)){
                        btnVerReserva.setEnabled(false);
                    }
                    else{
                        btnVerReserva.setEnabled(true);
                    }
                } 
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pisoField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tipoField = new javax.swing.JTextField();
        Reservación = new javax.swing.JLabel();
        estadoField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        numHab = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        cancelarBoton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        JPiso = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        tipoHab = new javax.swing.JComboBox<>();
        filtro = new javax.swing.JButton();
        btnVerReserva = new javax.swing.JButton();
        btnVerLimpieza = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        habitacionesTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 30, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Datos de habitación");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tipo");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        jPanel1.add(pisoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 370, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Piso");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));
        jPanel1.add(tipoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 370, 30));

        Reservación.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Reservación.setForeground(new java.awt.Color(0, 0, 0));
        Reservación.setText("Estado");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));
        jPanel1.add(estadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 370, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Numero de habitacion");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 15, -1, -1));
        jPanel1.add(numHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 220, 30));

        jButton2.setBackground(new java.awt.Color(239, 35, 60));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 210, 30));

        cancelarBoton.setBackground(new java.awt.Color(239, 35, 60));
        cancelarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelarBoton.setText("Cancelar");
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 200, 30));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 460, 13));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, 490, 260));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Piso");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        JPiso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "2", "3", "4", "5", "6", "7", " " }));
        add(JPiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 160, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tipo");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        tipoHab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Estandar", "Doble", "Suite", "Business" }));
        add(tipoHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 150, 30));

        filtro.setBackground(new java.awt.Color(255, 127, 17));
        filtro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filtro.setForeground(new java.awt.Color(255, 255, 255));
        filtro.setText("Filtrar");
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });
        add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 130, 30));

        btnVerReserva.setBackground(new java.awt.Color(255, 127, 17));
        btnVerReserva.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerReserva.setForeground(new java.awt.Color(255, 255, 255));
        btnVerReserva.setText("Ver Reserva");
        btnVerReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReservaActionPerformed(evt);
            }
        });
        add(btnVerReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 290, 30));

        btnVerLimpieza.setBackground(new java.awt.Color(255, 127, 17));
        btnVerLimpieza.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerLimpieza.setForeground(new java.awt.Color(255, 255, 255));
        btnVerLimpieza.setText("Ver Limpieza");
        btnVerLimpieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLimpiezaActionPerformed(evt);
            }
        });
        add(btnVerLimpieza, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 290, 30));

        habitacionesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tipo", "Piso", "Estado"
            }
        ));
        habitacionesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                habitacionesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(habitacionesTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 610, 290));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 600, 13));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 600, 13));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 460, 13));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Habitacion hab = new Habitacion();
        hab = hab.obtenerHabxId(Integer.parseInt(numHab.getText()));
        MostrarTabla(hab);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cancelarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBotonActionPerformed
        // TODO add your handling code here:
        habitacionesTable.clearSelection();
        tipoField.setText("");
        pisoField.setText("");
        estadoField.setText("");
    }//GEN-LAST:event_cancelarBotonActionPerformed

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
        mostrarTabla((String) JPiso.getSelectedItem(),(String) tipoHab.getSelectedItem());
    }//GEN-LAST:event_filtroActionPerformed

    private void btnVerReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReservaActionPerformed
        Reservacion reserva = new Reservacion();
        int id = reserva.obteneridReservaXidHabitacion(idHabitacione);
        if(id !=0 ){
            System.out.println("id seleccionado: "+id);
        }else{
            System.out.println("No se encuentra reserva asociada");
        }

        VistaDatosReserva vistaDatos = new VistaDatosReserva (id);
        vistaDatos.setVisible(true);
    }//GEN-LAST:event_btnVerReservaActionPerformed

    private void habitacionesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_habitacionesTableMouseClicked
        // TODO add your handling code here:
        int fila = habitacionesTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) habitacionesTable.getModel();
        numHab.setText(model.getValueAt(fila, 0).toString());
        tipoField.setText(model.getValueAt(fila, 1).toString());
        pisoField.setText(model.getValueAt(fila, 2).toString());
        estadoField.setText(model.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_habitacionesTableMouseClicked

    private void btnVerLimpiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLimpiezaActionPerformed
        vistaDatosLimpieza_Habitacion vistaHabLimpie = new vistaDatosLimpieza_Habitacion(idHabitacione);
       vistaHabLimpie.setVisible(true);
    }//GEN-LAST:event_btnVerLimpiezaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JPiso;
    private javax.swing.JLabel Reservación;
    private javax.swing.JButton btnVerLimpieza;
    private javax.swing.JButton btnVerReserva;
    private javax.swing.JButton cancelarBoton;
    private javax.swing.JTextField estadoField;
    private javax.swing.JButton filtro;
    private javax.swing.JTable habitacionesTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField numHab;
    private javax.swing.JTextField pisoField;
    private javax.swing.JTextField tipoField;
    private javax.swing.JComboBox<String> tipoHab;
    // End of variables declaration//GEN-END:variables
}

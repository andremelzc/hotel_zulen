/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Admin;

import Persistencia.HabitacionRepository;
import Persistencia.TipoHabitacionRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Habitacion;
import modelo.Reservacion;
import modelo.TipoDeHabitacion;
import vista.VistaDatosReserva_RA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Persistencia.DatabaseConnection;

/**
 *
 * @author PC
 */
public class vistaAdministradorHabitaciones extends javax.swing.JPanel {

    DefaultTableModel modelo;
    int idHabitacione;
    String estado;

    public vistaAdministradorHabitaciones() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        modelo = (DefaultTableModel) habitacionesTable.getModel();
        Object[] Habitaciones = new Object[3];
        btnVerReserva.setEnabled(false);
        configurar(habitacionesTable);

    }

    public void mostrarTabla(String Piso, String Tipo) {
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
            if (!"Ninguno".equalsIgnoreCase(Piso)) {
                stmt.setInt(paramIndex++, Integer.parseInt(Piso));
            }
            if (!"Ninguno".equalsIgnoreCase(Tipo)) {
                stmt.setString(paramIndex++, Tipo);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Object filas[] = {
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

    private void MostrarTabla(Habitacion habitacion) {
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
                    estado = habitacionesTable.getValueAt(filaSeleccionada, 3).toString();
                    if ("Ocupado".equalsIgnoreCase(estado)) {
                        btnVerReserva.setEnabled(true);
                    } else {
                        btnVerReserva.setEnabled(false);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        habitacionesTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pisoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tipoField = new javax.swing.JTextField();
        Reservación = new javax.swing.JLabel();
        estadoField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        numHab = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        registrarBoton = new javax.swing.JButton();
        cancelarBoton = new javax.swing.JButton();
        modificarBoton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        JPiso = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tipoHab = new javax.swing.JComboBox<>();
        filtro = new javax.swing.JButton();
        btnVerReserva = new javax.swing.JButton();
        btnVerLimpieza = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 610, 320));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Datos de habitación");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tipo:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 88, -1, -1));
        jPanel1.add(pisoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 196, 437, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Piso:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 162, -1, -1));
        jPanel1.add(tipoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 116, 437, 40));

        Reservación.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reservación.setForeground(new java.awt.Color(0, 0, 0));
        Reservación.setText("Estado:");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 248, -1, -1));
        jPanel1.add(estadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 276, 437, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Numero de habitacion: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 15, -1, -1));

        numHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numHabActionPerformed(evt);
            }
        });
        jPanel1.add(numHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 43, 212, 39));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 44, 190, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 490, 330));

        registrarBoton.setBackground(new java.awt.Color(255, 127, 17));
        registrarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        registrarBoton.setForeground(new java.awt.Color(255, 255, 255));
        registrarBoton.setText("Registrar");
        registrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBotonActionPerformed(evt);
            }
        });
        add(registrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, 430, 30));

        cancelarBoton.setBackground(new java.awt.Color(255, 127, 17));
        cancelarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelarBoton.setForeground(new java.awt.Color(255, 255, 255));
        cancelarBoton.setText("Cancelar");
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });
        add(cancelarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 400, 200, 30));

        modificarBoton.setBackground(new java.awt.Color(255, 127, 17));
        modificarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        modificarBoton.setForeground(new java.awt.Color(255, 255, 255));
        modificarBoton.setText("Modificar");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });
        add(modificarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 400, 220, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Piso: ");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        JPiso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "2", "3", "4", "5", "6", "7", " " }));
        add(JPiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tipo: ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        tipoHab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Estandard", "Doble", "Suite", "Business" }));
        add(tipoHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        filtro.setBackground(new java.awt.Color(255, 127, 17));
        filtro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filtro.setForeground(new java.awt.Color(255, 255, 255));
        filtro.setText("Filtrar");
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });
        add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, 30));

        btnVerReserva.setBackground(new java.awt.Color(255, 127, 17));
        btnVerReserva.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerReserva.setForeground(new java.awt.Color(255, 255, 255));
        btnVerReserva.setText("Ver Reserva");
        btnVerReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReservaActionPerformed(evt);
            }
        });
        add(btnVerReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 280, 30));

        btnVerLimpieza.setBackground(new java.awt.Color(255, 127, 17));
        btnVerLimpieza.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerLimpieza.setForeground(new java.awt.Color(255, 255, 255));
        btnVerLimpieza.setText("Ver Limpieza");
        btnVerLimpieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLimpiezaActionPerformed(evt);
            }
        });
        add(btnVerLimpieza, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 290, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void registrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBotonActionPerformed

        JOptionPane.showMessageDialog(null, "No es posible registrar más habitaciones");
    }//GEN-LAST:event_registrarBotonActionPerformed

    private void cancelarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBotonActionPerformed
        // TODO add your handling code here:
        habitacionesTable.clearSelection();
        tipoField.setText("");
        pisoField.setText("");
        estadoField.setText("");
    }//GEN-LAST:event_cancelarBotonActionPerformed

    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
        // TODO add your handling code here:
        int fila = habitacionesTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) habitacionesTable.getModel();

        int id = Integer.parseInt(model.getValueAt(fila, 0).toString());

        String tipo = tipoField.getText();
        TipoHabitacionRepository thr = new TipoHabitacionRepository();
        TipoDeHabitacion tipohabitacion = thr.obtenerPorConcepto(tipo);

        String piso = pisoField.getText();
        String estado = estadoField.getText();

        HabitacionRepository habirepo = new HabitacionRepository();
        Habitacion habitacion = new Habitacion(id, tipohabitacion, piso, estado);
        habirepo.actualizar(habitacion);

        model.setValueAt(tipoField.getText(), fila, 1);
        model.setValueAt(pisoField.getText(), fila, 2);
        model.setValueAt(estadoField.getText(), fila, 3);
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void habitacionesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_habitacionesTableMouseClicked
        // TODO add your handling code here:
        int fila = habitacionesTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) habitacionesTable.getModel();
        numHab.setText(model.getValueAt(fila, 0).toString());
        tipoField.setText(model.getValueAt(fila, 1).toString());
        pisoField.setText(model.getValueAt(fila, 2).toString());
        estadoField.setText(model.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_habitacionesTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Habitacion hab = new Habitacion();
        hab = hab.obtenerHabxId(Integer.parseInt(numHab.getText()));
        MostrarTabla(hab);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed

        mostrarTabla((String) JPiso.getSelectedItem(), (String) tipoHab.getSelectedItem());

    }//GEN-LAST:event_filtroActionPerformed

    private void btnVerReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReservaActionPerformed
        Reservacion reserva = new Reservacion();
        int id = reserva.obteneridReservaXidHabitacion(idHabitacione);
        if (id != 0) {
            System.out.println("id seleccionado: " + id);
            VistaDatosReserva_RA vistaDatos = new VistaDatosReserva_RA(id);
            vistaDatos.setVisible(true);
        } else {
            System.out.println("No se encuentra reserva asociada");
        }

    }//GEN-LAST:event_btnVerReservaActionPerformed

    private void btnVerLimpiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLimpiezaActionPerformed
        vistaDatosLimpieza_Habitacion vistaHabLimpie = new vistaDatosLimpieza_Habitacion(idHabitacione);
        vistaHabLimpie.setVisible(true);
    }//GEN-LAST:event_btnVerLimpiezaActionPerformed

    private void numHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numHabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numHabActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JPiso;
    private javax.swing.JLabel Reservación;
    private javax.swing.JButton btnVerLimpieza;
    private javax.swing.JButton btnVerReserva;
    private javax.swing.JButton cancelarBoton;
    private javax.swing.JTextField estadoField;
    private javax.swing.JButton filtro;
    private javax.swing.JTable habitacionesTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBoton;
    private javax.swing.JTextField numHab;
    private javax.swing.JTextField pisoField;
    private javax.swing.JButton registrarBoton;
    private javax.swing.JTextField tipoField;
    private javax.swing.JComboBox<String> tipoHab;
    // End of variables declaration//GEN-END:variables
}

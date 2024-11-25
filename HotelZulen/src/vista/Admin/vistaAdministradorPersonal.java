/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Admin;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Persistencia.*;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.time.LocalDateTime;
import modelo.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class vistaAdministradorPersonal extends javax.swing.JPanel {

    DefaultTableModel mt = new DefaultTableModel();

    public vistaAdministradorPersonal() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        String ids[] = {"DNI", "Cargo", "Nombres", "Apellidos", "Telefono", "Direccion", "Estado", "Usuario", "Contraseña"};
        mt.setColumnIdentifiers(ids);

        resetearTabla();

    }

   private void resetearTabla() {
        // Limpiar todas las filas de la tabla
        mt.setRowCount(0);

        // Obtener los datos actualizados de la base de datos o de alguna otra fuente
        List<Personal> personal = new ArrayList<>();
        PersonalRepository personalRepository = new PersonalRepository();
        personal = personalRepository.obtenerTodos();

        // Volver a agregar los datos a la tabla
        for (Personal personal1 : personal) {
            Object[] fila = {
                personal1.getDNI(),
                personal1.getFuncion(),
                personal1.getNombre(),
                personal1.getApellido(),
                personal1.getTelefono(),
                personal1.getDireccion(),
                personal1.getEstado(),
                personal1.getUsuario(),
                "*".repeat(personal1.getContrasena().length())};
            mt.addRow(fila);
        }

        // Refrescar la vista de la tabla (opcional, pero a veces ayuda a garantizar que los cambios se vean reflejados)
        personalTable.setModel(mt);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        personalTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dni = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        Nombre = new javax.swing.JLabel();
        dniField = new javax.swing.JTextField();
        Reservación = new javax.swing.JLabel();
        dni1 = new javax.swing.JLabel();
        cargoField = new javax.swing.JTextField();
        Nombre1 = new javax.swing.JLabel();
        telefonoField = new javax.swing.JTextField();
        direccin = new javax.swing.JLabel();
        direccionField = new javax.swing.JTextField();
        Nombre2 = new javax.swing.JLabel();
        estadoField = new javax.swing.JTextField();
        Nombre3 = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        Nombre4 = new javax.swing.JLabel();
        contrasenaField = new javax.swing.JPasswordField();
        apellidoField = new javax.swing.JTextField();
        registrarBoton = new javax.swing.JButton();
        cancelarBoton = new javax.swing.JButton();
        modificarBoton = new javax.swing.JButton();
        deshabilitarBoton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        personalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "Cargo", "Nombres", "Apellidos", "Telefono", "Direccion", "Estado", "Usuario", "Contraseña"
            }
        ));
        personalTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personalTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(personalTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 750, 427));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Datos de personal");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 241, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dni.setText("DNI:");
        dni.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel1.add(nombreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 200, 40));

        Nombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre.setText("Nombre:");
        Nombre.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        jPanel1.add(dniField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 200, 40));

        Reservación.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reservación.setText("Apellido:");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 90, -1));

        dni1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dni1.setText("Cargo");
        dni1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(dni1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 70, -1));
        jPanel1.add(cargoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 200, 40));

        Nombre1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre1.setText("Estado:");
        Nombre1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 80, 30));
        jPanel1.add(telefonoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 40));

        direccin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        direccin.setText("Direccion:");
        direccin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(direccin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));
        jPanel1.add(direccionField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 420, 40));

        Nombre2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre2.setText("Telefono:");
        Nombre2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        jPanel1.add(estadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 200, 40));

        Nombre3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre3.setText("Contraseña:");
        Nombre3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 130, -1));
        jPanel1.add(usuarioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 40));

        Nombre4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre4.setText("Usuario:");
        Nombre4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        contrasenaField.setText("jPasswordField1");
        jPanel1.add(contrasenaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 200, 40));
        jPanel1.add(apellidoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 200, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 460, 380));

        registrarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        registrarBoton.setText("Registrar");
        registrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBotonActionPerformed(evt);
            }
        });
        add(registrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, 110, 50));

        cancelarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelarBoton.setText("Cancelar");
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });
        add(cancelarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 450, 100, 50));

        modificarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        modificarBoton.setText("Modificar");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });
        add(modificarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, 100, 50));

        deshabilitarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deshabilitarBoton.setText("Deshabilitar");
        deshabilitarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deshabilitarBotonActionPerformed(evt);
            }
        });
        add(deshabilitarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 450, 120, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void personalTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personalTableMouseClicked
        // TODO add your handling code here:
        int fila = personalTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) personalTable.getModel();

        dniField.setText(model.getValueAt(fila, 0).toString());
        cargoField.setText(model.getValueAt(fila, 1).toString());
        nombreField.setText(model.getValueAt(fila, 2).toString());
        apellidoField.setText(model.getValueAt(fila, 3).toString());
        telefonoField.setText(model.getValueAt(fila, 4).toString());
        direccionField.setText(model.getValueAt(fila, 5).toString());
        estadoField.setText(model.getValueAt(fila, 6).toString());
        usuarioField.setText(model.getValueAt(fila, 7).toString());
        contrasenaField.setText(model.getValueAt(fila, 8).toString());
    }//GEN-LAST:event_personalTableMouseClicked

    private void registrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBotonActionPerformed
        // TODO add your handling code here:
        int DNI = Integer.parseInt(dniField.getText());
        String funcionalidad = cargoField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        int telefono = Integer.parseInt(telefonoField.getText());
        String direccion = direccionField.getText();
        String estado = estadoField.getText();
        String usuario = usuarioField.getText();
        String contrasena = contrasenaField.getText();
        
        LocalDateTime fechaActual = LocalDateTime.now();
        
        Personal personalNuevo = new Personal(DNI, funcionalidad, nombre, apellido, telefono, direccion, usuario, contrasena, estado, fechaActual, fechaActual) {
        };
        
        PersonalRepository personalRepository = new PersonalRepository();
        personalRepository.crear(personalNuevo);
        
        Object[] filaNueva = {
          DNI, funcionalidad, nombre, apellido, telefono, direccion, estado, usuario, "*".repeat(contrasena.length())  
        };
        mt.addRow(filaNueva);
    }//GEN-LAST:event_registrarBotonActionPerformed

    private void cancelarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBotonActionPerformed
        // TODO add your handling code here:
        personalTable.clearSelection();
        dniField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
    }//GEN-LAST:event_cancelarBotonActionPerformed

    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
        // TODO add your handling code here:
        PersonalRepository personalRepository = new PersonalRepository();
        
        int DNI = Integer.parseInt(dniField.getText());
        String funcionalidad = cargoField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        int telefono = Integer.parseInt(telefonoField.getText());
        String direccion = direccionField.getText();
        String estado = estadoField.getText();
        String usuario = usuarioField.getText();
        
        Personal personal = personalRepository.obtener(DNI);
        
        String contrasena = personal.getContrasena();
        
        LocalDateTime fechaModificacion = LocalDateTime.now();
        
        // No se usará la fechaCreacion, solo se pasa un parámetro porque el constructor lo pide
        Personal personalNuevo = new Personal(DNI, funcionalidad, nombre, apellido, telefono, direccion, usuario, contrasena, estado, fechaModificacion, fechaModificacion) {
        };
        
        
        personalRepository.actualizar(personalNuevo);
        resetearTabla();
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void deshabilitarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deshabilitarBotonActionPerformed
        // TODO add your handling code here:
        int DNI = Integer.parseInt(dniField.getText());
        
        
        PersonalRepository personalRepository = new PersonalRepository();
        personalRepository.eliminar(DNI);
        
        resetearTabla();
    }//GEN-LAST:event_deshabilitarBotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre1;
    private javax.swing.JLabel Nombre2;
    private javax.swing.JLabel Nombre3;
    private javax.swing.JLabel Nombre4;
    private javax.swing.JLabel Reservación;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JButton cancelarBoton;
    private javax.swing.JTextField cargoField;
    private javax.swing.JPasswordField contrasenaField;
    private javax.swing.JButton deshabilitarBoton;
    private javax.swing.JLabel direccin;
    private javax.swing.JTextField direccionField;
    private javax.swing.JLabel dni;
    private javax.swing.JLabel dni1;
    private javax.swing.JTextField dniField;
    private javax.swing.JTextField estadoField;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBoton;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTable personalTable;
    private javax.swing.JButton registrarBoton;
    private javax.swing.JTextField telefonoField;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}

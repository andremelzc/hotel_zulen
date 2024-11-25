/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.AmaLlaves;

import Persistencia.LimpiezaRepository;
import Persistencia.PersonalRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import modelo.AmaDeLlaves;
import modelo.Housekeeper;
import modelo.Personal;

/**
 *
 * @author Suyco
 */
public class vistaAmaLLavesHousekeeperCRUD extends javax.swing.JPanel {

    DefaultTableModel modelo;
    private AmaDeLlaves amaLlaves;
    public vistaAmaLLavesHousekeeperCRUD(AmaDeLlaves amaLlaves) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.amaLlaves = amaLlaves;
        btnToggle.setSelected(true);
        modelo = (DefaultTableModel) Tabla.getModel();
        Object[] houseKeeper = new Object[8];
      
        LimpiezaRepository repoLimpieza = new LimpiezaRepository(); 
        if(repoLimpieza.registroHechoHoyParaHabitacion1()){
            System.out.println("Ya se han asignado limpiezas el dia de hoy");
             btnAsignar.setEnabled(false);
        }
    }
    
    public int seleccionarPedido(JTable Tabla) {
        try {
            int fila = Tabla.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila >= 0) {
                int id = Integer.parseInt(Tabla.getValueAt(fila, 0).toString());
                dniField.setText(Tabla.getValueAt(fila, 0).toString());
                nombreField.setText(Tabla.getValueAt(fila, 1).toString());
                apellidoField.setText(Tabla.getValueAt(fila, 2).toString());
                telefonoField.setText(Tabla.getValueAt(fila, 3).toString());
                estadoField.setText(Tabla.getValueAt(fila, 7).toString());
                correoField.setText(Tabla.getValueAt(fila, 4).toString());
                usuarioField.setText(Tabla.getValueAt(fila, 5).toString());
                contrasenaField.setText(Tabla.getValueAt(fila, 6).toString());
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
    private void mostrarTablaActivos() {
        
        modelo.setRowCount(0);

        List<Housekeeper> house = new ArrayList<>();
        
        house = amaLlaves.obtenerListaHouseActivos();

        
        for (Housekeeper housekeeper : house) {
            Object[] fila = {
                housekeeper.getDNI(),
                housekeeper.getNombre(),
                housekeeper.getApellido(),
                housekeeper.getTelefono(),
                housekeeper.getDireccion(),
                housekeeper.getUsuario(),
                housekeeper.getContrasena(),
                housekeeper.getEstado(),
                housekeeper.getFechaCrea()
            };
            modelo.addRow(fila);
        }

        Tabla.setModel(modelo);
    }
    private void mostrarTablaInactivos(){
        modelo.setRowCount(0);
        List<Housekeeper> house = new ArrayList<>();
        
        house = amaLlaves.obtenerListaHouseInactivos();
        for (Housekeeper housekeeper : house) {
            Object[] fila = {
                housekeeper.getDNI(),
                housekeeper.getNombre(),
                housekeeper.getApellido(),
                housekeeper.getTelefono(),
                housekeeper.getDireccion(),
                housekeeper.getUsuario(),
                housekeeper.getContrasena(),
                housekeeper.getEstado(),
                housekeeper.getFechaCrea()
            };
            modelo.addRow(fila);
        }

        Tabla.setModel(modelo);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dni = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Reservación = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        Nombre1 = new javax.swing.JLabel();
        telefonoField = new javax.swing.JTextField();
        direccin = new javax.swing.JLabel();
        correoField = new javax.swing.JTextField();
        Nombre2 = new javax.swing.JLabel();
        estadoField = new javax.swing.JTextField();
        Nombre3 = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        Nombre4 = new javax.swing.JLabel();
        contrasenaField = new javax.swing.JPasswordField();
        btnBuscar = new javax.swing.JButton();
        dniField = new javax.swing.JTextField();
        nombreField = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btnToggle = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        btnAsignar = new javax.swing.JButton();
        registrarBoton = new javax.swing.JButton();
        deshabilitarBoton = new javax.swing.JButton();
        cancelarBoton = new javax.swing.JButton();
        modificarBoton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 520));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Apellidos", "Telefono", "Correo", "Usuario", "Contraseña", "Estado", "Fecha de creacion"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 770, 370));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 45, 44));
        jLabel4.setText("Datos de Housekeepers");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 288, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dni.setForeground(new java.awt.Color(0, 0, 0));
        dni.setText("DNI");
        dni.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Nombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre.setForeground(new java.awt.Color(0, 0, 0));
        Nombre.setText("Nombre");
        Nombre.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        Reservación.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reservación.setForeground(new java.awt.Color(0, 0, 0));
        Reservación.setText("Apellido");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, -1));
        jPanel1.add(apellidoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 250, 30));

        Nombre1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre1.setForeground(new java.awt.Color(0, 0, 0));
        Nombre1.setText("Estado");
        Nombre1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 80, 30));
        jPanel1.add(telefonoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 250, 30));

        direccin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        direccin.setForeground(new java.awt.Color(0, 0, 0));
        direccin.setText("Correo");
        direccin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(direccin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));
        jPanel1.add(correoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 250, 30));

        Nombre2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre2.setForeground(new java.awt.Color(0, 0, 0));
        Nombre2.setText("Telefono");
        Nombre2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        estadoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoFieldActionPerformed(evt);
            }
        });
        jPanel1.add(estadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 250, 30));

        Nombre3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre3.setForeground(new java.awt.Color(0, 0, 0));
        Nombre3.setText("Contraseña");
        Nombre3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 130, -1));

        usuarioField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioFieldActionPerformed(evt);
            }
        });
        jPanel1.add(usuarioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 250, 30));

        Nombre4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre4.setForeground(new java.awt.Color(0, 0, 0));
        Nombre4.setText("Usuario");
        Nombre4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));
        jPanel1.add(contrasenaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 250, 30));

        btnBuscar.setBackground(new java.awt.Color(255, 127, 17));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 90, -1));
        jPanel1.add(dniField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 210, 30));
        jPanel1.add(nombreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 250, 30));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 370, 10));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 410, 350));

        btnToggle.setBackground(new java.awt.Color(255, 127, 17));
        btnToggle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnToggle.setForeground(new java.awt.Color(255, 255, 255));
        btnToggle.setText("Solo activos");
        btnToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleActionPerformed(evt);
            }
        });
        jPanel2.add(btnToggle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 180, 30));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ver Limpiezas asignadas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 180, 30));

        btnAsignar.setBackground(new java.awt.Color(255, 127, 17));
        btnAsignar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAsignar.setForeground(new java.awt.Color(255, 255, 255));
        btnAsignar.setText("Asignar Reservas");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAsignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 180, 30));

        registrarBoton.setBackground(new java.awt.Color(255, 127, 17));
        registrarBoton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrarBoton.setForeground(new java.awt.Color(255, 255, 255));
        registrarBoton.setText("Registrar");
        registrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(registrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 410, 170, 30));

        deshabilitarBoton.setBackground(new java.awt.Color(255, 127, 17));
        deshabilitarBoton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deshabilitarBoton.setForeground(new java.awt.Color(255, 255, 255));
        deshabilitarBoton.setText("Deshabilitar");
        deshabilitarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deshabilitarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(deshabilitarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, 170, 30));

        cancelarBoton.setBackground(new java.awt.Color(255, 127, 17));
        cancelarBoton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancelarBoton.setForeground(new java.awt.Color(255, 255, 255));
        cancelarBoton.setText("Cancelar");
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 450, 170, 30));

        modificarBoton.setBackground(new java.awt.Color(255, 127, 17));
        modificarBoton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        modificarBoton.setForeground(new java.awt.Color(255, 255, 255));
        modificarBoton.setText("Modificar");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(modificarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 410, 170, 30));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 700, 10));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 390, 370, 10));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void registrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBotonActionPerformed
        // TODO add your handling code here:
        int DNI = Integer.parseInt(dniField.getText());
        String funcionalidad = "Housekeeper";
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        int telefono = Integer.parseInt(telefonoField.getText());
        String direccion = correoField.getText();
        String estado = "Activo";
        String usuario = usuarioField.getText();
        String contrasena = contrasenaField.getText();
        LocalDateTime fechaCrea = LocalDateTime.now();
        Personal personalNuevo = new Personal(DNI, funcionalidad, nombre, apellido, telefono, direccion, usuario, contrasena, estado) {
        };

        PersonalRepository personalRepository = new PersonalRepository();
        personalRepository.crear(personalNuevo);

        Object[] filaNueva = {
            DNI, nombre, apellido, telefono, direccion, usuario, contrasena,estado,fechaCrea
        };
        
        modelo.addRow(filaNueva);
    }//GEN-LAST:event_registrarBotonActionPerformed

    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
        // TODO add your handling code here:
        PersonalRepository personalRepository = new PersonalRepository();
        Housekeeper house = new Housekeeper();
        int DNI = Integer.parseInt(dniField.getText());
        house = amaLlaves.obtenerHousekeeper(DNI);
        String funcionalidad = "Housekeeper";
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        int telefono = Integer.parseInt(telefonoField.getText());
        String direccion = correoField.getText();
        String estado = estadoField.getText();
        String usuario = usuarioField.getText();

        Personal personal = personalRepository.obtener(DNI);

        String contrasena = personal.getContrasena();

        
        Personal personalNuevo = new Personal(DNI, funcionalidad, nombre, apellido, telefono, direccion, usuario, contrasena, estado,house.getFechaCrea(),house.getFechaMod()) {
        };

        personalRepository.actualizar(personalNuevo);
        
        if("Inactivo".equals(estado)){
            mostrarTablaInactivos();
        }else{
            mostrarTablaActivos();
        }
        
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void deshabilitarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deshabilitarBotonActionPerformed
        // TODO add your handling code here:
        int DNI = Integer.parseInt(dniField.getText());

        PersonalRepository personalRepository = new PersonalRepository();
        personalRepository.eliminar(DNI);
        estadoField.setText("Inactivo");
        //resetearTabla();
    }//GEN-LAST:event_deshabilitarBotonActionPerformed

    private void cancelarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBotonActionPerformed
        // TODO add your handling code here:
        //personalTable.clearSelection();
        dniField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        telefonoField.setText("");
        estadoField.setText("");
        correoField.setText("");
        usuarioField.setText("");
        contrasenaField.setText("");
    }//GEN-LAST:event_cancelarBotonActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Housekeeper housekeeper = new Housekeeper();
        housekeeper = amaLlaves.obtenerHousekeeper(Integer.parseInt(dniField.getText()));
        nombreField.setText(housekeeper.getNombre());
        apellidoField.setText(housekeeper.getApellido());
        telefonoField.setText(String.valueOf(housekeeper.getTelefono()));
        estadoField.setText(housekeeper.getEstado());
        correoField.setText(housekeeper.getDireccion());
        usuarioField.setText(housekeeper.getUsuario());
        contrasenaField.setText(housekeeper.getContrasena());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleActionPerformed
        if(btnToggle.isSelected()){
            mostrarTablaActivos();
        }else{
            mostrarTablaInactivos();
        }
    }//GEN-LAST:event_btnToggleActionPerformed

    public JToggleButton getBtnToggle() {
        return btnToggle;
    }

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int idHousekeeper = seleccionarPedido(Tabla);
        vistaDatosLimpieza vistaLimpiezas = new vistaDatosLimpieza(idHousekeeper);
        vistaLimpiezas.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClicked
        int idHousekeeper = seleccionarPedido(Tabla);
    }//GEN-LAST:event_MouseClicked

    private void estadoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoFieldActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        int resultado = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {    
            amaLlaves.asignarLimpiezas();
            JOptionPane.showMessageDialog(null, "Las limpiezas han sido asignadas correctamente en la BD.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btnAsignar.setEnabled(false); 
        }
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void usuarioFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioFieldActionPerformed

    public JButton getBtnAsignar() {
        return btnAsignar;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre1;
    private javax.swing.JLabel Nombre2;
    private javax.swing.JLabel Nombre3;
    private javax.swing.JLabel Nombre4;
    private javax.swing.JLabel Reservación;
    private javax.swing.JTable Tabla;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JToggleButton btnToggle;
    private javax.swing.JButton cancelarBoton;
    private javax.swing.JPasswordField contrasenaField;
    private javax.swing.JTextField correoField;
    private javax.swing.JButton deshabilitarBoton;
    private javax.swing.JLabel direccin;
    private javax.swing.JLabel dni;
    private javax.swing.JTextField dniField;
    private javax.swing.JTextField estadoField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton modificarBoton;
    private javax.swing.JTextField nombreField;
    private javax.swing.JButton registrarBoton;
    private javax.swing.JTextField telefonoField;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}

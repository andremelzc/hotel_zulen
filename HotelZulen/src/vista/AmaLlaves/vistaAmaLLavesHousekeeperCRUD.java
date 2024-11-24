/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.AmaLlaves;

import Persistencia.PersonalRepository;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
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
        FlatNightOwlIJTheme.setup();
        initComponents();
        this.amaLlaves = amaLlaves;
        btnToggle.setSelected(true);
        modelo = (DefaultTableModel) Tabla.getModel();
        Object[] houseKeeper = new Object[8];
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
        nombreField = new javax.swing.JTextField();
        Nombre = new javax.swing.JLabel();
        dniField = new javax.swing.JTextField();
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
        registrarBoton = new javax.swing.JButton();
        modificarBoton = new javax.swing.JButton();
        deshabilitarBoton = new javax.swing.JButton();
        cancelarBoton = new javax.swing.JButton();
        btnToggle = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 520));

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 45, 44));
        jLabel4.setText("Datos de Housekeepers");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dni.setText("DNI:");
        dni.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        nombreField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(nombreField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 200, 40));

        Nombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre.setText("Nombre:");
        Nombre.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        dniField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(dniField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 200, 40));

        Reservación.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reservación.setText("Apellido:");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 90, -1));

        apellidoField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(apellidoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 200, 40));

        Nombre1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre1.setText("Estado:");
        Nombre1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 80, 30));

        telefonoField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(telefonoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 40));

        direccin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        direccin.setText("Correo");
        direccin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(direccin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        correoField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(correoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 420, 40));

        Nombre2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre2.setText("Telefono:");
        Nombre2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        estadoField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(estadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 200, 40));

        Nombre3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre3.setText("Contraseña:");
        Nombre3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 130, -1));

        usuarioField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(usuarioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 40));

        Nombre4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Nombre4.setText("Usuario:");
        Nombre4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Nombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        contrasenaField.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.add(contrasenaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 200, 40));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        registrarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        registrarBoton.setText("Registrar");
        registrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBotonActionPerformed(evt);
            }
        });

        modificarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        modificarBoton.setText("Modificar");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });

        deshabilitarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deshabilitarBoton.setText("Deshabilitar");
        deshabilitarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deshabilitarBotonActionPerformed(evt);
            }
        });

        cancelarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelarBoton.setText("Cancelar");
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });

        btnToggle.setText("Solo activos");
        btnToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleActionPerformed(evt);
            }
        });

        jButton1.setText("Ver Limpiezas asignadas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnToggle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(registrarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(deshabilitarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(971, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registrarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modificarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deshabilitarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(btnToggle))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jButton1)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addContainerGap(485, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int idHousekeeper = seleccionarPedido(Tabla);
        vistaDatosLimpieza vistaLimpiezas = new vistaDatosLimpieza(idHousekeeper);
        vistaLimpiezas.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClicked
        int idHousekeeper = seleccionarPedido(Tabla);
    }//GEN-LAST:event_MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre1;
    private javax.swing.JLabel Nombre2;
    private javax.swing.JLabel Nombre3;
    private javax.swing.JLabel Nombre4;
    private javax.swing.JLabel Reservación;
    private javax.swing.JTable Tabla;
    private javax.swing.JTextField apellidoField;
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
    private javax.swing.JButton modificarBoton;
    private javax.swing.JTextField nombreField;
    private javax.swing.JButton registrarBoton;
    private javax.swing.JTextField telefonoField;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}

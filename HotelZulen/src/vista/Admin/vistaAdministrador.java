/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.Admin;

import java.awt.BorderLayout;
import modelo.Administrador;
import static modelo.InicioSession.cerrarSesion;
import vista.Recepcionista.vistaRecepcionistaFacturar;
import vista.Recepcionista.vistaRecepcionistaRegistrar;

/**
 *
 * @author PC
 */
public class vistaAdministrador extends javax.swing.JFrame {
    
    private Administrador administrador;
    /**
     * Creates new form vistaAdministrador
     */
    public vistaAdministrador(Administrador administrador) {
        initComponents();
        this.administrador=administrador;
        mostrarInfo();
    }
    
    // Método para mostrar la información del recepcionista
    private void mostrarInfo() {
        // Ejemplo: mostrar el nombre y el ID del recepcionista en etiquetas o campos de la interfaz
        if (administrador != null) {
            // Aquí puedes asignar los valores a componentes de la interfaz
            // Ejemplo: labelNombre.setText(recepcionista.getNombre());
            System.out.println("Nombre del recepcionista: " + administrador.getNombre());
            System.out.println("ID del recepcionista: " + administrador.getDNI());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelIzquierda = new javax.swing.JPanel();
        iniciarSesion = new javax.swing.JLabel();
        personalBoton = new javax.swing.JButton();
        serviciosBoton = new javax.swing.JButton();
        habitacionesBoton = new javax.swing.JButton();
        consultarBoton = new javax.swing.JButton();
        finanzasBoton = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        cerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(221, 221, 221));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelIzquierda.setBackground(new java.awt.Color(43, 45, 66));

        iniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        iniciarSesion.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        iniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        iniciarSesion.setText("Hotel Zulen");

        personalBoton.setBackground(new java.awt.Color(141, 153, 174));
        personalBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        personalBoton.setText("Personal");
        personalBoton.setBorder(null);
        personalBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personalBotonMouseClicked(evt);
            }
        });
        personalBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personalBotonActionPerformed(evt);
            }
        });

        serviciosBoton.setBackground(new java.awt.Color(141, 153, 174));
        serviciosBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        serviciosBoton.setText("Servicios");
        serviciosBoton.setBorder(null);
        serviciosBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serviciosBotonMouseClicked(evt);
            }
        });
        serviciosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviciosBotonActionPerformed(evt);
            }
        });

        habitacionesBoton.setBackground(new java.awt.Color(141, 153, 174));
        habitacionesBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        habitacionesBoton.setText("Habitaciones");
        habitacionesBoton.setBorder(null);
        habitacionesBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                habitacionesBotonMouseClicked(evt);
            }
        });
        habitacionesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                habitacionesBotonActionPerformed(evt);
            }
        });

        consultarBoton.setBackground(new java.awt.Color(141, 153, 174));
        consultarBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        consultarBoton.setText("Consultar");
        consultarBoton.setBorder(null);
        consultarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consultarBotonMouseClicked(evt);
            }
        });
        consultarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarBotonActionPerformed(evt);
            }
        });

        finanzasBoton.setBackground(new java.awt.Color(141, 153, 174));
        finanzasBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        finanzasBoton.setText("Finanzas");
        finanzasBoton.setActionCommand("");
        finanzasBoton.setBorder(null);
        finanzasBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finanzasBotonMouseClicked(evt);
            }
        });
        finanzasBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finanzasBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIzquierdaLayout = new javax.swing.GroupLayout(panelIzquierda);
        panelIzquierda.setLayout(panelIzquierdaLayout);
        panelIzquierdaLayout.setHorizontalGroup(
            panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(iniciarSesion)
                .addGap(42, 42, 42)
                .addComponent(personalBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(serviciosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(habitacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(consultarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(finanzasBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        panelIzquierdaLayout.setVerticalGroup(
            panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdaLayout.createSequentialGroup()
                .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdaLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(personalBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serviciosBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(habitacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(consultarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finanzasBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelIzquierdaLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(iniciarSesion)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        background.add(panelIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 120));

        content.setBackground(new java.awt.Color(221, 221, 221));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        background.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1280, 520));

        cerrarSesion.setBackground(new java.awt.Color(239, 35, 60));
        cerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        background.add(cerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 640, 220, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 1274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void personalBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personalBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_personalBotonMouseClicked

    private void personalBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personalBotonActionPerformed
        // TODO add your handling code here:
        vistaAdministradorPersonal administradorPersonal = new vistaAdministradorPersonal();
        administradorPersonal.setSize(1280, 720);
        administradorPersonal.setLocation(0, 0);
        content.removeAll();
        content.add(administradorPersonal, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_personalBotonActionPerformed

    private void serviciosBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviciosBotonMouseClicked
        // TODO add your handling code here:
        vistaAdministradorServicios administradorServicios = new vistaAdministradorServicios();
        administradorServicios.setSize(1280, 720);
        administradorServicios.setLocation(0, 0);
        content.removeAll();
        content.add(administradorServicios, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_serviciosBotonMouseClicked

    private void serviciosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviciosBotonActionPerformed
        // TODO add your handling code here:
        vistaRecepcionistaRegistrar recepcionistaRegistrar = new vistaRecepcionistaRegistrar();
        recepcionistaRegistrar.setSize(1280, 720);
        recepcionistaRegistrar.setLocation(0, 0);
        content.removeAll();
        content.add(recepcionistaRegistrar, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_serviciosBotonActionPerformed

    private void habitacionesBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_habitacionesBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_habitacionesBotonMouseClicked

    private void habitacionesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_habitacionesBotonActionPerformed
        // TODO add your handling code here:
        vistaAdministradorHabitaciones administradorHabitaciones = new vistaAdministradorHabitaciones();
        administradorHabitaciones.setSize(1280, 720);
        administradorHabitaciones.setLocation(0, 0);
        content.removeAll();
        content.add(administradorHabitaciones, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_habitacionesBotonActionPerformed

    private void consultarBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_consultarBotonMouseClicked

    private void consultarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarBotonActionPerformed
        // TODO add your handling code here:
        vistaAdministradorReservaciones administradorReservaciones = new vistaAdministradorReservaciones();
        administradorReservaciones.setSize(1280, 720);
        administradorReservaciones.setLocation(0, 0);
        content.removeAll();
        content.add(administradorReservaciones, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_consultarBotonActionPerformed

    private void finanzasBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finanzasBotonMouseClicked
        // TODO add your handling code here:
        vistaRecepcionistaFacturar recepcionistaFacturar = new vistaRecepcionistaFacturar();
        recepcionistaFacturar.setSize(1280, 720);
        recepcionistaFacturar.setLocation(0, 0);
        content.removeAll();
        content.add(recepcionistaFacturar, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_finanzasBotonMouseClicked

    private void finanzasBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finanzasBotonActionPerformed
        // TODO add your handling code here:
        vistaAdministradorFinanzas administradorFinanzas = new vistaAdministradorFinanzas();
        administradorFinanzas.setSize(1280, 720);
        administradorFinanzas.setLocation(0, 0);
        content.removeAll();
        content.add(administradorFinanzas, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_finanzasBotonActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
       cerrarSesion();
    }//GEN-LAST:event_cerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JButton consultarBoton;
    private javax.swing.JPanel content;
    private javax.swing.JButton finanzasBoton;
    private javax.swing.JButton habitacionesBoton;
    private javax.swing.JLabel iniciarSesion;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JButton personalBoton;
    private javax.swing.JButton serviciosBoton;
    // End of variables declaration//GEN-END:variables
}

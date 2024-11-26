/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.Admin;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import java.awt.BorderLayout;
import javax.swing.JButton;
import modelo.Administrador;
import static modelo.InicioSession.cerrarSesion;

import vista.vistaConsultarHuesped;

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
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.administrador = administrador;
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
        content = new javax.swing.JPanel();
        cerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelIzquierda.setBackground(new java.awt.Color(255, 127, 17));
        panelIzquierda.setForeground(new java.awt.Color(255, 255, 255));
        panelIzquierda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        iniciarSesion.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        iniciarSesion.setText("Hotel Zulen");
        panelIzquierda.add(iniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 25, -1, -1));

        personalBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        personalBoton.setText("Personal");
        personalBoton.setBorder(null);
        personalBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personalBotonActionPerformed(evt);
            }
        });
        panelIzquierda.add(personalBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 25, 210, 45));

        serviciosBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        serviciosBoton.setText("Servicios");
        serviciosBoton.setBorder(null);
        serviciosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviciosBotonActionPerformed(evt);
            }
        });
        panelIzquierda.add(serviciosBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 25, 210, 45));

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
        panelIzquierda.add(habitacionesBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 25, 210, 45));

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
        panelIzquierda.add(consultarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 25, 210, 45));

        background.add(panelIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1290, 100));

        content.setBackground(new java.awt.Color(255, 255, 255));

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
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    public JButton getPersonalBoton() {
        return personalBoton;
    }


    private void serviciosBotonMouseClicked(java.awt.event.MouseEvent evt) {

        // TODO add your handling code here:
        vistaAdministradorServicios administradorServicios = new vistaAdministradorServicios();
        administradorServicios.setSize(1280, 720);
        administradorServicios.setLocation(0, 0);
        content.removeAll();
        content.add(administradorServicios, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

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
        vistaConsultarHuesped consultarHuesped = new vistaConsultarHuesped();
        consultarHuesped.setSize(1280, 720);
        consultarHuesped.setLocation(0, 0);
        content.removeAll();
        content.add(consultarHuesped, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_consultarBotonActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_cerrarSesionActionPerformed

    private void habitacionesBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_habitacionesBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_habitacionesBotonMouseClicked

    private void serviciosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviciosBotonActionPerformed
        // TODO add your handling code here:
        vistaAdministradorServicios administradorServicios = new vistaAdministradorServicios();
        administradorServicios.setSize(1280, 720);
        administradorServicios.setLocation(0, 0);
        content.removeAll();
        content.add(administradorServicios, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_serviciosBotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JButton consultarBoton;
    private javax.swing.JPanel content;
    private javax.swing.JButton habitacionesBoton;
    private javax.swing.JLabel iniciarSesion;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JButton personalBoton;
    private javax.swing.JButton serviciosBoton;
    // End of variables declaration//GEN-END:variables
}

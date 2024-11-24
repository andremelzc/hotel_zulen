/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Admin;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.BorderLayout;
import vista.vistaConsultarHabitacion;
import vista.vistaConsultarHuesped;

/**
 *
 * @author PC
 */
public class vistaAdministradorReservaciones extends javax.swing.JPanel {

    /**
     * Creates new form vistaAdministradorPersonal
     */
    public vistaAdministradorReservaciones() {
        FlatNightOwlIJTheme.setup();
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        huespedesBoton = new javax.swing.JButton();
        habitacionesBoton = new javax.swing.JButton();
        subcontent = new javax.swing.JPanel();

        setBackground(new java.awt.Color(221, 221, 221));

        huespedesBoton.setBackground(new java.awt.Color(239, 35, 60));
        huespedesBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        huespedesBoton.setText("Huesped");
        huespedesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huespedesBotonActionPerformed(evt);
            }
        });

        habitacionesBoton.setBackground(new java.awt.Color(239, 35, 60));
        habitacionesBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        habitacionesBoton.setText("Habitación");
        habitacionesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                habitacionesBotonActionPerformed(evt);
            }
        });

        subcontent.setBackground(new java.awt.Color(221, 221, 221));

        javax.swing.GroupLayout subcontentLayout = new javax.swing.GroupLayout(subcontent);
        subcontent.setLayout(subcontentLayout);
        subcontentLayout.setHorizontalGroup(
            subcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        subcontentLayout.setVerticalGroup(
            subcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(350, 350, 350)
                            .addComponent(huespedesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(habitacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(subcontent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(huespedesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(habitacionesBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(subcontent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void huespedesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huespedesBotonActionPerformed
        // TODO add your handling code here:
        vistaConsultarHuesped recepcionistaConsultarHuesped = new vistaConsultarHuesped();
        recepcionistaConsultarHuesped.setSize(1280, 720);
        recepcionistaConsultarHuesped.setLocation(0, 0);
        subcontent.removeAll();
        subcontent.add(recepcionistaConsultarHuesped, BorderLayout.CENTER);
        subcontent.revalidate();
        subcontent.repaint();
    }//GEN-LAST:event_huespedesBotonActionPerformed

    private void habitacionesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_habitacionesBotonActionPerformed
        // TODO add your handling code here:
        vistaConsultarHabitacion recepcionistaConsultarHabitacion = new vistaConsultarHabitacion();
        recepcionistaConsultarHabitacion.setSize(1280, 720);
        recepcionistaConsultarHabitacion.setLocation(0, 0);
        subcontent.removeAll();
        subcontent.add(recepcionistaConsultarHabitacion, BorderLayout.CENTER);
        subcontent.revalidate();
        subcontent.repaint();
    }//GEN-LAST:event_habitacionesBotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton habitacionesBoton;
    private javax.swing.JButton huespedesBoton;
    private javax.swing.JPanel subcontent;
    // End of variables declaration//GEN-END:variables
}

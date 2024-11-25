/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.JefeCocina;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;


/**
 *
 * @author PC
 */
public class vistaJefeCocinaCarta extends javax.swing.JPanel {


    public vistaJefeCocinaCarta() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRegistrarCombo = new javax.swing.JButton();
        btnModificarConsumible = new javax.swing.JButton();
        contenedor = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrarCombo.setBackground(new java.awt.Color(255, 127, 17));
        btnRegistrarCombo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarCombo.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarCombo.setText("Registrar Combo");
        btnRegistrarCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarComboActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrarCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 220, 30));

        btnModificarConsumible.setBackground(new java.awt.Color(255, 127, 17));
        btnModificarConsumible.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarConsumible.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarConsumible.setText("Registrar Consumible");
        btnModificarConsumible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarConsumibleActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificarConsumible, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 220, 30));

        contenedor.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );

        jPanel1.add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 49, 1280, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarConsumibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarConsumibleActionPerformed
        vistaJefeCocinaCartaConsumible consumible = new vistaJefeCocinaCartaConsumible();
        System.out.println("Ingresando a la pestaña de cosumible");
        consumible.setPreferredSize(new Dimension(1280, 475)); 
        consumible.setLocation(0, 0);
        contenedor.setLayout(new BorderLayout());
        contenedor.removeAll();
        contenedor.add(consumible, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
        System.out.println("Panel agregado: " + consumible.isVisible());
        consumible.getMostrarTabla().doClick();
    }//GEN-LAST:event_btnModificarConsumibleActionPerformed

    private void btnRegistrarComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarComboActionPerformed

       vistaJefeCocinaCartaCombo combo = new vistaJefeCocinaCartaCombo();
       System.out.println("Ingresando a la pestaña de combitos");
       combo.setPreferredSize(new Dimension(1280, 475)); 
       combo.setLocation(0, 0);
       contenedor.setLayout(new BorderLayout());
       contenedor.removeAll();
       contenedor.add(combo, BorderLayout.CENTER);
       contenedor.revalidate();
       contenedor.repaint();
       System.out.println("Panel agregado: " + combo.isVisible());
    }//GEN-LAST:event_btnRegistrarComboActionPerformed
     
    public JButton getBtnRegistrarCombo() {
        return btnRegistrarCombo;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificarConsumible;
    private javax.swing.JButton btnRegistrarCombo;
    private javax.swing.JPanel contenedor;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

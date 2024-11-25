/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.AmaLlaves;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.BorderLayout;
import javax.swing.JButton;
import modelo.AmaDeLlaves;
import static modelo.InicioSession.cerrarSesion;


/**
 *
 * @author PC
 */
public class vistaAmaLLaves extends javax.swing.JFrame {

    private AmaDeLlaves amaLlaves;
    public vistaAmaLLaves(AmaDeLlaves amaLlaves) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.amaLlaves = amaLlaves;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelIzquierda = new javax.swing.JPanel();
        iniciarSesion = new javax.swing.JLabel();
        principalBoton = new javax.swing.JButton();
        habitacionesBoton = new javax.swing.JButton();
        houseKeepersBoton = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        cerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setPreferredSize(new java.awt.Dimension(1280, 720));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelIzquierda.setBackground(new java.awt.Color(255, 127, 17));
        panelIzquierda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        iniciarSesion.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        iniciarSesion.setText("Hotel Zulen");
        panelIzquierda.add(iniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 25, -1, -1));

        principalBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        principalBoton.setText("Principal");
        principalBoton.setBorder(null);
        principalBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                principalBotonMouseClicked(evt);
            }
        });
        principalBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                principalBotonActionPerformed(evt);
            }
        });
        panelIzquierda.add(principalBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 25, 250, 45));

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
        panelIzquierda.add(habitacionesBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 25, 250, 45));

        houseKeepersBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        houseKeepersBoton.setText("HouseeKeepers");
        houseKeepersBoton.setBorder(null);
        houseKeepersBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                houseKeepersBotonMouseClicked(evt);
            }
        });
        houseKeepersBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                houseKeepersBotonActionPerformed(evt);
            }
        });
        panelIzquierda.add(houseKeepersBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 25, 250, 45));

        background.add(panelIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 100));

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

        background.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1280, 520));

        cerrarSesion.setBackground(new java.awt.Color(239, 35, 60));
        cerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        background.add(cerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 650, 220, 50));

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

    private void principalBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_principalBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_principalBotonMouseClicked

    private void principalBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_principalBotonActionPerformed
        // TODO add your handling code here:
        System.out.println("princiapl");
        vistaAmaLLavesPrincipal amaLLavesPrincipal = new vistaAmaLLavesPrincipal();
        amaLLavesPrincipal.setSize(1280, 720);
        amaLLavesPrincipal.setLocation(0, 0);
        content.removeAll();
        content.add(amaLLavesPrincipal, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_principalBotonActionPerformed

    public JButton getPrincipalBoton() {
        return principalBoton;
    }
    
    private void habitacionesBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_habitacionesBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_habitacionesBotonMouseClicked

    private void habitacionesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_habitacionesBotonActionPerformed
        // TODO add your handling code here:
        vistaAmaLLavesHabitaciones amaLLavesHabitaciones = new vistaAmaLLavesHabitaciones(amaLlaves);
        amaLLavesHabitaciones.setSize(1280, 720);
        amaLLavesHabitaciones.setLocation(0, 0);
        content.removeAll();
        content.add(amaLLavesHabitaciones, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_habitacionesBotonActionPerformed

    private void houseKeepersBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_houseKeepersBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_houseKeepersBotonMouseClicked

    private void houseKeepersBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_houseKeepersBotonActionPerformed
        // TODO add your handling code here:
        vistaAmaLLavesHousekeeperCRUD amaLLavesHouseKeeper = new vistaAmaLLavesHousekeeperCRUD(amaLlaves);
        amaLLavesHouseKeeper.setSize(1280, 720);
        amaLLavesHouseKeeper.setLocation(0, 0);
        content.removeAll();
        content.add(amaLLavesHouseKeeper, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
        amaLLavesHouseKeeper.getBtnToggle().doClick();
    }//GEN-LAST:event_houseKeepersBotonActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
       cerrarSesion();
    }//GEN-LAST:event_cerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JPanel content;
    private javax.swing.JButton habitacionesBoton;
    private javax.swing.JButton houseKeepersBoton;
    private javax.swing.JLabel iniciarSesion;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JButton principalBoton;
    // End of variables declaration//GEN-END:variables
}

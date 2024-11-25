/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Recepcionista;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.BorderLayout;
import modelo.Recepcionista;

/**
 *
 * @author PC
 */
public class vistaRecepcionistaPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form vistaRecepcionistaRegistrar
     */

    public vistaRecepcionistaPrincipal(Recepcionista recepcionista) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        bienvenidoLabel.setText("Bienvenido, "+recepcionista.getNombre()+" "+recepcionista.getApellido());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        bienvenidoLabel = new javax.swing.JLabel();
        bienvenidoLabel1 = new javax.swing.JLabel();
        bienvenidoLabel2 = new javax.swing.JLabel();
        bienvenidoLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 520));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/recepcionPrincipal.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        bienvenidoLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bienvenidoLabel.setForeground(new java.awt.Color(0, 0, 0));
        bienvenidoLabel.setText("Bienvenido, \"nombre recepcionista\"");
        add(bienvenidoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, -1, -1));

        bienvenidoLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bienvenidoLabel1.setForeground(new java.awt.Color(0, 0, 0));
        bienvenidoLabel1.setText("tres botones de la sección de arriba.");
        add(bienvenidoLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, -1, 30));

        bienvenidoLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bienvenidoLabel2.setForeground(new java.awt.Color(0, 0, 0));
        bienvenidoLabel2.setText("Por favor, con el fin de atender al huésped, ");
        add(bienvenidoLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, -1));

        bienvenidoLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bienvenidoLabel3.setForeground(new java.awt.Color(0, 0, 0));
        bienvenidoLabel3.setText("siéntese libre de presionar cualquiera de los");
        add(bienvenidoLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, -1, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bienvenidoLabel;
    private javax.swing.JLabel bienvenidoLabel1;
    private javax.swing.JLabel bienvenidoLabel2;
    private javax.swing.JLabel bienvenidoLabel3;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}

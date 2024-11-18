/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.AmaLlaves;

import Persistencia.LimpiezaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import modelo.AmaDeLlaves;

/**
 *
 * @author Suyco
 */
public class vistaAmaLLavesHousekeeperAsignar extends javax.swing.JPanel {

    private static AmaDeLlaves amaLlavesActual;
    
    public vistaAmaLLavesHousekeeperAsignar(AmaDeLlaves amaLlaves) {
        initComponents();
        this.amaLlavesActual = amaLlaves;
        LimpiezaRepository repoLimpieza = new LimpiezaRepository(); 
        if(repoLimpieza.registroHechoHoyParaHabitacion1()){
            System.out.println("Ya se han asignado limpiezas el dia de hoy");
             btnAsignar.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAsignar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 520));

        jLabel1.setText("jLabel1");

        btnAsignar.setText("Asignar Reservas");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1038, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnAsignar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(389, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        int resultado = JOptionPane.showConfirmDialog(null, "¿Deseas continuar=?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {    
            amaLlavesActual.asignarLimpiezas();
            JOptionPane.showMessageDialog(null, "Las limpiezas han sido asignadas correctamente en la BD.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btnAsignar.setEnabled(false); 
        }
       
    }//GEN-LAST:event_btnAsignarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

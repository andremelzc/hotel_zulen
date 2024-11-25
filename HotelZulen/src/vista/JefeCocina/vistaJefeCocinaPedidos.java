/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.JefeCocina;

import vista.Admin.*;
import Persistencia.ReservacionHabitacionComboRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import modelo.ReservacionHabitacionCombo;
/**
 *
 * @author PC
 */
public class vistaJefeCocinaPedidos extends javax.swing.JPanel {

    private ReservacionHabitacionCombo objPedido1 = new ReservacionHabitacionCombo();
    private ReservacionHabitacionComboRepository obj = new ReservacionHabitacionComboRepository();
    private Timer autoUpdateTimer;  // Variable para almacenar el Timer

    public void setAutoUpdateTimer() {
        this.autoUpdateTimer = null;
    }
    
    public vistaJefeCocinaPedidos() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        btnToggle.setSelected(false);
        obj.mostrarHabitacionComboSNoEnviados(jTable1);
    }
    
    private void startAutoUpdateEnviados() {
        // Si ya hay un Timer en ejecución, deténlo
        if (autoUpdateTimer != null) {
            autoUpdateTimer.stop();
        }
        // Crear un Timer que actualice la tabla cada 5000 milisegundos (5 segundos)
        autoUpdateTimer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obj.mostrarHabitacionComboSEnviados(jTable1);
                System.out.println("Timer-Enviados");
            }
        });
        autoUpdateTimer.start(); 
    }
    private void startAutoUpdateNoEnviados() {
        // Si ya hay un Timer en ejecución, deténlo
        if (autoUpdateTimer != null) {
            autoUpdateTimer.stop();
        }
        // Crear un Timer que actualice la tabla cada 5000 milisegundos (5 segundos)
        autoUpdateTimer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obj.mostrarHabitacionComboSNoEnviados(jTable1);
                System.out.println("Timer-No enviados");
            }
        });
        autoUpdateTimer.start(); // Iniciar el Timer
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_Enviado = new javax.swing.JButton();
        btnToggle = new javax.swing.JToggleButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btn_Enviado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Enviado.setText("Marcar como Enviado");
        btn_Enviado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EnviadoActionPerformed(evt);
            }
        });

        btnToggle.setText("Solo enviados");
        btnToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnToggle)
                .addGap(302, 302, 302)
                .addComponent(btn_Enviado, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Enviado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnToggle))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        ReservacionHabitacionComboRepository obj = new ReservacionHabitacionComboRepository();
        obj.seleccionarPedido(jTable1, objPedido1);
        objPedido1.mostrarInfos();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_EnviadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnviadoActionPerformed
        // TODO add your handling code here:
        ReservacionHabitacionComboRepository obj = new ReservacionHabitacionComboRepository();
        obj.modificarEstadoListoS(objPedido1);
    }//GEN-LAST:event_btn_EnviadoActionPerformed

    private void btnToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleActionPerformed
        if(btnToggle.isSelected()){
            obj.mostrarHabitacionComboSEnviados(jTable1);
            startAutoUpdateEnviados();
        }else{
            obj.mostrarHabitacionComboSNoEnviados(jTable1);
            startAutoUpdateNoEnviados();
        }
    }//GEN-LAST:event_btnToggleActionPerformed

    public JToggleButton getBtnToggle() {
        return btnToggle;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnToggle;
    private javax.swing.JButton btn_Enviado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

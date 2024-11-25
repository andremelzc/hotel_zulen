/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.Recepcionista;

import Persistencia.DatabaseConnection;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.BorderLayout;
import javax.swing.JButton;
import modelo.InicioSession;
import static modelo.InicioSession.cerrarSesion;
import modelo.Recepcionista;
import vista.iniciarSesionPersonal;

/**
 *
 * @author PC
 */
public class vistaRecepcionista extends javax.swing.JFrame {
    
    private Recepcionista recepcionista; // Almacena el objeto Recepcionista

    
    public vistaRecepcionista(Recepcionista recepcionista) {
        FlatArcOrangeIJTheme.setup();
        this.recepcionista=recepcionista;
        initComponents();
        this.setLocationRelativeTo(null);
        mostrarInfo();
        
        
    }

    // Método para mostrar la información del recepcionista
    private void mostrarInfo() {
        // Ejemplo: mostrar el nombre y el ID del recepcionista en etiquetas o campos de la interfaz
        if (recepcionista != null) {
            // Aquí puedes asignar los valores a componentes de la interfaz
            // Ejemplo: labelNombre.setText(recepcionista.getNombre());
            System.out.println("Nombre del recepcionista: " + recepcionista.getNombre());
            System.out.println("ID del recepcionista: " + recepcionista.getDNI());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelIzquierda = new javax.swing.JPanel();
        iniciarSesion = new javax.swing.JLabel();
        principalBoton = new javax.swing.JButton();
        registrarBoton = new javax.swing.JButton();
        consultarBoton = new javax.swing.JButton();
        facturarBoton = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        cerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setMinimumSize(new java.awt.Dimension(1280, 720));
        background.setPreferredSize(new java.awt.Dimension(1280, 720));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelIzquierda.setBackground(new java.awt.Color(255, 127, 17));
        panelIzquierda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 63, 0)));
        panelIzquierda.setForeground(new java.awt.Color(255, 255, 255));
        panelIzquierda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        iniciarSesion.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        iniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
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
        panelIzquierda.add(principalBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 25, 180, 45));

        registrarBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        registrarBoton.setText("Registrar reserva");
        registrarBoton.setBorder(null);
        registrarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registrarBotonMouseClicked(evt);
            }
        });
        registrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBotonActionPerformed(evt);
            }
        });
        panelIzquierda.add(registrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 25, 180, 45));

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
        panelIzquierda.add(consultarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 25, 180, 45));

        facturarBoton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        facturarBoton.setText("Facturar");
        facturarBoton.setBorder(null);
        facturarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturarBotonMouseClicked(evt);
            }
        });
        facturarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturarBotonActionPerformed(evt);
            }
        });
        panelIzquierda.add(facturarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 25, 180, 45));

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
        vistaRecepcionistaPrincipal recepcionistaPrincipal = new vistaRecepcionistaPrincipal(recepcionista);
        recepcionistaPrincipal.setSize(1280,720);
        recepcionistaPrincipal.setLocation(0, 0);
        content.removeAll();
        content.add(recepcionistaPrincipal, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_principalBotonActionPerformed

    public JButton getPrincipalBoton() {
        return principalBoton;
    }
        
    private void registrarBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_registrarBotonMouseClicked

    private void registrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBotonActionPerformed
        // TODO add your handling code here:
        vistaRecepcionistaRegistrar recepcionistaRegistrar = new vistaRecepcionistaRegistrar(recepcionista);
        recepcionistaRegistrar.setSize(1280, 720);
        recepcionistaRegistrar.setLocation(0, 0);
        content.removeAll();
        content.add(recepcionistaRegistrar, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
        recepcionistaRegistrar.getHuespedesBoton().doClick();
        recepcionistaRegistrar.getHuespedesBoton().setBorderPainted(true);
    
        
        
    }//GEN-LAST:event_registrarBotonActionPerformed

    private void consultarBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarBotonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_consultarBotonMouseClicked

    private void consultarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarBotonActionPerformed
        // TODO add your handling code here:
        vistaRecepcionistaConsultar recepcionistaConsultar = new vistaRecepcionistaConsultar();
        recepcionistaConsultar.setSize(1280, 720);
        recepcionistaConsultar.setLocation(0, 0);
        content.removeAll();
        content.add(recepcionistaConsultar, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
        recepcionistaConsultar.getHuespedesBoton().doClick();
    }//GEN-LAST:event_consultarBotonActionPerformed

    private void facturarBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturarBotonMouseClicked
        // TODO add your handling code here:
        vistaRecepcionistaFacturar recepcionistaFacturar = new vistaRecepcionistaFacturar(recepcionista);
        recepcionistaFacturar.setSize(1280 , 720);
        recepcionistaFacturar.setLocation(0,0);
        content.removeAll();
        content.add(recepcionistaFacturar, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_facturarBotonMouseClicked

    private void facturarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturarBotonActionPerformed
       
       
    }//GEN-LAST:event_facturarBotonActionPerformed

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_cerrarSesionActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JButton consultarBoton;
    private javax.swing.JPanel content;
    private javax.swing.JButton facturarBoton;
    private javax.swing.JLabel iniciarSesion;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JButton principalBoton;
    private javax.swing.JButton registrarBoton;
    // End of variables declaration//GEN-END:variables
}

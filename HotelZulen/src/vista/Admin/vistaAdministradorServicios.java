/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Admin;

import Persistencia.ServiciosAdicionalesRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ServiciosAdicionales;

public class vistaAdministradorServicios extends javax.swing.JPanel {

    DefaultTableModel modelo;
    public vistaAdministradorServicios() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        modelo = (DefaultTableModel) Tabla.getModel();
        mostrarTabla();
    }

    private int seleccionarPedido(JTable Tabla) {
        try {
            int fila = Tabla.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila >= 0) {
                int id = Integer.parseInt(Tabla.getValueAt(fila, 0).toString());
                System.out.println("id seleccionada: "+id);
                nombreService.setText(Tabla.getValueAt(fila, 1).toString());
                PrecioField.setText(Tabla.getValueAt(fila,2).toString());
                EstadoField.setText(Tabla.getValueAt(fila, 3).toString());
                
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
    
    private void mostrarTabla(){
        ServiciosAdicionalesRepository repo = new ServiciosAdicionalesRepository();
        List<ServiciosAdicionales> listaServicios = new ArrayList<>();
        listaServicios = repo.obtenerServicios();
        modelo.setRowCount(0);
        
        for (ServiciosAdicionales serv : listaServicios) {
            Object[] fila = {
                serv.getId(),
                serv.getConcepto(),
                serv.getCosto(),
                serv.getEstado()
            };
            modelo.addRow(fila);
        }

        Tabla.setModel(modelo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PrecioField = new javax.swing.JTextField();
        Reservación = new javax.swing.JLabel();
        EstadoField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nombreService = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        registrarBoton = new javax.swing.JButton();
        modificarBoton = new javax.swing.JButton();
        cancelarBoton = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Datos del servicio");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 78, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Precio:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 95, -1, -1));
        jPanel1.add(PrecioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 123, 437, 40));

        Reservación.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reservación.setForeground(new java.awt.Color(0, 0, 0));
        Reservación.setText("Estado:");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 181, -1, -1));
        jPanel1.add(EstadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 209, 437, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nombre del servicio: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 16, 403, -1));
        jPanel1.add(nombreService, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 50, 437, 39));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(729, 125, -1, -1));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Costo", "Estado"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClick(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 139, 533, 261));

        registrarBoton.setBackground(new java.awt.Color(255, 127, 17));
        registrarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        registrarBoton.setForeground(new java.awt.Color(255, 255, 255));
        registrarBoton.setText("Registrar");
        registrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(registrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 430, 430, 30));

        modificarBoton.setBackground(new java.awt.Color(255, 127, 17));
        modificarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        modificarBoton.setForeground(new java.awt.Color(255, 255, 255));
        modificarBoton.setText("Modificar");
        modificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(modificarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 390, 220, 30));

        cancelarBoton.setBackground(new java.awt.Color(255, 127, 17));
        cancelarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelarBoton.setForeground(new java.awt.Color(255, 255, 255));
        cancelarBoton.setText("Cancelar");
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 390, 200, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClick
       int idServicio = seleccionarPedido(Tabla);
    }//GEN-LAST:event_MouseClick

    private void registrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBotonActionPerformed

      
    }//GEN-LAST:event_registrarBotonActionPerformed

    private void modificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBotonActionPerformed
       
    }//GEN-LAST:event_modificarBotonActionPerformed

    private void cancelarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBotonActionPerformed
        // TODO add your handling code here:
        nombreService.setText("");
        PrecioField.setText("");
        EstadoField.setText("");
    }//GEN-LAST:event_cancelarBotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EstadoField;
    private javax.swing.JTextField PrecioField;
    private javax.swing.JLabel Reservación;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton cancelarBoton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBoton;
    private javax.swing.JTextField nombreService;
    private javax.swing.JButton registrarBoton;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.JefeCocina;

import Persistencia.DatabaseConnection;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Combo;
import modelo.Consumible;


public class vistaJefeCocinaCartaCombo extends javax.swing.JPanel {

    DefaultTableModel modelo;
    DefaultTableModel modeloConsumible;
    
    public vistaJefeCocinaCartaCombo() {
        FlatArcOrangeIJTheme.setup();
        System.out.println("Ingresando a la pestaña de combos");
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        this.add(scrollPane);
        initComponents();
        modelo = (DefaultTableModel) Tabla.getModel();
        modeloConsumible = (DefaultTableModel) TablaConsumibles.getModel();
    }

    private int seleccionarPedido(){
        try {
            int fila = Tabla.getSelectedRow();
            // Verificar si se ha seleccionado una fila
            if (fila >= 0) {
                int id = Integer.parseInt(Tabla.getValueAt(fila, 0).toString());
                System.out.println("id seleccionada: "+id);
                Tipo1.setText(Tabla.getValueAt(fila, 1).toString());
                Descripcion1.setText(Tabla.getValueAt(fila, 2).toString());
                
                
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
    
    private void mostrarTabla(List<Combo> listaCombos){
        modelo.setRowCount(0);
        
        for (Combo combo : listaCombos) {
            Object[] fila = {
                combo.getId(),
                combo.getTipoComida(),
                combo.getDescripcion(),
            };
            modelo.addRow(fila);
        }

        Tabla.setModel(modelo);
    }
    
    private void mostrarTablaConsumiblesXCombo(int id ){
        String sql = "SELECT c.idCONSUMIBLE, c.NombreConsumible, c.Precio " +
                 "FROM combo_has_consumible chc " +
                 "JOIN consumible c ON chc.CONSUMIBLE_idCONSUMIBLE = c.idCONSUMIBLE " +
                 "WHERE chc.COMBO_idCOMBO = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modeloConsumible.setRowCount(0);
                Object[] fila = {
                    rs.getInt("idCONSUMIBLE"),
                    rs.getString("NombreConsumible"),
                    (float)rs.getDouble("Precio")
                };
                modeloConsumible.addRow(fila);
            }
            TablaConsumibles.setModel(modeloConsumible);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> obtenerDatosDeColumna(JTable table) {
        List<String> datos = new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();  // Número de filas
        
        // Recorrer todas las filas del JTable
        for (int i = 0; i < rowCount; i++) {
            String valorColumna = (String) model.getValueAt(i, 0);  // Obtener el valor de la primera (y única) columna
            datos.add(valorColumna);  // Añadir el valor a la lista
        }
        
        return datos;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pisoField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tipoField = new javax.swing.JTextField();
        Reservación = new javax.swing.JLabel();
        estadoField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        numHab = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filtroTipo = new javax.swing.JComboBox<>();
        FIltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        modificarBtn = new javax.swing.JButton();
        registrarBoton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Tipo1 = new javax.swing.JTextField();
        Descripcion1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaConsumibles = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        jPanel2.setBackground(new java.awt.Color(141, 153, 174));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(45, 45, 44));
        jLabel3.setText("Tipo:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        pisoField.setBackground(new java.awt.Color(221, 221, 221));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 45, 44));
        jLabel4.setText("Piso:");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        tipoField.setBackground(new java.awt.Color(221, 221, 221));

        Reservación.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reservación.setForeground(new java.awt.Color(45, 45, 44));
        Reservación.setText("Estado:");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        estadoField.setBackground(new java.awt.Color(221, 221, 221));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(45, 45, 44));
        jLabel5.setText("Numero de habitacion: ");

        numHab.setBackground(new java.awt.Color(221, 221, 221));

        jButton1.setBackground(new java.awt.Color(239, 35, 60));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(tipoField, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addComponent(pisoField)
                    .addComponent(Reservación)
                    .addComponent(estadoField)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(numHab, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numHab, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pisoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Reservación)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(estadoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        setPreferredSize(new java.awt.Dimension(1260, 520));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tipo de combo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        filtroTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Desayuno", "Almuerzo", "Cena" }));
        jPanel1.add(filtroTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 260, -1));

        FIltrar.setBackground(new java.awt.Color(255, 127, 17));
        FIltrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FIltrar.setForeground(new java.awt.Color(255, 255, 255));
        FIltrar.setText("Filtrar");
        FIltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FIltrarActionPerformed(evt);
            }
        });
        jPanel1.add(FIltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 160, -1));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Tipo de comida", "Descripcion "
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClick(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 590, 330));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Datos del combo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, -1, -1));

        modificarBtn.setBackground(new java.awt.Color(255, 127, 17));
        modificarBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        modificarBtn.setForeground(new java.awt.Color(255, 255, 255));
        modificarBtn.setText("Modificar");
        modificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBtnActionPerformed(evt);
            }
        });
        jPanel1.add(modificarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, 260, 30));

        registrarBoton1.setBackground(new java.awt.Color(255, 127, 17));
        registrarBoton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrarBoton1.setForeground(new java.awt.Color(255, 255, 255));
        registrarBoton1.setText("Agregar nuevo combo");
        registrarBoton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBoton1ActionPerformed(evt);
            }
        });
        jPanel1.add(registrarBoton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 300, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Descripcion ");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tipo ");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 64, -1));

        Tipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tipo1ActionPerformed(evt);
            }
        });
        jPanel4.add(Tipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 380, 30));

        Descripcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Descripcion1ActionPerformed(evt);
            }
        });
        jPanel4.add(Descripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 330, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 450, 100));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaConsumibles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        TablaConsumibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Precio"
            }
        ));
        jScrollPane2.setViewportView(TablaConsumibles);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, 130));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Consumibles");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 480, 180));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 580, 20));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 430, 20));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 580, 20));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 440, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBtnActionPerformed
        Combo com = new Combo(seleccionarPedido(),Tipo1.getText(),Descripcion1.getText());
        vistaJefeCartaModificar vistaModificar = new vistaJefeCartaModificar(modeloConsumible,com);
        vistaModificar.setVisible(true);
    }//GEN-LAST:event_modificarBtnActionPerformed

    private void registrarBoton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBoton1ActionPerformed
    vistaJefeCocinaCartaAgregar vistaAgregar = new vistaJefeCocinaCartaAgregar();
    vistaAgregar.setVisible(true);
    }//GEN-LAST:event_registrarBoton1ActionPerformed

    private void MouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClick
       int id = seleccionarPedido();
        mostrarTablaConsumiblesXCombo(id);
    }//GEN-LAST:event_MouseClick

    private void FIltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FIltrarActionPerformed
        if("Ninguno".equals((String)filtroTipo.getSelectedItem())){
            JOptionPane.showMessageDialog(null, "Seleccione un filtro.", "Información", JOptionPane.INFORMATION_MESSAGE);
            
        }else{
            Combo combo = new Combo();
            List<Combo> listaCombos = new ArrayList<>();
            listaCombos = combo.obtenerComboXTipo((String) filtroTipo.getSelectedItem());
            mostrarTabla(listaCombos);
        }
    }//GEN-LAST:event_FIltrarActionPerformed

    private void Descripcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Descripcion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Descripcion1ActionPerformed

    private void Tipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tipo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tipo1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Descripcion1;
    private javax.swing.JButton FIltrar;
    private javax.swing.JLabel Reservación;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable TablaConsumibles;
    private javax.swing.JTextField Tipo1;
    private javax.swing.JTextField estadoField;
    private javax.swing.JComboBox<String> filtroTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JTextField numHab;
    private javax.swing.JTextField pisoField;
    private javax.swing.JButton registrarBoton1;
    private javax.swing.JTextField tipoField;
    // End of variables declaration//GEN-END:variables
}

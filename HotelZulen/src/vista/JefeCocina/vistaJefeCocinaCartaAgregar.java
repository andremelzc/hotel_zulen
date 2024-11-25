/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.JefeCocina;

import Persistencia.ComboConsumibleRepository;
import Persistencia.ComboRepository;
import Persistencia.ConsumibleRepository;
import Persistencia.DatabaseConnection;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Combo;
import modelo.Consumible;

/**
 *
 * @author Suyco
 */
public class vistaJefeCocinaCartaAgregar extends javax.swing.JFrame {

    DefaultTableModel modelo2;
    
    public vistaJefeCocinaCartaAgregar() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modelo2= (DefaultTableModel)TablaConsumibles.getModel();
        ConsumibleRepository consumible = new  ConsumibleRepository();
        consumible.MostrarConsumiblesEnComboBox(jComboBox1);
    }
    
    
    private void insertarTabla(String nombreConsumible){
        String sql = "SELECT * FROM consumible WHERE NombreConsumible = ?";
        try (Connection conexion = DatabaseConnection.getConnection(); // Obtener conexión
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            // Establecer el parámetro en la consulta SQL
            stmt.setString(1, nombreConsumible);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar si hay resultados
            if (rs.next()) {
                // Crear un objeto Consumible con los datos obtenidos
               Object[] fila = {
                rs.getInt("idCONSUMIBLE"),
                rs.getString("NombreConsumible"),
                (float)rs.getDouble("Precio")
                };
                modelo2.addRow(fila);
                TablaConsumibles.setModel(modelo2);
                
                
            }

        } catch (SQLException e) {
            // Manejo de excepciones
            System.err.println("Error al buscar el consumible por nombre: " + e.getMessage());
        }
        
        
    }
    
    private List<Consumible> obtenerDatosDeColumna() {
        List<Consumible> ListaConsumible = new ArrayList<>();

        int rowCount = modelo2.getRowCount();  // Número de filas
        
        // Recorrer todas las filas del JTable
        for (int i = 0; i < rowCount; i++) {
            Consumible con = new Consumible((int)modelo2.getValueAt(i, 0),
                                            (String) modelo2.getValueAt(i, 1),
                                            (float)modelo2.getValueAt(i, 2));
            ListaConsumible.add(con); 
        }
        
        return ListaConsumible;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaConsumibles = new javax.swing.JTable();
        registrarBoton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Descripcion1 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(45, 45, 44));
        jLabel2.setText("Datos del combo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 41, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 127, 17));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Registrar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 220, -1));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Quitar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 220, -1));

        TablaConsumibles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        TablaConsumibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre del consumible", "Precio"
            }
        ));
        jScrollPane2.setViewportView(TablaConsumibles);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 490, 150));

        registrarBoton1.setBackground(new java.awt.Color(255, 127, 17));
        registrarBoton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrarBoton1.setForeground(new java.awt.Color(255, 255, 255));
        registrarBoton1.setText("Crear combo");
        registrarBoton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrarBoton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBoton1ActionPerformed(evt);
            }
        });
        jPanel3.add(registrarBoton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 480, 30));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 480, 20));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 480, 20));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 480, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 229, -1, 290));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desayuno", "Almuerzo", "Cena" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 430, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(45, 45, 44));
        jLabel10.setText("Tipo");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 64, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(45, 45, 44));
        jLabel9.setText("Descripcion");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        Descripcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Descripcion1ActionPerformed(evt);
            }
        });
        jPanel1.add(Descripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 360, 30));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 480, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(45, 45, 44));
        jLabel6.setText("Consumible");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 360, 30));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 480, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Descripcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Descripcion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Descripcion1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insertarTabla((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int fila = TablaConsumibles.getSelectedRow();
        modelo2.removeRow(fila);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void registrarBoton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBoton1ActionPerformed
        if(Descripcion1.getText() != null){
            List<Consumible> ListaConsumible = new ArrayList<>();
            ComboRepository repoCombo = new ComboRepository();
            ComboConsumibleRepository repoCC = new ComboConsumibleRepository();
            int id = repoCombo.crearCombo((String) jComboBox2.getSelectedItem(), Descripcion1.getText());
            ListaConsumible = obtenerDatosDeColumna();
            repoCC.asociarComboConsumible(id, ListaConsumible);
            //MENSAJE
        }
        else{
            //MENSAJE DE QUE DEBE LLENAR TODOS LOS CAMPOS
        }

    }//GEN-LAST:event_registrarBoton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Descripcion1;
    private javax.swing.JTable TablaConsumibles;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton registrarBoton1;
    // End of variables declaration//GEN-END:variables
}

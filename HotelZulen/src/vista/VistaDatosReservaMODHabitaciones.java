/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.util.List;
import javax.swing.JFrame;
import Persistencia.DatabaseConnection;
import Persistencia.TipoHabitacionRepository;
import javax.swing.table.DefaultTableModel;
import modelo.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.TipoDeHabitacion;

/**
 *
 * @author Suyco
 */
public class VistaDatosReservaMODHabitaciones extends javax.swing.JFrame {

    DefaultTableModel modeloHab;
    
    public VistaDatosReservaMODHabitaciones(List<Habitacion> listaHab) {
        FlatArcOrangeIJTheme.setup();
        
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modeloHab = (DefaultTableModel) TablaHab.getModel();
        mostrarTabla(listaHab);
        
    }

    private void mostrarTabla(List<Habitacion> listaHab){
        for(Habitacion hab : listaHab){
            Object filas [] = 
            {
                hab.getId(),
                hab.getTipoHabitacion(),
                hab.getPiso(),
                hab.getEstado()
            };
            modeloHab.addRow(filas);
        }
        TablaHab.setModel(modeloHab);
    }
    private void RegistrarEnTabla (int tipoHabitacion){
        String sql = """
                     SELECT * FROM habitaciones 
                     WHERE TIPO_HAB_idCategoria = ? 
                     AND Estado = 'Disponible' 
                     LIMIT 1;""";
        try (Connection connection = DatabaseConnection.getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, tipoHabitacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TipoDeHabitacion tipo = new TipoHabitacionRepository().obtener(rs.getInt("TIPO_HAB_idCategoria"));
                    Object fila []= {
                        rs.getInt("idHabitaciones"),
                        tipo,
                        rs.getString("Piso"),
                        rs.getString("Estado")  
                    };
                    modeloHab.addRow(fila);
     
            }
            TablaHab.setModel(modeloHab);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaHab = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tipo de habitacion");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estandar", "Doble", "Suite", "Business" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 230, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        TablaHab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Habitacion", "Tipo", "Piso", "Estado"
            }
        ));
        jScrollPane1.setViewportView(TablaHab);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 411, 199));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 190, -1));

        jButton2.setBackground(new java.awt.Color(255, 127, 17));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 190, -1));

        jButton3.setBackground(new java.awt.Color(255, 127, 17));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Ejecutar cambios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 160, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = TablaHab.getSelectedRow();
        modeloHab.removeRow(fila);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       int intHabitacionSeleccionada = 0;
        switch ((String)jComboBox1.getSelectedItem()) {
            case "Estandar" ->
                intHabitacionSeleccionada = 1;
            case "Doble" ->
                intHabitacionSeleccionada = 2;
            case "Suite" ->
                intHabitacionSeleccionada = 3;
            case "Business" ->
                intHabitacionSeleccionada = 4;
        }
        RegistrarEnTabla(intHabitacionSeleccionada);
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null,
                            "Las habitaciones han sido registradas correctamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton3ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaHab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

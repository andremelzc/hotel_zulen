/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.JefeCocina;


import Persistencia.ComboConsumibleRepository;
import Persistencia.ReservacionHabitacionComboRepository;
import Persistencia.ComboRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import modelo.Combo;
import modelo.Consumible;


public class vistaJefeCocinaEstadisticas extends javax.swing.JPanel {
    
    DefaultTableModel model = new DefaultTableModel();
       
    /**
     * Creates new form vistaAdministradorPersonal
     */
    public vistaJefeCocinaEstadisticas() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        
        String ids[] = {"ID", "Combo", "Total Ganancias"};
        model.setColumnIdentifiers(ids); //es como q actualizar el modelo con nuevos cambios
        contentTableEst.setModel(model);
        
        //Incializar para obtener todos los pedidos de combos
        List<Combo> combos = new ArrayList<>();
        ComboRepository comborepo = new ComboRepository();
        ComboConsumibleRepository comboConsuRepo = new ComboConsumibleRepository();
        
        //obtener todos los combos de la bd
        combos = comborepo.obtenerTodosCombos();
        
        // Matriz para almacenar los datos antes de añadirlos a la tabla
        Object[][] datosCombos = new Object[combos.size()][3];
        int index = 0;
        for (Combo combo : combos){
            // Inicializar para tener todos los consumibles por id de combo
            List<Consumible> consumibles = comboConsuRepo.obtenerConsumiblesPorCombo(combo.getId());
            
            //Hallamos el precio del combo
            float precio=0;
            
            //Iteramos los consumibles del combo 
            for (Consumible consumible : consumibles) {
                    precio = precio + consumible.getPrecio();
            }
            
            System.out.println(combo.getId());
            System.out.println(combo.getDescripcion());
            System.out.println(precio);
            
            Object[] fila = {combo.getId(), combo.getDescripcion(), precio};
            datosCombos[index++] = fila;
            
            
            
        }
        
        ReservacionHabitacionComboRepository repo = new ReservacionHabitacionComboRepository();
        
        Map<Integer,Integer> estadisticas = repo.obtenerCantPedidos();
        
        for ( Object[] row : datosCombos ){
            Integer cantidad = estadisticas.get(row[0]);
            if (cantidad != null) {
                System.out.println("El total de cantidad para el combo " + row[0]+ " es: " + cantidad);
                float totalPrecio = ((Float) row[2])*cantidad;
                row[2] = totalPrecio;              
                
                
            } else {
                System.out.println("No se encontró el combo con ID " + row[0]);
               
            }
        }
        
        // Ordenar la matriz por el tercer elemento (Total Ganancias)
        Arrays.sort(datosCombos, (fila1,fila2)->{
            Float valor1 = (Float) fila1[2];
            Float valor2 = (Float) fila2[2];
            return valor2.compareTo(valor1);
        });
        
        // Añadir las filas ordenadas al modelo de la tabla
        for(Object[] row : datosCombos){
            model.addRow(row);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        cerrarSesion = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentTableEst = new javax.swing.JTable();
        title2 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        BebidasName = new javax.swing.JLabel();
        BebidasName1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cerrarSesion.setBackground(new java.awt.Color(239, 35, 60));
        cerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        background.add(cerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 660, 220, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        title1.setText("Resumen de pedidos del día");
        jPanel1.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 27, -1, -1));

        contentTableEst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Combo", "Total Ganancias"
            }
        ));
        jScrollPane1.setViewportView(contentTableEst);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 139, 499, 275));

        title2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        title2.setText("(De más a menos ganancias)");
        jPanel1.add(title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 77, -1, -1));

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

        jPanel1.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(863, 0, -1, -1));

        BebidasName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BebidasName.setText("Combos más");
        jPanel1.add(BebidasName, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 220, -1, -1));

        BebidasName1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BebidasName1.setText("pedidos");
        jPanel1.add(BebidasName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 254, -1, -1));

        background.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 2259, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BebidasName;
    private javax.swing.JLabel BebidasName1;
    private javax.swing.JPanel background;
    private javax.swing.JButton cerrarSesion;
    private javax.swing.JPanel content;
    private javax.swing.JTable contentTableEst;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    // End of variables declaration//GEN-END:variables
}

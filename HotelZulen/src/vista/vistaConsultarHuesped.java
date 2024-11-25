/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import vista.VistaDatosReserva_RA;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Huesped;
import modelo.Reservacion;

/**
 *
 * @author PC
 */
public class vistaConsultarHuesped extends javax.swing.JPanel {
    
    DefaultTableModel modelo;
    /**
     * Creates new form vistaRecepcionistaRegistrarHuespedes
     */
    public vistaConsultarHuesped() {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        modelo = (DefaultTableModel) Tabla.getModel();
        Object[] reserva = new Object[4];
        
    }

    public void consultar(){
        Reservacion reserva = new Reservacion();
        List<Reservacion> listaReserva = new ArrayList<>();
        listaReserva = reserva.obtenerXDniYEstado(Integer.parseInt(dniHuesped.getText()),(String) desplegable.getSelectedItem());
        Huesped huesped = new Huesped();
        huesped = huesped.obtenerXDni(Integer.parseInt(dniHuesped.getText()));
        nombreHuesped.setText(huesped.getNombre()+" "+huesped.getApellido());
        // Limpiar la tabla antes de agregar los nuevos datos
        modelo.setRowCount(0);
    
        // Recorrer la lista de reservas y agregar cada reserva al modelo de la tabla
        for (Reservacion r : listaReserva) {
            // Crear un array de objetos con los datos que deseas mostrar en la tabla
            Object[] reservaData = new Object[5];
            reservaData[0] = r.getIdReserva();
            reservaData[1] = r.getNumHabitaciones();
            reservaData[2] = r.getIncioHuesped();
            reservaData[3] = r.getFinHuesped();
            reservaData[4] = r.getFechaCrea();

        // Agregar la fila al modelo de la tabla
        modelo.addRow(reservaData);
    }
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        desplegable = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dniHuesped = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        nombreHuesped = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Filtro:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        jLabel2.setText("Nombre");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " idReserva", "# Habitaciones", "Fecha Inicio", "Fecha Final", "Fecha Creacion"
            }
        ));
        jScrollPane3.setViewportView(Tabla);
        if (Tabla.getColumnModel().getColumnCount() > 0) {
            Tabla.getColumnModel().getColumn(3).setResizable(false);
            Tabla.getColumnModel().getColumn(4).setResizable(false);
        }

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 1050, 240));

        desplegable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "confirmada", "vigente", "cancelada", "finalizada" }));
        add(desplegable, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 130, -1));

        jLabel3.setText("DNI del huesped: ");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jButton1.setText("Aplicar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, -1, -1));
        add(dniHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 100, -1));

        jButton2.setText("Ver Reserva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, -1, -1));
        add(nombreHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 180, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        consultar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       Reservacion reserva = new Reservacion();
       int id = reserva.seleccionarReserva(Tabla);
       System.out.println("id seleccionado: "+id);
       VistaDatosReserva_RA vistaDatos = new VistaDatosReserva_RA (id);
       vistaDatos.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JComboBox<String> desplegable;
    private javax.swing.JTextField dniHuesped;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nombreHuesped;
    // End of variables declaration//GEN-END:variables
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Huesped;

import Persistencia.ComboConsumibleRepository;
import Persistencia.ComboRepository;
import Persistencia.HabitacionRepository;
import Persistencia.ReservacionHabitacionComboRepository;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelo.Combo;
import modelo.Consumible;
import modelo.Huesped;
import modelo.Reservacion;
import vista.Recepcionista.*;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionHabitacionesRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import modelo.Habitacion;

/**
 *
 * @author PC
 */
public class vistaHuespedRoomServiceAlmuerzo extends javax.swing.JPanel {

    public Huesped huesped;
    DefaultTableModel mt = new DefaultTableModel();
    JComboBox<String> comboBox = new JComboBox<>();

    /**
     * Creates new form vistaRecepcionistaRegistrarHuespedes
     */
    public vistaHuespedRoomServiceAlmuerzo(Huesped huesped) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.huesped = huesped;

        ReservacionHuespedRepository reseHueRepo = new ReservacionHuespedRepository();
        List<Reservacion> reservaciones = reseHueRepo.obtenerReservasPorHuesped(huesped.getDNI());

        ReservacionHabitacionesRepository reseHaRepo = new ReservacionHabitacionesRepository();
        // Sacar todas las habitaciones
        List<Habitacion> habitaciones = new ArrayList<>();
        for (Reservacion reservacion : reservaciones) {
            List<Habitacion> subHabitaciones = reseHaRepo.obtenerHabitacionesPorReservacion(reservacion.getIdReserva());
            habitaciones.addAll(subHabitaciones);
        }

        System.out.println(huesped.getNombre());

        // COMBO BOX PARA SELECCIONAR RESERVA
        reservacionesCombo.removeAllItems();
        for (Reservacion rese : reservaciones) {
            reservacionesCombo.addItem(String.valueOf(rese.getIdReserva()));
        }

        // COMBO BOX PARA SELECCIONAR HABITACION
        habitacionesCombo.removeAllItems();
        for (Habitacion habi : habitaciones) {
            habitacionesCombo.addItem(String.valueOf(habi.getId()));
        }

        // PARA LAS TABLAS
        String ids[] = {"ID", "Combo", "Precio"};
        mt.setColumnIdentifiers(ids);
        System.out.println("dsada");
        tablaCombos.setModel(mt);

        // Inicializar para tener todos los combos
        List<Combo> combos = new ArrayList<>();
        System.out.println("hola");
        ComboRepository comborepo = new ComboRepository();
        ComboConsumibleRepository comboconsurepo = new ComboConsumibleRepository();

        // Todos los combos
        combos = comborepo.obtenerTodosCombos();

        for (Combo combo : combos) {

            // Solo combos del almuerzo
            if (combo.getTipoComida().equals("Almuerzo")) {

                // Inicializar para tener todos los consumibles por cada combo
                List<Consumible> consumibles = comboconsurepo.obtenerConsumiblesPorCombo(combo.getId());

                // Hallamos el precio del combo
                float precio = 0;

                // Iteramos los consumibles del combo
                for (Consumible consumible : consumibles) {
                    precio = precio + consumible.getPrecio();
                }

                System.out.println(combo.getId());
                System.out.println(combo.getDescripcion());
                System.out.println(precio);

                Object[] fila = {combo.getId(), combo.getDescripcion(), precio};
                mt.addRow(fila);
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCombos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        precioSeleccionadoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboSeleccionadoField = new javax.swing.JTextField();
        Reservación = new javax.swing.JLabel();
        reservacionesCombo = new javax.swing.JComboBox<>();
        Reservación1 = new javax.swing.JLabel();
        habitacionesCombo = new javax.swing.JComboBox<>();
        cantidadField = new javax.swing.JTextField();
        Reservación2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        pedirBoton = new javax.swing.JButton();
        borrarBoton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCombos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Combo", "Precio"
            }
        ));
        tablaCombos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCombosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCombos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, 320));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(45, 45, 44));
        jLabel2.setText("Combo");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, -1));
        jPanel1.add(precioSeleccionadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 110, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(45, 45, 44));
        jLabel3.setText("Precio");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel1.add(comboSeleccionadoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 360, 30));

        Reservación.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Reservación.setForeground(new java.awt.Color(45, 45, 44));
        Reservación.setText("Reservacion");
        Reservación.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        reservacionesCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(reservacionesCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 300, 30));

        Reservación1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Reservación1.setForeground(new java.awt.Color(45, 45, 44));
        Reservación1.setText("Habitación");
        Reservación1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        habitacionesCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(habitacionesCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 300, 30));
        jPanel1.add(cantidadField, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 100, 30));

        Reservación2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Reservación2.setForeground(new java.awt.Color(45, 45, 44));
        Reservación2.setText("Cantidad");
        Reservación2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Reservación2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 440, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 540, 210));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 45, 44));
        jLabel4.setText("Seleccione el combo que desee pedir");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, -1));

        pedirBoton.setBackground(new java.awt.Color(255, 127, 17));
        pedirBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pedirBoton.setText("Pedir");
        pedirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedirBotonActionPerformed(evt);
            }
        });
        add(pedirBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 330, 200, 30));

        borrarBoton.setBackground(new java.awt.Color(255, 127, 17));
        borrarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        borrarBoton.setText("Borrar");
        borrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBotonActionPerformed(evt);
            }
        });
        add(borrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 330, 200, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void pedirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedirBotonActionPerformed
        // TODO add your handling code here:

        int fila = tablaCombos.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tablaCombos.getModel();
        int idCombo = Integer.parseInt(model.getValueAt(fila, 0).toString());

        int cantidad = Integer.parseInt(cantidadField.getText());
        if (cantidadField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad que desea pedir del combo.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ReservacionHabitacionComboRepository rhcr = new ReservacionHabitacionComboRepository();
        int idReservacion = Integer.parseInt((String) reservacionesCombo.getSelectedItem());
        int idHabitacion = Integer.parseInt((String) habitacionesCombo.getSelectedItem());

        // Verificamos si hay relacion entre la habitacion y la reserva
        ReservacionHabitacionesRepository rhr = new ReservacionHabitacionesRepository();
        if (!(rhr.relacionHabitacionReserva(idHabitacion, idReservacion))) {
            JOptionPane.showMessageDialog(null, "La habitación escodiga no correspondo a la reserva echa");
            return;
        }

        HabitacionRepository habirepo = new HabitacionRepository();

        LocalDateTime fechaActual = LocalDateTime.now();

        Habitacion habitacion = habirepo.obtener(idHabitacion);

        //  idReserva, idHabitacion, huesped, idTipoHabitacion, idCombo, cantidad, estado, fechaPedido, fechaEnvio: SERÁ NULL
        rhcr.asociarReservacionCombo(
                idReservacion,
                idHabitacion,
                huesped,
                habitacion.getTipoHabitacion().getId(),
                idCombo,
                cantidad,
                "Pedido",
                fechaActual);
        JOptionPane.showMessageDialog(null, "Combo asociado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_pedirBotonActionPerformed

    private void borrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBotonActionPerformed
        // TODO add your handling code here:
        comboSeleccionadoField.setText("");
        precioSeleccionadoField.setText("");
    }//GEN-LAST:event_borrarBotonActionPerformed

    private void tablaCombosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCombosMouseClicked
        // TODO add your handling code here:
        int fila = tablaCombos.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tablaCombos.getModel();

        // Obtener valores
        comboSeleccionadoField.setText(model.getValueAt(fila, 1).toString());
        precioSeleccionadoField.setText(model.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_tablaCombosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Reservación;
    private javax.swing.JLabel Reservación1;
    private javax.swing.JLabel Reservación2;
    private javax.swing.JButton borrarBoton;
    private javax.swing.JTextField cantidadField;
    private javax.swing.JTextField comboSeleccionadoField;
    private javax.swing.JComboBox<String> habitacionesCombo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton pedirBoton;
    private javax.swing.JTextField precioSeleccionadoField;
    private javax.swing.JComboBox<String> reservacionesCombo;
    private javax.swing.JTable tablaCombos;
    // End of variables declaration//GEN-END:variables
}

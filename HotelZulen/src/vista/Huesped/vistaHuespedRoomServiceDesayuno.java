/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Huesped;

import Persistencia.ComboConsumibleRepository;
import Persistencia.ComboRepository;
import Persistencia.DatabaseConnection;
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
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import modelo.Habitacion;

/**
 *
 * @author PC
 */
public class vistaHuespedRoomServiceDesayuno extends javax.swing.JPanel {

    public Huesped huesped;
    DefaultTableModel mt = new DefaultTableModel();
    JComboBox<String> comboBox = new JComboBox<>();

   
    public vistaHuespedRoomServiceDesayuno(Huesped huesped) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.huesped = huesped;
        habitacionesCombo.removeAllItems();
        reservacionesCombo.removeAllItems();
        
        System.out.println(huesped.getNombre());

        // PARA LAS TABLAS
        String ids[] = {"ID", "Combo", "Precio"};
        mt.setColumnIdentifiers(ids);
        System.out.println("Tabla creada");
        tablaCombos.setModel(mt);

        CargarTablaDesayuno();
        CargarBoxReservasSegundoPlano(huesped);
    }

    private void CargarTablaDesayuno(){
        // Matriz para almacenar los datos del coste por combo
        Object[][] datosCombos = new Object[10][3];
        int index=0;
        
        String sql = "SELECT * FROM combo WHERE TipoComida = 'Desayuno'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                ComboConsumibleRepository comboconsurepo = new ComboConsumibleRepository();
                
                // Inicializar para tener todos los consumibles por cada combo
                List<Consumible> consumibles = comboconsurepo.obtenerConsumiblesPorCombo(rs.getInt("idCOMBO"));
                // Hallamos el precio del combo
                float precio = 0;
                // Iteramos los consumibles del combo
                for (Consumible consumible : consumibles) {
                    precio = precio + consumible.getPrecio();
                }
                System.out.println(rs.getInt("idCOMBO"));
                System.out.println(rs.getString("Descripcion"));
                System.out.println(precio);
                // Almacenar los valores en la matriz
                datosCombos[index][0] = rs.getInt("idCOMBO");
                datosCombos[index][1] = rs.getString("Descripcion");
                datosCombos[index][2] = precio;
                
                Object[] fila = {rs.getInt("idCOMBO"), rs.getString("Descripcion"), precio};
                mt.addRow(fila);
                tablaCombos.setModel(mt);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void CargarComboBoxReservas(Huesped huespedActual){
        String sql = "SELECT * FROM reservaciones_has_huespedes WHERE HUESPEDES_DNI = ? ";
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, huespedActual.getDNI());
            ResultSet rs = stmt.executeQuery();
            reservacionesCombo.removeAllItems();
            while (rs.next()) {
                ReservacionRepository rev = new ReservacionRepository();
                Reservacion reservacion = rev.obtenerParaRoomService(rs.getInt("RESERVACIONES_idReservaciones"));
                if(reservacion == null){
                    
                }else{
                    System.out.println("reservacion id > " + reservacion.getIdReserva());
                    reservacionesCombo.addItem(String.valueOf(reservacion.getIdReserva()));
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void CargarBoxReservasSegundoPlano(Huesped huespedActual){
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                
                CargarComboBoxReservas(huespedActual);
                // Mostrar el JOptionPane con el mensaje "Cargando completo"
                if(reservacionesCombo.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null, "No tienes reservas vigentes por el momento", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                    System.out.println("Carga completa");
                return null;
            }

            @Override
            protected void done() {
                
            }
        };
        worker.execute();
    }
    private void CargarComboBoxHabitaciones(int idReservacion){

        
        String sql = "SELECT * FROM reservaciones_has_habitaciones WHERE RESERVACIONES_idReservaciones = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReservacion);
            ResultSet rs = stmt.executeQuery();
            habitacionesCombo.removeAllItems();
            while (rs.next()) {
                habitacionesCombo.addItem(String.valueOf(rs.getInt("HABITACIONES_idHabitaciones")));
   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCombos = new javax.swing.JTable();
        pedirBoton = new javax.swing.JButton();
        borrarBoton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
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

        pedirBoton.setBackground(new java.awt.Color(255, 127, 17));
        pedirBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pedirBoton.setForeground(new java.awt.Color(255, 255, 255));
        pedirBoton.setText("Pedir");
        pedirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedirBotonActionPerformed(evt);
            }
        });
        add(pedirBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 330, 200, 30));

        borrarBoton.setBackground(new java.awt.Color(255, 127, 17));
        borrarBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        borrarBoton.setForeground(new java.awt.Color(255, 255, 255));
        borrarBoton.setText("Borrar");
        borrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBotonActionPerformed(evt);
            }
        });
        add(borrarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 330, 200, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 45, 44));
        jLabel4.setText("Seleccione el combo que desee pedir");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, -1));

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
        reservacionesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservacionesComboActionPerformed(evt);
            }
        });
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
        JOptionPane.showMessageDialog(null, "Combo asociado exitosamente.", "Éxitom", JOptionPane.INFORMATION_MESSAGE);
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

    private void reservacionesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservacionesComboActionPerformed
        if(reservacionesCombo.getSelectedItem()== null){
            
        }else{
            CargarComboBoxHabitaciones(Integer.parseInt((String) reservacionesCombo.getSelectedItem()));
        }
        
    }//GEN-LAST:event_reservacionesComboActionPerformed


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

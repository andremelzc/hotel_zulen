/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Recepcionista;

import Persistencia.DatabaseConnection;

import Persistencia.*;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;


import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import modelo.Boleta;

import modelo.Huesped;
import modelo.Recepcionista;


/**
 *
 * @author PC
 */
public class vistaRecepcionistaFacturar extends javax.swing.JPanel {

    private int huespedDNI;

    private int idReservaElegida;
    
    private int PagoCheckOut;
    
    private Recepcionista recepcionistaActual;
    
    public vistaRecepcionistaFacturar(Recepcionista recepcionista) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        this.recepcionistaActual=recepcionista;
        jTextArea1.setText("");
        jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void cargarJComboBoxDeReservas() {
    String sql = "SELECT reservaciones.idReservaciones " +
                 "FROM reservaciones_has_huespedes " +
                 "JOIN reservaciones ON reservaciones_has_huespedes.RESERVACIONES_idReservaciones = reservaciones.idReservaciones " +
                 "WHERE reservaciones_has_huespedes.HUESPEDES_DNI = ? AND reservaciones.CheckIn IS NOT NULL AND reservaciones.Estado <> 'finalizada' ";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, huespedDNI);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idReservacion = rs.getInt("idReservaciones");
            desplegableReservas.addItem(String.valueOf(idReservacion));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void cargarPrimeraCuenta() {
    String sql = """
        SELECT
            r.idReservaciones,
            h.Nombre AS NombreHuesped,
            h.Apellidos AS ApellidosHuesped,
            r.FechaInicio,
            r.FechaFinal,
            GROUP_CONCAT(DISTINCT CONCAT(
                 ha.idHabitaciones, 
                         ' ', ha.Piso, 
                         ' ', ht.Concepto, 
                         ' $', FORMAT(ht.Precio, 2)
            ) SEPARATOR '\\n') AS DetalleHabitaciones,
            GROUP_CONCAT(DISTINCT CONCAT(
                s.NombreServicio,
                ' ', FORMAT(s.Costo, 2)
            ) SEPARATOR '\\n') AS DetalleServicios
        FROM reservaciones r
        INNER JOIN reservaciones_has_huespedes rh ON r.idReservaciones = rh.RESERVACIONES_idReservaciones
        INNER JOIN huespedes h ON rh.HUESPEDES_DNI = h.DNI
        LEFT JOIN reservaciones_has_habitaciones h_id ON r.idReservaciones = h_id.RESERVACIONES_idReservaciones
        LEFT JOIN habitaciones ha ON h_id.HABITACIONES_idHabitaciones = ha.idHabitaciones
        LEFT JOIN tipo_hab ht ON ha.TIPO_HAB_idCategoria = ht.idCategoria
        LEFT JOIN reservaciones_has_servicios_adicionales rs_s ON r.idReservaciones = rs_s.RESERVACIONES_idReservaciones
        LEFT JOIN servicios_adicionales s ON rs_s.SERVICIOS_ADICIONALES_idSERVICIOS_UNICO = s.idSERVICIOS_UNICO
        WHERE r.idReservaciones = ?
        GROUP BY r.idReservaciones, h.Nombre, h.Apellidos, r.FechaInicio, r.FechaFinal;
    """;

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setInt(1, idReservaElegida);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                StringBuilder resumen = new StringBuilder();
                 // Encabezado del Hotel
                resumen.append("===========================================\n");
                resumen.append("                  Hotel Zulen\n");
                resumen.append("                  Av. Amezaga\n");
                resumen.append("               RUC: 69435678901\n");
                resumen.append("-------------------------------------------\n");
                resumen.append("               BOLETA DE VENTA\n");
                resumen.append("-------------------------------------------\n");
                
                // Información del titular y fechas
                resumen.append(String.format("Titular: %s %s\n", 
                    rs.getString("NombreHuesped"), rs.getString("ApellidosHuesped")));
                resumen.append(String.format("Fecha de Inicio: %s\n", 
                    rs.getTimestamp("FechaInicio").toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                resumen.append(String.format("Fecha de Fin: %s\n", 
                    rs.getTimestamp("FechaFinal").toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                resumen.append("-------------------------------------------\n");

               // Detalle de Habitaciones
                resumen.append("Detalle de Habitaciones\n");
                resumen.append("-------------------------------------------\n");
                resumen.append(String.format("%-5s %-5s %-10s %-8s\n", "Hab", "Piso", "Tipo", "Precio"));
                String detalleHabitaciones = rs.getString("DetalleHabitaciones");
                if (detalleHabitaciones != null) {
                    for (String habitacion : detalleHabitaciones.split("\n")) {
                        String[] parts = habitacion.split(" ");
                        resumen.append(String.format("%-5s %-5s %-10s %-8s\n", parts[0], parts[1], parts[2], parts[3]));
                    }
                } else {
                    resumen.append("No hay habitaciones asociadas a esta reserva\n");
                }
                resumen.append("-------------------------------------------\n");
                
                // Detalle de Servicios
                resumen.append("Detalle de Servicios\n");
                resumen.append("-------------------------------------------\n");
                resumen.append(String.format("%-15s %-10s\n", "Servicio", "Costo"));
                String detalleServicios = rs.getString("DetalleServicios");
                if (detalleServicios != null) {
                    for (String servicio : detalleServicios.split("\n")) {
                        String[] parts = servicio.split(" ");
                        resumen.append(String.format("%-15s %-10s\n", parts[0], parts[1]));
                    }
                } else {
                    resumen.append("No hay servicios adicionales asociados a esta reserva\n");
                }
                resumen.append("-------------------------------------------\n");
                // Totales
                resumen.append("Saldo a pagar: $0.00\n");
                resumen.append("-------------------------------------------\n");
                resumen.append("               DETALLE DE CONSUMOS\n");
                resumen.append("-------------------------------------------\n");
                // Mostrar en el JTextArea
                jTextArea1.setText(resumen.toString());
            } else {
                jTextArea1.setText("No se encontró la reserva con el ID especificado.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar la cuenta: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private int cargarCuentaEnJText(int idReservaElegida){
        int PagoCheckOut = 0;
        String encabezados = String.format(
            " %-25s %-8s %-8s %-12s %-10s\n",
            "Combo", "Hab.", "Cant", "Pre-unit", "Total"
        );
        jTextArea1.append(encabezados);
        jTextArea1.append("------------------------------------------------------------\n");

        String sql = "SELECT " +
                     "rh.RESERVA_has_HAB_HAB_idHabitaciones, " +
                     "c.Descripcion AS DescripcionCombo, " +
                     "rh.cantPedido, " +
                     "rh.FechaEnvio, " +
                     "SUM(co.Precio * rh.cantPedido) AS PrecioTotalCombo " +
                     "FROM reservaciones_has_habitaciones_has_combo rh " +
                     "JOIN combo c ON rh.COMBO_idCOMBO = c.idCOMBO " +
                     "JOIN combo_has_consumible ch ON c.idCOMBO = ch.COMBO_idCOMBO " +
                     "JOIN consumible co ON ch.CONSUMIBLE_idCONSUMIBLE = co.idCONSUMIBLE " +
                     "WHERE rh.RESERVA_has_HAB_RESERVA_idReserva = ? " +
                     "AND rh.Estado = 'Enviado' " +
                     "AND rh.FechaEnvio IS NOT NULL " +
                     "GROUP BY rh.RESERVA_has_HAB_HAB_idHabitaciones, c.Descripcion, rh.cantPedido, rh.FechaEnvio";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idReservaElegida); // Establecer el parámetro de idReserva
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int idHabitacion = rs.getInt("RESERVA_has_HAB_HAB_idHabitaciones");
                String descripcionCombo = rs.getString("DescripcionCombo");
                int cantidad = rs.getInt("cantPedido");
                LocalDateTime fechaEnvio = rs.getTimestamp("FechaEnvio").toLocalDateTime();
                double precioTotalCombo = rs.getDouble("PrecioTotalCombo");

                String fila = String.format(
                    " %-25s %-8d %-8d %-12.2f %-10.2f\n",
                    descripcionCombo, idHabitacion, cantidad, precioTotalCombo, precioTotalCombo * cantidad
                );
                PagoCheckOut = (int) (PagoCheckOut + (precioTotalCombo * cantidad)); 
                jTextArea1.append(fila);
            }
             jTextArea1.append("------------------------------------------------------------\n");
             jTextArea1.append(String.format("Total a pagar: $%.2f\n", (double) PagoCheckOut));
             jTextArea1.append(String.format("IGV (18%%):     $%.2f\n", PagoCheckOut * 0.18));
             jTextArea1.append(String.format("Total Final:   $%.2f\n", PagoCheckOut * 1.18));
             jTextArea1.append("===========================================\n");

        } catch (SQLException e) {
            System.out.println("Error al obtener los pedidos: " + e.getMessage());
        }
        return PagoCheckOut;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DNIhuesped = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        desplegableReservas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(1280, 520));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("DNI del huesped");

        DNIhuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DNIhuespedActionPerformed(evt);
            }
        });

        jLabel2.setText("Reserva");

        desplegableReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desplegableReservasActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Metodo de pago >");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Transferencia Bancaria", "Yape", "Plin" }));

        jLabel4.setText("Nombre del titular > ");

        jLabel7.setText("Saldo pendiente > ");

        jButton2.setText("Confirmar pago");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(90, 90, 90))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(desplegableReservas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DNIhuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(275, 275, 275)
                        .addComponent(jButton1)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(DNIhuesped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(desplegableReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(108, Short.MAX_VALUE))))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void DNIhuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DNIhuespedActionPerformed
        huespedDNI = Integer.parseInt(DNIhuesped.getText());
        cargarJComboBoxDeReservas();
    }//GEN-LAST:event_DNIhuespedActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jTextArea1.setText("");
       String reservaSeleccionada = (String) desplegableReservas.getSelectedItem();
       idReservaElegida = Integer.parseInt(reservaSeleccionada);
       cargarPrimeraCuenta();
       PagoCheckOut = cargarCuentaEnJText(idReservaElegida);
       jLabel10.setText(String.valueOf(PagoCheckOut));
       HuespedRepository hue = new HuespedRepository();
       Huesped huesped = new Huesped();
       huesped = hue.obtener(Integer.parseInt(DNIhuesped.getText()));
       jLabel5.setText(huesped.getNombre()+" "+huesped.getApellido());
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        Boleta boleta = new Boleta();
        boleta = recepcionistaActual.obtenerBoleta(idReservaElegida);
        boleta.setMetodoPagoCheckOut((String)jComboBox1.getSelectedItem());
        boleta.setEstadoPagoCheckOut("Pagado");
        boleta.setFechaPagoCheckOut(LocalDateTime.now());
        boleta.setPagoCheckOut((double)PagoCheckOut);
        int respuesta = JOptionPane.showConfirmDialog(
                null, 
                "¿Desea continuar con el pago?", 
                "Continuar con el pago", 
                JOptionPane.YES_NO_OPTION
            );
        if (respuesta == JOptionPane.YES_OPTION) {
            System.out.println("El usuario desea continuar con el pago.");
            recepcionistaActual.actualizarBoleta(boleta);
            JOptionPane.showMessageDialog(null, "Boleta generada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);         
            ReservacionRepository repo = new ReservacionRepository();
            repo.setFechaCheckOut(idReservaElegida);
            repo.setFinalizada(idReservaElegida);
        }            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void desplegableReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desplegableReservasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_desplegableReservasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DNIhuesped;
    private javax.swing.JComboBox<String> desplegableReservas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Recepcionista;

import Persistencia.DatabaseConnection;

import Persistencia.*;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.recepcionistaActual = recepcionista;
        jTextArea1.setText("");
        jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void cargarJComboBoxDeReservas() {
        String sql = "SELECT reservaciones.idReservaciones "
                + "FROM reservaciones_has_huespedes "
                + "JOIN reservaciones ON reservaciones_has_huespedes.RESERVACIONES_idReservaciones = reservaciones.idReservaciones "
                + "WHERE reservaciones_has_huespedes.HUESPEDES_DNI = ? AND reservaciones.CheckIn IS NOT NULL AND reservaciones.Estado <> 'finalizada' ";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
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
                    resumen.append(String.format("Titular: %s %s\n",
                            rs.getString("NombreHuesped"), rs.getString("ApellidosHuesped")));
                    resumen.append(String.format("Fecha de Inicio: %s\n",
                            rs.getTimestamp("FechaInicio").toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    resumen.append(String.format("Fecha de Fin: %s\n",
                            rs.getTimestamp("FechaFinal").toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    resumen.append("-------------------------------------------\n");
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

    private int cargarCuentaEnJText(int idReservaElegida) {
        int PagoCheckOut = 0;
        String encabezados = String.format(
                " %-15s %-8s %-8s %-12s %-10s\n",
                "Combo", "Hab.", "Cant", "Pre-unit", "Total"
        );
        jTextArea1.append(encabezados);
        jTextArea1.append("------------------------------------------------------------\n");
        String sql = "SELECT "
                + "rh.RESERVA_has_HAB_HAB_idHabitaciones, "
                + "c.Descripcion AS DescripcionCombo, "
                + "rh.cantPedido, "
                + "rh.FechaEnvio, "
                + "SUM(co.Precio * rh.cantPedido) AS PrecioTotalCombo "
                + "FROM reservaciones_has_habitaciones_has_combo rh "
                + "JOIN combo c ON rh.COMBO_idCOMBO = c.idCOMBO "
                + "JOIN combo_has_consumible ch ON c.idCOMBO = ch.COMBO_idCOMBO "
                + "JOIN consumible co ON ch.CONSUMIBLE_idCONSUMIBLE = co.idCONSUMIBLE "
                + "WHERE rh.RESERVA_has_HAB_RESERVA_idReserva = ? "
                + "AND rh.Estado = 'Enviado' "
                + "AND rh.FechaEnvio IS NOT NULL "
                + "GROUP BY rh.RESERVA_has_HAB_HAB_idHabitaciones, c.Descripcion, rh.cantPedido, rh.FechaEnvio";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReservaElegida); // Establecer el parámetro de idReserva
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idHabitacion = rs.getInt("RESERVA_has_HAB_HAB_idHabitaciones");
                String descripcionCombo = rs.getString("DescripcionCombo");
                int cantidad = rs.getInt("cantPedido");
                LocalDateTime fechaEnvio = rs.getTimestamp("FechaEnvio").toLocalDateTime();
                double precioTotalCombo = rs.getDouble("PrecioTotalCombo");

                String fila = String.format(
                        " %-15s %-8d %-8d %-12.2f %-10.2f\n",
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
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(1280, 520));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("DNI del huesped");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        DNIhuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DNIhuespedActionPerformed(evt);
            }
        });
        jPanel1.add(DNIhuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 210, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Reserva");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

        jPanel1.add(desplegableReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 290, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 779, 300));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 140, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Metodo de pago ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Transferencia Bancaria", "Yape", "Plin" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 220, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre del titular");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 183, 22));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Saldo pendiente");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 127, 17));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Confirmar pago");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 380, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 150, 16));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 455, 13));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 49, 455, 13));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, 384, 296));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 770, 13));

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
        jLabel5.setText(huesped.getNombre() + " " + huesped.getApellido());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Boleta boleta = new Boleta();
        boleta = recepcionistaActual.obtenerBoleta(idReservaElegida);
        boleta.setMetodoPagoCheckOut((String) jComboBox1.getSelectedItem());
        boleta.setEstadoPagoCheckOut("Pagado");
        boleta.setFechaPagoCheckOut(LocalDateTime.now());
        boleta.setPagoCheckOut((double) PagoCheckOut);
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
            try {
                crearArchivo(DNIhuesped.getText(), String.valueOf(desplegableReservas.getSelectedItem()), jTextArea1.getText(), "2");
                System.out.println("Boleta generada en .txt");
            } catch (IOException ex) {
                Logger.getLogger(vistaRecepcionistaFacturar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void crearArchivo(String dni, String numeroReserva, String contenido, String momento) throws IOException {
        // Define el nombre del archivo
        String nombreArchivo = "src/txt/" + dni + "_" + numeroReserva + "_" + momento + ".txt";

        // Define la ubicación donde se creará el archivo
        File archivo = new File(nombreArchivo);

        // Usa FileWriter y BufferedWriter para escribir en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(contenido); // Escribe el contenido
            System.out.println("Archivo creado con éxito: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            throw new IOException("Error al crear o escribir en el archivo: " + e.getMessage(), e);
        }
    }

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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

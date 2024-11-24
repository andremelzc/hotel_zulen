/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Recepcionista;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Boleta;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Recepcionista;
import modelo.Reservacion;
import modelo.ServiciosAdicionales;

/**
 *
 * @author PC
 */
public class vistaRecepcionistaRegistrar extends javax.swing.JPanel {

    List<Habitacion> listaHabitaciones = new ArrayList<>();
    List<ServiciosAdicionales> listaServicios = new ArrayList<>();
    List<Huesped> listaHuespedes = new ArrayList<>();
    Reservacion reservacion = new Reservacion();
    private Double PrecioTotal;
    private Recepcionista recepcionistaActual;
    
    public vistaRecepcionistaRegistrar(Recepcionista recepcionista) {
        FlatNightOwlIJTheme.setup();
        initComponents();
        this.recepcionistaActual = recepcionista;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        huespedesBoton = new javax.swing.JButton();
        subcontent = new javax.swing.JPanel();
        verRegistroBoton = new javax.swing.JButton();
        habitacionesBoton = new javax.swing.JButton();
        serviciosBoton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 520));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        huespedesBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        huespedesBoton.setText("Huéspedes");
        huespedesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huespedesBotonActionPerformed(evt);
            }
        });
        add(huespedesBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 260, 50));

        javax.swing.GroupLayout subcontentLayout = new javax.swing.GroupLayout(subcontent);
        subcontent.setLayout(subcontentLayout);
        subcontentLayout.setHorizontalGroup(
            subcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        subcontentLayout.setVerticalGroup(
            subcontentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        add(subcontent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1280, 440));

        verRegistroBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        verRegistroBoton.setText("Ver Registro");
        verRegistroBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verRegistroBotonActionPerformed(evt);
            }
        });
        add(verRegistroBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, 257, 50));

        habitacionesBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        habitacionesBoton.setText("Habitaciones");
        habitacionesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                habitacionesBotonActionPerformed(evt);
            }
        });
        add(habitacionesBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 270, 50));

        serviciosBoton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        serviciosBoton.setText("Servicios");
        serviciosBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviciosBotonActionPerformed(evt);
            }
        });
        add(serviciosBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 257, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void huespedesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huespedesBotonActionPerformed
        // TODO add your handling code here:
        vistaRecepcionistaRegistrarHuespedes recepcionistaRegistrarHuespedes = new vistaRecepcionistaRegistrarHuespedes();
        recepcionistaRegistrarHuespedes.setSize(1280, 720);
        recepcionistaRegistrarHuespedes.setLocation(0, 0);
        subcontent.removeAll();
        subcontent.add(recepcionistaRegistrarHuespedes, BorderLayout.CENTER);
        subcontent.revalidate();
        subcontent.repaint();
        listaHuespedes = recepcionistaRegistrarHuespedes.getListaHuespedes();
    }//GEN-LAST:event_huespedesBotonActionPerformed

    private void verRegistroBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verRegistroBotonActionPerformed
        // Crear instancia de la vista de recepción
        vistaRecepcionistaRegistrarVerRegistro recepcionistaRegistrarVerRegistro = new vistaRecepcionistaRegistrarVerRegistro();
        recepcionistaRegistrarVerRegistro.setSize(1280, 440);
        recepcionistaRegistrarVerRegistro.setLocation(0, 0);

        // Configurar el subcontent
        subcontent.removeAll();
        subcontent.add(recepcionistaRegistrarVerRegistro, BorderLayout.CENTER);
        subcontent.revalidate();
        subcontent.repaint();

        // Generar el resumen en la vista de recepción
        PrecioTotal =   recepcionistaRegistrarVerRegistro.generarResumen(reservacion, listaHuespedes, listaHabitaciones, listaServicios);

        // Añadir el ActionListener al botón de registrar en la vista de recepción
        recepcionistaRegistrarVerRegistro.jButtonRegistrar.addActionListener((ActionEvent evt1) -> {
            ConfirmarPago vistaConfirmar = new ConfirmarPago(listaHuespedes,PrecioTotal);
            vistaConfirmar.setVisible(true);
            // Agregar listener al botón de ConfirmarPago
            vistaConfirmar.addConfirmarPagoListener((ActionEvent evt2) -> {
                
                // Llamada a la función cuando se presiona el botón
                int resultado = JOptionPane.showConfirmDialog(null, "¿Deseas continuar=?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
                if (resultado == JOptionPane.YES_OPTION) {
                    
                    int idReserva = reservacion.crearReservacion(reservacion, listaHuespedes, listaHabitaciones, listaServicios);
                    reservacion.setIdReserva(idReserva);
                    Boleta boleta = new Boleta (reservacion,"Efectivo", PrecioTotal, "Pagado", LocalDateTime.now(), null, null, null, null);
                    recepcionistaActual.GenerarBoleta(boleta);
                    JOptionPane.showMessageDialog(null, "La reserva se ha registrado correctamente en la base de datos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "La boleta se ha generado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    
                    System.out.println("Pago confirmado");
                    vistaConfirmar.dispose(); // Cierra la ventana de ConfirmarPago
                    
                }
                else{
                    vistaConfirmar.dispose(); // Cierra la ventana de ConfirmarPago
                }
                
                
            });

            vistaConfirmar.addCancelarReservaListener((ActionEvent evt3) -> {
                System.out.println("Reserva cancelada");
                vistaConfirmar.dispose(); // Cierra la ventana de ConfirmarPago
            });
   
        });
    }//GEN-LAST:event_verRegistroBotonActionPerformed

    private void habitacionesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_habitacionesBotonActionPerformed
        // TODO add your handling code here:
        vistaRecepcionistaRegistrarHabitaciones recepcionistaRegistrarHabitaciones = new vistaRecepcionistaRegistrarHabitaciones();
        recepcionistaRegistrarHabitaciones.setSize(1280, 720);
        recepcionistaRegistrarHabitaciones.setLocation(0, 0);
        subcontent.removeAll();
        subcontent.add(recepcionistaRegistrarHabitaciones, BorderLayout.CENTER);
        subcontent.revalidate();
        subcontent.repaint();
        listaHabitaciones = recepcionistaRegistrarHabitaciones.getListaHabitaciones();
        reservacion = recepcionistaRegistrarHabitaciones.getReservacion();
    }//GEN-LAST:event_habitacionesBotonActionPerformed

    private void serviciosBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviciosBotonActionPerformed
        // TODO add your handling code here:
        vistaRecepcionistaRegistrarServicios recepcionistaRegistrarServicios = new vistaRecepcionistaRegistrarServicios();
        recepcionistaRegistrarServicios.setSize(1280, 440); // Ajusta el tamaño a 1280x440 o al tamaño de subcontent
        recepcionistaRegistrarServicios.setLocation(0, 0);
        subcontent.removeAll();
        subcontent.add(recepcionistaRegistrarServicios, BorderLayout.CENTER);
        subcontent.revalidate();
        subcontent.repaint();
        listaServicios = recepcionistaRegistrarServicios.getListaServicios();
    }//GEN-LAST:event_serviciosBotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton habitacionesBoton;
    private javax.swing.JButton huespedesBoton;
    private javax.swing.JButton serviciosBoton;
    private javax.swing.JPanel subcontent;
    private javax.swing.JButton verRegistroBoton;
    // End of variables declaration//GEN-END:variables
}

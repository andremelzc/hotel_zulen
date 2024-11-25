/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Recepcionista;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatNightOwlIJTheme;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ServiciosAdicionales;



public class vistaRecepcionistaRegistrarVerRegistro extends javax.swing.JPanel {



    public Double generarResumen(Reservacion reservacion, List<Huesped> listaHuespedes, List<Habitacion> listaHabitaciones, List<ServiciosAdicionales> listaServicios) {
        StringBuilder resumen = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        double precioTotal = 0;
        long diasDiferencia = ChronoUnit.DAYS.between(reservacion.getIncioHuesped().toLocalDate(), reservacion.getFinHuesped().toLocalDate());
        // Asumiendo que Reservacion tiene un método getTitular para obtener el nombre del titular
        resumen.append("Nombre del titular: ").append(listaHuespedes.getFirst().getNombre() + " " + listaHuespedes.getFirst().getApellido()).append("\n\n");

        resumen.append("Acompañantes:\n");
        for (int i = 1; i < listaHuespedes.size(); i++) {
            resumen.append("Acompañante").append(i).append(": ").append(listaHuespedes.get(i).getNombre() + " " + listaHuespedes.get(i).getApellido()).append("\n");
        }
        resumen.append("\n");

        resumen.append("Habitaciones:\n");
        for (Habitacion habitacion : listaHabitaciones) {
            resumen.append("NºHabitacion ").append(habitacion.getId())
                    .append(" - Piso ").append(habitacion.getPiso())
                    .append(" - Tipo ").append(habitacion.getTipoHabitacion().getConcepto())
                    .append(" - Precio ").append(habitacion.getTipoHabitacion().getPrecio()).append("\n");
            precioTotal += habitacion.getTipoHabitacion().getPrecio()*diasDiferencia;
        }
        resumen.append("\n");

        resumen.append("Servicios asignados:\n");
        for (ServiciosAdicionales servicio : listaServicios) {
            resumen.append(servicio.getConcepto()).append(" - Precio ").append(servicio.getCosto()).append("\n");
            precioTotal += servicio.getCosto();
        }
        resumen.append("\n");

        resumen.append("Fecha de inicio de la reserva: ");
        resumen.append(reservacion.getIncioHuesped().format(formatter)).append("\n");
        resumen.append("Fecha de fin de la reserva: ");
        resumen.append(reservacion.getFinHuesped().format(formatter)).append("\n");
        
        resumen.append("Duración de la reserva: ").append(diasDiferencia).append(" días\n");

        // Asumiendo que Reservacion tiene un método getPrecioTotal
        resumen.append("Precio Total: ").append(precioTotal).append("\n");

        // Establecer el texto en el JTextArea existente
        jTextAreaResumen.setText(resumen.toString());
        return precioTotal;
    }

    public vistaRecepcionistaRegistrarVerRegistro() {
        FlatArcOrangeIJTheme.setup();
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHabitacion1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaResumen = new javax.swing.JTextArea();
        jButtonRegistrar = new javax.swing.JButton();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHabitacion1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelHabitacion1.setText("Resumen de la Reserva:");
        jPanel1.add(jLabelHabitacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 270, 40));

        jSeparator2.setBackground(new java.awt.Color(75, 76, 73));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1200, 10));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        jTextAreaResumen.setColumns(20);
        jTextAreaResumen.setRows(5);
        jScrollPane2.setViewportView(jTextAreaResumen);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 410, 360));

        jButtonRegistrar.setText("Confirmar y Pagar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 310, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
                 
    }//GEN-LAST:event_jButtonRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelHabitacion1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextArea jTextAreaResumen;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import Persistencia.DatabaseConnection;
import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionRepository;
import Persistencia.ReservacionServicioRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ServiciosAdicionales;
import javax.swing.border.TitledBorder;
import java.time.temporal.ChronoUnit;
import java.util.Collections;




public class VistaDatosReserva_RA extends javax.swing.JFrame {

    private static Reservacion reservaActual;
    private static long diasReservados;
    private static LocalDateTime fechaHastaCalculado;
    private List<Habitacion> listaHabitacionActual=null;
    
    public VistaDatosReserva_RA(int idReserva) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        this.add(scrollPane);
        reservaActual = obtenerReserva(idReserva);
        mostrarDatos(reservaActual);
        // Crear un JScrollPane que envuelve al JPanel
        scrollHuesped.setViewportView(panelHuesped);
        TitledBorder titledBorder1 = BorderFactory.createTitledBorder("HUESPEDES ASIGNADOS");
        titledBorder1.setTitleJustification(TitledBorder.CENTER);
        scrollHuesped.setBorder(titledBorder1);
        TitledBorder titledBorder2 = BorderFactory.createTitledBorder("HABITACIONES ASIGNADAS");
        titledBorder2.setTitleJustification(TitledBorder.CENTER);
        scrollHabitacion.setBorder(titledBorder2);
        TitledBorder titledBorder3 = BorderFactory.createTitledBorder("SERVICIOS ASIGNADAS");
        titledBorder3.setTitleJustification(TitledBorder.CENTER);
        scrollServicios.setBorder(titledBorder3);
        imprimirHuespedesEnSegundoPlano(idReserva);
        imprimirHabitacionesEnSegundoPlano(idReserva);
        imprimirServiciosEnSegundoPlano(idReserva);
    }

    private  boolean verificarSolapamientosReservas(int idReservaciones, LocalDateTime nuevaFechaInicio, LocalDateTime nuevaFechaFin, List<Habitacion> listaHabitaciones) {
        
        String query = "SELECT h.idHabitaciones, r.idReservaciones, r.FechaInicio, r.FechaFinal " +
                       "FROM habitaciones h " +
                       "LEFT JOIN reservaciones_has_habitaciones rhh ON h.idHabitaciones = rhh.HABITACIONES_idHabitaciones " +
                       "LEFT JOIN reservaciones r ON rhh.RESERVACIONES_idReservaciones = r.idReservaciones " +
                       "WHERE h.idHabitaciones IN (" + String.join(",", Collections.nCopies(listaHabitaciones.size(), "?")) + ") " +
                       "AND r.idReservaciones != ? " +  // Excluir la propia reserva
                       "AND (r.FechaInicio <= ? AND r.FechaFinal >= ? " +
                       "OR r.FechaInicio BETWEEN ? AND ? " +
                       "OR r.FechaFinal BETWEEN ? AND ?);";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder mensajeSolapamientos = new StringBuilder();  // Para acumular los solapamientos
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            // Establecer parámetros para las habitaciones (69 y 88)
            int paramIndex = 1;
            for (Habitacion hab : listaHabitaciones) {
                stmt.setInt(paramIndex++, hab.getId());
            }
            
            // Establecer el ID de la reserva que estamos modificando
            stmt.setInt(paramIndex++, idReservaciones);
            
            // Establecer las fechas de la nueva reserva
            stmt.setObject(paramIndex++, nuevaFechaInicio);
            stmt.setObject(paramIndex++, nuevaFechaFin);
            stmt.setObject(paramIndex++, nuevaFechaInicio);
            stmt.setObject(paramIndex++, nuevaFechaFin);
            stmt.setObject(paramIndex++, nuevaFechaInicio);
            stmt.setObject(paramIndex++, nuevaFechaFin);
            
            ResultSet rs = stmt.executeQuery();
            
            // Verificar los resultados
            boolean haySolapamientos = false;
            while (rs.next()) {
                int habitacionId = rs.getInt("idHabitaciones");
                int reservaId = rs.getInt("idReservaciones");
                LocalDateTime fechaInicioReserva = rs.getObject("FechaInicio", LocalDateTime.class);
                LocalDateTime fechaFinReserva = rs.getObject("FechaFinal", LocalDateTime.class);
                
                // Acumular la información del solapamiento
                haySolapamientos = true;
                mensajeSolapamientos.append("La habitación ").append(habitacionId)
                                     .append(" está ocupada por la reserva ").append(reservaId)
                                     .append(" desde ").append(fechaInicioReserva.format(formatter))
                                     .append(" hasta ").append(fechaFinReserva.format(formatter)).append("\n");
            }
            
            if (haySolapamientos) {
                
                JOptionPane.showMessageDialog(null, mensajeSolapamientos.toString(), "Solapamientos Encontrados", JOptionPane.WARNING_MESSAGE);
                return true;
            } else {
                
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void MeterPagoAdicional(int idReservacion, double montoAdicional) {
    String sql = "UPDATE pago SET PagoCheckOut = COALESCE(PagoCheckOut, 0) + ? WHERE RESERVA_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();  // Asegúrate de tener un método para obtener tu conexión
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        // Configurar los parámetros del SQL
        stmt.setDouble(1, montoAdicional); // Monto adicional que deseas agregar
        stmt.setInt(2, idReservacion);    // ID de la reserva
        
        // Ejecutar la consulta
        int filasActualizadas = stmt.executeUpdate();
        
        if (filasActualizadas > 0) {
            System.out.println("Pago adicional registrado correctamente para la reserva: " + idReservacion);
        } else {
            System.out.println("No se encontró la reserva con ID: " + idReservacion);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error al actualizar el pago adicional.");
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Desde = new javax.swing.JTextField();
        Hasta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        NumHabitaciones = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        CheckIN = new javax.swing.JLabel();
        CheckOut = new javax.swing.JLabel();
        FechaCreacion = new javax.swing.JLabel();
        Titular = new javax.swing.JLabel();
        CheckIn = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        scrollHuesped = new javax.swing.JScrollPane();
        panelHuesped = new javax.swing.JPanel();
        scrollHabitacion = new javax.swing.JScrollPane();
        panelHabitacion = new javax.swing.JPanel();
        scrollServicios = new javax.swing.JScrollPane();
        panelServicios = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Datos de la Reserva");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel5.setText("Titular: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 43, -1, -1));

        jLabel6.setText("Fecha de creacion: ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 65, -1, -1));

        jLabel7.setText("Desde : ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 11, -1, -1));

        jLabel8.setText("Hasta: ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 44, -1, -1));

        Desde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesdeActionPerformed(evt);
            }
        });
        jPanel2.add(Desde, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 6, 122, -1));
        jPanel2.add(Hasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 127, -1));

        jLabel9.setText("Número de habitaciones: ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 87, -1, -1));
        jPanel2.add(NumHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 87, 38, 16));

        jLabel11.setText("CheckIn: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 82, -1, -1));

        jLabel12.setText("CheckOut: ");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));
        jPanel2.add(CheckIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 82, 115, 16));
        jPanel2.add(CheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 117, 16));
        jPanel2.add(FechaCreacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 65, 130, 16));
        jPanel2.add(Titular, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 43, 135, 16));

        CheckIn.setBackground(new java.awt.Color(255, 127, 17));
        CheckIn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CheckIn.setForeground(new java.awt.Color(255, 255, 255));
        CheckIn.setText("CheckIn");
        CheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckInActionPerformed(evt);
            }
        });
        jPanel2.add(CheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 130, -1));

        Modificar.setBackground(new java.awt.Color(255, 127, 17));
        Modificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Modificar.setForeground(new java.awt.Color(255, 255, 255));
        Modificar.setText("Ampliar reserva");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        jPanel2.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 130, -1));

        jLabel1.setText("Dias reservados:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 60, 20));

        jButton1.setBackground(new java.awt.Color(255, 127, 17));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Restablecer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

        panelHuesped.setBackground(new java.awt.Color(255, 255, 255));
        panelHuesped.setLayout(new javax.swing.BoxLayout(panelHuesped, javax.swing.BoxLayout.Y_AXIS));
        scrollHuesped.setViewportView(panelHuesped);

        panelHabitacion.setBackground(new java.awt.Color(255, 255, 255));
        panelHabitacion.setLayout(new javax.swing.BoxLayout(panelHabitacion, javax.swing.BoxLayout.Y_AXIS));
        scrollHabitacion.setViewportView(panelHabitacion);

        panelServicios.setBackground(new java.awt.Color(255, 255, 255));
        panelServicios.setLayout(new javax.swing.BoxLayout(panelServicios, javax.swing.BoxLayout.Y_AXIS));
        scrollServicios.setViewportView(panelServicios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
            .addComponent(scrollHabitacion)
            .addComponent(scrollHuesped)
            .addComponent(scrollServicios)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckInActionPerformed
        int resultado = JOptionPane.showConfirmDialog(null, "¿Deseas continuar=?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (resultado == JOptionPane.YES_OPTION) {
            System.out.println("El recepcionista hizo checkIn a un huesped.");
            reservaActual.setCheckIn();
            reservaActual.actualizarCheckIn(reservaActual);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            CheckIN.setText(reservaActual.getCheckIn().format(formatter));
            CheckIn.setEnabled(false);
            reservaActual.setVigente(reservaActual);
            System.out.println("estado de la reserva seteada a 'vigente'");
            JOptionPane.showMessageDialog(null, "Se registro el CheckIn en la BD correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            
        }
    }//GEN-LAST:event_CheckInActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // Construir el mensaje detallado
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Detalle de costos adicionales por ampliación:\n\n");
        double costoTotalAdicional = 0.0;
        ReservacionRepository repoRev = new  ReservacionRepository ();
        System.out.println("MODIFICANDO RESERVA");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(reservaActual.getCheckIn()!=null && reservaActual.getCheckOut()!=null){
            //No pasa nada aqui poke el boton esta deshabilitado
        }
        //El jField DESDE esta deshabilitado
        else if(reservaActual.getCheckIn()!=null && reservaActual.getCheckOut()==null){
            //compara el texto con la reserva final de la base de datos
            if(LocalDateTime.parse(Hasta.getText(), formatter).equals(reservaActual.getFinHuesped())){
                JOptionPane.showMessageDialog(null, "Ampliacion cancelada, misma fecha final", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
            if(LocalDateTime.parse(Hasta.getText(), formatter).isBefore(reservaActual.getFinHuesped())){
                JOptionPane.showMessageDialog(null, "Ampliacion cancelada, la fecha a elegir no puede ser antes de la fecha original", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
            if(LocalDateTime.parse(Hasta.getText(), formatter).isAfter(reservaActual.getFinHuesped())){
                boolean solapa = verificarSolapamientosReservas(reservaActual.getIdReserva(),reservaActual.getIncioHuesped(), LocalDateTime.parse(Hasta.getText(), formatter), listaHabitacionActual);
                
                if(!solapa){
                    long diasAdicionales =  ChronoUnit.DAYS.between(reservaActual.getFinHuesped(), LocalDateTime.parse(Hasta.getText(), formatter));
                    
                     // Iterar sobre la lista de habitaciones de la reserva actual
                    for (Habitacion habitacion : listaHabitacionActual) {
                        double precioPorDia = habitacion.getTipoHabitacion().getPrecio();
                        double costoAdicional = diasAdicionales * precioPorDia;
                        costoTotalAdicional += costoAdicional;

                        // Agregar información de la habitación al mensaje
                        mensaje.append("Habitación: ").append(habitacion.getId())
                               .append("\nTipo: ").append(habitacion.getTipoHabitacion().getConcepto())
                               .append("\nPrecio por día: $").append(precioPorDia)
                               .append("\nCosto adicional: $").append(costoAdicional).append("\n\n");
                    }
                    // Agregar el costo total al mensaje
                    mensaje.append("Costo total adicional: $").append(costoTotalAdicional);
                    
                    
                    int opcion = JOptionPane.showConfirmDialog(
                        null,                          // Componente padre (null para no tener ventana principal)
                        mensaje.toString(),  // Mensaje que se mostrará
                        "Confirmación",                // Título del cuadro de diálogo
                        JOptionPane.YES_NO_OPTION,      // Tipo de opciones (Sí, No)
                        JOptionPane.QUESTION_MESSAGE    // Tipo de mensaje (Pregunta)
                    );
                    // Evaluar la opción seleccionada
                    if (opcion == JOptionPane.YES_OPTION) {
                        System.out.println("Seleccionaste Sí");
                        reservaActual.setFinHuesped(LocalDateTime.parse(Hasta.getText(), formatter));
                        repoRev.actualizar(reservaActual);
                        MeterPagoAdicional(reservaActual.getIdReserva(), costoTotalAdicional);
                        JOptionPane.showMessageDialog(null, "Reserva modificada", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                    } else if (opcion == JOptionPane.NO_OPTION) {
                        System.out.println("Seleccionaste No");
                    } else {
                        System.out.println("Ninguna opción seleccionada o el cuadro de diálogo fue cerrado");
                    }
                    
                }
                
            }
            
        }
        //AQUI EL DESDE no esta deshabilitado
        else{
            //ACA NO TOCARON EL DESDE
            if(LocalDateTime.parse(Desde.getText(),formatter).equals(reservaActual.getIncioHuesped())){
                
                if(LocalDateTime.parse(Hasta.getText(), formatter).equals(reservaActual.getFinHuesped())){
                JOptionPane.showMessageDialog(null, "Ampliacion cancelada, misma fecha final", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                if(LocalDateTime.parse(Hasta.getText(), formatter).isBefore(reservaActual.getFinHuesped())){
                    JOptionPane.showMessageDialog(null, "Ampliacion cancelada, la fecha a elegir no puede ser antes de la fecha original", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                if(LocalDateTime.parse(Hasta.getText(), formatter).isAfter(reservaActual.getFinHuesped())){

                    boolean solapa = verificarSolapamientosReservas(reservaActual.getIdReserva(),reservaActual.getIncioHuesped(), LocalDateTime.parse(Hasta.getText(), formatter), listaHabitacionActual);
                    
                    if(!solapa){
                        long diasAdicionales =  ChronoUnit.DAYS.between(reservaActual.getFinHuesped(), LocalDateTime.parse(Hasta.getText(), formatter));
                        
                         // Iterar sobre la lista de habitaciones de la reserva actual
                        for (Habitacion habitacion : listaHabitacionActual) {
                            double precioPorDia = habitacion.getTipoHabitacion().getPrecio();
                            double costoAdicional = diasAdicionales * precioPorDia;
                            costoTotalAdicional += costoAdicional;

                            // Agregar información de la habitación al mensaje
                            mensaje.append("Habitación: ").append(habitacion.getId())
                                   .append("\nTipo: ").append(habitacion.getTipoHabitacion().getConcepto())
                                   .append("\nPrecio por día: $").append(precioPorDia)
                                   .append("\nCosto adicional: $").append(costoAdicional).append("\n\n");
                        }
                        // Agregar el costo total al mensaje
                        mensaje.append("Costo total adicional: $").append(costoTotalAdicional);
                    
                        
                        int opcion = JOptionPane.showConfirmDialog(
                        null,                          // Componente padre (null para no tener ventana principal)
                        mensaje.toString(),  // Mensaje que se mostrará
                        "Confirmación",                // Título del cuadro de diálogo
                        JOptionPane.YES_NO_OPTION,      // Tipo de opciones (Sí, No)
                        JOptionPane.QUESTION_MESSAGE    // Tipo de mensaje (Pregunta)
                    );
                    // Evaluar la opción seleccionada
                    if (opcion == JOptionPane.YES_OPTION) {
                        System.out.println("Seleccionaste Sí");
                        reservaActual.setFinHuesped(LocalDateTime.parse(Hasta.getText(), formatter));
                        repoRev.actualizar(reservaActual);
                        MeterPagoAdicional(reservaActual.getIdReserva(), costoTotalAdicional);
                        JOptionPane.showMessageDialog(null, "Reserva modificada", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                    } else if (opcion == JOptionPane.NO_OPTION) {
                        System.out.println("Seleccionaste No");
                    } else {
                        System.out.println("Ninguna opción seleccionada o el cuadro de diálogo fue cerrado");
                    }
                    }
                    
                }
            }//ACA TOCAN EL DESDE
            else{
                
                int hora = fechaHastaCalculado.getHour();
                int minuto = fechaHastaCalculado.getMinute();
                int segundo = fechaHastaCalculado.getSecond();
                LocalDateTime campoHastaBuscar =  LocalDateTime.parse(Hasta.getText(), formatter);
                LocalDateTime campoDesdeModificadoSIoSI = LocalDateTime.parse(Desde.getText(), formatter);
                
                if(ChronoUnit.DAYS.between(campoDesdeModificadoSIoSI, campoHastaBuscar)>=diasReservados){
                    boolean solapa = verificarSolapamientosReservas(reservaActual.getIdReserva(),LocalDateTime.parse(Desde.getText(),formatter), LocalDateTime.parse(Hasta.getText(), formatter), listaHabitacionActual);
                    
                    
                    if(!solapa){
                        long diasAdicionales =  ChronoUnit.DAYS.between(campoDesdeModificadoSIoSI, campoHastaBuscar)-diasReservados;
                         // Iterar sobre la lista de habitaciones de la reserva actual
                        for (Habitacion habitacion : listaHabitacionActual) {
                            double precioPorDia = habitacion.getTipoHabitacion().getPrecio();
                            double costoAdicional = diasAdicionales * precioPorDia;
                            costoTotalAdicional += costoAdicional;

                            // Agregar información de la habitación al mensaje
                            mensaje.append("Habitación: ").append(habitacion.getId())
                                   .append("\nTipo: ").append(habitacion.getTipoHabitacion().getConcepto())
                                   .append("\nPrecio por día: $").append(precioPorDia)
                                   .append("\nCosto adicional: $").append(costoAdicional).append("\n\n");
                        }
                        // Agregar el costo total al mensaje
                        mensaje.append("Costo total adicional: $").append(costoTotalAdicional);
                        int opcion = JOptionPane.showConfirmDialog(
                        null,                          // Componente padre (null para no tener ventana principal)
                        mensaje.toString(),  // Mensaje que se mostrará
                        "Confirmación",                // Título del cuadro de diálogo
                        JOptionPane.YES_NO_OPTION,      // Tipo de opciones (Sí, No)
                        JOptionPane.QUESTION_MESSAGE    // Tipo de mensaje (Pregunta)
                    );
                    // Evaluar la opción seleccionada
                    if (opcion == JOptionPane.YES_OPTION) {
                        System.out.println("Seleccionaste Sí");
                        reservaActual.setIncioHuesped(LocalDateTime.parse(Desde.getText(),formatter));
                        reservaActual.setFinHuesped(LocalDateTime.parse(Hasta.getText(), formatter));
                        repoRev.actualizar(reservaActual);
                        MeterPagoAdicional(reservaActual.getIdReserva(), costoTotalAdicional);
                        JOptionPane.showMessageDialog(null, "Reserva modificada", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                    } else if (opcion == JOptionPane.NO_OPTION) {
                        System.out.println("Seleccionaste No");
                    } else {
                        System.out.println("Ninguna opción seleccionada o el cuadro de diálogo fue cerrado");
                    }
                    }
                    
                    
                }
                else{
                    System.out.println(ChronoUnit.DAYS.between(campoDesdeModificadoSIoSI, campoHastaBuscar));
                    JOptionPane.showMessageDialog(null, "ERROR, la reserva no puede contener menos de "+diasReservados+" dias", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
              
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void DesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesdeActionPerformed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Obtener la fecha actual del campo "Hasta" (para extraer la hora, minuto y segundo)
        LocalDateTime fechaHastaAnterior = LocalDateTime.parse(Hasta.getText(), formatter); 
         // Obtener la fecha actual del campo "Desde"
        LocalDateTime fechaDesdeActual = LocalDateTime.parse(Desde.getText(), formatter);
        // Extraer la hora, minuto y segundo de "Hasta"
        int hora = fechaHastaAnterior.getHour();
        int minuto = fechaHastaAnterior.getMinute();
        int segundo = fechaHastaAnterior.getSecond();
        
        fechaHastaCalculado = fechaDesdeActual.plusDays(diasReservados)
            .withHour(hora)
            .withMinute(minuto)
            .withSecond(segundo);
        
        if(fechaHastaCalculado.equals(fechaHastaAnterior)){
            Modificar.setEnabled(false);
        }else{
            Modificar.setEnabled(true);
            // Actualizar el campo "Hasta" con la nueva fecha
            Hasta.setText(fechaHastaCalculado.format(formatter));
            JOptionPane.showMessageDialog(null, "Fecha final de la reserva calculada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_DesdeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Desde.setText(reservaActual.getIncioHuesped().format(formatter));
        Hasta.setText(reservaActual.getFinHuesped().format(formatter));
                
    }//GEN-LAST:event_jButton1ActionPerformed
    private Reservacion obtenerReserva(int idReserva){
        Reservacion reserva = new Reservacion();
        return reserva.obtenerReserva(idReserva);
    }
    private JPanel crearPanelHuesped(Huesped huesped) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    if(huesped.getEsTitular()){
        panel.setBorder(BorderFactory.createTitledBorder("Titular"));

        // Campos para el nombre
        JPanel panelNombre = new JPanel();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(huesped.getNombre(), 20);
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);
        panel.add(panelNombre);

        // Campos para el DNI
        JPanel panelDni = new JPanel();
        JLabel lblDni = new JLabel("DNI:");
        JTextField txtDni = new JTextField(String.valueOf(huesped.getDNI()), 20);
        txtDni.setEditable(false);
        panelDni.add(lblDni);
        panelDni.add(txtDni);
        panel.add(panelDni);

        // Campos para el correo
        JPanel panelCorreo = new JPanel();
        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(huesped.getDireccion(), 20);
        panelCorreo.add(lblCorreo);
        panelCorreo.add(txtCorreo);
        panel.add(panelCorreo);

        // Campos para la dirección (Telefono)
        JPanel panelDireccion = new JPanel();
        JLabel lblDireccion = new JLabel("Telefono:");
        JTextField txtDireccion = new JTextField(String.valueOf(huesped.getTelefono()), 20);
        panelDireccion.add(lblDireccion);
        panelDireccion.add(txtDireccion);
        panel.add(panelDireccion);

        // Botón "Modificar"
        JButton btnModificar = new JButton("Modificar");
        panel.add(btnModificar);

        // Acción del botón "Modificar"
        btnModificar.addActionListener(e -> {
           Huesped hue = new Huesped();
           hue.actualizar(huesped);
           JOptionPane.showMessageDialog(null, "Datos actualizados para " + huesped.getNombre(),"Éxito",JOptionPane.INFORMATION_MESSAGE);
        });

        return panel;
    }
    else{
        panel.setBorder(BorderFactory.createTitledBorder("Huésped"));

        // Campos para el nombre
        JPanel panelNombre = new JPanel();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(huesped.getNombre(), 20);
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);
        panel.add(panelNombre);

        // Campos para el DNI
        JPanel panelDni = new JPanel();
        JLabel lblDni = new JLabel("DNI:");
        JTextField txtDni = new JTextField(String.valueOf(huesped.getDNI()), 20);
        txtDni.setEditable(false);
        panelDni.add(lblDni);
        panelDni.add(txtDni);
        panel.add(panelDni);

        // Campos para el correo
        JPanel panelCorreo = new JPanel();
        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(huesped.getDireccion(), 20);
        panelCorreo.add(lblCorreo);
        panelCorreo.add(txtCorreo);
        panel.add(panelCorreo);

        // Campos para la dirección (Telefono)
        JPanel panelDireccion = new JPanel();
        JLabel lblDireccion = new JLabel("Telefono:");
        JTextField txtDireccion = new JTextField(String.valueOf(huesped.getTelefono()), 20);
        panelDireccion.add(lblDireccion);
        panelDireccion.add(txtDireccion);
        panel.add(panelDireccion);

        // Botón "Modificar"
        JButton btnModificar = new JButton("Modificar");
        panel.add(btnModificar);

        // Acción del botón "Modificar"
        btnModificar.addActionListener(e -> {
           JOptionPane.showMessageDialog(null, "Datos actualizados para " + huesped.getNombre());
        });

        return panel;
    }
}
    private JPanel crearPanelHabitacion (Habitacion hab){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Habitación "+hab.getId()));

        
        // Campos para el Numero de habitacion
        JPanel panelNumHab = new JPanel();
        JLabel lblNum = new JLabel("Numero de habitacion:");
        JTextField txtNum = new JTextField(String.valueOf(hab.getId()), 20);
        txtNum.setEditable(false);
        panelNumHab.add(lblNum);
        panelNumHab.add(txtNum);
        panel.add(panelNumHab);
        
        // Campos para el tipo de Habitacion
        JPanel panelTipoHab = new JPanel();
        JLabel lblNombre = new JLabel("Tipo habitacion:");
        JTextField txtNombre = new JTextField(hab.getTipoHabitacion().getConcepto(), 20);
        txtNombre.setEditable(false);
        panelTipoHab.add(lblNombre);
        panelTipoHab.add(txtNombre);
        panel.add(panelTipoHab);

        // Campos para el Piso
        JPanel panelPiso = new JPanel();
        JLabel lblPiso = new JLabel("Piso:");
        JTextField txtPiso = new JTextField(String.valueOf(hab.getPiso()), 20);
        txtPiso.setEditable(false);
        panelPiso.add(lblPiso);
        panelPiso.add(txtPiso);
        panel.add(panelPiso);

        
        return panel;
        
        
    }
    private JPanel crearPanelServicios (ServiciosAdicionales servicio){
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Servicio"));

        
        // Campos para el concepto
        JPanel panelNumHab = new JPanel();
        JLabel lblNum = new JLabel("Concepto:");
        JTextField txtNum = new JTextField(servicio.getConcepto(), 20);
        txtNum.setEditable(false);
        panelNumHab.add(lblNum);
        panelNumHab.add(txtNum);
        panel.add(panelNumHab);
        
        // Campos para el costo
        JPanel panelTipoHab = new JPanel();
        JLabel lblNombre = new JLabel("Costo:");
        JTextField txtNombre = new JTextField(String.valueOf(servicio.getCosto()), 20);
        txtNombre.setEditable(false);
        panelTipoHab.add(lblNombre);
        panelTipoHab.add(txtNombre);
        panel.add(panelTipoHab);

        return panel;
        
    }
    private void mostrarDatos(Reservacion reserva){
        Huesped huespedTitular = new Huesped();
        
        diasReservados = ChronoUnit.DAYS.between(reserva.getIncioHuesped(), reserva.getFinHuesped());
        jLabel3.setText(String.valueOf(diasReservados));
        
        huespedTitular = huespedTitular.obtenerHuespedTitularXidReserva(reserva.getIdReserva());
        Titular.setText(huespedTitular.getNombre()+" "+huespedTitular.getApellido());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        NumHabitaciones.setText(String.valueOf(reserva.getNumHabitaciones()));
        Desde.setText(reserva.getIncioHuesped().format(formatter));
        Hasta.setText(reserva.getFinHuesped().format(formatter));
        FechaCreacion.setText(reserva.getFechaCrea().format(formatter));
        System.out.println("check in = "+reserva.getCheckIn()+"\n"+"checkout "+reserva.getCheckOut());
        if(reserva.getCheckIn()!=null && reserva.getCheckOut()!=null){
            CheckIN.setText(reserva.getCheckIn().format(formatter));
            CheckIn.setEnabled(false);
            Modificar.setEnabled(false);
            Desde.setEditable(false);
            Hasta.setEditable(false);
            Modificar.setEnabled(false);
            CheckOut.setText(reserva.getCheckOut().format(formatter));
        }
        else if(reserva.getCheckIn()!=null && reserva.getCheckOut()==null){
            CheckIN.setText(reserva.getCheckIn().format(formatter));
            CheckIn.setEnabled(false);
            Desde.setEditable(false);
            CheckOut.setText("------");
        }
        else{
              CheckIN.setText("------");
              CheckOut.setText("------");
        }
    }
    private void ImprimirHuespedes(int idReserva){
        ReservacionHuespedRepository repoReservaHuesped = new ReservacionHuespedRepository(); 
        List<Huesped > listaHuesped = repoReservaHuesped.obtenerHuespedesPorReserva(idReserva);
        System.out.println("Cantidad de huespedes encontrados: " + listaHuesped.size());
        panelHuesped.setLayout((new BoxLayout(panelHuesped, BoxLayout.Y_AXIS)));
        panelHuesped.setBorder(BorderFactory.createTitledBorder("HUESPEDES ASIGNADOS"));
        for (Huesped huesped : listaHuesped) {
            JPanel panelIndividual = crearPanelHuesped(huesped);
            panelHuesped.add(panelIndividual);
            panelHuesped.revalidate(); 
            panelHuesped.repaint();
        }
    }
    private void ImprimirHabitaciones(int idReserva){
        ReservacionHabitacionesRepository repoReservaHab = new ReservacionHabitacionesRepository(); 
        listaHabitacionActual = repoReservaHab.obtenerHabitacionesPorReservacion(idReserva);
        System.out.println("Cantidad de servicios encontrados: " + listaHabitacionActual.size());
        panelHabitacion.setLayout((new BoxLayout(panelHabitacion, BoxLayout.Y_AXIS)));
        panelHabitacion.setBorder(BorderFactory.createTitledBorder("HABITACIONES ASIGNADAS"));
        
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.X_AXIS));
        
        JButton botonModificar = new JButton("Modificar registros");
        
        botonModificar.addActionListener(e -> {
            VistaDatosReservaMODHabitaciones vistaMod = new VistaDatosReservaMODHabitaciones(listaHabitacionActual);
            vistaMod.setVisible(true);
            System.out.println("Botón 'Modificar registros' presionado.");
            
        });
        panelBtn.add(botonModificar);
        panelHabitacion.add(panelBtn);
        
        for (Habitacion habitacion : listaHabitacionActual) {
            JPanel panelIndividual = crearPanelHabitacion(habitacion);
            panelHabitacion.add(panelIndividual);
            panelHabitacion.revalidate(); // Reorganiza el diseño
            panelHabitacion.repaint();
        }
    }
    private void ImprimirServicios(int idReserva){
        ReservacionServicioRepository repoReservaServicio = new ReservacionServicioRepository();
        List<ServiciosAdicionales> listaServicios =repoReservaServicio.obtenerServiciosXIdReserva(idReserva);
        System.out.println("Cantidad de servicios encontrados: " + listaServicios.size());
        panelServicios.setLayout((new BoxLayout(panelServicios, BoxLayout.Y_AXIS)));        
        panelServicios.setBorder(BorderFactory.createTitledBorder("SERVICIOS ASIGNADAS"));
        
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.X_AXIS));
        
        JButton botonModificar = new JButton("Modificar registros");
        
        botonModificar.addActionListener(e -> {
            System.out.println("Botón 'Modificar registros' presionado.");
            VistaDatosReservaMODServicios vistaMod = new VistaDatosReservaMODServicios(listaServicios);
            vistaMod.setVisible(true);
        });
        panelBtn.add(botonModificar);
        panelServicios.add(panelBtn);
        
        for (ServiciosAdicionales servicios : listaServicios) {
            JPanel panelIndividual = crearPanelServicios(servicios);
            panelServicios.add(panelIndividual);
            panelServicios.revalidate(); // Reorganiza el diseño
            panelServicios.repaint();
        }
        
    }
    private void imprimirHuespedesEnSegundoPlano(int idReserva) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Paso 2: Imprimir los huéspedes
                ImprimirHuespedes(idReserva);
                return null;
            }

            @Override
            protected void done() {
                // Esta tarea es independiente, no depende de la reserva, y se ejecuta en paralelo
            }
        };
        worker.execute();
    }
    private void imprimirHabitacionesEnSegundoPlano(int idReserva) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Paso 3: Imprimir las habitaciones
                ImprimirHabitaciones(idReserva);
                
                return null;
            }

            @Override
            protected void done() {
                // Esta tarea también se ejecuta en paralelo
            }
        };
        worker.execute();
    }
    private void imprimirServiciosEnSegundoPlano(int idReserva) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Paso 4: Imprimir los servicios
                ImprimirServicios(idReserva);
                return null;
            }

            @Override
            protected void done() {
                // Esta tarea también se ejecuta en paralelo
            }
        };
        worker.execute();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CheckIN;
    private javax.swing.JButton CheckIn;
    private javax.swing.JLabel CheckOut;
    private javax.swing.JTextField Desde;
    private javax.swing.JLabel FechaCreacion;
    private javax.swing.JTextField Hasta;
    private javax.swing.JButton Modificar;
    private javax.swing.JLabel NumHabitaciones;
    private javax.swing.JLabel Titular;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelHabitacion;
    private javax.swing.JPanel panelHuesped;
    private javax.swing.JPanel panelServicios;
    private javax.swing.JScrollPane scrollHabitacion;
    private javax.swing.JScrollPane scrollHuesped;
    private javax.swing.JScrollPane scrollServicios;
    // End of variables declaration//GEN-END:variables
}

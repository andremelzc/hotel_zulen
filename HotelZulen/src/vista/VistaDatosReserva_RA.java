/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionServicioRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
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
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ServiciosAdicionales;

public class VistaDatosReserva_RA extends javax.swing.JFrame {

    private static Reservacion reservaActual;
    
    public VistaDatosReserva_RA(int idReserva) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        this.add(scrollPane);
        reservaActual = obtenerReserva(idReserva);
        mostrarDatos(reservaActual);
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
        scrollHuesped = new javax.swing.JScrollPane();
        panelHuesped = new javax.swing.JPanel();
        scrollHabitacion = new javax.swing.JScrollPane();
        panelHabitacion = new javax.swing.JPanel();
        scrollServicios = new javax.swing.JScrollPane();
        panelServicios = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 127, 17)));
        jPanel2.setForeground(new java.awt.Color(255, 51, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Datos de la Reserva");

        jLabel5.setText("Titular: ");

        jLabel6.setText("Fecha de creacion: ");

        jLabel7.setText("Desde : ");

        jLabel8.setText("Hasta: ");

        Desde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesdeActionPerformed(evt);
            }
        });

        jLabel9.setText("Número de habitaciones: ");

        jLabel11.setText("CheckIn: ");

        jLabel12.setText("CheckOut: ");

        CheckIn.setText("CheckIn");
        CheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckInActionPerformed(evt);
            }
        });

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(CheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Modificar)
                            .addComponent(NumHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Titular, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FechaCreacion, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)))
                        .addGap(35, 35, 35)))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(14, 14, 14))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(Desde, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(Hasta, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                        .addContainerGap(37, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CheckIN, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(Titular, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CheckIN, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NumHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Modificar)
                            .addComponent(CheckIn))))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        panelHuesped.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 127, 17)));
        panelHuesped.setLayout(new javax.swing.BoxLayout(panelHuesped, javax.swing.BoxLayout.Y_AXIS));
        scrollHuesped.setViewportView(panelHuesped);

        panelHabitacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 127, 17)));
        panelHabitacion.setLayout(new javax.swing.BoxLayout(panelHabitacion, javax.swing.BoxLayout.Y_AXIS));
        scrollHabitacion.setViewportView(panelHabitacion);

        panelServicios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 127, 17)));
        panelServicios.setLayout(new javax.swing.BoxLayout(panelServicios, javax.swing.BoxLayout.Y_AXIS));
        scrollServicios.setViewportView(panelServicios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollHabitacion)
            .addComponent(scrollHuesped)
            .addComponent(scrollServicios)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

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
            JOptionPane.showMessageDialog(null, "Se registro el CheckIn en la BD correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            
        }
    }//GEN-LAST:event_CheckInActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModificarActionPerformed

    private void DesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DesdeActionPerformed
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
        JLabel txtDni = new JLabel(String.valueOf(huesped.getDNI()));
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
        JLabel txtNum = new JLabel(String.valueOf(hab.getId()));
        panelNumHab.add(lblNum);
        panelNumHab.add(txtNum);
        panel.add(panelNumHab);
        
        // Campos para el tipo de Habitacion
        JPanel panelTipoHab = new JPanel();
        JLabel lblNombre = new JLabel("Tipo habitacion:");
        JLabel txtNombre = new JLabel(hab.getTipoHabitacion().getConcepto());
        panelTipoHab.add(lblNombre);
        panelTipoHab.add(txtNombre);
        panel.add(panelTipoHab);

        // Campos para el Piso
        JPanel panelPiso = new JPanel();
        JLabel lblPiso = new JLabel("Piso:");
        JLabel txtPiso = new JLabel(String.valueOf(hab.getPiso()));
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
        JLabel txtNum = new JLabel(servicio.getConcepto());
        panelNumHab.add(lblNum);
        panelNumHab.add(txtNum);
        panel.add(panelNumHab);
        
        // Campos para el costo
        JPanel panelTipoHab = new JPanel();
        JLabel lblNombre = new JLabel("Costo:");
        JLabel txtNombre = new JLabel(String.valueOf(servicio.getCosto()));
        panelTipoHab.add(lblNombre);
        panelTipoHab.add(txtNombre);
        panel.add(panelTipoHab);

        

        return panel;
        
    }
    
    private void mostrarDatos(Reservacion reserva){
        Huesped huespedTitular = new Huesped();
        
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
            CheckOut.setText(reserva.getCheckOut().format(formatter));
        }
        else if(reserva.getCheckIn()!=null && reserva.getCheckOut()==null){
            CheckIN.setText(reserva.getCheckIn().format(formatter));
            CheckIn.setEnabled(false); 
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
        
        for (Huesped huesped : listaHuesped) {
            JPanel panelIndividual = crearPanelHuesped(huesped);
            panelHuesped.add(panelIndividual);
            panelHuesped.revalidate(); // Reorganiza el diseño
            panelHuesped.repaint();    // Redibuja para reflejar los cambios
        }
    }
    private void ImprimirHabitaciones(int idReserva){
        ReservacionHabitacionesRepository repoReservaHab = new ReservacionHabitacionesRepository(); 
        List<Habitacion > listaHabitacion = repoReservaHab.obtenerHabitacionesPorReservacion(idReserva);
        System.out.println("Cantidad de servicios encontrados: " + listaHabitacion.size());
        panelHabitacion.setLayout((new BoxLayout(panelHabitacion, BoxLayout.Y_AXIS)));
        
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.X_AXIS));
        
        JButton botonModificar = new JButton("Modificar registros");
        
        botonModificar.addActionListener(e -> {
            System.out.println("Botón 'Modificar registros' presionado.");
            VistaDatosReservaMODHabitaciones vistaMod = new VistaDatosReservaMODHabitaciones(listaHabitacion);
            vistaMod.setVisible(true);
            
        });
        panelBtn.add(botonModificar);
        panelHabitacion.add(panelBtn);
        
        for (Habitacion habitacion : listaHabitacion) {
            JPanel panelIndividual = crearPanelHabitacion(habitacion);
            panelHabitacion.add(panelIndividual);
            panelHabitacion.revalidate(); // Reorganiza el diseño
            panelHabitacion.repaint();    // Redibuja para reflejar los cambios
        }
    }
    private void ImprimirServicios(int idReserva){
        ReservacionServicioRepository repoReservaServicio = new ReservacionServicioRepository();
        List<ServiciosAdicionales> listaServicios =repoReservaServicio.obtenerServiciosXIdReserva(idReserva);
        System.out.println("Cantidad de servicios encontrados: " + listaServicios.size());
        panelServicios.setLayout((new BoxLayout(panelServicios, BoxLayout.Y_AXIS)));        
        
        
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
            panelServicios.repaint();    // Redibuja para reflejar los cambios
            
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
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

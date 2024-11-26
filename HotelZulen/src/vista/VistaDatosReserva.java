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
import javax.swing.SwingWorker;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;
import modelo.ServiciosAdicionales;

public class VistaDatosReserva extends javax.swing.JFrame {

    private static Reservacion reservaActual;
    
    public VistaDatosReserva(int idReserva) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        this.add(scrollPane);
        reservaActual = obtenerReserva(idReserva);
        mostrarDatos(reservaActual);
        // Crear un JScrollPane que envuelve al JPanel
        scrollHuesped.setViewportView(panelHuesped);
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
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Datos de la Reserva");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Titular: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 43, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Fecha de creacion: ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 65, -1, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Desde : ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 11, -1, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Hasta: ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 44, -1, -1));
        jPanel2.add(Desde, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 6, 122, -1));
        jPanel2.add(Hasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 44, 127, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Número de habitaciones: ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 87, -1, -1));

        NumHabitaciones.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(NumHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 87, 38, 16));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("CheckIn: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 82, -1, -1));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("CheckOut: ");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        CheckIN.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(CheckIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 82, 115, 16));

        CheckOut.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(CheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 117, 16));

        FechaCreacion.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(FechaCreacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 65, 73, 16));

        Titular.setForeground(new java.awt.Color(0, 0, 0));
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
        jPanel2.add(CheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, -1));

        Modificar.setBackground(new java.awt.Color(255, 127, 17));
        Modificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Modificar.setForeground(new java.awt.Color(255, 255, 255));
        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        jPanel2.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 130, -1));

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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(scrollHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            JOptionPane.showMessageDialog(null, "Se registro el CheckIn en la BD correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            
        }
    }//GEN-LAST:event_CheckInActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModificarActionPerformed
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
           /* huesped.setNombre(txtNombre.getText());
            huesped.setDNI(txtDni.getText());
            huesped.setCorreo(txtCorreo.getText());
            huesped.setTelefono(txtDireccion.getText());
            */JOptionPane.showMessageDialog(null, "Datos actualizados para " + huesped.getNombre());
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
        panelNumHab.add(lblNum);
        panelNumHab.add(txtNum);
        panel.add(panelNumHab);
        
        // Campos para el tipo de Habitacion
        JPanel panelTipoHab = new JPanel();
        JLabel lblNombre = new JLabel("Tipo habitacion:");
        JTextField txtNombre = new JTextField(hab.getTipoHabitacion().getConcepto(), 20);
        panelTipoHab.add(lblNombre);
        panelTipoHab.add(txtNombre);
        panel.add(panelTipoHab);

        // Campos para el Piso
        JPanel panelPiso = new JPanel();
        JLabel lblPiso = new JLabel("Piso:");
        JTextField txtPiso = new JTextField(String.valueOf(hab.getPiso()), 20);
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
        panelNumHab.add(lblNum);
        panelNumHab.add(txtNum);
        panel.add(panelNumHab);
        
        // Campos para el costo
        JPanel panelTipoHab = new JPanel();
        JLabel lblNombre = new JLabel("Costo:");
        JTextField txtNombre = new JTextField(String.valueOf(servicio.getCosto()), 20);
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
        panelHuesped.setBorder(BorderFactory.createTitledBorder("HUESPEDES ASIGNADOS"));
        for (Huesped huesped : listaHuesped) {
            JPanel panelIndividual = crearPanelHuesped(huesped);
            panelHuesped.add(panelIndividual);
        }
    }
    private void ImprimirHabitaciones(int idReserva){
        ReservacionHabitacionesRepository repoReservaHab = new ReservacionHabitacionesRepository(); 
        List<Habitacion > listaHabitacion = repoReservaHab.obtenerHabitacionesPorReservacion(idReserva);
        System.out.println("Cantidad de servicios encontrados: " + listaHabitacion.size());
        panelHabitacion.setLayout((new BoxLayout(panelHabitacion, BoxLayout.Y_AXIS)));
        panelHabitacion.setBorder(BorderFactory.createTitledBorder("HABITACIONES ASIGNADAS"));
        
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.X_AXIS));
        
        JButton botonModificar = new JButton("Modificar registros");
        
        botonModificar.addActionListener(e -> {
            System.out.println("Botón 'Modificar registros' presionado.");
            
        });
        panelBtn.add(botonModificar);
        panelHabitacion.add(panelBtn);
        
        for (Habitacion habitacion : listaHabitacion) {
            JPanel panelIndividual = crearPanelHabitacion(habitacion);
            panelHabitacion.add(panelIndividual);
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

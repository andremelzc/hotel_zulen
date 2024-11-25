/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.Admin;

import Persistencia.LimpiezaRepository;
import Persistencia.TipoHabitacionRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import modelo.Housekeeper;
import modelo.Limpieza;

/**
 *
 * @author Suyco
 */
public class vistaDatosLimpieza_Habitacion_AL extends javax.swing.JFrame {

    private static Limpieza limpiezaActual;
    
     public vistaDatosLimpieza_Habitacion_AL(int idHabitacion) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        this.add(scrollPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        limpiezaActual = obtenerLimpiezaxidHabitacion(idHabitacion);
        if( limpiezaActual==null){
            System.out.println("No se han asignado limpiezas el dia de hoy");
        }else{
            System.out.println("DNI del housekeeper asignado: "+limpiezaActual.getPersonalDNI());
            System.out.println("ID de la habitacion: "+limpiezaActual.getIdHabitacion());
            imprimirLimpieza();
            Housekeeper house = new Housekeeper();
            house = house.obtener(limpiezaActual.getPersonalDNI());
            System.out.println("DNI del housekeeper : "+house.getDNI());
            imprimirHousekeeper(house);
        }
        
    }
    private Limpieza obtenerLimpiezaxidHabitacion(int idHabitacion){
        LimpiezaRepository repoLimpieza = new LimpiezaRepository();
        return repoLimpieza.obtenerLimpiezaXidHabitacion(idHabitacion);
    }
    private JPanel crearPanelLimpieza(Limpieza limpieza){
        JPanel panelLimpiezaas = new JPanel();
        panelLimpiezaas.setLayout(new BoxLayout(panelLimpiezaas, BoxLayout.Y_AXIS));
        panelLimpiezaas.setBorder(BorderFactory.createTitledBorder("Limpieza"));
        
        //Campos para la habitacion
        JPanel panelHabitacion = new JPanel();
        JLabel lblHabitacion = new JLabel("Habitacion:");
        JLabel txtIdHabitacion = new JLabel(String.valueOf(limpieza.getIdHabitacion()));
        panelHabitacion.add(lblHabitacion);
        panelHabitacion.add(txtIdHabitacion);
        panelLimpiezaas.add(panelHabitacion);

        // Campos para el tipo de habitacion
        JPanel panelTipo = new JPanel();
        JLabel lblTipo = new JLabel("Tipo de habitacion:");
        TipoHabitacionRepository repoTipo = new TipoHabitacionRepository();
        JLabel txtTipo = new JLabel(repoTipo.obtener(limpieza.getCategoriaHab()).getConcepto());
        panelTipo.add(lblTipo);
        panelTipo.add(txtTipo);
        panelLimpiezaas.add(panelTipo);

        // Campos para el TipoLimpieza
        JPanel panelTipoLimpieza = new JPanel();
        JLabel lblTipoLimpieza = new JLabel("Tipo limpieza:");
        JLabel txtTipoLimpieza = new JLabel(limpieza.getTipoLimpieza());
        panelTipoLimpieza.add(lblTipoLimpieza);
        panelTipoLimpieza.add(txtTipoLimpieza);
        panelLimpiezaas.add(panelTipoLimpieza);

        // Campos para el estado de la limpieza
        JPanel panelEstadoLimpieza = new JPanel();
        JLabel lblEstadoLimpieza = new JLabel("Estado de la limpieza:");
        // Crear un JComboBox con las opciones de estado de limpieza
        String[] estadosLimpieza = {"asignada", "Finalizado"};
        JComboBox<String> comboEstadoLimpieza = new JComboBox<>(estadosLimpieza);

        // Seleccionar el estado actual basado en el valor de limpieza.getEstadoLimpieza()
        comboEstadoLimpieza.setSelectedItem(limpieza.getEstadoLimpieza());
        
        panelEstadoLimpieza.add(lblEstadoLimpieza);
        panelEstadoLimpieza.add(comboEstadoLimpieza);
        panelLimpiezaas.add(panelEstadoLimpieza);

        // Botón "Modificar"
        JButton btnModificarLimpieza = new JButton("Modificar");
        panelLimpiezaas.add(btnModificarLimpieza);

        // Acción del botón "Modificar"
        btnModificarLimpieza.addActionListener(e -> {
          
            JOptionPane.showMessageDialog(null, "Estado de la limpieza actualizado" + limpieza.getPersonalDNI());
        });

        return panelLimpiezaas;
    
    }
    private JPanel crearPanelHousekeeper(Housekeeper housekeeper){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Housekeeper "));

        
        // Campos para el DNI
        JPanel panelDNI = new JPanel();
        JLabel lblDNI = new JLabel("DNI:");
        JLabel txtDNI = new JLabel(String.valueOf(housekeeper.getDNI()));
        
        panelDNI.add(lblDNI );
        panelDNI.add(txtDNI);
        panel.add(panelDNI);
        
        // Campos para el nombre
        JPanel panelNombre = new JPanel();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(housekeeper.getNombre()+" "+housekeeper.getApellido(), 20);
        txtNombre.setEditable(false);
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);
        panel.add(panelNombre);
        
        // Campos para el correo
        JPanel panelCorreo = new JPanel();
        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(housekeeper.getDireccion(), 20);
        txtCorreo.setEditable(false);
        panelCorreo.add(lblCorreo);
        panelCorreo.add(txtCorreo);
        panel.add(panelCorreo);
        
        // Campos  (Telefono)
        JPanel panelDireccion = new JPanel();
        JLabel lblDireccion = new JLabel("Telefono:");
        JTextField txtDireccion = new JTextField(String.valueOf(housekeeper.getTelefono()), 20);
        txtDireccion.setEditable(false);
        panelDireccion.add(lblDireccion);
        panelDireccion.add(txtDireccion);
        panel.add(panelDireccion);

        // Botón "Modificar"
        JButton btnModificar = new JButton("Modificar");
        panel.add(btnModificar);

        // Acción del botón "Modificar"
        btnModificar.addActionListener(e -> {
           JOptionPane.showMessageDialog(null, "Datos actualizados para " + housekeeper.getDNI());
        });

        return panel;
    }
    
    private void imprimirLimpieza(){
        PanelLimpieza.setLayout((new BoxLayout(PanelLimpieza, BoxLayout.Y_AXIS)));
        JPanel panelIndividual = crearPanelLimpieza(limpiezaActual);
        PanelLimpieza.add(panelIndividual);
        PanelLimpieza.revalidate();
        PanelLimpieza.repaint(); // Refresca el panel para que se vea en la ventana
    }
    private void imprimirHousekeeper(Housekeeper housekeeper){
       PanelHousekeeper.setLayout((new BoxLayout(PanelHousekeeper, BoxLayout.Y_AXIS)));
       JPanel panelIndividual = crearPanelHousekeeper(housekeeper);
       PanelHousekeeper.add(panelIndividual);
       PanelHousekeeper.revalidate();
        PanelHousekeeper.repaint(); // Refresca el panel para que se vea en la ventana
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanelLimpieza = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelHousekeeper = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        PanelLimpieza.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 127, 17)));

        javax.swing.GroupLayout PanelLimpiezaLayout = new javax.swing.GroupLayout(PanelLimpieza);
        PanelLimpieza.setLayout(PanelLimpiezaLayout);
        PanelLimpiezaLayout.setHorizontalGroup(
            PanelLimpiezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        PanelLimpiezaLayout.setVerticalGroup(
            PanelLimpiezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(PanelLimpieza);

        PanelHousekeeper.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 127, 17)));

        javax.swing.GroupLayout PanelHousekeeperLayout = new javax.swing.GroupLayout(PanelHousekeeper);
        PanelHousekeeper.setLayout(PanelHousekeeperLayout);
        PanelHousekeeperLayout.setHorizontalGroup(
            PanelHousekeeperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        PanelHousekeeperLayout.setVerticalGroup(
            PanelHousekeeperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(PanelHousekeeper);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelHousekeeper;
    private javax.swing.JPanel PanelLimpieza;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

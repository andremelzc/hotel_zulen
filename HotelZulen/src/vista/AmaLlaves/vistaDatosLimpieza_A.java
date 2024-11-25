/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.AmaLlaves;

import Persistencia.LimpiezaRepository;
import Persistencia.PersonalRepository;
import Persistencia.TipoHabitacionRepository;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.SwingWorker;
import modelo.Housekeeper;
import modelo.Limpieza;

/**
 *
 * @author Suyco
 */
public class vistaDatosLimpieza_A extends javax.swing.JFrame {

    private static Housekeeper housekeeperActual;
    
    public vistaDatosLimpieza_A(int idHousekeeper) {
        FlatArcOrangeIJTheme.setup();
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        housekeeperActual = obtenerHousekeeper(idHousekeeper);
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        this.add(scrollPane);
        imprimirHousekeeper(housekeeperActual);
        imprimirLimpiezasEnSegundoPlano(idHousekeeper);
    }

    private JPanel crearPanelHousekeeper (Housekeeper housekeeper){
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
        JLabel txtNombre = new JLabel(housekeeper.getNombre()+" "+housekeeper.getApellido());
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);
        panel.add(panelNombre);
        
        // Campos para el correo
        JPanel panelCorreo = new JPanel();
        JLabel lblCorreo = new JLabel("Correo:");
        JLabel txtCorreo = new JLabel(housekeeper.getDireccion());
        panelCorreo.add(lblCorreo);
        panelCorreo.add(txtCorreo);
        panel.add(panelCorreo);
        
        // Campos para la dirección (Telefono)
        JPanel panelDireccion = new JPanel();
        JLabel lblDireccion = new JLabel("Telefono:");
        JLabel txtDireccion = new JLabel(String.valueOf(housekeeper.getTelefono()));
        panelDireccion.add(lblDireccion);
        panelDireccion.add(txtDireccion);
        panel.add(panelDireccion);


        return panel;  
    }
    
    private JPanel crearPanelLimpiezas (Limpieza limpieza){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Limpieza"));
        
        //Campos para la habitacion
        JPanel panelHabitacion = new JPanel();
        JLabel lblHabitacion = new JLabel("Habitacion:");
        JLabel txtIdHabitacion = new JLabel(String.valueOf(limpieza.getIdHabitacion()));
        panelHabitacion.add(lblHabitacion);
        panelHabitacion.add(txtIdHabitacion);
        panel.add(panelHabitacion);

        // Campos para el tipo de habitacion
        JPanel panelTipo = new JPanel();
        JLabel lblTipo = new JLabel("Tipo de habitacion:");
        TipoHabitacionRepository repoTipo = new TipoHabitacionRepository();
        JLabel txtTipo = new JLabel(repoTipo.obtener(limpieza.getCategoriaHab()).getConcepto());
        panelTipo.add(lblTipo);
        panelTipo.add(txtTipo);
        panel.add(panelTipo);

        // Campos para el TipoLimpieza
        JPanel panelCorreo = new JPanel();
        JLabel lblCorreo = new JLabel("Tipo limpieza:");
        JLabel txtCorreo = new JLabel(limpieza.getTipoLimpieza());
        panelCorreo.add(lblCorreo);
        panelCorreo.add(txtCorreo);
        panel.add(panelCorreo);

        // Campos para el estado de la limpieza
        JPanel panelDireccion = new JPanel();
        JLabel lblDireccion = new JLabel("Estado de la limpieza:");
        // Crear un JComboBox con las opciones de estado de limpieza
        String[] estadosLimpieza = {"asignada", "Finalizado"};
        JComboBox<String> comboEstadoLimpieza = new JComboBox<>(estadosLimpieza);
        comboEstadoLimpieza.setSelectedItem(limpieza.getEstadoLimpieza());
        
        panelDireccion.add(lblDireccion);
        panelDireccion.add(comboEstadoLimpieza);
        panel.add(panelDireccion);

        // Botón "Modificar"
        JButton btnModificar = new JButton("Modificar");
        panel.add(btnModificar);

        // Acción del botón "Modificar"
        btnModificar.addActionListener(e -> {
          
            JOptionPane.showMessageDialog(null, "Estado de la limpieza cambiado de " + limpieza.getPersonalDNI());
        });

        return panel;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelLimpiezas = new javax.swing.JPanel();
        jScrollHousekeeper = new javax.swing.JScrollPane();
        PanelHousekeeper = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout PanelLimpiezasLayout = new javax.swing.GroupLayout(PanelLimpiezas);
        PanelLimpiezas.setLayout(PanelLimpiezasLayout);
        PanelLimpiezasLayout.setHorizontalGroup(
            PanelLimpiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        PanelLimpiezasLayout.setVerticalGroup(
            PanelLimpiezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(PanelLimpiezas);

        javax.swing.GroupLayout PanelHousekeeperLayout = new javax.swing.GroupLayout(PanelHousekeeper);
        PanelHousekeeper.setLayout(PanelHousekeeperLayout);
        PanelHousekeeperLayout.setHorizontalGroup(
            PanelHousekeeperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        PanelHousekeeperLayout.setVerticalGroup(
            PanelHousekeeperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 179, Short.MAX_VALUE)
        );

        jScrollHousekeeper.setViewportView(PanelHousekeeper);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollHousekeeper, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollHousekeeper, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private Housekeeper obtenerHousekeeper(int idHousekeeper){
        PersonalRepository repoPersonal = new PersonalRepository();
        return repoPersonal.obtenerHouskeeper(idHousekeeper);
    }
    private void imprimirHousekeeper(Housekeeper housekeeper){
        PanelHousekeeper.setLayout((new BoxLayout(PanelHousekeeper, BoxLayout.Y_AXIS)));
        JPanel panelIndividual = crearPanelHousekeeper(housekeeper);
        PanelHousekeeper.add(panelIndividual);
        PanelHousekeeper.revalidate();
        PanelHousekeeper.repaint();
    }
    private void imprimirLimpiezas(int idHousekeeper){
        LimpiezaRepository repoLimpieza = new LimpiezaRepository();
        List<Limpieza> listLimpiezas = new ArrayList<>();
        listLimpiezas = repoLimpieza.obtenerListaLimpiezasxDNI(idHousekeeper);
        PanelLimpiezas.setLayout((new BoxLayout(PanelLimpiezas, BoxLayout.Y_AXIS)));
        for(Limpieza limpieza : listLimpiezas){
            JPanel panelIndividual = crearPanelLimpiezas(limpieza);
            PanelLimpiezas.add(panelIndividual);
            PanelLimpiezas.revalidate();
            PanelLimpiezas.repaint();
        }
    }
    private void imprimirLimpiezasEnSegundoPlano(int idHousekeeper) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Paso 3: Imprimir las habitaciones
                imprimirLimpiezas(idHousekeeper);
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
    private javax.swing.JPanel PanelHousekeeper;
    private javax.swing.JPanel PanelLimpiezas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollHousekeeper;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

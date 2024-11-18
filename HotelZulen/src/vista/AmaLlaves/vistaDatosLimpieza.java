/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.AmaLlaves;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import modelo.Housekeeper;
import modelo.Huesped;

/**
 *
 * @author Suyco
 */
public class vistaDatosLimpieza extends javax.swing.JFrame {


    public vistaDatosLimpieza() {
        initComponents();
        JScrollPane scrollPane = new JScrollPane(jPanel1);
        this.add(scrollPane);
    }

    private JPanel crearPanelHousekeeper (Housekeeper housekeeper){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Housekeeper "));

        
        // Campos para el DNI
        JPanel panelDNI = new JPanel();
        JLabel lblDNI = new JLabel("DNI:");
        JTextField txtDNI = new JTextField(String.valueOf(housekeeper.getDNI()), 20);
        panelDNI.add(lblDNI );
        panelDNI.add(txtDNI);
        panel.add(panelDNI);
        
        // Campos para el nombre
        JPanel panelNombre = new JPanel();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(housekeeper.getNombre(), 20);
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);
        panel.add(panelNombre);
        
        // Campos para el correo
        JPanel panelCorreo = new JPanel();
        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(housekeeper.getDireccion(), 20);
        panelCorreo.add(lblCorreo);
        panelCorreo.add(txtCorreo);
        panel.add(panelCorreo);
        
        // Campos para la dirección (Telefono)
        JPanel panelDireccion = new JPanel();
        JLabel lblDireccion = new JLabel("Telefono:");
        JTextField txtDireccion = new JTextField(String.valueOf(housekeeper.getTelefono()), 20);
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
            */JOptionPane.showMessageDialog(null, "Datos actualizados para " + housekeeper.getDNI());
        });

        return panel;
        
        
    }
    
    private JPanel crearPanelTitular (Huesped huesped){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Titular"));
        
        // Campos para el DNI
        JPanel panelDni = new JPanel();
        JLabel lblDni = new JLabel("DNI:");
        JTextField txtDni = new JTextField(String.valueOf(huesped.getDNI()), 20);
        panelDni.add(lblDni);
        panelDni.add(txtDni);
        panel.add(panelDni);

        // Campos para el nombre
        JPanel panelNombre = new JPanel();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(huesped.getNombre(), 20);
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);
        panel.add(panelNombre);

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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

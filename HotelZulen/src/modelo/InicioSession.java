/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.InicioSesionRepository;
import java.io.IOException;
import javax.swing.JOptionPane;
import vista.Admin.vistaAdministrador;
import vista.AmaLlaves.vistaAmaLLaves;
import vista.Huesped.vistaHuesped;
import vista.Recepcionista.vistaRecepcionista;
import vista.JefeCocina.vistaJefeCocina;
import vista.iniciarSesion;

/**
 *
 * @author Suyco
 */
public class InicioSession {

    private static Personal usuarioActual;
    private static Huesped huespedActual;

    public static void iniciarSesion(String usuario, String contraseña) throws IOException {
        InicioSesionRepository identificarUsuario = new InicioSesionRepository();
        usuarioActual = identificarUsuario.iniciarSesion(usuario, contraseña);

        if (usuarioActual != null) {
            iniciarSesion LogIn = new iniciarSesion();
            LogIn.setVisible(false);
            mostrarVistaCorrespondiente();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void iniciarSesionHuesped(String usuario, String contraseña) throws IOException {
        InicioSesionRepository identificarUsuario = new InicioSesionRepository();
        huespedActual = identificarUsuario.iniciarSesionHuesped(usuario, contraseña);

        if (huespedActual != null) {
            iniciarSesion LogIn = new iniciarSesion();
            LogIn.setVisible(false);
            vistaHuesped vistaHuesped = new vistaHuesped(huespedActual);
            vistaHuesped.setVisible(true);   
            vistaHuesped.getPrincipalBoton().doClick();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void mostrarVistaCorrespondiente() throws IOException {
        if (usuarioActual instanceof Administrador) {
            Administrador administrador = (Administrador) usuarioActual;
            System.out.println("Abriendo vista de administrador");
            vistaAdministrador vistaAdmin = new vistaAdministrador(administrador);
            vistaAdmin.getPersonalBoton().doClick();
            vistaAdmin.setVisible(true);

        } else if (usuarioActual instanceof Recepcionista) {
            Recepcionista recepcionista = (Recepcionista) usuarioActual;
            System.out.println("Abriendo vista de recepcionista");
            vistaRecepcionista vistaRecepionista = new vistaRecepcionista(recepcionista);
            vistaRecepionista.getPrincipalBoton().doClick();
            vistaRecepionista.setVisible(true);
        } else if (usuarioActual instanceof AmaDeLlaves) {
            AmaDeLlaves amaLlaves = (AmaDeLlaves) usuarioActual;
            System.out.println("Abriendo vista del ama de llaves");
            vistaAmaLLaves vistaAma = new vistaAmaLLaves(amaLlaves);
            vistaAma.getPrincipalBoton().doClick();
            vistaAma.setVisible(true);
        } else if (usuarioActual instanceof KitchenManager) {
            KitchenManager jefeCocina = (KitchenManager) usuarioActual;
            System.out.println("Abriendo vista del jefe de cocina");
            vistaJefeCocina vistaCocina = new vistaJefeCocina(jefeCocina);
            vistaCocina.getPrincipalBoton().doClick();
            vistaCocina.setVisible(true);
        } else {
            System.out.println("Tipo de usuario no reconocido.");
        }
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        huespedActual = null;
        java.awt.Window[] windows = java.awt.Window.getWindows();
        for (java.awt.Window window : windows) {
            window.dispose(); // Cerrar todas las ventanas abiertas
        }
        iniciarSesion LogIn = new iniciarSesion();
        LogIn.setVisible(true);
        LogIn.getIngresarPersonal().doClick();
        System.out.println("Sesion cerrada. Volviendo a la vista de inicio de sesion.");
 
    }

}

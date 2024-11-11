/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.InicioSesionRepository;
import java.io.IOException;
import vista.Admin.vistaAdministrador;
import vista.Huesped.vistaHuesped;
import vista.Recepcionista.vistaRecepcionista;
import vista.JefeCocina.vistaJefeCocina;

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
            mostrarVistaCorrespondiente();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
    
    public static void iniciarSesionHuesped(String usuario, String contraseña) throws IOException{
        InicioSesionRepository identificarUsuario = new InicioSesionRepository();
        huespedActual = identificarUsuario.iniciarSesionHuesped(usuario, contraseña);
        
        if (huespedActual != null){
            vistaHuesped vistaHuesped = new vistaHuesped(huespedActual);
            vistaHuesped.setVisible(true);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    private static void mostrarVistaCorrespondiente() throws IOException {
        if (usuarioActual instanceof Administrador) {
            Administrador administrador = (Administrador) usuarioActual;
            System.out.println("Abriendo vista de administrador");
           vistaAdministrador vistaAdmin = new vistaAdministrador(administrador);
            vistaAdmin.setVisible(true);
        } else if (usuarioActual instanceof Recepcionista) {
            Recepcionista recepcionista = (Recepcionista) usuarioActual;
            System.out.println("Abriendo vista de recepcionista");
            vistaRecepcionista vistaRecepionista = new vistaRecepcionista(recepcionista);
            vistaRecepionista.setVisible(true);
        } else if(usuarioActual instanceof AmaDeLlaves) { 
            AmaDeLlaves amaLlaves = (AmaDeLlaves) usuarioActual;
            System.out.println("Abriendo vista del ama de llaves");
            //new VistaAmaLlaves().mostrar()
        } else if (usuarioActual instanceof KitchenManager){
            KitchenManager jefeCocina = (KitchenManager) usuarioActual;
            System.out.println("Abriendo vista del jefe de cocina");
            vistaJefeCocina vistaCocina = new vistaJefeCocina();
            vistaCocina.setVisible(true);
            //new VistaKitchenManager().mostrar()
        } else {
            System.out.println("Tipo de usuario no reconocido.");
        }
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        System.out.println("Sesion cerrada. Volviendo a la vista de inicio de sesion.");
    }

}

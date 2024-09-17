/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Fabri;

import java.time.LocalDateTime;
/**
 *
 * @author Fabrizio Mantari
 */
public class Huesped {
    protected String Nombre;
    protected String Apellido;
    protected String DNI;
    protected String telefono;
    protected String dirección; 
    protected String ID;
    protected String usuario;
    protected String contraseña;
    protected String ID_Habitacion;
    protected Facturas factura;
    protected LocalDateTime inicio_Huesped;
    protected LocalDateTime fin_Huesped;

    public Huesped(String Nombre, String Apellido, String DNI, String telefono, String dirección, String ID, String usuario, String contraseña, String ID_Habitacion, Facturas factura, LocalDateTime inicio_Huesped, LocalDateTime fin_Huesped) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.dirección = dirección;
        this.ID = ID;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.ID_Habitacion = ID_Habitacion;
        this.factura = factura;
        this.inicio_Huesped = inicio_Huesped;
        this.fin_Huesped = fin_Huesped;
    }
    
    

}

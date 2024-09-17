/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Fabri;

/**
 *
 * @author Fabrizio Mantari
 */
public abstract class Personal {
    protected String Nombre;
    protected String Apellido;
    protected String DNI;
    protected String telefono;
    protected String dirección; 
    protected String ID;
    protected String usuario;
    protected String contraseña;
    protected String funcion;
    
    public Personal(String Nombre,String Apellido, String DNI, String telefono, String dirección, String ID, String usuario, String contraseña, String funcion){ 
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.dirección = dirección;
        this.ID = ID; 
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.funcion = funcion;
    }
}
 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Fabri;

/**
 *
 * @author Fabrizio Mantari
 */
public class Facturas {
    private String ID;
    private String ID_Cliente;
    private int cantHhabitaciones;
    private int costoServicios; 
    private int costoConsumibles;
    private int ID_Recepcionista;

    public Facturas(String ID, String ID_Cliente, int cantHhabitaciones, int costoServicios, int costoConsumibles, int ID_Recepcionista) {
        this.ID = ID;
        this.ID_Cliente = ID_Cliente;
        this.cantHhabitaciones = cantHhabitaciones;
        this.costoServicios = costoServicios;
        this.costoConsumibles = costoConsumibles;
        this.ID_Recepcionista = ID_Recepcionista;
    }
    
    
}

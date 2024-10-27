/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Suyco
 */
import java.util.List;


/**
 *
 * @author Suyco
 */
public class ServiciosAdicionales implements IActualizar<ServiciosAdicionales> {
    int id;
    String concepto;
    double costo;
    String estado;

    public ServiciosAdicionales() {
    }

    public ServiciosAdicionales(int id, String concepto, double costo, String estado) {
        this.id = id;
        this.concepto = concepto;
        this.costo = costo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }

    @Override
    public void agregar(List<ServiciosAdicionales> lista, ServiciosAdicionales elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<ServiciosAdicionales> lista, ServiciosAdicionales elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(List<ServiciosAdicionales> lista, ServiciosAdicionales elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<ServiciosAdicionales> lista) {

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-30s %-10s%n", "ID", "Concepto", "Costo");
        System.out.println("--------------------------------------------------------------------------------------");
        
        // Imprimimos toda la lista de servicios
        for (ServiciosAdicionales servicio : lista) {
            System.out.printf("%-4d %-30s %-10.2f%n",
                    servicio.getId(),
                    servicio.getConcepto(),
                    servicio.getCosto());
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    @Override
    public ServiciosAdicionales obtenerPorId(List<ServiciosAdicionales> lista, int id) {
        for (ServiciosAdicionales servicio : lista) {
            if (servicio.getId()== id) {
                return servicio; 
            }
        }
        return null; 
    }

}

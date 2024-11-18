/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Suyco
 */
import Persistencia.ServiciosAdicionalesRepository;
import java.util.List;


/**
 *
 * @author Suyco
 */
public class ServiciosAdicionales  {
    int id;
    String concepto;
    double costo;
    String estado;
    private ServiciosAdicionalesRepository repository;

    public ServiciosAdicionales() {
        this.repository= new ServiciosAdicionalesRepository();
    }
    
    public ServiciosAdicionales(String concepto, double costo, String estado) {
        this.concepto = concepto;
        this.costo = costo;
        this.estado = estado;
        this.repository= new ServiciosAdicionalesRepository();
    }
    
    public ServiciosAdicionales(int id, String concepto, double costo, String estado) {
        this.id = id;
        this.concepto = concepto;
        this.costo = costo;
        this.estado = estado;
        this.repository= new ServiciosAdicionalesRepository();
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
    public void agregarServicioAdicional(String concepto, double costo, String estado) {
        ServiciosAdicionales nuevoServicio = new ServiciosAdicionales(concepto, costo, estado);
        repository.crear(nuevoServicio); // Llama al método del repositorio para guardar el nuevo servicio
        System.out.println("Servicio adicional agregado exitosamente.");
    }
    
    public ServiciosAdicionales obtenerServicioxString (String concepto){
        return repository.obtenerxString(concepto);
    }

    
}

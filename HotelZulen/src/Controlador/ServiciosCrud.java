/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.ServiciosAdicionales;

/**
 *
 * @author Suyco
 */
public class ServiciosCrud {
    
    public List<ServiciosAdicionales> listaServicios;

    public ServiciosCrud() {
        this.listaServicios = new ArrayList<>();
        listaServicios.add(new ServiciosAdicionales(1,"HouseKeeping",70));
        listaServicios.add(new ServiciosAdicionales(2,"FitnessCenter",30));
    }
    
    public void agregarServicios(ServiciosAdicionales servicio,String concepto,int costo){
        int ultimoId= listaServicios.size();
        listaServicios.add(new ServiciosAdicionales(ultimoId+1,concepto,costo));
    }
    public void eliminarServicioXId(int id){
        for(int i=0;i<listaServicios.size();i++){
            if(listaServicios.get(i).getId()==id){
                listaServicios.remove(i);
            }
        }
    }
    public void modificarServicioxId(int id,String concepto,int costo){
        for(int i=0;i<listaServicios.size();i++){
            if(listaServicios.get(i).getId()== id){
                listaServicios.set(i,new ServiciosAdicionales(id,concepto,costo));
                break;
            }
        }
    }
    public void mostrarServicios(){
        for (int i=0;i<listaServicios.size();i++){
            System.out.println("Id: "+listaServicios.get(i).getId());
            System.out.println("Concepto: "+listaServicios.get(i).getConcepto());
            System.out.println("Costo: "+listaServicios.get(i).getCosto());
            System.out.println("--------------------------------------------");
        }
    }

    public boolean verificarExistenciaxId(int id){
        for (int i=0;i<listaServicios.size();i++){
            if(listaServicios.get(i).getId()==id){                
               return true;
            }
        }
        return false;
    }
}

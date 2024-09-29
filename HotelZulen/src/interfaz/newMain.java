/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;
import Controlador.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Personal;
/**
 *
 * @author PC
 */
public class newMain {
    public static void main(String[] args){
        List<Personal> listaPersonales = new ArrayList<>();
        
        PersonalCrud personalCrud = new PersonalCrud();
        listaPersonales = personalCrud.cargarCSVlista();
        
        personalCrud.leerPersonal(listaPersonales);
    }
}

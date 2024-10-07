/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import Controlador.*;
import modelo.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.Personal;

/**
 *
 * @author PC
 */
public class newMain {

    public static void main(String[] args) {
        PersonalCrud personalCrud = new PersonalCrud();
        List<Personal> listaPersonal = personalCrud.cargarCSVlista();
        personalCrud.eliminarPersonal(listaPersonal, 1);
        
      

    }
}

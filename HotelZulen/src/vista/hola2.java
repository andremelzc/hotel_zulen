/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Persistencia.ComboConsumibleRepository;
import Persistencia.ConsumibleRepository;
import modelo.Combo;
import modelo.Consumible;
import Persistencia.ComboRepository;
import java.util.ArrayList;
import java.util.List;
import modelo.ComboConsumible;
import Persistencia.DatabaseConnection;
import modelo.Huesped;
import Persistencia.*;
import modelo.*;

/**
 *
 * @author PC
 */
public class hola2 {

    public static void main(String args[]) {
        HabitacionRepository habitacionRepository = new HabitacionRepository();
        Habitacion habitacion = new Habitacion();
        
        habitacion = habitacionRepository.obtener(1);
        System.out.println(habitacion.getTipoHabitacion().getConcepto());
    }
}

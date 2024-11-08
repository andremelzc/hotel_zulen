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

/**
 *
 * @author PC
 */
public class hola {

    public static void main(String args[]) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        ComboRepository comboRepository = new ComboRepository();

        // Lista para guardar los datos en la tabla
        List<Object[]> filas = new ArrayList<>();

        // Inicializar para tener todos los combos
        List<Combo> combos = new ArrayList<>();
        System.out.println("hola");
        ComboRepository comborepo = new ComboRepository();
        ComboConsumibleRepository comboconsurepo = new ComboConsumibleRepository();

        // Todos los combos
        combos = comborepo.obtenerTodosCombos();

        for (Combo combo : combos) {
            // Solo combos del almuerzo

            if (combo.getTipoComida().equals("Almuerzo")) {
                System.out.println(combo.getDescripcion());
                // Inicializar para tener todos los consumibles por cada combo
                List<Consumible> consumibles = comboconsurepo.obtenerConsumiblesPorCombo(combo.getId());

                // Hallamos el precio del combo
                float precio = 0;

                // Iteramos los consumibles del combo
                for (Consumible consumible : consumibles) {
                    precio = precio + consumible.getPrecio();
                }
                
                System.out.println(combo.getId());
                System.out.println(combo.getTipoComida());
                System.out.println(combo.getDescripcion());
                System.out.println(precio);

            }

        }

        Object[][] data = new Object[filas.size()][];

        for (int i = 0; i < filas.size(); i++) {
            data[i] = filas.get(i);
        }

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebitas_andre;

import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Reservación {
//Cambiar por huésped

    PersonalCrud personalCrud = new PersonalCrud();

    int id_cliente;
    int id_habitacion;

    public Reservación() {
    }

    public Reservación(int id_cliente, int id_habitacion) {
        this.id_cliente = id_cliente;
        this.id_habitacion = id_habitacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public void asignarReservacion(Scanner sc) {
        System.out.println("\n-----------------------------");
        System.out.println("Asignando habitacion");
        //Cambiar por lógica de huésped
        do {
            System.out.println("-----------------------------");
            System.out.println("ID de huesped: ");
            this.id_cliente = sc.nextInt();
            
        } while (!personalCrud.buscarPersonal(id_cliente));
        
        //Asegurar existencia de habitación
        do {
            System.out.println("-----------------------------");
            System.out.println("ID de habitacion: ");
            this.id_habitacion = sc.nextInt();
            System.out.println("-----------------------------");
        } while (true);

    }

}

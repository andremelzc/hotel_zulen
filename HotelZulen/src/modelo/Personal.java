/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Personal implements IActualizar<Personal> {

    int DNI;
    String nombre;
    String apellido;
    int telefono;
    String direccion;
    String usuario;
    String contrasena;
    String funcion;
    String estado;

    public Personal() {

    }

    public Personal(int DNI, String nombre, String apellido, int telefono, String direccion, String usuario, String contrasena, String funcion, String estado) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.funcion = funcion;
        this.estado = estado;
    }

    //Constructor Agregado por Miguel para el Inicio de Usuario
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public void agregar(List<Personal> lista, Personal elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<Personal> lista, Personal elemento) {
        Scanner sc = new Scanner(System.in);

        for (Personal personal : lista) {
            if (elemento.getDNI() == personal.getDNI()) {
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s %-20s %-20s %-15s %-15s %-10s%n",
                        "DNI", "Nombre", "Apellido", "Teléfono", "Funcion", "Estado");
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println("Nuevos Datos");
                //Nombre
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();
                personal.setNombre(nombre);
                //Apellido
                System.out.println("Apellido: ");
                String apellido = sc.nextLine();
                personal.setApellido(apellido);
                //DNI
                boolean flag_dni = false;
                int dni;
                do {
                    System.out.println("DNI");
                    dni = sc.nextInt();
                    personal.setDNI(dni);
                    if (String.valueOf(dni).length() == 8) {
                        flag_dni = true;
                    }
                    if (!flag_dni) {
                        System.out.println("-----------------------------");
                        System.out.println("Ingrese un DNI correcto");
                        System.out.println("-----------------------------");
                    }
                } while (!flag_dni);
                personal.setDNI(dni);
                //Telefono
                boolean flag_telefono = false;
                int telefono;
                do {
                    System.out.println("Telefono");
                    telefono = sc.nextInt();
                    if (String.valueOf(telefono).length() == 9) {
                        flag_telefono = true;
                    }
                    if (!flag_telefono) {
                        System.out.println("-----------------------------");
                        System.out.println("Ingrese un telefono correcto");
                        System.out.println("-----------------------------");
                    }
                } while (!flag_telefono);
                personal.setTelefono(telefono);
                //Direccion y limpiamos buffer
                sc.nextLine();
                System.out.println("Dirección");
                String direccion;
                direccion = sc.nextLine();
                personal.setDireccion(direccion);
                // Usuario
                String usuario;
                System.out.println("Usuario: ");
                usuario = sc.nextLine();
                personal.setUsuario(usuario);
                // Contraseña
                System.out.println("Contrasena: ");
                String contrasena;
                contrasena = sc.nextLine();
                personal.setContrasena(contrasena);
                //Funcion
                System.out.println("Funcion");
                String funcion;
                funcion = sc.nextLine();
                personal.setFuncion(funcion);
            }
        }
    }

    @Override
    public void eliminar(List<Personal> lista, Personal elemento) {
        for(Personal personal : lista){
            if(personal.getDNI()==elemento.getDNI()){
                personal.setEstado("Inactivo");
            }
        }
    }

    @Override
    public void mostrarLista(List<Personal> lista) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-15s %-15s %-10s%n",
                "DNI", "Nombre", "Apellido", "Teléfono", "Funcion", "Estado");
        System.out.println("------------------------------------------------------------------------------------------------------");

        // Recorre y muestra la lista de huéspedes
        for (Personal personal : lista) {
            System.out.printf("%-10d %-20s %-20s %-15s %-15s %-10s%n",
                    personal.getDNI(),
                    personal.getNombre(),
                    personal.getApellido(),
                    personal.getTelefono(),
                    personal.getFuncion(),
                    personal.getEstado());
        }
        System.out.println("------------------------------------------------------------------------------------------------------");

    }

    @Override
    public Personal obtenerPorId(List<Personal> lista, int id) {
        for (Personal personal : lista) {
            if (personal.getDNI() == id) {
                return personal;
            }
        }
        return null;
    }

    public void buscarPersonalXDNI(List<Personal> listaPersonal, int dni) {
        boolean find = false;
        for (Personal personal : listaPersonal) {
            // Buscamos el DNI
            if (personal.getDNI() == dni) {
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s %-20s %-20s %-15s %-15s %-10s%n",
                        "DNI", "Nombre", "Apellido", "Teléfono", "Funcion", "Estado");
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10d %-20s %-20s %-15s %-15s %-10s%n",
                        personal.getDNI(),
                        personal.getNombre(),
                        personal.getApellido(),
                        personal.getTelefono(),
                        personal.getFuncion(),
                        personal.getEstado());
                find = true;
                break;  // Si encuentras el huésped, no necesitas seguir buscando
            }
        }
        if (!find) {
            System.out.println("Huesped no encontrado");
        }
    }

}

package modelo;

import java.util.List;
import java.util.Scanner;

public class Habitacion implements IActualizar<Habitacion> {

    private int id;
    private TipoDeHabitacion tipoHabitacion;
    private String piso;
    private String estado;

    public Habitacion(int id, TipoDeHabitacion tipoHabitacion, String piso, String estado) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.piso = piso;
        this.estado = estado;
    }

    public Habitacion() {
    }

    public int getId() {
        return id;
    }

    public TipoDeHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public String getPiso() {
        return piso;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoHabitacion(TipoDeHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void agregar(List<Habitacion> lista, Habitacion elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<Habitacion> lista, Habitacion elemento) {
        Scanner sc = new Scanner(System.in);
        boolean find = false;

        for (Habitacion habitacion : lista) {
            if (habitacion.getId() == elemento.getId()) {
                System.out.println("------------------------------------------");
                System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
                System.out.println("------------------------------------------");
                System.out.printf("%-4s %-10s %-15s%n",
                        habitacion.getId(), habitacion.getTipoHabitacion().getConcepto(),
                        habitacion.getPiso());
                System.out.println("------------------------------------------");
                System.out.println("Nuevos Datos");
                System.out.println("------------------------------------------");
                System.out.println("Tipo: ");
                String tipo = sc.nextLine();
                TipoDeHabitacion tipoHabitacion = new TipoDeHabitacion(100, tipo, 00);
                habitacion.setTipoHabitacion(tipoHabitacion);
                System.out.println("Piso: ");
                String piso = sc.nextLine();
                habitacion.setPiso(piso);
                sc.nextLine();
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("-----------------------------");
            System.out.println("No se encontro algun habitacion con dicho ID");
        }
        
        
    }

    @Override
    public void eliminar(List<Habitacion> lista, Habitacion elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<Habitacion> lista) {

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-19s %-5s %-12s%n", "ID", "Tipo de habitacion", "Piso", "Estado");
        System.out.println("--------------------------------------------------------------------------------------");
        // Imprimimos toda la lista
        for (Habitacion habitacion : lista) {

            System.out.printf("%-4s %-19s %-5s %-12s%n",
                    habitacion.getId(),
                    habitacion.getTipoHabitacion().getConcepto(),
                    habitacion.getPiso(),
                    habitacion.getEstado());

        }
        System.out.println("--------------------------------------------------------------------------------------");

    }

    @Override
    public Habitacion obtenerPorId(List<Habitacion> lista, int id) {

        for (Habitacion habitacion : lista) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null;
    }

    public void leerTodoHabitacionDisponible(List<Habitacion> listaHabitacion) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-10s %-5s %-12s%n", "ID", "Tipo", "Piso", "Estado");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Habitacion habitacion : listaHabitacion) {
            if (habitacion.getEstado().equals("Disponible")) {
                System.out.printf("%-4s %-10s %-5s %-12s%n",
                        habitacion.getId(),
                        habitacion.getTipoHabitacion().getConcepto(),
                        habitacion.getPiso(),
                        habitacion.getEstado());
            }

        }

    }

    public void leerTodoHabitacionReservado(List<Habitacion> listaHabitacion) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-10s %-5s %-12s%n", "ID", "Tipo", "Piso", "Estado");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Habitacion habitacion : listaHabitacion) {
            if (habitacion.getEstado().equals("Reservado")) {
                System.out.printf("%-4s %-10s %-5s %-12s%n",
                        habitacion.getId(),
                        habitacion.getTipoHabitacion().getConcepto(),
                        habitacion.getPiso(),
                        habitacion.getEstado());
            }

        }

    }

    public void leerTodoHabitacionOcupada(List<Habitacion> listaHabitacion) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-10s %-5s %-12s%n", "ID", "Tipo", "Piso", "Estado");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Habitacion habitacion : listaHabitacion) {
            if (habitacion.getEstado().equals("Ocupada")) {
                System.out.printf("%-4s %-10s %-5s %-12s%n",
                        habitacion.getId(),
                        habitacion.getTipoHabitacion().getConcepto(),
                        habitacion.getPiso(),
                        habitacion.getEstado());
            }

        }

    }

}
package modelo;

import java.util.List;
import java.util.Scanner;

public class Huesped implements IActualizar<Huesped> {

    private int DNI;
    private String nombre;
    private String apellido;
    private int telefono;
    private String direccion;
    private String usuario;
    private String contrasena;
    private String estado;

    public Huesped(int DNI, String nombre, String apellido, int telefono, String direccion, String usuario, String contrasena, String estado) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Huesped() {

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

    public String getEstado() {
        return estado;
    }

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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void agregar(List<Huesped> lista, Huesped elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<Huesped> lista, Huesped elemento) {
        Scanner sc = new Scanner(System.in);

        for (Huesped huesped : lista) {
            if (elemento.getDNI()== huesped.getDNI()) {
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s %-20s %-20s %-15s %-10s%n",
                        "DNI", "Nombre", "Apellido", "Teléfono", "Estado");
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10d %-20s %-20s %-15d %-10d%n",
                        huesped.getDNI(),
                        huesped.getNombre(),
                        huesped.getApellido(),
                        huesped.getTelefono(),
                        huesped.getEstado());
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println("Nuevos Datos: ");
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();
                huesped.setNombre(nombre);
                System.out.println("Aapellido: ");
                String apellido = sc.nextLine();
                huesped.setApellido(apellido);
                System.out.println("Telefono: ");
                String telefono = sc.nextLine();
                huesped.setTelefono(Integer.parseInt(telefono));
            }
        }
    }

    @Override
    public void eliminar(List<Huesped> lista, Huesped elemento) {
        for(Huesped huesped : lista){
            if(huesped.getDNI()==elemento.getDNI()){
                huesped.setEstado("Inactivo");
            }
        }
    }

    @Override
    public void mostrarLista(List<Huesped> lista) {

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-15s %-10s%n",
                "DNI", "Nombre", "Apellido","Teléfono", "Estado");
        System.out.println("------------------------------------------------------------------------------------------------------");

        // Recorre y muestra la lista de huéspedes
        for (Huesped huesped : lista) {
            System.out.printf("%-10d %-20s %-20s %-15d %-10d%n",
                    huesped.getDNI(),
                    huesped.getNombre(),
                    huesped.getApellido(),
                    huesped.getTelefono(),
                    huesped.getEstado());
        }
        System.out.println("------------------------------------------------------------------------------------------------------");

    }

    @Override
    public Huesped obtenerPorId(List<Huesped> lista, int id) {

        for (Huesped huesped : lista) {
            if (huesped.getDNI()== id) {
                return huesped;
            }
        }
        return null;

    }

    public void leerHuespedActivo(List<Huesped> listaHuesped) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-15s %-15s %-12s %-15s %-20s %-15s %-15s%n",
                "DNI", "Nombre", "Apellido", "Teléfono", "Dirección", "Usuario", "Contraseña");
        System.out.println("--------------------------------------------------------------------------------------");

        boolean hayHuespedesActivos = false;

        for (Huesped huesped : listaHuesped) {

            if (huesped.getEstado().equals("Activo")) {
                System.out.printf("%-4d %-15s %-15s %-12s %-15s %-20s %-15s %-15s%n",
                        huesped.getDNI(), huesped.getNombre(), huesped.getApellido(),
                        huesped.getTelefono(), huesped.getDireccion(), huesped.getUsuario(), huesped.getContrasena());
                hayHuespedesActivos = true; // Hay al menos un huésped activo
            }
        }

        if (!hayHuespedesActivos) {
            System.out.println("No hay huéspedes activos en el sistema.");
        }

        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void leerHuespedInactivo(List<Huesped> listaHuesped) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-15s %-15s %-12s %-15s %-20s %-15s %-15s%n",
                "DNI", "Nombre", "Apellido", "Teléfono", "Dirección", "Usuario", "Contraseña");
        System.out.println("--------------------------------------------------------------------------------------");

        boolean hayHuespedesInactivos = false; // Para verificar si hay huéspedes activos

        // Imprimimos toda la lista
        for (Huesped huesped : listaHuesped) {
            // Verificamos si está activo
            if (huesped.getEstado().equals("Activo")) {
                System.out.printf("%-4d %-15s %-15s %-12s %-15s %-20s %-15s %-15s%n",
                        huesped.getDNI(), huesped.getNombre(), huesped.getApellido(),
                        huesped.getTelefono(), huesped.getDireccion(), huesped.getUsuario(), huesped.getContrasena());
                hayHuespedesInactivos = true; // Hay al menos un huésped activo
            }
        }

        // Mensaje si no hay huéspedes activos
        if (!hayHuespedesInactivos) {
            System.out.println("No hay huéspedes inactivos en el sistema.");
        }

        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void buscarHuespedXDNI(List<Huesped> listaHuesped, int dni) {
        boolean find = false;
        for (Huesped huesped : listaHuesped) {
            // Buscamos el DNI
            if (huesped.getDNI() == dni) {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-12s %-15s %-15s %-10s %-20s %-15s %-15s\n",
                        "DNI", "Nombre", "Apellido", "Telefono", "Direccion", "Usuario", "Contrasena");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-12s %-15s %-15s %-10s %-20s %-15s %-15s\n",
                        huesped.getDNI(),
                        huesped.getNombre(),
                        huesped.getApellido(),
                        huesped.getTelefono(),
                        huesped.getDireccion(),
                        huesped.getUsuario(),
                        huesped.getContrasena()
                );
                find = true;
                break;  // Si encuentras el huésped, no necesitas seguir buscando
            }
        }
        if (!find) {
            System.out.println("Huesped no encontrado");
        }
    }
}

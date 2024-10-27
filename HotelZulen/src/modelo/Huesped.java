package modelo;

import java.util.List;

public class Huesped implements IActualizar<Huesped>{
    private int ID;
    private String nombre;
    private String apellido;
    private int DNI;
    private int telefono;
    private String direccion;
    private String usuario;
    private String contrasena;
    private int estado;

    public Huesped(int ID, String nombre, String apellido, int DNI, int telefono, String direccion, String usuario, String contrasena, int estado) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
    }
    
   public Huesped(){
       
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

    public int getID() {
        return ID;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    public int getEstado(){
        return estado;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public void agregar(List<Huesped> lista, Huesped elemento) {
        lista.add(elemento);
    }

    @Override
    public void actualizar(List<Huesped> lista, Huesped elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(List<Huesped> lista, Huesped elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarLista(List<Huesped> lista) {

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-20s %-10s %-15s %-10s%n", 
            "ID", "Nombre", "Apellido", "DNI", "Teléfono", "Estado");
        System.out.println("------------------------------------------------------------------------------------------------------");

        // Recorre y muestra la lista de huéspedes
        for (Huesped huesped : lista) {
            System.out.printf("%-10d %-20s %-20s %-10d %-15d %-10d%n",
                huesped.getID(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getDNI(),
                huesped.getTelefono(),
                huesped.getEstado()); 
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
    
    }

    @Override
    public Huesped obtenerPorId(List<Huesped> lista, int id) {
        
        for(Huesped huesped : lista){
            if(huesped.getID()== id){
                return huesped;
            }
        }
        return null;
    
    }
    
    public void leerHuespedActivo(List<Huesped> listaHuesped) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-15s %-15s %-12s %-15s %-20s %-15s %-15s%n",
            "ID", "Nombre", "Apellido", "DNI", "Teléfono", "Dirección", "Usuario", "Contraseña");
        System.out.println("--------------------------------------------------------------------------------------");

        boolean hayHuespedesActivos = false;
        
        for (Huesped huesped : listaHuesped) {
        
            if (huesped.getEstado() == 1) {
                System.out.printf("%-4d %-15s %-15s %-12s %-15s %-20s %-15s %-15s%n",
                    huesped.getID(), huesped.getNombre(), huesped.getApellido(), huesped.getDNI(),
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
            "ID", "Nombre", "Apellido", "DNI", "Teléfono", "Dirección", "Usuario", "Contraseña");
    System.out.println("--------------------------------------------------------------------------------------");

    boolean hayHuespedesInactivos = false; // Para verificar si hay huéspedes activos

    // Imprimimos toda la lista
    for (Huesped huesped : listaHuesped) {
        // Verificamos si está activo
        if (huesped.getEstado() == 0) {
            System.out.printf("%-4d %-15s %-15s %-12s %-15s %-20s %-15s %-15s%n",
                    huesped.getID(), huesped.getNombre(), huesped.getApellido(), huesped.getDNI(),
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
            System.out.printf("%-4s %-15s %-15s %-12s %-10s %-20s %-15s %-15s\n",
                    "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Usuario", "Contrasena");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-4s %-15s %-15s %-12s %-10s %-20s %-15s %-15s\n", 
                    huesped.getID(), 
                    huesped.getNombre(), 
                    huesped.getApellido(), 
                    huesped.getDNI(),
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

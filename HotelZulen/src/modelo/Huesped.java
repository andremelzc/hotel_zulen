package modelo;


import Persistencia.HuespedRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Huesped   {
    
    int DNI;
    String nombre;
    String apellido;
    int telefono;
    String direccion;
    String usuario;
    String contrasena;
    String estado;
    Boolean esTitular;
    LocalDateTime FechaCrea;
    LocalDateTime FechaMod;
    private HuespedRepository repo;

    public Huesped(int DNI, String nombre, String apellido, int telefono, String direccion, String usuario, String contrasena, String estado, Boolean esTitular) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
        this.esTitular = esTitular;
        this.FechaCrea = LocalDateTime.now();
        this.FechaMod = LocalDateTime.now();
        this.repo = new HuespedRepository();
    }

    public Huesped(int DNI, String nombre, String apellido, int telefono, String direccion, String usuario, String contrasena, String estado, Boolean esTitular, LocalDateTime FechaCrea, LocalDateTime FechaMod) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
        this.esTitular = esTitular;
        this.FechaCrea = FechaCrea;
        this.FechaMod = FechaMod;
        this.repo = new HuespedRepository();
    }
    
    

    public Huesped() {
        this.repo = new HuespedRepository();
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getEsTitular() {
        return esTitular;
    }

    public void setEsTitular(Boolean esTitular) {
        this.esTitular = esTitular;
    }

    public LocalDateTime getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(LocalDateTime FechaCrea) {
        this.FechaCrea = FechaCrea;
    }
    
    public LocalDateTime getFechaMod() {
        return FechaMod;
    }

    public void setFechaMod() {
        this.FechaMod = LocalDateTime.now();
    }

    public void setFechaMod(LocalDateTime FechaMod) {
        this.FechaMod = FechaMod;
    }

    
    public void agregarHuesped(int DNI, String nombre,String  apellido,int  telefono,String  direccion,String  usuario, String contrasena,String  estado,Boolean esTitular){
        Huesped huesped = new Huesped(DNI, nombre, apellido, telefono, direccion, usuario, contrasena, estado, esTitular);
        repo.crear(huesped);
    }
    public void eliminarHuesped(){
        
    }
    public Huesped obtenerHuespedTitularXidReserva(int idReserva){
        return repo.obtenerHuespedTitutlarxIdReserva(idReserva);
    }
    
    public Huesped obtenerXDni(int DNI){
        return repo.obtener(DNI);
    }

}

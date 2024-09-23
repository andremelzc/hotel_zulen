/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;
import Controlador.PersonalCrud;//Necesario para manipular al Personal -> Admin Recepcionista
import Controlador.HuespedCrud;//Necesario para manipular al Personal -> Sobretodo verificar existencia en el Inicio de Sesión
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Miguel Giron
 */
public class InicioSesion {
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
    public boolean verificarExistenciaUsuario(String nombre_usuario){
        boolean existe = false;
        HuespedCrud huesped = new HuespedCrud();
        PersonalCrud personal = new PersonalCrud();
            if(huesped.existeHuespedUsuaro(nombre_usuario) && personal.existePersonalUsuaro(nombre_usuario)){
                System.out.println("Registro Peligroso: Existen 2 Registros con el mismo Nombre");
            }else if(huesped.existeHuespedUsuaro(nombre_usuario)){
                existe = true;
                setCategoria("Huesped");
                System.out.println("Huesped: ");
            }else if(personal.existePersonalUsuaro(nombre_usuario)){
                setCategoria("Personal");
                existe = true;
                System.out.println("Personal: ");
            }else{
                System.out.println("No se Encontro ningun registro con el Nombre de Usuario Ingresado");
            }
            
        return existe;
    }
    
    public boolean verificarInicioPersonal(String usu, String contra){
        boolean validez = false;
        try {
                    System.out.println("Intentamos en Personal");
                    CSVReader reader = new CSVReader(new FileReader("personal.csv"));
                    String[] nextLine;

                    try {
                        while ((nextLine = reader.readNext()) != null) {
                             if ("1".equals(nextLine[9]) && usu.equals(nextLine[6]) && contra.equals(nextLine[7]) ) {
                                System.out.println("Bienvenido:");
                                System.out.println(" Ha ingresado como " +  nextLine[6]+", "+ nextLine[1] + " " + nextLine[2]);

                                validez = true;
                            }

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CsvValidationException ex) {
                        Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
                    }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(!validez){
            System.out.println("Usuario Inactivo o Contraseña Incorrecta");
        }
        return validez;
    }
    
    public boolean verificarInicioHuesped(String usu, String contra){
        boolean validez = false;
        
        try {
                    System.out.println("Intentamos en Huesped");
                    CSVReader readers = new CSVReader(new FileReader("huespedes.csv"));
                    String[] nextLines;

                    try {
                        while ((nextLines = readers.readNext()) != null) {
                            if ("1".equals(nextLines[3]) && usu.equals(nextLines[4]) && contra.equals(nextLines[5]) ) {
                                System.out.println("Bienvenido:");
                                System.out.println(" Ha ingresado como Huesped: " +  nextLines[4]+", "+ nextLines[5]);

                                validez = true;
                            }
                            

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CsvValidationException ex) {
                        Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
                    }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        if(!validez){
            System.out.println("Usuario Inactivo o Contraseña Incorrecta");
        }
        return validez;
    }
    
    public boolean verificarValidezPersonal(String usu, String contra) {
        
        
        
        boolean validez = false;
        if(categoria.equalsIgnoreCase("Personal")){
            validez = verificarInicioPersonal(usu, contra);

        }else if(categoria.equalsIgnoreCase("Huesped")){
            validez = verificarInicioHuesped(usu, contra);

        }else{
            System.out.println("NEIN");
        }        
//        if(validez){
//            System.out.println("Bienvenido: "+usu+", "+contra+" haz iniciado como: "+categoria);
//        }
    return validez;
    }
  
    
    
}

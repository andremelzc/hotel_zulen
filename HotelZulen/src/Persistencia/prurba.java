/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Persistencia;

import Persistencia.DatabaseConnection;
/**
 *
 * @author Fabrizio Mantari
 */
public class prurba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DatabaseConnection prueba = new DatabaseConnection();
        DatabaseConnection.testConnection();
    }
    
}

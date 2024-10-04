/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import modelo.Personal;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.AmaDeLlaves;

/**
 *
 * @author PC
 */
public class PersonalCrud {

    List<Personal> listaPersonal = new ArrayList<>();

    // Función para cargar el csv en una lista
    public List<Personal> cargarCSVlista() {
        List<Personal> listaPersonales = new ArrayList<>();
        try {
            // Leemos el csv
            CSVReader reader = new CSVReader(new FileReader("personal.csv"));
            String[] nextLine;

            try {

                while ((nextLine = reader.readNext()) != null) {
                    Personal personal = new Personal(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2], Integer.parseInt(nextLine[3]),
                            Integer.parseInt(nextLine[4]), nextLine[5], nextLine[6], nextLine[7], nextLine[8], Integer.parseInt(nextLine[9]));
                    listaPersonales.add(personal);

                }

            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersonales;
    }

    // Función para leer todo el personal
    public void leerPersonal(List<Personal> listaPersonal) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s %s%n",
                "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Usuario", "Contraseña", "Funcion");
        System.out.println("--------------------------------------------------------------------------------------");
        // Imprimimos toda la lista
        for (Personal personal : listaPersonal) {
            // Verificamos si está activo
            if (personal.getEstado() == 1) {
                System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s %s%n", personal.getID(), personal.getNombre(), personal.getApellido(), personal.getDNI(),
                        personal.getTelefono(), personal.getDireccion(), personal.getUsuario(), personal.getContrasena(), personal.getFuncion());
            }
        }

    }

    // Funcion para buscar personal
    public void buscarPersonal(List<Personal> listaPersonal, int id_buscar) {
        boolean find = false;
        for (Personal personal : listaPersonal) {
            // Buscamos el ID
            if (personal.getID() == id_buscar && personal.getEstado() == 1) {
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s %s%n",
                        "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Usuario", "Contraseña", "Funcion");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s %s%n", personal.getID(), personal.getNombre(), personal.getApellido(), personal.getDNI(),
                        personal.getTelefono(), personal.getDireccion(), personal.getUsuario(), personal.getContrasena(), personal.getFuncion());
                find = true;
            }
        }
        
        if(!find){
            System.out.println("Personal no encontrado");
        }
    }

    // Funcion para agregar personal a una lista, luego se usa otra para escribir en el csv
    public void agregarPersonal(Personal personal, int contador) {
        //Cambiamos el id en relación a la base de datos
        personal.setID(numeroLineas() + contador);

        //Agregamos a la listaa
        listaPersonal.add(personal);

        //Confirmamos que lo agregamos
        System.out.println("-----------------------------");
        System.out.println("Personal agregado: " + personal.getNombre() + " " + personal.getApellido());
        //if(personal.getFuncion().equals("Ama de LLaves")){
        //listaAmaLlaves.add(new AmaDeLlaves(personal.getID(),personal.getNombre(),personal.getApellido(),personal.getDNI(),personal.getTelefono(),
        //personal.getDireccion(),personal.getUsuario(),personal.getContrasena(),personal.getEstado()));
        //}
    }

    // Funcion para escribir en el csv
    public void escribirCSV() {
        //Lista para enviar al CSV
        try (CSVWriter escritor = new CSVWriter(new FileWriter("personal.csv", true))) {
            for (Personal personal : listaPersonal) {
                String[] datos = {String.valueOf(personal.getID()), personal.getNombre(), personal.getApellido(), String.valueOf(personal.getDNI()), String.valueOf(personal.getTelefono()), personal.getDireccion(), personal.getUsuario(), personal.getContrasena(), personal.getFuncion(), String.valueOf(personal.getEstado())}; //El 1 significa que la cuenta está activa 
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Funcion para contar numero de lineas en el csv, sirve para hallar el id
    public int numeroLineas() {
        int numero = 0;
        try (CSVReader lector = new CSVReader(new FileReader("personal.csv"))) {
            try {
                while ((lector.readNext()) != null) {
                    numero++;
                }
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, "Error al leer el archivo CSV", ex);
        }

        return numero;
    }

    public boolean existePersonal(List<Personal> listaPersonal, int id_buscar) {
        boolean existe = false;
        for(Personal personal : listaPersonal){
            if(personal.getID()==id_buscar){
                existe = true;
            }
        }
        return existe;
    }

    public boolean existePersonalUsuaro(List<Personal> listaPersonal, String usuario_buscar) {
        boolean existe = false;
        for(Personal personal : listaPersonal){
            if(personal.getUsuario().equals(usuario_buscar)){
                existe=true;
            }
        }
        return existe;
    }


    public void actualizarPersonal(int id_actualizar) {
        //Usamos una colección
        List<String[]> allData = new ArrayList<>();
        PersonalCrud personalCrud = new PersonalCrud();
        Scanner sc = new Scanner(System.in);
        boolean find = false;
        try {
            CSVReader lector = new CSVReader(new FileReader("personal.csv"));
            try {
                //Se lee toda la información
                allData = lector.readAll();
                for (String[] nextLine : allData) {
                    if (id_actualizar == Integer.parseInt(nextLine[0]) && "1".equals(nextLine[9])) {
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %s%n",
                                "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Funcion");
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %s%n", nextLine[0], nextLine[1], nextLine[2], nextLine[3],
                                nextLine[4], nextLine[5], nextLine[8]);
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.println("Nuevos Datos");
                        //Nombre
                        System.out.println("Nombre: ");
                        nextLine[1] = sc.nextLine();
                        //Apellido
                        System.out.println("Apellido: ");
                        nextLine[2] = sc.nextLine();
                        //DNI
                        boolean flag_dni = false;
                        int dni;
                        do {
                            System.out.println("DNI");
                            nextLine[3] = String.valueOf(sc.nextInt());
                            if (String.valueOf(nextLine[3]).length() == 8) {
                                flag_dni = true;
                            }
                            if (!flag_dni) {
                                System.out.println("-----------------------------");
                                System.out.println("Ingrese un DNI correcto");
                                System.out.println("-----------------------------");
                            }
                        } while (!flag_dni);
                        //Telefono
                        boolean flag_telefono = false;
                        int telefono;
                        do {
                            System.out.println("Telefono");
                            nextLine[4] = String.valueOf(sc.nextInt());
                            if (String.valueOf(nextLine[4]).length() == 9) {
                                flag_telefono = true;
                            }
                            if (!flag_telefono) {
                                System.out.println("-----------------------------");
                                System.out.println("Ingrese un telefono correcto");
                                System.out.println("-----------------------------");
                            }
                        } while (!flag_telefono);
                        //Direccion y limpiamos buffer
                        sc.nextLine();
                        System.out.println("Dirección");
                        nextLine[5] = sc.nextLine();
                        //Agregado de mi parte para verificar el inicio
                        String usuario;
                        boolean flag_usuario = false;
                        do {
                            System.out.println("Usuario: ");
                            nextLine[6] = sc.nextLine();
                            List<Personal> lista_Personal = cargarCSVlista();
                            if (personalCrud.existePersonalUsuaro(lista_Personal, nextLine[6])) {
                                flag_usuario = true;
                                System.out.println("-----------------------------");
                                System.out.println("Ingrese un usuario no existente");
                                System.out.println("-----------------------------");
                            } else {
                                flag_usuario = false;
                            }
                        } while (flag_usuario);

                        //Contraseña
                        System.out.println("Contrasena: ");
                        nextLine[7] = sc.nextLine();
                        //Funcion
                        boolean flag_funcion = false;
                        String funcion;
                        do {
                            System.out.println("Funcion");
                            nextLine[8] = sc.nextLine();
                            if ("Administrador".equals(nextLine[8]) || "Recepcionista".equals(nextLine[8]) || "Ama de LLaves".equals(nextLine[8]) || "Jefe de Cocina".equals(nextLine[8])) {
                                flag_funcion = true;
                            }
                            if (!flag_funcion) {
                                System.out.println("-----------------------------");
                                System.out.println("Funcion no existente, vuelva a ingresar");
                                System.out.println("-----------------------------");
                            }
                        } while (!flag_funcion);
                        find = true;
                    }
                }
                if (!find) {
                    System.out.println("-----------------------------");
                    System.out.println("No se encontro algun personal con dicho ID");
                }
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Abrimos otro para sobre escribir
        try (CSVWriter escritor = new CSVWriter(new FileWriter("personal.csv"))) {

            escritor.writeAll(allData);
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarPersonal(int id_eliminar) {
        List<String[]> allData = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            CSVReader lector = new CSVReader(new FileReader("personal.csv"));
            try {
                //Se lee toda la información
                allData = lector.readAll();
                for (String[] nextLine : allData) {
                    if (id_eliminar == Integer.parseInt(nextLine[0])) {
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %s%n",
                                "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Funcion");
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %s%n", nextLine[0], nextLine[1], nextLine[2], nextLine[3],
                                nextLine[4], nextLine[5], nextLine[8]);
                        System.out.println("--------------------------------------------------------------------------------------");
                        nextLine[9] = "0";
                    }
                }
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Abrimos otro para sobre escribir
        try (CSVWriter escritor = new CSVWriter(new FileWriter("personal.csv"))) {

            escritor.writeAll(allData);
        } catch (IOException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerTodoAmaLlaves() {
        try {
            CSVReader reader = new CSVReader(new FileReader("personal.csv"));
            String[] nextLine;
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s %s%n",
                    "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Usuario", "Contraseña", "Funcion");
            System.out.println("--------------------------------------------------------------------------------------");
            try {
                while ((nextLine = reader.readNext()) != null) {

                    if ("1".equals(nextLine[9]) && "Ama de Llaves".equals(nextLine[8])) {
                        System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s %s%n", nextLine[0], nextLine[1], nextLine[2], nextLine[3],
                                nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8]);

                    }

                }
                System.out.println("--------------------------------------------------------------------------------------");
            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

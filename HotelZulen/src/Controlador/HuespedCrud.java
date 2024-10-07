package Controlador;

import modelo.Huesped;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Personal;

public class HuespedCrud {

    List<Huesped> listaHuesped = new ArrayList<>();

    public List<Huesped> getListaHuesped() {
        return listaHuesped;
    }
    
    

    public void agregarHuesped(Huesped huesped) {
        listaHuesped.add(huesped);
        System.out.println("-----------------------------");
        System.out.println("Huesped agregado: " + huesped.getNombre() + " " + huesped.getApellido());
        escribirCSV();
    }

    public void escribirCSV() {
        try (CSVWriter escritor = new CSVWriter(new FileWriter("huespedes.csv", true))) {
            for (Huesped huesped : listaHuesped) {
                String[] datos = {String.valueOf(numeroLineas() + 1), huesped.getNombre(), huesped.getApellido(), String.valueOf(huesped.getDNI()),
                    String.valueOf(huesped.getTelefono()), huesped.getDireccion(), huesped.getUsuario(), huesped.getContrasena(), "1",};   //El 1 significa que la cuenta está activa
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Huesped> cargarCSVlista() {
        List<Huesped> listaHuespedes = new ArrayList<>();
        try {
            // Leemos el csv
            CSVReader reader = new CSVReader(new FileReader("huespedes.csv"));
            String[] nextLine;

            try {

                while ((nextLine = reader.readNext()) != null) {
                    Huesped huesped = new Huesped(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2], Integer.parseInt(nextLine[3]),
                            Integer.parseInt(nextLine[4]), nextLine[5], nextLine[6], nextLine[7], Integer.parseInt(nextLine[8]));
                    listaHuespedes.add(huesped);

                }

            } catch (IOException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonalCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaHuespedes;
    }

    public HashMap<Integer, Huesped> cargarCSVHash() {
        HashMap<Integer, Huesped> mapaHuesped = new HashMap<>();
        try {

            CSVReader reader = new CSVReader(new FileReader("huespedes.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    Huesped huesped = new Huesped(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2], Integer.parseInt(nextLine[3]),
                            Integer.parseInt(nextLine[4]), nextLine[5], nextLine[6], nextLine[7], Integer.parseInt(nextLine[8]));

                    mapaHuesped.put(huesped.getID(), huesped);
                }
            } catch (IOException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapaHuesped;
    }

    public int numeroLineas() {
        int numero = 0;
        try (CSVReader lector = new CSVReader(new FileReader("huespedes.csv"))) {
            try {
                while ((lector.readNext()) != null) {
                    numero++;
                }
            } catch (CsvValidationException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, "Error al leer el archivo CSV", ex);
        }

        return numero;
    }

    public void leerHuesped(List<Huesped> listaHuesped) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s",
                "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Usuario", "Contraseña");
        System.out.println("--------------------------------------------------------------------------------------");
        // Imprimimos toda la lista
        for (Huesped huesped : listaHuesped) {
            // Verificamos si está activo
            if (huesped.getEstado() == 1) {
                System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s", huesped.getID(), huesped.getNombre(), huesped.getApellido(), huesped.getDNI(),
                        huesped.getTelefono(), huesped.getDireccion(), huesped.getUsuario(), huesped.getContrasena());
            }
        }
    }

    public void buscarHuesped(List<Huesped> listaHuesped, int id_buscar) {
        boolean find = false;
        for (Huesped huesped : listaHuesped) {
            //Buscamos el ID
            if (huesped.getID() == id_buscar && huesped.getEstado() == 1) {
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s",
                        "ID", "Nombre", "Apellido", "DNI", "Telefono", "Direccion", "Usuario", "Contraseña");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf("%-4s %-10s %-15s %-12s %-10s %-20s %-15s %-15s", huesped.getID(), huesped.getNombre(), huesped.getApellido(), huesped.getDNI(),
                        huesped.getTelefono(), huesped.getDireccion(), huesped.getUsuario(), huesped.getContrasena());
                find = true;
            }
        }
        if(!find){
            System.out.println("Huesped no encontrado");
        }
    }

    //Funcion Agregada por Miguel para el Inicio de Sesión
    public boolean existeHuespedUsuaro(String usuario_buscar) {
        boolean existe = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("huespedes.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    if (usuario_buscar.equals(nextLine[4])) {
                        existe = true;
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
        return existe;
    }
    //Fin de la Función agregada por Miguel para el Inicio de Sesión

    public boolean existeHuesped(int id_huesped) {
        boolean existe = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("huespedes.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    if (id_huesped == Integer.parseInt(nextLine[0])) {
                        existe = true;
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
        return existe;
    }
    
  
    public void actualizarHuesped(int id_actualizar) {
        List<String[]> allData = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean find = false;
        try {
            CSVReader lector = new CSVReader(new FileReader("huespedes.csv"));
            try {
                //Se lee toda la información
                allData = lector.readAll();
                for (String[] row : allData) {
                    if (id_actualizar == Integer.parseInt(row[0]) && "1".equals(row[3])) {
                        System.out.println("-----------------------------");
                        System.out.println("ID Nombre   Apellido");
                        System.out.println("-----------------------------");
                        System.out.println(row[0] + "  " + row[1] + "  " + row[2]);
                        System.out.println("-----------------------------");
                        System.out.println("Nuevos Datos");
                        System.out.println("-----------------------------");
                        System.out.println("Nombre: ");
                        row[1] = sc.nextLine();
                        System.out.println("Apellido: ");
                        row[2] = sc.nextLine();
                        find = true;
                    }
                }
                if (!find) {
                    System.out.println("-----------------------------");
                    System.out.println("No se encontro algun huesped con dicho ID");
                }
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Abrimos otro para sobre escribir
        try (CSVWriter escritor = new CSVWriter(new FileWriter("huespedes.csv"))) {

            escritor.writeAll(allData);
        } catch (IOException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarHuesped(int id_eliminar) {
        List<String[]> allData = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            CSVReader lector = new CSVReader(new FileReader("huespedes.csv"));
            try {
                //Se lee toda la información
                allData = lector.readAll();
                for (String[] row : allData) {
                    if (id_eliminar == Integer.parseInt(row[0])) {
                        System.out.println("-----------------------------");
                        System.out.println("ID Nombre   Apellido");
                        System.out.println("-----------------------------");
                        System.out.println(row[0] + "  " + row[1] + "  " + row[2]);
                        row[3] = "0";
                    }
                }
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Abrimos otro para sobre escribir
        try (CSVWriter escritor = new CSVWriter(new FileWriter("huespedes.csv"))) {

            escritor.writeAll(allData);
        } catch (IOException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

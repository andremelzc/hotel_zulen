package Controlador;

import modelo.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Huesped;

public class HabitacionCrud {

    List<Habitacion> listaHabitacion = new ArrayList<>();
    
    public List<Habitacion> cargarCSVlista() {
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        try {
            // Leemos el csv
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                // Check if the line has the expected number of columns
                if (nextLine.length >= 4) { // Ensure there are at least 4 columns
                    TipoDeHabitacion tipoHabitacion = new TipoDeHabitacion(nextLine[1]);
                    Habitacion habitacion = new Habitacion(Integer.parseInt(nextLine[0]), tipoHabitacion, Integer.parseInt(nextLine[2]), nextLine[3]);
                    listaHabitaciones.add(habitacion); //agregando a la Lista
                } else {
                    System.err.println("Error: línea con datos insuficientes: " + Arrays.toString(nextLine));
                }
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listaHabitaciones; //Retorna la lista cargada
}

     // Método para cargar las habitaciones desde CSV a la lista
    public HashMap<Integer, Habitacion> cargarCSVHash(){
        HashMap<Integer, Habitacion> mapaHabitacion = new HashMap<>();
        try {
            
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    TipoDeHabitacion tipoDeHabitacion = new TipoDeHabitacion(nextLine[1]);
                    Habitacion habitacion = new Habitacion(Integer.parseInt(nextLine[0]), tipoDeHabitacion, Integer.parseInt(nextLine[2]), nextLine[3]);
                    
                    mapaHabitacion.put(habitacion.getId(), habitacion);
                }
            } catch (IOException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuespedCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapaHabitacion;
    }
    

    public void agregarHabitacion(Habitacion habitacion,List<Habitacion> listaHabitacion) {
        listaHabitacion.add(habitacion);
        System.out.println("-----------------------------");
        
        System.out.println("Habitacion " + habitacion.getTipoHabitacion().getConcepto() + " agregada en el Piso " + habitacion.getPiso());
        
        escribirCSV();
    }

    public void escribirCSV() {
        try (CSVWriter escritor = new CSVWriter(new FileWriter("habitaciones.csv", true))) {
            for (Habitacion habitacion : listaHabitacion) {
                String[] datos = {String.valueOf(numeroLineas() + 1), habitacion.getTipoHabitacion().getConcepto(), String.valueOf(habitacion.getPiso()),
                habitacion.getEstado()};   //El 1 significa que la cuenta está activa
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int numeroLineas() {
        int numero = 0;
        try (CSVReader lector = new CSVReader(new FileReader("habitaciones.csv"))) {
            try {
                while ((lector.readNext()) != null) {
                    numero++;
                }
            } catch (CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, "Error al leer el archivo CSV", ex);
        }

        return numero;
    }

    public void leerTodoHabitacion(List<Habitacion> listaHabitacion) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-10s %-15s %-12s%n", "ID", "Tipo de habitacion", "Piso", "Estado");
        System.out.println("--------------------------------------------------------------------------------------");
        // Imprimimos toda la lista
        for (Habitacion habitacion : listaHabitacion) {
            // Verificamos si está activo
            
                System.out.printf("%-4s %-10s %-15s %-12s%n", 
                habitacion.getId(), 
                habitacion.getTipoHabitacion().getConcepto(), 
                habitacion.getPiso(), 
                habitacion.getEstado());
            
        }
    }
    
    public void leerTodoHabitacionDisponible() {
        try {
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;
            System.out.println("-----------------------------");
            System.out.println("ID Tipo   Piso    Estado    Servicio    ");
            System.out.println("-----------------------------");
            try {
                while ((nextLine = reader.readNext()) != null) {
                    if ("Disponible".equalsIgnoreCase(nextLine[3])) {
                        System.out.println(nextLine[0] + "  " + nextLine[1] + "    " + nextLine[2] + "    " + nextLine[3]);
                        System.out.println("-----------------------------");
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean buscarHabitacion(int id_buscar) {
        boolean find = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {

                    if (id_buscar == Integer.parseInt(nextLine[0]) && "1".equals(nextLine[3])) {
                        System.out.println("-----------------------------");
                        System.out.println("ID Tipo   Piso");
                        System.out.println("-----------------------------");
                        System.out.println(nextLine[0] + "  " + nextLine[1] + "    " + nextLine[2]);
                        find = true;
                    }

                }
                if (!find) {
                    System.out.println("No se encontro algun habitacion con dicho ID");
                }
            } catch (IOException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public void actualizarHabitacion(int id_actualizar) {
        List<String[]> allData = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean find = false;
        try {
            CSVReader lector = new CSVReader(new FileReader("habitaciones.csv"));
            try {
                //Se lee toda la información
                allData = lector.readAll();
                for (String[] row : allData) {
                    if (id_actualizar == Integer.parseInt(row[0]) && "1".equals(row[3])) {
                        System.out.println("-----------------------------");
                        System.out.println("ID Tipo   Piso");
                        System.out.println("-----------------------------");
                        System.out.println(row[0] + "  " + row[1] + "  " + row[2]);
                        System.out.println("-----------------------------");
                        System.out.println("Nuevos Datos");
                        System.out.println("-----------------------------");
                        System.out.println("Tipo: ");
                        row[1] = sc.nextLine();
                        System.out.println("Piso: ");
                        row[2] = sc.nextLine();
                        find = true;
                    }
                }
                if (!find) {
                    System.out.println("-----------------------------");
                    System.out.println("No se encontro algun habitacion con dicho ID");
                }
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Abrimos otro para sobre escribir
        try (CSVWriter escritor = new CSVWriter(new FileWriter("habitaciones.csv"))) {

            escritor.writeAll(allData);
        } catch (IOException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarHabitacion(int id_eliminar) {
        List<String[]> allData = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            CSVReader lector = new CSVReader(new FileReader("habitaciones.csv"));
            try {
                //Se lee toda la información
                allData = lector.readAll();
                for (String[] row : allData) {
                    if (id_eliminar == Integer.parseInt(row[0])) {
                        System.out.println("-----------------------------");
                        System.out.println("ID Tipo   Piso");
                        System.out.println("-----------------------------");
                        System.out.println(row[0] + "  " + row[1] + "  " + row[2]);
                        row[3] = "0";
                    }
                }
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Abrimos otro para sobre escribir
        try (CSVWriter escritor = new CSVWriter(new FileWriter("habitaciones.csv"))) {

            escritor.writeAll(allData);
        } catch (IOException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean existeHabitacion(int id_habitacion) {
        boolean existe = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    if (Integer.parseInt(nextLine[0]) == id_habitacion) {
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

    public boolean habitacionOcupada(int id_habitacion) {
        boolean ocupada = false;
        try {
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    if (Integer.parseInt(nextLine[0]) == id_habitacion && nextLine[3].equals("Ocupado")) {
                        ocupada= true;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ocupada;
    }

}

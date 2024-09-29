package Controlador;

import modelo.Habitacion;
import modelo.TipoDeHabitacion;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HabitacionCrud {

    List<Habitacion> listaHabitacion = new ArrayList<>();

    public void agregarHabitacion(Habitacion habitacion) {
        listaHabitacion.add(habitacion);
        System.out.println("-----------------------------");
        System.out.println("Habitacion " + habitacion.getTipoHabitacion().getConcepto() + " agregada en el Piso " + habitacion.getPiso());
        escribirCSV();
    }

    public void escribirCSV() {
        try (CSVWriter escritor = new CSVWriter(new FileWriter("habitaciones.csv", true))) {
            for (Habitacion habitacion : listaHabitacion) {
                String[] datos = {String.valueOf(numeroLineas() + 1), habitacion.getTipoHabitacion().getConcepto(), Integer.toString(habitacion.getPiso()), "1", habitacion.getEstado(), habitacion.getServicio(), Integer.toString(habitacion.getIDHuesped())};   //El 1 significa que la cuenta está activa
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

    public void leerTodoHabitacion() {
        try {
            CSVReader reader = new CSVReader(new FileReader("habitaciones.csv"));
            String[] nextLine;
            System.out.println("-----------------------------");
            System.out.println("ID Tipo   Piso    Estado    Servicio    IDHuesped");
            System.out.println("-----------------------------");
            try {
                while ((nextLine = reader.readNext()) != null) {
                    if ("1".equals(nextLine[3])) {
                        System.out.println(nextLine[0] + "  " + nextLine[1] + "    " + nextLine[2] + "    " + nextLine[4] + "    " + nextLine[5] + "    " + nextLine[6]);
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
                    if (Integer.parseInt(nextLine[0]) == id_habitacion && nextLine[4].equals("Ocupado")) {
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

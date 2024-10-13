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

public class HabitacionCrud {
    Funcionalidades funcionalidades = new Funcionalidades();
    List<Habitacion> listaHabitacion = new ArrayList<>();
    public List<Habitacion> getListaHabitacion() {
        return listaHabitacion;
    }

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
    public HashMap<Integer, Habitacion> cargarCSVHash() {
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

    public void agregarHabitacion(Habitacion habitacion) {
        habitacion.setId(numeroLineas()+1);
        listaHabitacion.clear();
        listaHabitacion.add(habitacion);
        System.out.println("-----------------------------");

        System.out.println("Habitacion " + habitacion.getTipoHabitacion().getConcepto() + " agregada en el Piso " + habitacion.getPiso());

        escribirCSV(listaHabitacion);
    }

    public void escribirCSV(List<Habitacion> listaHabitacion) {
        try (CSVWriter escritor = new CSVWriter(new FileWriter("habitaciones.csv", true))) {
            for (Habitacion habitacion : listaHabitacion) {
                String[] datos = {String.valueOf(habitacion.getId()), habitacion.getTipoHabitacion().getConcepto(), String.valueOf(habitacion.getPiso()),
                    habitacion.getEstado()};   //El 1 significa que la cuenta está activa
                escritor.writeNext(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(HabitacionCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sobreescribirCSV(List<Habitacion> listaHabitacion) {
        try (CSVWriter escritor = new CSVWriter(new FileWriter("habitaciones.csv", false))) {  // Cambiar true por false para sobrescribir
            int id = 1; // Iniciar el contador desde 1
            for (Habitacion habitacion : listaHabitacion) {
                String[] datos = {
                    String.valueOf(habitacion.getId()), // Usamos el id que hemos inicializado
                    habitacion.getTipoHabitacion().getConcepto(),
                    String.valueOf(habitacion.getPiso()),
                    habitacion.getEstado()
                };
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

            System.out.printf("%-4s %-10s %-15s %-12s%n",
                    habitacion.getId(),
                    habitacion.getTipoHabitacion().getConcepto(),
                    habitacion.getPiso(),
                    habitacion.getEstado());

        }
    }

    public Habitacion buscarHabitacionPorId(int idHabitacion, List<Habitacion> listaHabitaciones) {
        for (Habitacion habitacion : listaHabitaciones) {
            if (habitacion.getId() == idHabitacion) {
                return habitacion;
            }
        }
        return null;  // Si no se encuentra la habitación
    }

    public void leerTodoHabitacionDisponible(List<Habitacion> listaHabitacion) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-10s %-15s %-12s%n", "ID", "Tipo", "Piso", "Estado");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Habitacion habitacion : listaHabitacion) {
            if (habitacion.getEstado().equals("Disponible")) {
                System.out.printf("%-4s %-10s %-15s %-12s%n",
                        habitacion.getId(),
                        habitacion.getTipoHabitacion().getConcepto(),
                        habitacion.getPiso(),
                        habitacion.getEstado());
            }

        }

    }

    public boolean buscarHabitacion(List<Habitacion> listaHabitaciones, int id_buscar) {
        boolean find = false;

        // Encabezado de la tabla
        System.out.println("------------------------------------------");
        System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
        System.out.println("------------------------------------------");

        // Buscar el ID en la lista de habitaciones
        for (Habitacion habitacion : listaHabitaciones) {
            if (habitacion.getId() == id_buscar) {
                // Imprimir detalles de la habitación si se encuentra
                System.out.printf("%-4s %-10s %-15s%n",
                        habitacion.getId(),
                        habitacion.getTipoHabitacion().getConcepto(),
                        habitacion.getPiso());
                find = true;
                break; // Salir del bucle si se encontró la habitación
            }
        }

        // Mensaje si no se encontró la habitación
        if (!find) {
            System.out.println("Habitación no encontrada.");
        }
        return find;
    }

    public void buscarHabitacionXId(List<Habitacion> listaHabitaciones, int id) {
        boolean find = false;

        // Encabezado de la tabla
        System.out.println("------------------------------------------");
        System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
        System.out.println("------------------------------------------");

        // Buscar el ID en la lista de habitaciones
        for (Habitacion habitacion : listaHabitaciones) {
            if (habitacion.getId() == id) {
                // Imprimir detalles de la habitación si se encuentra
                System.out.printf("%-4s %-10s %-15s%n",
                        habitacion.getId(),
                        habitacion.getTipoHabitacion().getConcepto(),
                        habitacion.getPiso());
                find = true;
                break; // Salir del bucle si se encontró la habitación
            }
        }

        // Mensaje si no se encontró la habitación
        if (!find) {
            System.out.println("Habitación no encontrada.");
        }
    }

    public void actualizarHabitacion(List<Habitacion> listaHabitaciones, int id_actualizar) {
        
        Scanner sc = new Scanner(System.in);
        boolean find = false;
        HabitacionCrud habitacionCrud = new HabitacionCrud();

        for (Habitacion habitacion : listaHabitaciones) {
            if (habitacion.getId() == id_actualizar) {
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
                TipoDeHabitacion tipoHabitacion = new TipoDeHabitacion(tipo);
                habitacion.setTipoHabitacion(tipoHabitacion);
                System.out.println("Piso: ");
                int piso = sc.nextInt();
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
        funcionalidades.vaciarCSV("habitaciones.csv");
        escribirCSV(listaHabitaciones);
        
    }

    public void eliminarHabitacion(List<Habitacion> listaHabitaciones, int id_eliminar) {
        for(Habitacion habitacion : listaHabitaciones){
            if(id_eliminar==habitacion.getId()){
                habitacion.setEstado("No disponible");
            }
        }
        funcionalidades.vaciarCSV("habitaciones.csv");
        escribirCSV(listaHabitaciones);
    }

    public boolean existeHabitacion(List<Habitacion> listaHabitaciones, int id_habitacion) {
        boolean existe = false;
        for(Habitacion habitacion : listaHabitaciones){
            if(habitacion.getId()==id_habitacion){
                existe=true;
            }
        }
        return existe;
    }

    public boolean habitacionOcupada(List<Habitacion> listaHabitaciones, int id_habitacion) {
        boolean ocupada = false;
        for(Habitacion habitacion : listaHabitaciones){
            if(habitacion.getEstado().equals("Ocupado")){
                ocupada = true;
            }
        }
        return ocupada;
    }

}

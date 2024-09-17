package pruebas_cuenca;

import pruebitas_andre.Personal;
import pruebitas_andre.PersonalCrud;
import pruebitas_andre.Reservación;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class CuencaMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HuespedCrud huespedC = new HuespedCrud();
        PersonalCrud personal = new PersonalCrud();
        HabitacionCrud habitacion = new HabitacionCrud();
        Reservación reservacion = new Reservación();

        CuencaMain obj = new CuencaMain();

        boolean flag = true;

        do {
            System.out.println("------------------");
            System.out.println("Menu de opciones");
            System.out.println("------------------");
            System.out.println("1. CRUD personal");
            System.out.println("2. CRUD huesped");
            System.out.println("3. CRUD habitacion");
            System.out.println("4. Asignar habitacion");
            System.out.println("5. Salir");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int op = sc.nextInt();
            sc.nextLine(); //Limpiar el buffer

            switch (op) {
                case 1:
                   obj.menuPersonal(sc);
                    break;
                case 2:
                    obj.menuHuesped(sc);
                    break;
                case 3:
                    obj.menuHabitacion(sc);
                    break;
                case 4:
                    reservacion.asignarReservacion(sc);
                    System.out.println("\n4");
                    break;
                case 5:
                    flag = false;
                    System.out.println("Saliendo...");
                    break;
            }
        } while (flag);
    }

    public void menuHuesped(Scanner sc) {
        HuespedCrud huespedCrud = new HuespedCrud();
        boolean flagHuesped = true;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD HUESPED");
            System.out.println("------------------");
            System.out.println("1. Agregar huesped");
            System.out.println("2. Leer todo huesped");
            System.out.println("3. Buscar huesped");
            System.out.println("4. Actualizar huesped");
            System.out.println("5. Eliminar huesped");
            System.out.println("6. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opHuesped = sc.nextInt();
            sc.nextLine();

            switch (opHuesped) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.println("Usuario: ");
                    String usuario = sc.nextLine();
                    System.out.println("Contraseña: ");
                    String contrasena = sc.nextLine();
                    //Traer un ID real
                    System.out.println("IDHabitacion: ");
                    int idHabitacion = sc.nextInt();
                    sc.nextLine();
                    // Permitir al usuario ingresar en otro formato / Traer la fecha desde la reserva
                    System.out.println("Fecha de ingreso (YYYY-MM-dd): ");
                    LocalDate fechaIngreso = LocalDate.parse(sc.nextLine());
                    System.out.println("Fecha de salida (YYYY-MM-dd): ");
                    LocalDate fechaSalida = LocalDate.parse(sc.nextLine());
                    Huesped nuevoHuesped = new Huesped(nombre, apellido, usuario, contrasena, idHabitacion, fechaIngreso, fechaSalida);
                    huespedCrud.agregarHuesped(nuevoHuesped);
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todo el huesped");
                    System.out.println("-----------------------------\n");
                    huespedCrud.leerTodoHuesped();
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    huespedCrud.buscarHuesped(id_buscar);
                    System.out.println("-----------------------------\n");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    huespedCrud.actualizarHuesped(id_actualizar);
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    huespedCrud.eliminarHuesped(id_eliminar);
                    System.out.println("-----------------------------\n");
                case 6:
                    flagHuesped = false;
                    break;
            }
        } while (flagHuesped);
    }

    public void menuPersonal(Scanner sc) {
        PersonalCrud personalCrud = new PersonalCrud();
        boolean flagPersonal = true;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD PERSONAL");
            System.out.println("------------------");
            System.out.println("1. Agregar personal");
            System.out.println("2. Leer todo personal");
            System.out.println("3. Buscar personal");
            System.out.println("4. Actualizar personal");
            System.out.println("5. Eliminar personal");
            System.out.println("6. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opPersonal = sc.nextInt();
            sc.nextLine();

            switch (opPersonal) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando personal");
                    System.out.println("-----------------------------");
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    String apellido = sc.nextLine();
                    Personal nuevoPersonal = new Personal(nombre, apellido);
                    personalCrud.agregarPersonal(nuevoPersonal);
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todo el personal");
                    System.out.println("-----------------------------\n");
                    personalCrud.leerTodoPersonal();
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    personalCrud.buscarPersonal(id_buscar);
                    System.out.println("-----------------------------\n");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    personalCrud.actualizarPersonal(id_actualizar);
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    personalCrud.eliminarPersonal(id_eliminar);
                    System.out.println("-----------------------------\n");
                case 6:
                    flagPersonal = false;
                    break;
            }
        } while (flagPersonal);
    }

    public void menuHabitacion(Scanner sc) {
        HabitacionCrud HabitacionCrud = new HabitacionCrud();
        boolean flagHabitacion = true;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD HABITACION");
            System.out.println("------------------");
            System.out.println("1. Agregar Habitacion");
            System.out.println("2. Leer todo Habitacion");
            System.out.println("3. Buscar Habitacion");
            System.out.println("4. Actualizar Habitacion");
            System.out.println("5. Eliminar Habitacion");
            System.out.println("6. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opHabitacion = sc.nextInt();
            sc.nextLine();

            switch (opHabitacion) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.println("Piso: ");
                    int piso = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Estado: ");
                    String estado = sc.nextLine();
                    System.out.println("Personal asignado: ");
                    String personalAsignado = sc.nextLine();
                    System.out.println("Servicio: ");
                    String servicio = sc.nextLine();
                    System.out.println("IDHuesped: ");
                    int IDHuesped = sc.nextInt();

                    Habitacion nuevoHabitacion = new Habitacion(piso, tipo, estado, personalAsignado, servicio, IDHuesped);
                    HabitacionCrud.agregarHabitacion(nuevoHabitacion);
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todo el Habitacion");
                    System.out.println("-----------------------------\n");
                    HabitacionCrud.leerTodoHabitacion();
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    HabitacionCrud.buscarHabitacion(id_buscar);
                    System.out.println("-----------------------------\n");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    HabitacionCrud.actualizarHabitacion(id_actualizar);
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    HabitacionCrud.eliminarHabitacion(id_eliminar);
                    System.out.println("-----------------------------\n");
                case 6:
                    flagHabitacion = false;
                    break;
            }
        } while (flagHabitacion);
    }
}

package interfaz;

/**
 *
 * @author PC
 */
import Controlador.*;
import modelo.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.ServiciosAdicionales;
import modelo.TipoDeHabitacion;
import Persistencia.HabitacionRepository;
import Persistencia.HotelRepository;
import Persistencia.HuespedRepository;
import Persistencia.ReservacionHabitacionesRepository;
import Persistencia.ReservacionHuespedRepository;
import Persistencia.ReservacionRepository;
import Persistencia.ReservacionServicioRepository;
import Persistencia.ServiciosAdicionalesRepository;
import Persistencia.TipoHabitacionRepository;
import Persistencia.FuncionalidadesRepository;
import Persistencia.PersonalRepository;
import java.util.logging.Level;
import java.util.logging.Logger;

//import pruebas_giron.*; //En caso se use fuera de este package
public class main {

    // Funcionalidades
    FuncionalidadesRepository funcionalidadesRepository = new FuncionalidadesRepository();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PersonalCrud personal = new PersonalCrud();

        //Reservación reservacion = new Reservación(); //Esto es de Melendez
        InicioSesion isesion = new InicioSesion();
        main obj = new main();
        boolean sesion = true;//Para mantener la sesión Activa
        boolean flag = true;//Melendez lógica
        boolean usuario = false;//Para verificar si el nombre de Usuario Existe

        do {
            boolean ingresa = false;

            boolean tipo = false;
            System.out.println("------------------");
            System.out.println(" Inicio de sesion ");
            System.out.println("------------------");
            //Debe Haber un Mensaje
            String nombre;
            do {
                System.out.println("1. Ingrese su Usuario");
                nombre = sc.nextLine();
                //Esta funcion Indica si es Personal o Huesped
                usuario = isesion.verificarExistenciaUsuario(nombre);
                //Requiero una Función Booleana que determine si lo encuentra en Personal o Huesped
                //Si lo encuentra retornará verdadero y saldrá del bucle

                if (!usuario) {
                    System.out.println("Por favor Reingrese su Usuario:");
                }
            } while (!usuario);

            System.out.println("2. Ingrese su Contrasena");
            String contra = sc.nextLine();

            //Verificar condicion entonces puede acceder al menu de Opciones
            ingresa = isesion.verificarValidezPersonal(nombre, contra);

            System.out.println("\n");
            //Para ver qué menú imprimir
            if (ingresa) {

                String funcion = isesion.funcionPersonal(nombre);
                if (!"null".equals(isesion.funcionPersonal(nombre))) {
                    funcion = isesion.funcionPersonal(nombre);
                } else {
                    funcion = "Huesped";
                }
                System.out.println(funcion);

                switch (funcion) {
                    case "Huesped":

                        break;
                    case "Administrador":
                        obj.vistaAdmin(sc);
                        break;
                    case "Recepcionista":
                        obj.vistaRececpionista(sc);
                        break;
                    case "Ama de LLaves":
                        break;
                    case "Jefe de Cocina":
                        break;
                }
            }

            System.out.println("Si desea cerrar la sesion, ingrese 'salir' para terminar la Ejecucion");
            String desicion = sc.nextLine();
            if (desicion.equalsIgnoreCase("salir")) {
                sesion = false;
            }

        } while (sesion);

    }//Fin del main

    public void vistaAdmin(Scanner sc) throws IOException {
        boolean flag = true;
        main obj = new main();
        do {
            System.out.println("------------------");
            System.out.println("Vista Administrador");
            System.out.println("------------------");
            System.out.println("1. CRUD personal");
            System.out.println("2. CRUD huesped");
            System.out.println("3. CRUD habitacion");
            System.out.println("4. CRUD servicios");
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
                    obj.menuServicios(sc);
                    break;
                case 5:
                    flag = false;
                    System.out.println("Saliendo...");
                    break;

            }
        } while (flag);
    }

    public void vistaRececpionista(Scanner sc) throws IOException {
        boolean flag = true;
        main obj = new main();
        ReservacionCrud reservacionCrud = new ReservacionCrud();
        List<Reservacion> listaReservaciones = new ArrayList<>();
        listaReservaciones = reservacionCrud.cargarCSVlista();
        HabitacionCrud habitacionCrud = new HabitacionCrud();
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        listaHabitaciones = habitacionCrud.cargarCSVlista();
        HuespedCrud huespedCrud = new HuespedCrud();
        List<Huesped> listaHuesped = new ArrayList<>();
        listaHuesped = huespedCrud.cargarCSVlista();

        List<Reservacion> reservacionesCargadas = new ArrayList<>();
        ReservacionRepository reservaRepo = new ReservacionRepository();
        Reservacion reserva = new Reservacion();
        reservacionesCargadas = reservaRepo.cargarCSVtoLista("reservaciones.csv");

        List<ServiciosAdicionales> serviciosCargados = new ArrayList<>();
        ServiciosAdicionalesRepository serviciosRepo = new ServiciosAdicionalesRepository();
        ServiciosAdicionales servicio = new ServiciosAdicionales();
        serviciosCargados = serviciosRepo.cargarCSVtoLista("serviciosAdicionales.csv");

        List<Huesped> HuespedesCargados = new ArrayList<>();
        HuespedRepository huespedRepo = new HuespedRepository();
        Huesped huesped = new Huesped();
        HuespedesCargados = huespedRepo.cargarCSVtoLista("huespedes.csv");

        List<Habitacion> habitacionCargada = new ArrayList<>();
        HabitacionRepository habitacionRepo = new HabitacionRepository();
        Habitacion habitacion = new Habitacion();
        habitacionCargada = habitacionRepo.cargarCSVtoLista("habitaciones.csv");

        List<ResevacionHabitacion> reservaHabitacionCargados = new ArrayList<>();
        ReservacionHabitacionesRepository reservaHabitacionesRepo = new ReservacionHabitacionesRepository();
        ResevacionHabitacion reservaHabitacion = new ResevacionHabitacion();
        reservaHabitacionCargados = reservaHabitacionesRepo.cargarCSVtoLista("reservaHabitacion.csv");

        List<ReservacionHuesped> reservaHuespedCargados = new ArrayList<>();
        ReservacionHuespedRepository reservaHuespedRepo = new ReservacionHuespedRepository();
        ReservacionHuesped reservaHuesped = new ReservacionHuesped();
        reservaHuespedCargados = reservaHuespedRepo.cargarCSVtoLista("reservaHuesped.csv");

        List<ReservacionServicio> reservacionServicioCargada = new ArrayList<>();
        ReservacionServicioRepository reservaServicioRepo = new ReservacionServicioRepository();
        ReservacionServicio reservaServicio = new ReservacionServicio();
        reservacionServicioCargada = reservaServicioRepo.cargarCSVtoLista("reservaServicios.csv");

        LocalDate fechaActual = LocalDate.now();
        do {
            System.out.println("------------------");
            System.out.println("Vista Recepcionista");
            System.out.println("------------------");
            System.out.println("1. Gestionar huespedes");
            System.out.println("2. Consultar informacion");
            System.out.println("3. Reservar habitacion");
            System.out.println("4. Ampliar reserva");
            System.out.println("5. Registrar ingreso");
            System.out.println("6. Registrar salida");
            System.out.println("7. Salir");

            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int op = sc.nextInt();
            sc.nextLine(); //Limpiar el buffer

            switch (op) {
                case 1:
                    obj.menuHuesped(sc);
                    break;
                case 2:
                    listaReservaciones.clear();

                    int op_info;
                    boolean flagConsulta = true;
                    do {
                        //Consultar Información 
                        System.out.println("\n-----------------------------");
                        System.out.println("Consultando Informacion");
                        System.out.println("-----------------------------");
                        System.out.println("1. Consultar Todas las Habitaciones"); //Podría modificarse a Reservas Activas???
                        System.out.println("2. Consultar Habitaciones Disponibles");
                        System.out.println("3. Consultar Habitaciones Reservadas");
                        System.out.println("4. Consultar Habitaciones Ocupadas");
                        System.out.println("5. Consultar Tiempo de Reservaciones en Curso");
                        System.out.println("6. Consultar Tiempo de Reservaciones en Espera");
                        System.out.println("7. Consultar Tiempo de Reservaciones Finalizadas");
                        System.out.println("8. Retroceder");
                        do {
                            System.out.println("Ingrese el su opción");
                            op_info = sc.nextInt();
                            sc.nextLine(); //Limpiar el buffer
                            if (op_info < 1 || op_info > 8) {
                                System.out.println("Opcion Fuera de Rango");
                            }
                        } while (op_info < 1 || op_info > 8);
                        switch (op_info) {
                            case 1:
                                habitacion.mostrarLista(habitacionCargada);
                                break;
                            case 2:

                                habitacion.leerTodoHabitacionDisponible(habitacionCargada);
                                break;
                            case 3:
                                //Consultar Habitaciones Reservado
                                habitacion.leerTodoHabitacionReservado(habitacionCargada);
                                break;
                            case 4:
                                //Consultar Habitaciones Ocupada
                                habitacion.leerTodoHabitacionOcupada(habitacionCargada);
                                break;
                            case 5:
                                //Consultar Tiempo de Reservación En Curso
                                //reservacionCrud.leerTiempoReservacionTiempo(listaReservaciones);                           
                                break;
                            case 6:
                                //Consultar Tiempo de Reservación en Espera
                                //reservacionCrud.leerTiempoReservacionEspera(listaReservaciones);                            
                                break;
                            case 7:
                                //Consultar Tiempo de Reservación en Espera
                                //reservacionCrud.leerTiempoReservacionFinalizada(listaReservaciones);
                                break;
                            case 8:
                                flagConsulta = false;
                                break;
                        }
                    } while (flagConsulta);
                    //obj.menuHuesped(sc); // Taba antes de las Modificaciones de Miguel
                    break;
                case 3:

                    //Reserva de habitación
                    System.out.println("\n-----------------------------");
                    System.out.println("Reservando habitación");
                    System.out.println("-----------------------------");

                    // ID huesped
                    boolean flagHuesped = false;
                    boolean continuarReserva = true;
                    int idHuesped;
                    int dniHuesped;
                    String respuesta;

                    System.out.println("DNI de huesped: (METE ID POR MIENTRAS< BABOSO)  ");
                    idHuesped = sc.nextInt();
                    sc.nextLine();

                    // ID habitación
                    boolean flagHabitacion = false;
                    boolean seguir = true;
                    int idHabitacion;
                    String answer;

                    System.out.println("\nID de habitación: ");
                    idHabitacion = sc.nextInt();
                    sc.nextLine();

                    // Fechas:
                    LocalDate fechaHoy = LocalDate.now();
                    LocalDate fechaFin;
                    LocalDate fechaInicio;
                    // Fecha de inicio
                    boolean flagFechaInicio = false;
                    String fecha_inicio;
                    do {
                        System.out.println("Fecha de inicio de hospedaje (formato: YYYY-MM-DD): ");
                        fecha_inicio = sc.nextLine().trim();
                        fechaInicio = LocalDate.parse(fecha_inicio);
                        if (fechaHoy.isBefore(fechaInicio) || fechaHoy.isEqual(fechaInicio)) {
                            flagFechaInicio = true;
                        } else {
                            System.out.println("Fecha inválida, ingrese otra");
                            System.out.println("-----------------------------");
                        }
                    } while (!flagFechaInicio);
                    // Fecha de fin
                    boolean flagFechaFin = false;
                    String fecha_fin;
                    do {
                        System.out.println("Fecha de fin de hospedaje (formato: YYYY-MM-DD): ");
                        fecha_fin = sc.nextLine().trim();
                        fechaFin = LocalDate.parse(fecha_fin);
                        if (fechaHoy.isBefore(fechaFin) && fechaInicio.isBefore(fechaFin)) {
                            flagFechaFin = true;
                        } else {
                            System.out.println("Fecha inválida, ingrese otra");
                            System.out.println("-----------------------------");
                        }
                    } while (!flagFechaFin);
                    //Estado de la reservación
                    boolean flagEstado = false;
                    String estado;

                    estado = "En espera";

                    // Servicios de la reserva
                    boolean flagServicio = false;
                    String servicios = null;

                    servicio.mostrarLista(serviciosCargados);

                    System.out.println("Escriba los servicios que más desee con el siguiente formato 'House-Fitnes-Masaje'.");
                    String serviciosDeseados = sc.nextLine();

                    HotelRepository hotel = new HotelRepository();

                    Reservacion nuevaReserva = new Reservacion(
                            hotel.contarLineasCSV("reservaciones.csv") + 1,
                            1,
                            estado,
                            fechaInicio,
                            fechaFin);
                    reserva.agregar(reservacionesCargadas, nuevaReserva);
                    reservaRepo.cargarRegistroToCSV(nuevaReserva, "reservaciones.csv");

                    ReservacionHuesped nuevaReservaHuesped = new ReservacionHuesped(nuevaReserva, huesped.obtenerPorId(HuespedesCargados, idHuesped));
                    reservaHuesped.agregar(reservaHuespedCargados, nuevaReservaHuesped);
                    reservaHuespedRepo.cargarRegistroToCSV(nuevaReservaHuesped, "reservaHuesped.csv");

                    ResevacionHabitacion nuevaReservaHabitacion = new ResevacionHabitacion(nuevaReserva, habitacion.obtenerPorId(habitacionCargada, idHabitacion));
                    reservaHabitacion.agregar(reservaHabitacionCargados, nuevaReservaHabitacion);
                    reservaHabitacionesRepo.cargarRegistroToCSV(nuevaReservaHabitacion, "reservaHabitacion.csv");

                    List<String> listaServiciosDeseados = reservaServicio.separarServicios(serviciosDeseados);
                    reservaServicio.crearReservaServicios(listaServiciosDeseados, serviciosCargados, nuevaReserva, reservacionServicioCargada);
                    reservaServicioRepo.cargarListaToCSV(reservacionServicioCargada, "reservaServicios.csv");

                    break;

                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    flag = false;
                    System.out.println("Saliendo...");
                    break;
            }
        } while (flag);
    }

    public void menuPersonal(Scanner sc) {
        Personal personal = new Personal();
        List<Personal> PersonalCargado = new ArrayList<>();
        PersonalRepository personalRepository = new PersonalRepository();
        try {
            PersonalCargado = personalRepository.cargarCSVtoLista("personal.csv");
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            System.out.println("7. Leer todo Ama de Llaves");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opPersonal = sc.nextInt();
            sc.nextLine();

            switch (opPersonal) {
                case 1:
                    boolean flag_salir = false;

                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando personal");
                    System.out.println("-----------------------------");
                    do {
                        //Nombre
                        System.out.println("Nombre: ");
                        String nombre = sc.nextLine();
                        //Apellido
                        System.out.println("Apellido: ");
                        String apellido = sc.nextLine();
                        //DNI
                        boolean flag_dni = false;
                        int dni;
                        do {
                            System.out.println("DNI");
                            dni = sc.nextInt();
                            if (String.valueOf(dni).length() == 8) {
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
                            telefono = sc.nextInt();
                            if (String.valueOf(telefono).length() == 9) {
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
                        String direccion = sc.nextLine();
                        //Agregado de mi parte para verificar el inicio
                        String usuario;

                        System.out.println("Usuario: ");
                        usuario = sc.nextLine();

                        //Contraseña
                        System.out.println("Contrasena: ");
                        String contrasena = sc.nextLine();
                        //Funcion
                        boolean flag_funcion = false;
                        String funcion;
                        do {
                            System.out.println("Funcion");
                            funcion = sc.nextLine();
                            if ("Administrador".equals(funcion) || "Recepcionista".equals(funcion) || "Ama de LLaves".equals(funcion) || "Jefe de Cocina".equals(funcion)) {
                                flag_funcion = true;
                            }
                            if (!flag_funcion) {
                                System.out.println("-----------------------------");
                                System.out.println("Funcion no existente, vuelva a ingresar");
                                System.out.println("-----------------------------");
                            }
                        } while (!flag_funcion);

                        Personal nuevoPersonal = new Personal(dni, nombre, apellido, telefono, direccion, usuario, contrasena, funcion, "Activo");
                        //Agregamos al csv
                        personalRepository.cargarRegistroToCSV(nuevoPersonal, "personal.csv");
                        System.out.println("-----------------------------");
                        System.out.println("Quieres agregar otro?");
                        String op_seguir = sc.nextLine();

                        //Si la respuesta es no, se sale del do-while
                        if ("no".equals(op_seguir) || "No".equals(op_seguir) || "NO".equals(op_seguir)) {
                            flag_salir = true;
                        }
                    } while (!flag_salir);

                    break;
                case 2:
                    List<Personal> listaPersonales = new ArrayList<>();
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todo el personal");
                    System.out.println("-----------------------------\n");
                    personal.mostrarLista(PersonalCargado);
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando personal");
                    System.out.println("-----------------------------");
                    System.out.println("DNI del personal: ");
                    int id_buscar = sc.nextInt();
                    sc.nextLine();
                    personal.buscarPersonalXDNI(PersonalCargado, id_buscar);
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    for(Personal personal1 : PersonalCargado){
                        if(personal1.getDNI()==id_actualizar){
                            personal = personal1;
                        }
                    }
                    personal.actualizar(PersonalCargado, personal);
                    funcionalidadesRepository.vaciarCSV("personal.csv");
                    personalRepository.cargarListaToCSV(PersonalCargado, "personal.csv");
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    for(Personal personal1 : PersonalCargado){
                        if(personal1.getDNI()==id_eliminar){
                            personal = personal1;
                        }
                    }
                    personal.eliminar(PersonalCargado, personal);
                    funcionalidadesRepository.vaciarCSV("personal.csv");
                    personalRepository.cargarListaToCSV(PersonalCargado, "personal.csv");
                    System.out.println("-----------------------------\n");
                case 6:
                    flagPersonal = false;
                    break;
                case 7:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo Ama de Llaves");
                    System.out.println("-----------------------------\n");
                    personalCrud.leerTodoAmaLlaves();
                    break;
            }
        } while (flagPersonal);
    }

    public void menuHuesped(Scanner sc) throws IOException {
        HuespedCrud huespedCrud = new HuespedCrud();
        Huesped huesped = new Huesped();
        List<Huesped> HuespedesCargados = new ArrayList<>();
        HuespedRepository huespedRepo = new HuespedRepository();
        HuespedesCargados = huespedRepo.cargarCSVtoLista("huespedes.csv");
        boolean flagHuesped = true;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD HUESPED");
            System.out.println("------------------");
            System.out.println("1. Agregar huesped");
            System.out.println("2. Mostrar huespedes");
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
                    System.out.println("DNI: ");
                    int dni = sc.nextInt();
                    System.out.println("Telefono: ");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Direccion: ");
                    String direccion = sc.nextLine();
                    System.out.println("Usuario: ");
                    String usuario = sc.nextLine();
                    System.out.println("Contraseña: ");
                    String contrasena = sc.nextLine();
                    String estado = "Activo";

                    Huesped nuevoHuesped = new Huesped(dni, nombre, apellido, telefono, direccion, usuario, contrasena, estado);
                    nuevoHuesped.agregar(HuespedesCargados, nuevoHuesped);
                    huespedRepo.cargarRegistroToCSV(nuevoHuesped, "huespedes.csv");
                    System.out.println("-----------------------------\n");
                    break;
                case 2:

                    int respuesta;
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo huespedes");
                    System.out.println("-----------------------------\n");
                    System.out.println("Seleccione opcion: ");
                    System.out.println("1. Huespedes activos ");
                    System.out.println("2. Huespedes desactivos ");
                    System.out.println("3. Toda la data (Inactivos y Activos) ");
                    respuesta = sc.nextInt();
                    sc.nextLine();
                    switch (respuesta) {
                        case 1:
                            huesped.leerHuespedActivo(HuespedesCargados);
                            break;
                        case 2:
                            huesped.leerHuespedInactivo(HuespedesCargados);
                            break;
                        case 3:
                            huesped.mostrarLista(HuespedesCargados);
                            break;
                        default:
                            System.out.println("Ingrese una opción válida.");
                    }

                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("DNI del huesped: ");
                    int dni_buscar = sc.nextInt();
                    sc.nextLine();
                    huesped.buscarHuespedXDNI(HuespedesCargados, dni_buscar);
                    System.out.println("-----------------------------\n");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    for (Huesped huespedes : HuespedesCargados) {
                        if (huespedes.getDNI() == id_actualizar) {
                            huesped = huespedes;
                        }
                    }
                    huesped.actualizar(HuespedesCargados, huesped);
                    funcionalidadesRepository.vaciarCSV("huespedes.csv");
                    huespedRepo.cargarListaToCSV(HuespedesCargados, "huespedes.csv");
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    for (Huesped huespedes : HuespedesCargados) {
                        if (huespedes.getDNI() == id_eliminar) {
                            huesped = huespedes;
                        }
                    }
                    huesped.eliminar(HuespedesCargados, huesped);
                    funcionalidadesRepository.vaciarCSV("huespedes.csv");
                    huespedRepo.cargarListaToCSV(HuespedesCargados, "huespedes.csv");
                    System.out.println("-----------------------------\n");
                case 6:
                    flagHuesped = false;
                    break;
            }
        } while (flagHuesped);
    }

    public void menuHabitacion(Scanner sc) throws IOException {
        // Cargamos los tipos de habitaaciones
        List<TipoDeHabitacion> tiposCargados = new ArrayList<>();
        TipoHabitacionRepository tipoRepo = new TipoHabitacionRepository();
        TipoDeHabitacion nuevoTipo = new TipoDeHabitacion();
        tiposCargados = tipoRepo.cargarCSVtoLista("tipoHabitacion.csv");

        // Cargamos las habitaciones
        List<Habitacion> habitacionCargada = new ArrayList<>();
        HabitacionRepository habitacionRepo = new HabitacionRepository();
        Habitacion habitacion = new Habitacion();
        habitacionCargada = habitacionRepo.cargarCSVtoLista("habitaciones.csv");

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
            System.out.println("6. Leer Tipos de Habitacion");
            System.out.println("7. Cambiar precio a tipo de Habitacion");
            System.out.println("8. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opHabitacion = sc.nextInt();
            sc.nextLine();

            switch (opHabitacion) {
                case 1:
                    System.out.println("\n-----------------------------");
                    System.out.println("FALTA AGREGAR TONOTOS CON LA NUEVA LOGICA\n");
                    System.out.println("Agregando Habitacion");
                    System.out.println("-----------------------------");

                    System.out.println("Tipo: ");
                    String tipo = sc.nextLine();
                    for (TipoDeHabitacion tipoDeHabitacion : tiposCargados) {
                        if (tipo.equals(tipoDeHabitacion.getConcepto())) {
                            nuevoTipo = tipoDeHabitacion;
                        }
                    }
                    System.out.println("Piso: ");
                    int piso = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Estado: ");
                    String estado = sc.nextLine();
                    int id = funcionalidadesRepository.numeroLineas("habitaciones.csv") + 1;
                    Habitacion nuevoHabitacion = new Habitacion(id, nuevoTipo, String.valueOf(piso), estado);
                    habitacion.agregar(habitacionCargada, nuevoHabitacion);
                    habitacionRepo.cargarRegistroToCSV(nuevoHabitacion, "habitaciones.csv");
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todas las Habitaciones");
                    System.out.println("-----------------------------\n");
                    System.out.println("------------------------------------------");
                    System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
                    habitacion.mostrarLista(habitacionCargada);
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    Habitacion habitacion_escogida = new Habitacion();
                    habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_buscar);
                    System.out.println("--------------------------------------------------------------------------------------");
                    System.out.printf("%-4s %-19s %-5s %-12s%n", "ID", "Tipo de habitacion", "Piso", "Estado");
                    System.out.println("--------------------------------------------------------------------------------------");
                    // Imprimimos toda la lista
                    System.out.printf("%-4s %-19s %-5s %-12s%n",
                            habitacion_escogida.getId(),
                            habitacion_escogida.getTipoHabitacion().getConcepto(),
                            habitacion_escogida.getPiso(),
                            habitacion_escogida.getEstado());

                    System.out.println("--------------------------------------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    System.out.println("------------------------------------------");
                    System.out.printf("%-4s %-10s %-15s%n", "ID", "Tipo", "Piso");
                    habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_actualizar);
                    habitacion.actualizar(habitacionCargada, habitacion_escogida);
                    funcionalidadesRepository.vaciarCSV("habitaciones.csv");
                    habitacionRepo.cargarListaToCSV(habitacionCargada, "habitaciones.csv");
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    habitacion_escogida = habitacion.obtenerPorId(habitacionCargada, id_eliminar);
                    habitacion.eliminar(habitacionCargada, habitacion_escogida);
                    funcionalidadesRepository.vaciarCSV("habitaciones.csv");
                    habitacionRepo.cargarListaToCSV(habitacionCargada, "habitaciones.csv");
                    System.out.println("-----------------------------\n");
                case 6:
                    nuevoTipo.mostrarLista(tiposCargados);
                    break;
                case 7:
                    System.out.println("\n-----------------------------");
                    nuevoTipo.mostrarLista(tiposCargados);
                    System.out.println("\n-----------------------------");
                    System.out.println("Tipo de habitación a cambiar el precio: ");
                    int id_tipo = sc.nextInt();
                    sc.nextLine();
                    for (TipoDeHabitacion tipoDeHabitacion : tiposCargados) {
                        if (id_tipo == tipoDeHabitacion.getId()) {
                            System.out.println("Nuevo precio: ");
                            float nuevo_precio = sc.nextInt();
                            sc.nextLine();
                            tipoDeHabitacion.setPrecio(nuevo_precio);
                        }
                    }
                    funcionalidadesRepository.vaciarCSV("tipoHabitacion.csv");
                    tipoRepo.cargarListaToCSV(tiposCargados, "tipoHabitacion.csv");
                    break;
                case 8:
                    flagHabitacion = false;
                    break;
            }
        } while (flagHabitacion);
    }

    public void menuServicios(Scanner sc) throws IOException {

        List<ServiciosAdicionales> serviciosCargados = new ArrayList<>();
        ServiciosAdicionalesRepository serviciosRepo = new ServiciosAdicionalesRepository();
        serviciosCargados = serviciosRepo.cargarCSVtoLista("serviciosAdicionales.csv");
        ServiciosAdicionales servicios = new ServiciosAdicionales();

        boolean flagServicio = true;
        do {
            System.out.println("\n------------------");
            System.out.println("CRUD SERVICIOS");
            System.out.println("------------------");
            System.out.println("1. Agregar servicio");
            System.out.println("2. Mostrar servicios");
            System.out.println("3. Actualizar servicio");
            System.out.println("4. Eliminar servicio");
            System.out.println("5. Retroceder");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opServicio = sc.nextInt();
            sc.nextLine();

            switch (opServicio) {
                case 1:
                    boolean flag_salir = false;

                    System.out.println("\n-----------------------------");
                    System.out.println("Agregando servicio");
                    System.out.println("-----------------------------");
                    do {
                        //Nombre
                        System.out.println("Concepto: ");
                        String concepto = sc.nextLine();
                        //Costo
                        int costo;
                        boolean flag_costo = false;
                        do {
                            System.out.println("Costo: ");
                            costo = sc.nextInt();
                            if (costo > 0) {
                                flag_costo = true;
                            }
                            if (!flag_costo) {
                                System.out.println("-----------------------------");
                                System.out.println("Ingrese un precio correcto");
                                System.out.println("-----------------------------");
                            }
                        } while (!flag_costo);
                        int id = funcionalidadesRepository.numeroLineas("serviciosAdicionales.csv") + 1; //Agregando a la lista de servicios
                        ServiciosAdicionales nuevoServicio = new ServiciosAdicionales(id, concepto, costo, "Disponible");
                        servicios.agregar(serviciosCargados, servicios);
                        serviciosRepo.cargarRegistroToCSV(nuevoServicio, "serviciosAdicionales.csv");

                        System.out.println("-----------------------------");
                        System.out.println("Quieres agregar otro?");
                        sc.nextLine();// para consumir una linea >/
                        String op_seguir = sc.nextLine();

                        //Si la respuesta es no, se sale del do-while
                        if ("no".equals(op_seguir) || "No".equals(op_seguir) || "NO".equals(op_seguir)) {
                            flag_salir = true;
                        }
                    } while (!flag_salir);
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo los servicios");
                    System.out.println("-----------------------------\n");
                    servicios.mostrarLista(serviciosCargados);
                    break;
                case 3: //ACTUALIZAR SERVICIO X ID
                    System.out.println("\n-----------------------------");

                    System.out.println("ID a actualizar: ");
                    int id_buscar = sc.nextInt();
                    sc.nextLine(); // consumir una linea en blanco

                    /*System.out.println("-----------------------------");
                    System.out.println("El ID ingresado no se encuentra");
                    System.out.println("-----------------------------");*/
                    for (ServiciosAdicionales serviciosAdicionales : serviciosCargados) {
                        if (serviciosAdicionales.getId() == id_buscar) {
                            //Nombre
                            System.out.println("Concepto: ");
                            String concepto = sc.nextLine();
                            //Costo
                            int costo;
                            boolean flag_costo = false;
                            do {
                                System.out.println("Costo: ");
                                costo = sc.nextInt();
                                if (costo > 0) {
                                    flag_costo = true;
                                }
                                if (!flag_costo) {
                                    System.out.println("-----------------------------");
                                    System.out.println("Ingrese un precio correcto");
                                    System.out.println("-----------------------------");
                                }
                            } while (!flag_costo);
                            serviciosAdicionales.setCosto(costo);
                            serviciosAdicionales.setConcepto(concepto);
                        }
                    }
                    funcionalidadesRepository.vaciarCSV("serviciosAdicionales.csv");
                    serviciosRepo.cargarListaToCSV(serviciosCargados, "serviciosAdicionales.csv");

                    System.out.println("-----------------------------");
                    System.out.println("Registro modificado");
                    System.out.println("-----------------------------");

                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando servicio");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    sc.nextLine();
                    for (ServiciosAdicionales serviciosAdicionales : serviciosCargados) {
                        if (serviciosAdicionales.getId() == id_eliminar) {
                            serviciosAdicionales.setEstado("No Disponible");
                        }
                    }

                    /*System.out.println("-----------------------------");
                    System.out.println("El ID ingresado no se encuentra");
                    System.out.println("-----------------------------");*/
                    funcionalidadesRepository.vaciarCSV("serviciosAdicionales.csv");
                    serviciosRepo.cargarListaToCSV(serviciosCargados, "serviciosAdicionales.csv");

                    System.out.println("-----------------------------");
                    System.out.println("Registro eliminado");
                    System.out.println("-----------------------------\n");

                    break;

                case 5:
                    flagServicio = false;
                    break;

            }
        } while (flagServicio);
    }
}

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
import modelo.Servicios;
import modelo.TipoDeHabitacion;
import Controlador.ServiciosCrud;
import java.util.HashMap;

//import pruebas_giron.*; //En caso se use fuera de este package
public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PersonalCrud personal = new PersonalCrud();
        ServiciosCrud servicios = new ServiciosCrud();

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

    public void vistaAdmin(Scanner sc) {
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

    public void vistaRececpionista(Scanner sc) {
        boolean flag = true;
        main obj = new main();
        ReservacionCrud reservacionCrud = new ReservacionCrud();
        HabitacionCrud habitacionCrud = new HabitacionCrud();
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        listaHabitaciones = habitacionCrud.cargarCSVlista();
        HuespedCrud huespedCrud = new HuespedCrud();
        List<Huesped> listaHuesped = new ArrayList<>();
        listaHuesped = huespedCrud.cargarCSVlista();
        LocalDate fechaActual = LocalDate.now();
        do {
            System.out.println("------------------");
            System.out.println("Vista Recepcionista");
            System.out.println("------------------");
            System.out.println("1. Registrar huesped");
            System.out.println("2. Consultar información");
            System.out.println("3. Reservar habitación");
            System.out.println("4. Actualizar reserva");
            System.out.println("5. Salir");

            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int op = sc.nextInt();
            sc.nextLine(); //Limpiar el buffer

            switch (op) {
                case 1:
                    obj.menuHuesped(sc);
                    break;
                case 2:
                    int op_info;
                    
                    //Consultar Información 
                    System.out.println("\n-----------------------------");
                    System.out.println("Consultando Informacion");
                    System.out.println("-----------------------------");
                    System.out.println("1. Consultar Todas las Habitaciones"); //Podría modificarse a Reservas Activas???
                    System.out.println("2. Consultar Habitaciones Disponibles");
                    System.out.println("3. Consultar Tiempo de Reservaciones en Curso");
                    do{
                        System.out.println("Ingrese el su opción");
                        op_info = sc.nextInt(); sc.nextLine(); //Limpiar el buffer
                        if(op_info<1 || op_info>3){
                            System.out.println("Opcion Fuera de Rango, Solo se Aceptan 1,2 y 3");
                        }
                    }while(op_info<1 || op_info>3);
                    switch(op_info){
                        case 1:
                            //Consultar Todas las Habitaciones
                            habitacionCrud.leerTodoHabitacion(listaHabitaciones);
                            break;
                        case 2:
                            //Consultar Habitaciones Disponibles
                            habitacionCrud.leerTodoHabitacionDisponible();
                            break;
                        case 3:
                            //Consultar Tiempo de Reservación
                            reservacionCrud.leerTiempoReservacionTiempo();
                            break;
                    }
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
                    
                    do {
                        System.out.println("DNI de huesped: ");
                        dniHuesped = sc.nextInt();
                        sc.nextLine();
                        //Verificaos si existe
                        boolean existe = huespedCrud.existeHuespedXDni(listaHuesped,dniHuesped);
                        if (existe) {
                            flagHuesped = true;
                            System.out.println("Datos del huesped");
                            huespedCrud.buscarHuespedXDNI(listaHuesped,dniHuesped);
                        }else{
                            System.out.println("El huesped no existe");
                            System.out.println("¿Desea intentar denuevo? (si/no)");
                            respuesta = sc.nextLine().trim(); // Captura la respuesta del usuario
                            if ("no".equalsIgnoreCase(respuesta)) {
                                 // Si la respuesta es "no", salimos del bucle y terminamos el caso 3
                                System.out.println("No se registró el huésped. Saliendo de la reserva...");
                                flagHuesped = true;
                                continuarReserva = false;
                            } else if (!"si".equalsIgnoreCase(respuesta)) {
                             // Si la respuesta no es ni "si" ni "no", puedes indicarle que ingrese una opción válida
                                System.out.println("Opción inválida, por favor ingrese 'si' o 'no'.");
                            }
                        }
                    } while (!flagHuesped);
                    
                    if (!continuarReserva) {
                        break; // Salimos del case 3
                    }
                    // ID habitación
                    boolean flagHabitacion = false;
                    boolean seguir=true;
                    int idHabitacion;
                    String answer;
                    do {
                        System.out.println("ID de habitación: ");
                        idHabitacion = sc.nextInt();
                        sc.nextLine();
                        //Verificamos si existe
                        boolean existe = habitacionCrud.existeHabitacion(idHabitacion);
                        if (existe) {
                            flagHabitacion = true;
                            //Verificamos si está ocupada (puede cambiarse con lógica/datos de reservas) <<<<-- cambiar
                            boolean ocupada = reservacionCrud.existeHabitacionReservacion(idHabitacion);
                            if (ocupada) {
                                flagHabitacion = false;
                                System.out.println("Habitación ocupada, pruebe con otra");
                                System.out.println("-----------------------------");
                            } else {
                                flagHabitacion = true;
                                System.out.println("Datos de la habitacion: ");
                                habitacionCrud.buscarHabitacionXId(listaHabitaciones,idHabitacion );
                                System.out.println("-----------------------------");
                                System.out.println("Deseas seguir? (si/no)");
                                answer=sc.nextLine().trim();
                                if("no".equalsIgnoreCase(answer)){
                                    seguir=false;
                                }
                            }
                        } else {
                            System.out.println("Habitación no existente");
                            System.out.println("-----------------------------");
                        }
                    } while (!flagHabitacion);
                    if(!seguir){
                        break;
                    }
                    
                    // Servicios de la reserva
                    boolean flagServicio = false;
                    String servicios = null;
                    List<Servicios> listaServicios = new ArrayList<>();
                    do {
                        System.out.println("Combo para habitación");
                        System.out.println("1: Housekeeping");
                        System.out.println("2: Fitness Room");
                        System.out.println("3: Housekeeping y Fiteness Room");
                        int servicio = sc.nextInt();
                        sc.nextLine();
                        //Comprobamos con las opciones
                        switch (servicio) {
                            case 1:
                                servicios = "Housekeeping";
                                listaServicios.add(new Servicios(1,"HouseKeeping",70));
                                flagServicio = true;
                                break;
                            case 2:
                                servicios = "Fitness";
                                listaServicios.add(new Servicios(2,"FitnessCenter",30));
                                flagServicio = true;
                                break;
                            case 3:
                                servicios = "Housekeeping, Fitness";
                                listaServicios.add(new Servicios(1,"HouseKeeping",70));
                                listaServicios.add(new Servicios(2,"FitnessCenter",30));
                                flagServicio = true;
                                break;
                            default:
                                flagServicio = false;
                                break;
                        }
                        if (!flagServicio) {
                            System.out.println("Opción inválida, vuelva a ingresar");
                        }
                    } while (!flagServicio);
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
                   
                    if(fechaInicio.isAfter(fechaHoy)){
                       estado = "En espera";
                    }else if(fechaInicio.isEqual(fechaHoy)){
                       estado = "Vigente";
                    }else{
                        estado = "Hubo error";
                    }
                    
                    idHuesped=huespedCrud.obtenerIdXDni(listaHuesped, dniHuesped);
                    
                    // Cargamos el csv de huespedes en un hashmap
                    HashMap<Integer, Huesped> mapaHuesped = huespedCrud.cargarCSVHash();

                    // Cargamos el csv de habitaciones en un hashmap
                    HashMap<Integer, Habitacion> mapaHabitacion = habitacionCrud.cargarCSVHash();
                    
                    // Agregamos la reserva
                    Habitacion habitacion = mapaHabitacion.get(idHabitacion);
                    Huesped huesped = mapaHuesped.get(idHuesped);
                    
                    Reservacion reservacion = new Reservacion(1, habitacion, huesped, listaServicios, estado,fechaInicio, fechaFin);
                    
                    //Agregamos a la lista
                    reservacionCrud.agregarReservacion(reservacion);
                    
                    reservacionCrud.escribirCSV();
                    
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
            System.out.println("7. Leer todo Ama de Llaves");
            System.out.println("------------------");
            System.out.println("Que desea hacer?");
            int opPersonal = sc.nextInt();
            sc.nextLine();

            switch (opPersonal) {
                case 1:
                    boolean flag_salir = false;
                    int sumador_id = 1;

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
                        boolean flag_usuario = false;
                        do {
                            System.out.println("Usuario: ");
                            usuario = sc.nextLine();
                            List<Personal> listaPersonales = personalCrud.cargarCSVlista();
                            if (personalCrud.existePersonalUsuaro(listaPersonales, usuario)) {
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

                        //Inicializamos, de todas formas, luego se va a cambiar
                        int id = 0;

                        Personal nuevoPersonal = new Personal(id, nombre, apellido, dni, telefono, direccion, usuario, contrasena, funcion, Integer.parseInt("1"));
                        //Agregamos a la lista
                        personalCrud.agregarPersonal(nuevoPersonal, sumador_id);
                        System.out.println("-----------------------------");
                        System.out.println("Quieres agregar otro?");
                        String op_seguir = sc.nextLine();

                        //Si la respuesta es no, se sale del do-while
                        if ("no".equals(op_seguir) || "No".equals(op_seguir) || "NO".equals(op_seguir)) {
                            flag_salir = true;
                        }
                        sumador_id++;
                    } while (!flag_salir);

                    //Escrimos en el csv
                    personalCrud.escribirCSV(personalCrud.getListaPersonal());

                    break;
                case 2:
                    List<Personal> listaPersonales = new ArrayList<>();
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todo el personal");
                    System.out.println("-----------------------------\n");
                    listaPersonales = personalCrud.cargarCSVlista();
                    personalCrud.leerPersonal(listaPersonales);
                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    listaPersonales = personalCrud.cargarCSVlista();
                    personalCrud.buscarPersonal(listaPersonales, id_buscar);
                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Actualizando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a actualizar: ");
                    int id_actualizar = sc.nextInt();
                    listaPersonales = personalCrud.cargarCSVlista();
                    personalCrud.actualizarPersonal(listaPersonales, id_actualizar);
                    System.out.println("-----------------------------\n");
                    break;
                case 5:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando personal");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    listaPersonales = personalCrud.cargarCSVlista();
                    personalCrud.eliminarPersonal(listaPersonales, id_eliminar);
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

    public void menuHuesped(Scanner sc) {
        HuespedCrud huespedCrud = new HuespedCrud();
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
                    int estado = 0;
                    Huesped nuevoHuesped = new Huesped(dni, nombre, apellido, dni, telefono, direccion, usuario, contrasena, estado);
                    huespedCrud.agregarHuesped(nuevoHuesped);
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    List<Huesped> listaHuespedes = new ArrayList<>();
                    listaHuespedes = huespedCrud.cargarCSVlista();
                    
                    int respuesta;
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo huespedes");
                    System.out.println("-----------------------------\n");
                    System.out.println("Seleccione opcion: ");
                    System.out.println("1. Huespedes activos ");
                    System.out.println("2. Huespedes desactivos ");
                    System.out.println("3. Toda la data (Inactivos y Activos) ");
                    respuesta= sc.nextInt();
                    sc.nextLine();
                    switch(respuesta){
                        case 1: 
                            huespedCrud.leerHuespedActivo(listaHuespedes);
                            break;
                        case 2:
                            huespedCrud.leerHuespedInactivo(listaHuespedes);
                            break;
                        case 3:
                            huespedCrud.leerHuespedesAoI(listaHuespedes);
                            break;
                        default:
                         System.out.println("Ingrese una opción válida.");   
                    }

                    break;
                case 3:
                    System.out.println("\n-----------------------------");
                    System.out.println("Buscando huesped");
                    System.out.println("-----------------------------");
                    System.out.println("ID a buscar: ");
                    int id_buscar = sc.nextInt();
                    listaHuespedes = huespedCrud.cargarCSVlista();
                    huespedCrud.buscarHuesped(listaHuespedes, id_buscar);
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

    public void menuHabitacion(Scanner sc) {
        HabitacionCrud HabitacionCrud = new HabitacionCrud();
        TipoDeHabitacion tipoHabitacionMostrar = new TipoDeHabitacion();
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        listaHabitaciones=HabitacionCrud.cargarCSVlista();
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
                    System.out.println("Agregando Habitacion");
                    System.out.println("-----------------------------");
                    System.out.println("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.println("Piso: ");
                    int piso = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Estado: ");
                    String estado = sc.nextLine();
           

                    TipoDeHabitacion tipoHabitacion = new TipoDeHabitacion(tipo);
                    Habitacion nuevoHabitacion = new Habitacion(piso, tipoHabitacion, piso, estado);
                    HabitacionCrud.agregarHabitacion(nuevoHabitacion);
                    System.out.println("-----------------------------\n");
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo todo el Habitacion");
                    System.out.println("-----------------------------\n");
                    HabitacionCrud.leerTodoHabitacion(listaHabitaciones);
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
                    tipoHabitacionMostrar.mostrarTiposDeHabitaciones();
                    break;
                case 7:
                    System.out.println("\n-----------------------------");
                    tipoHabitacionMostrar.mostrarTiposDeHabitaciones();
                    System.out.println("Concepto a actualizar: ");
                    String concepto_actualizar = sc.nextLine();
                    if (!tipoHabitacionMostrar.verificarExistenciaDeKey(concepto_actualizar)) {
                        System.out.println("-----------------------------");
                        System.out.println("El concepto ingresado no se encuentra");
                        System.out.println("-----------------------------");
                    } else {
                        //Costo
                        System.out.println("Nuevo Costo: ");
                        int costo = sc.nextInt();
                        sc.nextLine();

                        //Cambiando el precio del concepto ingresado
                        tipoHabitacionMostrar.cambiarPrecio(concepto_actualizar, costo);

                        System.out.println("-----------------------------");
                        System.out.println("Registro modificado");
                        System.out.println("-----------------------------");
                    }
                    break;
                case 8:
                    flagHabitacion = false;
                    break;
            }
        } while (flagHabitacion);
    }

    public void menuServicios(Scanner sc) {
        ServiciosCrud serviciosCrud = new ServiciosCrud();
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

                        //Agregando a la lista de servicios
                        Servicios nuevoServicio = new Servicios();
                        serviciosCrud.agregarServicios(nuevoServicio, concepto, costo);
                        System.out.println("-----------------------------");
                        System.out.println("Quieres agregar otro?");
                        sc.nextLine();// para consumir una linea >/
                        String op_seguir = sc.nextLine();

                        //Si la respuesta es no, se sale del do-while
                        if ("no".equals(op_seguir) || "No".equals(op_seguir) || "NO".equals(op_seguir)) {
                            flag_salir = true;
                        }
                    } while (!flag_salir);

                    //Escrimos en el csv
                    //personalCrud.escribirCSV();
                    break;
                case 2:
                    System.out.println("\n-----------------------------");
                    System.out.println("Imprimiendo los servicios");
                    System.out.println("-----------------------------\n");
                    serviciosCrud.mostrarServicios();
                    break;
                case 3: //ACTUALIZAR SERVICIO X ID
                    System.out.println("\n-----------------------------");
                    serviciosCrud.mostrarServicios();
                    System.out.println("ID a actualizar: ");
                    int id_buscar = sc.nextInt();
                    sc.nextLine(); // consumir una linea en blanco
                    if (!serviciosCrud.verificarExistenciaxId(id_buscar)) {
                        System.out.println("-----------------------------");
                        System.out.println("El ID ingresado no se encuentra");
                        System.out.println("-----------------------------");
                    } else {
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

                        //Cambiando el registro del id por otros datos
                        serviciosCrud.modificarServicioxId(id_buscar, concepto, costo);

                        System.out.println("-----------------------------");
                        System.out.println("Registro modificado");
                        System.out.println("-----------------------------");
                    }

                    break;
                case 4:
                    System.out.println("\n-----------------------------");
                    System.out.println("Eliminando servicio");
                    System.out.println("-----------------------------");
                    System.out.println("ID a eliminar: ");
                    int id_eliminar = sc.nextInt();
                    sc.nextLine();
                    if (!serviciosCrud.verificarExistenciaxId(id_eliminar)) {
                        System.out.println("-----------------------------");
                        System.out.println("El ID ingresado no se encuentra");
                        System.out.println("-----------------------------");
                    } else {
                        serviciosCrud.eliminarServicioXId(id_eliminar);
                        System.out.println("-----------------------------");
                        System.out.println("Registro eliminado");
                        System.out.println("-----------------------------\n");
                    }
                    break;
                case 5:
                    flagServicio = false;
                    break;

            }
        } while (flagServicio);
    }
}

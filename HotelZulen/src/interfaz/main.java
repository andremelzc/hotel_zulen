package interfaz;

/**
 *
 * @author PC
 */
import servicio.HabitacionCrud;
import servicio.HuespedCrud;
import servicio.PersonalCrud;
import modelo.Habitacion;
import modelo.Personal;
import modelo.AmaDeLlaves;
import modelo.Huesped;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import pruebas_giron.*; //En caso se use fuera de este package
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            do{
                System.out.println("1. Ingrese su Usuario");
                nombre = sc.nextLine();
                //Esta funcion Indica si es Personal o Huesped
                usuario = isesion.verificarExistenciaUsuario(nombre);
                //Requiero una Función Booleana que determine si lo encuentra en Personal o Huesped
                //Si lo encuentra retornará verdadero y saldrá del bucle
                
                if(!usuario){
                    System.out.println("Por favor Reingrese su Usuario:");
                }
            }while(!usuario);
            
            
            System.out.println("2. Ingrese su Contrasena");
            String contra = sc.nextLine();
            
            
            //Verificar condicion entonces puede acceder al menu de Opciones
            ingresa = isesion.verificarValidezPersonal(nombre, contra);
            
            System.out.println("\n");

            if (ingresa) {
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
                            //reservacion.asignarReservacion(sc); //Esto es de Melendez
                            System.out.println("\n4");
                            break;
                        case 5:
                            flag = false;
                            System.out.println("Saliendo...");
                            break;
                    }
                } while (flag);
            }

            System.out.println("Si desea cerrar la sesion, ingrese 'salir' para terminar la Ejecucion");
            String desicion = sc.nextLine();
            if (desicion.equalsIgnoreCase("salir")) {
                sesion = false;
            }

        } while (sesion);

    }//Fin del main

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
                            if(personalCrud.existePersonalUsuaro(usuario)){
                                flag_usuario=true;
                                System.out.println("-----------------------------");
                                System.out.println("Ingrese un usuario no existente");
                                System.out.println("-----------------------------");
                            }else{
                                flag_usuario=false;
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

                        //Agregamos el dni en el slot 1, por ahora, luego es reemplazado por el id
                        Personal nuevoPersonal = new Personal(dni, nombre, apellido, dni, telefono, direccion, usuario, contrasena, funcion, Integer.parseInt("1"));
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
                    personalCrud.escribirCSV();

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

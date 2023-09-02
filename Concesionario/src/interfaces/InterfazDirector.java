package interfaces;

import comprobacion.Comprobacion;
import operaciones.*;

import java.io.BufferedReader;
import java.util.Scanner;

public class InterfazDirector {
    private Scanner scanner = new Scanner(System.in);
    private Comprobacion comprobacion;
    private OpCoches opCoches;
    private OpClientes opClientes;
    private OpVendedores opVendedores;
    private OpExposiciones opExposiciones;
    private OpDirector opDirector;

    public InterfazDirector(OpCoches opCoches, OpClientes opClientes, OpVendedores opVendedores, OpExposiciones opExposiciones, OpDirector opDirector, Comprobacion comprobacion) {
        this.comprobacion = comprobacion;
        this.opCoches = opCoches;
        this.opClientes = opClientes;
        this.opVendedores = opVendedores;
        this.opExposiciones = opExposiciones;
        this.opDirector = opDirector;
    }

    public void menuDirector() {
        String opcion = "";
        while (!opcion.equals("8")) {
            System.out.println("-------------------------");
            System.out.println("------MENÚ DIRECTOR------");
            System.out.println("-------------------------");
            System.out.println("1- Altas");
            System.out.println("2- Bajas");
            System.out.println("3- Venta");
            System.out.println("4- Reserva");
            System.out.println("5- Exposiciones");
            System.out.println("6- Modificaciones");
            System.out.println("7- Consultas");
            System.out.println("8- Salir");
            System.out.println();
            System.out.print("Escoja una opción:");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    menuAltas();
                    break;
                case "2":
                    menuBajas();
                    break;
                case "3":
                    opDirector.comprar();
                    break;
                case "4":
                    menuReservas();
                    break;
                case "5":
                    menuExposiciones();
                    break;
                case "6":
                    menuModificaciones();
                    break;
                case "7":
                    menuConsultas();
                    break;
                case "8":
                    break;
            }
        }
    }

    private void menuAltas() {
        String opcion = "";
        while (!opcion.equals("5")) {
            System.out.println("--------------------------");
            System.out.println("--------MENÚ ALTAS--------");
            System.out.println("--------------------------");
            datos();
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opCoches.agregarCoche();
                    break;
                case "2":
                    opClientes.agregarCliente();
                    break;
                case "3":
                    opVendedores.agregarVendedor();
                    break;
                case "4":
                    opExposiciones.agregarExposicion();
                    break;
            }
        }
    }

    private void menuBajas() {
        String opcion = "";
        while (!opcion.equals("5")) {
            System.out.println("--------------------------");
            System.out.println("--------MENÚ BAJAS--------");
            System.out.println("--------------------------");
            datos();
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opCoches.removerCoche();
                    break;
                case "2":
                    opClientes.removerCliente();
                    break;
                case "3":
                    opVendedores.removerVendedor();
                    break;
                case "4":
                    opExposiciones.removerExposicion();
                    break;
            }
        }
    }

    private void menuModificaciones() {
        String opcion = "";
        while (!opcion.equals("5")) {
            System.out.println("---------------------------");
            System.out.println("----MENÚ MODIFICACIONES----");
            System.out.println("---------------------------");
            datos();
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opCoches.modificarCoche();
                    break;
                case "2":
                    opClientes.modificarCliente();
                    break;
                case "3":
                    opVendedores.modificarVendedor();
                    break;
                case "4":
                    opExposiciones.modificarExposicion();
                    break;
            }
        }
    }

    private void menuConsultas() {
        String opcion = "";
        while (!opcion.equals("6")) {
            System.out.println("--------------------------");
            System.out.println("------MENÚ CONSULTAS------");
            System.out.println("--------------------------");
            System.out.println("1- Coches");
            System.out.println("2- Clientes");
            System.out.println("3- Vendedores");
            System.out.println("4- Exposiciones");
            System.out.println("5- Director");
            System.out.println("6- Salir");
            System.out.println();
            System.out.print("Escoja una opción:");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opCoches.imprimirCoches();
                    break;
                case "2":
                    opClientes.imprimirClientes();
                    break;
                case "3":
                    opVendedores.imprimirVendedores();
                    break;
                case "4":
                    opExposiciones.imprimirExposiciones();
                    break;
                case "5":
                    opDirector.imprimirDirector();
                    break;
                case "6":
                    break;
            }
        }
    }

    private void menuExposiciones() {
        String opcion = "";
        while (!opcion.equals("3")) {
            System.out.println("-------------------------");
            System.out.println("----MENÚ EXPOSICIONES----");
            System.out.println("-------------------------");
            System.out.println("1- Alta");
            System.out.println("2- Baja");
            System.out.println("3- Salir");
            System.out.println();
            System.out.print("Escoja una opción:");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opDirector.agregarCocheExpo();
                    break;
                case "2":
                    opDirector.removerCocheExpo();
                    break;
                case "3":
                    break;
            }
        }
    }
    private void menuReservas(){
        String opcion = "";
        while (!opcion.equals("3")) {
            System.out.println("-----------------------");
            System.out.println("-----MENÚ RESERVAS-----");
            System.out.println("-----------------------");
            System.out.println("1- Alta");
            System.out.println("2- Baja");
            System.out.println("3- Salir");
            System.out.println();
            System.out.print("Escoja una opción:");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opDirector.reservar();
                    break;
                case "2":
                    opDirector.cancelarReserva();
                    break;
                case "3":
                    break;
            }
        }
    }
    public boolean menuLogin() {
        String opcion = "0";
        while (!opcion.equals("3")) {
            System.out.println("--------------------------");
            System.out.println("------LOGIN DIRECTOR------");
            System.out.println("--------------------------");
            System.out.println("1- Alta");
            System.out.println("2- Existente");
            System.out.println("3- Salir");
            System.out.println();
            System.out.print("Escoja una opción:");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    if(opDirector.getDirector().getDni() == null) {
                        opDirector.agregarDirector();
                    }
                    break;
                case "2":
                    if(opDirector.identificarDirector() == true)
                        return true;
                case "3":
                    return false;
            }
        }
        return false;
    }
    private void datos() {
        System.out.println("1- Coches");
        System.out.println("2- Clientes");
        System.out.println("3- Vendedores");
        System.out.println("4- Exposiciones");
        System.out.println("5- Salir");
        System.out.println();
        System.out.print("Escoja una opción:");
    }
}
package interfaces;

import operaciones.OpClientes;
import operaciones.OpCoches;
import operaciones.OpExposiciones;
import operaciones.OpVendedores;

import java.util.Scanner;

public class InterfazVendedor {
    private Scanner scanner = new Scanner(System.in);
    private OpClientes opClientes;
    private OpVendedores opVendedores;
    private OpCoches opCoches;
    private OpExposiciones opExposiciones;

    public InterfazVendedor(OpClientes opClientes, OpVendedores opVendedores, OpCoches opCoches, OpExposiciones opExposiciones) {
        this.opClientes = opClientes;
        this.opVendedores = opVendedores;
        this.opCoches = opCoches;
        this.opExposiciones = opExposiciones;
    }

    public void menuVendedor() {
        String opcion = "";
        while (!opcion.equals("4")) {
            System.out.println("-------------------------");
            System.out.println("------MENÚ VENDEDOR------");
            System.out.println("-------------------------");
            System.out.println("1- Venta");
            System.out.println("2- Reserva");
            System.out.println("3- Consultas");
            System.out.println("4- Salir");
            System.out.println();
            System.out.print("Escoja una opción:");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opVendedores.comprar();
                    break;
                case "2":
                    menuReservas();
                    break;
                case "3":
                    menuConsultas();
                    break;
                case "4":
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
                    opVendedores.reservar();
                    break;
                case "2":
                    opVendedores.cancelarReserva();
                    break;
                case "3":
                    break;
            }
        }
    }
    private void menuConsultas() {
        String opcion = "";
        while (!opcion.equals("5")) {
            System.out.println("--------------------------");
            System.out.println("------MENÚ CONSULTAS------");
            System.out.println("--------------------------");
            System.out.println("1- Coches");
            System.out.println("2- Clientes");
            System.out.println("3- Vendedores");
            System.out.println("4- Exposiciones");
            System.out.println("5- Salir");
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
                    break;
            }
        }
    }

}

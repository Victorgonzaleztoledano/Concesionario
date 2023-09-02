package interfaces;

import operaciones.*;

import java.util.Scanner;

public class InterfazPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private InterfazVendedor ven;
    private InterfazDirector dir;
    private OpCoches opCoches;
    private OpVendedores opVendedores;

    public InterfazPrincipal(InterfazVendedor ven, InterfazDirector dir, OpCoches opCoches, OpVendedores opVendedores) {
        this.ven = ven;
        this.dir = dir;
        this.opCoches = opCoches;
        this.opVendedores = opVendedores;
    }

    public void menuPrincipal() {
        String opcion = "0";
        while (!opcion.equals("4")) {
            System.out.println("--------------------------");
            System.out.println("--------Bienvenid@--------");
            System.out.println("--------------------------");
            System.out.println("1- Stock");
            System.out.println("2- Vendedor");
            System.out.println("3- Director");
            System.out.println("4- Salir");
            System.out.println();
            System.out.print("Escoja una opción:");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    opCoches.imprimirCoches();
                    break;
                case "2":
                    if (opVendedores.identificarVendedor() == true) {
                        ven.menuVendedor();
                    }
                    break;
                case "3":
                    if (dir.menuLogin() == true) {
                        dir.menuDirector();
                    }
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Escoja una de las opciones mostradas");
            }
        }
        System.out.println("¡¡¡Hasta luego!!!");
    }
}
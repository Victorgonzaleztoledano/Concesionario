package operaciones;

import comprobacion.Comprobacion;
import domain.Coche;
import domain.ConcesionarioCoc;
import domain.Estado;
import domain.TipoVehiculo;
import excepciones.AlreadyExistsException;
import excepciones.ConstructorException;
import excepciones.NotExistsException;
import excepciones.WrongOptionException;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OpCoches {
    private Scanner scanner = new Scanner(System.in);
    private Comprobacion comprobacion;
    private ConcesionarioCoc concesionario;
    private HashMap<String, Coche> coches;

    public OpCoches(ConcesionarioCoc concesionario, Comprobacion comprobacion) {
        this.concesionario = concesionario;
        this.coches = concesionario.getCoches();
        this.comprobacion = comprobacion;
    }

    public void agregarCoche() {
        try {
            String var;
            Coche coche = new Coche();

            datosCoche(coche);

            System.out.print("Introduzca la matrícula:");
            var = scanner.nextLine();
            if (!comprobacion.comprobarMatricula(var))
                throw new ConstructorException("Introduzca una matrícula válida");
            if (comprobacion.comprobarMatRepe(var))
                throw new AlreadyExistsException("La matrícula introducida ya existe");
            else coche.setMatricula(var);

            coche.setEstado(Estado.Venta);

            System.out.println("Coche agregado con éxito");
            concesionario.agregarCoche(coche);
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerCoche() {
        Coche coche = convertIndice();
        if (comprobacion.comprobarObject(coche)) {
            concesionario.removerCoche(coche);
            System.out.println("Coche removido con éxito");
        }
    }

    public void modificarCoche() {
        try {
            Coche coche = convertIndice();
            if (comprobacion.comprobarObject(coche)) {
                scanner.nextLine();
                datosCoche(coche);

                System.out.println("Coche modificado con éxito");
            }
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void imprimirCoches() {
        try {
            if (!coches.isEmpty()) {
                System.out.println("----------COCHES EN STOCK----------");
                for (Coche coche : coches.values()) {
                    System.out.println(coche.toString());
                    System.out.println("---");
                }
            } else throw new NotExistsException("No hay coches en stock");
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void datosCoche(Coche coche) throws ConstructorException {
        String var;
        boolean correct = false;
        while (!correct) {
            System.out.println("Marque el tipo de coche:");
            System.out.println("1- Turismo");
            System.out.println("2- Industrial");
            System.out.println("3- Todoterreno");
            System.out.print("Escoja una de las opciones:");
            var = scanner.nextLine();
            switch (var) {
                case "1":
                    coche.setTipo(TipoVehiculo.Turismo);
                    correct = true;
                    break;
                case "2":
                    coche.setTipo(TipoVehiculo.Industrial);
                    correct = true;
                    break;
                case "3":
                    coche.setTipo(TipoVehiculo.Todoterreno);
                    correct = true;
                    break;
            }
        }

        System.out.print("Introduzca una marca:");
        var = scanner.nextLine();
        if (comprobacion.comprobarNombre(var)) coche.setMarca(var.trim());
        else throw new ConstructorException("Introduzca una marca válida");

        System.out.print("Introduzca un modelo:");
        var = scanner.nextLine();
        if (comprobacion.comprobarNull(var)) throw new ConstructorException("El modelo no puede ser vacío");
        else coche.setModelo(var.trim());
    }

    public Coche convertIndice() {
        try {
            if (!coches.isEmpty()) {
                ArrayList<Coche> cochesIndice = new ArrayList<>();
                int i = 0;
                for (Coche coche : coches.values()) {
                    cochesIndice.add(coche);
                }
                System.out.println("----------------");
                System.out.println("-----COCHES-----");
                for (Coche coche : cochesIndice) {
                    i++;
                    System.out.println(i + "- " + coche.toString());
                }
                System.out.println("----------------");
                System.out.print("Escoja un coche de la lista:");
                String var = scanner.nextLine();
                i = comprobacion.comprobarOpcion(var);
                if (-1 < i && i < cochesIndice.size() + 1) {
                    Coche coche = cochesIndice.get(i - 1);
                    return coche;
                } else throw new WrongOptionException("Escoja una de las opciones mostradas");
            } else throw new NotExistsException("No hay coches en el stock");
        } catch (WrongOptionException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void sacarCoche(Coche coche) {
        concesionario.removerCoche(coche);
    }

    public void agregarCoche(Coche coche) {
        concesionario.agregarCoche(coche);
    }
}

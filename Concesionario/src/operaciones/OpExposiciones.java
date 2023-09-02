package operaciones;

import comprobacion.Comprobacion;
import domain.Coche;
import domain.ConcesionarioExp;
import domain.Exposicion;
import excepciones.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OpExposiciones {
    private Scanner scanner = new Scanner(System.in);
    private Comprobacion comprobacion;
    private ConcesionarioExp concesionario;
    private HashMap<Integer, Exposicion> exposiciones;

    public OpExposiciones(ConcesionarioExp concesionario, Comprobacion comprobacion) {
        this.concesionario = concesionario;
        this.exposiciones = concesionario.getExposiciones();
        this.comprobacion = comprobacion;
    }

    public void agregarExposicion() {
        try {
            Exposicion exposicion = new Exposicion();
            int numExpo;
            System.out.print("Introduzca número de exposición:");
            String var = scanner.nextLine();
            if (comprobacion.comprobarNum(var)) throw new WrongOptionException("Introduzca un número válido");
            else numExpo = Integer.parseInt(var);
            if (comprobacion.comprobarNumExpoRepe(numExpo))
                throw new AlreadyExistsException("El número de exposición ya existe");
            exposicion.setNumExposicion(numExpo);

            datosExposicion(exposicion);

            System.out.println("Exposición agregada con éxito");
            concesionario.agregarExposicion(exposicion);
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (WrongOptionException e) {
            System.out.println(e.getMessage());
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerExposicion() {
        Exposicion exposicion = convertirIndice();
        if (comprobacion.comprobarObject(exposicion)) {
            if (exposicion.getCoches().isEmpty()) {
                concesionario.removerExposicion(exposicion);
                System.out.println("Exposición removida con éxito");
            }
        }
    }

    public void modificarExposicion() {
        try {
            Exposicion exposicion = convertirIndice();
            if (comprobacion.comprobarObject(exposicion)) {

                datosExposicion(exposicion);

                System.out.println("Exposición modificada con éxito");
            }
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void imprimirExposiciones() {
        try {
            if (!exposiciones.isEmpty()) {
                System.out.println("----------EXPOSICIONES----------");
                for (Exposicion exposicion : exposiciones.values()) {
                    System.out.println(exposicion.toString());
                    if (exposicion.getCoches().isEmpty()) System.out.println("No hay coches en la exposición.");
                    else {
                        System.out.println("----COCHES EN EXPOSICIÓN Nº" + exposicion.getNumExposicion() + "----");
                        for (Coche coche : exposicion.getCoches().values()) {
                            System.out.println(coche.toString());
                        }
                    }
                    System.out.println("---");
                }
            } else throw new NotExistsException("No hay exposiciones dadas de alta");
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void datosExposicion(Exposicion exposicion) throws AlreadyExistsException, ConstructorException {
        System.out.print("Introduzca un teléfono:");
        String var = scanner.nextLine();
        if (!comprobacion.comprobarTelefono(var))
            throw new ConstructorException("El teléfono introducido es incorrecto");
        if (comprobacion.comprobarTlfnRepe(var) == true) throw new AlreadyExistsException("El teléfono ya existe");
        else exposicion.setTelefono(var);

        System.out.print("Introduzca una dirección:");
        var = scanner.nextLine();
        if (comprobacion.comprobarNull(var)) throw new ConstructorException("La dirección no puede estar vacía");
        else exposicion.setDireccion(var.trim());

        System.out.println();
    }

    public Exposicion convertirIndice() {
        try {
            if (!exposiciones.isEmpty()) {
                ArrayList<Exposicion> exposicionesIndice = new ArrayList<>();
                int i = 0;
                for (Exposicion exposicion : exposiciones.values()) {
                    exposicionesIndice.add(exposicion);
                }
                System.out.println("----------------");
                System.out.println("--EXPOSICIONES--");
                for (Exposicion exposicion : exposicionesIndice) {
                    i++;
                    System.out.println(i + "- " + exposicion.toString());
                }
                System.out.println("----------------");
                System.out.print("Escoja una exposición de la lista:");
                String var = scanner.nextLine();
                i = comprobacion.comprobarOpcion(var);
                if (-1 < i && i < exposicionesIndice.size() + 1) {
                    Exposicion exposicion = exposicionesIndice.get(i - 1);
                    return exposicion;
                } else throw new WrongOptionException("Escoja una de las opciones mostradas");
            } else throw new NotExistsException("No hay exposiciones dadas de alta");
        } catch (WrongOptionException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Coche convertirIndiceCoches(Exposicion exposicion) {
        try {
            HashMap<String, Coche> coches = exposicion.getCoches();
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
                } else throw new WrongOptionException("Escoja una opción de las mostradas");
            } else throw new NotExistsException("No hay coches reservados por el cliente");
        } catch (WrongOptionException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
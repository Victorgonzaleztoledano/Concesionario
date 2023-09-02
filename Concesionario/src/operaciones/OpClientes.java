package operaciones;

import comprobacion.Comprobacion;
import domain.*;
import excepciones.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OpClientes {
    private Scanner scanner = new Scanner(System.in);
    private Comprobacion comprobacion;
    private ConcesionarioCli conceClientes;
    private HashMap<String, Cliente> clientes;

    public OpClientes(ConcesionarioCli conceClientes, Comprobacion comprobacion) {
        this.comprobacion = comprobacion;
        this.conceClientes = conceClientes;
        clientes = conceClientes.getClientes();
    }

    public void agregarCliente() {
        try {
            Cliente cliente = new Cliente();

            System.out.print("Introduzca un dni:");
            String var = scanner.nextLine();
            if (!comprobacion.comprobarDni(var)) throw new ConstructorException("El dni introducido es incorrecto");
            if (comprobacion.comprobarDniRepe(var) == true) throw new AlreadyExistsException("El dni ya existe");
            else cliente.setDni(var);

            datosCliente(cliente);

            System.out.println();
            System.out.println("Cliente agregado con éxito");
            conceClientes.agregarCliente(cliente);
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerCliente() {
        try {
            Cliente cliente = conventirIndice();
            if (comprobacion.comprobarObject(cliente)) {
                if (cliente.getCochesComprados().isEmpty() && cliente.getCochesReservados().isEmpty()) {
                    conceClientes.removerCliente(cliente);
                    System.out.println("Cliente removido con éxito");
                } else
                    throw new NotPossibleException("No se puede dar de baja un cliente con coches comprados o reservados");
            }
        } catch (NotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarCliente() {
        try {
            Cliente cliente = conventirIndice();
            if (comprobacion.comprobarObject(cliente)) {

                datosCliente(cliente);

                System.out.println("Cliente modificado con éxito");
            }
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void imprimirClientes() {
        try {
            if (!clientes.isEmpty()) {
                System.out.println("----------CLIENTES----------");
                for (Cliente cliente : clientes.values()) {
                    System.out.println(cliente.toString());
                    System.out.println("---");
                    if (cliente.getCochesComprados().isEmpty()) System.out.println("No ha comprado ningún coche.");
                    else {
                        System.out.println("----COCHES COMPRADOS----");
                        for (Coche coche : cliente.getCochesComprados().values()) {
                            System.out.println(coche.toString());
                        }
                    }
                    System.out.println("---");
                    if (cliente.getCochesReservados().isEmpty()) System.out.println("No ha reservado ningún coche.");
                    else {
                        System.out.println("----COCHES RESERVADOS----");
                        for (Coche coche : cliente.getCochesReservados().values()) {
                            System.out.println(coche.toString());
                        }
                    }
                    System.out.println("--------------------");
                }
            } else throw new NotExistsException("No hay clientes dados de alta");
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void datosCliente(Cliente cliente) throws AlreadyExistsException, ConstructorException {
        System.out.print("Introduzca un nombre:");
        String var = scanner.nextLine();
        if (!comprobacion.comprobarNombre(var))
            throw new ConstructorException("El nombre introducido es incorrecto");
        else cliente.setNombre(var.trim());

        System.out.print("Introduzca una dirección:");
        var = scanner.nextLine();
        if (comprobacion.comprobarNull(var)) throw new ConstructorException("La dirección no puede estar vacía");
        else cliente.setDireccion(var.trim());

        System.out.print("Introduzca un teléfono:");
        var = scanner.nextLine();
        if (!comprobacion.comprobarTelefono(var))
            throw new ConstructorException("El teléfono introducido es incorrecto");
        if (comprobacion.comprobarTlfnRepe(var) == true) throw new AlreadyExistsException("El teléfono ya existe");
        else cliente.setTelefono(var);
    }


    public Cliente conventirIndice() {
        try {
            if (!clientes.isEmpty()) {
                ArrayList<Cliente> clienteIndice = new ArrayList<>();
                int i = 0;
                for (Cliente cliente : clientes.values()) {
                    clienteIndice.add(cliente);
                }
                System.out.println("----------------");
                System.out.println("----CLIENTES----");
                for (Cliente cliente : clienteIndice) {
                    i++;
                    System.out.println(i + "- " + cliente.toString());
                }
                System.out.println("----------------");
                System.out.print("Escoja un cliente de la lista:");
                String var = scanner.nextLine();
                i = comprobacion.comprobarOpcion(var);
                if (-1 < i && i < clienteIndice.size() + 1) {
                    Cliente cliente = clienteIndice.get(i - 1);
                    return cliente;
                } else throw new WrongOptionException("Escoja una de las opciones mostradas");
            } else throw new NotExistsException("No hay clientes dados de alta");
        } catch (WrongOptionException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Coche convertirIndiceCoche(Cliente cliente) {
        try {
            HashMap<String, Coche> coches = cliente.getCochesReservados();
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
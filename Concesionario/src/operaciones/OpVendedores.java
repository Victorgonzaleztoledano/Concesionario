package operaciones;

import comprobacion.Comprobacion;
import domain.*;
import excepciones.*;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OpVendedores {
    private Scanner scanner = new Scanner(System.in);
    private Comprobacion comprobacion;
    private OpClientes opClientes;
    private OpCoches opCoches;
    private ConcesionarioVend concesionario;
    private HashMap<String, Vendedor> vendedores;

    public OpVendedores(OpClientes opClientes, OpCoches opCoches, ConcesionarioVend concesionario, Comprobacion comprobacion) {
        this.opClientes = opClientes;
        this.opCoches = opCoches;
        this.concesionario = concesionario;
        this.vendedores = concesionario.getVendedores();
        this.comprobacion = comprobacion;
    }

    public void agregarVendedor() {
        try {
            Vendedor vendedor = new Vendedor();

            System.out.print("Introduzca un dni:");
            String var = scanner.nextLine();
            if (!comprobacion.comprobarDni(var)) throw new ConstructorException("El dni introducido es incorrecto");
            if (comprobacion.comprobarDniRepe(var) == true) throw new AlreadyExistsException("El dni ya existe");
            else vendedor.setDni(var);

            datosVendedor(vendedor);

            System.out.println("Vendedor agregado con éxito");
            concesionario.agregarVendedor(vendedor);
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerVendedor() {
        try {
            Vendedor vendedor = convertirIndice();
            if (comprobacion.comprobarObject(vendedor)) {
                if (vendedor.getCochesVendidos().isEmpty()) {
                    concesionario.removerVendedor(vendedor);
                    System.out.println("Vendedor removido con éxito");
                } else
                    throw new NotPossibleException("No se puede dar de baja un cliente con coches comprados o reservados");
            }
        } catch (NotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarVendedor() {
        try {
            Vendedor vendedor = convertirIndice();
            if (comprobacion.comprobarObject(vendedor)) {
                scanner.nextLine();
                datosVendedor(vendedor);

                System.out.println("Vendedor modificado con éxito");
            }
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void imprimirVendedores() {
        try {
            if (!vendedores.isEmpty()) {
                System.out.println("----------VENDEDORES----------");
                for (Vendedor vendedor : vendedores.values()) {
                    System.out.println(vendedor.toString());
                    System.out.println("Sueldo: " + vendedor.getSueldo() + " €");
                    System.out.println("---");
                    if (vendedor.getCochesVendidos().isEmpty()) System.out.println("No ha vendido ningún coche.");
                    else {
                        System.out.println("----COCHES VENDIDOS----");
                        for (Coche coche : vendedor.getCochesVendidos().values()) {
                            System.out.println(coche.toString());
                        }
                    }
                    System.out.println("--------------------");
                }
            } else throw new NotExistsException("No hay vendedores dados de alta");
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void comprar() {
        Cliente cliente = opClientes.conventirIndice();
        if (comprobacion.comprobarObject(cliente)) {
            Coche coche = opCoches.convertIndice();
            if (comprobacion.comprobarObject(coche)) {
                Vendedor vendedor = convertirIndice();
                if (comprobacion.comprobarObject(vendedor)) {
                    coche.setEstado(Estado.Vendido);

                    cliente.comprarCoche(coche);
                    vendedor.venderCoche(coche);
                    opCoches.sacarCoche(coche);

                    System.out.println("Coche comprado por éxito");
                    System.out.println("Su comprador es: " + cliente.getNombre());
                    System.out.println("Su vendedor es: " + vendedor.getNombre());
                }
            }
        }
    }

    public void reservar() {
        Cliente cliente = opClientes.conventirIndice();
        if (comprobacion.comprobarObject(cliente)) {
            Coche coche = opCoches.convertIndice();
            if (comprobacion.comprobarObject(coche)) {
                Vendedor vendedor = convertirIndice();
                if (comprobacion.comprobarObject(vendedor)) {
                    coche.setEstado(Estado.Reservado);

                    cliente.reservarCoche(coche);
                    opCoches.sacarCoche(coche);

                    System.out.println("Coche reservado con éxito");
                    System.out.println("Reservado por: " + cliente.getNombre());
                }
            }
        }
    }

    public void cancelarReserva() {
        Cliente cliente = opClientes.conventirIndice();
        if (comprobacion.comprobarObject(cliente)) {
            Coche coche = opClientes.convertirIndiceCoche(cliente);
            if (comprobacion.comprobarObject(coche)) {
                coche.setEstado(Estado.Venta);
                cliente.cancelarReserva(coche);
                opCoches.agregarCoche(coche);
                System.out.println("Reserva cancelada con éxito");
            }
        }
    }
    public boolean identificarVendedor(){
        System.out.print("Introduzca el dni del vendedor:");
        String var = scanner.nextLine();
        if(comprobacion.comprobrarVendedor(var)) return true;
        else {
            System.out.println("No se encuentra el vendedor");
            return false;
        }
    }

    private void datosVendedor(Vendedor vendedor) throws ConstructorException, AlreadyExistsException {
        System.out.print("Introduzca un nombre:");
        String var = scanner.nextLine();
        if (!comprobacion.comprobarNombre(var))
            throw new ConstructorException("El nombre introducido es incorrecto");
        else vendedor.setNombre(var.trim());

        System.out.print("Introduzca una dirección:");
        var = scanner.nextLine();
        if (comprobacion.comprobarNull(var)) throw new ConstructorException("La dirección no puede estar vacía");
        else vendedor.setDireccion(var.trim());

        System.out.print("Introduzca un teléfono:");
        var = scanner.nextLine();
        if (!comprobacion.comprobarTelefono(var))
            throw new ConstructorException("El teléfono introducido es incorrecto");
        if (comprobacion.comprobarTlfnRepe(var) == true) throw new AlreadyExistsException("El teléfono ya existe");
        else vendedor.setTelefono(var);

        System.out.println();
    }

    public Vendedor convertirIndice() {
        try {
            if (!vendedores.isEmpty()) {
                ArrayList<Vendedor> vendedoresIndice = new ArrayList<>();
                int i = 0;
                for (Vendedor vendedor : vendedores.values()) {
                    vendedoresIndice.add(vendedor);
                }
                System.out.println("----------------");
                System.out.println("---VENDEDORES---");
                for (Vendedor vendedor : vendedoresIndice) {
                    i++;
                    System.out.println(i + "- " + vendedor.toString());
                }
                System.out.println("----------------");
                System.out.print("Escoja un vendedor de la lista:");
                String var = scanner.nextLine();
                i = comprobacion.comprobarOpcion(var);
                if (-1 < i && i < vendedoresIndice.size() + 1) {
                    Vendedor vendedor = vendedoresIndice.get(i - 1);
                    return vendedor;
                } else throw new WrongOptionException("Escoja una de las opciones mostradas");
            } else throw new NotExistsException("No hay vendedores dados de alta");
        } catch (WrongOptionException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NotExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

package operaciones;

import comprobacion.Comprobacion;
import domain.*;
import excepciones.AlreadyExistsException;
import excepciones.ConstructorException;
import excepciones.NotExistsException;

import java.util.Scanner;

public class OpDirector {
    private Scanner scanner = new Scanner(System.in);
    private Comprobacion comprobacion;
    private OpClientes opClientes;
    private OpCoches opCoches;
    private OpExposiciones opExposiciones;
    private ConcesionarioDir direct;
    private Director director;


    public OpDirector(OpClientes opClientes, OpCoches opCoches, OpExposiciones opExposiciones, ConcesionarioDir direct, Comprobacion comprobacion) {
        this.opClientes = opClientes;
        this.opCoches = opCoches;
        this.opExposiciones = opExposiciones;
        this.direct = direct;
        this.director = direct.getDirector();
        this.comprobacion = comprobacion;
    }

    public void comprar() {
        Cliente cliente = opClientes.conventirIndice();
        if (comprobacion.comprobarObject(cliente)) {
            Coche coche = opCoches.convertIndice();
            if (comprobacion.comprobarObject(coche)) {
                Director director = direct.getDirector();
                if (comprobacion.comprobarObject(director)) {
                    coche.setEstado(Estado.Vendido);

                    cliente.comprarCoche(coche);
                    director.venderCoche(coche);
                    opCoches.sacarCoche(coche);

                    System.out.println("Coche comprado con éxito");
                    System.out.println("Su comprador es: " + cliente.getNombre());
                    System.out.println("Su vendedor es el director: " + director.getNombre());
                }
            }
        }
    }

    public void reservar() {
        Cliente cliente = opClientes.conventirIndice();
        if (comprobacion.comprobarObject(cliente)) {
            Coche coche = opCoches.convertIndice();
            if (comprobacion.comprobarObject(coche)) {
                Director director = direct.getDirector();
                if (comprobacion.comprobarObject(director)) {
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
            if (comprobacion.comprobarObject(coche)){
                coche.setEstado(Estado.Venta);
                cliente.cancelarReserva(coche);
                opCoches.agregarCoche(coche);
                System.out.println("Reserva cancelada con éxito");
            }
        }
    }

    public void agregarCocheExpo() {
        Coche coche = opCoches.convertIndice();
        if (comprobacion.comprobarObject(coche)) {
            Exposicion exposicion = opExposiciones.convertirIndice();
            if (comprobacion.comprobarObject(exposicion)) {
                coche.setEstado(Estado.Exposicion);
                exposicion.agregar(coche);
                opCoches.sacarCoche(coche);
                System.out.println("Coche movido a exposición con éxito");
            }
        }
    }

    public void removerCocheExpo() {
        Exposicion exposicion = opExposiciones.convertirIndice();
        if (comprobacion.comprobarObject(exposicion)) {
            Coche coche = opExposiciones.convertirIndiceCoches(exposicion);
            if (comprobacion.comprobarObject(coche)) {
                coche.setEstado(Estado.Venta);
                exposicion.remover(coche);
                opCoches.agregarCoche(coche);
                System.out.println("Coche introducido al stock con éxito");
            }
        }
    }

    public void imprimirDirector() {
        try {
            if (comprobacion.comprobarObject(director)) {
                System.out.println("------DIRECTOR-------");
                System.out.println(director.toString());
                System.out.println("El director ha vendido: " + director.getCochesVendidos().size() + " coches.");
            } else throw new NotExistsException("No hay director dado de alta");
        }
        catch (NotExistsException e){
            System.out.println(e.getMessage());
        }
    }
    public void agregarDirector() {
        try {
            System.out.print("Introduzca un dni:");
            String var = scanner.nextLine();
            if (!comprobacion.comprobarDni(var)) throw new ConstructorException("El dni introducido es incorrecto");
            else director.setDni(var);

            System.out.print("Introduzca un nombre:");
            var = scanner.nextLine();
            if (!comprobacion.comprobarNombre(var))
                throw new ConstructorException("El nombre introducido es incorrecto");
            else director.setNombre(var.trim());

            System.out.print("Introduzca una dirección:");
            var = scanner.nextLine();
            if (comprobacion.comprobarNull(var)) throw new ConstructorException("La dirección no puede estar vacía");
            else director.setDireccion(var.trim());

            System.out.print("Introduzca un teléfono:");
            var = scanner.nextLine();
            if (!comprobacion.comprobarTelefono(var))
                throw new ConstructorException("El teléfono introducido es incorrecto");
            else director.setTelefono(var);

            System.out.println();
            System.out.println("Director agregado con éxito");

        } catch (ConstructorException e) {
            System.out.println(e.getMessage());
            director.setDni(null);
        }
    }
    public boolean identificarDirector(){
        System.out.print("Introduzca el dni del director:");
        String var = scanner.nextLine();
        if(comprobacion.comprobrarDirector(var)) return true;
        else {
            System.out.println("El dni introducido no existe");
            return false;
        }
    }

    public Director getDirector() {
        return director;
    }
}

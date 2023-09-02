import comprobacion.Comprobacion;
import domain.*;

import interfaces.InterfazDirector;
import interfaces.InterfazPrincipal;

import interfaces.InterfazVendedor;
import operaciones.*;

public class Main {
    public static void main(String[] args) {
        ConcesionarioVend vendedores = new ConcesionarioVend();
        ConcesionarioCoc coches = new ConcesionarioCoc();
        ConcesionarioCli clientes = new ConcesionarioCli();
        ConcesionarioDir director = new ConcesionarioDir();
        ConcesionarioExp exposiciones = new ConcesionarioExp();

        Comprobacion comprobacion = new Comprobacion(coches,clientes,vendedores,exposiciones,director);

        OpClientes opClientes = new OpClientes(clientes, comprobacion);
        OpCoches opCoches = new OpCoches(coches, comprobacion);
        OpVendedores opVendedores = new OpVendedores(opClientes, opCoches, vendedores, comprobacion);
        OpExposiciones opExposiciones = new OpExposiciones(exposiciones, comprobacion);
        OpDirector opDirector = new OpDirector(opClientes, opCoches,opExposiciones, director, comprobacion);

        InterfazVendedor intVen = new InterfazVendedor(opClientes, opVendedores, opCoches, opExposiciones);
        InterfazDirector intDir = new InterfazDirector(opCoches, opClientes, opVendedores, opExposiciones, opDirector, comprobacion);

        InterfazPrincipal menu = new InterfazPrincipal(intVen, intDir, opCoches, opVendedores);

        menu.menuPrincipal();
    }
}
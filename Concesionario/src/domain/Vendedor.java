package domain;

import excepciones.ConstructorException;

import java.util.HashMap;

public class Vendedor extends Persona{
    private int sueldo;
    private HashMap<String, Coche> cochesVendidos = new HashMap<>();


    public Vendedor() {
    }

    public Vendedor(String nombre, String direccion, String dni, String telefono, int sueldo) throws ConstructorException {
        super(nombre, direccion, dni, telefono);
        this.sueldo = sueldo;
    }
    public void venderCoche(Coche coche){
        cochesVendidos.put(coche.getMatricula(), coche);
    }

    public int getSueldo() {
        return getCochesVendidos().size() * 200;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public HashMap<String, Coche> getCochesVendidos() {
        return cochesVendidos;
    }

    public void setCochesVendidos(HashMap<String, Coche> cochesVendidos) {
        this.cochesVendidos = cochesVendidos;
    }
}

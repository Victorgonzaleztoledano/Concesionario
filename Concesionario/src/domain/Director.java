package domain;

import excepciones.ConstructorException;

import java.util.HashMap;

public class Director extends Persona{
    private HashMap<String, Coche> cochesVendidos = new HashMap<>();
    public Director() {
    }

    public Director(String nombre, String direccion, String dni, String telefono) throws ConstructorException {
        super(nombre, direccion, dni, telefono);
    }
    public void venderCoche(Coche coche){
        cochesVendidos.put(coche.getMatricula(), coche);
    }

    public HashMap<String, Coche> getCochesVendidos() {
        return cochesVendidos;
    }

    public void setCochesVendidos(HashMap<String, Coche> cochesVendidos) {
        this.cochesVendidos = cochesVendidos;
    }
}

package domain;

import excepciones.ConstructorException;

import java.util.HashMap;

public class Cliente extends Persona{
    private HashMap<String, Coche> cochesComprados = new HashMap<>();
    private HashMap<String, Coche> cochesReservados = new HashMap<>();
    public Cliente(String nombre, String direccion, String dni, String telefono) throws ConstructorException {
        super(nombre, direccion, dni, telefono);
    }

    public Cliente() {
    }

    public HashMap<String, Coche> getCochesComprados() {
        return cochesComprados;
    }

    public void setCochesComprados(HashMap<String, Coche> cochesComprados) {
        this.cochesComprados = cochesComprados;
    }

    public HashMap<String, Coche> getCochesReservados() {
        return cochesReservados;
    }

    public void setCochesReservados(HashMap<String, Coche> cochesReservados) {
        this.cochesReservados = cochesReservados;
    }

    public void comprarCoche(Coche coche){
        cochesComprados.put(coche.getMatricula(), coche);
    }
    public void reservarCoche(Coche coche){
        cochesReservados.put(coche.getMatricula(), coche);
    }
    public void cancelarReserva(Coche coche){
        cochesReservados.remove(coche.getMatricula());
    }
}

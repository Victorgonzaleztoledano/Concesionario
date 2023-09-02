package domain;

import java.util.HashMap;

public class Exposicion {
    private int numExposicion;
    private String telefono;
    private String direccion;
    private HashMap<String, Coche> coches = new HashMap<>();

    public Exposicion(){
    }
    public void agregar(Coche coche){
        coches.put(coche.getMatricula(), coche);
    }
    public void remover(Coche coche){
        coches.put(coche.getMatricula(), coche);
    }

    public int getNumExposicion() {
        return numExposicion;
    }

    public void setNumExposicion(int numExposicion) {
        this.numExposicion = numExposicion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public HashMap<String, Coche> getCoches() {
        return coches;
    }

    public void setCoches(HashMap<String, Coche> coches) {
        this.coches = coches;
    }

    @Override
    public String toString() {
        return "Número: " + numExposicion + " | Teléfono: " + telefono + " | Dirección: " + direccion;
    }
}

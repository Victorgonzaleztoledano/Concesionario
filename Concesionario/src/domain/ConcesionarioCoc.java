package domain;

import java.util.HashMap;

public class ConcesionarioCoc {
    private HashMap<String, Coche> coches = new HashMap<>();

    public HashMap<String, Coche> getCoches() {
        return coches;
    }

    public void setCoches(HashMap<String, Coche> coches) {
        this.coches = coches;
    }
    public void agregarCoche(Coche coche){
        coches.put(coche.getMatricula(), coche);
    }
    public void removerCoche(Coche coche){
        coches.remove(coche.getMatricula());
    }
}
package domain;

import java.util.HashMap;

public class ConcesionarioExp {
    private HashMap<Integer, Exposicion> exposiciones = new HashMap<>();

    public HashMap<Integer, Exposicion> getExposiciones() {
        return exposiciones;
    }

    public void setExposiciones(HashMap<Integer, Exposicion> exposiciones) {
        this.exposiciones = exposiciones;
    }

    public void agregarExposicion(Exposicion exposicion){
        exposiciones.put(exposicion.getNumExposicion(), exposicion);
    }
    public void removerExposicion(Exposicion exposicion){
        exposiciones.remove(exposicion.getNumExposicion());
    }
}

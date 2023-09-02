package domain;

import java.util.HashMap;

public class ConcesionarioVend {
    private HashMap<String, Vendedor> vendedores = new HashMap<>();

    public HashMap<String, Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(HashMap<String, Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public void agregarVendedor(Vendedor vendedor){
        vendedores.put(vendedor.getDni(),vendedor);
    }
    public void removerVendedor(Vendedor vendedor){
        vendedores.remove(vendedor.getDni());
    }
}

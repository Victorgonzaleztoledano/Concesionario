package domain;

import java.util.HashMap;

public class ConcesionarioCli {
    private HashMap<String, Cliente> clientes = new HashMap<>();

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }
    public void agregarCliente(Cliente cliente){
        clientes.put(cliente.getDni(), cliente);
    }
    public void removerCliente(Cliente cliente){
        clientes.remove(cliente.getDni());
    }
}

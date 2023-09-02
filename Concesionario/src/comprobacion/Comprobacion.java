package comprobacion;

import domain.*;

import java.util.HashMap;

public class Comprobacion {
    private ConcesionarioCoc conceCoches;
    private HashMap<String, Coche> coches;
    private ConcesionarioCli conceClientes;
    private HashMap<String, Cliente> clientes;
    private ConcesionarioVend conceVendedores;
    private HashMap<String, Vendedor> vendedores;
    private ConcesionarioExp conceExposiciones;
    private HashMap<Integer, Exposicion> exposiciones;
    private ConcesionarioDir conceDirector;
    private Director director;

    public Comprobacion(ConcesionarioCoc conceCoches, ConcesionarioCli conceClientes, ConcesionarioVend conceVendedores, ConcesionarioExp conceExposiciones, ConcesionarioDir conceDirector) {
        this.conceCoches = conceCoches;
        coches = conceCoches.getCoches();
        this.conceClientes = conceClientes;
        clientes = conceClientes.getClientes();
        this.conceVendedores = conceVendedores;
        vendedores = conceVendedores.getVendedores();
        this.conceExposiciones = conceExposiciones;
        exposiciones = conceExposiciones.getExposiciones();
        this.conceDirector = conceDirector;
        director = conceDirector.getDirector();
    }

    public Comprobacion() {
    }

    public boolean comprobarNombre(String nombre) {
        if (nombre.matches("^[a-zA-Z]+$") && nombre != null && !nombre.isEmpty()) return true;
        else return false;
    }

    public boolean comprobarDni(String dni) {
        if (dni.matches("\\d{8}[A-Za-z]")) return true;
        else return false;
    }

    public boolean comprobarNull(String vacio) {
        if (vacio == null || vacio.trim().isEmpty()) return true;
        else return false;
    }

    public boolean comprobarTelefono(String telefono) {
        if (telefono.matches("\\d{9}")) return true;
        else return false;
    }

    public boolean comprobarObject(Object object) {
        if (object == null) return false;
        else return true;
    }

    public int comprobarOpcion(String string) {
        if (string.matches("-?\\d+")) return Integer.parseInt(string);
        else return -1;
    }

    public boolean comprobarDniRepe(String dni) {
        if (clientes.containsKey(dni)) return true;
        if (vendedores.containsKey(dni)) return true;
        if(director.getDni().equals(dni)) return true;
        else return false;
    }

    public boolean comprobarTlfnRepe(String telefono) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.getTelefono().equals(telefono)) return true;
        }
        for (Vendedor vendedor : vendedores.values()) {
            if (vendedor.getTelefono().equals(telefono)) return true;
        }
        for (Exposicion exposicion : exposiciones.values()) {
            if (exposicion.getTelefono().equals(telefono)) return true;
        }
        if(director.getTelefono().equals(telefono)) return true;
        else return false;
    }

    public boolean comprobarNum(String string) {
        int numero;
        if (string == null || string.isEmpty()) return true;
        if (!string.matches("\\d+")) return true;
        else numero = Integer.parseInt(string);
        if (numero < 1) return true;
        else return false;
    }

    public boolean comprobarNumExpoRepe(int i) {
        if (exposiciones.containsKey(i)) return true;
        else return false;
    }

    public boolean comprobarMatricula(String matricula) {
        if (matricula.matches("\\d{4}[A-Za-z]{3}")) return true;
        else return false;
    }

    public boolean comprobarMatRepe(String matricula) {
        if (coches.containsKey(matricula)) return true;
        return false;
    }

    public boolean comprobrarDirector(String dni) {
        if(director.getDni() == null) return false;
        if (director.getDni().equals(dni)) return true;
        else return false;
    }

    public boolean comprobrarVendedor(String dni) {
        if (vendedores.isEmpty()) {
            for (Vendedor vendedor : vendedores.values()) {
                if (vendedor.getDni().equals(dni)) return true;
            }
        } return false;
    }
}
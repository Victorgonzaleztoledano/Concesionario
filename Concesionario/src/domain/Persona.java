package domain;

import comprobacion.Comprobacion;
import excepciones.ConstructorException;

public abstract class Persona {
    private String nombre;
    private String direccion;
    private String dni;
    private String telefono;
    private Comprobacion comprobacion = new Comprobacion();

    public Persona(String nombre, String direccion, String dni, String telefono) throws ConstructorException {
        if (!comprobacion.comprobarNombre(nombre))
            throw new ConstructorException("El nombre no puede contener números");
        else this.nombre = nombre;
        if (comprobacion.comprobarNull(direccion)) throw new ConstructorException("La dirección no puede estar vacía");
        else this.direccion = direccion;
        if (comprobacion.comprobarDni(dni)) throw new ConstructorException("El dni introducido no es correcto");
        else this.dni = dni;
        if (comprobacion.comprobarTelefono(telefono))
            throw new ConstructorException("El teléfono introducido no es correcto");
        else this.telefono = telefono;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Dirección: " + direccion + " | Dni: " + dni + " | Teléfono: " + telefono;
    }
}
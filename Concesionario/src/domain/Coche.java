package domain;

public class Coche {
    private String marca;
    private String modelo;
    private String matricula;
    private Estado estado;
    private TipoVehiculo tipo;

    public Coche(String marca, String modelo, String matricula, Estado estado, TipoVehiculo tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.estado = estado;
        this.tipo = tipo;
    }

    public Coche() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + " | Modelo: " + modelo + " | Matrícula: " + matricula + " | Estado: " + estado + " | Tipo: " + tipo;
    }
}

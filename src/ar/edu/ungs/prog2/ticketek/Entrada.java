package ar.edu.ungs.prog2.ticketek;

public class Entrada implements IEntrada {

    private String codigoEntrada;
    String nombreSede;
    private Sector sector;
    private int fila;
    private int asiento;
    private Espectaculo espectaculo;

    public Entrada(String codigoEntrada){
        this.codigoEntrada = codigoEntrada;
    }

    public double precio() {
        return 0;
    }

    public String ubicacion() {
        return "";
    }
}

package ar.edu.ungs.prog2.ticketek;

public class Estadio extends Sede {

    private double precioUnico;

    public Estadio(String nombre, String direccion, int capacidadMax, double precioUnico) {
        super(nombre, direccion, capacidadMax);
        this.precioUnico = precioUnico;
    }
}

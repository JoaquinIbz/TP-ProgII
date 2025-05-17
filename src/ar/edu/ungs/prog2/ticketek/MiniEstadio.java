package ar.edu.ungs.prog2.ticketek;

public class MiniEstadio extends Sede {

    private int cantPuestos;
    private double precioBase;
    private double consumision;

    public MiniEstadio(String nombre, String direccion, int capacidadMax, int cantPuestos, double precioBase, double consumision) {
        super(nombre, direccion, capacidadMax);
        this.cantPuestos = cantPuestos;
        this.precioBase = precioBase;
        this.consumision = consumision;
    }
}

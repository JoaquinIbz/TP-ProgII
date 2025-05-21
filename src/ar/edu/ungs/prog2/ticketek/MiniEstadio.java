package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

/*public class MiniEstadio extends Sede {

    private int cantPuestos;
    private double precioBase;
    private double consumision;

    public MiniEstadio(String nombre, String direccion, int capacidadMax, int cantPuestos, double precioBase, double consumision) {
    
        super(nombre, direccion, capacidadMax);
        this.cantPuestos = cantPuestos;
        this.precioBase = precioBase;
        this.consumision = consumision;
    }
}*/
public class MiniEstadio extends Sede {

    String[] sectores; // {"Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"}
    int[] capacidadesPorSector; //
    int[] porcentajeAdicionalPorSector;
    int cantPuestos;
    double consumicion;
    int asientosPorFila;


    public MiniEstadio(String nombre, String direccion, int capacidadMax, int asientosPorFila, int cantPuestos,
    		double consumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMax);
        this.cantPuestos = cantPuestos;
        this.consumicion = consumicion;
        this.sectores = sectores;
        this.capacidadesPorSector = capacidad;
        this.porcentajeAdicionalPorSector = porcentajeAdicional;
    }

    public void reservarAsiento(String[] sector, int fila, int asiento) {
        return;
    }

    public void liberarAsiento(String[] sector, int fila, int asiento) {
        return;
    }

    public boolean estaDisponible(String[] sector, int fila, int asiento) {
        return false;
    }

    @Override
    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha) {
        return null;
    }
}

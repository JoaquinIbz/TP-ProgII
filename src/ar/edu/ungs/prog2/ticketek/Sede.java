package ar.edu.ungs.prog2.ticketek;

public class Sede {

    String direccion;
    String nombre;
    int capacidadActual;
    int capacidadMax;
    int asientosDisponibles;
    double precioUnico;
    double precioBase;
    int cantPuestos;
    double consumicion;
    Sector sector;

    public Sede(String direccion, String nombre, int capacidadMax){
        this.direccion = direccion;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
    }



}

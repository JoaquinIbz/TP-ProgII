package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public abstract class Sede {

    String nombre;
    String direccion;
    int capacidadMax;
    int capacidadActual;
    double precioUnico;
    double precioBase;
    int[] capacidad;
    private int numero;
    private int cantFilas;
    int asientosDisponibles;
    

    public Sede(String nombre, String direccion, int capacidadMax){
        this.direccion = direccion;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
    }

    public abstract Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha);


}

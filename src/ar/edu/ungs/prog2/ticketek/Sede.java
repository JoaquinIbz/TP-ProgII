package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public abstract class Sede {

    String direccion;
    String nombre;
    int capacidadActual;
    int capacidadMax;
    int asientosDisponibles;
    double precioUnico;
    double precioBase;
    String[] sectores;
    int[] capacidad;
    private int numero;
    private int cantFilas;
    private HashMap<Integer,Integer> asientosPorFila;
    

    public Sede(String nombre, String direccion, int capacidadMax){
        this.direccion = direccion;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;

    }



}

package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Sede {

    String nombre;
    String direccion;
    int capacidadMax;
    int capacidadActual;
    double precioBase;
    int[] capacidad;
    private int numero;
    private int cantFilas;
    int asientosDisponibles;
    HashMap<String, LinkedList<Entrada>> entradasVendidas; // fecha, lista de entradas
    

    public Sede(String nombre, String direccion, int capacidadMax){
        this.direccion = direccion;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

    public abstract double calcularPrecio(double precioBase);

}

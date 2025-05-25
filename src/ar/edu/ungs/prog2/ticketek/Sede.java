package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Sede {

    String nombre;
    String direccion;
    int capacidadMax;
    int capacidadActual;
    int[] capacidad;
    private int numero;
    private int cantFilas;
    int asientosDisponibles;
    HashMap<String, HashMap<Integer, Entrada>> entradasVendidas; //clave = fecha, valor= clave=codigo valor= Entrada
    

    public Sede(String nombre, String direccion, int capacidadMax){
        this.direccion = direccion;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

    public abstract double calcularPrecio(String fecha, String sector, double precioBase);

    public abstract double recaudacion(String fecha);

    public abstract Entrada venderEntrada(String email, String nombreSede, Espectaculo espectaculo, String fecha, String sector, int asiento);

    public abstract int cantidadDeEntradasVendidas();

    public abstract String toString();

    public abstract void anularEntrada(String sector, int fila, int asiento);

    public abstract String toString(String fecha);

}

	

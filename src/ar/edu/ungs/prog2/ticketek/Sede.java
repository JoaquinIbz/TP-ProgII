package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Sede {

    String nombre;
    String direccion;
    int capacidadMax;
    int capacidadActual;
    int[] capacidad;
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

    public String toString(){
        return "Sede: "+this.nombre+", Direccion: "+this.direccion;
    }
    
    public abstract String entradaVendidaPorFecha(String fecha);

    public abstract void anularEntrada(String sector, int fila, int asiento);

    public abstract double recaudacionTotalPorSede(String nombreEspectaculo,String nombreSede);



}

	

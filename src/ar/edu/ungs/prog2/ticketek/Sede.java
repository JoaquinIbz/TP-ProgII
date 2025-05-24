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

    public abstract double calcularPrecio(double precioBase);

    public int entradasVendidasPorSector(String fecha, String sector) {
    	int cont = 0;
        if(this.entradasVendidas.containsKey(fecha)){
            return 0;
        }
        HashMap<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
        for(Entrada e : entradas.values()){
            if(e.getSector().equals(sector)){
                cont++;
            }
        }
    	return cont;
    }

    public abstract String toString();

    public abstract void anularEntrada(String sector, int fila, int asiento);

    public abstract String toString(String fecha);
}

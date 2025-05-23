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
    HashMap<String, LinkedList<Entrada>> entradasVendidas; // fecha, lista de entradas
    

    public Sede(String nombre, String direccion, int capacidadMax){
        this.direccion = direccion;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

    public abstract double calcularPrecio(double precioBase);
    
    public int cantidadEntradasVendidas(String fecha) {
    	LinkedList<Entrada> entradas = entradasVendidas.get(fecha);
    	if(entradas != null) {
    		return entradas.size();
    	}
    	return 0;
    }
    public int entradasVendidasPorSector(String fecha, String sector) {
    	int cont = 0;
    	LinkedList<Entrada> entradas = entradasVendidas.get(fecha);
    	if(entradas != null) {
    		for(Entrada e : entradas) {
    			if(e.getSector().equals(sector) && e.getSector() != null) {
    				cont++;
    			}
    		}
    	}
    	return cont;
    }

    public abstract String toString();

    public abstract String entradasVendidas(String fecha);

}

package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public abstract class Sede {

    protected String nombre;
    protected String direccion;
    protected int capacidadMax;
    protected int[] capacidad;
    protected Map<String, Map<Integer, Entrada>> entradasVendidas; //clave = fecha, valor= clave=codigo valor= Entrada
    //------------------------------------------
    protected Map<String, Double> recaudacionPorEspectaculo; 
    //------------------------------------------
    

    public Sede(String nombre, String direccion, int capacidadMax){
    	if(nombre == null || direccion == null || capacidadMax <= 0) {
    		throw new RuntimeException("Por favor, ingrese datos validos");
    	}
        this.direccion = direccion;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.entradasVendidas = new HashMap<>();
        this.recaudacionPorEspectaculo = new HashMap<>();
    }
    //estadio
    
    public abstract Entrada venderEntrada(String email, Espectaculo espectaculo, String fecha, String sector);
    //teatro, miniestadio
    
    public abstract Entrada venderEntrada(String email, Espectaculo espectaculo, String fecha, String sector, int numeroButaca);
    

    public abstract double calcularPrecio(String fecha, String sector, double precioBase);

    public abstract double recaudacion(String fecha);


    public String toString(){
        return "Sede: "+this.nombre+", Direccion: "+this.direccion;
    }
    
    public abstract String entradaVendidaPorFecha(String fecha);

//    public abstract void anularEntrada(String sector, int fila, int asiento);
    public abstract void anularEntrada(String sector, int fila,  int numeroAsiento);

    public abstract double recaudacionTotalPorSede(String nombreEspectaculo,String nombreSede);
    
    public abstract boolean tieneButacas();	

	public int getCapacidadMax() {
		return capacidadMax;
	}

	public String getNombre() {
		return nombre;
	}



}

	

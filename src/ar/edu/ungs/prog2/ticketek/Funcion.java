package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Funcion {

    private Sede sede;
    private String nombreEspectaculo;
    private String fecha;
    private double precioBase;
//------------------------------------------------
    private Map<String, Set<Integer>> asientosOcupadosPorSector; //clave= nombre del sector, valor= conjunto con numeros de asientos ocupados 
    private int capacidadMaxima; //solo para Estadio
    private int cantEntradasVendidas;  //solo para Estadio
//------------------------------------------------
    public Funcion(Sede sede, String fecha, double precioBase) {
    	if(sede == null || fecha == null || precioBase <= 0) {
    		throw new RuntimeException("Los datos son invalidos");
    	}
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
        this.asientosOcupadosPorSector = new HashMap<>();
        
        if(!sede.tieneButacas()) {
        	this.capacidadMaxima = ((Estadio)sede).getCapacidadMax();
        	this.cantEntradasVendidas = 0;
        }
    }

//-----------------------------------------------------------
    public boolean estaOcupado(String sector, int asiento) {
    	Set<Integer> ocupados = asientosOcupadosPorSector.getOrDefault(sector, new HashSet<>());
    	return ocupados.contains(asiento);
    }
    
    public void ocuparAsiento(String sector, int asiento) {
    	Set<Integer> ocupados = asientosOcupadosPorSector.computeIfAbsent(sector, k -> new HashSet<>());
    	ocupados.add(asiento);
    }
    
    public boolean hayLugarDisponible() {
    	return cantEntradasVendidas < capacidadMaxima;
    }
    
    public void registrarEntrada() {
    	cantEntradasVendidas++;
    }
//-----------------------------------------------------------
    public double calcularPrecioEstadio(String fecha){
        return this.sede.calcularPrecio(fecha, "CAMPO",this.precioBase);
    }

    public double calcularPrecioTeatroMiniestadio(String fecha, String sector){
        return this.sede.calcularPrecio(fecha, sector, this.precioBase);
    }

    public double recaudacion(){
        return this.sede.recaudacion(this.fecha);
    }

    public String toString() {
        //*  - (24/07/2025) El Monumental - 200/500
        //*  - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50
        StringBuilder sb = new StringBuilder();
        sb.append(" - ("+this.fecha+") "+ this.sede.entradaVendidaPorFecha(this.fecha));
        return sb.toString();
    }

	public Sede getSede() {
		return sede;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}


	


}

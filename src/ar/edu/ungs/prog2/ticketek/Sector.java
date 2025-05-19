	package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Sector {

    private int numero;
    private String tipoSector;
    private int cantFilas;
    private HashMap<Integer,Integer> asientosPorFila; // fila, asiento

    public Sector(int numero, String tipoSector, int filas, int asientos){
    	this.numero = numero;
    	this.tipoSector = tipoSector;
    	this.cantFilas = filas;
    }
}

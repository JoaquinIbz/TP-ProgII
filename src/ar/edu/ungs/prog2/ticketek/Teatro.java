package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Teatro extends Sede{

    String[] sectores; // {"Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"} O {"Campo"}
    int asientosPorFila;
    int[] porcentajeAdicional;
    HashMap<String,HashMap<Integer, Integer>> asientos; // [SECTOR] [FILA, ASIENTO]

    public Teatro(String nombre, String direccion, int capacidadMax, int asientosPorFila, String[] sectores,
    		int[] capacidad, int[] porcentajeAdicional) {
    	super(nombre, direccion, capacidadMax);
        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
    	
    	
    }

    public void reservarAsiento(String[] sector, int fila, int asiento) {
        return;
    }

    public void liberarAsiento(String[] sector, int fila, int asiento) {
        return;
    }

    public boolean estaDisponible(String[] sector, int fila, int asiento) {
        return false;
    }

    @Override
    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha) {
        return null;
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return 0;
    }
}

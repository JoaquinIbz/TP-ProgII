package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Teatro extends Sede{

    String[] sectores; // {"Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"} O {"Campo"}
    int asientosPorFila;
    int[] porcentajeAdicional;
    HashMap<String,HashMap<Integer, Integer>> asientos;// [SECTOR] [FILA, ASIENTO]

    public Teatro(String nombre, String direccion, int capacidadMax, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
    	super(nombre, direccion, capacidadMax);
        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.asientos = new HashMap<>();
    }

    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha, String sector, int[] asientos) {
        Entrada entrada = new Entrada(email,nombreSede,nombreEspectaculo,fecha,asientos,sector);
        if(!this.entradasVendidas.containsKey(fecha)){
            LinkedList<Entrada> e = new LinkedList<>();
            e.add(entrada);
            this.entradasVendidas.put(fecha,e);
        }else{
            this.entradasVendidas.get(fecha).add(entrada);
        }



        return entrada;
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return 0;
    }

    public void liberarAsiento(String sector, int[] asientos) {
        return;
    }

    public boolean estaDisponible(String sector, int[] asientos) {
        return false;
    }

}

package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Teatro extends Sede{

    String[] sectores; // {"Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"}
    int asientosPorFila;
    int[] porcentajeAdicional;
    HashMap<String,HashMap<Integer, LinkedList<Integer>>> asientosDisponibles;// [SECTOR] [FILA, LISTA<ASIENTOS>]

    public Teatro(String nombre, String direccion, int capacidadMax, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
    	super(nombre, direccion, capacidadMax);
        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.asientosDisponibles = inicializarAsientosDisponibles(sectores,capacidad);
    }

    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha, String sector, int[] asientos) {
        if(!estaDisponible(sector,asientos)){
            throw new RuntimeException("El sector y/o los asientos son incorrectos.");
        }
        this.asientosDisponibles.put(sector,ocuparAsiento(sector,asientos));       // ocupa asiento
        Entrada entrada = new Entrada(email,nombreSede,nombreEspectaculo,fecha,asientos,sector);
        entrada.setSector(sector);
        entrada.setEmail(email);
        entrada.setUbicacion(buscarFilaYasiento(sector,asientos));

        if(!this.entradasVendidas.containsKey(fecha)){
            LinkedList<Entrada> e = new LinkedList<>();
            e.add(entrada);
            this.entradasVendidas.put(fecha,e);
        }else{
            this.entradasVendidas.get(fecha).add(entrada);
        }
        return entrada;
    }

    private LinkedList<Butaca> buscarFilaYasiento(String sector, int[] asientos){
        LinkedList<Butaca> butacas = new LinkedList<>();
        HashMap<Integer,LinkedList<Integer>> filaYasientos = this.asientosDisponibles.get(sector);
        for(Map.Entry<Integer,LinkedList<Integer>> entrada : filaYasientos.entrySet()){
            for(int asiento : asientos){
                if(entrada.getValue().contains(asiento)){
                    Butaca butaca = new Butaca(entrada.getKey(),asiento);
                    butacas.add(butaca);
                }
            }
        }
        return butacas;
    }


    public HashMap<Integer,LinkedList<Integer>> ocuparAsiento(String sector, int[] asientos){
        HashMap<Integer, LinkedList<Integer>> mapa = null;
        if(estaDisponible(sector,asientos)) {
            HashMap<Integer, LinkedList<Integer>> filasYasientos = this.asientosDisponibles.get(sector);
            for (int asiento=0 ; asiento<asientos.length ; asiento++) {
                for (LinkedList<Integer> listaAsientos : filasYasientos.values()) {
                    listaAsientos.remove(asiento);
                }
            }
            mapa = filasYasientos;
        }
        return mapa;
    }

    public boolean estaDisponible(String sector, int[] asientos) {
        HashMap<Integer, LinkedList<Integer>> filasYasientos = this.asientosDisponibles.get(sector);
        if (filasYasientos == null) {
            return false;
        }
        for(int asiento : asientos){
            boolean encontrado = false;
            for(LinkedList<Integer> listaAsientos : filasYasientos.values()){
                if(listaAsientos.contains(asiento)){
                    encontrado = true;
                    break;
                }
            }
            if(!encontrado){
                return false;
            }
        }
        return true;
    }

    private HashMap<String, HashMap<Integer,LinkedList<Integer>>> inicializarAsientosDisponibles(String[] sectores, int[] capacidad){
        //      0               1               2               3           INDICE
        // ["Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"]     SECTOR
        // [    100,            200,           300,           400     ]     CAPACIDAD

        //     [SECTOR]          [FILA] [ASIENTO]
        HashMap<String, HashMap<Integer,LinkedList<Integer>>> mapa = new HashMap<>();
        for(int sector=0 ; sector<sectores.length ; sector++){                              // ej platea vip

            int totalAsientos = capacidad[sector];                                          // ej capacidad 100
            HashMap<Integer, LinkedList<Integer>> filaYAsientos = new HashMap<>();          // fila, asientos

            for(int numeroAsiento=1 ; numeroAsiento<=totalAsientos ; numeroAsiento++){      // asiento 1, 2, 3, 4, ... hasta 100.

                int fila = ((numeroAsiento - 1) / this.asientosPorFila) + 1;
                LinkedList<Integer> asientos = new LinkedList<>();
                if(!filaYAsientos.containsKey(fila)){
                    asientos.add(numeroAsiento);
                    filaYAsientos.put(fila,asientos);
                }else{
                    filaYAsientos.get(fila).add(numeroAsiento);
                }
            }
            mapa.put(sectores[sector],filaYAsientos);
        }
        return mapa;
    }

    public void liberarAsiento(String sector, int[] asientos) {
        return;
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return 0;
    }

    public String toString(){
        // - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50
        return ""+this.nombre+" - "+this.sectores[0]+": "+
    }


}

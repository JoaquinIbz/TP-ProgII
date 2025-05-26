package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class MiniEstadio extends Sede {

    String[] sectores; // {"Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"} O {"Campo"}
    int asientosPorFila;
    int[] porcentajeAdicional;
    HashMap<String,HashMap<Integer, LinkedList<Integer>>> asientosDisponibles;// [SECTOR] [FILA, LISTA<ASIENTOS>]
    int cantPuestos;
    double consumicion;


    public MiniEstadio(String nombre, String direccion, int capacidadMax, int asientosPorFila, int cantPuestos, double consumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMax);
        this.asientosPorFila = asientosPorFila;
        this.cantPuestos = cantPuestos;
        this.consumicion = consumicion;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.asientosDisponibles = inicializarAsientosDisponibles(sectores,capacidad);

    }

    public Entrada venderEntrada(String email, String nombreSede, Espectaculo espectaculo, String fecha, String sector, int asiento) {
        if(!estaDisponible(sector,asiento)){
            throw new RuntimeException("El sector y/o los asientos son incorrectos.");
        }

        Entrada entrada = new Entrada(email,nombreSede,espectaculo,fecha,asiento,sector);
        entrada.setButaca(buscarFilaYasiento(sector,asiento));

        this.asientosDisponibles.put(sector,ocuparAsiento(sector,asiento));       // ocupa asiento

        if(!this.entradasVendidas.containsKey(fecha)){
            this.entradasVendidas.put(fecha,new HashMap<>());
        }
        this.entradasVendidas.get(fecha).put(entrada.getCodigo(),entrada);
        return entrada;
    }

    private Butaca buscarFilaYasiento(String sector, int asiento){
        Butaca butaca = null;
        HashMap<Integer,LinkedList<Integer>> filaYasientos = this.asientosDisponibles.get(sector);
        for(Map.Entry<Integer,LinkedList<Integer>> entrada : filaYasientos.entrySet()){
            if(entrada.getValue().contains(asiento)){
                Butaca b = new Butaca(entrada.getKey(),asiento);
                butaca = b;

            }
        }
        return butaca;
    }


    public HashMap<Integer,LinkedList<Integer>> ocuparAsiento(String sector, int asiento){
        HashMap<Integer, LinkedList<Integer>> mapa = null;
        if(estaDisponible(sector,asiento)) {
            HashMap<Integer, LinkedList<Integer>> filasYasientos = this.asientosDisponibles.get(sector);
            for (LinkedList<Integer> listaAsientos : filasYasientos.values()) {
                listaAsientos.remove(Integer.valueOf(asiento));
            }
            mapa = filasYasientos;
        }
        return mapa;
    }

    public boolean estaDisponible(String sector, int asiento) {
        HashMap<Integer, LinkedList<Integer>> filasYasientos = this.asientosDisponibles.get(sector);
        if (filasYasientos == null) {
            return false;
        }
        for(LinkedList<Integer> listaAsientos : filasYasientos.values()){
            if(listaAsientos.contains(asiento)){
                return true;
            }
        }
        return false;
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

    @Override
    public void anularEntrada(String sector, int fila, int asiento) {
    	HashMap<Integer, LinkedList<Integer>> filas = asientosDisponibles.get(sector);
        if(filas == null){
            throw new RuntimeException("Sector no encontrado: " + sector);
        }

        LinkedList<Integer> asientos = filas.get(fila);
        if(asientos == null){
            throw new RuntimeException("Fila no encontrada en sector " + sector + ": fila " + fila);
        }

        asientos.add(asiento);
    }
    
    /*
    public boolean puedeVenderEntrada(String fecha, String sector, int asiento) {
    	HashMap<Integer, LinkedList<Integer>> filas = asientosDisponibles.get(sector);
    	if(filas == null) return false;
    	for(LinkedList<Integer> listaAsientos : filas.values()) {
    		if(listaAsientos.contains(asiento)) {
    			return true;
    		}
    	}
    	return false;
    }

     */

    public int cantidadDeEntradasVendidas(){
        int cantidad = 0;
        for(Map.Entry<String,HashMap<Integer,Entrada>> entry : this.entradasVendidas.entrySet()){
            HashMap<Integer,Entrada> entradas = entry.getValue();
            for(Entrada e : entradas.values()){
                cantidad++;
            }
        }
        return cantidad;
    }
    @Override
    public double calcularPrecio(String fecha,String sector,double precioBase){
        double porcentaje = obtenerPorcentajePorSector(sector);
        return precioBase * (1+porcentaje/100);
    }

    @Override
    public double recaudacion(String fecha) {
        double recaudacion = 0;
        HashMap<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
        if(entradas != null){
            for(Entrada entrada : entradas.values()){
                recaudacion += entrada.precio() + this.consumicion;
            }
        }
        return recaudacion;
    }

    private double obtenerPorcentajePorSector(String sector){
        if(sector.equals(this.sectores[0])){
            return this.porcentajeAdicional[0];
        }else if(sector.equals(this.sectores[1])){
            return this.porcentajeAdicional[1];
        }else if(sector.equals(this.sectores[2])){
            return this.porcentajeAdicional[2];
        }
        return this.porcentajeAdicional[3];
    }


    public String toString(String fecha) {
        //*  - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre+" - ");
        for(int i=0 ; i<this.sectores.length ; i++){
            sb.append(this.sectores[i]+": ");
            int vendidas = 0;
            HashMap<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
            if(entradas != null){
                for(Entrada e : entradas.values()){
                    if(e.getSector().equals(this.sectores[i])){
                        vendidas++;
                    }
                }
                sb.append(vendidas+"/"+this.capacidad[i]);
                if(i < this.sectores.length - 1){
                    sb.append(" | ");
                }
            }else{
                sb.append(vendidas+"/"+this.capacidad[i]);
                if(i < this.sectores.length - 1){
                    sb.append(" | ");
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String toString(){
        return this.nombre;
    }

    @Override
    public double recaudacionTotalPorSede(String nombreEspectaculo,String nombreSede) {
        double total = 0;
        for(Map.Entry<String,HashMap<Integer,Entrada>> entry : this.entradasVendidas.entrySet()) {
            HashMap<Integer,Entrada> entradas = entry.getValue();
            for(Entrada entrada : entradas.values()){
                if(entrada.getNombreEspectaculo().equals(nombreEspectaculo) && entrada.nombreSede.equals(nombreSede)){
                    total += entrada.precio() + this.consumicion;
                }

            }
        }
        return total;
    }
}

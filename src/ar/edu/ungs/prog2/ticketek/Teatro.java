package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Teatro extends Sede{

	private String[] sectores; // {"Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"}
	private int asientosPorFila;
	private int[] porcentajeAdicional;
	private Map<String,Map<Integer, List<Integer>>> asientosDisponibles; // [SECTOR] [FILA, LISTA<ASIENTOS>]

    public Teatro(String nombre, String direccion, int capacidadMax, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
    	super(nombre, direccion, capacidadMax);
    	
    	if (asientosPorFila <= 0 || sectores == null || capacidad == null || porcentajeAdicional == null
    		    || sectores.length == 0 || capacidad.length == 0 || porcentajeAdicional.length == 0
    		    || sectores.length != capacidad.length || capacidad.length != porcentajeAdicional.length) {
    		    throw new RuntimeException("Datos inválidos para Teatro");
    		}
    	
        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
        this.asientosDisponibles = inicializarAsientosDisponibles(sectores,capacidad);
    }

 /*   public Entrada venderEntrada(String email, String nombreSede, Espectaculo espectaculo, String fecha, String sector, int asiento) {
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
    }*/
    @Override
    public Entrada venderEntrada(String email, Espectaculo espectaculo, String fecha, String sector, int numeroButaca){
    	 Funcion funcion = espectaculo.obtenerFuncion(fecha);
    	  if (funcion.estaOcupado(sector, numeroButaca)) {
    	     throw new RuntimeException("El asiento ya está ocupado.");
    	  }

    	  Entrada entrada = new Entrada(email, this.nombre, espectaculo, fecha, numeroButaca, sector);
    	  entradasVendidas.putIfAbsent(fecha, new HashMap<>());
    	  entradasVendidas.get(fecha).put(entrada.getCodigo(), entrada);
    	  funcion.ocuparAsiento(sector, numeroButaca);
    	  
    	  //--------------------
    	  double precio = entrada.precio();
    	  if(recaudacionPorEspectaculo.containsKey(espectaculo.getNombre())) {
    		  double precioActual = recaudacionPorEspectaculo.get(espectaculo.getNombre());
    		  recaudacionPorEspectaculo.put(espectaculo.getNombre(), precioActual + precio);
    	  }else {
    		  recaudacionPorEspectaculo.put(espectaculo.getNombre(), precio);
    	  }
    	  //------------------------
    	  return entrada;
    }
    @Override 
    public Entrada venderEntrada(String email, Espectaculo espectaculo, String fecha, String sector) {
    	throw new RuntimeException("Es teatro, lleva butaca");
    }

//    private Butaca buscarFilaYasiento(String sector, int asiento){
//        Butaca butaca = null;
//        Map<Integer,List<Integer>> filaYasientos = this.asientosDisponibles.get(sector);
//        for(Map.Entry<Integer,List<Integer>> entrada : filaYasientos.entrySet()){
//            if(entrada.getValue().contains(asiento)){
//                Butaca b = new Butaca(entrada.getKey(),asiento);
//                butaca = b;
//
//            }
//        }
//        return butaca;
//    }


    public Map<Integer,List<Integer>> ocuparAsiento(String sector, int asiento){
        Map<Integer, List<Integer>> mapa = null;
        if(estaDisponible(sector,asiento)) {
            Map<Integer, List<Integer>> filasYasientos = this.asientosDisponibles.get(sector);
            for (List<Integer> listaAsientos : filasYasientos.values()) {
                listaAsientos.remove(Integer.valueOf(asiento));
            }
            mapa = filasYasientos;
        }
        return mapa;
    }

    public boolean estaDisponible(String sector, int asiento) {
        Map<Integer, List<Integer>> filasYasientos = this.asientosDisponibles.get(sector);
        if (filasYasientos == null) {
            return false;
        }
        for(List<Integer> listaAsientos : filasYasientos.values()){
            if(listaAsientos.contains(asiento)){
                return true;
            }
        }
        return false;
    }

    private Map<String, Map<Integer,List<Integer>>> inicializarAsientosDisponibles(String[] sectores, int[] capacidad){
        //      0               1               2               3           INDICE
        // ["Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"]     SECTOR
        // [    100,            200,           300,           400     ]     CAPACIDAD

        //     [SECTOR]          [FILA] [ASIENTO]
        Map<String, Map<Integer,List<Integer>>> mapa = new HashMap<>();
        for(int sector=0 ; sector<sectores.length ; sector++){                              // ej platea vip
            int totalAsientos = capacidad[sector];                                          // ej capacidad 100
            Map<Integer, List<Integer>> filaYAsientos = new HashMap<>();          // fila, asientos

            for(int numeroAsiento=1 ; numeroAsiento<=totalAsientos ; numeroAsiento++){      // asiento 1, 2, 3, 4, ... hasta 100.
                int fila = ((numeroAsiento - 1) / this.asientosPorFila) + 1;
                List<Integer> asientos = new LinkedList<>();
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

/*    @Override
    public void anularEntrada(String sector, int fila, int asiento) {
    	HashMap<Integer, LinkedList<Integer>> filas = asientosDisponibles.get(sector);
        if (filas == null) {
            throw new RuntimeException("Sector no encontrado: " + sector);
        }

        LinkedList<Integer> asientos = filas.get(fila);
        if (asientos == null) {
            throw new RuntimeException("Fila no encontrada en sector " + sector + ": fila " + fila);
        }

        asientos.addLast(asiento); // O(1) al final
    }*/
    @Override
    public void anularEntrada(String sector, int fila, int numeroAsiento) {
        int filaCalculada = ((numeroAsiento - 1) / this.asientosPorFila) + 1;

        // Recuperar las filas del sector
        Map<Integer, List<Integer>> filas = asientosDisponibles.get(sector);
        if (filas == null) {
            throw new RuntimeException("Sector no encontrado: " + sector);
        }

        List<Integer> asientos = filas.get(filaCalculada);
        if (asientos == null) {
            throw new RuntimeException("Fila no encontrada en sector " + sector + ": fila " + filaCalculada);
        }

        asientos.addLast(numeroAsiento); // Liberar el asiento
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

    @Override
    public double calcularPrecio(String fecha,String sector,double precioBase){
        double porcentaje = obtenerPorcentajePorSector(sector);
        return precioBase * (1+(porcentaje/100));
    }


    @Override
    public double recaudacion(String fecha) {
        double recaudacion = 0;
        Map<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
        if(entradas != null){
            for(Entrada entrada : entradas.values()){
                recaudacion += entrada.precio();
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
    @Override
    public String entradaVendidaPorFecha(String fecha) {
        //*  - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre+" - ");
        for(int i=0 ; i<this.sectores.length ; i++){
            sb.append(this.sectores[i]+": ");
            int vendidas = 0;
            Map<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
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
        return "Teatro: "+this.nombre+", Direccion: "+this.direccion;
    }

 /*   @Override
    public double recaudacionTotalPorSede(String nombreEspectaculo,String nombreSede) {
        double total = 0;
        for(Map.Entry<String,Map<Integer,Entrada>> entry : this.entradasVendidas.entrySet()) {
            Map<Integer,Entrada> entradas = entry.getValue();
            for(Entrada entrada : entradas.values()){
                if(entrada.getNombreEspectaculo().equals(nombreEspectaculo) && entrada.getNombreSede().equals(nombreSede)){
                    total += entrada.precio();
                }
            }
        }
        return total;
    }*/
    @Override
    public double recaudacionTotalPorSede(String nombreEspectaculo, String nombreSede) {
    	return recaudacionPorEspectaculo.getOrDefault(nombreEspectaculo, 0.0);//Si existe un espectaculo con ese nombre, devuelve el valor de ese espectaculo
    																		  //Si no existe, devuelve 0.0
    }
    @Override
    public boolean tieneButacas() {
    	return true;
    }
}

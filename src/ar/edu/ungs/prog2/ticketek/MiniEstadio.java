package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*public class MiniEstadio extends Sede {

    private int cantPuestos;
    private double precioBase;
    private double consumision;

    public MiniEstadio(String nombre, String direccion, int capacidadMax, int cantPuestos, double precioBase, double consumision) {
    
        super(nombre, direccion, capacidadMax);
        this.cantPuestos = cantPuestos;
        this.precioBase = precioBase;
        this.consumision = consumision;
    }
}*/
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

    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha, String sector, int[] asientos) {
        if(!estaDisponible(sector,asientos)){
            throw new RuntimeException("El sector y/o los asientos son incorrectos.");
        }
        this.asientosDisponibles.put(sector,ocuparAsiento(sector,asientos));       // ocupa asiento
        Entrada entrada = new Entrada(email,nombreSede,nombreEspectaculo,fecha,asientos,sector);
        entrada.setButacas(buscarFilaYasiento(sector,asientos));


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
            for(int asiento : asientos){
                for(Map.Entry<Integer,LinkedList<Integer>> entry : filasYasientos.entrySet()){
                    LinkedList<Integer> lista = entry.getValue();
                    if(lista.contains(asiento)){
                        lista.remove(asiento);
                        break;
                    }
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

    @Override
    public String toString() {
        //*  - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre+" - ");
        return sb.toString();
    }

    public String entradasVendidas(String fecha){
        StringBuilder sb = new StringBuilder();
        LinkedList<Entrada> entradas = this.entradasVendidas.get(fecha);
        HashMap<String, Integer> contadorPorSector = new HashMap<>(); // Sector, entradas vendidas
        for(String sector : this.sectores){
            contadorPorSector.put(sector,0);
        }
        if(entradas != null){
            for(Entrada e : entradas){
                String sector = e.getSector();
                if(contadorPorSector.containsKey(sector)){
                    int vendidas = contadorPorSector.get(sector);
                    if(e.getButacas()!=null){
                        int cantidad = e.getButacas().length;
                        vendidas += cantidad;
                        contadorPorSector.put(sector,vendidas);
                    }
                    vendidas += 1;
                    contadorPorSector.put(sector,vendidas);
                }
            }
        }
        for(int s=0 ; s<this.sectores.length ; s++){
            int cantidad = contadorPorSector.get(this.sectores[s]);
            sb.append(this.sectores[s]+": "+ cantidad+"/"+this.capacidad[s]);
            if(s < this.sectores.length -1){
                sb.append(" | ");
            }
        }
        return sb.toString();
    }
}

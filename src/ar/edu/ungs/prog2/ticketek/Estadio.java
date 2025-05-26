package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Estadio extends Sede {

    String sector;
    double precioUnico;

    public Estadio(String nombre, String direccion, int capacidadMax) {
        super(nombre, direccion, capacidadMax);
        this.capacidadActual = this.capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

    public Entrada venderEntrada(String email, String nombreSede, Espectaculo espectaculo, String fecha, String sector, int asiento){
        Entrada entrada = new Entrada(email,nombreSede, espectaculo, fecha, sector);
        entrada.setButaca(new Butaca(0,0));
        if(this.capacidadActual > 0){
            if(!this.entradasVendidas.containsKey(fecha)){
                HashMap<Integer,Entrada> entradas = new HashMap<>();
                entradas.put(entrada.getCodigo(),entrada);
                this.entradasVendidas.put(fecha,entradas);
                this.capacidadActual --;
            }else{
                HashMap<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
                entradas.put(entrada.getCodigo(),entrada);
                this.entradasVendidas.put(fecha,entradas);
                this.capacidadActual --;
            }
        }else{
            throw new RuntimeException("No hay mas espacio para vender.");
        }
        return entrada;
    }


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
        return precioBase;
    }


    @Override
    public double recaudacion(String fecha) {
        double recaudacion = 0;
        HashMap<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
        for(Entrada entrada : entradas.values()){
            recaudacion += entrada.precio();
        }
        return recaudacion;
    }

    @Override
    public String toString(){
        return this.nombre;
    }

    public String toString(String fecha) {
        // - (24/07/2025) El Monumental - 200/500
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre+" - ");
        int vendidas = 0;
        HashMap<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
        if(entradas != null){
            vendidas = entradas.size();
            sb.append(vendidas+"/"+this.capacidadMax+"\n");
            return sb.toString();
        }else{
            sb.append(vendidas+"/"+this.capacidadMax+"\n");
            return sb.toString();
        }
    }

    @Override
    public void anularEntrada(String sector, int fila, int asiento) {
    	this.capacidadActual++;
    }


    public boolean puedeVenderEntrada(String fecha) {
    	if(capacidadActual > 0) return true;
    	return false;
    }
    
    @Override
    public double recaudacionTotalEspectaculo(String nombreEspectaculo) {
    	double total = 0;
    	for(HashMap<Integer, Entrada> mapaEntradas : entradasVendidas.values()) {
    		for(Entrada entrada : mapaEntradas.values()) {
    			if(entrada.getEspectaculo().nombre.equals(nombreEspectaculo)) {
    				System.out.println("Contando entrada " + entrada.getCodigo() + " para " + entrada.getEspectaculo().nombre + " - $" + entrada.precio());

    				total += entrada.precio();
    			}
    		}
    	}
    	return total;
    }
}

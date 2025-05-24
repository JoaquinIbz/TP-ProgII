package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Estadio extends Sede {

    String sector;

    public Estadio(String nombre, String direccion, int capacidadMax) {
        super(nombre, direccion, capacidadMax);
        this.sector = "CAMPO";
        this.capacidadActual = this.capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha){
        Entrada entrada = new Entrada(email,nombreSede, nombreEspectaculo, fecha);
        entrada.setSector(sector);
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

    @Override
    public double calcularPrecio(double precioBase){
        return precioBase;
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
}

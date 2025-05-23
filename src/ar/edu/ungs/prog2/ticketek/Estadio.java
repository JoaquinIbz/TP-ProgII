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
        if(this.capacidadActual > 0){
            if(!this.entradasVendidas.containsKey(fecha)){
                LinkedList<Entrada> e = new LinkedList<>();
                e.add(entrada);
                this.entradasVendidas.put(fecha,e);
                this.capacidadActual --;
            }else{
                this.entradasVendidas.get(fecha).add(entrada);
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
    public String toString() {
        // - (24/07/2025) El Monumental - 200/500
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre+" - ");
        return sb.toString();
    }

    public String entradasVendidas(String fecha){
        LinkedList<Entrada> entradas = this.entradasVendidas.get(fecha);
        if(entradas == null){
            return ""+0+"/"+this.capacidadMax;
        }
        return ""+entradas.size()+"/"+this.capacidadMax;
    }


}

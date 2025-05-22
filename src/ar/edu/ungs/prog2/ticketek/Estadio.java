package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Estadio extends Sede {

    String sector;

    public Estadio(String nombre, String direccion, int capacidadMax) {
        super(nombre, direccion, capacidadMax);
        this.sector = "CAMPO";
        this.capacidadMax = capacidadMax;
        this.capacidadActual = this.capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha){
        Entrada entrada = new Entrada(email,nombreSede, nombreEspectaculo, fecha);
        entrada.setEmail(email);
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


}

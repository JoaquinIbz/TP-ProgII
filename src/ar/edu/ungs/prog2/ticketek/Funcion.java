package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Funcion {

    Sede sede;
    String codigo;
    String fecha;
    double precioBase;

    public Funcion(Sede sede, String fecha, double precioBase){
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
    }
    public double calcularPrecioFinal(String[] sector, double extraConsumision){
        return 0.0;

    }
}

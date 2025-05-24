package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Funcion {

    Sede sede;
    String nombreEspectaculo;
    String fecha;
    double precioBase;

    public Funcion(Sede sede, String fecha, double precioBase) {
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
    }

    public double calcularPrecio() {
        if (this.sede instanceof Estadio) {
            Estadio estadio = (Estadio) this.sede;
            return estadio.calcularPrecio(this.precioBase);
        }
        return 0.0;
    }

    public String toString() {
        //*  - (24/07/2025) El Monumental - 200/500
        //*  - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }


	


}

package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Funcion {

    Sede sede;
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
        //" - (01/03/25) Microestadio Sur - VIP: 0/50 | Comun: 0/100 | Baja: 4/150 | Alta: 0/200\n"
        if(this.sede instanceof Estadio){
            return
        }
    }
}

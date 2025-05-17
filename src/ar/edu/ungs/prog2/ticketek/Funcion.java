package ar.edu.ungs.prog2.ticketek;

public class Funcion {

    Sede sede;
    String fecha;
    double precioBase;

    public Funcion(Sede sede, String fecha, double precioBase){
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
    }

    public void reservarAsiento(Sector sector, int fila, int asiento){

    }

    public void liberarAsiento(Sector sector, int fila, int asiento){

    }

    public boolean estaDisponible(Sector sector, int fila, int asiento){

    }

    public double calcularPrecioFinal(Sector sector, double extraConsumision){


    }

}

package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

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
    HashMap<String,HashMap<Integer, Integer>> asientos;// [SECTOR] [FILA, ASIENTO]
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

    }

    public Entrada venderEntrada(String email, String nombreSede, String nombreEspectaculo, String fecha, String sector, int[] asientos) {
        Entrada entrada = new Entrada(email,nombreSede,nombreEspectaculo,fecha,asientos,sector);

        return entrada;
    }


    public void reservarAsiento(String[] sector, int fila, int asiento) {
        return;
    }

    public void liberarAsiento(String[] sector, int fila, int asiento) {
        return;
    }

    public boolean estaDisponible(String[] sector, int fila, int asiento) {
        return false;
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return 0;
    }
}

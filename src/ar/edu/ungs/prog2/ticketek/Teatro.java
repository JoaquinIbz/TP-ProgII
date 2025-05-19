package ar.edu.ungs.prog2.ticketek;

public class Teatro extends Sede{

    int asientosPorFila;
    String[] sectores;
    int[] capacidad;
    int[] porcentajeAdicional;
    

//    public Teatro(String nombre, String direccion, int capacidadMax, double precioBase) {
//        super(nombre, direccion, capacidadMax);
//        this.precioBase = precioBase;
//    }
    public Teatro(String nombre, String direccion, int capacidadMax, int asientosPorFila, String[] sectores,
    		int[] capacidad, int[] porcentajeAdicional) {
    	super(nombre, direccion, capacidadMax);
        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.capacidad = capacidad;
        this.porcentajeAdicional = porcentajeAdicional;
    	
    	
    }
}

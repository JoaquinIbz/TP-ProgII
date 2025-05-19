package ar.edu.ungs.prog2.ticketek;

public class Teatro extends Sede{

    private double precioBase;
    

//    public Teatro(String nombre, String direccion, int capacidadMax, double precioBase) {
//        super(nombre, direccion, capacidadMax);
//        this.precioBase = precioBase;
//    }
    public Teatro(String nombre, String direccion, int capacidadMax, int asientosPorFila, String[] sectores,
    		int[] capacidad, int[] porcentajeAdicional) {
    	super(nombre, direccion, capacidadMax);
    	
    	
    }
}

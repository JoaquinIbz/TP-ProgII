package ar.edu.ungs.prog2.ticketek;

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

    String[] sectores; // "P-VIP", "P-COMUN", "P-BAJA", "P-ALTA"
    int[] capacidadesPorSector; //
    int[] porcentajeAdicionalPorSector;
    int cantPuestos;
    double consumicion;


    public MiniEstadio(String nombre, String direccion, int capacidadMax, int asientosPorFila, int cantPuestos,
    		double consumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMax);
        this.cantPuestos = cantPuestos;
        this.consumicion = consumicion;
        this.sectores = sectores;
        this.capacidadesPorSector = capacidad;
        this.porcentajeAdicionalPorSector = porcentajeAdicional;
    }
}

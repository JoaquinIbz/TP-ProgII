package ar.edu.ungs.prog2.ticketek;

public class Entrada implements IEntrada {

    String nombreSede;
    String email;
    private String fecha;
    private int fila;
    private int asiento;
    private String sector;
    private String nombreEspectaculo;
    private Espectaculo espectaculo;

    // entrada de estadio.
    public Entrada(String email, String nombreSede, String nombreEspectaculo, String fecha){
        this.nombreSede = nombreSede;
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
    }

    // entrada de teatro y miniestadio.
    public Entrada(String email, String nombreSede, String nombreEspectaculo, String fecha, int[] asientos, String sector){
        this.nombreSede = nombreSede;
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
        this.fila = fila;
        this.asiento = asiento;
        this.sector = sector;
    }


    public double precio() {
        Funcion funcion = this.espectaculo.obtenerFuncion(this.fecha);
        return funcion.calcularPrecio();
    }

    /**
     * Si la entrada es para estadio, la ubicacion será "CAMPO".
     * sino, será "{SECTOR} f:{NRO FILA} a:{NRO ASIENTO}"
     *
     * Por ejemplo:
     *  - CAMPO
     *  - Platea Común f:3 a:31
     * @return
     */
    public String ubicacion() {
        if(sector == null) {
        	return "CAMPO";
        }
        else {
        	return sector + "f: " + fila + " a: " + asiento;
        }
    }

    public String toString(){
        return "Entrada{ " + "espectaculo= " + nombreEspectaculo + "/" + ", sede= " + nombreSede + "/" + ", fecha= " +
    fecha + "/" + ", email= " + email + "/" + ", ubicacion= " + ubicacion() + "/" + "}";
        		}

    public Espectaculo getEspectaculo(){
        return this.espectaculo;
    }
    public void setEspectaculo(Espectaculo espectaculo){
        this.espectaculo = espectaculo;
    }

	public String getFecha() {
		return fecha;
	}
    
}

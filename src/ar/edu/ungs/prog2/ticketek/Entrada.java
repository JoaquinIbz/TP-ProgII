package ar.edu.ungs.prog2.ticketek;

public class Entrada implements IEntrada {

    private String codigoEntrada;
    String nombreSede;
    private String fecha;
    private int fila;
    private int asiento;
    private String nombreEspectaculo;
    private Espectaculo espectaculo;


    public Entrada(String nombreSede, String nombreEspectaculo, String fecha){
        this.nombreSede = nombreSede;
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
    }

    public double precio() {
        Funcion funcion = this.espectaculo.obtenerFuncion(fecha);
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
        return "";
    }

    public String toString(){
        return "";
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

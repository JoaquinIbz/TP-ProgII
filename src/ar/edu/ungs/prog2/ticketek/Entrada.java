package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.util.LinkedList;



public class Entrada implements IEntrada {

    private String nombreSede;
    private String email;
    private String fecha;
   
    private int numeroButaca;
    
    private String sector;
    private String nombreEspectaculo;
    private Espectaculo espectaculo;
    private int codigo;
    private double precio;
    private static int siguienteCodigo = 4000;

    // entrada de estadio.
    public Entrada(String email, String nombreSede, Espectaculo espectaculo, String fecha, String sector){
    	if(email == null || nombreSede == null || espectaculo == null || fecha == null || sector.equals("")) {
    		throw new RuntimeException("Los datos son invalidos");
    	}
        this.nombreSede = nombreSede;
        this.espectaculo = espectaculo;
        this.fecha = fecha;
        this.email = email;
        this.numeroButaca = 0;
        this.codigo = siguienteCodigo;
        siguienteCodigo += 100;
        this.precio = precio();
        this.sector = sector;
        this.nombreEspectaculo = this.espectaculo.getNombre();
    }

    // entrada de teatro y miniestadio.
    public Entrada(String email, String nombreSede, Espectaculo espectaculo, String fecha, int numeroButaca, String sector){
        this.nombreSede = nombreSede;
        this.espectaculo = espectaculo;
        this.fecha = fecha;
        this.sector = sector;
        this.email = email;
        this.numeroButaca = numeroButaca;
        this.codigo = siguienteCodigo;
        siguienteCodigo += 100;
        this.precio = precio();
        this.nombreEspectaculo = this.espectaculo.getNombre();
    }


    public double precio() {
        double precio = 0;
        Funcion funcion = this.espectaculo.obtenerFuncion(this.fecha);
        precio = funcion.getPrecioBase();
        return precio;
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
    	if (this.sector.equals("CAMPO")) {
            return "CAMPO";
        }
        return this.sector + " asiento:" + numeroButaca;
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

    public String getSector(){
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
/*
    public void setButaca(Butaca butaca){
        this.butaca = butaca;
    }
    public Butaca getButaca(){
        return this.butaca;
    }
*/

	public int getCodigo() {
		return codigo;
	}

	public String getEmail() {
		return email;
	}
/*    public String toString(){
        String estado = "";
        Fecha fecha = new Fecha(this.fecha);
        if(fecha.esPasada()) {
            estado = " P - ";
        }
        StringBuilder sb = new StringBuilder();
        // - 7196 - Coldplay en vivo - 30/04/2025 P - La bombonera - CAMPO
        //         - Coldplay en vivo - 25/07/25 - La bombonera - CAMPO
        // " P - "
        sb.append(this.codigo).append(" - ").append(this.nombreEspectaculo).append(" - ").append(fecha).append(estado).append(" - ").append(this.nombreSede).append(" - ").append(this.sector);
        if(!this.sector.equals("CAMPO")){
            sb.append(" f:"+this.butaca.getFila()+" a:"+this.butaca.getAsiento());
        }
        return sb.toString();
    }*/
	public String toString(){
        String estado = "";
        Fecha fecha = new Fecha(this.fecha);
        if(fecha.esPasada()) {
            estado = " P - ";
        }
        StringBuilder sb = new StringBuilder();
        // - 7196 - Coldplay en vivo - 30/04/2025 P - La bombonera - CAMPO
        //         - Coldplay en vivo - 25/07/25 - La bombonera - CAMPO
        // " P - "
        sb.append(this.codigo).append(" - ").append(this.nombreEspectaculo).append(" - ").append(fecha).append(estado).append(" - ").append(this.nombreSede).append(" - ").append(this.ubicacion());
//        if(!this.sector.equals("CAMPO")){
//            sb.append(" Numero de butaca: "+ numeroButaca);
//        }
        return sb.toString();
    }

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public int getNumeroButaca() {
		return numeroButaca;
	}

	public String getNombreSede() {
		return nombreSede;
	}

	
	

}

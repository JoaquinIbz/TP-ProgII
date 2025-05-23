package ar.edu.ungs.prog2.ticketek;

import java.util.LinkedList;

public class Entrada implements IEntrada {

    String nombreSede;
    String email;
    private String fecha;
    private Butaca butaca;
    private String sector;
    private String nombreEspectaculo;
    private Espectaculo espectaculo;

    // entrada de estadio.
    public Entrada(String email, String nombreSede, String nombreEspectaculo, String fecha){
        this.nombreSede = nombreSede;
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
        this.email = email;
    }

    // entrada de teatro y miniestadio.
    public Entrada(String email, String nombreSede, String nombreEspectaculo, String fecha, int asientos, String sector){
        this.nombreSede = nombreSede;
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
        this.sector = sector;
        this.email = email;
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
        if(this.sector.equals("CAMPO")) {
        	return this.sector;
        }else {
        	//* Si la entrada es para estadio, la ubicacion será "CAMPO".
            //* sino, será "{SECTOR} f:{NRO FILA} a:{NRO ASIENTO}"
            StringBuilder sb = new StringBuilder();
            sb.append("{"+this.sector+"} ");
            sb.append("f:{");sb.append(butaca.getFila()+"} ");
            sb.append("a:{");sb.append(butaca.getAsiento()+"} ");
            return sb.toString();
        }
    }

    public String toString(){
        return "Entrada{ " + "espectaculo= " + this.nombreEspectaculo + "/" + ", sede= " + this.nombreSede + "/" + ", fecha= " +
                this.fecha + "/" + ", email= " + this.email + "/" + ", ubicacion= " + ubicacion() + "/" + "}";
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
    public void setEmail(String email){
        this.email = email;
    }

    public void setButaca(Butaca butaca){
        this.butaca = butaca;
    }
    public Butaca getButaca(){
        return this.butaca;
    }
}

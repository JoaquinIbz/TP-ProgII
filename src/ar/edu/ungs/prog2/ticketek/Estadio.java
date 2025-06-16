package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Estadio extends Sede {

	private int capacidadActual;

    public Estadio(String nombre, String direccion, int capacidadMax) {
        super(nombre, direccion, capacidadMax);
        this.capacidadActual = this.capacidadMax;
        this.entradasVendidas = new HashMap<>();
    }

/*    public Entrada venderEntrada(String email, String nombreSede, Espectaculo espectaculo, String fecha, String sector, int asiento){
        Entrada entrada = new Entrada(email,nombreSede, espectaculo, fecha, sector);
        entrada.setButaca(new Butaca(0,0));
        if(estaDisponible()){
            if(!this.entradasVendidas.containsKey(fecha)){
                HashMap<Integer,Entrada> entradas = new HashMap<>();
                entradas.put(entrada.getCodigo(),entrada);
                this.entradasVendidas.put(fecha,entradas);
                this.capacidadActual --;
                return entrada;
            }else{
                HashMap<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
                entradas.put(entrada.getCodigo(),entrada);
                this.entradasVendidas.put(fecha,entradas);
                this.capacidadActual --;
                return entrada;
            }
        }else{
            throw new RuntimeException("No hay mas espacio para vender.");
        }
    }*/
    @Override
    public Entrada venderEntrada(String email, Espectaculo espectaculo, String fecha, String sector) {
    	if (!estaDisponible()) {
            throw new RuntimeException("No hay más lugar disponible.");
        }

        Entrada entrada = new Entrada(email, this.nombre, espectaculo, fecha, sector);
        entradasVendidas.putIfAbsent(fecha, new HashMap<>());
        entradasVendidas.get(fecha).put(entrada.getCodigo(), entrada);
        capacidadActual--;
        
        //-----------------------------------------------------------------
        double precio = entrada.precio();
  	  if(recaudacionPorEspectaculo.containsKey(espectaculo.getNombre())) {
  		  double precioActual = recaudacionPorEspectaculo.get(espectaculo.getNombre());
  		  recaudacionPorEspectaculo.put(espectaculo.getNombre(), precioActual + precio);
  	  }else {
  		  recaudacionPorEspectaculo.put(espectaculo.getNombre(), precio);
  	  }
        //-----------------------------------------------------------------
        return entrada;
    }
    @Override
    public Entrada venderEntrada(String email, Espectaculo espectaculo, String fecha, String sector, int numeroButaca) {
    	throw new RuntimeException("El estadio no lleva asiento");
    }


    private boolean estaDisponible() {
    	return capacidadActual > 0;
    }

	public int cantidadDeEntradasVendidas(){
        int cantidad = 0;
        for(Map.Entry<String,Map<Integer,Entrada>> entry : this.entradasVendidas.entrySet()){
            Map<Integer,Entrada> entradas = entry.getValue();
            for(Entrada e : entradas.values()){
                cantidad++;
            }
        }
        return cantidad;
    }


    public double calcularPrecio(String fecha,String sector,double precioBase){
        return precioBase;
    }


    @Override
    public double recaudacion(String fecha) {
        double recaudacion = 0;
        Map<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
        if(entradas != null){
            for(Entrada entrada : entradas.values()){
                recaudacion += entrada.precio();
            }
        }
        return recaudacion;
    }

    @Override
    public String toString(){
        return "Estadio: "+this.nombre+", Direccion: "+this.direccion;
    }
    @Override
    public String entradaVendidaPorFecha(String fecha) {
        // - (24/07/2025) El Monumental - 200/500
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre+" - ");
        int vendidas = 0;
        Map<Integer,Entrada> entradas = this.entradasVendidas.get(fecha);
        if(entradas != null){
            vendidas = entradas.size();
            sb.append(vendidas+"/"+this.capacidadMax+"\n");
            return sb.toString();
        }else{
            sb.append(vendidas+"/"+this.capacidadMax+"\n");
            return sb.toString();
        }
    }

    @Override
    public void anularEntrada(String sector, int fila, int numeroAsiento) {
    	this.capacidadActual++;
    }


/*    @Override
    public double recaudacionTotalPorSede(String nombreEspectaculo,String nombreSede) {
        double total = 0;
        for(Map.Entry<String,Map<Integer,Entrada>> entry : this.entradasVendidas.entrySet()) {
            Map<Integer,Entrada> entradas = entry.getValue();
            for(Entrada entrada : entradas.values()){
                if(entrada.getNombreEspectaculo().equals(nombreEspectaculo) && entrada.getNombreSede().equals(nombreSede)){
                    total += entrada.precio();
                }

            }
        }
        return total;
    }*/
    @Override
    public double recaudacionTotalPorSede(String nombreEspectaculo, String nombreSede) {
    	return recaudacionPorEspectaculo.getOrDefault(nombreEspectaculo, 0.0);//Si existe un espectaculo con ese nombre, devuelve el valor de ese espectaculo
		  																	  //Si no existe, devuelve 0.0
    }
    
    @Override
    public boolean tieneButacas() {
    	return false;
    }
}

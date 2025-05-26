package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    //private HashMap<String, LinkedList<Entrada>> entradasCompradas; // fecha, entrada
    private HashMap<String, HashMap<Integer, Entrada>> entradasCompradas; //clave = fecha, valor= clave=codigo valor= Entrada

    public Usuario(String nombre, String apellido, String email, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        //this.entradasCompradas = new HashMap<>();
        this.entradasCompradas = new HashMap<>();
    }

    public void comprarEntrada(Entrada entrada, String fecha){
     // Guardar en el mapa de códigos (mapaDeEntradas)
        if (!this.entradasCompradas.containsKey(fecha)) {
            this.entradasCompradas.put(fecha, new HashMap<>());
        }
        HashMap<Integer, Entrada> entradas = this.entradasCompradas.get(fecha);
        entradas.put(entrada.getCodigo(), entrada); // O(1)
    }

    public LinkedList<Entrada> listarEntradasFuturas(){
    	LinkedList<Entrada> entradasFuturas = new LinkedList<>();
        for(Map.Entry<String,HashMap<Integer,Entrada>> entry : this.entradasCompradas.entrySet()){
            Fecha fecha = new Fecha(entry.getKey());
            if(fecha.esFutura()){
                HashMap<Integer,Entrada> entradas = entry.getValue();
                for(Entrada e : entradas.values()){

                    entradasFuturas.add(e);
                }
            }
        }
        return entradasFuturas;
    }

    public LinkedList<Entrada> listarEntradasPasadas(){
        LinkedList<Entrada> entradasPasadas = new LinkedList<>();
        for(Map.Entry<String,HashMap<Integer,Entrada>> entry : this.entradasCompradas.entrySet()){
            Fecha fecha = new Fecha(entry.getKey());
            if(fecha.esPasada()){
                HashMap<Integer,Entrada> entradas = entry.getValue();
                for(Entrada e : entradas.values()){
                    entradasPasadas.add(e);
                }
            }
        }
        return entradasPasadas;
    }

    public LinkedList<Entrada> listarTotalEntradas(){
        LinkedList<Entrada> total = new LinkedList<>();
        total.addAll(listarEntradasPasadas());
        total.addAll(listarEntradasFuturas());
        return total;
    }
    
    public boolean anularEntrada(Entrada entrada, Sede sede) {
    	String fecha = entrada.getFecha();
    	int codigo = entrada.getCodigo();
    	HashMap<Integer, Entrada> mapa = this.entradasCompradas.get(fecha);
        if(mapa == null || !mapa.containsKey(codigo)) {
        	throw new RuntimeException("Entrada no encontrada");
        }
        mapa.remove(codigo);
        sede.anularEntrada(entrada.getSector(), entrada.getButaca().getFila(), entrada.getButaca().getAsiento());
        return true;
    }
    
    public String toString() {
    	return "Nombre: "+this.nombre +", "+ "email: (" + this.email + ")";
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getApellido(){
        return this.apellido;
    }
    public String getEmail(){
        return this.email;
    }
    public String getContrasenia(){
        return this.contrasenia;
    }




}

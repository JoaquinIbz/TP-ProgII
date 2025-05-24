package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
//    private HashMap<String, LinkedList<Entrada>> entradasCompradas; // fecha, entrada
    private HashMap<String, HashMap<String, Entrada>> entradasCompradas; //clave = fecha, valor= clave=codigo valor= Entrada

    public Usuario(String nombre, String apellido, String email, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.entradasCompradas = new HashMap<>();
    }

    public void comprarEntrada(Entrada entrada, String fecha){
        if(!this.entradasCompradas.containsKey(fecha)){
            LinkedList<Entrada> entradas = new LinkedList<>();
            this.entradasCompradas.put(fecha, entradas);
        }
        LinkedList<Entrada> entradas = this.entradasCompradas.get(fecha);
        entradas.add(entrada);
    }

    public LinkedList<Entrada> listarEntradasFuturas(){
    	LinkedList<Entrada> listaEntradas = new LinkedList<>();
        for(String f : entradasCompradas.keySet()){
            Fecha fecha = new Fecha(f);
        	if(fecha.esFutura()) {
        		listaEntradas.addAll(entradasCompradas.get(f));
        	}
        }
        return listaEntradas;
    }

    public LinkedList<Entrada> listarEntradasPasadas(){
        LinkedList<Entrada> listaEntradas = new LinkedList<>();
        for(String f : entradasCompradas.keySet()) {
        	Fecha fecha = new Fecha(f);
        	if(fecha.esPasada()) {
        		listaEntradas.addAll(entradasCompradas.get(f));
        	}
        }
        return listaEntradas;
    }

    public LinkedList<Entrada> listarTotalEntradas(){
        LinkedList<Entrada> total = new LinkedList<>();
        for(LinkedList<Entrada> entrada : entradasCompradas.values()) {
        	total.addAll(entrada);
        }
        return total;
    }
    
    public boolean anularEntrada(Entrada entrada) {
    	String fecha = entrada.getFecha();
    	HashMap<String, Entrada> mapa = entradasCompradas.get(fecha);
        
    	return true;
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

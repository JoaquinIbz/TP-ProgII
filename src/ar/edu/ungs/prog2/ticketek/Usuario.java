package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private HashMap<String, LinkedList<Entrada>> entradasCompradas; // fecha, entrada

    public Usuario(String nombre, String apellido, String email, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.entradasCompradas = new HashMap<>();
    }

    public void comprarEntrada(Entrada entrada, String fecha){

    }

    public LinkedList<Entrada> listarEntradasFuturas(){
    	LinkedList<Entrada> listaEntradas = new LinkedList<>();
        LocalDate fechaActual = LocalDate.now();
        for(String fecha : entradasCompradas.keySet()) {
        	LocalDate fechaEntrada = LocalDate.parse(fecha);
        	if(fechaEntrada.isAfter(fechaActual)) {
        		listaEntradas.addAll(entradasCompradas.get(fecha));
        	}
        }
        return listaEntradas;
    }

//    public LinkedList<Entrada> listarEntradasPasadas(String fecha){
//        
//    }

    public LinkedList<Entrada> listarTotalEntradas(){
        LinkedList<Entrada> total = new LinkedList<>();
        for(LinkedList<Entrada> entrada : entradasCompradas.values()) {
        	total.addAll(entrada);
        }
        return total;
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

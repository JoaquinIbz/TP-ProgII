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
    private HashMap<String, HashMap<String, Entrada>> mapaDeEntradas; //clave = fecha, valor= clave=codigo valor= Entrada

    public Usuario(String nombre, String apellido, String email, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.entradasCompradas = new HashMap<>();
        this.mapaDeEntradas = new HashMap<>();
    }

    public void comprarEntrada(Entrada entrada, String fecha){
        if(!this.entradasCompradas.containsKey(fecha)){
            LinkedList<Entrada> entradas = new LinkedList<>();
            this.entradasCompradas.put(fecha, entradas);
        }
        LinkedList<Entrada> entradas = this.entradasCompradas.get(fecha);
        entradas.add(entrada);
        
     // Guardar en el mapa de códigos (mapaDeEntradas)
        if (!this.mapaDeEntradas.containsKey(fecha)) {
            this.mapaDeEntradas.put(fecha, new HashMap<>());
        }
        
        HashMap<String, Entrada> mapaCodigos = this.mapaDeEntradas.get(fecha);
        mapaCodigos.put(entrada.getCodigo(), entrada); // O(1)
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
    
    public boolean anularEntrada(Entrada entrada, Sede sede) {
    	String fecha = entrada.getFecha();
    	String codigo = entrada.getCodigo();
    	HashMap<String, Entrada> mapa = mapaDeEntradas.get(fecha);
        if(mapa == null || !mapa.containsKey(codigo)) {
        	throw new RuntimeException("Entrada no encontrada");
        }
        mapa.remove(codigo);
        
     // Liberar asiento (O(1))
        
        
       // sede.liberarAsiento(entrada.getSector(), entrada.getButaca().getFila(), entrada.getButaca().getAsiento());
        if(sede instanceof Estadio) {
        	((Estadio) sede).anularEntrada();
        }
        if(sede instanceof MiniEstadio) {
        	((MiniEstadio) sede).liberarAsiento(entrada.getSector(), entrada.getButaca().getFila(), entrada.getButaca().getAsiento());
        }
        if(sede instanceof Teatro) {
        	((Teatro) sede).liberarAsiento(entrada.getSector(), entrada.getButaca().getFila(), entrada.getButaca().getAsiento());
        }
        	

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

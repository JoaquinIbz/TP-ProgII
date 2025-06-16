package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private Map<String, Map<Integer, Entrada>> entradasCompradas; //clave = fecha, valor= clave=codigo valor= Entrada

    public Usuario(String nombre, String apellido, String email, String contrasenia){
    	if(email == null || nombre == null || apellido == null || contrasenia == null) {
    		throw new RuntimeException("los datos son invalidos");
    	}
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.entradasCompradas = new HashMap<>();
    }

    public void comprarEntrada(Entrada entrada, String fecha){
     // Guardar en el mapa de códigos (mapaDeEntradas)
        if (!this.entradasCompradas.containsKey(fecha)) {
            this.entradasCompradas.put(fecha, new HashMap<>());
        }
        Map<Integer, Entrada> entradas = this.entradasCompradas.get(fecha);
        entradas.put(entrada.getCodigo(), entrada); // O(1)
    }

    public List<Entrada> listarEntradasFuturas(){
    	List<Entrada> entradasFuturas = new LinkedList<>();
        for(Map.Entry<String,Map<Integer,Entrada>> entry : this.entradasCompradas.entrySet()){
            Fecha fecha = new Fecha(entry.getKey());
            if(fecha.esFutura()){
                Map<Integer,Entrada> entradas = entry.getValue();
                for(Entrada e : entradas.values()){
                    entradasFuturas.add(e);
                }
            }
        }
        return entradasFuturas;
    }

    public List<Entrada> listarEntradasPasadas(){
        List<Entrada> entradasPasadas = new LinkedList<>();
        for(Map.Entry<String,Map<Integer,Entrada>> entry : this.entradasCompradas.entrySet()){
            Fecha fecha = new Fecha(entry.getKey());
            if(fecha.esPasada()){
                Map<Integer,Entrada> entradas = entry.getValue();
                for(Entrada e : entradas.values()){
                    entradasPasadas.add(e);
                }
            }
        }
        return entradasPasadas;
    }

    public List<Entrada> listarTotalEntradas(){
        List<Entrada> total = new LinkedList<>();
        total.addAll(listarEntradasPasadas());
        total.addAll(listarEntradasFuturas());
        return total;
    }
    

    public boolean anularEntrada(Entrada entrada, Sede sede) {
    	String fecha = entrada.getFecha();
    	int codigo = entrada.getCodigo();
    	Map<Integer, Entrada> mapa = this.entradasCompradas.get(fecha);
    	
    	if(mapa == null || !mapa.containsKey(codigo)) {
    		throw new RuntimeException("Entrada no encontrada");
    	}
    	mapa.remove(codigo);
    	
    	if(sede.tieneButacas()) {
    		//para teatro y miniestadio
    		sede.anularEntrada(entrada.getSector(), 0, entrada.getNumeroButaca());
    	}
    	else {
    		//para estadio sin butacas 
    		sede.anularEntrada("CAMPO", 0, 0);;
    	}
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

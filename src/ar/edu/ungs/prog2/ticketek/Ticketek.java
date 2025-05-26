package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Ticketek implements ITicketek {

    String nombreEmpresa;
    private HashMap<String, Usuario> usuarios;//email, usuario
    //private LinkedList<Sede> sedes;
    private HashMap<String, Sede> sedes;//nombre, sede
    private HashMap<String, Espectaculo> espectaculos;


    // metodos del diagrama
    public Ticketek(){
    	this.usuarios = new HashMap<>();
    	this.sedes = new HashMap<>();
    	this.espectaculos = new HashMap<>();
    }

    // metodos de la interfaz
    public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
    	for(String s : sedes.keySet()) {
    		if(s.equals(nombre)) {
    			throw new RuntimeException("Ya existe una sede con este nombre");
    		}
    	}
    	if(capacidadMaxima <= 0) {
    		throw new RuntimeException("La capacidad debe ser mayor a 0");
    	}
    	Estadio estadio = new Estadio(nombre, direccion, capacidadMaxima);
    	sedes.put(estadio.nombre, estadio);
    	
    }

    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
    	//TEATRO
        for(String s : sedes.keySet()) {
            if(s.equals(nombre)) {
                throw new RuntimeException("Ya existe una sede con este nombre");
            }
        }
    	if(capacidadMaxima <= 0 || asientosPorFila <= 0 || sectores.length == 0 || capacidad.length == 0 || porcentajeAdicional.length == 0) {
    		throw new RuntimeException("Esos datos son invalidos");
    	}
    	Teatro teatro = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional);
    	sedes.put(teatro.nombre, teatro);
    }
    

    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
//   //MiniEstadio
        for(String s : sedes.keySet()) {
            if(s.equals(nombre)) {
                throw new RuntimeException("Ya existe una sede con este nombre");
            }
        }
    	if(capacidadMaxima <= 0 || asientosPorFila <= 0 || sectores.length == 0 || capacidad.length == 0|| porcentajeAdicional.length == 0) {
    		throw new RuntimeException("Por favor, ingrese datos validos");
    	}
    	MiniEstadio miniestadio = new MiniEstadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos, precioConsumicion, sectores, capacidad, porcentajeAdicional);
    	sedes.put(miniestadio.nombre, miniestadio);
    }

    public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
        if(usuarios.containsKey(email)) {
            throw new RuntimeException("este email ya esta registrado");
        }
        if(email == null || nombre == null || apellido == null || contrasenia == null) {
            throw new RuntimeException("los datos son invalidos");
        }
        Usuario usuario = new Usuario(nombre,apellido,email, contrasenia);
        usuarios.put(email, usuario);
    }

    public void registrarEspectaculo(String nombre) {
        if(this.espectaculos.containsKey(nombre)){
            throw new RuntimeException("El nombre del espectaculo ya esta registrado.");
        }
        this.espectaculos.put(nombre,new Espectaculo(nombre));
    }

    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {

        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Sede s = verificarRegistroSede(sede);
        if(espectaculo.verificarDisponibilidad(fecha)){
            Funcion funcion = new Funcion(s,fecha,precioBase);
            espectaculo.agregarFuncion(fecha,funcion);
        }
    }

    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas) {
        // vender entrada estadio
        Usuario usuario = autenticarUsuario(email,contrasenia);
        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Funcion funcion = espectaculo.obtenerFuncion(fecha);
        LinkedList<IEntrada> entradasVendidas = new LinkedList<>();

        for(int i=0 ; i<cantidadEntradas ; i++){
            Entrada entrada = funcion.sede.venderEntrada(email,funcion.sede.nombre,espectaculo,fecha, "CAMPO" , 0);
            usuario.comprarEntrada(entrada,fecha);
            entradasVendidas.add(entrada);
        }
        return entradasVendidas;
    }

    private Sede verificarRegistroSede(String nombre){
        Sede sede = this.sedes.get(nombre);
        if(sede != null){
            return sede;
        }
        throw new RuntimeException("La sede no se encuentra registrada.");
    }

    private Usuario autenticarUsuario(String email,String contrasenia){
        Usuario usuario = this.usuarios.get(email);
        if(usuario == null){
            throw new RuntimeException("El usuario no se encuentra registrado.");
        }
        if(!usuario.getContrasenia().equals(contrasenia)){
            throw new RuntimeException("Contrasenia incorrecta.");
        }
        return usuario;
    }

    private Espectaculo verificarRegistroEspectaculo(String nombreEspectaculo){
        if(this.espectaculos.containsKey(nombreEspectaculo)){
            return this.espectaculos.get(nombreEspectaculo);
        }
        throw new RuntimeException("El espectaculo no se encuentra registrado.");
    }

    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {

        Usuario usuario = autenticarUsuario(email,contrasenia);
        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Funcion funcion = espectaculo.obtenerFuncion(fecha);
        LinkedList<IEntrada> entradasVendidas = new LinkedList<>();

        if(funcion.sede instanceof Teatro){
            Teatro teatro = (Teatro) funcion.sede;
            for(int a : asientos){
                Entrada entrada = teatro.venderEntrada(email,teatro.nombre,espectaculo,fecha,sector,a);
                usuario.comprarEntrada(entrada,fecha);
                entradasVendidas.add(entrada);
            }
        }else if(funcion.sede instanceof MiniEstadio){
            MiniEstadio miniEstadio = (MiniEstadio) funcion.sede;
            for(int a : asientos){
                Entrada entrada = miniEstadio.venderEntrada(email,miniEstadio.nombre,espectaculo,fecha,sector,a);
                usuario.comprarEntrada(entrada,fecha);
                entradasVendidas.add(entrada);
            }
        }else{
            throw new RuntimeException("La sede no es un teatro ni miniEstadio.");
        }
    	return entradasVendidas;
    }

    public String listarFunciones(String nombreEspectaculo) {

        StringBuilder sb = new StringBuilder();
        LinkedList<Funcion> funciones = null;
        for(Espectaculo espectaculo : this.espectaculos.values()){
            if(espectaculo.nombre.equals(nombreEspectaculo)){
                funciones = espectaculo.obtenerTodasLasFunciones();
                break;
            }
        }
        for(Funcion funcion : funciones){
        	sb.append(funcion.toString());
        }
        return sb.toString();
    }

    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        List<IEntrada> entradas = new LinkedList<>();
        for(Usuario usuario : usuarios.values()) {
        	for(Entrada entrada : usuario.listarTotalEntradas()) {
        		if(entrada.getEspectaculo().nombre.equals(nombreEspectaculo)) {
        			entradas.add(entrada);
        		}
        	}
        }
        return entradas;
    }

    public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
        Usuario usuario = autenticarUsuario(email, contrasenia);
        if(usuario == null) {
        	throw new RuntimeException("El usuario no existe");
        }
        List<IEntrada> entradas = new LinkedList<>();
        entradas.addAll(usuario.listarEntradasFuturas());
        return entradas;
    }

    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia){
    	Usuario usuario = autenticarUsuario(email, contrasenia);
    	LinkedList entradas = new LinkedList<>();
        entradas = usuario.listarTotalEntradas();
    	return entradas;
    }

    public boolean anularEntrada(IEntrada entrada, String contrasenia) {//O(1)
    	Entrada e = (Entrada) entrada;
    	Usuario usuario = autenticarUsuario(e.getEmail(), contrasenia);
    	if(entrada == null || contrasenia == null) {
    		throw new RuntimeException("Los datos son invalidos");
    	}
    	Fecha fechaEntrada = new Fecha(e.getFecha());
    	if(fechaEntrada.esPasada()) {
    		return false;
    	}
    	Sede sede = sedes.get(e.nombreSede);
    	if(sede == null) {
    		throw new RuntimeException("La sede no esta registrada.");
    	}
    	return usuario.anularEntrada(e, sede);

    }

    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
    	if(entrada == null || fecha == null || sector == null || contrasenia == null) {
    		throw new RuntimeException("dato invalido");
    	}
    	Entrada e = (Entrada) entrada;
    	Usuario usuario = autenticarUsuario(e.getEmail(), contrasenia);
        Fecha fechaEntrada = new Fecha(fecha);
        Sede sede = sedes.get(e.nombreSede);
        Espectaculo espectaculo = verificarRegistroEspectaculo(e.getNombreEspectaculo());
    	if(usuario == null ) {
            throw new RuntimeException("la contrasenia es invalida");
        }
    	if(fechaEntrada.esPasada()) {
            throw new RuntimeException("La fecha de la entrada ya paso");
        }
    	Entrada nueva = sede.venderEntrada(e.getEmail(), sede.nombre, espectaculo, fecha, sector, asiento);
        anularEntrada(entrada, contrasenia);
        usuario.comprarEntrada(nueva,fecha);
    	return nueva;
    }

    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
        if(entrada == null || fecha == null || contrasenia == null) {
            throw new RuntimeException("dato invalido");
        }
        Entrada e = (Entrada) entrada;
        Usuario usuario = autenticarUsuario(e.getEmail(), contrasenia);
        Fecha fechaEntrada = new Fecha(fecha);
        Sede sede = sedes.get(e.nombreSede);
        Espectaculo espectaculo = verificarRegistroEspectaculo(e.getNombreEspectaculo());
        if(fechaEntrada.esPasada()) {
            throw new RuntimeException("La fecha de la entrada ya paso");
        }
        Entrada nueva = sede.venderEntrada(e.getEmail(), sede.nombre, espectaculo, fecha, "CAMPO",0);
        anularEntrada(entrada, contrasenia);
        usuario.comprarEntrada(nueva,fecha);
        return nueva;
    }

    // estadio
    public double costoEntrada(String nombreEspectaculo, String fecha) {
        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Funcion funcion = espectaculo.obtenerFuncion(fecha);
        return funcion.calcularPrecioEstadio(fecha);
    }

    // teatro y mini estadio
    public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Funcion funcion = espectaculo.obtenerFuncion(fecha);
        return funcion.calcularPrecioTeatroMiniestadio(fecha,sector);
    }

    public double totalRecaudado(String nombreEspectaculo) {
        Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        return espectaculo.calcularTotalRecaudado();
    	
    }

    public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
        double total = 0;
    	Espectaculo espectaculo = verificarRegistroEspectaculo(nombreEspectaculo);
        Sede sede = espectaculo.obtenerSede(nombreSede);
        total = sede.recaudacionTotalPorSede(nombreEspectaculo,nombreSede);
        return total;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(!this.usuarios.isEmpty() || !this.sedes.isEmpty() || !this.espectaculos.isEmpty()){
            sb.append("Usuarios registrados:\n");
            for(Usuario u : usuarios.values()) {
                sb.append(" - ").append(u.toString()).append("\n");
            }

            sb.append("\nSedes registradas:\n");
            for(Sede s : sedes.values()) {
                sb.append(" - ").append(s.toString()).append("\n");
            }

            sb.append("\nEspectaculos:\n");
            for(Espectaculo e : espectaculos.values()) {
                int cantFunciones = e.getFunciones().size();
                double recaudacion = e.calcularTotalRecaudado();
                sb.append(" - ").append(e.toString()).append(": ").append(cantFunciones).append(" funciones").append("\n");
            }
        }else{
            sb.append("==========================================");
        }
        return sb.toString();
    }


}

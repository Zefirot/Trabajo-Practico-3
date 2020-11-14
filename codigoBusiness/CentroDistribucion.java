package codigoBusiness;

import java.util.HashMap;
import java.util.Map;

public class CentroDistribucion {

	private double latitud,longitud;
	private Map<Cliente, Integer> clientes;
	
	public CentroDistribucion( double latitud, double longitud ) {
		this.latitud=latitud;
		this.longitud=longitud;
		
		this.clientes = new HashMap<Cliente, Integer>();
	}
	
	public void agregarCliente(Cliente cliente, int distancia) {
		clientes.put(cliente, distancia);
	}
	
	public void eliminarCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	
	public boolean contiene(Cliente cliente) {
		return clientes.containsKey(cliente);
	}
	
	public int cantidadDeClientes() {
		return clientes.size();
	}
	
	public Map<Cliente,Integer> clientes(){
		return clientes;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}

}

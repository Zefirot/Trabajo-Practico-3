package codigoBusiness;

import java.util.HashSet;
import java.util.Set;

public class CentroDistribucion {

	private double latitud,longitud;
	private Set<Cliente> clientes;
	private double costo;
	
	public CentroDistribucion( double latitud, double longitud ) {
		this.latitud=latitud;
		this.longitud=longitud;
		
		this.clientes = new HashSet<Cliente>();
	}
	
	//Cada vez que se agrega un cliente se le suma al costo total la distancia entre ese cliente y el centro
	public void agregarCliente(Cliente cliente, int distancia) {
		clientes.add(cliente);
		costo += distancia; 
	}
	
	public boolean contiene(Cliente cliente) {
		return clientes.contains(cliente);
	}
	
	public int cantidadDeClientes() {
		return clientes.size();
	}
	
	public Set<Cliente> clientes(){
		return clientes;
	}
	
	public double costoTotal() {
		return costo;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}

}

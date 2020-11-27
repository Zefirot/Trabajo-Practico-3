package codigoBusiness;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProcesarDatos {

	private Set<Cliente> clientes;
	private Set<CentroDistribucion> centros;
	private int solucionesPosibles;
	
	private ArrayList<CentroDistribucion> soluciones;
	
	public ProcesarDatos(Set<Cliente> clientes, Set<CentroDistribucion> centros, int k) {
		
		this.clientes = clientes;
		this.centros = centros;
		this.solucionesPosibles = k;
		
		this.soluciones = new ArrayList<CentroDistribucion>();
		
		procesar();
		
	}
	
	private void procesar() {
		int solucion = 0;
		
		while( solucion<solucionesPosibles ) {  //K veces 
			
			construirSolucion();  //Se asigna todos los clientes al centro mas cercano - O(n^2)
			
			CentroDistribucion centroPosible = centroConMayoresClientes();  //Se busca el centro con mayor clientes - O(n)
		
			//Se elimina ese centro como futura solucion y tambien se eliman sus clientes
			centros.remove(centroPosible);
			clientes.removeAll(centroPosible.clientes());  //O(n)

			//Se toma como un posible centro el centro encontrado con mayores clientes asignados
			soluciones.add(centroPosible);

			solucion++;
			
		}
		
		//Si despues de llegar a las k soluciones existen clientes, entonces se les asigna un centro cercano que ya es solucion
		if( clientes.size()!=0 ) {
			
			asignarClientesSueltos();
			
		}
	}
	
	//Asigna cada cliente al centro de distribucion mas cercano
	private void construirSolucion() {
		
		for(Cliente cliente : clientes) {
			
			CentroDistribucion centro = centroConMenorDistancia(cliente);
			
			int distancia = Haversine.distancia(cliente.getLatitud(), cliente.getLongitud(), centro.getLatitud() , centro.getLongitud());
			
			centro.agregarCliente(cliente, distancia);
			
		}
		
		
	}
	
	private CentroDistribucion centroConMenorDistancia(Cliente cliente) {
		CentroDistribucion centroConMenorDistancia = null;
		int distanciaMenor = Integer.MAX_VALUE;
		
		for(CentroDistribucion centro : centros){
			
			int distanciaActual = Haversine.distancia(cliente.getLatitud(), cliente.getLongitud(), centro.getLatitud() , centro.getLongitud());
			
			if(distanciaMenor>distanciaActual) {
				
				centroConMenorDistancia = centro;
				distanciaMenor = distanciaActual;
				
			}
			
		}
		
		return centroConMenorDistancia;
	}
	
	
	
	private CentroDistribucion centroConMayoresClientes() {
		int clientes = 0;
		CentroDistribucion centroConMayorClientes = null; 	
		
		for(CentroDistribucion centro : centros) {
			
			if( clientes<centro.cantidadDeClientes() ) {
				
				clientes = centro.cantidadDeClientes();
				centroConMayorClientes = centro;
				
			}
			
		}
		
		return centroConMayorClientes;
		
	}
	
	
	private void asignarClientesSueltos() {
		
		for(Cliente cliente : clientes) {
			asignarACentroConMenorDistancia(cliente);
		}
		
	}
	
	private void asignarACentroConMenorDistancia(Cliente cliente) {

		int distanciaMenor = Integer.MAX_VALUE;

		CentroDistribucion centroMasCercano = soluciones.get(0);
		
		for(CentroDistribucion centro : soluciones) {

			int distanciaActual = Haversine.distancia(cliente.getLatitud(), cliente.getLongitud(), centro.getLatitud(), centro.getLongitud());
			
			if( distanciaMenor>distanciaActual ) {
				
				distanciaMenor = distanciaActual;
				
				centroMasCercano = centro;
				
			}
		}
		
		centroMasCercano.agregarCliente(cliente, distanciaMenor);
	
	}
	
	
	public ArrayList<CentroDistribucion> centrosPosibles(){
		return soluciones;
	}
	
	
}

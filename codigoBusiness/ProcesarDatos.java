package codigoBusiness;

import java.util.HashSet;
import java.util.Set;

public class ProcesarDatos {

	private Set<Cliente> clientes;
	private Set<CentroDistribucion> centros;
	private int solucionesPosibles;
	
	private Set<CentroDistribucion> soluciones;
	
	public ProcesarDatos(Set<Cliente> clientes, Set<CentroDistribucion> centros, int k) {
		
		this.clientes = clientes;
		this.centros = centros;
		this.solucionesPosibles = k;
		
		this.soluciones = new HashSet<CentroDistribucion>();
	}
	
	private void procesar() {
		int solucion = 0;
		
		while( solucion<=solucionesPosibles ) {
			
			
			
			
			
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
			centroConMenorDistancia = centro;
			
			if(distanciaMenor>=distanciaActual) {
				
				centroConMenorDistancia = centro;
				distanciaMenor = distanciaActual;
				
			}
			
		}
		
		
		return centroConMenorDistancia;
		
		
	}
	
	
}

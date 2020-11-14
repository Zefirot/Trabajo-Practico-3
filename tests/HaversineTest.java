package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import codigoBusiness.Cliente;
import codigoBusiness.Haversine;

public class HaversineTest {

	
	@Test
	public void puntosCercanosTest() {
		Cliente c1 = new Cliente( -34.534126, -58.547588);
		Cliente c2 = new Cliente( -34.523468, -58.552135 );
		
		int distanciaActual = Haversine.distancia(c1.getLatitud(), c1.getLongitud(), c2.getLatitud(), c2.getLongitud());
		
		assertTrue( 1255<=distanciaActual && distanciaActual<=1260 ); //Se compara la distancia obtenida con una aproximacion de 5.
	}
	
	@Test
	public void puntosAlejadosTest() {
		Cliente c1 = new Cliente(-34.649021, -58.579112);
		Cliente c2 = new Cliente(-34.431971, -59.240153);
		
		int distanciaActual = Haversine.distancia(c1.getLatitud(), c1.getLongitud(), c2.getLatitud(), c2.getLongitud());
		
		assertTrue( 65150<=distanciaActual && distanciaActual<=65200 ); //Proximidad de 50 metros
		
	}
	
	@Test
	public void puntosMuyAlejadosTest() {
		Cliente c1 = new Cliente(-36.763064, -62.800085);
		Cliente c2 = new Cliente(37.920336, -89.182414);
		
		int distanciaActual = Haversine.distancia(c1.getLatitud(), c1.getLongitud(), c2.getLatitud(), c2.getLongitud());
		
		assertTrue( 8735000<=distanciaActual && distanciaActual<=8736000 ); //Proximidad de 1000 metros

	}
	
	

}

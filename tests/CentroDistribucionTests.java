package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigoBusiness.CentroDistribucion;
import codigoBusiness.Cliente;

public class CentroDistribucionTests {

	private CentroDistribucion centro;
	private Cliente c1, c2, c3, c4;
	
	@Before
	public void iniciar() {
		centro = new CentroDistribucion(-56.57498,68.57486);
		
		c1 = new Cliente(6,8);
		c2 = new Cliente(9,10);
		c3 = new Cliente(648,5498);
		c4 = new Cliente(6648,3488);
		
		centro.agregarCliente(c1, 51648);
		centro.agregarCliente(c2, 48498);
		centro.agregarCliente(c3, 548);
		centro.agregarCliente(c4, 484421);
	}
	
	@Test
	public void agregarTests() {
		Cliente c5 = new Cliente(545,6874);
		
		centro.agregarCliente(c5, 554);
		
		assertTrue(centro.contiene(c5));
	}
	
	@Test
	public void eliminarTest() {
		centro.eliminarCliente(c3);
		
		assertFalse(centro.contiene(c3));
	}
	
	@Test
	public void cantidadDeClientesTest() {
		assertEquals(4, centro.cantidadDeClientes());
	}
	
	@Test
	public void latitudTest() {
		assertEquals(0, Double.compare(-56.57498, centro.getLatitud()) );
	}
	
	@Test
	public void longitudTest() {
		assertEquals(0,	Double.compare(68.57486, centro.getLongitud()) );
	}

}

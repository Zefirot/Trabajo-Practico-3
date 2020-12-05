package tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import codigoBusiness.CentroDistribucion;
import codigoBusiness.Cliente;
import codigoBusiness.ProcesarDatos;

public class ProcesarDatosTest {

	
	private ProcesarDatos procesarDatos;
	
	
	@Test
	public void ejemplo1Test() {
		armarEjemplo1();
		
		ArrayList<CentroDistribucion> solucionesPosibles = procesarDatos.centrosPosibles();
		
		ArrayList<Cliente> solucion1 = solucion1Ejemplo1();
		ArrayList<Cliente> solucion2 = solucion2Ejemplo1();
		
		
		Assert.sonIguales(solucion1, solucion2, solucionesPosibles);
		
	}
	
	private void armarEjemplo1() {
		Cliente c1 = new Cliente(-34.611489, -58.446047);
		Cliente c2 = new Cliente(-34.617102, -58.445505); 
		Cliente c3 = new Cliente(-34.619448, -58.437891);
		Cliente c4 = new Cliente(-34.612499, -58.436736);
		Cliente c5 = new Cliente(-34.616597, -58.422590);
		Cliente c6 = new Cliente(-34.622062, -58.424755);

		CentroDistribucion ct1 = new CentroDistribucion(-34.614845, -58.439443);
		CentroDistribucion ct2 = new CentroDistribucion(-34.617251, -58.428688);
		
		Set<Cliente> clientes = new HashSet<Cliente>();
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		clientes.add(c4);
		clientes.add(c5);
		clientes.add(c6);
		
		Set<CentroDistribucion> centros = new HashSet<CentroDistribucion>();
		centros.add(ct1);
		centros.add(ct2);
		
		procesarDatos = new ProcesarDatos( clientes, centros, 2 );
		
	}
	
	private ArrayList<Cliente> solucion1Ejemplo1(){
		Cliente c1 = new Cliente(-34.611489, -58.446047);
		Cliente c2 = new Cliente(-34.617102, -58.445505); 
		Cliente c3 = new Cliente(-34.619448, -58.437891);
		Cliente c4 = new Cliente(-34.612499, -58.436736);
		
		ArrayList<Cliente> solucion1 = new ArrayList<Cliente>();
		solucion1.add(c1);
		solucion1.add(c2);
		solucion1.add(c3);
		solucion1.add(c4);
		
		return solucion1;
	}
	
	private ArrayList<Cliente> solucion2Ejemplo1(){
		Cliente c5 = new Cliente(-34.616597, -58.422590);
		Cliente c6 = new Cliente(-34.622062, -58.424755);
		
		ArrayList<Cliente> solucion2 = new ArrayList<Cliente>();
		solucion2.add(c5);
		solucion2.add(c6);
		
		return solucion2;
	}

}

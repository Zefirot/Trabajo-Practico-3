package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import codigoBusiness.CentroDistribucion;
import codigoBusiness.Cliente;

public class Assert {

	public static void sonIguales(ArrayList<Cliente> solucion1, ArrayList<Cliente> solucion2 ,ArrayList<CentroDistribucion> solucionActual ) {
		
		sonIguales(solucion1, toSetAtArrayList(solucionActual.get(0).clientes()));
		sonIguales(solucion2, toSetAtArrayList(solucionActual.get(1).clientes()));
		
	}
	
	private static void sonIguales( ArrayList<Cliente> solucionEsperada, ArrayList<Cliente> solucionActual ) {
		
		assertEquals(solucionEsperada.size(), solucionActual.size());
			
		for(int i=0 ; i<solucionEsperada.size() ; i++ ) {
			
			assertTrue(solucionEsperada.contains(solucionActual.get(i)));
			
		}
	
	}

	private static ArrayList<Cliente> toSetAtArrayList(Set<Cliente> clientes) {
	
		ArrayList<Cliente> ret = new ArrayList<Cliente>();
		
		for(Cliente cliente: clientes) {
			ret.add(cliente);
		}
		
		return ret;
		
	}

	
	
}

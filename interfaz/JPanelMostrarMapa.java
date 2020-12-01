package interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import codigoBusiness.CentroDistribucion;
import codigoBusiness.Cliente;
import codigoBusiness.ProcesarDatos;

public class JPanelMostrarMapa extends JPanel {

	private ArrayList<CentroDistribucion> soluciones;
	
	private JMapViewer mapa;
	
	public JPanelMostrarMapa() {
		this.setBounds(0, 0, 521, 325);
		setLayout(null);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBounds(10, 11, 501, 260);
		add(panelMapa);
		
		mapa = new JMapViewer();
		mapa.setBounds(10, 11, 501, 260);
		
		panelMapa.add(mapa);
		
		
	}
	
	public void setDatos(ArrayList<Cliente> clientes, ArrayList<CentroDistribucion> centros, int cantSoluciones) {
		Set<Cliente> conjClientes = new HashSet<Cliente>(clientes);
		Set<CentroDistribucion> conjCentros = new HashSet<CentroDistribucion>(centros);
		
		ProcesarDatos datos = new ProcesarDatos(conjClientes, conjCentros, cantSoluciones);
	
		this.soluciones = datos.centrosPosibles();
		
		marcarCentros();
		
	}
	
	public void marcarCentros() {
		CentroDistribucion centroPrincipal = soluciones.get(0);
		Coordinate coordinate = new Coordinate(centroPrincipal.getLatitud(), centroPrincipal.getLongitud());
		mapa.setDisplayPosition(coordinate, 12);
	
		
		for(int i=0 ; i<soluciones.size() ; i++ ) {
			
			Coordinate cordenada = new Coordinate(soluciones.get(i).getLatitud(), soluciones.get(i).getLongitud());
			MapMarker centro = new MapMarkerDot("Centro: "+i, cordenada);
			centro.getStyle().setBackColor(Color.RED);
			centro.getStyle().setColor(Color.RED);
			
			mapa.addMapMarker(centro);
			
			marcarClientes(soluciones.get(i).clientes());
			
		}
		
		
	}
	
	public void marcarClientes(Set<Cliente> clientes) {
		int numeroCliente = 1;
		for(Cliente cliente : clientes) {
			
			Coordinate cordenada = new Coordinate(cliente.getLatitud(), cliente.getLongitud());
			MapMarker clientePoint = new MapMarkerDot("Cliente: "+ numeroCliente ,cordenada);
			clientePoint.getStyle().setBackColor(Color.BLUE);
			clientePoint.getStyle().setColor(Color.BLUE);
			
			mapa.addMapMarker(clientePoint);
			
			numeroCliente++;
		}
		
	}
	
}

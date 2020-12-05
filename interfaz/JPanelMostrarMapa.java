package interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import codigoBusiness.CentroDistribucion;
import codigoBusiness.Cliente;
import codigoBusiness.ProcesarDatos;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class JPanelMostrarMapa extends JPanel {

	private ArrayList<CentroDistribucion> soluciones;
	
	private JMapViewer mapa;
	private JLabel lblCostoTotal;
	
	public JPanelMostrarMapa() {
		this.setBounds(0, 0, 521, 325);
		setLayout(null);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBounds(10, 11, 501, 260);
		add(panelMapa);
		
		//Se crea y se agrega el mapa al panel
		mapa = new JMapViewer();
		mapa.setBounds(10, 11, 501, 260);
		panelMapa.add(mapa);
		
		lblCostoTotal = new JLabel("Costo Total: ");
		lblCostoTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCostoTotal.setBounds(10, 300, 181, 14);
		add(lblCostoTotal);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UIMain.volverACargarDatos();
			}

		});
		btnVolver.setBounds(401, 297, 89, 23);
		add(btnVolver);
		
		
	}
	
	//Esta funcion toma los datos y muestra todo por pantalla
	public void setDatos(ArrayList<Cliente> clientes, ArrayList<CentroDistribucion> centros, int cantSoluciones) {
		Set<Cliente> conjClientes = new HashSet<Cliente>(clientes);
		Set<CentroDistribucion> conjCentros = new HashSet<CentroDistribucion>(centros);
		
		ProcesarDatos datos = new ProcesarDatos(conjClientes, conjCentros, cantSoluciones);
	
		this.soluciones = datos.centrosPosibles();
		
		marcarCentros();
		marcarCosto();
		
	}
	
	//Esta funcion marca los puntos para representar los centros, tambien traza el camino de centro-cliente.
	public void marcarCentros() {
		CentroDistribucion centroPrincipal = soluciones.get(0);
		Coordinate coordinate = new Coordinate(centroPrincipal.getLatitud(), centroPrincipal.getLongitud());
		mapa.setDisplayPosition(coordinate, 12);
	
		
		for(int i=0 ; i<soluciones.size() ; i++ ) {
			
			Coordinate cordenada = new Coordinate(soluciones.get(i).getLatitud(), soluciones.get(i).getLongitud());
			MapMarker centro = new MapMarkerDot("Centro: "+i+1, cordenada);
			centro.getStyle().setBackColor(Color.RED);
			centro.getStyle().setColor(Color.RED);
			
			mapa.addMapMarker(centro);

			marcarClientes(soluciones.get(i).clientes());
			
			trazarRutaClienteCentro(soluciones.get(i), soluciones.get(i).clientes());
		}
		
		
	}
	
	//Esta funcion simplemente marca a los clientes con un punto en el mapa
	private void marcarClientes(Set<Cliente> clientes) {
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
	
	//Esta funcion se encarga de trazar la distancia entre los centros y los clientes
	private void trazarRutaClienteCentro(CentroDistribucion centro, Set<Cliente> clientes) {
		
		for(Cliente cliente : clientes) {
			ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
			
			coordenadas.add(new Coordinate(centro.getLatitud(), centro.getLongitud()));
			coordenadas.add(new Coordinate(cliente.getLatitud(),cliente.getLongitud()));
			coordenadas.add(new Coordinate(centro.getLatitud(), centro.getLongitud()));
			
			MapPolygon polygono = new MapPolygonImpl(coordenadas);
			
			mapa.addMapPolygon(polygono);
		}
		
	}
	
	//Esta funcion realiza la suma del costo total para luego mostrarla
	private void marcarCosto() {
		double costoTotal = soluciones.stream().mapToDouble(c -> c.costoTotal()).sum();
		
		lblCostoTotal.setText(lblCostoTotal.getText() + costoTotal);
		
	}
}

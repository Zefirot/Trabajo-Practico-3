package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UIMain {

	private JFrame frame;
	private static JPanelCargarDatos panelDatos;
	private static JPanelMostrarMapa panelMapa;
	private static JLayeredPane layeredPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMain window = new UIMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public UIMain() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 537, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 521, 325);
		frame.getContentPane().add(layeredPane);
		
		panelDatos = new JPanelCargarDatos();
		panelDatos.setVisible(true);
		layeredPane.add(panelDatos);
		
		panelMapa = new JPanelMostrarMapa();
		panelMapa.setVisible(false);
		layeredPane.add(panelMapa);
		
	}
	
	public static void cambiarASolucion() {
		panelDatos.setVisible(false);
		panelMapa.setVisible(true);
		
		panelMapa.setDatos(panelDatos.getClientes(), panelDatos.getCentros(), panelDatos.getCantidadSoluciones());
		
	}
	
	//Este metodo "reinicia" todo por default
	public static void volverACargarDatos() {
		panelMapa.setVisible(false);//Primero quito el panelDatosProcesados de la vision del usuario
		
		panelDatos = new JPanelCargarDatos();
		panelMapa = new JPanelMostrarMapa();
		
		//Se actualiza el layeredPane quitando los componentes anteriores y metiendo los nuevos.
		layeredPane.remove(panelDatos);
		layeredPane.add(panelDatos);

		layeredPane.remove(panelMapa);
		layeredPane.add(panelMapa);
		
		panelDatos.setVisible(true);
		panelMapa.setVisible(false);
	}
}

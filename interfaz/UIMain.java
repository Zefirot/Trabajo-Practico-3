package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UIMain {

	private JFrame frame;
	private static JPanelCargarDatos panelDatos;
	private static JPanelMostrarMapa panelMapa;
	
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
		
		
		JLayeredPane layeredPane = new JLayeredPane();
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
}

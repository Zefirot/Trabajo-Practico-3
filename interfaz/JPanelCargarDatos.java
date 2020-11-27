package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import codigoBusiness.ClientesJSON;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class JPanelCargarDatos extends JPanel {
	private JLabel lblCargarCentros;
	private JTable tableClientes;

	private ClientesJSON clientes;
	
	public JPanelCargarDatos() {
		this.setBounds(0, 0, 521, 325);
		setLayout(null);
		
		JLabel lblCargarClientes = new JLabel("Cargar Clientes: ");
		lblCargarClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCargarClientes.setBounds(39, 11, 97, 28);
		add(lblCargarClientes);
		
		lblCargarCentros = new JLabel("Cargar Centros: ");
		lblCargarCentros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCargarCentros.setBounds(39, 50, 97, 21);
		add(lblCargarCentros);
		
		JLabel lblCantidadDeSoluciones = new JLabel("Cantidad de soluciones: ");
		lblCantidadDeSoluciones.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidadDeSoluciones.setBounds(322, 28, 168, 21);
		add(lblCantidadDeSoluciones);
		
		JButton btnCargarClientes = new JButton("Cargar");
		btnCargarClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				clientes = capturarArchivo(btnCargarClientes);
				
				
			}
		});
		btnCargarClientes.setBounds(146, 15, 89, 23);
		add(btnCargarClientes);
		
		JButton btnCargarCentros = new JButton("Cargar");
		btnCargarCentros.setBounds(146, 50, 89, 23);
		add(btnCargarCentros);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProcesar.setBounds(209, 280, 89, 23);
		add(btnProcesar);
		
		JSpinner spinnerSoluciones = new JSpinner();
		spinnerSoluciones.setBounds(470, 29, 30, 20);
		add(spinnerSoluciones);
		
		//Tablas
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Numero");
		model.addColumn("Latitud");
		model.addColumn("Longitud");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 124, 213, 141);
		scrollPane.setBackground(null);
		add(scrollPane);
		
		tableClientes = new JTable();
		tableClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(tableClientes);
		tableClientes.setEnabled(false);
		tableClientes.setModel(model);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClientes.setBounds(128, 98, 46, 14);
		add(lblClientes);
		
		JCheckBox checkBoxClientes = new JCheckBox("");
		checkBoxClientes.setBounds(242, 16, 21, 23);
		checkBoxClientes.setEnabled(false);
		add(checkBoxClientes);
		
		JCheckBox checkBoxCentros = new JCheckBox("");
		checkBoxCentros.setBounds(241, 50, 21, 23);
		checkBoxCentros.setEnabled(false);
		add(checkBoxCentros);
		
		
	}
	
	private ClientesJSON capturarArchivo(JButton boton) {
		ClientesJSON clientesCapturados = null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Seleccionar archivo");
		
		if(fc.showOpenDialog(boton) == JFileChooser.APPROVE_OPTION) {

			File archivo = new File(fc.getSelectedFile().toString());

			clientesCapturados = clientesCapturados.leerJSON(archivo.getPath());

			//checkBox.setSelected(true);

		}
		
		return clientesCapturados;
	}
	
}

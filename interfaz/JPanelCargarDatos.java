package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import codigoBusiness.CentroDistribucion;
import codigoBusiness.CentroDistribucionJSON;
import codigoBusiness.Cliente;
import codigoBusiness.ClientesJSON;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class JPanelCargarDatos extends JPanel {
	private DefaultTableModel modelClientes;
	private DefaultTableModel modelCentros;
	
	private ArrayList<Cliente> clientes;
	private ArrayList<CentroDistribucion> centros;
	
	public JPanelCargarDatos() {
		this.setBounds(0, 0, 521, 325);
		setLayout(null);
		
		//Labels, checkBox y spinner
		JLabel lblCargarClientes = new JLabel("Cargar Clientes: ");
		lblCargarClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCargarClientes.setBounds(39, 11, 97, 28);
		add(lblCargarClientes);
		
		JLabel lblCargarCentros = new JLabel("Cargar Centros: ");
		lblCargarCentros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCargarCentros.setBounds(39, 50, 97, 21);
		add(lblCargarCentros);
		
		JLabel lblCantidadDeSoluciones = new JLabel("Cantidad de soluciones: ");
		lblCantidadDeSoluciones.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidadDeSoluciones.setBounds(322, 28, 168, 21);
		add(lblCantidadDeSoluciones);
		
		JCheckBox checkBoxClientes = new JCheckBox("");
		checkBoxClientes.setBounds(242, 16, 21, 23);
		checkBoxClientes.setEnabled(false);
		add(checkBoxClientes);
		
		JCheckBox checkBoxCentros = new JCheckBox("");
		checkBoxCentros.setBounds(241, 50, 21, 23);
		checkBoxCentros.setEnabled(false);
		add(checkBoxCentros);
		
		JSpinner spinnerSoluciones = new JSpinner();
		spinnerSoluciones.setBounds(470, 29, 30, 20);
		add(spinnerSoluciones);
		
		//Botones
		JButton btnCargarClientes = new JButton("Cargar");
		btnCargarClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				clientes = capturarArchivoClientes(btnCargarClientes, checkBoxClientes);
				cargarTablaClientes(modelClientes);
				
			}
		});
		btnCargarClientes.setBounds(146, 15, 89, 23);
		add(btnCargarClientes);
		
		JButton btnCargarCentros = new JButton("Cargar");
		btnCargarCentros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				centros = capturarArchivoCentros(btnCargarCentros, checkBoxCentros);
				cargarTablaCentros(modelCentros);
				setearSpinner(spinnerSoluciones);
			}
		});
		btnCargarCentros.setBounds(146, 50, 89, 23);
		add(btnCargarCentros);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProcesar.setBounds(209, 280, 89, 23);
		add(btnProcesar);

		//Tabla de Clientes
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClientes.setBounds(121, 101, 46, 14);
		add(lblClientes);
	
		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setBounds(39, 123, 213, 141);
		scrollPaneClientes.setBackground(null);
		add(scrollPaneClientes);
		
		modelClientes = new DefaultTableModel();
		modelClientes.addColumn("Numero");
		modelClientes.addColumn("Latitud");
		modelClientes.addColumn("Longitud");
		
		JTable tableClientes = new JTable();
		tableClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneClientes.setViewportView(tableClientes);
		tableClientes.setEnabled(false);
		tableClientes.setModel(modelClientes);
		
		//Tabla de Centros
		JLabel lblCentros = new JLabel("Centros");
		lblCentros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCentros.setBounds(373, 102, 46, 14);
		add(lblCentros);
		
		JScrollPane scrollPaneCentros = new JScrollPane();
		scrollPaneCentros.setBounds(287, 123, 213, 141);
		scrollPaneCentros.setBackground(null);
		add(scrollPaneCentros);
		
		modelCentros = new DefaultTableModel();
		modelCentros.addColumn("Numero");
		modelCentros.addColumn("Latitud");
		modelCentros.addColumn("Longitud");
		
		JTable tableCentros = new JTable();
		tableCentros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneCentros.setViewportView(tableCentros);
		tableCentros.setEnabled(false);
		tableCentros.setModel(modelCentros);
		
		
	}
	
	private ArrayList<Cliente> capturarArchivoClientes(JButton boton, JCheckBox checkBox) {
		ClientesJSON clientesJSON = new ClientesJSON();
		ArrayList<Cliente> clientesCapturados = null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Seleccionar archivo");
		
		if(fc.showOpenDialog(boton) == JFileChooser.APPROVE_OPTION) {

			File archivo = new File(fc.getSelectedFile().toString());

			clientesCapturados = clientesJSON.leerJSON(archivo.getPath()).getTodosLosClientes();

			checkBox.setSelected(true);

		}
		
		return clientesCapturados;
	}
	
	private ArrayList<CentroDistribucion> capturarArchivoCentros(JButton boton, JCheckBox checkBox){
		CentroDistribucionJSON centrosJSON = new CentroDistribucionJSON();
		ArrayList<CentroDistribucion> centrosCapturados = null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Seleccionar archivo");
		
		if(fc.showOpenDialog(boton) == JFileChooser.APPROVE_OPTION) {

			File archivo = new File(fc.getSelectedFile().toString());

			centrosCapturados = centrosJSON.leerJSON(archivo.getPath()).getTodosLosCentros();

			checkBox.setSelected(true);

		}
		
		return centrosCapturados;
	}
	
	private void cargarTablaClientes(DefaultTableModel model) {
		for(int i=0 ; i<clientes.size() ; i++) {
			
			model.addRow(new String[] {String.valueOf(i+1), 
					String.valueOf(clientes.get(i).getLatitud()),
					String.valueOf(clientes.get(i).getLongitud())});
		}
	}
	
	private void cargarTablaCentros(DefaultTableModel model) {
		for(int i=0 ; i<centros.size() ; i++) {

			model.addRow(new String[] {String.valueOf(i+1), 
					String.valueOf(centros.get(i).getLatitud()),
					String.valueOf(centros.get(i).getLongitud())});
		}
	}
	
	private void setearSpinner(JSpinner spinnerSoluciones) {
		SpinnerModel modelo = new SpinnerNumberModel(0, 0, centros.size(), 1);  
		spinnerSoluciones.setModel(modelo);
	}
	
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	
}

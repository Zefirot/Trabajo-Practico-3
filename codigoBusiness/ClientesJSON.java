package codigoBusiness;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ClientesJSON {

	private ArrayList<Cliente> clientes;
	
	public ClientesJSON() {
		this.clientes = new ArrayList<Cliente>();
	}
	
	public void agregarCliente(Cliente persona) {
		clientes.add(persona);
	}
	
	public Cliente getCliente(int i) {
		return clientes.get(i);
	}
	
	public int getCantidadClientes() {
		return clientes.size();
	}
	
	public ArrayList<Cliente> getTodosLosClientes(){
		@SuppressWarnings("unchecked")
		ArrayList<Cliente> ret = (ArrayList<Cliente>) clientes.clone();
		return ret;
	}
	
	public String generarJSONBasico() {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(this);
		
		return json;
	}
	
	public String generarJSONPretty() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		
		return json;
	}
	
	public void guardarJSON(String jsonParaGuardar, String archivoDestino) {
		
		try {
			FileWriter writer = new FileWriter(archivoDestino);
			writer.write(jsonParaGuardar);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ClientesJSON leerJSON(String archivo) {
		Gson gson = new Gson();
		ClientesJSON ret = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			
			ret = gson.fromJson(br, ClientesJSON.class);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	public static void main(String[] args) {
		
		Cliente c1 = new Cliente(-34.611489, -58.446047);
		Cliente c2 = new Cliente(-34.617102, -58.445505); 
		Cliente c3 = new Cliente(-34.619448, -58.437891);
		Cliente c4 = new Cliente(-34.612499, -58.436736);
		Cliente c5 = new Cliente(-34.616597, -58.422590);
		Cliente c6 = new Cliente(-34.622062, -58.424755);
		
		ClientesJSON ct = new ClientesJSON();
		ct.agregarCliente(c1);
		ct.agregarCliente(c2);
		ct.agregarCliente(c3);
		ct.agregarCliente(c4);
		ct.agregarCliente(c5);
		ct.agregarCliente(c6);
		
		String jsonBasico = ct.generarJSONPretty();
		
		ct.guardarJSON(jsonBasico, "Clientes.JSON");
		
	}
	
	
}

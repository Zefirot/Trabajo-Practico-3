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
	
	public void agregarPersona(Cliente persona) {
		clientes.add(persona);
	}
	
	public Cliente getPersona(int i) {
		return clientes.get(i);
	}
	
	public int getCantidadPersonas() {
		return clientes.size();
	}
	
	public ArrayList<Cliente> getTodasLasPersonas(){
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
	
	
}

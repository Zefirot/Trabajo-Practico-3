package codigoBusiness;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CentroDistribucionJSON {

	private ArrayList<CentroDistribucion> centros;
	
	public CentroDistribucionJSON() {
		this.centros = new ArrayList<CentroDistribucion>();
	}
	
	public void agregarPersona(CentroDistribucion persona) {
		centros.add(persona);
	}
	
	public CentroDistribucion getPersona(int i) {
		return centros.get(i);
	}
	
	public int getCantidadPersonas() {
		return centros.size();
	}
	
	public ArrayList<Cliente> getTodasLasPersonas(){
		@SuppressWarnings("unchecked")
		ArrayList<Cliente> ret = (ArrayList<Cliente>) centros.clone();
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
	
	public CentroDistribucionJSON leerJSON(String archivo) {
		Gson gson = new Gson();
		CentroDistribucionJSON ret = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			
			ret = gson.fromJson(br, CentroDistribucionJSON.class);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	
}

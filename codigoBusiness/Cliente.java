package codigoBusiness;

public class Cliente {

	private double latitud;
	private double longitud;
	
	public Cliente(double latitud, double longitud) {
		
		this.latitud=latitud;
		this.longitud=longitud;
			
	}
	
	@Override
	public boolean equals(Object cliente) {
		
		if(cliente == this) { //Comparo de que el objeto no sea el mismo objeto
			return true;
		}
		
		if( !(cliente instanceof Cliente) ) { //Comparo de que el objeto no sea una instancia de Cliente
			return false;
		}
		
		Cliente clienteActual = (Cliente) cliente;
		
		return this.getLatitud()==clienteActual.getLatitud() &&   
				this.getLongitud() == clienteActual.getLongitud();
	}
	
	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	
	
	
}

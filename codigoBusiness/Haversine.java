package codigoBusiness;

public class Haversine {

	private static final double radioDeLaTierra=6371;  //Radio aproximado de la tierra

	public static int distancia(double latitudOrigen, double longitudOrigen, double latitudFin, double longitudFin) {

		//Se calcula la diferencia entre los 2 puntos y luego se pasa a radianes
		double difLat  = Math.toRadians((latitudFin - latitudOrigen));
		double difLong = Math.toRadians((longitudFin - longitudOrigen));

		//Paso a radianes la latitud origen y fin
		double latOrgin = Math.toRadians(latitudOrigen);
		double latFin   = Math.toRadians(latitudFin);

		//Se aplica la formula de haversine
		double a = haversen(difLat) + Math.cos(latOrgin) * Math.cos(latFin) * haversen(difLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (int)Math.ceil(radioDeLaTierra * c * 1000); // Se redondea el resultado obtenido en metros hacia arriba
	}

	//Se calcula el seno al cuadrado de la diferencia de latitud y longitud
	public static double haversen(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}
	
	
}

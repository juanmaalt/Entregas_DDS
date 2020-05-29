public Interface AccuWeatherAPI(){
	public HashMap obtenerClimaAPI(String ciudad);
}

public Interface WeatherBitAPI(){
	public BigDecimal obtenerClimaAPI(String ciudad);
}



class ClimaAccuweather implements AccuWeatherAPI{
	private List<Map<String, Object>> condicionesClimaticas;
	private requestTime = null;
	private requestCount = 0;

	public BigDecimal obtenerClimaAPI(String ciudad){
		if(requestCount < 10){
			condicionesClimaticas = List<Map<String, Object>> condicionesClimaticas = apiClima.getWeather(ciudad);
			requestTime = condicionesClimaticas.get(0).get("DateTime").getHourOfDay();
			return condicionesClimaticas.get(0).get("Temperature");
		}
				
		int horaActual = new DateTime().getHourOfDay();

		return condicionesClimaticas.get(horaActual-requestTime).get("Temperature");
	}
}

class ClimaWeatherBit implements WeatherBitAPI{
	public BigDecimal obtenerClimaAPI(String ciudad){
		//Llamada a API de WeatherBit
	}
}

class Clima{
	public int obtenerClimaActual(String interfazClima, String ciudad){
		switch(interfazClima) {
			case "AccuWeather":
				ClimaAccuweather.obtenerClimaAPI(ciudad);
				break;
			case "WeatherBit"
				ClimaWeatherBit.obtenerClimaAPI(ciudad);
				break;
			default:
			// Por default usamos AccuWeather
			ClimaAccuweather.obtenerClimaAPI(ciudad);
	}
}



class Usuario {
	private Guardarropas guardarropas;
	private String interfazClima = "AccuWheather";
	private String ciudad = "Buenos Aires, Argentina";

	public BigDecimal obtenerClima(String interfazClima, String ciudad){
		return Clima.obtenerClimaActual(interfazClima, ciudad)
	}
	
	public Atuendo obtenerSugerencias(){
		return guardarropas.sugerirAtuendo(obtenerClima(interfazClima, ciudad));
	}
}


class Guardarropas{
	private List<Prendas> prendas;
	
	public Atuendo sugerirAtuendo(BigDecimal temperatura){
		Prenda remera = seleccionarPrenda(prendas, REMERA, temperatura);
		Prenda pantalon = seleccionarPrenda(prendas, PANTALON, temperatura);
		Prenda zapatos = seleccionarPrenda(prendas, ZAPATOS, temperatura);
		Prenda gorro = seleccionarPrenda(prendas, GORRO, temperatura);
		
		return new Atuendo(remera, pantalon, zapatos, gorro);
	}
	
	public Prenda seleccionarPrenda(TipoPrenda tipoPrenda, BigDecimal temperatura){
		return prendas.filter(prenda => (prenda.tipoPrenda == tipoPrenda && prenda.temperaturaValida(temperatura))).[0];
	}
}

class Prenda {
	....
	private BigDecimal temperaturaMinima
	private BigDecimal temperaturaMaxima

	public bool temperaturaValida(BigDecimal temperatura){
		return temperaturaMinima >= temperatura && temperaturaMaxima =< temperatura;
	}
}


class Atuendo{
	private Prenda remera;
	private Prenda pantalon;
	private Prenda zapatos;
	private Prenda gorro;
}

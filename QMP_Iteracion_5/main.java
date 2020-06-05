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
	private List<Guardarropas> listaDeGuardarropas;
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
	private String nombreGuardarropas;
	private List<Usuario> dueniosGuardarropas;
	private List<Prenda> prendas;
	private List<Pair<Prenda, Integer>> prendasSugeridas;
	private List<Pair<Prenda, Integer>> prendasModificadas;
	
	public Guardarropas(String _nombreGuardarropas, List<Usuario> _dueniosGuardarropas){
		nombreGuardarropas = _nombreGuardarropas;
		dueniosGuardarropas = _dueniosGuardarropas;
	}
	
	public sugerirAgregarPrenda(Prenda _prendaSugerida){
		prendasSugeridas.add(_prendaSugerida, 1);
	}
	
	public sugerirQuitarPrenda(Prenda _prendaSugerida){
		prendasSugeridas.add(_prendaSugerida, 0);
	}
	
	public verSugerenciasDeCambio(){
		prendasSugeridas.forEach(prenda => {
			bool esAceptada = mostrarPrenda(prenda.getLeft());
			if(esAceptada){
				if(prenda.getRight() == 0{
					prendas.remove(prenda.getLeft());
				}else{
					prendas.add(prenda.getLeft());
				}
				
				prendasModificadas.add(prenda);
			}
		})
		
		prendasSugeridas = {};		
	}
	
	public verCambiosGuardarropas(){
		prendasModificadas.forEach(prenda => {
			bool esAceptada = mostrarPrenda(prenda.getLeft());
			if(esAceptada){
				if(prenda.getRight() == 0{
					prendas.add(prenda.getLeft());
					
				}else{
					prendas.remove(prenda.getLeft());
				}
				
				prendasModificadas.remove(prenda);
			}
		})		
	}
	
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

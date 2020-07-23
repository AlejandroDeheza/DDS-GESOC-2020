package model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import ubicacion.InfoDeUbicacion;

import javax.ws.rs.core.MediaType;

import org.json.*;

public class MercadoLibreApi implements InfoDeUbicacion {
	
	public ClientResponse ObtenerJSON(String Url) {
		ClientResponse data = Client.create()
				.resource("https://api.mercadolibre.com/")
				.path(Url)
				.accept(MediaType.APPLICATION_JSON) 
				.get(ClientResponse.class);
		return data;
	}
	
	public JSONObject obtenerJSONEspecifico(String objetivo, String enlace, JSONArray jsonArray) {
		String idObjetivo = null;
		
		for(int i= 0; i < jsonArray.length();i++) {
			JSONObject jsonObjetivo = jsonArray.getJSONObject(i);
			
			if(jsonObjetivo.getString("name").equals(objetivo))
				idObjetivo = jsonObjetivo.getString("id");
		}
		
		return new JSONObject(ObtenerJSON(enlace + idObjetivo).getEntity(String.class));
	}
	
	public JSONObject obtenerJSONPais(String Pais) {
		
		ClientResponse data = ObtenerJSON("/classified_locations/countries");
		JSONArray jsonArray = new JSONArray(data.getEntity(String.class));
		return obtenerJSONEspecifico(Pais, "/classified_locations/countries/", jsonArray);
	}
	
	public JSONObject obtenerJSONProvincia(String Provincia, String Pais) {
		
		JSONObject infoPais = obtenerJSONPais(Pais);
		JSONArray listaProvincias = infoPais.getJSONArray("states");
		return obtenerJSONEspecifico(Provincia, "/classified_locations/states/", listaProvincias);
	}

	public JSONObject obtenerJSONCiudad(String Ciudad, String Provincia, String Pais) {
		
		JSONObject infoProvincia = obtenerJSONProvincia(Provincia, Pais);		
		JSONArray listaCiudades = infoProvincia.getJSONArray("cities");
		return obtenerJSONEspecifico(Ciudad, "/classified_locations/cities/", listaCiudades);
	}
	
	public JSONObject obtenerJSONMoneda(String Pais){
		JSONObject infoPais = obtenerJSONPais(Pais);
		return new JSONObject(ObtenerJSON("/currencies/" + infoPais.getString("currency_id")).getEntity(String.class));
	}
	
	//No hace falta usar @override cuando usamos una Interface
	public String obtenerInfoPais(String Pais) {
		return obtenerJSONPais(Pais).toString();
	}
	

	public String obtenerInfoProvincia(String Provincia, String Pais) {
		return obtenerJSONProvincia(Provincia, Pais).toString();
	}
	

	public String obtenerInfoCiudad(String Ciudad, String Provincia, String Pais) {
		return obtenerJSONCiudad(Ciudad, Provincia, Pais).toString();
	}
	

	public String obtenerInfoMoneda(String Pais) {
		return obtenerJSONMoneda(Pais).toString();
	}
}

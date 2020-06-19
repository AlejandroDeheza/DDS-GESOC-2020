package model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		
	
	public JSONObject obtenerJSONPais(String Pais) {
		String idPais = null;
		
		ClientResponse data = ObtenerJSON("/classified_locations/countries");

		JSONArray JsonArray = new JSONArray(data.getEntity(String.class));
		
		for(int i= 0; i < JsonArray.length();i++) {
			JSONObject jsonPais = JsonArray.getJSONObject(i);
			
			if(jsonPais.getString("name").equals(Pais))
				idPais = jsonPais.getString("id");
		}
		
		return new JSONObject(ObtenerJSON("/classified_locations/countries/" + idPais).getEntity(String.class));
		
	}


	
	public JSONObject obtenerJSONProvincia(String Provincia, String Pais) {
		JSONObject infoPais = obtenerJSONPais(Pais);
		String idProvincia = null;
		
		JSONArray listaProvincias = infoPais.getJSONArray("states");
		
		for(int i= 0; i < listaProvincias.length();i++) {
			JSONObject jsonProvincia = listaProvincias.getJSONObject(i);
			
			if(jsonProvincia.getString("name").equals(Provincia))
				idProvincia = jsonProvincia.getString("id");
		}
		
		return new JSONObject(ObtenerJSON("/classified_locations/states/" + idProvincia).getEntity(String.class));

	}

	
	public JSONObject obtenerJSONCiudad(String Ciudad, String Provincia, String Pais) {
		JSONObject infoProvincia = obtenerJSONProvincia(Provincia,Pais);
		String idCiudad = null;
		
		JSONArray listaCiudades = infoProvincia.getJSONArray("cities");
		
		for(int i= 0; i < listaCiudades.length();i++) {
			JSONObject jsonCiudad = listaCiudades.getJSONObject(i);
			
			if(jsonCiudad.getString("name").equals(Ciudad))
				idCiudad = jsonCiudad.getString("id");
		}
		
		return new JSONObject(ObtenerJSON("/classified_locations/cities/" + idCiudad).getEntity(String.class));
		
		
	}
	
	public JSONObject obtenerJSONMoneda(String Pais){
		JSONObject infoPais = obtenerJSONPais(Pais);
		
		return new JSONObject(ObtenerJSON("/currencies/" + infoPais.getString("currency_id")).getEntity(String.class));
	}
	
	@Override
	public String obtenerInfoPais(String Pais) {
		return obtenerJSONPais(Pais).toString();
	}
	
	@Override
	public String obtenerInfoProvincia(String Provincia, String Pais) {
		return obtenerJSONProvincia(Provincia, Pais).toString();
	}
	
	@Override
	public String obtenerInfoCiudad(String Ciudad, String Provincia, String Pais) {
		return obtenerJSONCiudad(Ciudad, Provincia, Pais).toString();
	}
	
	@Override
	public String obtenerInfoMoneda(String Pais) {
		return obtenerJSONMoneda(Pais).toString();
	}
}

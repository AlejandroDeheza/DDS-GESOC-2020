package model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.json.*;

public class MercadoLibreApi implements InfoDireccionPostal {
	
	public ClientResponse ObtenerJSON(String Url) {
		ClientResponse data = Client.create()
				.resource("https://api.mercadolibre.com/")
				.path(Url)
				.accept(MediaType.APPLICATION_JSON) 
				.get(ClientResponse.class);
		return data;
	}
		
	@Override
	public JSONObject obtenerInfoPais(String Pais) {
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


	@Override
	public JSONObject obtenerInfoProvincia(String Provincia, String Pais) {
		JSONObject infoPais = obtenerInfoPais(Pais);
		String idProvincia = null;
		
		JSONArray listaProvincias = infoPais.getJSONArray("states");
		
		for(int i= 0; i < listaProvincias.length();i++) {
			JSONObject jsonProvincia = listaProvincias.getJSONObject(i);
			
			if(jsonProvincia.getString("name").equals(Provincia))
				idProvincia = jsonProvincia.getString("id");
		}
		
		return new JSONObject(ObtenerJSON("/classified_locations/states/" + idProvincia).getEntity(String.class));

	}

	@Override
	public JSONObject obtenerInfoCiudad(String Ciudad, String Provincia, String Pais) {
		JSONObject infoProvincia = obtenerInfoProvincia(Provincia,Pais);
		String idCiudad = null;
		
		JSONArray listaCiudades = infoProvincia.getJSONArray("cities");
		
		for(int i= 0; i < listaCiudades.length();i++) {
			JSONObject jsonCiudad = listaCiudades.getJSONObject(i);
			
			if(jsonCiudad.getString("name").equals(Ciudad))
				idCiudad = jsonCiudad.getString("id");
		}
		
		return new JSONObject(ObtenerJSON("/classified_locations/cities/" + idCiudad).getEntity(String.class));
		
		
	}
	
	
	public JSONObject obtenerInfoMoneda(String Pais){
		JSONObject infoPais = obtenerInfoPais(Pais);
		
		return new JSONObject(ObtenerJSON("/currencies/" + infoPais.getString("currency_id")).getEntity(String.class));
	
	}

}

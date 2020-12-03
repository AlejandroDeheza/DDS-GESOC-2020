package model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import ubicacion.InfoDeUbicacionYMoneda;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

public class MercadoLibreApi implements InfoDeUbicacionYMoneda {
	
	public ClientResponse obtenerJSON(String Url) {
		ClientResponse data = Client.create()
				.resource("https://api.mercadolibre.com/")
				.path(Url)
				.accept(MediaType.APPLICATION_JSON) 
				.get(ClientResponse.class);
		return data;
	}
	
	public ClientResponse obtenerJSON(String Url,MultivaluedMap<String,String> params) {
		ClientResponse data = Client.create()
				.resource("https://api.mercadolibre.com/")
				.path(Url)
				.queryParams(params)
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
		
		return new JSONObject(obtenerJSON(enlace + idObjetivo).getEntity(String.class));
	}
	
	public JSONObject obtenerJSONPais(String Pais) {
		
		ClientResponse data = obtenerJSON("/classified_locations/countries");
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
		return new JSONObject(obtenerJSON("/currencies/" + infoPais.getString("currency_id")).getEntity(String.class));
	}
	
	public JSONObject obtenerRatioAPesos(String idMoneda){		
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		
		queryParams.add("from",idMoneda);
		queryParams.add("to","ARS");
		
		return new JSONObject(obtenerJSON("/currency_conversions/search",queryParams).getEntity(String.class));
	}
	
	public JSONArray obtenerMonedas() {
		return new JSONArray(obtenerJSON("/currencies/").getEntity(String.class));
	}
	
	public JSONArray obtenerPaises(){
		return new JSONArray(obtenerJSON("/classified_locations/countries").getEntity(String.class));		
	}
	
	public JSONArray obtenerProvincias(String Pais) {
		return obtenerJSONPais(Pais).getJSONArray("states");
	}
	
	public JSONArray obtenerCiudades(String Provincia, String Pais){
		return obtenerJSONProvincia(Provincia, Pais).getJSONArray("cities");
	}

	public List<String> obtenerListaCurrencies(){
		List<String> monedas = new ArrayList<String>();

		JSONArray array = this.obtenerMonedas();
		for(int i = 0 ;i < array.length() ;i++) {
			JSONObject item = array.getJSONObject(i);
			monedas.add((String)item.get("id"));
		}

		return monedas;
	}

	public List<String> obtenerListaPaises(){
		List<String> paises = new ArrayList<String>();

		JSONArray array = this.obtenerPaises();
		for(int i = 0 ;i < array.length() ;i++) {
			JSONObject item = array.getJSONObject(i);
			paises.add((String)item.get("id"));
		}

		return paises;
	}
	public List<String> obtenerListaProvincias(String pais){
		List<String> provincias = new ArrayList<String>();

		JSONArray array = this.obtenerProvincias(pais);
		for(int i = 0 ;i < array.length() ;i++) {
			JSONObject item = array.getJSONObject(i);
			provincias.add((String)item.get("id"));
		}

		return provincias;
	}
	public List<String> obtenerListaCiudades(String provincia){
		List<String> ciudades = new ArrayList<String>();

		JSONArray array = this.obtenerProvincias(provincia);
		for(int i = 0 ;i < array.length() ;i++) {
			JSONObject item = array.getJSONObject(i);
			ciudades.add((String)item.get("id"));
		}

		return ciudades;
	}

}

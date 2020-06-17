package model;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public interface InfoDireccionPostal{
	public JSONObject obtenerInfoPais(String Pais);
	
	public JSONObject obtenerInfoProvincia(String Provincia, String Pais);
			
	public JSONObject obtenerInfoCiudad(String Ciudad, String Provincia, String Pais);

}

package model;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class DireccionPostal {
	String calle, altura, piso, depto, ciudad, provincia, pais;
	
	ClientResponse dataPaises;
	public void infoPais() {
		dataPaises =  Client.create()
	    	       .resource("https://api.mercadolibre.com/")
	    	       .path("classified_locations/countries")
	    	       .accept(MediaType.APPLICATION_JSON) 
	    	       .get(ClientResponse.class);
	
	}
	

}

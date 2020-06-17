package model;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class DireccionPostal {
	String calle, altura, piso, depto, ciudad, provincia, pais;

	public DireccionPostal(String calle, String altura, String piso, String depto, String ciudad, String provincia,
			String pais) {

		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
	}
	
	
		

}

package ubicacion;

import javax.persistence.Embeddable;

@Embeddable
public class Ubicacion {
	private String pais, provincia, ciudad;
	
	public Ubicacion() {}
	
	public Ubicacion(String pais, String provincia, String ciudad) {
		this.pais = pais;
		this.provincia = provincia;
		this.ciudad = ciudad;
	}

}

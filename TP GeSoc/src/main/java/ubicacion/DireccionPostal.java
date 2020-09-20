package ubicacion;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class DireccionPostal {
	@Embedded
	private Direccion direccion;
	@Embedded
	private Ubicacion ubicacion;

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public DireccionPostal() {}
	
	public DireccionPostal(Direccion direccion, Ubicacion ubicacion) {
		this.direccion = direccion;
		this.ubicacion = ubicacion;
	}		

}

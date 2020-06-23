package ubicacion;

public class DireccionPostal {
	private Direccion direccion;
	private Ubicacion ubicacion;

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public DireccionPostal(Direccion direccion, Ubicacion ubicacion) {
		this.direccion = direccion;
		this.ubicacion = ubicacion;
	}		

}

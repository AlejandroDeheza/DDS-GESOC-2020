package model;

public class DireccionPostal {
	Direccion direccion;
	Ubicacion ubicacion;

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public DireccionPostal(Direccion direccion, Ubicacion ubicacion) {
		this.direccion = direccion;
		this.ubicacion = ubicacion;
	}		

}

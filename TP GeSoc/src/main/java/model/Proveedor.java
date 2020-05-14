package model;

public class Proveedor {
	String razonSocial;
	String direccionPostal;
	int cuil;

	public Proveedor(String razonSocial, String direccionPostal, int cuil) {
		this.razonSocial = razonSocial;
		this.direccionPostal = direccionPostal;
		this.cuil = cuil;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public int getCuil() {
		return cuil;
	}

	public void setCuil(int cuil) {
		this.cuil = cuil;
	}

}

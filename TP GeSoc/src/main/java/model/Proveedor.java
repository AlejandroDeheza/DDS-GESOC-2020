package model;

public class Proveedor {
	private String razonSocial;
	private String direccionPostal;
	private int cuil;

	public Proveedor(String razonSocial, String direccionPostal, int cuil) {
		this.razonSocial = razonSocial;
		this.direccionPostal = direccionPostal;
		this.cuil = cuil;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccionPostal() {
		return this.direccionPostal;
	}

	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public int getCuil() {
		return this.cuil;
	}

	public void setCuil(int cuil) {
		this.cuil = cuil;
	}

}

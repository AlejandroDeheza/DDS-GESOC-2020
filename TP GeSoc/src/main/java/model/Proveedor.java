package model;
public class Proveedor {
	String razonSocial;
	String direccionPostal;
	int dni;
	
	
	public Proveedor(String razonSocial, String direccionPostal, int dni) {
		this.razonSocial = razonSocial;
		this.direccionPostal = direccionPostal;
		this.dni = dni;
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

	public int getdni() {
		return dni;
	}

	public void setdni(int dni) {
		this.dni = dni;
	}
	
}

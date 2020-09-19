package model;

import javax.persistence.*;

import ubicacion.DireccionPostal;

@Entity
@Table(name = "Proveedores")
public class Proveedor {
	@Id
	@GeneratedValue
	private Long id;
	

	private String razonSocial;

	@Embedded
	private DireccionPostal direccionPostal;
	
	private int cuil;

	public Proveedor(String razonSocial, DireccionPostal direccionPostal, int cuil) {
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

	public DireccionPostal getDireccionPostal() {
		return this.direccionPostal;
	}

	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public int getCuil() {
		return this.cuil;
	}

	public void setCuil(int cuil) {
		this.cuil = cuil;
	}

}

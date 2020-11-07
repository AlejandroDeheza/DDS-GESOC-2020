package model;

import javax.persistence.*;

import ubicacion.DireccionPostal;

@Entity
@Table(name = "proveedores")
public class Proveedor {
	@Id
	@GeneratedValue
	@Column(name = "id_proveedor")
	private Long id;
	
	@Column(name = "razon_social")
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

	public Long getId() {
		return id;
	}

	public Proveedor() {}

}

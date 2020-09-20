package ubicacion;

import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
	private String calle, altura, piso, depto;
	
	public Direccion() {}
	
	public Direccion(String unaCalle, String unaAltura, String unPiso, String unDepto) {
		this.calle = unaCalle;
		this.altura = unaAltura;
		this.piso = unPiso;
		this.depto = unDepto;
	}
	
}

package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EtiquetaOperacion {

	@Column(name = "etiqueta")
	public String texto;
	
	public EtiquetaOperacion (String textoDeLaEtiqueta){
		texto=textoDeLaEtiqueta.toUpperCase();
	}
	public void setTexto(String textoDeLaEtiqueta) {
		texto=textoDeLaEtiqueta.toUpperCase();
	}
	public String getTexto() {
		return texto;
	}
	
	public EtiquetaOperacion() {}
}

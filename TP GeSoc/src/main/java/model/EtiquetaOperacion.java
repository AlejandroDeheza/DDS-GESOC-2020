package model;
public class EtiquetaOperacion {
	public String texto;
	public EtiquetaOperacion (String textoDeLaEtiqueta){
		texto=textoDeLaEtiqueta.toUpperCase();
	}
	public void setTexto(String textoDeLaEtiqueta) {
		texto=textoDeLaEtiqueta.toUpperCase();
	}
}
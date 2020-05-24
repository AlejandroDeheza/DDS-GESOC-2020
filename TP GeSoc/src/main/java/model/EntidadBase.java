package model;
public class EntidadBase {
	String nombreFicticio;
	String descripcion;
	EntidadJuridica entidadJuridica;
	
	public EntidadBase(String nombreFicticio, String descripcion) {

		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
	}
	
	public EntidadBase(String nombreFicticio, String descripcion, EntidadJuridica entidadJuridica) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
		this.entidadJuridica = entidadJuridica;
	}

}

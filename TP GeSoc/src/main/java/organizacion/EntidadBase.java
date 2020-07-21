package organizacion;

import java.util.ArrayList;
import java.util.List;

import model.OperacionDeEgreso;

public class EntidadBase {
	private String nombreFicticio;
	private String descripcion;
	private EntidadJuridica entidadJuridica;
	private List<OperacionDeEgreso> egresos = new ArrayList<>();
	
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

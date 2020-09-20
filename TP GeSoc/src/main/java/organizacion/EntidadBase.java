package organizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.CategoriaEntidad;
import model.OperacionDeEgreso;

@Entity
@Table(name = "Entidades_Base")
public class EntidadBase extends Entidad{
	private String descripcion;
	
	@ManyToOne
	public EntidadJuridica entidadJuridica;
	
	public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
		this.entidadJuridica=entidadJuridica;
	}
	
	public EntidadBase() {
		
	}
	
	public EntidadBase (String nombreFicticio, CategoriaEntidad categoriaEntidad, String descripcion, EntidadJuridica entidadJuridica) {
		this.nombreFicticio=nombreFicticio;
		this.categoriaEntidad=categoriaEntidad;
		this.descripcion=descripcion;
		this.entidadJuridica=entidadJuridica;
	}

	public void asociarAEntidadJuridica (EntidadJuridica entidadJuridica) {
		this.categoriaEntidad.asociarAEntidadJuridica(this, entidadJuridica);
	}
	
	/*
	public EntidadBase(String nombreFicticio, String descripcion) {

		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
	}
	
	public EntidadBase(String nombreFicticio, String descripcion, EntidadJuridica entidadJuridica) {
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
		this.entidadJuridica = entidadJuridica;
	}*/

	
	
	

}

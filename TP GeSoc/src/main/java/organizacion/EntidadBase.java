package organizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.CategoriaEntidad;
import model.OperacionDeEgreso;

@Entity
@Table(name = "entidades_base")
public class EntidadBase extends Entidad{
	private String descripcion;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "entidad_juridica")
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

	public void asociarAEntidadJuridica (EntidadJuridica entidadJ) {
		if(this.categoriaEntidad != null)
			this.categoriaEntidad.asociarAEntidadJuridica(this, entidadJ);
		else
			entidadJuridica = entidadJ;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public EntidadJuridica getEntidadJuridica() {
		return entidadJuridica;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

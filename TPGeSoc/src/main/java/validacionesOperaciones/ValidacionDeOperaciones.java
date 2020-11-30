package validacionesOperaciones;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import model.OperacionDeEgreso;


@Entity
@Table (name = "validaciones_de_operaciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_validacion")
public abstract class ValidacionDeOperaciones {
	@Id
	@GeneratedValue
	@Column(name = "id_validacion")
	private Long id;

	public String descripcion;
	
	public abstract boolean operacionValida(OperacionDeEgreso operacion);

	public Long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

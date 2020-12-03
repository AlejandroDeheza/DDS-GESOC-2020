package comportamientoEntidad;

import model.OperacionDeEgreso;
import organizacion.Entidad;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;

import javax.persistence.*;

@Entity
@Table (name = "comportamientos_categorias")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_comporamiento")
public class Comportamiento {

	@Id
	@GeneratedValue
	@Column(name = "id_comportamiento")
	private Long id;
	
	public void alAgregarEntidad(EntidadBase entidadB,EntidadJuridica entidadJ){
		
	}
		
	public void alAgregarEgreso(Entidad entidadB, OperacionDeEgreso egreso) {
		
	}

	public Comportamiento() {
	}
	
}

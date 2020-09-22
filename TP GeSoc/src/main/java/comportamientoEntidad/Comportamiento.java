package comportamientoEntidad;

import javax.persistence.*;

import model.*;
import organizacion.*;

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

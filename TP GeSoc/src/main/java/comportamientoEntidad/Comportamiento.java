package comportamientoEntidad;

import javax.persistence.*;

import model.*;
import organizacion.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Comportamiento {

	@Id
	@GeneratedValue
	private Long id;
	
	public void alAgregarEntidad(EntidadBase entidadB,EntidadJuridica entidadJ){
		
	}
		
	public void alAgregarEgreso(Entidad entidadB, OperacionDeEgreso egreso) {
		
	}
	
}

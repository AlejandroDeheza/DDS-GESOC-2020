package comportamientoEntidad;

import organizacion.*;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.*;

@Entity
@DiscriminatorValue("LIMITAR_AGREGADO_BASES")
public class PoderAgregarEntidadesBaseAJuridica extends Comportamiento{

	@Override
	public void alAgregarEntidad(EntidadBase entidadB, EntidadJuridica entidadJ) {
		throw new LaEntidadJuridicaNoAdmiteEntidadesBaseException();
	}
	
	public PoderAgregarEntidadesBaseAJuridica() {
	}
}

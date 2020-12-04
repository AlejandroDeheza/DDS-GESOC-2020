package comportamientoEntidad;

import exceptions.LaEntidadJuridicaNoAdmiteEntidadesBaseException;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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

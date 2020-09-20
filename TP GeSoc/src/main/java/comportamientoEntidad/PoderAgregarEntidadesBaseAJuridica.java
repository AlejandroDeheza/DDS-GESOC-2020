package comportamientoEntidad;

import organizacion.*;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.*;

@Entity
@DiscriminatorValue("Eb")
public class PoderAgregarEntidadesBaseAJuridica extends Comportamiento{

	@Override
	public void alAgregarEntidad(EntidadBase entidadB, EntidadJuridica entidadJ) {
		System.out.println("aca");
		throw new LaEntidadJuridicaNoAdmiteEntidadesBaseException();
	}
	
	public PoderAgregarEntidadesBaseAJuridica() {
	}
}

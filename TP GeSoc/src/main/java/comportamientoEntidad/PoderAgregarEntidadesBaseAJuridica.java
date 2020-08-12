package comportamientoEntidad;

import organizacion.*;
import exceptions.*;

public class PoderAgregarEntidadesBaseAJuridica extends Comportamiento{

	@Override
	public void alAgregarEntidad(EntidadBase entidadB, EntidadJuridica entidadJ) {
		throw new LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException();
	}
}

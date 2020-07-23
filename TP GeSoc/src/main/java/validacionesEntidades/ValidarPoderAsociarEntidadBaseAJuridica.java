package validacionesEntidades;

import organizacion.*;

public class ValidarPoderAsociarEntidadBaseAJuridica implements ValidacionEntidad {
	
	EntidadJuridica entidadALaQueNoPuedePertenecer;

	@Override
	public boolean entidadValida(Entidad entidad) {

		EntidadBase entBase = (EntidadBase) entidad;
		return entBase.entidadJuridica.ID != entidadALaQueNoPuedePertenecer.ID;
	}
	
	public ValidarPoderAsociarEntidadBaseAJuridica (EntidadJuridica entidadALaQueNoPuedePertenecer) {
		this.entidadALaQueNoPuedePertenecer=entidadALaQueNoPuedePertenecer;
	}

}

package comportamientoEntidad;

import exceptions.LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;

public class PoderAsociarEntidadJuridicaABase extends Comportamiento {

	EntidadJuridica entidadALaQueNoPuedePertenecer;
	
	
	
	public PoderAsociarEntidadJuridicaABase(EntidadJuridica entidadALaQueNoPuedePertenecer) {
		this.entidadALaQueNoPuedePertenecer = entidadALaQueNoPuedePertenecer;
	}

//	@Override
//	public void alAsociarEntidadJuridica(EntidadJuridica entidadJ) {	
//		if(entidadJ.ID != entidadALaQueNoPuedePertenecer.ID)
//			throw new LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException();
//	}
	
	@Override
	public void alAgregarEntidad(EntidadBase entidadB,EntidadJuridica entidadJ){	
		if(entidadJ.ID != entidadALaQueNoPuedePertenecer.ID)
			throw new LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException();
	}
}

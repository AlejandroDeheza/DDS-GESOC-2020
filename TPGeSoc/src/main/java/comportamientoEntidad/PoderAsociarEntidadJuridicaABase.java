package comportamientoEntidad;

import exceptions.LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;

import javax.persistence.*;

@Entity
@DiscriminatorValue("IMPEDIR_ASOCIAR_JURIDICA")
public class PoderAsociarEntidadJuridicaABase extends Comportamiento {

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "entidad_bloqueada")
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
		if(entidadJ.id == entidadALaQueNoPuedePertenecer.id)
			throw new LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException();
	}
	
	public PoderAsociarEntidadJuridicaABase() {
	}
}

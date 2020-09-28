package comportamientoEntidad;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import exceptions.LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;

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

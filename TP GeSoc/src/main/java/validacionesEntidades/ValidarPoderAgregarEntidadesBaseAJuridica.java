package validacionesEntidades;

import java.util.stream.Collectors;

import organizacion.*;

public class ValidarPoderAgregarEntidadesBaseAJuridica implements ValidacionEntidad {
	
	@Override
	public boolean entidadValida(Entidad entidad) {
		
		EntidadJuridica entJuridica = (EntidadJuridica)entidad;
		return !entJuridica.tieneAsociadasEntidadesBase;
	}
	
	public ValidarPoderAgregarEntidadesBaseAJuridica() {}
	
}

package model;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;
import organizacion.*;
import validacionesEntidades.*;

public class CategoriaEntidad {
	public String texto;
	List<ValidacionEntidad> validaciones = new ArrayList<>();
	
	public CategoriaEntidad(List<ValidacionEntidad> validaciones, String texto){
		this.validaciones = validaciones;
		this.texto = texto.toUpperCase();
	}
	
	public void setComportamientos(List<ValidacionEntidad> validaciones) {
		this.validaciones=validaciones;
	}
	
	public void agregarNuevoEgreso(Entidad entidad, OperacionDeEgreso egreso) {
		//Se agrega el egreso y si no pasa las validaciones se rollbackea el cambio. 
		entidad.egresos.add(egreso);
		if(!this.pasaLasValidaciones(entidad)) {
			entidad.egresos.removeIf(egre -> egre.IDOperacionDeEgreso == egreso.IDOperacionDeEgreso);
			throw new LaCantidadDeEgresosSuperaElMontoMaximoException();
		}
	}
	
	public void asociarAEntidadJuridica(EntidadBase entidadBase, EntidadJuridica entidadJuridica) {
		//Se hacen los cambios y si no pasan las validaciones se rollbackea. 
		EntidadJuridica entidadJuridicaAnterior = entidadBase.entidadJuridica;
		entidadBase.entidadJuridica = entidadJuridica;
		entidadJuridica.tieneAsociadasEntidadesBase = true;
		
		if(!this.pasaLasValidaciones(entidadBase)) {
			entidadBase.entidadJuridica = entidadJuridicaAnterior;
			entidadJuridica.tieneAsociadasEntidadesBase=false;
			throw new LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException();
		}
		else {
			if(!entidadJuridica.categoriaEntidad.pasaLasValidaciones(entidadJuridica)) {
				entidadBase.entidadJuridica = entidadJuridicaAnterior;
				entidadJuridica.tieneAsociadasEntidadesBase=false;
				throw new LaEntidadJuridicaNoAdmiteEntidadesBaseException();
			}
		}
	}
	
	public boolean pasaLasValidaciones(Entidad entidad) {
		return validaciones.stream().allMatch(validacion -> validacion.entidadValida(entidad));
	}
	
	
}

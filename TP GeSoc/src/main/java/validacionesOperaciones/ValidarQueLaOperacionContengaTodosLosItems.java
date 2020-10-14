package validacionesOperaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.OperacionDeEgreso;


@Entity
//@DiscriminatorValue("contega_todos_los_items")
@DiscriminatorValue("CONTIENE_ITEMS")
public class ValidarQueLaOperacionContengaTodosLosItems extends ValidacionDeOperaciones {
	
	public boolean pasaLaValidacion(OperacionDeEgreso operacion) {
		return operacion.getPresupuestosMinimos() == 0 || operacion.contieneItemsDelPresupuesto(operacion.getPresupuestoElegido());
	}

}

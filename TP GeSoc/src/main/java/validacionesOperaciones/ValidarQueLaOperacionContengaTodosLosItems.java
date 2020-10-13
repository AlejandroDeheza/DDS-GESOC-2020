package validacionesOperaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.OperacionDeEgreso;


@Entity
@DiscriminatorValue("contega_todos_los_items")
public class ValidarQueLaOperacionContengaTodosLosItems extends ValidacionDeOperaciones {
	
	public boolean pasaLaValidacion(OperacionDeEgreso operacion) {
		return operacion.presupuestosMinimos == 0 || operacion.contieneTodosLosItemsDelPresupuesto();
	}

}

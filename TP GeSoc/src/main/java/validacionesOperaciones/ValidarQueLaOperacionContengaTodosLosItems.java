package validacionesOperaciones;

import model.OperacionDeEgreso;
import model.Presupuesto;

public class ValidarQueLaOperacionContengaTodosLosItems implements ValidadorDeOperaciones {
	
	private boolean contieneTodosLosItemsDe(Presupuesto presupuesto, OperacionDeEgreso operacion) {
		return presupuesto.getItems().stream().allMatch(item -> operacion.contiene(item));
	}
	
	public boolean pasoCorrectamente(OperacionDeEgreso operacion) {
		return operacion.presupuestos.stream().anyMatch(presupuesto -> this.contieneTodosLosItemsDe(presupuesto,operacion));
	}

}

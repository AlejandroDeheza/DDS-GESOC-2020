package validacionesOperaciones;

import model.OperacionDeEgreso;
import model.Presupuesto;

public class ValidarQueLaOperacionContengaTodosLosItems implements ValidacionDeOperaciones {
	
	private boolean contieneTodosLosItemsDe(Presupuesto presupuesto, OperacionDeEgreso operacion) {
		return presupuesto.getItems().stream().allMatch(item -> operacion.contiene(item));
	}
	
	public boolean pasaLaValidacion(OperacionDeEgreso operacion) {
		//return operacion.presupuestos.stream().anyMatch(presupuesto -> this.contieneTodosLosItemsDe(presupuesto,operacion));
		return operacion.presupuestosMinimos == 0 || contieneTodosLosItemsDe(operacion.presupuestoElegido,operacion);
	}

}

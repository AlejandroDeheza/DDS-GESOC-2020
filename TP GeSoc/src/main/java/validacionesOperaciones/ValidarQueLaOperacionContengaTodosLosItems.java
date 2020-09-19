package validacionesOperaciones;

import model.OperacionDeEgreso;
import model.Presupuesto;

public class ValidarQueLaOperacionContengaTodosLosItems implements ValidadorDeOperaciones {
	
//	private boolean contieneTodosLosItemsDe(Presupuesto presupuesto, OperacionDeEgreso operacion) {
//		return presupuesto.getItems().stream().allMatch(item -> operacion.contiene(item));
//	}
	
//	public boolean pasoCorrectamente(OperacionDeEgreso operacion) {
//		return operacion.presupuestos.stream().anyMatch(presupuesto -> this.contieneTodosLosItemsDe(presupuesto,operacion));
//	}

	public boolean pasoCorrectamente(OperacionDeEgreso operacion) {
		return operacion.getPresupuestoElegido() != null || 
				operacion.presupuestos.stream().anyMatch(p -> p.equals(operacion.getPresupuestoElegido()));
	}//Tener en cuenta que tiene que ser la misma referencia al presupuesto.
}

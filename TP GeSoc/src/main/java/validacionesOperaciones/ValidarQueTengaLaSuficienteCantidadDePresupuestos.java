package validacionesOperaciones;

import model.OperacionDeEgreso;

public class ValidarQueTengaLaSuficienteCantidadDePresupuestos implements ValidacionDeOperaciones {

	@Override
	public boolean pasaLaValidacion(OperacionDeEgreso operacion) {
		return operacion.presupuestos.size() >= operacion.presupuestosMinimos;
	}

}

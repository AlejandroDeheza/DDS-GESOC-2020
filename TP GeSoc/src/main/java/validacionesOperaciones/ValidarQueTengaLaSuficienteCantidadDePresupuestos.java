package validacionesOperaciones;

import model.OperacionDeEgreso;

public class ValidarQueTengaLaSuficienteCantidadDePresupuestos implements ValidadorDeOperaciones {

	@Override
	public boolean pasoCorrectamente(OperacionDeEgreso operacion) {
		return operacion.presupuestos.size() >= operacion.presupuestosMinimos;
	}

}

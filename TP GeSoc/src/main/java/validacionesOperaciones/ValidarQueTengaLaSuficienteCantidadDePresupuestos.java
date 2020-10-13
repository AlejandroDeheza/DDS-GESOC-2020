package validacionesOperaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.OperacionDeEgreso;


@Entity
@DiscriminatorValue("cantidad_de_presupuestos_suficiente")
public class ValidarQueTengaLaSuficienteCantidadDePresupuestos extends ValidacionDeOperaciones {

	public boolean pasaLaValidacion(OperacionDeEgreso operacion) {
		return operacion.cantidadDePresupuestos() >= operacion.presupuestosMinimos;
	}

}

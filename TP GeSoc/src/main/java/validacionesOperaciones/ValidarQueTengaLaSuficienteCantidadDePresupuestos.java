package validacionesOperaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.OperacionDeEgreso;


@Entity
//@DiscriminatorValue("cantidad_de_presupuestos_suficiente")
@DiscriminatorValue("CANT_PRESUPUESTOS")
public class ValidarQueTengaLaSuficienteCantidadDePresupuestos extends ValidacionDeOperaciones {

	public boolean operacionValida(OperacionDeEgreso operacion) {
		return operacion.cantidadDePresupuestos() >= operacion.getPresupuestosMinimos();
	}
	public ValidarQueTengaLaSuficienteCantidadDePresupuestos(){
		this.descripcion = "Validar que la operación tenga cargada la cantidad mínima definida de presupuestos";
	}
}

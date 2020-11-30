package validacionesOperaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.OperacionDeEgreso;


@Entity
//@DiscriminatorValue("contega_todos_los_items")
@DiscriminatorValue("CONTIENE_ITEMS")
public class ValidarQueLaOperacionContengaTodosLosItems extends ValidacionDeOperaciones {

	public boolean operacionValida(OperacionDeEgreso operacion) {
		return operacion.getPresupuestosMinimos() == 0 || operacion.contieneItemsDelPrespuestoElegido();
	}

	public ValidarQueLaOperacionContengaTodosLosItems(){
		this.descripcion = "Validar que la operación contenga todos los items del presupuesto elegido";
	}

	public String getDescripcion() {
		return descripcion;
	}

}

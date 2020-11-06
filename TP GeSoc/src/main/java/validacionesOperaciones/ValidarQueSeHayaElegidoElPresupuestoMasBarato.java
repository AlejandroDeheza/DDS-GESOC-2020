package validacionesOperaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.OperacionDeEgreso;

@Entity
//@DiscriminatorValue("presupuesto_mas_barato")
@DiscriminatorValue("PRES_MAS_BARATO")
public class ValidarQueSeHayaElegidoElPresupuestoMasBarato extends ValidacionDeOperaciones{
	
	public boolean operacionValida(OperacionDeEgreso operacion) {
		return operacion.getPresupuestosMinimos()==0 || operacion.menorPrecioDePresupuestos().equals(operacion.valorTotalDelPresupuestoElegido());
	}
	public ValidarQueSeHayaElegidoElPresupuestoMasBarato(){
		this.descripcion = "Validar que se haya elegido el presupuesto más barato de los disponibles";
	}

}

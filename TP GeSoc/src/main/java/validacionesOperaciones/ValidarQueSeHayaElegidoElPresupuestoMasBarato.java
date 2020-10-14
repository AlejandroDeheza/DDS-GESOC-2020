package validacionesOperaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.OperacionDeEgreso;

@Entity
//@DiscriminatorValue("presupuesto_mas_barato")
@DiscriminatorValue("PRES_MAS_BARATO")
public class ValidarQueSeHayaElegidoElPresupuestoMasBarato extends ValidacionDeOperaciones{
	
	public boolean pasaLaValidacion(OperacionDeEgreso operacion) {
		return operacion.getPresupuestosMinimos()==0 || operacion.menorPrecioDePresupuestos().equals(operacion.valorTotalDelPresupuestoElegido());
	}

}
